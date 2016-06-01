package org.jefrienalvizures.clashtournament.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;

/**
 * Created by Familia on 31/05/2016.
 */
public class InicioFragment extends Fragment {

    TextView usuarioTxt;
    WebView wb;
    Usuario u;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        u = Comunicador.getUsuario();
        View v;
        if(u.getClan() == 0){
             v = inflater.inflate(R.layout.fragment_inicio,container,false);
        } else {
            v = inflater.inflate(R.layout.fragment_inicio0,container,false);
        }



        usuarioTxt = (TextView) v.findViewById(R.id.usuarioTxtInicio);
        usuarioTxt.setText(u.getNombre());

        Descripcion(v);

        return v;
    }

    public void Descripcion(View v){
        wb= (WebView) v.findViewById(R.id.webViewDesc);
        String text;
        text = "<html<body><p align=\"justify\">";
        text+= getString(R.string.bienvenida);
        text+= "</p></body></html>";
        wb.loadData(text, "text/html", "utf-8");
    }

}
