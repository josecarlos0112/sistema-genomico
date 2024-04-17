package gestion_informacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;
    private OrganizacionDocs organizacionDocumentos;
    private BusquedaTextos busquedaTextos;
    private Gestionfch gestionFechas;

    public GestionGUI() {
        frame = new JFrame("Gestión de Información");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputField.getPreferredSize().height));
        panel.add(inputField);

        JButton ordenarButton = new JButton("Ordenar Documento");
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    organizacionDocumentos.ordenarArchivo(inputField.getText(), "sorted_" + inputField.getText());
                    resultArea.append("Documento ordenado correctamente.\n");
                } catch (IOException ioException) {
                    resultArea.append("Error al ordenar el documento.\n");
                }
            }
        });
        panel.add(ordenarButton);

        JButton buscarButton = new JButton("Buscar Palabra");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean found = busquedaTextos.busquedaBinaria(inputField.getText(), "document.txt");
                    if (found) {
                        resultArea.append("Palabra encontrada en el documento.\n");
                    } else {
                        resultArea.append("Palabra no encontrada en el documento.\n");
                    }
                } catch (IOException ioException) {
                    resultArea.append("Error al buscar la palabra.\n");
                }
            }
        });
        panel.add(buscarButton);

        JButton agregarFechaButton = new JButton("Agregar Fecha");
        agregarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(inputField.getText());
                    gestionFechas.agregarFecha(date);
                    resultArea.append("Fecha agregada correctamente.\n");
                } catch (ParseException parseException) {
                    resultArea.append("Error al agregar la fecha. Asegúrese de que la fecha esté en el formato dd/MM/yyyy.\n");
                }
            }
        });
        panel.add(agregarFechaButton);

        JButton listarFechasButton = new JButton("Listar Fechas");
        listarFechasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.append("Fechas: " + gestionFechas.listarFechas() + "\n");
            }
        });
        panel.add(listarFechasButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GestionGUI();
    }
}