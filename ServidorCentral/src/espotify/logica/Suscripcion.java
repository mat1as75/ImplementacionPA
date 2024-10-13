/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Suscripcion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuscripcion;
    /* Semanal, Mensual, Anual */
    @Enumerated(EnumType.STRING)
    private TipoSuscripcion tipoSuscripcion;
    /* Pendiente, Vigente, Cancelada, Vencida */
    @Enumerated(EnumType.STRING)
    private EstadoSuscripcion estadoSuscripcion;
    @Temporal(TemporalType.DATE)
    private Date fechaSuscripcion;
    @OneToOne
    @JoinTable(name="Cliente_Suscripcion")
    private Cliente miCliente;

    public Suscripcion() {
    }

    public Suscripcion(Long idSuscripcion, TipoSuscripcion tipoSuscripcion, 
            EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion, Cliente miCliente) {
        this.idSuscripcion = idSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.estadoSuscripcion = estadoSuscripcion;
        this.fechaSuscripcion = fechaSuscripcion;
        this.miCliente = miCliente;
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcion tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }
    
    public EstadoSuscripcion getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(EstadoSuscripcion estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public Cliente getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(Cliente miCliente) {
        this.miCliente = miCliente;
    }
        
    public enum TipoSuscripcion {
        Semanal, 
        Mensual, 
        Anual
    }
    
    public enum EstadoSuscripcion {
        Pendiente, 
        Vigente, 
        Cancelada, 
        Vencida
    }
    
    /* Actualizar EstadoSuscripcion basado en la fecha actual */
    public void actualizarEstado() {
        
        if (estadoSuscripcion == EstadoSuscripcion.Vigente) {
            Date fechaActual = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaSuscripcion);
            
            switch (tipoSuscripcion) {
                case Semanal -> calendar.add(Calendar.WEEK_OF_YEAR, 1);
                case Mensual -> calendar.add(Calendar.MONTH, 1);
                case Anual -> calendar.add(Calendar.YEAR, 1);
            }
            
            /* Si la fecha actual es mayor que la fecha de 
            vencimiento calculada, la Suscripcion pasa a "VencidSuscripciona" */
            if (fechaActual.after(calendar.getTime())) {
                estadoSuscripcion = EstadoSuscripcion.Vencida;
            }
        }
    }
}
