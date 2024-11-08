package com.example.appanimes.activities;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appanimes.R;
import com.example.appanimes.adapter.adapterAnime;
import com.example.appanimes.entidades.Anime;
import com.example.appanimes.rescursos.AnimesService;
import com.example.appanimes.rescursos.apiresponde;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaAnimes extends AppCompatActivity {

    List<Anime> animeList=new ArrayList<>();
    adapterAnime adapter;
    private LinearLayoutManager layoutManager;

    private boolean isloading=false;
    private int page =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animes);

        RecyclerView recyclerView=findViewById(R.id.rvAnimes);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadMoreData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                // Verifica si llegamos al final de la lista
                if (!isloading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    // Si ya no hay más datos o si está cargando, no hace nada
                    isloading = true;
                    page++;  // Aumenta la página
                    loadMoreData();  // Cargar más datos
                }
            }
        });



    }

    private void loadMoreData(){

        Retrofit retrofit=new  Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AnimesService service=retrofit.create(AnimesService.class);

        service.getAnimes().enqueue(new Callback<apiresponde>() {
            @Override
            public void onResponse(Call<apiresponde> call, Response<apiresponde> response) {
                Log.i("MAIN_APP", String.valueOf(response.code()));
                if(response.isSuccessful()){
                    apiresponde apires=response.body();
                    animeList.addAll(apires.getData());
                    adapter.notifyDataSetChanged();
                    isloading=false;
                }
            }

            @Override
            public void onFailure(Call<apiresponde> call, Throwable throwable) {
                Log.e("MAIN_APP", throwable.getMessage());
            }
        });

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView rvAnimes = findViewById(R.id.rvAnimes);
        rvAnimes.setLayoutManager(new LinearLayoutManager(this));

        adapter = new adapterAnime(animeList);
        rvAnimes.setAdapter(adapter);
    }
}