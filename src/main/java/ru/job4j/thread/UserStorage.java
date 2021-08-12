package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")

    private volatile List<User> users = new ArrayList<User>();

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        User user1 = users.get(user.getId());
        if (user1 != null) {
            user1.setAmount(user1.getAmount() + user.getAmount());
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
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
