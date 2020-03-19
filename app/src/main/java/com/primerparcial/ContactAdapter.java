package com.primerparcial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    TextView lblnombre,lbltel,lblgrupo;

    public ContactAdapter(Context context, List<Contact> objects) {
         super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_diseno, parent, false);
        }

        lblnombre = convertView.findViewById(R.id.lbl_nombre);
        lbltel = convertView.findViewById(R.id.lbl_tel);
        lblgrupo = convertView.findViewById(R.id.lbl_grupo);

        Contact contact = getItem(position);

        assert contact != null;
        lblnombre.setText(contact.getNombre());
        lbltel.setText(contact.getTel());
        lblgrupo.setText(contact.getGrupo());

        return convertView;
    }



}

