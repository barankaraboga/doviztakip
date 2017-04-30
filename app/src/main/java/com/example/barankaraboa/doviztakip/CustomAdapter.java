package com.example.barankaraboa.doviztakip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barankaraboa.doviztakip.VeriAl.JsonParse;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<String> {

    JsonParse cek = new JsonParse();

    Context c;
    ArrayList<String> kurlar = cek.kurlarim;
    int[]images;

    public void dizidondur()  {

    }


    public CustomAdapter ( Context ctx,ArrayList<String> kurlar,int[]images) {

super(ctx,R.layout.model,kurlar);

        this.c= ctx;
        this.kurlar=cek.kurlarim;
        this.images=images;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {

            LayoutInflater infi=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infi.inflate(R.layout.model,null);
        }

        TextView tx = (TextView)(convertView.findViewById(R.id.isimyaz));
        ImageView img = (ImageView)(convertView.findViewById(R.id.imageView));

        tx.setText(kurlar.get(position));

        img.setImageResource(images[position]);

        return convertView;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {

            LayoutInflater infi=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infi.inflate(R.layout.model,null);
        }

        TextView tx = (TextView)(convertView.findViewById(R.id.isimyaz));
         ImageView img = (ImageView)(convertView.findViewById(R.id.imageView));
        tx.setText(kurlar.get(position).toString());

       img.setImageResource(images[position]);

        return convertView;
    }
}
