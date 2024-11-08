package com.example.appanimes.rescursos;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnimesService {

    @GET("anime")
    Call<apiresponde> getAnimes();

    @GET("anime/{id}/episodes")
    Call<apirescapitulo> getepisodes(@Path("id") int id);
}
