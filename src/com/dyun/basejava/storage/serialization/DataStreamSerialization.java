package com.dyun.basejava.storage.serialization;

import com.dyun.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerialization implements SerializationStrategy {

    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeForEach(dos, resume.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeForEach(dos, resume.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getDescription());
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        writeForEach(dos, ((ListSection) section).getList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeForEach(dos, ((OrganizationSection) section).getTable(), orgEntry -> {
                            Link titleLink = orgEntry.getTitleLink();
                            dos.writeUTF(titleLink.getName());
                            dos.writeUTF(titleLink.getUrl() != null ? titleLink.getUrl() : "");
                            writeForEach(dos, orgEntry.getList(), orgTimeEntry -> {
                                dos.writeUTF(orgTimeEntry.getDateFrom().toString());
                                dos.writeUTF(orgTimeEntry.getDateTo().toString());
                                dos.writeUTF(orgTimeEntry.getName());
                                dos.writeUTF(orgTimeEntry.getDescription() != null ? orgTimeEntry.getDescription() : "");
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readForEach(dis, () -> {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contactValue = dis.readUTF();
                resume.setContact(contactType, contactValue);
            });
            readForEach(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = readForEachList(dis, dis::readUTF);
                        resume.setSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgList = readForEachList(dis, () -> {
                            String linkName = dis.readUTF();
                            String linkUrl = dis.readUTF();
                            Link link = new Link(linkName, !linkUrl.equals("") ? linkUrl : null);
                            List<Organization.OrganizationTimeEntry> orgTimeEntries = readForEachList(dis, () -> {
                                LocalDate dateFrom = LocalDate.parse(dis.readUTF());
                                LocalDate dateTo = LocalDate.parse(dis.readUTF());
                                String name = dis.readUTF();
                                String description = dis.readUTF();
                                return new Organization.OrganizationTimeEntry(
                                        dateFrom, dateTo, name, !description.equals("") ? description : null);
                            });
                            return new Organization(link, orgTimeEntries);
                        });
                        resume.setSection(sectionType, new OrganizationSection(orgList));
                        break;
                }
            });
            return resume;
        }
    }

    private interface ConsumerException<T> {
        void action(T t) throws IOException;
    }

    private <T> void writeForEach(DataOutputStream dos, Collection<T> collection, ConsumerException<T> action) throws IOException {
        Objects.requireNonNull(dos, "dos must not be null");
        Objects.requireNonNull(collection, "collection must not be null");
        Objects.requireNonNull(action, "action must not be null");
        dos.writeInt(collection.size());
        for (T element : collection) {
            action.action(element);
        }
    }

    private interface ReaderException {
        void read() throws IOException;
    }

    private void readForEach(DataInputStream dis, ReaderException action) throws IOException {
        Objects.requireNonNull(dis, "dis must not be null");
        Objects.requireNonNull(action, "action must not be null");
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.read();
        }
    }

    private interface ListReaderException<T> {
        T read() throws IOException;
    }

    private <T> List<T> readForEachList(DataInputStream dis, ListReaderException<T> action) throws IOException {
        Objects.requireNonNull(dis, "dis must not be null");
        Objects.requireNonNull(action, "action must not be null");
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(action.read());
        }
        return list;
    }
}
