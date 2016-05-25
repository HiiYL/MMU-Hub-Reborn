package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class Week implements Serializable {
    String title;
    Announcement[] announcements;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Announcement[] getAnnouncements() {
        return announcements;
    }

    public String getTitle() {
        return title;
    }



    public Week(String title, Announcement[] announcements) {
        this.title = title;
        this.announcements = announcements;
    }
}
