package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private Resume r1;
    private Resume r2;
    private Resume r3;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        r1 = new Resume(UUID1);
        storage.save(r1);
        r2 = new Resume(UUID2);
        storage.save(r2);
        r3 = new Resume(UUID3);
        storage.save(r3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume r4 = new Resume(UUID4);
        storage.save(r4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r2);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= 10_000; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (Exception e) {
            Assert.fail();
        }

        storage.save(new Resume("UUID_overflow"));
    }

    @Test
    public void update() {
        storage.update(r2);
    }

    @Test
    public void delete() {
        storage.delete(UUID1);
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(r2, storage.get(UUID2));
        Assert.assertEquals(r3, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(new Resume[]{r1, r2, r3}, storage.getAll());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}