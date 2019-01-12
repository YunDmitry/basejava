package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int maxStorageSize = 3;

    private Resume[] storage = new Resume[maxStorageSize];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (get(r.getUuid()) == null) {
            if (size < maxStorageSize) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR: Storage is full. Capacity = " + maxStorageSize);
            }
        } else {
            System.out.println("ERROR: Resume is already saved");
        }
    }

    public void update(Resume r) {
        if (get(r.getUuid()) != null) {
            //resume is founded
        } else {
            System.out.println("ERROR: Resume is not found");
        }
    }

    public Resume get(String uuid) {
        int resumeNum = getNumber(uuid);
        if (resumeNum > -1) {
            return storage[resumeNum];
        } else {
            return null;
        }
    }

    public void delete(String uuid) {
        int resumeNum = getNumber(uuid);
        if (resumeNum > -1) {
            storage[resumeNum] = storage[size - 1];
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

    private int getNumber(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        //дошли до конца массива, резюме не нашли
        return -1;
    }

}
