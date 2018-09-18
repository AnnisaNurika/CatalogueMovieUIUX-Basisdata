package com.example.allseven64.cataloguemovieuiux.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.allseven64.cataloguemovieuiux.R;
import com.example.allseven64.cataloguemovieuiux.adapter.FavAdapter;
import com.example.allseven64.cataloguemovieuiux.db.MovieHelper;
import com.example.allseven64.cataloguemovieuiux.entity.MovieModel;

import java.util.ArrayList;

import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    Context context;

    private Cursor list;
    private FavAdapter favAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        context = view.getContext();

        recyclerView = view.findViewById(R.id.rv_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        favAdapter = new FavAdapter(getActivity());
        favAdapter.setListFavMovie(list);
        recyclerView.setAdapter(favAdapter);

        new LoadMovieAsync().execute();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private class LoadMovieAsync extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(CONTENT_URI, null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor movieModels){
            super.onPostExecute(movieModels);

            list = movieModels;
            favAdapter.setListFavMovie(list);
            favAdapter.notifyDataSetChanged();


            if (list.getCount() == 0){
                Toast.makeText(context, "Tidak ada data saat ini", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
