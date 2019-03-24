package com.dyun.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {1, 7, 2, 7, 2, 1, 1, 5};
        System.out.println(minValue(values));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 12, 45, 21, 1);
        System.out.println(oddOrEven(list).toString());
    }

    private static int minValue(int[] values) {
        Stream<Integer> stream = Arrays.stream(values).boxed();
        return stream.distinct().sorted().reduce((s1, s2) -> s1 * 10 + s2).orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce((s1, s2) -> s1 + s2).orElse(0) % 2 == 0)
        {
            return integers.stream().filter(o -> o % 2 == 0).collect(Collectors.toList());
        }
        return integers.stream().filter(o -> o % 2 != 0).collect(Collectors.toList());

    }
}
