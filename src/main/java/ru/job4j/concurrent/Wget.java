package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.print("\rLoading : " + i  + "%");
        }
        System.out.print("\rLoading finished");
    }
}
