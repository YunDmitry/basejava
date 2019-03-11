package com.dyun.basejava.storage.serialization;

import com.dyun.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerialization implements SerializationStrategy {

    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                String sectionClass = entry.getValue().getClass().getName();
                dos.writeUTF(sectionClass);
                switch (sectionClass) {
                    case ("com.dyun.basejava.model.TextSection"):
                        TextSection textSection = (TextSection) entry.getValue();
                        dos.writeUTF(textSection.getDescription());
                        break;
                    case ("com.dyun.basejava.model.ListSection"):
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getList();
                        dos.writeInt(list.size());
                        for (String str : list) {
                            dos.writeUTF(str);
                        }
                        break;
                    case ("com.dyun.basejava.model.OrganizationSection"):
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> orgList = organizationSection.getTable();
                        dos.writeInt(orgList.size());
                        for (Organization org : orgList) {
                            Link titleLink = org.getTitleLink();
                            dos.writeUTF(titleLink.getName());
                            dos.writeUTF(titleLink.getUrl() != null ? titleLink.getUrl() : "");
                            List<Organization.OrganizationTimeEntry> orgTimeEntries = org.getList();
                            dos.writeInt(orgTimeEntries.size());
                            for (Organization.OrganizationTimeEntry orgTimeEntry : orgTimeEntries) {
                                dos.writeUTF(orgTimeEntry.getDateFrom().toString());
                                dos.writeUTF(orgTimeEntry.getDateTo().toString());
                                dos.writeUTF(orgTimeEntry.getName());
                                dos.writeUTF(orgTimeEntry.getDescription() != null ? orgTimeEntry.getDescription() : "");
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contactValue = dis.readUTF();
                resume.setContact(contactType, contactValue);
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                String sectionClass = dis.readUTF();
                switch (sectionClass) {
                    case ("com.dyun.basejava.model.TextSection"):
                        resume.setSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ("com.dyun.basejava.model.ListSection"):
                        int listSize = dis.readInt();
                        List<String> list = new ArrayList<>(listSize);
                        for (int j = 0; j < listSize; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.setSection(sectionType, new ListSection(list));
                        break;
                    case ("com.dyun.basejava.model.OrganizationSection"):
                        int orgListSize = dis.readInt();
                        List<Organization> orgList = new ArrayList<>(orgListSize);
                        for (int j = 0; j < orgListSize; j++) {
                            String linkName = dis.readUTF();
                            String linkUrl = dis.readUTF();
                            Link link = new Link(linkName, !linkUrl.equals("") ? linkUrl : null);
                            int orgTimeEntriesSize = dis.readInt();
                            List<Organization.OrganizationTimeEntry> orgTimeEntries = new ArrayList<>(orgTimeEntriesSize);
                            for (int k = 0; k < orgTimeEntriesSize; k++) {
                                LocalDate dateFrom = LocalDate.parse(dis.readUTF());
                                LocalDate dateTo = LocalDate.parse(dis.readUTF());
                                String name = dis.readUTF();
                                String description = dis.readUTF();
                                orgTimeEntries.add(new Organization.OrganizationTimeEntry(
                                        dateFrom, dateTo, name, !description.equals("") ? description : null));
                            }
                            orgList.add(new Organization(link, orgTimeEntries));
                        }
                        resume.setSection(sectionType, new OrganizationSection(orgList));
                        break;
                }
            }
            return resume;
        }
    }
}
