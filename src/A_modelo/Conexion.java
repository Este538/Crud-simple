/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_modelo;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta.
 * Fecha: 18/02/2023
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection conexion;
    
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
