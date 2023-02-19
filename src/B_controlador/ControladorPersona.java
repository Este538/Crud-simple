/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B_controlador;

import A_modelo.Persona;
import A_modelo.PersonaDAO;
import C_vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * Fecha: 18/02/2023
 */
public class ControladorPersona implements ActionListener{
    private PersonaDAO datosPersona = new PersonaDAO();
    private Persona persona = new Persona();
    private VistaPrincipal ventanaCentral = new VistaPrincipal();
    private DefaultTableModel tablaPersona = new DefaultTableModel();
    
    public ControladorPersona(VistaPrincipal ventanaPrincipal){
        this.ventanaCentral = ventanaPrincipal;
        this.ventanaCentral.getBtnListar().addActionListener(this);
        this.ventanaCentral.getBtnGuardar().addActionListener(this);
        this.ventanaCentral.getBtnEditar().addActionListener(this);
        this.ventanaCentral.getBtnAceptar().addActionListener(this);
        this.ventanaCentral.getBtnBorrar().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
       
       if(evento.getSource() == ventanaCentral.getBtnListar()){
           listar(ventanaCentral.getTablaCrud());
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
       } 
       
       if(evento.getSource() == ventanaCentral.getBtnGuardar()){
           agregar();
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
       }
       
       if(evento.getSource() == ventanaCentral.getBtnEditar()){
           int fila = ventanaCentral.getTablaCrud().getSelectedRow();
           if(fila == -1){
               JOptionPane.showMessageDialog(ventanaCentral, "Debe seleccionar una fila");
           }else{
               int id = Integer.parseInt((String)ventanaCentral.getTablaCrud().getValueAt(fila, 0).toString());
               String nombre = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 1);
               String correo = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 2);
               String telefono = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 3);
               
               ventanaCentral.getTxtID().setText("" + id);
               ventanaCentral.getTxtNombre().setText(nombre);
               ventanaCentral.getTxtCorreo().setText(correo);
               ventanaCentral.getTxtTelefono().setText(telefono);
           }
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
       }
       
       if(evento.getSource()== ventanaCentral.getBtnAceptar()){
           actualizar();
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
       }
       
       if(evento.getSource() == ventanaCentral.getBtnBorrar()){
           int fila = ventanaCentral.getTablaCrud().getSelectedRow();
           int id = Integer.parseInt((String)ventanaCentral.getTablaCrud().getValueAt(fila, 0).toString());
           if(fila == - 1){
               JOptionPane.showMessageDialog(ventanaCentral, "Debe seleccionar un usuario");
           }else{
               datosPersona.eliminar(id);
               JOptionPane.showMessageDialog(ventanaCentral, "Usuario eliminado");

           }
           actualizar();
           limpiarTabla();
       }
       
    }
    
    
    
    private final int numeroColumnas = 4;
    
    public void actualizar(){
        int id = Integer.parseInt(ventanaCentral.getTxtID().getText());
        String nombre = ventanaCentral.getTxtNombre().getText();
        String correo = ventanaCentral.getTxtCorreo().getText();
        String tel = ventanaCentral.getTxtTelefono().getText();
        persona.setID(id);
        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setTelefono(tel);
        int result = datosPersona.actualizar(persona);
        if(result == 1){
            JOptionPane.showMessageDialog(ventanaCentral, "Usuario actualizado");
        }else{
            JOptionPane.showMessageDialog(ventanaCentral, "Error");
        }
    }
    public void agregar(){
        String nombre = ventanaCentral.getTxtNombre().getText();
        String correo = ventanaCentral.getTxtCorreo().getText();
        String tel = ventanaCentral.getTxtTelefono().getText();
        
        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setTelefono(tel);
        
        int result = datosPersona.agregar(persona);
        
        if(result == 1){
            JOptionPane.showMessageDialog(ventanaCentral, "Ha sido agregado");
        } else {
            JOptionPane.showMessageDialog(ventanaCentral, "Error en registro");
        }
    }
    
    public void listar(JTable tabla){
        tablaPersona = (DefaultTableModel)tabla.getModel();
        List<Persona>listaPersona = datosPersona.listar();
        Object[]objetoPersona = new Object[numeroColumnas];
        
        for(int i = 0; i < listaPersona.size(); i++){
            objetoPersona[0] = listaPersona.get(i).getID();
            objetoPersona[1] = listaPersona.get(i).getNombre();
            objetoPersona[2] = listaPersona.get(i).getCorreo();
            objetoPersona[3] = listaPersona.get(i).getTelefono();
            
            tablaPersona.addRow(objetoPersona);
        }
        
        ventanaCentral.getTablaCrud().setModel(tablaPersona);
        
    }
    
    private void limpiarTabla(){
        for(int i= 0; i < ventanaCentral.getTablaCrud().getRowCount(); i++){
            tablaPersona.removeRow(i);
            i = i - 1;
        }
    }
    
}
