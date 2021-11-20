package com.obviousapp.Singleton;

import com.obviousapp.Model.ImageModel;

import java.util.ArrayList;

public class ImageListSigleton {

    ArrayList<ImageModel> imageModelArrayList = new ArrayList<>();

    public static ImageListSigleton instance = null;

    public static ImageListSigleton getInstance()
    {
        if(instance == null)
        {
            instance = new ImageListSigleton();
        }

        return instance;
    }

    public ArrayList<ImageModel> getImageModelArrayList() {
        return imageModelArrayList;
    }

    public void addImageModel(ImageModel model) {
        this.imageModelArrayList.add(model);
    }
}
