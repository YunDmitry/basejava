package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.*;

/**
 * HashMap based storage for Resumes
 */
public class MapResumeStorage extends AbstractStorage<Resume> {

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
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkKey(Resume key) {
        return !Objects.isNull(key);
    }

    @Override
    protected Resume getElement(Resume key) {
        return key;
    }

    @Override
    protected void addElement(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume key, Resume resume) {
        String uuid = key.getUuid();
        storage.put(uuid, resume);
    }

    @Override
    protected void removeElement(Resume key) {
        storage.remove(key.getUuid());
    }
}
