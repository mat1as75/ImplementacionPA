/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.Date;

/**
 *
 * @author tecnologo
 */
public class DTSuscripcion {
    
    private Long idSuscripcion;
    private String tipoSuscripcion;
    private String estadoSuscripcion;
    private Date fechaSuscripcion;
    private DTCliente miCliente;
    
    public DTSuscripcion(Long idSuscripcion, String tipoSuscripcion, 
            String estadoSuscripcion, Date fechaSuscripcion) {
        this.idSuscripcion = idSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.estadoSuscripcion = estadoSuscripcion;
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(String estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public DTCliente getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(DTCliente miCliente) {
        this.miCliente = miCliente;
    }
    
}
