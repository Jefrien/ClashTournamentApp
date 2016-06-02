package org.jefrienalvizures.clashtournament.bean;

/**
 * Created by Jefrien Alvizures on 01/06/2016.
 */
public class Clan {
    private Integer idClan;
    private String nombreClan;
    private int integrantesClan;
    private int idUsuarioClan;

    public Clan() {
    }

    public Clan(Integer idClan, String nombreClan, int integrantesClan, int idUsuarioClan) {
        this.idClan = idClan;
        this.nombreClan = nombreClan;
        this.integrantesClan = integrantesClan;
        this.idUsuarioClan = idUsuarioClan;
    }

    public Integer getIdClan() {
        return idClan;
    }

    public void setIdClan(Integer idClan) {
        this.idClan = idClan;
    }

    public String getNombreClan() {
        return nombreClan;
    }

    public void setNombreClan(String nombreClan) {
        this.nombreClan = nombreClan;
    }

    public int getIntegrantesClan() {
        return integrantesClan;
    }

    public void setIntegrantesClan(int integrantesClan) {
        this.integrantesClan = integrantesClan;
    }

    public int getIdUsuarioClan() {
        return idUsuarioClan;
    }

    public void setIdUsuarioClan(int idUsuarioClan) {
        this.idUsuarioClan = idUsuarioClan;
    }
}
