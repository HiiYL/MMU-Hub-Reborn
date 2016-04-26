package com.hiiyl.mmuhubreborn.Models;

import java.io.Serializable;

/**
 * Created by Hii on 26/04/16.
 */
public class Subject  implements Serializable {
    String name;
    SubjectFile[] subjectFiles;
    Week[] weeks;

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
}
