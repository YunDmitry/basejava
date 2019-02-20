package com.dyun.basejava.model;

import java.util.List;

public class ListSection implements Section {
    private List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void addElement(String value) {
        this.list.add(value);
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (String str : list) {
            result.append(" * ").append(str);
            if (i++ != list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
