package ru.job4j.concurrent;

public class ConcurrentOutput {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        System.out.println(Thread.currentThread().getName());
    }
}
