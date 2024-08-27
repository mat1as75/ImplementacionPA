/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

/**
 *
 * @author tecnologo
 */
public class Fabrica {
    
    // Constructor
    public Fabrica() {
        
    }
    
    public IControladorUsuario getControladorUsuario() {
        return (ControladorUsuario.getInstance());
    }
    
    public IControladorMultimedia getControladorMultimedia() {
        return (ControladorMultimedia.getInstance());
    }
   
}
