package com.example.appanimes.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appanimes.R;
import com.example.appanimes.activities.ListaCapsActivity;
import com.example.appanimes.entidades.Anime;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterAnime extends RecyclerView.Adapter<adapterAnime.AnimeViewHolder> {

    private final List<Anime> data;

    public adapterAnime(List<Anime> data) {this.data = data;}

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_anime, parent, false);

        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        View view = holder.itemView;

        Anime item = data.get(position);

        ImageView imagen=view.findViewById(R.id.ivUrlImg);
        TextView Nombre = view.findViewById(R.id.tvName);
        TextView NumEpisodios = view.findViewById(R.id.tvNumEpisodios);

        Nombre.setText(item.title);
        NumEpisodios.setText( "Num episodios: "+ item.episodes);
        Log.i("MAIN_APP","holoa" +(item.images.getJpg().getImageUrl()));
        Picasso.get()
                .load(item.images.getJpg().getImageUrl())  // La URL de la imagen
                .into(imagen);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaCapsActivity.class);
                Log.i("MAIN_APP","cd" +(item.mal_id));

                intent.putExtra("IDAnime",item.getMal_id());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
