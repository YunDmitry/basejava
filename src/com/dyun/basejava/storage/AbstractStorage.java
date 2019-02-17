package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract SK searchKey(String uuid);

    protected abstract boolean checkKey(SK key);

    protected abstract Resume getElement(SK key);

    protected abstract void addElement(SK key, Resume resume);

    protected abstract void updateElement(SK key, Resume resume);

    protected abstract void removeElement(SK key);

    protected abstract List<Resume> getStorageAsList();

    @Override
    public Resume get(String uuid) {
        SK key = getSearchKeyIfResumeExist(uuid);
        return getElement(key);
    }

    @Override
    public void save(Resume resume) {
        SK key = getSearchKeyIfResumeNotExist(resume.getUuid());
        addElement(key, resume);
    }

    @Override
    public void update(Resume resume) {
        SK key = getSearchKeyIfResumeExist(resume.getUuid());
        updateElement(key, resume);
    }

    @Override
    public void delete(String uuid) {
        SK key = getSearchKeyIfResumeExist(uuid);
        removeElement(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getStorageAsList();
        list.sort(COMPARATOR);
        return list;
    }

    private SK getSearchKeyIfResumeExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!checkKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfResumeNotExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (checkKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
