package com.grv.restproject.socialNetwork.user.service;

import jdk.nashorn.internal.ir.WhileNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    static List<User> users = new ArrayList<>();

    static Integer totalUsers = 3;

    static {
        users.add(new User(1, "Thor", new Date()));
        users.add(new User(2, "Loki", new Date()));
        users.add(new User(3, "Hela", new Date()));
    }


    public User getUserById(int id) {

        for (User user: users) {

            if (user.getId() == id) return user;
        }

        return null;
    }

    public List<User> getAllUsers() {

        return users;
    }

    public User addUser(User user) {

        if (user.getId() == null) {
            totalUsers++;
            user.setId(totalUsers);
        }

        users.add(user);

        return user;
    }

    public User deleteUserById(int id) {

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){

            User user = iterator.next();


            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
