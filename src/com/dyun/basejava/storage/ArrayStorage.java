package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            if (size < MAX_STORAGE_SIZE) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("ERROR: Storage is full. Capacity = " + MAX_STORAGE_SIZE);
            }
        } else {
            System.out.println("ERROR: Resume " + resume.getUuid() + " is already saved");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume " + uuid + " is not found");
        }

    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
