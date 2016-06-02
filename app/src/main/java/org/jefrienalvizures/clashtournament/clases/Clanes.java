package org.jefrienalvizures.clashtournament.clases;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.MainActivity;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Familia on 01/06/2016.
 */
public class Clanes {
    Clan clan = null;

    public void setClan(Clan c){
        this.clan = c;
    }

    public Clan getClanById(final int id, final Context context){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // INICIA REGISTRO

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("idClan",""+id);

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.getByIdClan, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    int estado = response.getInt("estado");
                                    if(estado == 1){
                                        JSONObject c = response.getJSONObject("clan");
                                        clan = new Clan(
                                                c.getInt("idClan"),
                                                c.getString("nombre"),
                                                c.getInt("integrantes"),
                                                c.getInt("idUsuario"));
                                    } else {
                                        Toast.makeText(context,response.getString("mensaje"),Toast.LENGTH_SHORT).show();
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
                        WebService.getInstance(context).addToRequestQueue(request);
                        // FIALIZA REGISTRO
                    }
                }
                ,3000);
        return clan;
    }

    public Clan getClanByName(final String nombre, final Context context) {

                        // INICIA REGISTRO

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre", nombre);

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.getByNameClan, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int estado = response.getInt("estado");
                                    if (estado == 1) {
                                        JSONObject c = response.getJSONObject("clan");
                                        clan = new Clan(
                                                c.getInt("idClan"),
                                                c.getString("nombre"),
                                                c.getInt("integrantes"),
                                                c.getInt("idUsuario"));
                                    } else {
                                        Toast.makeText(context, response.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception ex) {
                                    Log.e("Error", ex.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error response", error.getMessage());
                            }
                        });
                        WebService.getInstance(context).addToRequestQueue(request);
                        // FIALIZA REGISTRO

        return clan;
                }
}
