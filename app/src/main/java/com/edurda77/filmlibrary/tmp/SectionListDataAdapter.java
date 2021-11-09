package com.edurda77.filmlibrary.tmp;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.edurda77.filmlibrary.R;
import com.edurda77.filmlibrary.ui.FilmActivity;
import com.edurda77.filmlibrary.domain.Movie;

import java.util.ArrayList;


public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {
    interface OnStateClickListener{
        void onStateClick(Movie movie, int position);
    }
    private ArrayList<Movie> itemsList;
    private Context mContext;
    private final OnStateClickListener onClickListener;

    public SectionListDataAdapter(Context context, ArrayList<Movie> itemsList, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plaiyng_film, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Movie movie = itemsList.get(i);

        holder.titleMovie.setText(movie.getMovieTitle());
        holder.yearMovie.setText(movie.getYear());
        holder.rangMovie.setText((int) movie.getRang());
        holder.itemView.setOnClickListener(v -> onClickListener.onStateClick(movie, i));

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView titleMovie;
        protected TextView yearMovie;
        protected TextView rangMovie;
        //protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.titleMovie = view.findViewById(R.id.title_movie);
            this.yearMovie = view.findViewById(R.id.year_movie);
            this.rangMovie = view.findViewById(R.id.rang_movie);
            //this.itemImage = (ImageView) view.findViewById(R.id.itemImage);




        }

    }

}