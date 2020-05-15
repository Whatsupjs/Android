package com.example.assignment3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private  Activity context;
    private String[] _city;
    private String[] _name;

    public CustomListAdapter(Activity context, String[] city, String[] name){
        super(context, R.layout.list, city);
        this.context = context;
        this._city = city;
        this._name = name;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list, null, true);

        TextView cityText = rowView.findViewById(R.id.listCity);
        cityText.setText(_city[position]);
        TextView nameText = rowView.findViewById(R.id.listName);
        nameText.setText(_name[position]);

        return rowView;
    }
}
