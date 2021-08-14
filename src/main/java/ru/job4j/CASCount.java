package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    @GuardedBy("this")

    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int i = count.get();
        count.compareAndSet(i, i + 1);
    }

    public int get() {
        return count.get();
    }
}
