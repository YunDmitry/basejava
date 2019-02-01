package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract int size();

    protected abstract Object searchKey(String uuid);

    protected abstract boolean checkKey(Object key);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public abstract Resume[] getAll();

    public abstract void clear();

    protected abstract Resume getElement(Object key);

    protected abstract void addElement(Object key, Resume resume);

    protected abstract void updateElement(Object key, Resume resume);

    protected abstract void removeElement(Object key);

    public Resume get(String uuid) {
        Object key = getExistCheckedSearchKey(uuid);
        return getElement(key);
    }

    public void save(Resume resume) {
        Object key = getNotExistCheckedSearchKey(resume.getUuid());
        addElement(key, resume);
    }

    public void update(Resume resume) {
        Object key = getExistCheckedSearchKey(resume.getUuid());
        updateElement(key, resume);
    }

    public void delete(String uuid) {
        Object key = getExistCheckedSearchKey(uuid);
        removeElement(key);
    }

    private Object getExistCheckedSearchKey(String uud) {
        Object key = searchKey(uud);
        if (!checkKey(key)) {
            throw new NotExistStorageException(uud);
        }
        return key;
    }

    private Object getNotExistCheckedSearchKey(String uud) {
        Object key = searchKey(uud);
        if (checkKey(key)) {
            throw new ExistStorageException(uud);
        }
        return key;
    }
}
