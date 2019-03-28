package com.dyun.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapResumeStorageTest.class,
        FileStorageObjectStreamTest.class,
        PathStorageObjectStreamTest.class,
        PathStorageXmlStreamTest.class,
        PathStorageJsonStreamTest.class,
        PathStorageDataStreamTest.class,
        SqlStorageTest.class
})

public class AllStorageTest {
}
