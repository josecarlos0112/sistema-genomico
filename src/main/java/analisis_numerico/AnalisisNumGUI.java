package analisis_numerico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisisNumGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;

    public AnalisisNumGUI() {
        frame = new JFrame("Análisis Numérico");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        String iconPath = "src/resources/uax-corto.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame.setIconImage(icon.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frame.setLocation(width/2-500, height/2-300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        inputField = new JTextField("Ingrese números separados por comas...");
        inputField.setForeground(Color.GRAY);
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputField.getPreferredSize().height));
        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Ingrese números separados por comas...")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Ingrese números separados por comas...");
                }
            }
        });
        panel.add(inputField);

        JButton sumButton = new JButton("Calcular Sumatoria");
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().isEmpty() && !inputField.getText().equals("Ingrese números separados por comas...")) {
                    List<Integer> list = Arrays.stream(inputField.getText().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    int sum = list.stream().mapToInt(n -> SumatoriaNumeros.sumatoria(n)).sum();
                    resultArea.append("Sumatoria de cada número en la lista es " + sum + "\n");
                } else {
                    resultArea.append("Por favor, ingrese números.\n");
                }
            }
        });
        panel.add(sumButton);

        JButton listButton = new JButton("Listar Números");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().isEmpty() && !inputField.getText().equals("Ingrese números separados por comas...")) {
                    String[] numbers = inputField.getText().split(",");
                    if (numbers.length > 1) {
                        List<Integer> list = Arrays.stream(numbers)
                                .map(String::trim)
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        resultArea.append("Lista de números ingresados: " + list + "\n");
                    } else {
                        int n = Integer.parseInt(numbers[0].trim());
                        List<Integer> list = ListadoNumeros.listarNumeros(1, n);
                        resultArea.append("Números del 1 al " + n + ": " + list + "\n");
                    }
                } else {
                    resultArea.append("Por favor, ingrese un número o una lista de números.\n");
                }
            }
        });
        panel.add(listButton);

        JButton maxButton = new JButton("Calcular Máximo");
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().isEmpty() && !inputField.getText().equals("Ingrese números separados por comas...")) {
                    List<Integer> list = Arrays.stream(inputField.getText().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    int max = MaximoNumeros.maximo(list);
                    resultArea.append("Máximo de los números ingresados es " + max + "\n");
                } else {
                    resultArea.append("Por favor, ingrese números.\n");
                }
            }
        });
        panel.add(maxButton);

        JButton powButton = new JButton("Calcular Potencia");
        powButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().isEmpty() && !inputField.getText().equals("Ingrese números separados por comas...")) {
                    List<Integer> list = Arrays.stream(inputField.getText().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    List<Integer> powList = list.stream()
                            .map(n -> CalculoPotencias.potencia(n, 2))
                            .collect(Collectors.toList());
                    resultArea.append("Potencias de los números ingresados: " + powList + "\n");
                } else {
                    resultArea.append("Por favor, ingrese números.\n");
                }
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