package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 25/04/16.
 */
public class BulletinPost  implements Serializable {
    String title;
    String contents;
    String author;
    String url;

    String key;
    long datePosted, dateExpired;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BulletinPost(String title, String contents, String author, String url, String key, long datePosted, long dateExpired, String uid) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.url = url;
        this.key = key;
        this.datePosted = datePosted;
        this.dateExpired = dateExpired;
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public long getDateExpired() {
        return dateExpired;
    }


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