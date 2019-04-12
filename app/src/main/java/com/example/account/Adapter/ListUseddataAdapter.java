package com.example.account.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.account.R;
import com.example.account.model.Used;

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
        if (view == null){
            view = inflater.inflate(R.layout.item_detial, parent, false);


        }


            return null;
    }
}
