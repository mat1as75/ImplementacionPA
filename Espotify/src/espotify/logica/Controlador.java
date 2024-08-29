/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

/**
 *
 * @author tecnologo
 */
public class Controlador extends IControlador{

    // 1 de Singleton
    private Controlador() {
        
    }
    
    // 2 de Singleton
    private static Controlador miInstancia = null;
    
    // 3 de Singleton
    public static Controlador getInstance() {
        if (Controlador.miInstancia == null) {
            Controlador.miInstancia = new Controlador();
        }
        return (Controlador.miInstancia);
    }
}
