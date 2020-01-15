package com.example.skanda.schoolmanagement;

/**
 * Created by Suhas on 07-08-2018.
 */

public class Task {

    public Task(String t,String s,String e)
    {
        title=t;
        startdate=s;
        enddate=e;
    }

    public Task(String t,String s,String e,String sub)
    {
        title=t;
        startdate=s;
        enddate=e;
        submissiondate=sub;
    }
    public Task(String t,String sub)
    {
        title=t;

        submissiondate=sub;
    }
    public Task(String t)
    {
        title=t;


    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(String submissiondate) {
        this.submissiondate = submissiondate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    String title,startdate,enddate,description,submissiondate,file;

}
