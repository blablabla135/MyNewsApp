package com.gmail.mynewsapp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {ViewModelModule.class, DataModule.class, ActivityModule.class, AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    void inject(AppController appController);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
