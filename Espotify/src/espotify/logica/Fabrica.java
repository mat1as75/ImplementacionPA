
package espotify.logica;

import espotify.logica.IControlador;

public  class Fabrica {

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
        if(ICtrl==null)
        {
            return Controlador.getInstance();
        }
        return ICtrl;
}   
    
}
