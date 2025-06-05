package org.example;

import java.util.List;
import java.util.Optional;

import static java.nio.file.Files.find;

public class ResultTableCollection {
    private List<ResultTable> resultTables;

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
