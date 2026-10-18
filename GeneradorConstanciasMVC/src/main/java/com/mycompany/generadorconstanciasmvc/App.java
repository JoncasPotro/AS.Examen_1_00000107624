/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.generadorconstanciasmvc;

import controlador.AlumnoController;
import modelo.AlumnoDAO;
import vista.VentanaConstancias;

import javax.swing.*;

/**
 *
 * @author Jonatan Castelo
 */
public class App {

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                System.err.println(
                    "No se pudo aplicar el estilo del sistema."
                );
            }

            VentanaConstancias vista = new VentanaConstancias();

            AlumnoDAO dao = new AlumnoDAO();

            new AlumnoController(vista, dao);

            vista.setVisible(true);
        });
    }
}
