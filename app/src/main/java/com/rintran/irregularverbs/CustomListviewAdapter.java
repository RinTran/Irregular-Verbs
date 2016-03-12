package com.rintran.irregularverbs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rintr on 3/9/2016.
 */
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,resource,null);
        TextView tvNguyenMau = (TextView)view.findViewById(R.id.nguyenmau);
        TextView tvQuaKhu = (TextView)view.findViewById(R.id.quakhu);
        TextView tvQuaKhuPhanTu = (TextView)view.findViewById(R.id.quakhuphantu);
        TextView tvNghia = (TextView)view.findViewById(R.id.nghia);

        Verbs item = objects.get(position);
        tvNguyenMau.setText(item.getNguyenmau());
        tvQuaKhu.setText(item.getQuakhu());
        tvQuaKhuPhanTu.setText(item.getQuakhuphantu());
        tvNghia.setText(item.getNghia());

        return view;
        }
}
