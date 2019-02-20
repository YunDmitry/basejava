package com.dyun.basejava;

import com.dyun.basejava.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID1);
    private static final Resume RESUME_2 = new Resume(UUID2);
    private static final Resume RESUME_3 = new Resume(UUID3);
    private static final Resume RESUME_4 = new Resume(UUID4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        System.out.println(collection.toString());

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (Objects.equals(resume.getUuid(), UUID1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID1, RESUME_1);
        map.put(UUID2, RESUME_2);
        map.put(UUID3, RESUME_3);
        for (Map.Entry<String, Resume> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
