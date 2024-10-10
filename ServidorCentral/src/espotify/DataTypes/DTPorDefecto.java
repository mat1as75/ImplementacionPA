package espotify.DataTypes;
public class DTPorDefecto extends DTListaReproduccion{
    private DTGenero miGenero;

    public DTPorDefecto() {
    }

    public DTPorDefecto(DTGenero miGenero) {
        this.miGenero = miGenero;
    }

    public DTPorDefecto(DTGenero miGenero, String nombreLista, String fotoLista) {
        super(nombreLista, fotoLista);
        this.miGenero = miGenero;
    }

    public DTGenero getMiGenero() {
        return miGenero;
    }

    public void setMiGenero(DTGenero miGenero) {
        this.miGenero = miGenero;
    }
    
}
