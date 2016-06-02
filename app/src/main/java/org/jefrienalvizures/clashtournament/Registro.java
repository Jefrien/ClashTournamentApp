package org.jefrienalvizures.clashtournament;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText usuario,nombre,password;
    Button btnRegistro;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (EditText) findViewById(R.id.RegistroTxtUsuario);
        nombre = (EditText) findViewById(R.id.RegistroTxtNombre);
        password =(EditText) findViewById(R.id.RegistroTxtPassword);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        link = (TextView) findViewById(R.id.linkRegistro);
        btnRegistro.setOnClickListener(this);
        link.setOnClickListener(this);
    }


    public void registro(){
        if(!validar()){
            Toast.makeText(getBaseContext(),"Verifique los datos",Toast.LENGTH_SHORT).show();
            btnRegistro.setEnabled(true);
            return;
        }

        btnRegistro.setEnabled(false);
        final ProgressDialog pg = new ProgressDialog(this,R.style.Oscuro_ProgressDialog);
        pg.setIndeterminate(true);
        pg.setMessage("Registrando...");
        pg.setCancelable(false);
        pg.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // INICIA REGISTRO

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("usuario",usuario.getText().toString());
                        params.put("nombre",nombre.getText().toString());
                        params.put("password",password.getText().toString());

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.registrar, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    int estado = response.getInt("estado");
                                    if(estado == 1){
                                        pg.dismiss();
                                        Toast.makeText(getBaseContext(),response.getString("mensaje"),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registro.this,MainActivity.class));
                                        Registro.this.finish();
                                    } else {
                                        pg.dismiss();
                                        btnRegistro.setEnabled(true);
                                        Toast.makeText(getBaseContext(),response.getString("mensaje"),Toast.LENGTH_SHORT).show();
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
                        WebService.getInstance(getBaseContext()).addToRequestQueue(request);
                        // FIALIZA REGISTRO
                    }
                }
                ,3000);
    }


    public boolean validar(){
       boolean respuesta = true;

        String u = usuario.getText().toString();
        String n = nombre.getText().toString();
        String p = password.getText().toString();

        if(u.isEmpty() || u.length() < 3){
            usuario.setError("Minimo 3 caracteres!");
            respuesta = false;
        } else {
            usuario.setError(null);
        }
        if(n.isEmpty() || n.length() < 3){
            nombre.setError("Minimo 3 caracteres!");
            respuesta = false;
        } else {
            nombre.setError(null);
        }

        if(p.isEmpty() || p.length() < 3){
            password.setError("Minimo 3 caracteres!");
            respuesta = false;
        } else {
            password.setError(null);
        }


        return  respuesta;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegistro:
                registro();
                break;
            case R.id.linkRegistro:
                startActivity(new Intent(Registro.this,MainActivity.class));
                this.finish();
                break;
        }
    }
}
