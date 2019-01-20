package com.dyun.basejava;

import com.dyun.basejava.model.Resume;
import com.dyun.basejava.storage.SortedArrayStorage;
import com.dyun.basejava.storage.Storage;

/**
 * Test for your com.dyun.basejava.storage.SortedArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        Resume r1 = new Resume("uuid2");
        Resume r2 = new Resume("uuid1");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");  //not for saving

        System.out.println("--save block");
        System.out.println("Saving... r1");
        ARRAY_STORAGE.save(r1);
        System.out.println("Saving... r2");
        ARRAY_STORAGE.save(r2);
        System.out.println("Saving... r3");
        ARRAY_STORAGE.save(r3);
        System.out.println("--end save block");
        System.out.println();

        System.out.println("--update block");
        System.out.println("Updating... r3");
        ARRAY_STORAGE.update(r3);  //test for update existing resume
        System.out.println("Updating... r4");
        ARRAY_STORAGE.update(r4);
        System.out.println("--update end block");
        System.out.println();

        System.out.println("--get block");
        System.out.println("Storage size: " + ARRAY_STORAGE.size());
        System.out.println("Getting... r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Getting dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();
        System.out.println("--get end block");
        System.out.println();

        System.out.println("--delete block");
        System.out.println("Deleting... r1:" + ARRAY_STORAGE.get(r1.getUuid()));
        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println("Deleting... r4:");
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        System.out.println("--delete end block");
        System.out.println();

        System.out.println("--clear block");
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("--clear end block");

    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
