package com.obviousapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.obviousapp.Adapter.GridAdapter;
import com.obviousapp.Constants.ImageConstants;
import com.obviousapp.Model.ImageModel;
import com.obviousapp.R;
import com.obviousapp.Singleton.ImageListSigleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListOfImagesActivity extends AppCompatActivity {

    ImageListSigleton imageListSigleton = ImageListSigleton.getInstance();
    ArrayList<ImageModel> imageModelArrayList = null;
    GridView imageListGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_images);

        imageListGridView = (GridView)findViewById(R.id.list_of_images_grid_view);

        getJsonData();
        imageModelArrayList = imageListSigleton.getImageModelArrayList();

        GridAdapter adapter = new GridAdapter(getApplicationContext(),imageModelArrayList);
        imageListGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*
    Get the data.json data in JSon Array.
     */
    private void getJsonData()
    {
        try {
            JSONArray jsonArray = new JSONArray(loadJsonFromAsset());
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ImageModel model = new ImageModel();
                model.setTitle(jsonObject.getString(ImageConstants.TITLE));
                model.setUrl(jsonObject.getString(ImageConstants.URL));
                model.setHdUrl(jsonObject.getString(ImageConstants.HDURL));
                model.setExplanation(jsonObject.getString(ImageConstants.EXPLAINATION));
                model.setDate(jsonObject.getString(ImageConstants.DATE));
                imageListSigleton.addImageModel(model);
            }
        }
        catch (JSONException e)
        {

        }
    }

    /*
    Load json data from data.json file to String.
     */
    private String loadJsonFromAsset()
    {
        String json = null;
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
        }
        catch (IOException e)
        {

        }

        return json;
    }
}