package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    private boolean hasMaxStorageSizeLimit;
    private int maxStorageSize;

    protected AbstractStorage() {
        this.hasMaxStorageSizeLimit = false;
        this.maxStorageSize = 0;
    }

    protected AbstractStorage(boolean hasMaxStorageSizeLimit, int maxStorageSize) {
        this.hasMaxStorageSizeLimit = hasMaxStorageSizeLimit;
        this.maxStorageSize = maxStorageSize;
    }

    public Resume get(String uuid) {
        if (hasElement(uuid)) {
            return getElement(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        if (!hasElement(resume.getUuid())) {
            if (!hasMaxStorageSizeLimit || (hasMaxStorageSizeLimit && size() < maxStorageSize)) {
                addElement(resume);
            } else {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        if (hasElement(resume.getUuid())) {
            updateElement(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        if (hasElement(uuid)) {
            removeElement(uuid);
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

    protected abstract boolean hasElement(String uuid);

    protected abstract Resume getElement(String uuid);

    protected abstract void addElement(Resume resume);

    protected abstract void updateElement(Resume resume);

    protected abstract void removeElement(String uuid);
}
