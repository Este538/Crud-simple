/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_modelo;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * @since  18/02/2023
 * @version 1.0.
 * 
 * Descripción general. Se crea la clase persona con sus
 * atributos y métodos correspondientes para ser utilizado
 * como objeto mediador entre el PersonaDAO y la interfaz VistaPrincipal
 * junto al controlador controladorPersona.
 * 
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

    /**
     * Declaración de todos los valores de la clase.
     * @param ID
     * @param nombre
     * @param correo
     * @param telefono 
     */
    
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
    /**
     * 
     * @param ID 
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return nombre 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return correo 
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * 
     * @return telefono 
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * 
     * @param telefono 
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
