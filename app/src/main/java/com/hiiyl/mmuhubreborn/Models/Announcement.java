package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class Announcement implements Serializable {
    String author;
    String contents;
    long posted_date;
    String title;
    File file;

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
