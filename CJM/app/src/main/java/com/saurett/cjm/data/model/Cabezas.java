package com.saurett.cjm.data.model;

import java.io.Serializable;

/**
 * Created by texiumuser on 28/02/2017.
 */

public class Cabezas implements Serializable {

    private String nombreDelGanado;
    private String raza;
    private String edadD;
    private String costo;
    private String tipoDeGanado;
    private String colorDeGanado;
    private String genero;
    private String pesoInicial;
    private String numeroDeAreteInterno;
    private String firebaseIdAreteInterno;
    private String numeroDeAreteSiniga;
    private String pesoTerminal;

    private String numeroTotalDePartos;
    private String numeroDePartosInterno;
    private String fechaDelUltimoParto;


    private String firebaseId;
    private String estatus;
    private Long fechaDeCreacion;
    private Long fechaDeEdicion;

    public Cabezas() {
    }

    public String getNombreDelGanado() {
        return nombreDelGanado;
    }

    public void setNombreDelGanado(String nombreDelGanado) {
        this.nombreDelGanado = nombreDelGanado;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdadD() {
        return edadD;
    }

    public void setEdadD(String edadD) {
        this.edadD = edadD;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getTipoDeGanado() {
        return tipoDeGanado;
    }

    public void setTipoDeGanado(String tipoDeGanado) {
        this.tipoDeGanado = tipoDeGanado;
    }

    public String getColorDeGanado() {
        return colorDeGanado;
    }

    public void setColorDeGanado(String colorDeGanado) {
        this.colorDeGanado = colorDeGanado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(String pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public String getNumeroDeAreteInterno() {
        return numeroDeAreteInterno;
    }

    public void setNumeroDeAreteInterno(String numeroDeAreteInterno) {
        this.numeroDeAreteInterno = numeroDeAreteInterno;
    }

    public String getFirebaseIdAreteInterno() {
        return firebaseIdAreteInterno;
    }

    public void setFirebaseIdAreteInterno(String firebaseIdAreteInterno) {
        this.firebaseIdAreteInterno = firebaseIdAreteInterno;
    }

    public String getNumeroDeAreteSiniga() {
        return numeroDeAreteSiniga;
    }

    public void setNumeroDeAreteSiniga(String numeroDeAreteSiniga) {
        this.numeroDeAreteSiniga = numeroDeAreteSiniga;
    }

    public String getPesoTerminal() {
        return pesoTerminal;
    }

    public void setPesoTerminal(String pesoTerminal) {
        this.pesoTerminal = pesoTerminal;
    }

    public String getNumeroTotalDePartos() {
        return numeroTotalDePartos;
    }

    public void setNumeroTotalDePartos(String numeroTotalDePartos) {
        this.numeroTotalDePartos = numeroTotalDePartos;
    }

    public String getNumeroDePartosInterno() {
        return numeroDePartosInterno;
    }

    public void setNumeroDePartosInterno(String numeroDePartosInterno) {
        this.numeroDePartosInterno = numeroDePartosInterno;
    }

    public String getFechaDelUltimoParto() {
        return fechaDelUltimoParto;
    }

    public void setFechaDelUltimoParto(String fechaDelUltimoParto) {
        this.fechaDelUltimoParto = fechaDelUltimoParto;
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
