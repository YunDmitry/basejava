package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;

/**
 * Sorted Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(int index, Resume resume) {
        int indexAdd = -index - 1;
        System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
        storage[indexAdd] = resume;
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int searchKey(String uuid) {
        Resume key = new Resume(uuid);
         return Arrays.binarySearch(storage, 0, size, key);
    }

}
