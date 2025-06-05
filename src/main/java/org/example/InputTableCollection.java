package org.example;

import java.util.*;

public class InputTableCollection {

    List<InputTable> inputTableList = new ArrayList<>();

    public void add(InputTable inputTable) {
        inputTableList.add(inputTable);
    }

    Date firstDay;
    Date lastDay;

    private void calculateFirstAndLastDay() {
        firstDay = inputTableList.getFirst().getArrivalDate();
        lastDay = inputTableList.getLast().getDepartureDate();

        for (InputTable inputTable : inputTableList) {
            if (inputTable.getArrivalDate().before(firstDay)) {
                firstDay = inputTable.getArrivalDate();
            }
            if (inputTable.getDepartureDate().after(lastDay)) {
                lastDay = inputTable.getDepartureDate();
            }
        }
    }

    private Map<String, List<InputTable>> buildUniqueContagentTable() {
        Map<String, List<InputTable>> contagentTable = new HashMap<>();

        for (InputTable inputTable : inputTableList) {
            // Если контрагент еще не добавлен в таблицу, создаем новый список для его записей
            contagentTable.computeIfAbsent(inputTable.getContractor(), k -> new ArrayList<>()).add(inputTable);
        }

        return contagentTable;
    }
}
