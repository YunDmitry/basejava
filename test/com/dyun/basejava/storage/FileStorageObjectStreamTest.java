package com.dyun.basejava.storage;

public class FileStorageObjectStreamTest extends AbstractStorageTest {

    public FileStorageObjectStreamTest() {
        super(new FileStorage(STORAGE_DIR_FILE, new ObjectStreamSerialization()));
    }
}