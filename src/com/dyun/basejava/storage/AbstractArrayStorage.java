package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Abstract Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_STORAGE_SIZE = 10_000;

    protected Resume[] storage = new Resume[MAX_STORAGE_SIZE];

    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean checkKey(Object key) {
        return (Integer) key > -1;
    }

    @Override
    protected Resume getElement(int key, String keyString) {
        return storage[key];
    }

    @Override
    protected void addElement(int key, Resume resume) {
        if (size < MAX_STORAGE_SIZE) {
            addResume(key, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected void removeElement(int key) {
        removeResume(key);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void addResume(int index, Resume resume);

    protected abstract void removeResume(int index);

}
