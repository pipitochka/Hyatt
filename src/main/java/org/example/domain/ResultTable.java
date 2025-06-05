package org.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResultTable {
    private final String companyName;

    List<Double> sumByDay = new ArrayList<>();

    double finalSum;
}
