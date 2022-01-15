package com.example.sjtu;

public class CommentMessage {
    private String time;
    private String comment;

    public CommentMessage(String comment,String time){
        this.time = time;
        this.comment = comment;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
