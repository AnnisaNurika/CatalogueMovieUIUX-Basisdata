package com.example.allseven64.cataloguemovieuiux.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.allseven64.cataloguemovieuiux.DetailMovieActivity;
import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.example.allseven64.cataloguemovieuiux.R;
import com.example.allseven64.cataloguemovieuiux.asynctaskloader.SearchAsyncTaskLoader;
import com.example.allseven64.cataloguemovieuiux.adapter.SearchAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    ListView listView;
    SearchAdapter adapter;
    EditText edtMovie;
    Button btnCari;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        adapter = new SearchAdapter(getActivity());
        adapter.notifyDataSetChanged();

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItems movieItems = (MovieItems)parent.getItemAtPosition(position);

                Intent detailIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailIntent.putExtra(DetailMovieActivity.EXTRA_POSTER, movieItems.getPosterPath());
                detailIntent.putExtra(DetailMovieActivity.EXTRA_TITLE, movieItems.getTitle());
                detailIntent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW, movieItems.getOverview());
                detailIntent.putExtra(DetailMovieActivity.EXTRA_RELEASE, movieItems.getReleaseDate());
                detailIntent.putExtra(DetailMovieActivity.EXTRA_POPULARITY, movieItems.getPopularity());

                startActivity(detailIntent);
            }
        });

        edtMovie = view.findViewById(R.id.edt_movie);
        btnCari = view.findViewById(R.id.btn_movie);
        btnCari.setOnClickListener(myListener);

        String movie = edtMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        getLoaderManager().initLoader(0, bundle, this);
        return view;
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String kumpulanMovie = "";
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }
        return new SearchAsyncTaskLoader(getActivity(), kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String movie = edtMovie.getText().toString();;

            if (TextUtils.isEmpty(movie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, movie);
            getLoaderManager().restartLoader(0,bundle, HomeFragment.this);
        }
    };
}
