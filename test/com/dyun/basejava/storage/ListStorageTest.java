package com.dyun.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
        Assert.assertTrue(true);
    }
}