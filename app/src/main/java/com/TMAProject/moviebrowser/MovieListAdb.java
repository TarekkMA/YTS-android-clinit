package com.TMAProject.moviebrowser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.TMAProject.moviebrowser.API.Genrator;
import com.TMAProject.moviebrowser.API.YTS;
import com.TMAProject.moviebrowser.API.models.Movies;
import com.TMAProject.moviebrowser.API.models.response.ListResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mido PC on 2/21/2016.
 */
public class MovieListAdb extends RecyclerView.Adapter<MovieListAdb.movieVH> {
    List<Movies> moviesList = new ArrayList<Movies>();
    HashMap<Integer,Boolean> pageStats = new HashMap<>();
    public int lastPageLoaded;
    Context context;
    public MovieListAdb(Context context) {
        this.context=context;
        loadPage(1);
    }

    @Override
    public movieVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new movieVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(movieVH holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load(moviesList.get(position).getMediumCoverImage())
                .placeholder(R.drawable.move_placeholder_230_345)
                .into(holder.pic);
        holder.name.setText(moviesList.get(position).getTitle());
        holder.year.setText("("+moviesList.get(position).getYear()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,MovieDetails.class)
                        .putExtra("ID",moviesList.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    private void addPage(List<Movies> newList){
        int oldLastIndex = moviesList.size()-1;
        moviesList.addAll(newList);
        notifyItemRangeChanged(oldLastIndex,moviesList.size()-1);
    }

    public void loadPage(final int pageNum){
        if (pageStats.get(pageNum)==null) {
            Log.e("Adapter", "I'm loading page "+pageNum);
            Genrator.createService(YTS.class).getListByPage(pageNum).enqueue(new Callback<ListResponse>() {
                @Override
                public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                    if (response.isSuccess()) {
                        Log.d("Adapter", "Loaded " + response.body().getData().getMovies().size() + " movies" +
                                "\npage:" + response.body().getData().getPage_num());
                        addPage(response.body().getData().getMovies());
                        pageStats.put(pageNum, true);
                        lastPageLoaded = pageNum;
                    } else {
                        try {
                            Log.d("Adapter", "code:" + response.code() + ",err:" + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            pageStats.put(pageNum, false);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ListResponse> call, Throwable t) {
                    pageStats.put(pageNum, false);
                    Log.e("Adapter", "err:" + t.getMessage());
                    t.printStackTrace();
                }
            });
        }
    }


    public static class movieVH extends RecyclerView.ViewHolder{
        TextView name,year;
        ImageView pic;
        public movieVH(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.mov_name);
            year=(TextView)itemView.findViewById(R.id.mov_year);
            pic=(ImageView)itemView.findViewById(R.id.mov_pic);
        }
    }
}
