package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание окна
        JFrame frame = new JFrame("CSV Viewer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель для размещения элементов
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Кнопка для загрузки файла
        JButton button = new JButton("Загрузить CSV");
        panel.add(button, BorderLayout.NORTH);

        // Создаем таблицу для отображения данных
        JTable table = new JTable();
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        // Добавляем обработчик нажатия на кнопку
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открыть диалоговое окно для выбора файла
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Загружаем CSV файл
                        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

                        FileReader fileReader = new FileReader(selectedFile);
                        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                                .withCSVParser(parser)
                                .build();

                        // Читаем все строки из CSV файла
                        List<String[]> rows = csvReader.readAll();
                        csvReader.close();

                        // Получаем названия столбцов (первая строка CSV)
                        String[] columnNames = rows.get(0);
                        // Получаем данные для таблицы
                        String[][] data = new String[rows.size() - 1][columnNames.length];

                        // Заполняем таблицу данными
                        for (int i = 1; i < rows.size(); i++) {
                            data[i - 1] = rows.get(i);
                        }

                        InputTableCollection inputTableCollection = new InputTableCollection();

                        for (int i = 0; i < data.length; i++) {
                            try {
                                InputTable newtable = new InputTable(data[i][0], data[i][1], data[i][2], data[i][3],
                                        data[i][4], data[i][5], data[i][6], data[i][7],
                                        data[i][8], data[i][9], data[i][10]);
                                inputTableCollection.add(newtable);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame,
                                        "Ошибка в строке " + (i + 1) + ": " + ex.getMessage(),
                                        "Ошибка данных", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        }

                        ResultTableCollection resultTableCollection = new ResultTableCollection();

                        Analyser analyser = new Analyser(inputTableCollection, resultTableCollection);

                        analyser.analyse();

                        table.setModel(new javax.swing.table.DefaultTableModel(resultTableCollection.getData(),
                                resultTableCollection.columnNames));

                    } catch (IOException | CsvException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Ошибка при загрузке файла: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
