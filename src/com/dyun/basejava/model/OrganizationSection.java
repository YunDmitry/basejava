package com.dyun.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection implements Section {
    private List<Organization> table;

    public OrganizationSection(Organization... table) {
        this(Arrays.asList(table));
    }

    public OrganizationSection(List<Organization> table) {
        Objects.requireNonNull(table, "table must not be null");
        this.table = table;
    }

    public List<Organization> getTable() {
        return table;
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (Organization t : table) {
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

        OrganizationSection that = (OrganizationSection) o;

        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return table.hashCode();
    }
}
