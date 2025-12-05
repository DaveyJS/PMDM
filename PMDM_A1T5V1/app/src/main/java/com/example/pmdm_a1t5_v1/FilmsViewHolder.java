package com.example.pmdm_a1t5_v1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmsViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public ImageView coverView;
    private Context context;

    public FilmsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;

        this.titleView = itemView.findViewById(R.id.titleView);
        this.coverView = itemView.findViewById(R.id.coverView);
    }

    public void BindFilm(Films film) {
        titleView.setText(film.title);
        coverView.setImageResource(
                context.getResources()
                        .getIdentifier(film.image,
                                "drawable",
                                "com.example.pmdm_a1t5_v1")
        );
    }
}
