package com.dyun.basejava;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    //private static final Object LOCK = new Object();
    private static final Lock LOCK = new ReentrantLock();
    private static final int THREADS_NUMBER = 10000;
    //private static int counter;
    private static final AtomicInteger atomicInteger = new AtomicInteger();
//    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
//        @Override
//        protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat();
//        }
//    };

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
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

        //List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                    //System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
            //System.out.println(future.get(10, TimeUnit.SECONDS));
//            thread.start();
            //threads.add(thread);
        }

        /*threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(atomicInteger.get());
    }

    private static void inc() {
        atomicInteger.incrementAndGet();
//        LOCK.lock();
//        try {
//            counter++;
//        } finally {
//            LOCK.unlock();
//        }
    }
}
