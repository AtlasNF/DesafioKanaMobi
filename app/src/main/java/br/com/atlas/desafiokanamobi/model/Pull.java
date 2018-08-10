package br.com.atlas.desafiokanamobi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pull implements Serializable{
    @SerializedName("html_url")
    private String url;
    private String title;
    private Owner user;
    private String body;
    private String created_at;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
