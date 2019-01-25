package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Abstract Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int MAX_STORAGE_SIZE = 10_000;

    protected Resume[] storage = new Resume[MAX_STORAGE_SIZE];

    protected int size = 0;

    protected AbstractArrayStorage() {
        super(true, MAX_STORAGE_SIZE);
    }

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
    protected boolean hasElement(String uuid) {
        return getIndex(uuid) > -1;
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    protected void addElement(Resume resume) {
        addResume(getIndex(resume.getUuid()), resume);
        size++;
    }

    @Override
    protected void updateElement(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    @Override
    protected void removeElement(String uuid) {
        removeResume(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }


    protected abstract int getIndex(String uuid);

    protected abstract void addResume(int index, Resume resume);

    protected abstract void removeResume(int index);

}
