package com.ud.UrlShortenerSpringMVC.model;

import javax.persistence.*;

@Entity
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "short_code")
    private String shortCode;
    @Column(name = "long_url")
    private String longUrl;
    @Column(name = "user_userid")
    private long userUserId;

    public ShortUrl(long id, String shortCode, String longUrl, long userUserId) {
        this.id = id;
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.userUserId = userUserId;
    }

    public ShortUrl(String shortCode, String longUrl, long userUserId) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.userUserId = userUserId;
    }

    public ShortUrl() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(long userUserId) {
        this.userUserId = userUserId;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "id=" + id +
                ", shortCode='" + shortCode + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", userUserId=" + userUserId +
                '}';
    }
}
