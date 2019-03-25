package com.dyun.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {1, 7, 2, 7, 2, 1, 1, 5};
        System.out.println(minValue(values));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 12, 45, 21, 2);
        System.out.println(oddOrEven(list).toString());
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((s1, s2) -> s1 * 10 + s2)
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger();
        integers.forEach(item -> {
            if (item % 2 == 0) {
                evenList.add(item);
            } else {
                oddList.add(item);
            }
            sum.addAndGet(item);
        });

        if (sum.get() % 2 == 0) {
            return evenList;
        }
        return oddList;
    }
}
