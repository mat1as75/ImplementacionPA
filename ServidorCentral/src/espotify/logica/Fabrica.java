package espotify.logica;

public class Fabrica {

    private static Fabrica instancia;
    private static IControlador ICtrl=null;
    
    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControlador getControlador() {
        if(ICtrl==null) {
            ICtrl = Controlador.getInstance();
        }
        return ICtrl;
    }       
}
