package com.example.lab5;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] _captial;
    private String[] _country;
    private Integer[] _image;

    CustomListAdapter(Activity context, String[] captial, String[] country, Integer[] image){
        super(context, R.layout.listrow1, captial);
        this.context = context;
        this._captial = captial;
        this._country = country;
        this._image = image;
    }

    public @NonNull
    View getView(int position, View view, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.listrow1,null,true);

        ImageView img = rowView.findViewById(R.id.iconID);
        TextView capital = rowView.findViewById(R.id.capital1);
        TextView country = rowView.findViewById(R.id.country1);

        img.setImageResource(_image[position]);
        capital.setText(_captial[position]);
        country.setText(_country[position]);

        return rowView;
    }


}
