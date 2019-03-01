package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList based storage for Resumes
 */
public class ListStorage extends AbstractStorage<Integer> {
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
        return new ArrayList<>(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkKey(Integer key) {
        return key > -1;
    }

    @Override
    protected Resume getElement(Integer key) {
        return storage.get(key);
    }

    @Override
    protected void addElement(Integer key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateElement(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    protected void removeElement(Integer key) {
        storage.remove(key.intValue());
    }
}
