package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class Subject  implements Serializable {
    String name;
    String uri;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Subject(String name, Announcement[] announcements, SubjectFile[] subjectFiles) {
        this.name = name;
        this.announcements = announcements;
        this.subjectFiles = subjectFiles;
    }

    public Subject(String name, String uri, String uid) {
        this.name = name;
        this.uri = uri;
        this.uid = uid;
    }

    SubjectFile[] subjectFiles;
    Announcement[] announcements;

    public Announcement[] getAnnouncements() {
        return announcements;
    }

    Week[] weeks;

    public String getUri() {
        return uri;
    }
    public Week[] getWeeks() {
        return weeks;
    }

    public SubjectFile[] getSubjectFiles() {
        return subjectFiles;
    }

    public String getName() {
        return name;
    }

    public Subject(String name, SubjectFile[] subjectFiles, Week[] weeks) {
        this.name = name;
        this.subjectFiles = subjectFiles;
        this.weeks = weeks;
    }
    public Subject(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }
    public Subject() {

    }
}
