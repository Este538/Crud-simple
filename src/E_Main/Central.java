/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E_Main;

import B_controlador.ControladorPersona;
import C_vista.VistaPrincipal;

/**
 *
 * @author Esteban Alfonso Pacheco Serralta
 * Fecha: 18/02/2023
 */
public class Central {
    public static void main(String[] args) {
        VistaPrincipal vista = new VistaPrincipal();
        ControladorPersona controlador = new ControladorPersona(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}
