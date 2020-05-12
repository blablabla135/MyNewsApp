package com.gmail.mynewsapp.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.mynewsapp.viewmodel.ItemViewModel;
import com.gmail.mynewsapp.viewmodel.ListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ItemViewModel.class)
    protected abstract ViewModel itemViewModel(ItemViewModel itemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    protected abstract ViewModel listViewModel(ListViewModel listViewModel);

}
