package com.hiiyl.mmuhubreborn.Models;

/**
 * Created by Hii on 25/04/16.
 */
public class BulletinPost {
    String title, contents, author,url;
    long datePosted, dateExpired;
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public long getDatePosted() {
        return datePosted;
    }

    public BulletinPost() {

    }
    public BulletinPost(String title, String contents, String author, long datePosted) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.datePosted = datePosted;
    }

}