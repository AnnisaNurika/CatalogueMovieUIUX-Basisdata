package com.example.allseven64.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.allseven64.cataloguemovieuiux.DetailMovieActivity;
import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.example.allseven64.cataloguemovieuiux.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NowUpAdapter extends RecyclerView.Adapter<NowUpAdapter.ViewHolder> {
    private ArrayList<MovieItems> mData;
    private LayoutInflater mInflater;
    private Context context;

    public NowUpAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = new ArrayList<>();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
              holder.textViewTitle.setText(mData.get(position).getTitle());
        holder.textViewOverview.setText(mData.get(position).getOverview());
        holder.textViewPopularity.setText(holder.itemView.getResources().getString(R.string.popularity)+": "+mData.get(position).getPopularity());
        Glide.with(context).load(mData.get(position).getPosterPath()).into(holder.imageViewPoster);

        String re_date = mData.get(position).getReleaseDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date = dateFormat.parse(re_date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String release_date = newDateFormat.format(date);
            holder.textViewReleaseDate.setText(release_date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent (context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_TITLE, mData.get(position).getTitle());
                intent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW, mData.get(position).getOverview());
                intent.putExtra(DetailMovieActivity.EXTRA_RELEASE, mData.get(position).getReleaseDate());
                intent.putExtra(DetailMovieActivity.EXTRA_POPULARITY, mData.get(position).getPopularity());
                intent.putExtra(DetailMovieActivity.EXTRA_POSTER, mData.get(position).getPosterPath());

                context.startActivity(intent);
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context,"Share "+ ""+ mData.get(position).getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewOverview;
        TextView textViewReleaseDate;
        TextView textViewPopularity;
        Button btnDetail, btnShare;

        ViewHolder (View itemView){
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
