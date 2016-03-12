package com.rintran.irregularverbs;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ITAdapter extends ArrayAdapter<Item> {

    Context context = null;
    int resource = 0;
    List<Item> objects;

    public ITAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        //LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View view = View.inflate(context,resource,null);

        ImageView img = (ImageView) view.findViewById(R.id.theme_image);
        TextView textView = (TextView) view.findViewById(R.id.theme_name);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/timesbi.ttf");
        textView.setTypeface(typeface);

        Item item = objects.get(position);
        img.setImageResource(item.getImage());
        textView.setText(item.getName());

        return view;
    }
}
