package com.example.allseven64.cataloguemovieuiux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.example.allseven64.cataloguemovieuiux.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public SearchAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position){
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.imageViewPoster = convertView.findViewById(R.id.img_poster);
            holder.textViewTitle = convertView.findViewById(R.id.tv_title);
            holder.textViewOverview = convertView.findViewById(R.id.tv_overview);
            holder.textViewReleaseDate = convertView.findViewById(R.id.tv_release);
            holder.textViewPopularity = convertView.findViewById(R.id.tv_popularity);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewTitle.setText(mData.get(position).getTitle());
        holder.textViewOverview.setText(mData.get(position).getOverview());
        holder.textViewPopularity.setText(convertView.getResources().getString(R.string.popularity)+": "+mData.get(position).getPopularity());
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

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewOverview;
        TextView textViewReleaseDate;
        TextView textViewPopularity;
    }
}
