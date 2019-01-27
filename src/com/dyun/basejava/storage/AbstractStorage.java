package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int key = searchKey(uuid);
        if (checkKey(key)) {
            return doGet(key);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        int key = searchKey(resume.getUuid());
        if (!checkKey(key)) {
            addElement(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int key = searchKey(resume.getUuid());
        if (checkKey(key)) {
            updateElement(key, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int key = searchKey(uuid);
        if (checkKey(key)) {
            removeElement(key, uuid);
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

    protected abstract boolean checkKey(int key);

    protected abstract int searchKey(String uuid);

    protected abstract Resume doGet(int key);

    protected abstract void addElement(Resume resume);

    protected abstract void updateElement(int key, Resume resume);

    protected abstract void removeElement(int key, String uuid);
}
