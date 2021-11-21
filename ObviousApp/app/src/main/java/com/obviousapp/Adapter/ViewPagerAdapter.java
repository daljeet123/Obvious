package com.obviousapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.obviousapp.Model.ImageModel;
import com.obviousapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

    ArrayList<ImageModel> imageModelArrayList;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return imageModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.view_pager_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_detail);
        TextView titleTxt = (TextView) itemView.findViewById(R.id.title);
        TextView desTxt = (TextView) itemView.findViewById(R.id.description);

        Glide.with(context).load(imageModelArrayList.get(position).getUrl()).apply(new RequestOptions().override(500, 500)).into(imageView);
        titleTxt.setText(imageModelArrayList.get(position).getTitle());
        desTxt.setText(imageModelArrayList.get(position).getExplanation());
        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}