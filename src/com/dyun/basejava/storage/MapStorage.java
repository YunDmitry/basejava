package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap based storage for Resumes
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getStorageAsList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkKey(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected Resume getElement(String key) {
        return storage.get(key);
    }

    @Override
    protected void addElement(String key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    protected void updateElement(String key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    protected void removeElement(String key) {
        storage.remove(key);
    }
}
