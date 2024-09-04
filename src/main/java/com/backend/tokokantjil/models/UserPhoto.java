package com.backend.tokokantjil.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class UserPhoto {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String url;
    private String contentType;
    @Lob
    private byte[] contents;

    public UserPhoto(String title, String contentType, String url, byte[] contents) {
        this.url = url;
        this.title = title;
        this.contentType = contentType;
        this.contents = contents;
    }

    public UserPhoto() {
    }

    public UserPhoto(long id, String title, String url, String contentType, byte[] contents) {
        this.title = title;
        this.url = url;
        this.contentType = contentType;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
