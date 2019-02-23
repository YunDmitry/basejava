package com.dyun.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        listAllFiles(new File("").getAbsoluteFile());
    }

    private static void listAllFiles(File dir) {
        Objects.requireNonNull(dir, "dir must not be null");
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    listAllFiles(file);
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
