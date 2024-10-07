package espotify.DataTypes;

import java.util.List;

public class DTDatosListaReproduccion {
    private String nombreLista;
    private String fotoLista;
    private String tipoDeLista;
    private List<DTTemaSimple> temas;
    private String genero; // Solo para listas por defecto
    private String cliente; // Solo para listas particulares
    private Boolean privacidad; // Solo para listas particulares

    // Constructor para listas por defecto
    public DTDatosListaReproduccion(String nombreLista, String fotoLista, String tipoDeLista, List<DTTemaSimple> temas, String genero) {
        this.nombreLista = nombreLista;
        this.fotoLista = fotoLista;
        this.tipoDeLista = tipoDeLista;
        this.temas = temas;
        this.genero = genero;
        this.cliente = null;
        this.privacidad = null;
    }

    // Constructor para listas particulares
    public DTDatosListaReproduccion(String nombreLista, String fotoLista, String tipoDeLista, List<DTTemaSimple> temas, String cliente, Boolean privacidad) {
        this.nombreLista = nombreLista;
        this.fotoLista = fotoLista;
        this.tipoDeLista = tipoDeLista;
        this.temas = temas;
        this.genero = null;
        this.cliente = cliente;
        this.privacidad = privacidad;
    }

    // Getters & Setters
    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getFotoLista() {
        return fotoLista;
    }

    public void setFotoLista(String fotoLista) {
        this.fotoLista = fotoLista;
    }

    public String getTipoDeLista() {
        return tipoDeLista;
    }

    public void setTipoDeLista(String tipoDeLista) {
        this.tipoDeLista = tipoDeLista;
    }

    public List<DTTemaSimple> getTemas() {
        return temas;
    }

    public void setTemas(List<DTTemaSimple> temas) {
        this.temas = temas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Boolean getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }
}
