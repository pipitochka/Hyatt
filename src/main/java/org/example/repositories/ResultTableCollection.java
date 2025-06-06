package org.example.repositories;

import org.example.domain.ResultTable;

import java.util.List;
import java.util.Optional;

public class ResultTableCollection {
    private List<ResultTable> resultTables;

    String[] columnNames;

    public String[][] getData(){
        return new String[0][0];
    };

    public Optional<ResultTable> getResultTable(String tableName) {
        return resultTables.stream()
                .filter(table -> table.getCompanyName().equals(tableName))
                .findFirst();

    }

    public void checkTable(String tableName) {
        var table = getResultTable(tableName);
        if (!table.isPresent()) {
            ResultTable resultTable = new ResultTable(tableName);
            resultTables.add(resultTable);
        }
    }
}
