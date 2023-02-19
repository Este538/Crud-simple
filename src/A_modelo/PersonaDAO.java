/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_modelo;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * Fecha:18/02/2023
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PersonaDAO {
    private final Conexion conectar = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public int actualizar(Persona person){
        String sql = "update persona set Nombre=?, Correo=?, Telefono=? where Id=?";
        int result = 0;
        try{
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, person.getNombre());
            ps.setString(2, person.getCorreo());
            ps.setString(3, person.getTelefono());
            ps.setInt(4, person.getID());
            result = ps.executeUpdate();
            if(result == 1){
                return 1;
            }else{
                return 0;
            }
        }catch(Exception evento){
            System.out.println("No se actualiza");
        }
        return 1;
    }
    
    public int agregar(Persona person){
        String sql = "insert into persona(Nombre, Correo, Telefono) values(?, ?, ?)";
        try{
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, person.getNombre());
            ps.setString(2, person.getCorreo());
            ps.setString(3, person.getTelefono());
            ps.executeUpdate();
        }catch(Exception evento){
            System.out.println("No se pudo agregar");
        }  
        return 1;
    }
    
    public void eliminar(int id){
        String sql = "delete from persona where Id=" +id;
        try{
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception event){
            System.out.println("No se pudo eliminar");
        }
        
    }
    
    public List listar(){
        List<Persona>lista_persona = new ArrayList<>();
        String sql = "select * from persona";
        try{
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Persona persona = new Persona();
                
                persona.setID(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setCorreo(rs.getString(3));
                persona.setTelefono(rs.getString(4));
                lista_persona.add(persona);
      
            }
        }catch(Exception evento){
            System.out.println("No se realizó lista");
        }
        
        return lista_persona;
    }
}
