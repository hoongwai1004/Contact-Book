package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 정예린 on 11/16/2017.
 */

public class ListViewAdapter extends ArrayAdapter{

    List list = new ArrayList();

    public ListViewAdapter(Context context, int resources) {
        super(context, resources);
    }

    static class LayoutHandler{
        TextView ID, NAME;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.contact_details, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.ID = (TextView)row.findViewById(R.id.viewID);
            layoutHandler.NAME = (TextView)row.findViewById(R.id.viewContactName);
            row.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler)row.getTag();
        }

        Contact contact = (Contact)this.getItem(position);
        layoutHandler.ID.setText(contact.getId());
        layoutHandler.NAME.setText(contact.getName());
        return row;
    }
}
