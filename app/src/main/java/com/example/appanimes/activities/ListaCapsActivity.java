package com.example.appanimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appanimes.R;
import com.example.appanimes.adapter.adapterAnime;
import com.example.appanimes.entidades.capitulo;
import com.example.appanimes.adapter.adapterepisode;
import com.example.appanimes.rescursos.AnimesService;
import com.example.appanimes.rescursos.apirescapitulo;
import com.example.appanimes.rescursos.apiresponde;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCapsActivity extends AppCompatActivity {

    List<capitulo> episodeList=new ArrayList<>();
    adapterepisode adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_caps);

        Intent intent = getIntent();
        int id =intent.getIntExtra("IDAnime",0);

        Retrofit retrofit=new  Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AnimesService service=retrofit.create(AnimesService.class);

        service.getepisodes(id).enqueue(new Callback<apirescapitulo>() {
            @Override
            public void onResponse(Call<apirescapitulo> call, Response<apirescapitulo> response) {
                Log.i("MAIN_APP", String.valueOf(response.code()));
                if(response.isSuccessful()){
                    apirescapitulo apires=response.body();
                    episodeList.addAll(apires.getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<apirescapitulo> call, Throwable throwable) {
                Log.e("MAIN_APP", throwable.getMessage());

            }
        });

        setUpRecyclerView();
    }
    private void setUpRecyclerView() {
        RecyclerView rvListaCaps = findViewById(R.id.rvListaCaps);
        rvListaCaps.setLayoutManager(new LinearLayoutManager(this));

        adapter = new adapterepisode(episodeList);
        rvListaCaps.setAdapter(adapter);
    }
}