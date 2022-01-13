package com.example.sjtu;

public class CommentMessage {
    private long time;
    private String comment;

    public CommentMessage(String comment,long time){
        this.time = time;
        this.comment = comment;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
