package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract Object searchKey(String uuid);

    protected abstract boolean checkKey(Object key);

    protected abstract Resume getElement(Object key);

    protected abstract void addElement(Object key, Resume resume);

    protected abstract void updateElement(Object key, Resume resume);

    protected abstract void removeElement(Object key);

    protected abstract List<Resume> getStorageAsList();

    @Override
    public Resume get(String uuid) {
        Object key = getSearchKeyIfResumeExist(uuid);
        return getElement(key);
    }

    @Override
    public void save(Resume resume) {
        Object key = getSearchKeyIfResumeNotExist(resume.getUuid());
        addElement(key, resume);
    }

    @Override
    public void update(Resume resume) {
        Object key = getSearchKeyIfResumeExist(resume.getUuid());
        updateElement(key, resume);
    }

    @Override
    public void delete(String uuid) {
        Object key = getSearchKeyIfResumeExist(uuid);
        removeElement(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getStorageAsList();
        list.sort(COMPARATOR);
        return list;
    }

    private Object getSearchKeyIfResumeExist(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!checkKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfResumeNotExist(String uuid) {
        Object searchKey = searchKey(uuid);
        if (checkKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
