package com.hiiyl.mmuhubreborn.Models;

import android.util.Log;

/**
 * Created by Hii on 04/05/16.
 */
public class User {
    String displayName;
    String provider;
    Subject[] subjects;
    String id;



    String name;
    String faculty;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    String password;

    public User(String displayName, String provider, Subject[] subjects, String id, String name, String password, String faculty) {
        this.displayName = displayName;
        this.provider = provider;
        this.subjects = subjects;
        Log.d("SUBJECTS", "SUBJECT ASSIGNED");
        this.id = id;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
    }
    public User(String displayName, String provider, Subject[] subjects) {
        Log.d("SUBJECTS", "SUBJECT NOT ASSIGNED");
        this.displayName = displayName;
        this.provider = provider;
        this.subjects = subjects;
    }
    public User(String faculty, String name, String id, String password, Subject[] subjects) {
        this.faculty = faculty;
        this.name = name;
        this.id = id;
        this.password = password;
        this.subjects = subjects;
    }


    public User() {
        Log.d("SUBJECTS", "SUBJECT NOT ASSIGNED2");
    }

    public User(String displayName, String provider) {
        Log.d("SUBJECTS", "SUBJECT NOT ASSIGNED3");
        this.displayName = displayName;
        this.provider = provider;
    }
    public String getProvider() {
        return provider;
    }

    public String getDisplayName() {
        return displayName;
    }
    public Subject[] getSubjects() {
        return subjects;
    }


}
