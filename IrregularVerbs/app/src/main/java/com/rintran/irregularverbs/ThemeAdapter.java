package com.rintran.irregularverbs;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ThemeAdapter extends ArrayAdapter<Item>{

    Activity context=null;
    Item array[];
    int layoutId;
    public ThemeAdapter(Activity context, int resource, Item[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutId = resource;
        this.array = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        if(position>=0)
        {

            final TextView textView = (TextView)convertView.findViewById(R.id.text_theme);
            final ImageView imageView = (ImageView)convertView.findViewById(R.id.image_theme);

            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/timesbi.ttf");
            textView.setTypeface(typeface);

            switch(position) {
                case 0:

                default:
                    textView.setText(array[position].getName());
                    imageView.setImageResource(array[position].getLinkImage());
                    break;
            }
        }

        return convertView;
    }
}
