package com.dyun.basejava.storage;

import com.dyun.basejava.storage.serialization.ObjectStreamSerialization;

public class PathStorageObjectStreamTest extends AbstractStorageTest {

    public PathStorageObjectStreamTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}