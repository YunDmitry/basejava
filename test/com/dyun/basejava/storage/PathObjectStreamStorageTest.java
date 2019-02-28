package com.dyun.basejava.storage;

public class PathObjectStreamStorageTest extends AbstractStorageTest {

    public PathObjectStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}