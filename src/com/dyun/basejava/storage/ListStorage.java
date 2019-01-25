package com.dyun.basejava.storage;

import com.dyun.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList based storage for Resumes
 */
public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    protected ListStorage() {
        super(false, 0);
    }

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
    protected boolean hasElement(String uuid) {
        return storage.contains(new Resume(uuid));
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage.get(storage.indexOf(new Resume(uuid)));
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
