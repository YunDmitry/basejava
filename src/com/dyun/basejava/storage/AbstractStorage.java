package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int key = (Integer) searchKey(uuid);
        if (checkKey(key)) {
            return getElement(key, null);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        int key = (Integer) searchKey(resume.getUuid());
        if (!checkKey(key)) {
            addElement(key, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int key = (Integer) searchKey(resume.getUuid());
        if (checkKey(key)) {
            updateElement(key, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int key = (Integer) searchKey(uuid);
        if (checkKey(key)) {
            removeElement(key);
        } else {
            throw new NotExistStorageException(uuid);
        }

    }

    public abstract int size();

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public abstract Resume[] getAll();

    public abstract void clear();

    protected abstract boolean checkKey(Object key);

    protected abstract Object searchKey(String uuid);

    protected abstract Resume getElement(int key, String keyString);

    protected abstract void addElement(int key, Resume resume);

    protected abstract void updateElement(int key, Resume resume);

    protected abstract void removeElement(int key);
}
