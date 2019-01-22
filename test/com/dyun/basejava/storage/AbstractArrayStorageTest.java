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
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";
    private static final Resume resume1 = new Resume(UUID1);
    private static final Resume resume2 = new Resume(UUID2);
    private static final Resume resume3 = new Resume(UUID3);
    private static final Resume resume4 = new Resume(UUID4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume2);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            storage.clear();
            for (int i = 1; i <= AbstractArrayStorage.MAX_STORAGE_SIZE; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Error before storage is filled");
        }

        storage.save(new Resume("UUID_overflow"));
    }

    @Test
    public void update() {
        Resume resumeNew = new Resume(UUID3);
        storage.update(resumeNew);
        if (storage.get(UUID3) != resumeNew) {
            Assert.fail("Resume was not updated correctly");
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void delete() {
        storage.delete(UUID1);
        Assert.assertEquals(2, storage.size());
        try {
            storage.get(UUID1);
            Assert.fail("NotExistStorageException expected while getting deleted resume");
        } catch (NotExistStorageException ignored) {
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(new Resume[]{resume1, resume2, resume3}, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}