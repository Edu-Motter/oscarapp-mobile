package com.edumotter.oscar.utils;

import android.app.Application;
import com.edumotter.oscar.models.User;

public class Session extends Application {

    private User userSession =  new User();

    public User getUserSession(){
        return userSession;
    }

    public void setUserSession(User user){
        this.userSession = user;
    }
}