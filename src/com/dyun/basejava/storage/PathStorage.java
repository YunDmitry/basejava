package com.dyun.basejava.storage;

import com.dyun.basejava.exception.StorageException;
import com.dyun.basejava.model.Resume;
import com.dyun.basejava.storage.serialization.SerializationStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy serializationStrategy;

    public PathStorage(String dir, SerializationStrategy serializationStrategy) {
        Path directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.directory = directory;
        this.serializationStrategy = serializationStrategy;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean checkKey(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Resume getElement(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(path), e);
        }
    }

    @Override
    protected void addElement(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path create error", getFileName(path), e);
        }
        updateElement(path, resume);
    }

    @Override
    protected void updateElement(Path path, Resume resume) {
        try {
            serializationStrategy.doWrite(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException("Path write error", getFileName(path), e);
        }
    }

    @Override
    protected void removeElement(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete Path Error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> getStorageAsList() {
        return getFilesList().map(this::getElement).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::removeElement);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }


}
