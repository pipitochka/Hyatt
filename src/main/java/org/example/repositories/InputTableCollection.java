package org.example.repositories;

import org.example.domain.InputTable;

import java.util.*;

public class InputTableCollection {

    List<InputTable> inputTableList = new ArrayList<>();

    public void add(InputTable inputTable) {
        if (inputTableList.isEmpty()){
            firstDay = inputTable.getArrivalDate();
            lastDay = inputTable.getDepartureDate();
        }

        inputTableList.add(inputTable);
        if (inputTable.getArrivalDate().before(firstDay)) {
            firstDay = inputTable.getArrivalDate();
        }
        if (inputTable.getDepartureDate().after(lastDay)) {
            lastDay = inputTable.getDepartureDate();
        }
    }

    Date firstDay;
    Date lastDay;
}
