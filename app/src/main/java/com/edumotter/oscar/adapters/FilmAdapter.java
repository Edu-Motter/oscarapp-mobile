package com.edumotter.oscar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Film;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<Film> Films;

    public FilmAdapter(List<Film> Films) {
        this.Films = Films;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieName, movieType;
        ImageView movieImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.textViewFilm);
            movieType = itemView.findViewById(R.id.textViewType);
            movieImage = itemView.findViewById(R.id.imageViewFilm);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoItem =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cell,
                        parent, false);
        return new MyViewHolder(toDoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Film film = Films.get(position);
        if(film.getId() != 0){
            if(film.getPhoto() != null && !film.getPhoto().isEmpty()){
                Picasso.get().load(film.getPhoto())
                    .placeholder(R.drawable.loading_film)
                    .error(R.drawable.erro_film)
                    .fit()
                    .noFade()
                    .into(holder.movieImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            film.setImage(holder.movieImage);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                            film.setImage(holder.movieImage);
                        }
                    });
            } else {
                holder.movieImage.setImageResource(R.drawable.film);
            }

            holder.movieType.setText(String.valueOf(film.getGenre()));
            holder.movieName.setText(String.valueOf(film.getName()));
        }

    }

    @Override
    public int getItemCount() {
        return this.Films.size();
    }
}