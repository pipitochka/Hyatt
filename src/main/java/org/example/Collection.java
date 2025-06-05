package org.example;

import java.util.*;

public class Collection {

    List<Table> tableList = new ArrayList<>();

    public void add(Table table) {
        tableList.add(table);
    }

    Date firstDay;
    Date lastDay;

    private void calculateFirstAndLastDay() {
        firstDay = tableList.getFirst().getArrivalDate();
        lastDay = tableList.getLast().getDepartureDate();

        for (Table table : tableList) {
            if (table.getArrivalDate().before(firstDay)) {
                firstDay = table.getArrivalDate();
            }
            if (table.getDepartureDate().after(lastDay)) {
                lastDay = table.getDepartureDate();
            }
        }
    }

    private Map<String, List<Table>> buildUniqueContagentTable() {
        Map<String, List<Table>> contagentTable = new HashMap<>();

        for (Table table : tableList) {
            // Если контрагент еще не добавлен в таблицу, создаем новый список для его записей
            contagentTable.computeIfAbsent(table.getContractor(), k -> new ArrayList<>()).add(table);
        }

        return contagentTable;
    }

    public void caclucate(ResultTableCollection resultTableCollection) {
        for (Table table : tableList) {
            resultTableCollection.checkTable(table.getContractor());
            var first = table.getArrivalDate();
            var last = table.getDepartureDate();
            long diffMillis = first.getTime() - firstDay.getTime();
        }
    }

}
