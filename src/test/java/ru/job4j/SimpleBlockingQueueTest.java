package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        List<Integer> rsl = new ArrayList<>();
        List<Integer> exp = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            if (i % 2 == 1) {
                exp.add(i);
            }
        }
        Thread second = new Thread(() -> {
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
            rsl.add(queue.poll());
        });
        Thread first = new Thread(() -> {
            queue.offer(1);
            queue.offer(3);
            queue.offer(5);
            queue.offer(7);
            queue.offer(9);
            queue.offer(11);
            queue.offer(13);
            queue.offer(15);
        });
        second.start();
        first.start();
        first.join();
        second.join();
        assertThat(rsl, is(exp));
    }
}
