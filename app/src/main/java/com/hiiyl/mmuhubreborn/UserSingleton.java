package com.hiiyl.mmuhubreborn;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hiiyl.mmuhubreborn.Models.User;

/**
 * Created by Hii on 04/05/16.
 */
public class UserSingleton {
    private static UserSingleton singleton = new UserSingleton();
    private UserSingleton(){ }
    private User mUser;
    public DatabaseReference mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

    public static UserSingleton getInstance( ) {
        return singleton;
    }
    public User getUser() {
        return mUser;
    }

    public DatabaseReference getmFirebaseDatabase() {
        return mFirebaseDatabase;
    }

    public void setmFirebaseDatabase(DatabaseReference mFirebaseDatabase) {
        this.mFirebaseDatabase = mFirebaseDatabase;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }
}
