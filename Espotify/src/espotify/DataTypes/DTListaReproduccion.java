package espotify.DataTypes;

public abstract class DTListaReproduccion {
private String nombreLista;
private String fotoLista;

    public DTListaReproduccion() {
    }

    public DTListaReproduccion(String nombreLista, String fotoLista) {
        this.nombreLista = nombreLista;
        this.fotoLista = fotoLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public String getFotoLista() {
        return fotoLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public void setFotoLista(String fotoLista) {
        this.fotoLista = fotoLista;
    }
    
}
