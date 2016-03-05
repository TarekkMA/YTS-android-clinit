package com.TMAProject.moviebrowser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
=======
>>>>>>> 4ffab85756d1e8208246275d101b1d9ec1e80c8f
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.TMAProject.moviebrowser.API.Genrator;
import com.TMAProject.moviebrowser.API.YTS;
import com.TMAProject.moviebrowser.API.models.ListData;
import com.TMAProject.moviebrowser.API.models.response.ListResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    RecyclerView list;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
=======

>>>>>>> 4ffab85756d1e8208246275d101b1d9ec1e80c8f
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        list=(RecyclerView)findViewById(R.id.list);
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        list.setLayoutManager(layoutManager);
        final MovieListAdb adapter = new MovieListAdb(this);
        list.setAdapter(adapter);

        list.setOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                adapter.loadPage(current_page);
            }
        });

=======
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Genrator.createService(YTS.class).getListByPage(1).enqueue(new Callback<ListResponse>() {
                    @Override
                    public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                        Log.d("dddddd","re"+response.body().getData().getCount());
                    }

                    @Override
                    public void onFailure(Call<ListResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
>>>>>>> 4ffab85756d1e8208246275d101b1d9ec1e80c8f
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
