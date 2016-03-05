package com.TMAProject.moviebrowser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.TMAProject.moviebrowser.API.Genrator;
import com.TMAProject.moviebrowser.API.YTS;
import com.TMAProject.moviebrowser.API.models.Movies;
import com.TMAProject.moviebrowser.API.models.response.ListResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetails extends Activity {
    ScrollView bg;
    TextView title,year,gnere,imbdRate,criticsRate,aduRate,syno;
    ImageView pic;
    RatingBar crit;
    RatingBar adu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        bg=(ScrollView)findViewById(R.id.movieBg);
        title=(TextView)findViewById(R.id.movieName);
        year=(TextView)findViewById(R.id.movieYear);
        gnere=(TextView)findViewById(R.id.movieGenre);
        criticsRate=(TextView)findViewById(R.id.critRate);
        aduRate=(TextView)findViewById(R.id.aduRate);
        imbdRate=(TextView)findViewById(R.id.imbdRate);
        syno=(TextView)findViewById(R.id.movieSyno);
        pic=(ImageView)findViewById(R.id.movePic);


        getData();
    }
    void fillViews(Movies movie){
        Picasso.with(this)
                .load(movie.getBackgroundImage())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        bg.setBackground(new BitmapDrawable(getResources(),bitmap));
                    }
                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        title.setText(movie.getTitle());
        year.setText(movie.getYear()+"");
        gnere.setText(get1LineGenre(movie.getGenres()));
        imbdRate.setText(String.valueOf(movie.getRating()));
        syno.setText(movie.getDescriptionFull());
        Picasso.with(this)
                .load(movie.getMediumCoverImage())
                .into(pic);
    }
    String get1LineGenre(List<String> g){
        String toReturn="";
        for (int i =0;i<g.size();i++){
            if(i!=g.size()-1){
                toReturn+=(g.get(i)+" / ");
            }else{
                toReturn+=g.get(i);
            }
        }
        return toReturn;
    }
    void getData(){
        int idToLoad = getIntent().getIntExtra("ID",-1);
        if(idToLoad==-1)
            return;
        Log.e("MovieDetails", "I'm loading movie with id > "+idToLoad);
        Genrator.createService(YTS.class).getMovieDetiles(idToLoad).enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccess()) {
                    Log.d("MovieDetails", "Loaded " + response.body().getData().getMovie().getTitle());
                    fillViews(response.body().getData().getMovie());
                } else {
                    try {
                        Log.d("MovieDetails", "code:" + response.code() + ",err:" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.e("MovieDetails", "err:" + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
