package com.bobocode;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoApp {
    public static void main(String[] args) {
        int size = 80000;
        List<Integer> integerList = Stream.generate(() -> ThreadLocalRandom.current().nextInt(size * 10)).limit(size).collect(Collectors.toList());
        System.out.println(integerList);
        System.out.println("=".repeat(80));
        System.out.println(ForkJoinPool.commonPool().invoke(new MergeSortTask<>(integerList)));
    }
}
