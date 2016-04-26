package com.hiiyl.mmuhubreborn.Models;

/**
 * Created by Hii on 26/04/16.
 */
public class File {
    String content_id;
    String file_name;
    String file_path;

    public File() {
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

    String token;

    public File(String content_id, String file_name, String file_path, String token) {
        this.content_id = content_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.token = token;
    }
}
