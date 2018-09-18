package com.example.allseven64.cataloguemovieuiux.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.example.allseven64.cataloguemovieuiux.asynctaskloader.NowPlayingAsyncTaskLoader;
import com.example.allseven64.cataloguemovieuiux.R;
import com.example.allseven64.cataloguemovieuiux.adapter.NowUpAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    static final String TAG = NowPlayingFragment.class.getSimpleName();
    RecyclerView recyclerView;
    NowUpAdapter adapter;
    ArrayList<MovieItems> nowPlayingData;
    Context context;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        context = view.getContext();

        recyclerView = view.findViewById(R.id.rv_now_playing);
        adapter = new NowUpAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(0, null,this);

        Log.e(TAG, "onCreateView: halaman fragment ");
        return view;
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        return new NowPlayingAsyncTaskLoader(getContext(), nowPlayingData);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

}
