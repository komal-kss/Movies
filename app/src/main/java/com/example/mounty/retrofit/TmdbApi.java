package com.example.mounty.retrofit;

import com.example.mounty.pojo.TopRatedMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface TmdbApi {

    @GET("movie/top_rated")
    Call<String> getTopRatedMovies(
            @Query("api_key") String apiKey
    );
}
