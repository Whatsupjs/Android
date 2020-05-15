package com.example.lab7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] _name;

    CustomListAdapter(Activity context, String[] name) {
        super(context, R.layout.listrow, name);
        this.context = context;
        this._name = name;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater =context.getLayoutInflater();

        // ******* //
        View rowView = inflater.inflate(R.layout.listrow, null, true);

        TextView nameText = rowView.findViewById(R.id.name);
        nameText.setText(_name[position]);

        return rowView;
    }
}
