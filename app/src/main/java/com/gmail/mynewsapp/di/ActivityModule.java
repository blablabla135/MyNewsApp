package com.gmail.mynewsapp.di;

import com.gmail.mynewsapp.ui.ItemActivity;
import com.gmail.mynewsapp.ui.ListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract ItemActivity contributeItemActivity();

    @ContributesAndroidInjector
    abstract ListActivity contributeListActivity();
}
