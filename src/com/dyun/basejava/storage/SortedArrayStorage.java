package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        System.out.println(index);
        System.out.println(-index - 1);
        if (index < 0) {
            if (size < MAX_STORAGE_SIZE) {
                int indexAdd = -index - 1;
                /*for (int i = size; i > indexAdd; i--) {
                    storage[i] = storage[i - 1];
                }*/
                System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
                storage[indexAdd] = resume;
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
            /*for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }*/
            System.arraycopy(storage, index, storage, index - 1, size - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume " + uuid + " is not found");
        }

    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
