package org.jefrienalvizures.clashtournament.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.db.usuarioDB;

/**
 * Created by Familia on 31/05/2016.
 */
public class InicioFragment extends Fragment {

    TextView usuarioTxt;
    WebView wb;
    Usuario u;

    public interface OnButtonClickListenerInicio{
        void onAccionListener(int accion);
    }

    OnButtonClickListenerInicio listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnButtonClickListenerInicio) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSetTitleListener");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        u = Comunicador.getUsuario();
        View v;
             v = inflater.inflate(R.layout.fragment_inicio,container,false);
            Button crearClanBtn = (Button) v.findViewById(R.id.btnCrearClan);
            crearClanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAccionListener(1);
                }
            });

        usuarioTxt = (TextView) v.findViewById(R.id.usuarioTxtInicio);
        usuarioTxt.setText(u.getNombre());

        return v;
    }

    public void siClan(View v){

    }



}
