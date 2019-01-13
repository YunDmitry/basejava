package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int maxStorageSize = 10_000;

    private Resume[] storage = new Resume[maxStorageSize];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            if (size < maxStorageSize) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("ERROR: Storage is full. Capacity = " + maxStorageSize);
            }
        } else {
            System.out.println("ERROR: Resume is already saved");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Resume is not found");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("ERROR: Resume is not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume is not found");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
