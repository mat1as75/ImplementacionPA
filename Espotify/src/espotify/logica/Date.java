/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

public class Date {
    
    private int dia;
    private int mes;
    private int anio;
    
    // Constructor
    public Date(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    
    // Getters Y Setters
    public void setDia(int dia) {
        this.dia = dia;
    }
    
    public int getDia() {
        return this.dia;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getMes() {
        return this.mes;
    }
    
    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public int getAnio() {
        return this.anio;
    }
    
    // Obtener datos de fecha
    public String getDateData() {
        return this.dia + "/" + this.mes + "/" + this.anio;
    }
    
}
