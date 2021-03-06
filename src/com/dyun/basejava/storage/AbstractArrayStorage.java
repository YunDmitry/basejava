package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int MAX_STORAGE_SIZE = 10_000;

    protected Resume[] storage = new Resume[MAX_STORAGE_SIZE];

    protected int size = 0;

    protected abstract void addResume(int index, Resume resume);

    protected abstract void removeResume(int index);

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Resume> getStorageAsList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean checkKey(Integer key) {
        return key > -1;
    }

    @Override
    protected Resume getElement(Integer key) {
        return storage[key];
    }

    @Override
    protected void addElement(Integer key, Resume resume) {
        if (size < MAX_STORAGE_SIZE) {
            addResume(key, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected void updateElement(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected void removeElement(Integer key) {
        removeResume(key);
        storage[size - 1] = null;
        size--;
    }
}
