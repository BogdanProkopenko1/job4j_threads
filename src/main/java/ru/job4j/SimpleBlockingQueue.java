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

    public SimpleBlockingQueue(int capacity) {
        size = capacity;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() == this.size) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.size){
            notifyAll();
        }

        return this.queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
