package com.obviousapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.obviousapp.Model.ImageModel;
import com.obviousapp.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<ImageModel> imageModelArrayList;

    private LayoutInflater mInflater;

    Context context;

    public GridAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.imageModelArrayList = imageModelArrayList;
        this.context = context;

    }

    public int getCount() {
        return imageModelArrayList.size();
    }

    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new ViewHolder();
            convertView = inflater.inflate(R.layout.gridview_item, null);
            grid.imageView = (ImageView) convertView.findViewById(R.id.grid_image);

            convertView.setTag(grid);
        }
        else {
            grid = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(imageModelArrayList.get(position).getUrl()).apply(new RequestOptions().override(500, 500)).into(grid.imageView);
        return convertView;
    }
    public class ViewHolder
    {
        ImageView imageView;
    }
}