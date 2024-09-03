package espotify.DataTypes;

public class DTParticulares extends DTListaReproduccion{
    private boolean soyrivada;
    private DTCliente micliente;
    public DTParticulares() {
    }
    public DTParticulares(boolean soyrivada, String nombreLista, String fotoLista) {
        super(nombreLista, fotoLista);
        this.soyrivada = soyrivada;
    }

    public boolean isSoyrivada() {
        return soyrivada;
    }

    public void setSoyrivada(boolean soyrivada) {
        this.soyrivada = soyrivada;
    }

    public DTCliente getMicliente() {
        return micliente;
    }

    public void setMicliente(DTCliente micliente) {
        this.micliente = micliente;
    }
    
}
