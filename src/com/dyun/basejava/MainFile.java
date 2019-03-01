package com.dyun.basejava;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MainFile {
    private static final Comparator<File> FILE_COMPARATOR = Comparator.comparing(File::isDirectory).thenComparing(File::getName);

    public static void main(String[] args) {
        listAllFiles(new File("").getAbsoluteFile(), "");
    }

    private static void listAllFiles(File dir, String offset) {
        Objects.requireNonNull(dir, "dir must not be null");
        File[] files = dir.listFiles();
        if (files != null) {
            List<File> fileList = Arrays.asList(files);
            fileList.sort(FILE_COMPARATOR);
            for (File file : fileList) {
                if (file.isDirectory()) {
                    System.out.println(offset + "DIR: " + file.getName());
                    listAllFiles(file, offset  + "\t");
                } else {
                    System.out.println(offset + file.getName());
                }
            }
        }
    }
}
