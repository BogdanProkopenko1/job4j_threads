package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")

    private volatile Map<User, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user, user) != null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user, user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user, user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);
        if (from != null && to != null && from.getAmount() >= amount) {
            to.setAmount(to.getAmount() + amount);
            from.setAmount(from.getAmount() - amount);
            return true;
        }
        return false;
    }
}
