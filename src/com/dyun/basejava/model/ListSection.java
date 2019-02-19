package com.dyun.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection implements Section {
    private List<String> list = new ArrayList<>();

    public ListSection() {
    }

    public List<String> getList() {
        return list;
    }

    public void addElement(String value) {
        this.list.add(value);
    }

    @Override
    public void print() {
        for (String string : list) {
            System.out.println(" * " + string);
        }
    }
}
