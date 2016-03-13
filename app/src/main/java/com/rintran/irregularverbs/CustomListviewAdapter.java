package com.rintran.irregularverbs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomListviewAdapter extends ArrayAdapter<Verbs> {
    Context context;
    int resource;
    List<Verbs> objects;
    public CustomListviewAdapter(Context context, int resource, List<Verbs> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context,resource,null);

        TextView tvNguyenMau = (TextView)convertView.findViewById(R.id.nguyenmau);
        TextView tvQuaKhu = (TextView)convertView.findViewById(R.id.quakhu);
        TextView tvQuaKhuPhanTu = (TextView)convertView.findViewById(R.id.quakhuphantu);
        TextView tvNghia = (TextView)convertView.findViewById(R.id.nghia);

        Verbs item = objects.get(position);
        tvNguyenMau.setText(item.getNguyenmau());
        tvQuaKhu.setText(item.getQuakhu());
        tvQuaKhuPhanTu.setText(item.getQuakhuphantu());
        tvNghia.setText(item.getNghia());

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/timesbi.ttf");
        tvNghia.setTypeface(typeface);
        tvNguyenMau.setTypeface(typeface);
        tvQuaKhu.setTypeface(typeface);
        tvQuaKhuPhanTu.setTypeface(typeface);

        return convertView;
        }
}
