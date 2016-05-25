package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class Announcement implements Serializable {
    String author;
    String contents;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWeek_title() {
        return week_title;
    }

    long posted_date;

    public long getPriority() {
        return priority;
    }

    long priority;

    public Announcement(String author, String contents, long posted_date, long priority, String title, String week_title, File file, long week_number) {
        this.author = author;
        this.contents = contents;
        this.posted_date = posted_date;
        this.priority = priority;
        this.title = title;
        this.week_title = week_title;
        this.file = file;
        this.week_number = week_number;
    }

    String title;

    public Announcement(String author, String contents, long posted_date, String title, String week_title, File file, long week_number) {
        this.author = author;
        this.contents = contents;
        this.posted_date = posted_date;
        this.title = title;
        this.week_title = week_title;
        this.file = file;
        this.week_number = week_number;
    }

    String week_title;

    public Announcement(String author, String contents, long posted_date, String title, File file, long week_number) {
        this.author = author;
        this.contents = contents;
        this.posted_date = posted_date;
        this.title = title;
        this.file = file;
        this.week_number = week_number;
    }

    File file;

    public long getWeek_number() {
        return week_number;
    }

    long week_number;


    public Announcement(String contents, long posted_date, String title, File file, String author) {
        this.contents = contents;
        this.posted_date = posted_date;
        this.title = title;
        this.file = file;
        this.author = author;
    }
    public Announcement() {

    }

    public File getFile() {
        return file;
    }



    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public long getPosted_date() {
        return posted_date;
    }

    public String getTitle() {
        return title;
    }
}
