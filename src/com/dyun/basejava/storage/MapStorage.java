package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap based storage for Resumes
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        Resume[] resumeArray = new Resume[storage.size()];
        int i = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            resumeArray[i] = entry.getValue();
        }
        return resumeArray;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected String searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkKey(Object key) {
        String uuid = (String) key;
        return storage.containsKey(uuid);
    }

    @Override
    protected Resume getElement(Object key) {
        String uuid = (String) key;
        return storage.get(uuid);
    }

    @Override
    protected void addElement(Object key, Resume resume) {
        String uuid = (String) key;
        storage.put(uuid, resume);
    }

    @Override
    protected void updateElement(Object key, Resume resume) {
        String uuid = (String) key;
        storage.put(uuid, resume);
    }

    @Override
    protected void removeElement(Object key) {
        String uuid = (String) key;
        storage.remove(uuid);
    }
}
