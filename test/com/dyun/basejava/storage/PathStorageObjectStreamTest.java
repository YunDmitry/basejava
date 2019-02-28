package com.dyun.basejava.storage;

public class PathStorageObjectStreamTest extends AbstractStorageTest {

    public PathStorageObjectStreamTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}