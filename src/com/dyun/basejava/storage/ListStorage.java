package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList based storage for Resumes
 */
public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getStorageAsList() {
        return storage;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Object searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkKey(Object key) {
        return (Integer) key > -1;
    }

    @Override
    protected Resume getElement(Object key) {
        int index = (Integer) key;
        return storage.get(index);
    }

    @Override
    protected void addElement(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateElement(Object key, Resume resume) {
        int index = (Integer) key;
        storage.set(index, resume);
    }

    @Override
    protected void removeElement(Object key) {
        int index = (Integer) key;
        storage.remove(index);
    }
}
