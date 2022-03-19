package com.cs4015.bookstore.bookservice.core.user.controllers;

import java.util.ArrayList;
import java.util.List;

import com.cs4015.bookstore.bookservice.core.user.model.User;

public class CurrentUsers {
    private static List<User> cUser = new ArrayList<>();

    private CurrentUsers(){

    }

    public static void addCurrentUser(User user){
        cUser.add(user);
    }

    public static boolean searchCurrentUser(User user){
        return cUser.contains(user);
    }

    public static void removeCurrentUser(User user){
        cUser.remove(user);
    }

}
