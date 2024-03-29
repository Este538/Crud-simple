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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * @since  18/02/2023
 * @version 1.0 
 * 
 * Descripción general. Realizar las operaciones que fueron seleccionadas
 * por parte del usuario y realizar el control de las modificaciones a la 
 * base de datos.
 */
public class ControladorPersona implements ActionListener{
    private final PersonaDAO datosPersona = new PersonaDAO();
    private final Persona persona = new Persona();
    private VistaPrincipal ventanaCentral = new VistaPrincipal();
    private DefaultTableModel tablaPersona = new DefaultTableModel();
    private final int noSeleccion = -1;
    private final int agregado = 1;
    
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
           try {
               int id = Integer.parseInt((String)ventanaCentral.getTablaCrud().getValueAt(fila, 0).toString());
               String nombre = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 1);
               String correo = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 2);
               String telefono = (String)ventanaCentral.getTablaCrud().getValueAt(fila, 3);
               
               ventanaCentral.getTxtID().setText("" + id);
               ventanaCentral.getTxtNombre().setText(nombre);
               ventanaCentral.getTxtCorreo().setText(correo);
               ventanaCentral.getTxtTelefono().setText(telefono);
               
           }catch(ArrayIndexOutOfBoundsException noSeleccion){
               JOptionPane.showMessageDialog(ventanaCentral, "Debe seleccionar una fila");
           }
               
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
       }
       
       if(evento.getSource()== ventanaCentral.getBtnAceptar()){
         try{
           actualizar();
           limpiarTabla();
           listar(ventanaCentral.getTablaCrud());
         }catch(Exception event){
            System.out.println("Button failure using " + event);
        }       
       }
       
       if(evento.getSource() == ventanaCentral.getBtnBorrar()){
           int fila = ventanaCentral.getTablaCrud().getSelectedRow();
           int id = Integer.parseInt((String)ventanaCentral.getTablaCrud().getValueAt(fila, 0).toString());
           if(fila == noSeleccion){ 
               JOptionPane.showMessageDialog(ventanaCentral, "Debe seleccionar un usuario");
           }else{
               datosPersona.eliminar(id);
               JOptionPane.showMessageDialog(ventanaCentral, "Usuario eliminado");

           }
           actualizar();
           limpiarTabla();
           this.listar(ventanaCentral.getTablaCrud());
       }
       
    }
    
    
    
    private final int numeroColumnas = 4;
    
    /**
     * Actualizar los datos previamente seleccionados de la tabla.
     */
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
        if(result == agregado){
            JOptionPane.showMessageDialog(ventanaCentral, "Usuario actualizado");
        }else{
            JOptionPane.showMessageDialog(ventanaCentral, "Error");
        }
    }
    
    /**
     * Agregar datos nuevos a la tabla.
     */
    public void agregar(){
        String nombre = ventanaCentral.getTxtNombre().getText();
        String correo = ventanaCentral.getTxtCorreo().getText();
        String tel = ventanaCentral.getTxtTelefono().getText();
        
        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setTelefono(tel);
        
        if(encontrarCadenaVacia()){
            int result = datosPersona.agregar(persona);
        
            if(result == agregado){
                JOptionPane.showMessageDialog(ventanaCentral, "Ha sido agregado");
            } else {
                JOptionPane.showMessageDialog(ventanaCentral, "Error en registro");
            }
        } else{
            JOptionPane.showMessageDialog(ventanaCentral, "No se aceptan valores vacios");
        }
    }
    
    /**
     * Listar cada dato que esté presente en la tabla con los datos más
     * recientes.
     * @param tabla 
     */
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
    
    /*
        Método que limpia la tabla de acuerdo a siguiente lógica:
            1.- la tabla elimina primer elemento.
            2.- la tabla se corre lugar arriba.
            3.- para permitir que siga el incremento, se hace un reinicio en el bucle.
            4.- nunca se sale de la posición cero.
            5.- una vez que no queden elementos, termina.
    */
    private void limpiarTabla(){
        for(int i= 0; i < ventanaCentral.getTablaCrud().getRowCount(); i++){
            tablaPersona.removeRow(i);
            i = i - 1;
        }
    }
    
    private boolean encontrarCadenaVacia(){
        if(!"".equals(persona.getNombre()) || !"".equals(persona.getCorreo()) || !"".equals(persona.getTelefono())){
            return true;
        }
        return false;
    }
    
}
