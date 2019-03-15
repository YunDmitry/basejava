package com.dyun.basejava;

public class MainConcurrencyDeadlock {
    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String s[]) {
        Thread threadDeadlock1 = threadLock(A, B);
        Thread threadDeadlock2 = threadLock(B, A);

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

    private static Thread threadLock (Object first, Object second) {
        return new Thread(() -> {
            synchronized (first) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": Locked object " + first);
                    first.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": Trying to lock object " + second + "...");
                synchronized (second) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Locked object " + second);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
