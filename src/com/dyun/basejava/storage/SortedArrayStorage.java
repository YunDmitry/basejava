package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sorted Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);

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
    protected Object searchKey(String uuid) {
        Resume key = new Resume(uuid, "New fullname");
         return Arrays.binarySearch(storage, 0, size, key, UUID_COMPARATOR);
    }
}
