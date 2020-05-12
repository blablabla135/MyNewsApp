package com.gmail.mynewsapp.model;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.gmail.mynewsapp.model.retrofit.Article;
import com.gmail.mynewsapp.model.retrofit.Info;
import com.gmail.mynewsapp.model.retrofit.InfoApi;
import com.gmail.mynewsapp.model.room.News;
import com.gmail.mynewsapp.model.room.NewsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class Repository {


    private InfoApi infoApi;
    private NewsDao newsDao;

    @Inject
    public Repository(Retrofit retrofit, NewsDao newsDao) {
        this.infoApi = retrofit.create(InfoApi.class);
        this.newsDao = newsDao;
    }

    public void loadNews() {

        List<News> newsList = new ArrayList<>();

        Call<Info> call = infoApi.getInfo();

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Article> articles = new ArrayList<>();
                articles = response.body().getArticles();

                for (Article article : articles
                ) {
                    News news = new News();

                    news.setAuthor(article.getAuthor());
                    news.setContent(article.getContent());
                    news.setPublishedAt(article.getPublishedAt());
                    news.setTitle(article.getTitle());
                    news.setUrl(article.getUrl());
                    news.setUrlToImage(article.getUrlToImage());
                    news.setDescription(article.getDescription());

                    newsList.add(news);

                }
                deleteAllNews();
                insertNews(newsList);
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }

        });


    }

    public void insertNews(List<News> newsList) {
        for (News news : newsList
        ) {
            new InsertNewsAsyncTask(newsDao).execute(news);
        }
    }

    private static class InsertNewsAsyncTask extends AsyncTask<News, Void, Void> {
        private NewsDao newsDao;

        private InsertNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDao.insertNews(news[0]);
            return null;
        }
    }

    public News getNews(int id) {
        try {
            return new GetNewsAsyncTask(newsDao).execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    private static class GetNewsAsyncTask extends AsyncTask<Integer, Void, News> {
        private NewsDao newsDao;

        private GetNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected News doInBackground(Integer... integers) {
            return newsDao.getNews(integers[0]);
        }
    }

    public void deleteAllNews() {
        new DeleteAllNewsAsyncTask(newsDao).execute();
    }

    private static class DeleteAllNewsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsDao newsDao;

        private DeleteAllNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAllNews();
            return null;
        }
    }

    public List<News> getAllNews() {
        try {
            return new GetAllNewsAsyncTask(newsDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    private static class GetAllNewsAsyncTask extends AsyncTask<Void, Void, List<News>> {
        private NewsDao newsDao;

        private GetAllNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected List<News> doInBackground(Void... voids) {
            return newsDao.getAllNews();
        }
    }


}
