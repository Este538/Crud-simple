/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_modelo;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta.
 * @since 18/02/2023
 * @version 1.0
 * Descripción general. Realiza una conexión a una base de datos local.
 *  
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection conexion;
    
    /**
     * La clase realiza una llamada a la base de datos para
     * iniciar la conexión, para ello el método debe enviar
     * los valores de (url, user, password) para obtener
     * acceso.
     * 
     * @return conexion
     */
    
    public Connection getConexion(){
        String url = "jdbc:mysql://localhost:3306/bd_simple_crud";
        String user = "root";
        String password = "";
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion = DriverManager.getConnection(url,user, password);
        }catch(Exception event){
            System.out.println("Connection fail");
        }
        return conexion;
    }
}
