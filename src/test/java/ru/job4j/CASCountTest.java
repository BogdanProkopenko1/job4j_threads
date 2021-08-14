package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void increment() {
        CASCount casCount = new CASCount();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                casCount.increment();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                casCount.increment();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                casCount.increment();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                casCount.increment();
            }
        }).start();
        assertThat(12, is(casCount.get()));
    }
}