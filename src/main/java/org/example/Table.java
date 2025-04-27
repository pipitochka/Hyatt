package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Table {
    private final String date;
    private final String number;
    private final String contractor;
    private final String fullName;
    private final String inn;
    private final String nomenclature;
    private final String description;
    private final double nds;
    private final double quantity;
    private final double price;
    private final double sum;
    private Date arrivalDate;   // Добавляем поле для даты приезда
    private Date departureDate;

    private static final String DATE_PATTERN = "(\\d{2}\\.\\d{2}\\.\\d{4})";  // Паттерн для даты (например, 31.03.2025)


    public String getContractor() {
        return contractor;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Table(String date, String number, String contractor, String fullName,
                 String inn, String nomenclature, String description,
                 String nds, String quantity, String price, String sum) throws Exception{

        this.date = date;
        this.number = number;
        this.contractor = contractor;
        this.fullName = fullName;
        this.inn = inn;
        this.nomenclature = nomenclature;
        this.description = description;
        this.nds = Double.parseDouble(nds.replace("%", ""));
        this.quantity = Double.parseDouble(quantity.replace(" ", "").replace(",", "."));
        this.price = Double.parseDouble(price.replace(" ", "").replace(",", "."));
        this.sum = Double.parseDouble(sum.replace(" ", "").replace(",", "."));

        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(description);

        // Если нашли хотя бы одну дату, извлекаем её
        if (matcher.find()) {
            try {
                // Извлекаем первую дату как дату приезда
                String arrivalDateStr = matcher.group(1);
                arrivalDate = new SimpleDateFormat("dd.MM.yyyy").parse(arrivalDateStr);

                // Если нашли вторую дату, то извлекаем её как дату отъезда
                if (matcher.find()) {
                    String departureDateStr = matcher.group(1);
                    departureDate = new SimpleDateFormat("dd.MM.yyyy").parse(departureDateStr);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(arrivalDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    departureDate = calendar.getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new Exception("Invalid description");
            }
        }
    }


}
