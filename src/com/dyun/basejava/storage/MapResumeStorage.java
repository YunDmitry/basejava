package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap based storage for Resumes
 */
public class MapResumeStorage extends AbstractStorage {

    private Map<Resume, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage.values());
        list.sort(FULLNAME_COMPARATOR);
        return list;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume searchKey(String uuid) {
        return storage.get(new Resume(uuid));
    }

    @Override
    protected boolean checkKey(Object key) {
        Resume resume = (Resume) key;
        return storage.containsKey(resume);
    }

    @Override
    protected Resume getElement(Object key) {
        Resume resume = (Resume) key;
        return storage.get(resume);
    }

    @Override
    protected void addElement(Object key, Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected void updateElement(Object key, Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected void removeElement(Object key) {
        Resume resume = (Resume) key;
        storage.remove(resume);
    }
}
