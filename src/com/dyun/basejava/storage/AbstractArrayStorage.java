package com.dyun.basejava.storage;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Abstract Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int MAX_STORAGE_SIZE = 10_000;

    protected Resume[] storage = new Resume[MAX_STORAGE_SIZE];

    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            if (size < MAX_STORAGE_SIZE) {
                addResume(index, resume);
                size++;
            } else {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            removeResume(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(int index, Resume resume);

    protected abstract void removeResume(int index);

}
