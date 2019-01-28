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
        return storage.containsKey((String) key);
    }

    @Override
    protected Resume getElement(int key, String keyString) {
        return storage.get(keyString);
    }

    @Override
    protected void addElement(int key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeElement(int key) {
        //storage.remove(uuid);  //NOT FINISHED
    }
}
