package com.hiiyl.mmuhubreborn.Models;

import android.util.Log;

import com.hiiyl.mmuhubreborn.R;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class SubjectFile implements Serializable {
    String author;
    String content_id;
    String content_type;
    String description;
    String file_name;
    String file_path;
    long posted_date;
    long priority;
    String title;
    String token;

    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent_type() {
        return content_type;
    }

    public long getPriority() {
        return priority;
    }

    public SubjectFile(String content_id, String file_name, String file_path, String author, String content_type, String description, long posted_date, long priority, String title, String token, String uid) {
        this.content_id = content_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.author = author;
        this.content_type = content_type;
        this.description = description;
        this.posted_date = posted_date;
        this.priority = priority;
        this.title = title;
        this.token = token;
        this.uid = uid;
    }

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

    public SubjectFile(String file_name, String file_path, String author, String content_type, String description, long posted_date, String title, String token) {
        this.file_name = file_name;
        this.file_path = file_path;
        this.author = author;
        this.content_type = content_type;
        this.description = description;
        this.posted_date = posted_date;
        this.title = title;
        this.token = token;
    }

    public SubjectFile(String content_id, String file_name, String file_path, String author, String content_type, String description, long posted_date, long priority, String title, String token) {
        this.content_id = content_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.author = author;
        this.content_type = content_type;
        this.description = description;
        this.posted_date = posted_date;
        this.priority = priority;
        this.title = title;
        this.token = token;
    }
    public String getFileType() {
        String type = file_name.substring(file_name.lastIndexOf('.') + 1);
        Log.d("TYPE", type);
        return type;
    }
    public int getFileIcon() {
        switch(getFileType()) {
            case "ae":
                return R.drawable.file_type_icons_flat__01;
            case "ps":
                return R.drawable.file_type_icons_flat__02;
            case "ai":
                return R.drawable.file_type_icons_flat__03;
            case "id":
                return R.drawable.file_type_icons_flat__04;
            case "fl":
                return R.drawable.file_type_icons_flat__05;
            case "psd":
                return R.drawable.file_type_icons_flat__08;
            case "fla":
                return R.drawable.file_type_icons_flat__11;
            case "pdf":
                return R.drawable.file_type_icons_flat__12;
            case "avi":
                return R.drawable.file_type_icons_flat__14;
            case "mp4":
                return R.drawable.file_type_icons_flat__15;
            case "css":
                return R.drawable.file_type_icons_flat__19;
            case "ppt":
                return R.drawable.file_type_icons_flat__24;
            case "doc":
                return R.drawable.file_type_icons_flat__22;
            case "docx":
                return R.drawable.file_type_icons_flat__21;
            case "xls":
                return R.drawable.file_type_icons_flat__23;
            case "pptx":
                return R.drawable.file_type_icons_flat__20;
            case "mp3":
                return R.drawable.file_type_icons_flat__25;
            case "png":
                return R.drawable.file_type_icons_flat__26;
            case "jpg":
                return R.drawable.file_type_icons_flat__27;
            case "html":
                return R.drawable.file_type_icons_flat__28;
            case "xlsx":
                return R.drawable.file_type_icons_flat__30;


            default:
                return R.drawable.file_type_icons_flat__13;
        }
    }
}
