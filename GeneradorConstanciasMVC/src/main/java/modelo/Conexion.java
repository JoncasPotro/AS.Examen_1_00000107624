/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jonatan Castelo
 */
public class Conexion {

    private static final String URL =
        "jdbc:mysql://localhost:3306/control_escolar"
        + "?useSSL=false&serverTimezone=UTC";

    private static final String USUARIO = "root";

    // Sustituye por la contraseña de tu MySQL.
    private static final String PASSWORD = "1234";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
            URL, USUARIO, PASSWORD
        );
    }
}
