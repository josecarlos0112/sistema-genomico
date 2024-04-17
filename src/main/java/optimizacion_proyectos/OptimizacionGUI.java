package optimizacion_proyectos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class OptimizacionGUI {
    private JFrame frame;
    private JTextArea resultArea;
    private MejoraAlgoritmo mejoraAlgoritmo;

    public OptimizacionGUI() {
        frame = new JFrame("Optimización de Algoritmos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        mejoraAlgoritmo = new MejoraAlgoritmo();

        JButton userInputButton = new JButton("Ingresar Array");
        userInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = JOptionPane.showInputDialog(frame, "Ingrese los números del array separados por comas:");
                Integer[] array = Stream.of(userInput.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                resultArea.append("Array antes de ordenar: " + Arrays.toString(array) + "\n");
                mejoraAlgoritmo.sort(array);
                resultArea.append("Array después de ordenar: " + Arrays.toString(array) + "\n");
            }
        });
        panel.add(userInputButton);

        JButton randomArrayButton = new JButton("Generar Array Aleatorio");
        randomArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer[] array = generateRandomArray(10, 0, 100);
                resultArea.append("Array antes de ordenar: " + Arrays.toString(array) + "\n");
                mejoraAlgoritmo.sort(array);
                resultArea.append("Array después de ordenar: " + Arrays.toString(array) + "\n");
            }
        });
        panel.add(randomArrayButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        frame.setVisible(true);
    }

    private Integer[] generateRandomArray(int length, int min, int max) {
        Random random = new Random();
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = min + random.nextInt(max - min + 1);
        }
        return array;
    }

    public static void main(String[] args) {
        new OptimizacionGUI();
    }
}