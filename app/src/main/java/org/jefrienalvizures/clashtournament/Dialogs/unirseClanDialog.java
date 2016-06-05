package org.jefrienalvizures.clashtournament.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.db.usuarioDB;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Familia on 05/06/2016.
 */
public class unirseClanDialog extends DialogFragment implements View.OnClickListener {

    EditText idClanTxt;
    Button btnUnir;


    public interface OnJoinedClanListener{
        void onJoinedClanListener(int respuesta);
    }

    OnJoinedClanListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnJoinedClanListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSetTitleListener");

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return dialogoCrear();
    }

    public AlertDialog dialogoCrear(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_unirse_clan, null);

        builder.setView(v);

        idClanTxt = (EditText) v.findViewById(R.id.unirmeClanID);
        btnUnir = (Button) v.findViewById(R.id.btnUnirseClanForm);
        btnUnir.setOnClickListener(this);

        return builder.create();
    }

    public void unirme(){

        final ProgressDialog pg = new ProgressDialog(getContext(),R.style.Oscuro_ProgressDialog);
        pg.setIndeterminate(true);
        pg.setMessage("Enviando solicitud...");
        pg.setCancelable(false);
        pg.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // INICIA REGISTRO



                        Map<String,String> params2 = new HashMap<String,String>();
                        params2.put("usuario", Comunicador.getUsuario().getUsuario());
                        params2.put("idUsuario",Comunicador.getUsuario().getIdUsuario()+"");
                        params2.put("estado","3");

                        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, WebService.updateEstado, new JSONObject(params2), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Usuario u = Comunicador.getUsuario();
                                u.setEstado(3);
                                u.setClan(Integer.parseInt(idClanTxt.getText().toString()));
                                Comunicador.setUsuario(u);
                                new usuarioDB().modificar(getContext(),u);
                                listener.onJoinedClanListener(1);
                                pg.dismiss();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error response",error.getMessage());
                            }
                        });
                        WebService.getInstance(getContext()).addToRequestQueue(request2);


                        Map<String,String> params3 = new HashMap<String,String>();
                        params3.put("usuario",Comunicador.getUsuario().getUsuario());
                        params3.put("idUsuario",Comunicador.getUsuario().getIdUsuario()+"");
                        params3.put("clan",idClanTxt.getText().toString());

                        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, WebService.addClanUsuario, new JSONObject(params3), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error response",error.getMessage());
                            }
                        });
                        WebService.getInstance(getContext()).addToRequestQueue(request3);
                    }
                }
                ,3000);
    }




    public boolean validar(){
        boolean res = true;

        String idTxt = idClanTxt.getText().toString();
        if(idTxt.isEmpty() || idTxt.length() < 3){
            idClanTxt.setError("Minimo 3 caracteres");
            res = false;
        } else {
            idClanTxt.setError(null);
        }

        return res;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUnirseClanForm:
                unirme();
                break;
        }
    }
}
