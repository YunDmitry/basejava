package com.dyun.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
        Assert.assertTrue(true);
    }
}