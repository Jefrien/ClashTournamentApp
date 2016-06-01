package org.jefrienalvizures.clashtournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Usuario u = Comunicador.getUsuario();
        TextView t = (TextView) findViewById(R.id.nombre);
        t.setText(u.getNombre().toString());
    }
}
