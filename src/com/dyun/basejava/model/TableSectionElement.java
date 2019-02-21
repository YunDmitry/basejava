package com.dyun.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TableSectionElement {
    private final static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
    private Link titleLink;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String name;
    private String description;

    public TableSectionElement(String title, String titleUrl, LocalDate dateFrom, LocalDate dateTo, String name, String description) {
        Objects.requireNonNull(dateFrom, "dateFrom must not be null");
        Objects.requireNonNull(name, "name must not be null");
        if (title != null) {
            this.titleLink = new Link(title, titleUrl);
        }
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        String titleText = "";
        if (titleLink != null) {
            titleText = titleLink.getName() + (titleLink.getUrl() != null ? "(" + titleLink.getUrl() + ")" : "") + "\n";
        }
        String dateFromText = dateFrom.format(DATEFORMATTER);
        String dateToText = "Сейчас ";
        if (dateTo != null) {
            dateToText = dateTo.format(DATEFORMATTER);
        }
        String nameText = "";
        if (name != null) {
            nameText = name;
        }
        String descriptionText = "";
        if (description != null) {
            descriptionText = "\n                     " + description;
        }
        return titleText + dateFromText + " - " + dateToText + "    " + nameText + descriptionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableSectionElement that = (TableSectionElement) o;

        if (titleLink != null ? !titleLink.equals(that.titleLink) : that.titleLink != null) return false;
        if (!dateFrom.equals(that.dateFrom)) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (!name.equals(that.name)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = titleLink != null ? titleLink.hashCode() : 0;
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

