package com.dyun.basejava.model;

import java.time.LocalDate;
import java.util.List;

public class TableSection implements Section {
    private List<TableSectionElement> table;

    public TableSection(List<TableSectionElement> table) {
        this.table = table;
    }

    public void addElement(String title, LocalDate dateFrom, LocalDate dateTo, String name, String description) {
        table.add(new TableSectionElement(title, dateFrom, dateTo, name, description));
    }

    public List<TableSectionElement> getTable() {
        return table;
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (TableSectionElement t : table) {
            result.append(t.toString());
            if (i++ != table.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableSection that = (TableSection) o;

        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return table.hashCode();
    }
}
