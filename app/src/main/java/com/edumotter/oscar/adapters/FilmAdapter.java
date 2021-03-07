package com.edumotter.oscar.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Film;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<Film> Films;

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

    public FilmAdapter(List<Film> Films) {
        this.Films = Films;
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


//        String url = film.getPhoto();
//
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
//            holder.movieImage.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        holder.movieImage = film.getImage();
        holder.movieType.setText(String.valueOf(film.getGenre()));
        holder.movieName.setText(String.valueOf(film.getName()));
    }

    @Override
    public int getItemCount() {
        return this.Films.size();
    }
}