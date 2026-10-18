/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jonatan Castelo
 */
public class Alumno {
    private String id;
    private String nombre;
    private String carrera;
    private String periodo;
    private int semestre;
    private int materias;
    private String estatus;

    public Alumno(String id, String nombre, String carrera,
                  String periodo, int semestre,
                  int materias, String estatus) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.periodo = periodo;
        this.semestre = semestre;
        this.materias = materias;
        this.estatus = estatus;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public String getPeriodo() { return periodo; }
    public int getSemestre() { return semestre; }
    public int getMaterias() { return materias; }
    public String getEstatus() { return estatus; }
}