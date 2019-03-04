package com.dyun.basejava.storage.serialization;

import com.dyun.basejava.model.*;
import com.dyun.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerialization implements SerializationStrategy {
    private XmlParser xmlParser;

    public XmlStreamSerialization() {
        xmlParser = new XmlParser(Resume.class, OrganizationSection.class, Organization.class, Link.class,
                Organization.OrganizationTimeEntry.class, ListSection.class, TextSection.class);
    }

    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }
}
