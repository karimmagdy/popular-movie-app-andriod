package com.example.dell.movie;

import org.json.JSONException;
import org.json.JSONObject;


public class Review {

    private String id;
    private String author;
    private String content;
    private String url;

    public Review(String author,String content){
        this.author=author;
        this.content=content;

    }

    public Review(){
    }

    public Review(JSONObject trailer) throws JSONException {
        this.id = trailer.getString("id");
        this.author = trailer.getString("author");
        this.content = trailer.getString("content");
        this.url = trailer.getString("url");
    }

    public String getUrl() {
        return url;
    }

    public String getId() { return id; }

    public String getAuthor() { return author; }

    public String getContent() { return content; }
}
