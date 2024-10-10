/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package espotify.persistencia.exceptions;

/**
 *
 * @author tecnologo
 */
@SuppressWarnings("serial")
public class UsuarioRepetidoException extends Exception{

    public UsuarioRepetidoException(String msg) {
        super(msg);
    }
    
}
