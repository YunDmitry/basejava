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
        return storage.values().toArray(new Resume[storage.size()]);
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
