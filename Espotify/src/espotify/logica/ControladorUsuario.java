/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Logica;

/**
 *
 * @author tecnologo
 */
public class ControladorUsuario extends IControladorUsuario{

    // 1 de Singleton
    private ControladorUsuario() {
        
    }
    
    // 2 de Singleton
    private static ControladorUsuario miInstancia = null;
    
    // 3 de Singleton
    public static ControladorUsuario getInstance() {
        if (ControladorUsuario.miInstancia == null) {
            ControladorUsuario.miInstancia = new ControladorUsuario();
        }
        return (ControladorUsuario.miInstancia);
    }
}
