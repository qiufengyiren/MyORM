package com.dzq.pojo;

import java.io.Serializable;

public class News_Detail implements Serializable {

    private int id;
    private int newscreateId;
    private String title;
    private String titleName;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getNewscreateId() { return newscreateId; }

    public void setNewscreateId(int newscreateId) { this.newscreateId = newscreateId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getTitleName() { return titleName; }

    public void setTitleName(String titleName) { this.titleName = titleName; }

    public News_Detail(int id, int newscreateId, String title, String createByName) {
        this.id = id;
        this.newscreateId = newscreateId;
        this.title = title;
        this.titleName = titleName;
    }

    @Override
    public String toString() {
        return "News_Detail{" +
                "id=" + id +
                ", newscreateId=" + newscreateId +
                ", title='" + title + '\'' +
                ", titleName='" + titleName + '\'' +
                '}';
    }

    public  News_Detail(){

    }
}
