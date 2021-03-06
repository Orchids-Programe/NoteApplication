package com.ark.arkremindme;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GhiChuAdapter extends BaseAdapter {

    private Note context;
    private int layout;
    LayoutInflater inflater;
    private List<GhiChu> ghiChuList;
    private ArrayList<GhiChu> arrayList;

    public GhiChuAdapter(Note context, int layout, List<GhiChu> ghiChuList) {
        this.context = context;
        this.layout = layout;
        this.ghiChuList = ghiChuList;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<GhiChu>();
        this.arrayList.addAll(ghiChuList);

    }

    @Override
    public int getCount() {
        return ghiChuList.size();
    }

    @Override
    public Object getItem(int position) {
        return ghiChuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtTen, txtNgay, txtThoiGian;
        ImageView imgDelete, imgEdit;
        CheckBox cbGhiChu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTen = (TextView) convertView.findViewById(R.id.textView);
            holder.txtNgay = (TextView) convertView.findViewById(R.id.textView8);
            holder.txtThoiGian = (TextView) convertView.findViewById(R.id.textView9);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageView2);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageView3);
            holder.cbGhiChu = (CheckBox) convertView.findViewById(R.id.checkBoxGC);
            convertView.setTag(holder);

            holder.cbGhiChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    GhiChu ghiChu = (GhiChu) cb.getTag();
                    ghiChu.setSelected(cb.isChecked());
                }
            });

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GhiChu ghiChu = ghiChuList.get(position);

        holder.cbGhiChu.setChecked(ghiChu.isSelected());
        holder.cbGhiChu.setTag(ghiChu);

        holder.txtTen.setText(ghiChu.getTenGC());
        holder.txtNgay.setText(ghiChu.getDate());
        holder.txtThoiGian.setText(ghiChu.getTime());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaCongViec(ghiChu.getTenGC(), ghiChu.getIdGC());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaCV(ghiChu.getTenGC(), ghiChu.getIdGC());

            }
        });

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ghiChuList.clear();
        if (charText.length() == 0) {
            ghiChuList.addAll(arrayList);
        } else {
            for (GhiChu ghichu : arrayList) {
                if (ghichu.getTenGC().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ghiChuList.add(ghichu);
                }
            }
        }
        notifyDataSetChanged();
    }
}
