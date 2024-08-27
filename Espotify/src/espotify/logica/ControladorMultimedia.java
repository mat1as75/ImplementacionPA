/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

/**
 *
 * @author tecnologo
 */
public class ControladorMultimedia extends IControladorMultimedia{

    // 1 de Singleton
    private ControladorMultimedia() {
        
    }
    
    // 2 de Singleton
    private static ControladorMultimedia miInstancia = null;
    
    // 3 de Singleton
    public static ControladorMultimedia getInstance() {
        if (ControladorMultimedia.miInstancia == null) {
            ControladorMultimedia.miInstancia = new ControladorMultimedia();
        }
        return (ControladorMultimedia.getInstance());
    }
}
