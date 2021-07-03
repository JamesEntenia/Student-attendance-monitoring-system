package com.example.studentattendance;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    CheckBox AbsentCB,PresentCB;
    Context cont;
    ArrayList<String> Name;
    TextView NameTV;

    public ItemAdapter(Context c, ArrayList<String> n){
        Name = n;
        cont = c;
    }

    @Override
    public int getCount() {

        return Name.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(mInflater == null) {
            mInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(v == null) {
            v = mInflater.inflate(R.layout.update_attendance_listview, null);
        }
        NameTV = (TextView) v.findViewById(R.id.NameTextView);
        AbsentCB = (CheckBox) v.findViewById(R.id.AbsentCB);
        PresentCB = (CheckBox) v.findViewById(R.id.PresentCB);

        NameTV.setText(Name.get(position));

        return v;
    }


}
