package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    public ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("File read error", null, e);
        }

    }
}
