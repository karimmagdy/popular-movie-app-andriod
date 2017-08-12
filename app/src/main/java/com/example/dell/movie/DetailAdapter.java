package com.example.dell.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dell on 19/08/2016.
 */
public class DetailAdapter extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;

    private final Movie mLock = new Movie();

    private List<Movie> mObjects;

    public DetailAdapter(Context context, List<Movie> objects) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjects = objects;
    }

    public Context getContext() {
        return mContext;
    }

    public void add(Movie object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        synchronized (mLock) {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    public void setData(List<Movie> data) {
        clear();
        for (Movie movie : data) {
            add(movie);
        }
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Movie getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.detailmovies, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Movie movie = getItem(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage2();

        viewHolder = (ViewHolder) view.getTag();

        Picasso.with(getContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.title.setText(movie.getTitle());
        viewHolder.release_date.setText(movie.getDate());
        viewHolder.rating.setRating(Float.parseFloat(Double.toString(movie.getRating())));
        viewHolder.overview.setText(movie.getOverview());
        return view;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView title;
        public final TextView overview;
        public final TextView release_date;
      public  final RatingBar rating;
        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageview2);
            title = (TextView) view.findViewById(R.id.titleview);
            overview=(TextView) view.findViewById(R.id.overview);
            release_date=(TextView) view.findViewById(R.id.releasedateview);
            rating=(RatingBar) view.findViewById(R.id.ratingbar1);
        }
    }
}
