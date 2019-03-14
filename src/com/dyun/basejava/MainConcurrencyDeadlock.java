package com.dyun.basejava;

public class MainConcurrencyDeadlock {
    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String s[]) {
        Thread threadDeadlock1 = new Thread(() -> {
            synchronized (A) {
                try {
                    System.out.println("thread_deadlock1: Locked object A");
                    A.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread_deadlock1: Trying to lock object B...");
                synchronized (B) {
                    System.out.println("thread_deadlock1: Locked object B");
                }
            }
        });

        Thread threadDeadlock2 = new Thread(() -> {
            synchronized (B) {
                try {
                    System.out.println("thread_deadlock2: Locked object B");
                    B.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread_deadlock2: Trying to lock object A...");
                synchronized (A) {
                    System.out.println("thread_deadlock2: Locked object A");
                }
            }
        });

        threadDeadlock1.start();
        threadDeadlock2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (A) {
            A.notifyAll();
        }
        synchronized (B) {
            B.notifyAll();
        }
    }
}
