/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonatan Castelo
 */
public class AlumnoDAO {

    public List<Alumno> buscarPorId(String texto)
            throws SQLException {

        List<Alumno> alumnos = new ArrayList<>();

        String sql = """
            SELECT id, nombre, carrera, periodo,
                   semestre, materias, estatus
            FROM alumnos
            WHERE id LIKE ?
            ORDER BY id
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto.trim() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alumnos.add(new Alumno(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("carrera"),
                        rs.getString("periodo"),
                        rs.getInt("semestre"),
                        rs.getInt("materias"),
                        rs.getString("estatus")
                    ));
                }
            }
        }

        return alumnos;
    }
}