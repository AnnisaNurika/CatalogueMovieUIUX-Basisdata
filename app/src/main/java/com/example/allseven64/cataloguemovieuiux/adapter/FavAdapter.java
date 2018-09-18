package com.example.allseven64.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.allseven64.cataloguemovieuiux.CustomOnItemClickListener;
import com.example.allseven64.cataloguemovieuiux.DetailMovieActivity;
import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.example.allseven64.cataloguemovieuiux.R;
import com.example.allseven64.cataloguemovieuiux.db.MovieHelper;
import com.example.allseven64.cataloguemovieuiux.entity.MovieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.CONTENT_URI;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavoriteHolder> {
    private Cursor listFavMovie;
    private LayoutInflater mInflater;
    private Context context;

    public FavAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setListFavMovie(Cursor listFavMovie){
        this.listFavMovie = listFavMovie;
        notifyDataSetChanged();
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail, parent, false);
        return new FavoriteHolder(view);
    }


    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position){
        final MovieModel movieModel = getItem(position);

        holder.textViewTitle.setText(movieModel.getTitle());
        holder.textViewOverview.setText(movieModel.getOverview());
        holder.textViewPopularity.setText(holder.itemView.getResources().getString(R.string.popularity)+": "+movieModel.getPopularity());
        Glide.with(context).load(movieModel.getPosterPath()).into(holder.imageViewPoster);

        String re_date = movieModel.getReleaseDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date = dateFormat.parse(re_date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String release_date = newDateFormat.format(date);
            holder.textViewReleaseDate.setText(release_date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent (context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieModel);
                intent.putExtra(DetailMovieActivity.EXTRA_POSITION, position);
                context.startActivity(intent);

            }
        }));

        holder.btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context,"Share "+ ""+ movieModel.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public int getItemCount() {
        if (listFavMovie == null) return 0;
        return  listFavMovie.getCount();
    }

    private MovieModel getItem(int position){
        if(!listFavMovie.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new MovieModel(listFavMovie);
    }

    class FavoriteHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewOverview;
        TextView textViewReleaseDate;
        TextView textViewPopularity;
        Button btnShare, btnDetail;

        FavoriteHolder (View itemView){
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.img_cv);
            textViewTitle = itemView.findViewById(R.id.tv_title_cv);
            textViewOverview = itemView.findViewById(R.id.tv_overview_cv);
            textViewReleaseDate = itemView.findViewById(R.id.tv_release_cv);
            textViewPopularity = itemView.findViewById(R.id.tv_popularity_cv);
            btnDetail = itemView.findViewById(R.id.btn_set_detail);
            btnShare = itemView.findViewById(R.id.btn_set_share);

        }
    }
}
