package com.dyun.basejava;

import com.dyun.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        System.out.println("Field 0 name: " + field.getName());
        Method method = resume.getClass().getMethod("toString");
        System.out.println("toString invoke result: " + method.invoke(resume));
    }
}
