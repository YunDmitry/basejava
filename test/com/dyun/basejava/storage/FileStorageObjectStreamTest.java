package com.dyun.basejava.storage;

import com.dyun.basejava.storage.serialization.ObjectStreamSerialization;

import java.io.File;

public class FileStorageObjectStreamTest extends AbstractStorageTest {

    public FileStorageObjectStreamTest() {
        super(new FileStorage(new File(STORAGE_DIR), new ObjectStreamSerialization()));
    }
}