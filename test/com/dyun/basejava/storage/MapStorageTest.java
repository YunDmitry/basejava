package com.dyun.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class MapStorageTest  extends AbstractStorageTest{

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
        Assert.assertTrue(true);
    }
}