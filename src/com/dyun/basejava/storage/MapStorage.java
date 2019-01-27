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
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            resumeArray[i] = entry.getValue();
        }
        return resumeArray;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected int searchKey(String uuid) {
        return -1;  //NOT FINISHED
    }

    @Override
    protected boolean checkKey(int key) {
        return false;  //NOT FINISHED
    }

    @Override
    protected Resume doGet(int key) {
        return null;   //NOT FINISHED
    }

    @Override
    protected void addElement(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeElement(int key, String uuid) {
        storage.remove(uuid);
    }
}
