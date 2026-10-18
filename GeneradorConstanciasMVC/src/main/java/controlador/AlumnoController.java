/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Alumno;
import modelo.AlumnoDAO;
import vista.VentanaConstancias;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Jonatan Castelo
 */
public class AlumnoController {

    private final VentanaConstancias vista;
    private final AlumnoDAO dao;

    public AlumnoController(VentanaConstancias vista, AlumnoDAO dao) {
        this.vista = vista;
        this.dao = dao;

        vista.getTxtId().getDocument().addDocumentListener(
            new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    buscar();
                }

                public void removeUpdate(DocumentEvent e) {
                    buscar();
                }

                public void changedUpdate(DocumentEvent e) {
                    buscar();
                }
            }
        );

        vista.getLista().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                vista.mostrarDetalle(
                    vista.getLista().getSelectedValue()
                );
            }
        });

        vista.getBtnGenerar().addActionListener(
            e -> generarConstancia()
        );
    }

    private void buscar() {
        String texto = vista.getTextoId().trim();

        if (texto.isEmpty()) {
            vista.mostrarAlumnos(List.of());
            vista.mostrarDetalle(null);
            return;
        }

        try {
            List<Alumno> alumnos = dao.buscarPorId(texto);
            vista.mostrarAlumnos(alumnos);
            vista.mostrarDetalle(null);
        } catch (SQLException ex) {
            vista.mostrarError(
                "No fue posible consultar los alumnos.\n"
                + "Verifica la conexión a MySQL."
            );
        }
    }

    private void generarConstancia() {
        Alumno alumno = vista.getLista().getSelectedValue();

        if (alumno == null) {
            vista.mostrarError("Selecciona un alumno.");
            return;
        }

        if (!"Inscrito".equalsIgnoreCase(alumno.getEstatus())) {
            vista.mostrarError(
                "El alumno no cuenta con estatus de inscrito."
            );
            return;
        }

        String fecha = LocalDate.now().format(
            DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy",
                new java.util.Locale("es", "MX"))
        );

        String html = """
            <html>
            <head>
              <meta charset="UTF-8">
              <style>
                body {
                  font-family: Georgia, serif;
                  color: #17344f;
                  margin: 25px;
                  line-height: 1.6;
                }
                h2, h3 { text-align: center; }
                hr { border: 0; border-top: 1px solid #aabbd0; }
                .fecha { margin-top: 45px; }
              </style>
            </head>
            <body>
              <h3>Instituto Tecnologico de Sonora</h3>
              <p style="text-align:center">Dirección de Control Escolar</p>
              <h2>CONSTANCIA DE INSCRIPCIÓN</h2>
              <hr>
              <p>A quien corresponda:</p>
              <p>
                Se hace constar que <b>%s</b>, con ID <b>%s</b>,
                se encuentra inscrito en el <b>%d.° semestre</b>
                de la carrera de <b>%s</b>, cursando
                <b>%d materias</b> durante el periodo
                <b>%s</b>. Se extiende la presente para los fines
                que la persona interesada juzgue convenientes.
              </p>
              <p class="fecha">%s</p>
              <br><br>
              <hr>
              <p style="text-align:center">
                [Arquitectura de Software/Jose Gamaliel Rivera Ibarra]
              </p>
            </body>
            </html>
            """.formatted(
                escaparHtml(alumno.getNombre()),
                escaparHtml(alumno.getId()),
                alumno.getSemestre(),
                escaparHtml(alumno.getCarrera()),
                alumno.getMaterias(),
                escaparHtml(alumno.getPeriodo().toLowerCase(
                    new java.util.Locale("es", "MX"))),
                fecha
            );

        JEditorPane documento = new JEditorPane("text/html", html);
        documento.setEditable(false);
        documento.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(documento);
        scroll.setPreferredSize(new java.awt.Dimension(650, 450));

        Object[] opciones = {"Imprimir", "Cerrar"};

        int opcion = JOptionPane.showOptionDialog(
            vista, scroll, "Constancia de inscripción",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null, opciones, opciones[1]
        );

        if (opcion == 0) {
            try {
                documento.print();
            } catch (java.awt.print.PrinterException ex) {
                vista.mostrarError(
                    "No se pudo imprimir la constancia: "
                    + ex.getMessage()
                );
            }
        }
    }

    private String escaparHtml(String texto) {
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
