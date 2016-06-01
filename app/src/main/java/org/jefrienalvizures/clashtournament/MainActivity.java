package org.jefrienalvizures.clashtournament;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usuario,password;
    Button btnLogin;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.LoginTxtUsuario);
        password = (EditText) findViewById(R.id.LoginTxtPassword);


        btnLogin =(Button) findViewById(R.id.btnLogin);
        link = (TextView) findViewById(R.id.linkLogin);
        btnLogin.setOnClickListener(this);
        link.setOnClickListener(this);
    }


    public void log(){
        if(!validar()){
            Toast.makeText(getBaseContext(),"Verifique sus credenciales",Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(true);
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog pg = new ProgressDialog(MainActivity.this);
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
                        params.put("usuario",usuario.getText().toString());
                        params.put("password",password.getText().toString());
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.autenticar, new JSONObject(params), new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    //JSONArray listaUsuario =  response.getJSONArray("usuario");
                                    JSONObject user = response.getJSONObject("usuario");
                                    if(response.getInt("estado") == 1){


                                        Usuario userLogged = new Usuario(
                                                user.getInt("idUsuario"),
                                                user.getString("usuario"),
                                                user.getString("nombre"),
                                                user.getInt("clan")
                                        );
                                        Comunicador.setUsuario(userLogged);
                                        pg.dismiss();
                                        Toast.makeText(getApplicationContext(),"Bienvenido "+user.getString("nombre"),Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(MainActivity.this,Inicio.class));
                                        MainActivity.this.finish();
                                    } else {
                                        pg.dismiss();
                                        btnLogin.setEnabled(true);
                                        Toast.makeText(getApplicationContext(),"Verifique sus credenciales",Toast.LENGTH_LONG).show();
                                    }
                                } catch(Exception ex) {
                                    Log.e("Error",ex.getMessage());
                                }
                            }
                        }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Response",error.getMessage());
                            }
                        });
                        WebService.getInstance(getBaseContext()).addToRequestQueue(request);

                        //FINALIZA PROCESO DE LOGIN
                    }
                }
                ,3000);


    }

    public boolean validar(){
        boolean respuesta = true;

        String u = usuario.getText().toString();
        String p = password.getText().toString();

        if(u.isEmpty() || u.length() < 3){
            usuario.setError("Minimo 3 caracteres!");
            respuesta = false;
        } else {
            usuario.setError(null);
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
        switch (v.getId()){
            case R.id.btnLogin:
                log();
                break;
            case R.id.linkLogin:
                startActivity(new Intent(MainActivity.this,Registro.class));
                this.finish();
                break;
        }
    }
}
