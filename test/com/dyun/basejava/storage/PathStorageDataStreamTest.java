package com.dyun.basejava.storage;

import com.dyun.basejava.storage.serialization.DataStreamSerialization;

public class PathStorageDataStreamTest extends AbstractStorageTest {

    public PathStorageDataStreamTest() {
        super(new PathStorage(STORAGE_DIR, new DataStreamSerialization()));
    }
}