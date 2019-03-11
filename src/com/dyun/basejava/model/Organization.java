package com.dyun.basejava.model;

import com.dyun.basejava.util.DateUtil;
import com.dyun.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.dyun.basejava.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private final static long serailversionUID = 1L;

    private Link titleLink;
    private List<OrganizationTimeEntry> list;

    public Organization() {
    }

    public Organization(String title, String titleUrl, OrganizationTimeEntry... list) {
        this(new Link(title, titleUrl), Arrays.asList(list));
    }

    public Organization(Link titleLink, List<OrganizationTimeEntry> list) {
        Objects.requireNonNull(titleLink, "titleLink must not be null");
        Objects.requireNonNull(list, "list must not be null");
        this.titleLink = titleLink;
        this.list = list;
    }

    public List<OrganizationTimeEntry> getList() {
        return list;
    }

    public Link getTitleLink() {
        return titleLink;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class OrganizationTimeEntry implements Serializable {
        private final static long serailversionUID = 1L;
        private final static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateFrom;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateTo;

        private String name;
        private String description;

        public OrganizationTimeEntry() {
        }

        public OrganizationTimeEntry(int dateFromYear, Month dateFromMonth, String name, String description) {
            this(DateUtil.of(dateFromYear, dateFromMonth), NOW, name, description);
        }

        public OrganizationTimeEntry(int dateFromYear, Month dateFromMonth, int dateToYear, Month dateToMonth, String name, String description) {
            this(DateUtil.of(dateFromYear, dateFromMonth), DateUtil.of(dateToYear, dateToMonth), name, description);
        }

        public OrganizationTimeEntry(LocalDate dateFrom, LocalDate dateTo, String name, String description) {
            Objects.requireNonNull(dateFrom, "dateFrom must not be null");
            Objects.requireNonNull(dateTo, "dateTo must not be null");
            Objects.requireNonNull(name, "name must not be null");
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.name = name;
            this.description = description;
        }

        public LocalDate getDateFrom() {
            return dateFrom;
        }

        public LocalDate getDateTo() {
            return dateTo;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            String dateFromText = dateFrom.format(DATEFORMATTER);
            String dateToText = "Сейчас ";
            if (dateTo.isBefore(NOW)) {
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
            return dateFromText + " - " + dateToText + "    " + nameText + descriptionText;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrganizationTimeEntry that = (OrganizationTimeEntry) o;

            if (!dateFrom.equals(that.dateFrom)) return false;
            if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
            if (!name.equals(that.name)) return false;
            return description != null ? description.equals(that.description) : that.description == null;
        }

        @Override
        public int hashCode() {
            int result = dateFrom.hashCode();
            result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
            result = 31 * result + name.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}

