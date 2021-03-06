package org.jefrienalvizures.clashtournament.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.bean.Usuario;

import java.util.List;

/**
 * Created by Jefrien Alvizures on 03/06/2016.
 */
public class AdaptadorUsuario extends BaseAdapter {

    private Context context;
    private List<Usuario> items;

    public AdaptadorUsuario(Context context,List<Usuario> items){
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
        TextView id = (TextView) rowView.findViewById(R.id.idUsuarioItem);
        Usuario item = this.items.get(position);
        us.setText(item.getUsuario());
        id.setText(Integer.toString(item.getIdUsuario()));
        return rowView;
    }

}

