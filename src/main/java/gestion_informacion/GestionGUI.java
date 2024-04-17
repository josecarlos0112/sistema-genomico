package gestion_informacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionGUI extends JFrame{
    private JFrame frame;
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

        organizacionDocumentos = new OrganizacionDocs();
        busquedaTextos = new BusquedaTextos();
        gestionFechas = new Gestionfch();

        JButton ordenarButton = new JButton("Ordenar Documento");
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        organizacionDocumentos.ordenarArchivo(selectedFile.getPath(), "sorted_" + selectedFile.getName());
                        resultArea.append("Documento ordenado correctamente.\n");
                    } catch (IOException ioException) {
                        resultArea.append("Error al ordenar el documento. Asegúrese de que el archivo existe y puede ser leído.\n");
                    }
                }
            }
        });
        panel.add(ordenarButton);

        JButton buscarButton = new JButton("Buscar Palabra");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = JOptionPane.showInputDialog(frame, "Ingrese la palabra a buscar:");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        boolean found = busquedaTextos.busquedaBinaria(palabra, selectedFile.getPath());
                        if (found) {
                            resultArea.append("Palabra encontrada en el documento.\n");
                        } else {
                            resultArea.append("Palabra no encontrada en el documento.\n");
                        }
                    } catch (IOException ioException) {
                        resultArea.append("Error al buscar la palabra. Asegúrese de que el archivo existe y puede ser leído.\n");
                    }
                }
            }
        });
        panel.add(buscarButton);

        JButton agregarFechaButton = new JButton("Agregar Fecha");
        agregarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaStr = JOptionPane.showInputDialog(frame, "Ingrese la fecha en el formato dd/MM/yyyy:");
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
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
                try {
                    resultArea.append("Fechas: " + gestionFechas.listarFechas() + "\n");
                } catch (Exception exception) {
                    resultArea.append("Error al listar las fechas.\n");
                }
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