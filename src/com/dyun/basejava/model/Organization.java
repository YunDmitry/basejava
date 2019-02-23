package com.dyun.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private Link titleLink;
    private List<OrganizationTimeEntry> list;

    public Organization(String title, String titleUrl, List<OrganizationTimeEntry> list) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(list, "list must not be null");
        this.titleLink = new Link(title, titleUrl);
        this.list = list;
    }

    @Override
    public String toString() {
        String titleText = "";
        if (titleLink != null) {
            titleText = titleLink.getName() + (titleLink.getUrl() != null ? "(" + titleLink.getUrl() + ")" : "") + "\n";
        }
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (OrganizationTimeEntry t : list) {
            result.append(t.toString());
            if (i++ != list.size()) {
                result.append("\n");
            }
        }
        return titleText + result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!titleLink.equals(that.titleLink)) return false;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result = titleLink.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }
}

