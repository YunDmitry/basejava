package com.dyun.basejava.storage;

import com.dyun.basejava.storage.serialization.XmlStreamSerialization;

public class PathStorageXmlStreamTest extends AbstractStorageTest {

    public PathStorageXmlStreamTest() {
        super(new PathStorage(STORAGE_DIR, new XmlStreamSerialization()));
    }
}