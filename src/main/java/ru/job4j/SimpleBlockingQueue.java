package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")

    private volatile Queue<T> queue = new LinkedList<T>();
    private final int size;

    public SimpleBlockingQueue() {
        size = Integer.MAX_VALUE;
    }

    private SimpleBlockingQueue(int capacity) {
        size = capacity;
    }

    public synchronized void offer(T value) {
        while (queue.size() == size) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
