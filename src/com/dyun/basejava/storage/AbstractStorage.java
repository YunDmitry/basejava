package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = searchKey(uuid);
        if (checkKey(key)) {
            return getElement(key);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        Object key = searchKey(resume.getUuid());
        if (!checkKey(key)) {
            addElement(key, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        Object key = searchKey(resume.getUuid());
        if (checkKey(key)) {
            updateElement(key, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        Object key = searchKey(uuid);
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

    protected abstract Resume getElement(Object key);

    protected abstract void addElement(Object key, Resume resume);

    protected abstract void updateElement(Object key, Resume resume);

    protected abstract void removeElement(Object key);
}
