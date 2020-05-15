package com.example.lab5;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomListAdapter2 extends ArrayAdapter<String> {
    private Activity context;
    private String[] _captial;
    private String[] _country;
    private Integer[] _image;
    private String[] _alphabet;

    CustomListAdapter2(Activity context, String[] captial, String[] country, Integer[] image, String[] alphabet){
        super(context, R.layout.listrow1, captial);
        this.context = context;
        this._captial = captial;
        this._country = country;
        this._image = image;
        this._alphabet = alphabet;
    }

    public @NonNull
    View getView(int position, View view, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.listrow2,null,true);

        ImageView img = rowView.findViewById(R.id.iconID2);
        TextView capital = rowView.findViewById(R.id.capital2);
        TextView country = rowView.findViewById(R.id.country2);
        TextView alphabet = rowView.findViewById(R.id.alphabet);

        img.setImageResource(_image[position]);
        capital.setText(_captial[position]);
        country.setText(_country[position]);
        alphabet.setText(_alphabet[position]);

        return rowView;
    }


}
