package com.example.mounty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.mounty.model.TopRatedMovieModel;
import com.example.mounty.pojo.Result;
import com.example.mounty.pojo.TopRatedMoviesResponse;
import com.example.mounty.retrofit.TmdbApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<TopRatedMovieModel> topRatedMovieModelList;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String s1[];
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        fetchTopRatedData();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        s1=getResources().getStringArray(R.array.name);
        adapter=new MyAdapter(this,topRatedMovieModelList);
        recyclerView.setAdapter(adapter);




    }
    public void fetchTopRatedData(){
        progressDialog= new ProgressDialog(MainActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching Popular Movies ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit=new Retrofit.Builder().baseUrl(constants.base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
//respon
        TmdbApi tmdbApi=retrofit.create(TmdbApi.class) ;

        Call<String> topRatedMoviesResponseCall=tmdbApi.getTopRatedMovies(constants.api_key);

        topRatedMoviesResponseCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("tag","wregwrgergr");
                if(response.body()!=null)
                {
                    progressDialog.dismiss();
//                    TopRatedMoviesResponse data=response.body();
//
//                List<Result> listResult = data.getResults();
//                Log.i("tag",listResult.toString())  ;
//                for(Result result:listResult)
//                {
//                    String title= result.getTitle();
//                    String description=result.getOverview();
//                    String image=result.getPosterPath();
//                    TopRatedMovieModel  topRatedMovieModel= new TopRatedMovieModel(image,title,description);
//                    topRatedMovieModelList.add(topRatedMovieModel);
//                }
//                adapter.notifyDataSetChanged();
            }
                else
                    Log.i("tag","wregwrgergr");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("tag","wregwrgerg fail");
            }
        });

    }
}
