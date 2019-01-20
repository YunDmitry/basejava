package com.dyun.basejava;

import com.dyun.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String args[]) {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());
    }
}
