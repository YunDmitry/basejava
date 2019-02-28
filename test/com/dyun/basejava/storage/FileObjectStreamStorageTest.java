package com.dyun.basejava.storage;

public class FileObjectStreamStorageTest extends AbstractStorageTest {

    public FileObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR_FILE, new ObjectStreamSerialization()));
    }
}