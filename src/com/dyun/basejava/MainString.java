package com.dyun.basejava;

public class MainString {
    public static void main(String args[]) {
        String[] strArray= new String[]{"1", "2", "23", "48"};
        StringBuilder result = new StringBuilder();
        for(String str: strArray) {
            result.append(str).append("; ");
        }
        System.out.println(result.toString());
    }
}
