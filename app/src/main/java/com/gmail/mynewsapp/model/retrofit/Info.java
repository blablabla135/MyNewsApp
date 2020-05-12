package com.gmail.mynewsapp.model.retrofit;

import java.util.List;

public class Info {


    private String status;
    private Integer totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
