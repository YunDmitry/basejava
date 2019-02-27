package com.dyun.basejava;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MainFile {
    protected static final Comparator<File> FILE_COMPARATOR = Comparator.comparing(File::isDirectory).thenComparing(File::getName);

    public static void main(String[] args) {
        listAllFiles(new File("").getAbsoluteFile(), 0);
    }

    private static void listAllFiles(File dir, int offset) {
        Objects.requireNonNull(dir, "dir must not be null");
        String offsetStr = new String(new char[offset]).replace("\0", "  ");
        File[] array = dir.listFiles();
        if (array != null) {
            List<File> fileList = Arrays.asList(array);
            fileList.sort(FILE_COMPARATOR);
            for (File file : fileList) {
                if (file.isDirectory()) {
                    offset++;
                    System.out.println(offsetStr + "DIR: " + file.getName());
                    listAllFiles(file, offset);
                    offset--;
                } else {
                    System.out.println(offsetStr + file.getName());
                }
            }
        }
    }
}
