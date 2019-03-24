package com.dyun.basejava;

import java.util.Arrays;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {1, 7, 2, 7, 2, 1, 1, 5};
        System.out.println(minValue(values));
    }

    private static int minValue(int[] values) {
        Stream<Integer> stream = Arrays.stream(values).boxed();
        return stream.distinct().sorted().reduce((s1, s2) -> s1 * 10 + s2).orElse(0);
    }
}
