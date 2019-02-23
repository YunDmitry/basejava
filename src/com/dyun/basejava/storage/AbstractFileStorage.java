package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void writeFile(File file, Resume resume) throws IOException;

    protected abstract Resume getFile(File file) throws IOException;

    @Override
    protected File searchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean checkKey(File file) {
        return file.exists();
    }

    @Override
    protected Resume getElement(File file) {
        try {
            return getFile(file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void addElement(File file, Resume resume) {
        try {
            file.createNewFile();
            writeFile(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void updateElement(File file, Resume resume) {
        try {
            writeFile(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void removeElement(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> getStorageAsList() {
        List<Resume> result = new ArrayList<>();
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                result.add(getElement(file));
            }
        }
        return null;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list != null) {
            return list.length;
        }
        return 0;
    }
}
