package com.dyun.basejava.storage;

import com.dyun.basejava.storage.serialization.JsonStreamSerialization;

public class PathStorageJsonStreamTest extends AbstractStorageTest {

    public PathStorageJsonStreamTest() {
        super(new PathStorage(STORAGE_DIR, new JsonStreamSerialization()));
    }
}