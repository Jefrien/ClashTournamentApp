package org.jefrienalvizures.clashtournament.bean;

/**
 * Created by Familia on 31/05/2016.
 */
public class Usuario {
    private Integer idUsuario;
    private String usuario;
    private String nombre;
    private int clan;
    private int estado;

    public Usuario(Integer idUsuario, String usuario, String nombre, int clan) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.clan = clan;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClan() {
        return clan;
    }

    public void setClan(int clan) {
        this.clan = clan;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
