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
import com.example.appanimes.entidades.capitulo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterepisode extends RecyclerView.Adapter<adapterepisode.episodeViewHolder>{

    private final List<capitulo> data;

    public adapterepisode(List<capitulo> data) {this.data = data;}

    @NonNull
    @Override
    public adapterepisode.episodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.capitulo, parent, false);

        return new adapterepisode.episodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterepisode.episodeViewHolder holder, int position) {
        View view = holder.itemView;

        capitulo item = data.get(position);

        TextView Nombre = view.findViewById(R.id.tvName);
        TextView score = view.findViewById(R.id.tvscore);

        Nombre.setText(item.title.toString());
        score.setText("puntuacion: "+item.score);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class episodeViewHolder extends RecyclerView.ViewHolder {

        public episodeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
