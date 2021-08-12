package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = {"\\", "|", "/"};
        int flag = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (flag == process.length) {
                    flag = 0;
                }
                System.out.print("\r load: " + process[flag++]);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}
