/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Alumno;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Jonatan Castelo
 */
public class VentanaConstancias extends JFrame {

    private final JTextField txtId = new JTextField();
    private final DefaultListModel<Alumno> listaModelo =
            new DefaultListModel<>();
    private final JList<Alumno> lista = new JList<>(listaModelo);

    private final JLabel lblResultados =
            new JLabel("0 resultados");
    private final JLabel lblNombre = new JLabel("-");
    private final JLabel lblId = new JLabel("-");
    private final JLabel lblCarrera = new JLabel("-");
    private final JLabel lblPeriodo = new JLabel("-");
    private final JLabel lblSemestre = new JLabel("-");
    private final JLabel lblMaterias = new JLabel("-");
    private final JLabel lblEstatus = new JLabel("-");

    private final JButton btnGenerar =
            new JButton("Generar Constancia");

    private final Color azul = new Color(28, 83, 112);
    private final Color fondo = new Color(237, 241, 245);

    public VentanaConstancias() {
        setTitle("Control Escolar - Constancias");
        setSize(1050, 700);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout(0, 0));

        JLabel encabezado = new JLabel(
                "  Control Escolar  ›  Constancias  ›  Alumno inscrito");
        encabezado.setOpaque(true);
        encabezado.setBackground(azul);
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(new Font("SansSerif", Font.BOLD, 14));
        encabezado.setBorder(new EmptyBorder(14, 12, 14, 12));
        add(encabezado, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(15, 0));
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(new EmptyBorder(18, 18, 18, 18));
        add(contenido, BorderLayout.CENTER);

        JPanel izquierda = new JPanel();
        izquierda.setBackground(new Color(245, 248, 250));
        izquierda.setPreferredSize(new Dimension(260, 0));
        izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
        izquierda.setBorder(new EmptyBorder(8, 8, 8, 8));

        JLabel lblBusqueda = new JLabel("ID del alumno");
        lblBusqueda.setFont(new Font("SansSerif", Font.BOLD, 14));

        txtId.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42));
        txtId.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel ayuda = new JLabel(
                "<html>Escribe el ID completo o solo una parte."
                + "<br>Los resultados se actualizan con cada carácter.</html>");
        ayuda.setForeground(new Color(76, 105, 137));

        izquierda.add(lblBusqueda);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(txtId);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(ayuda);

        contenido.add(izquierda, BorderLayout.WEST);

        JPanel derecha = new JPanel(new BorderLayout(0, 12));
        derecha.setBackground(Color.WHITE);

        JPanel tituloLista = new JPanel(new BorderLayout());
        tituloLista.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("ALUMNOS COINCIDENTES");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        titulo.setForeground(azul);

        tituloLista.add(titulo, BorderLayout.WEST);
        tituloLista.add(lblResultados, BorderLayout.EAST);

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lista.setFixedCellHeight(42);
        lista.setCellRenderer((list, alumno, index, seleccionado, foco) -> {
            JPanel fila = new JPanel(new BorderLayout(12, 0));
            fila.setBorder(new EmptyBorder(8, 10, 8, 10));
            fila.setBackground(seleccionado
                    ? new Color(225, 237, 246) : Color.WHITE);

            JLabel id = new JLabel(alumno.getId());
            id.setPreferredSize(new Dimension(75, 22));
            id.setFont(new Font("SansSerif", Font.BOLD, 13));

            JLabel nombre = new JLabel(alumno.getNombre());
            JLabel carrera = new JLabel(alumno.getCarrera());
            carrera.setForeground(new Color(76, 105, 137));

            fila.add(id, BorderLayout.WEST);
            fila.add(nombre, BorderLayout.CENTER);
            fila.add(carrera, BorderLayout.EAST);

            fila.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    seleccionado ? azul : new Color(210, 220, 230)),
                new EmptyBorder(7, 9, 7, 9)
            ));
            return fila;
        });

        derecha.add(tituloLista, BorderLayout.NORTH);
        derecha.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel confirmacion = new JPanel(new GridLayout(0, 2, 12, 10));
        confirmacion.setBackground(new Color(245, 248, 250));
        confirmacion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "DATOS DE CONFIRMACIÓN"),
                new EmptyBorder(10, 10, 10, 10)));

        agregarDato(confirmacion, "Nombre", lblNombre);
        agregarDato(confirmacion, "ID", lblId);
        agregarDato(confirmacion, "Carrera", lblCarrera);
        agregarDato(confirmacion, "Periodo", lblPeriodo);
        agregarDato(confirmacion, "Semestre inscrito", lblSemestre);
        agregarDato(confirmacion, "Cantidad de materias", lblMaterias);
        agregarDato(confirmacion, "Estatus", lblEstatus);

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBackground(Color.WHITE);
        inferior.add(confirmacion, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.setBackground(Color.WHITE);
        btnGenerar.setBackground(azul);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGenerar.setEnabled(false);
        acciones.add(btnGenerar);
        inferior.add(acciones, BorderLayout.SOUTH);

        derecha.add(inferior, BorderLayout.SOUTH);
        contenido.add(derecha, BorderLayout.CENTER);
    }

    private void agregarDato(JPanel panel, String titulo, JLabel valor) {
        JPanel dato = new JPanel();
        dato.setBackground(new Color(245, 248, 250));
        dato.setLayout(new BoxLayout(dato, BoxLayout.Y_AXIS));

        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setForeground(new Color(76, 105, 137));
        valor.setFont(new Font("SansSerif", Font.BOLD, 13));

        dato.add(etiqueta);
        dato.add(Box.createVerticalStrut(5));
        dato.add(valor);
        panel.add(dato);
    }

    public String getTextoId() {
        return txtId.getText();
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JList<Alumno> getLista() {
        return lista;
    }

    public JButton getBtnGenerar() {
        return btnGenerar;
    }

    public void mostrarAlumnos(List<Alumno> alumnos) {
        listaModelo.clear();

        for (Alumno alumno : alumnos) {
            listaModelo.addElement(alumno);
        }

        lblResultados.setText(alumnos.size() + " resultados");
    }

    public void mostrarDetalle(Alumno alumno) {
        if (alumno == null) {
            lblNombre.setText("-");
            lblId.setText("-");
            lblCarrera.setText("-");
            lblPeriodo.setText("-");
            lblSemestre.setText("-");
            lblMaterias.setText("-");
            lblEstatus.setText("-");
            btnGenerar.setEnabled(false);
            return;
        }

        lblNombre.setText(alumno.getNombre());
        lblId.setText(alumno.getId());
        lblCarrera.setText(alumno.getCarrera());
        lblPeriodo.setText(alumno.getPeriodo());
        lblSemestre.setText(alumno.getSemestre() + ".° semestre");
        lblMaterias.setText(alumno.getMaterias() + " materias");
        lblEstatus.setText(alumno.getEstatus());
        btnGenerar.setEnabled(
                "Inscrito".equalsIgnoreCase(alumno.getEstatus()));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
                this, mensaje, "Control Escolar",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
