package com.dyun.basejava.util;

public class LazySingleton {
    int i;
    volatile private static LazySingleton INTSANCE;
    double sine = Math.sin(13.);

    private LazySingleton() {
    }

    private static class LazySingletonHoder {
        private static final LazySingleton INTSANCE = new LazySingleton();
    }

    public static synchronized LazySingleton getInstance() {
        return LazySingletonHoder.INTSANCE;
    }
//        if (INTSANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INTSANCE == null) {
//                    int i = 13;
//                    INTSANCE = new LazySingleton();
//                }
//            }
//        }
//        return INTSANCE;
//    }
}
