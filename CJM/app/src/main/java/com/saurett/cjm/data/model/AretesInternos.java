package com.saurett.cjm.data.model;

import java.io.Serializable;

/**
 * Created by texiumuser on 28/02/2017.
 */

public class AretesInternos implements Serializable {

    private String numeroDeAreteInterno;

    private String firebaseId;
    private String estatus;
    private Long fechaDeCreacion;
    private Long fechaDeEdicion;

    public AretesInternos() {
    }

    public AretesInternos(String numeroDeAreteInterno, String firebaseId, String estatus, Long fechaDeCreacion, Long fechaDeEdicion) {
        this.numeroDeAreteInterno = numeroDeAreteInterno;
        this.firebaseId = firebaseId;
        this.estatus = estatus;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeEdicion = fechaDeEdicion;
    }

    public String getNumeroDeAreteInterno() {
        return numeroDeAreteInterno;
    }

    public void setNumeroDeAreteInterno(String numeroDeAreteInterno) {
        this.numeroDeAreteInterno = numeroDeAreteInterno;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Long getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Long fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Long getFechaDeEdicion() {
        return fechaDeEdicion;
    }

    public void setFechaDeEdicion(Long fechaDeEdicion) {
        this.fechaDeEdicion = fechaDeEdicion;
    }
}
