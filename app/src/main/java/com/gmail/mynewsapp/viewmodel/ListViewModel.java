package com.gmail.mynewsapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.gmail.mynewsapp.model.Repository;
import com.gmail.mynewsapp.model.room.News;

import java.util.List;

import javax.inject.Inject;

public class ListViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public ListViewModel(Repository repository) {
        this.repository = repository;
    }

    public List<News> getNewsList() {
        repository.loadNews();
        return repository.getAllNews();
    }
}
