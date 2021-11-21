package com.obviousapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.obviousapp.Adapter.ViewPagerAdapter;
import com.obviousapp.Model.ImageModel;
import com.obviousapp.R;
import com.obviousapp.Singleton.ImageListSigleton;

import java.util.ArrayList;

public class ImageDetailActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageListSigleton imageListSigleton = ImageListSigleton.getInstance();
    ArrayList<ImageModel> imageModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_activity);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        imageModelArrayList = imageListSigleton.getImageModelArrayList();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getApplicationContext(),imageModelArrayList);
        viewPager.setAdapter(adapter);

        setSelectedImage(getIntent().getStringExtra("title"));
    }

    public void setSelectedImage(String title)
    {
        int position = 0;
        for (int i = 0; i <imageModelArrayList.size() ; i++) {
            if(title.equalsIgnoreCase(imageModelArrayList.get(i).getTitle()))
            {
                position = i;
                break;
            }
        }
        viewPager.setCurrentItem(position);
    }
}