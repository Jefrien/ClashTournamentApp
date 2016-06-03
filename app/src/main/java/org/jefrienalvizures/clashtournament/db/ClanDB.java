package org.jefrienalvizures.clashtournament.db;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Familia on 02/06/2016.
 */
public class ClanDB {

    Context context;
    Clan clan = null;

    public Clan obtenerById(Context c, final String id){
        this.context = c;

        final ProgressDialog pg = new ProgressDialog(context, R.style.Oscuro_ProgressDialog);
        pg.setIndeterminate(true);
        pg.setMessage("Iniciando ClashTournament");
        pg.setCancelable(false);
        pg.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

        // INICIA REGISTRO

        Map<String,String> params = new HashMap<String,String>();
        params.put("idClan",id);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.getClanById, new JSONObject(params), new Response.Listener<JSONObject>() {
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
                        Log.e("CLAN DESDE EL SERVIDOR",clan.getNombreClan());

                    } else {
                        Toast.makeText(context,"Error obteniendo el clan",Toast.LENGTH_SHORT).show();
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

}
