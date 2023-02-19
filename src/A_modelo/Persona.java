/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_modelo;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * Fecha: 18/02/2023
 */
public class Persona {
    private int ID;
    private String nombre;
    private String correo;
    private String telefono;
    
    /**
     * Declaración del constructor de manera manual
     * 
     */
    public Persona(){}

    public Persona(int ID, String nombre, String correo, String telefono) {
        this.ID = ID;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    /**
     * Declaración de los get y set
     */
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
