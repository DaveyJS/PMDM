package com.example.pmdm_a1t5_v1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsViewHolder> {
    private Films[] films;

    public FilmsAdapter(Films[] films) {
        this.films = films;
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_card, parent, false);
        return new FilmsViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {
        holder.BindFilm(this.films[position]);
    }

    @Override
    public int getItemCount() {
        return this.films.length;
    }
}
