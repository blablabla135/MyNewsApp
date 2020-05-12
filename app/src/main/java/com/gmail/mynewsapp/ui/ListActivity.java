package com.gmail.mynewsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.mynewsapp.R;
import com.gmail.mynewsapp.databinding.ActivityListBinding;
import com.gmail.mynewsapp.di.ViewModelFactory;
import com.gmail.mynewsapp.model.room.News;
import com.gmail.mynewsapp.viewmodel.ListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends AppCompatActivity {


    @Inject
    NewsAdapter newsAdapter;
    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel listViewModel;
    private ActivityListBinding activityListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        listViewModel = new ViewModelProvider(this, viewModelFactory).get(ListViewModel.class);
        activityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        activityListBinding.setListViewModel(listViewModel);
        activityListBinding.setLifecycleOwner(this);

        RecyclerView recyclerView = activityListBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter.setNewsList(listViewModel.getNewsList());
        recyclerView.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(News news) {
                Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                intent.putExtra("url", news.getUrl());
                intent.putExtra("urlToImage", news.getUrlToImage());
                intent.putExtra("publishedAt", news.getPublishedAt());
                intent.putExtra("content", news.getContent());
                intent.putExtra("description", news.getDescription());
                intent.putExtra("author", news.getAuthor());
                startActivity(intent);
            }
        });




    }
}
