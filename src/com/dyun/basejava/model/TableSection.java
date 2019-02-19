package com.dyun.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TableSection implements Section {
    private final static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
    private List<TableSectionElelement> table = new ArrayList<>();

    private class TableSectionElelement {
        private String title;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private String name;
        private String description;

        private TableSectionElelement(String title, LocalDate dateFrom, LocalDate dateTo, String name, String description) {
            this.title = title;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.name = name;
            this.description = description;
        }

        private void print() {
            if (title != null) {
                System.out.println(title);
            }
            String dateFromText = dateFrom.format(DATEFORMATTER);
            String dateToText = "Сейчас ";
            if (dateTo != null) {
                dateToText = dateTo.format(DATEFORMATTER);
            }
            String descriptionText = "";
            if (description != null) {
                descriptionText = "\n                     " + description;
            }
            System.out.println(dateFromText + " - " + dateToText + "    " + name + descriptionText);
        }
    }

    public void addElement(String title, LocalDate dateFrom, LocalDate dateTo, String name, String description) {
        table.add(new TableSectionElelement(title, dateFrom, dateTo, name, description));
    }

    public List<TableSectionElelement> getTable() {
        return table;
    }

    @Override
    public void print() {
        for (TableSectionElelement t : table) {
            t.print();
        }
    }
}
