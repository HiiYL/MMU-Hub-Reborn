package com.hiiyl.mmuhubreborn.Models;

import android.util.Log;

import java.util.List;

/**
 * Created by Hii on 04/05/16.
 */
public class User {
    String displayName;
    String uid;
    String id;
    String name;
    String faculty;
    String password;

    List<Subject> subjects;


    public User(String uid, String id, String name, String faculty, String password, List<Subject> subjects, String displayName) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.password = password;
        this.subjects = subjects;
        this.displayName = displayName;
        Log.d("SUBJECTS", "ADDING SUBJECTS");
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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




    public User() {
        Log.d("SUBJECTS", "SUBJECT NOT ASSIGNED2");
    }


    public String getDisplayName() {
        return displayName;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
