package org.jefrienalvizures.clashtournament.clases;

import org.jefrienalvizures.clashtournament.bean.Usuario;

/**
 * Created by Familia on 31/05/2016.
 */
public class Comunicador {
    private static Usuario usuario = null;

    public static void setUsuario(Usuario _usuario){
        usuario = _usuario;
    }
    public static Usuario getUsuario(){
        return usuario;
    }
}
