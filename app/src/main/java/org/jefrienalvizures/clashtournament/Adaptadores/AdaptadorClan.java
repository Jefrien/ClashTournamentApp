package org.jefrienalvizures.clashtournament.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.bean.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefrien Alvizures on 03/06/2016.
 */
public class AdaptadorClan extends BaseAdapter {

    private Context context;
    private List<Clan> items;

    public AdaptadorClan(Context context,List<Clan> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_usuario_clan, parent, false);
        }

        TextView us = (TextView) rowView.findViewById(R.id.nombreUsuarioItem);
        Clan clan = this.items.get(position);
        us.setText(clan.getNombreClan());
        return rowView;
    }

}
