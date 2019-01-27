package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListStorageTest {
    private Storage storage = new ListStorage();
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID1);
    private static final Resume RESUME_2 = new Resume(UUID2);
    private static final Resume RESUME_3 = new Resume(UUID3);
    private static final Resume RESUME_4 = new Resume(UUID4);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(RESUME_4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_2);
    }

    @Test
    public void update() {
        Resume resumeNew = new Resume(UUID3);
        storage.update(resumeNew);
        Assert.assertSame(resumeNew, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        try {
            storage.delete(UUID1);
            Assert.assertEquals(2, storage.size());
        } catch (StorageException e) {
            Assert.fail("Error while deleting");
        }
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID4);
    }

    @Test
    public void getAll() {
        Assert.assertSame(RESUME_1, storage.get(UUID1));
        Assert.assertSame(RESUME_2, storage.get(UUID2));
        Assert.assertSame(RESUME_3, storage.get(UUID3));
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}