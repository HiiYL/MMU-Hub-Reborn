package com.hiiyl.mmuhubreborn;

import com.firebase.client.Firebase;
import com.hiiyl.mmuhubreborn.Models.User;

/**
 * Created by Hii on 04/05/16.
 */
public class UserSingleton {
    private static UserSingleton singleton = new UserSingleton( );
    private UserSingleton(){ }
    private User mUser;
    private Firebase mFirebaseRef = new Firebase("https://mmu-hub-14826.firebaseio.com/");

    public static UserSingleton getInstance( ) {
        return singleton;
    }
    public User getUser() {
        return mUser;
    }


    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public Firebase getmFirebaseRef() {
        return mFirebaseRef;
    }
}
