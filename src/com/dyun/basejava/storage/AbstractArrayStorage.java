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
        int index = (Integer) key;
        return index > -1;
    }

    @Override
    protected Resume getElement(Object key) {
        int index = (Integer) key;
        return storage[index];
    }

    @Override
    protected void addElement(Object key, Resume resume) {
        int index = (Integer) key;
        if (size < MAX_STORAGE_SIZE) {
            addResume(index, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected void updateElement(Object key, Resume resume) {
        int index = (Integer) key;
        storage[index] = resume;
    }

    @Override
    protected void removeElement(Object key) {
        int index = (Integer) key;
        removeResume(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void addResume(int index, Resume resume);

    protected abstract void removeResume(int index);

}
