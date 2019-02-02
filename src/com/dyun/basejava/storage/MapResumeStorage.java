package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.*;

/**
 * HashMap based storage for Resumes
 */
public class MapResumeStorage extends AbstractStorage {

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
    protected Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkKey(Object key) {
        return !Objects.isNull(key);
    }

    @Override
    protected Resume getElement(Object key) {
        return (Resume) key;
    }

    @Override
    protected void addElement(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Object key, Resume resume) {
        String uuid = ((Resume) key).getUuid();
        storage.put(uuid, resume);
    }

    @Override
    protected void removeElement(Object key) {
        Resume resume = (Resume) key;
        storage.remove(resume.getUuid());
    }
}
