package com.example.boyidashi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.boyidashi.R;
import com.example.boyidashi.model.Used;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListUseddataAdapter extends BaseAdapter {
    private List<Used> listUsed;
    private LayoutInflater inflater;
    private Context context;


    public ListUseddataAdapter(List<Used> listUsed, Context context) {
        this.listUsed = listUsed;
        this.context = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return listUsed.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsed.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_detial, parent, false);
            holder = new ViewHolder();
            holder.tvNum = view.findViewById(R.id.tv_num);
            holder.tvtype = view.findViewById(R.id.tv_type);
            holder.tvDate = view.findViewById(R.id.tv_date);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (listUsed.get(position).getUseType() == 1) {
            holder.tvtype.setText("支出");
        } else {
            holder.tvtype.setText("收入");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.tvNum.setText(listUsed.get(position).getNumber() + "");
        holder.tvDate.setText(simpleDateFormat.format(listUsed.get(position).getData()));
        return view;
    }

    class ViewHolder {
        private TextView tvtype, tvNum, tvDate;

    }
}
