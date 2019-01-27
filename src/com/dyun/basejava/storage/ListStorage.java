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
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected int searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).equals(new Resume(uuid))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkKey(int key) {
        return key > -1;
    }

    @Override
    protected Resume doGet(int key) {
        return storage.get(key);
    }

    @Override
    protected void addElement(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateElement(Resume resume) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    protected void removeElement(String uuid) {
        storage.remove(new Resume(uuid));
    }
}
