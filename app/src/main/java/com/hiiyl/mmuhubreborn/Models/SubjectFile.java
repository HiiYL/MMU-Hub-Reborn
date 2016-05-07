package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class SubjectFile implements Serializable {
    String content_id;
    String file_name;
    String file_path;
    String author;
    String content_type;
    String description;
    long posted_date;
    String title;
    String token;




    public String getToken() {
        return token;
    }

    public String getContent_id() {
        return content_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public long getPosted_date() {
        return posted_date;
    }

    public String getTitle() {
        return title;
    }

    public SubjectFile(String content_id, String file_name, String file_path, String token) {
        this.content_id = content_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.token = token;
    }

    public SubjectFile(String content_id,String content_type, String file_path, String title, String token, String author, String description, String file_name, long posted_date) {
        this.content_id = content_id;
        this.content_type = content_type;
        this.file_name = file_name;
        this.file_path = file_path;
        this.author = author;
        this.description = description;
        this.posted_date = posted_date;
        this.title = title;
        this.token = token;
    }
    public SubjectFile() {

    }
}
