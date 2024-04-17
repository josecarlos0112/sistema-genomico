package analisis_numerico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnalisisNumGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;

    public AnalisisNumGUI() {
        frame = new JFrame("Análisis Numérico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        inputField = new JTextField(10);
        panel.add(inputField);

        JButton sumButton = new JButton("Calcular Sumatoria");
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(inputField.getText());
                int sum = SumatoriaNumeros.sumatoria(n);
                resultArea.append("Sumatoria de " + n + " es " + sum + "\n");
            }
        });
        panel.add(sumButton);

        JButton listButton = new JButton("Listar Números");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(inputField.getText());
                List<Integer> list = ListadoNumeros.listarNumeros(1, n);
                resultArea.append("Números del 1 al " + n + ": " + list + "\n");
            }
        });
        panel.add(listButton);

        JButton maxButton = new JButton("Calcular Máximo");
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(inputField.getText());
                List<Integer> list = ListadoNumeros.listarNumeros(1, n);
                int max = MaximoNumeros.maximo(list);
                resultArea.append("Máximo de los números del 1 al " + n + " es " + max + "\n");
            }
        });
        panel.add(maxButton);

        JButton powButton = new JButton("Calcular Potencia");
        powButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(inputField.getText());
                int pow = CalculoPotencias.potencia(n, 2);
                resultArea.append(n + " al cuadrado es " + pow + "\n");
            }
        });
        panel.add(powButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AnalisisNumGUI();
    }
}