package org.jefrienalvizures.clashtournament.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefrien Alvizures on 03/06/2016.
 */
public class AdaptadorUsuario extends ArrayAdapter<Usuario> {

    private Context contexto;
    private ArrayList<Usuario> arrayList;

    public AdaptadorUsuario(Context context, List<Usuario> resource) {
        super(context, 0,resource);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(
                    R.layout.item_usuario_clan,
                    parent,
                    false);
        }

        //Obteniendo instancia de la Tarea en la posición actual
        Usuario item = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.idUsuarioItem);
        id.setText(item.getIdUsuario());

        TextView usuario = (TextView) listItemView.findViewById(R.id.nombreUsuarioItem);
        usuario.setText(item.getUsuario());

        return listItemView;
    }
}
