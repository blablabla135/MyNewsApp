package com.gmail.mynewsapp.model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoApi {

    @GET("top-headlines?country=us&apiKey=5d0e08e3108c49e0b20176ef2d003724")
    Call<Info> getInfo();

}
