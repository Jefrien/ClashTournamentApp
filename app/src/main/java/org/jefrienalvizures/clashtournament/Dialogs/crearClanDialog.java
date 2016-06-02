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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.MainActivity;
import org.jefrienalvizures.clashtournament.R;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Familia on 01/06/2016.
 */
public class crearClanDialog extends DialogFragment implements View.OnClickListener{


    public interface OnClanCreatedListener{
        void onClanCreatedListener(String nombreClan);
    }

    OnClanCreatedListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnClanCreatedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSetTitleListener");

        }
    }



    EditText nombre;
    Button btnCrear;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return dialogoCrear();
    }

    public AlertDialog dialogoCrear(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_crear_clan, null);

        builder.setView(v);

        nombre = (EditText) v.findViewById(R.id.crearClanNombre);
        btnCrear = (Button) v.findViewById(R.id.btnCrearClanForm);

        btnCrear.setOnClickListener(this);

        return builder.create();
    }

    public void crearClan(){
        if(!validar()){
            Toast.makeText(getContext(),"Verifique los datos",Toast.LENGTH_SHORT).show();
            btnCrear.setEnabled(true);
            return;
        }

        btnCrear.setEnabled(false);
        final ProgressDialog pg = new ProgressDialog(getContext(),R.style.Oscuro_ProgressDialog);
        pg.setIndeterminate(true);
        pg.setMessage("Creando...");
        pg.setCancelable(false);
        pg.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // INICIA REGISTRO

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("nombre",nombre.getText().toString());
                        params.put("integrantes","1");
                        params.put("idUsuario",Comunicador.getUsuario().getIdUsuario().toString());


                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.addClan, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    int estado = response.getInt("estado");
                                    if(estado == 1){
                                        pg.dismiss();
                                        Toast.makeText(getContext(),response.getString("mensaje"),Toast.LENGTH_SHORT).show();
                                        crearClanDialog.this.dismiss();
                                        listener.onClanCreatedListener(nombre.getText().toString());
                                    } else {
                                        pg.dismiss();
                                        btnCrear.setEnabled(true);
                                        Toast.makeText(getContext(),response.getString("mensaje"),Toast.LENGTH_SHORT).show();
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

    public boolean validar(){
        boolean res = true;
        String n = nombre.getText().toString();
        if(n.isEmpty() || n.length() < 3){
            nombre.setError("Minimo 3 caracteres");
            res = false;
        } else {
            nombre.setError(null);
        }
        return res;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrearClanForm:
                crearClan();
                break;
        }
    }
}
