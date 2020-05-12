package com.gmail.mynewsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.gmail.mynewsapp.R;
import com.gmail.mynewsapp.databinding.ActivityItemBinding;
import com.gmail.mynewsapp.di.ViewModelFactory;
import com.gmail.mynewsapp.model.room.News;
import com.gmail.mynewsapp.viewmodel.ItemViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ItemActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private ItemViewModel itemViewModel;
    private ActivityItemBinding activityItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);

        activityItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_item);
        activityItemBinding.setItemViewModel(itemViewModel);
        activityItemBinding.setLifecycleOwner(this);

        itemViewModel.setNews(getIntent().getStringExtra("author")
                , getIntent().getStringExtra("url")
                , getIntent().getStringExtra("urlToImage")
                , getIntent().getStringExtra("content")
                , getIntent().getStringExtra("description")
                , getIntent().getStringExtra("publishedAt"));
    }
}
