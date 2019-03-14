package com.dyun.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final Object LOCK = new Object();
    private static final int THREADS_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());
            }
        };
        thread0.start();


        new Thread(() -> System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());

        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            });
            thread.start();
            threads.add(thread);

        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private static void inc() {
        double a = Math.sin(13.);

        synchronized (LOCK) {
            counter++;
        }
    }
}
