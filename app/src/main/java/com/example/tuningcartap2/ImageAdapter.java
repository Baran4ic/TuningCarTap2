package com.example.tuningcartap2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<com.example.tuningcartap2.Garage.CarImage> carImages;

    public ImageAdapter(Context context, ArrayList<com.example.tuningcartap2.Garage.CarImage> carImages) {
        this.context = context;
        this.carImages = carImages;
    }

    @Override
    public int getCount() {
        return carImages.size();
    }

    @Override
    public Object getItem(int position) {
        return carImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(carImages.get(position).getImageResId());
        return imageView;
    }
}