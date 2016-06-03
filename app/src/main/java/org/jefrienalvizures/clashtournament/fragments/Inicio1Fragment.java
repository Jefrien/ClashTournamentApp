package org.jefrienalvizures.clashtournament.fragments;

import android.app.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Clanes;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Familia on 02/06/2016.
 */
public class Inicio1Fragment extends Fragment {
    TextView usuarioTxt,nombreClanTxt,numeroIntegrantesClanTxt;
    WebView wb;
    Usuario u;
    Clan clan;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        u = Comunicador.getUsuario();
        clan = Clanes.getClan();
        View v;

            v = inflater.inflate(R.layout.fragment_inicio0,container,false);

        usuarioTxt = (TextView) v.findViewById(R.id.usuarioTxtInicio);
        usuarioTxt.setText(u.getNombre());
        nombreClanTxt = (TextView) v.findViewById(R.id.nombreClanInicio);
        numeroIntegrantesClanTxt = (TextView) v.findViewById(R.id.numeroIntegrantesClanInicio);

        clanId();
        return v;
    }

    public void clanId(){


        final ProgressDialog pg = new ProgressDialog(getContext(),R.style.Oscuro_ProgressDialog);
        pg.setIndeterminate(true);
        pg.setMessage("Iniciando Sesión...");
        pg.setCancelable(false);
        pg.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //INICIA PROCESO DE LOGIN

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("idClan",Comunicador.getUsuario().getClan()+"");


                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.getClanById, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{

                                    int estado = response.getInt("estado");
                                    if(estado == 1){


                                        JSONObject c = response.getJSONObject("clan");
                                        Clan clan = new Clan(
                                                c.getInt("idClan"),
                                                c.getString("nombre"),
                                                c.getInt("integrantes"),
                                                c.getInt("idUsuario"));
                                        Log.e("CLAN DESDE EL SERVIDOR",clan.getNombreClan());
                                        Clanes.setClan(clan);
                                        nombreClanTxt.setText(clan.getNombreClan());
                                        //numeroIntegrantesClanTxt.setText(clan.getIntegrantesClan()+"/50");
                                        pg.dismiss();

                                    } else {
                                        Toast.makeText(getContext(),"Error obteniendo el clan",Toast.LENGTH_SHORT).show();
                                    }
                                } catch(Exception ex){
                                    Log.e("Error",ex.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error response",error.getMessage());
                            }
                        });
                        WebService.getInstance(getContext()).addToRequestQueue(request);
                        // FIALIZA REGISTRO
                    }
                }
                ,3000);


    }


}
