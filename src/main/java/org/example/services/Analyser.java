package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.repositories.InputTableCollection;
import org.example.repositories.ResultTableCollection;

@RequiredArgsConstructor
public class Analyser {
    private final InputTableCollection inputTableCollection;

    private final ResultTableCollection resultTableCollection;

    public void analyse() {}

}
