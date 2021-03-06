package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.MAX_STORAGE_SIZE; i++) {
                storage.save(new Resume("fullName_" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Error before storage is filled");
        }

        storage.save(new Resume("UUID_overflow"));
    }
}
