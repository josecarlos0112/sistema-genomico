package analisis_genomico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalisisGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;

    public AnalisisGUI() {
        frame = new JFrame("Análisis Genómico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputField.getPreferredSize().height));
        panel.add(inputField);

        JButton countButton = new JButton("Contar genes");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConteoGenes conteoGenes = new ConteoGenes();
                String secuenciaADN = inputField.getText();
                int count = conteoGenes.contarGenes(secuenciaADN);
                resultArea.append("Conteo de genes: " + count + "\n");
            }
        });
        panel.add(countButton);

        JButton combButton = new JButton("Calcular combinaciones genéticas");
        combButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                if (!inputText.isEmpty()) {
                    CombinacionesGeneticas combinacionesGeneticas = new CombinacionesGeneticas();
                    int n = Integer.parseInt(inputText);
                    int result = combinacionesGeneticas.calcularCombinaciones(n);
                    resultArea.append("Combinaciones genéticas: " + result + "\n");
                } else {
                    resultArea.append("Por favor, ingrese un número.\n");
                }
            }
        });
        panel.add(combButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AnalisisGUI gui = new AnalisisGUI();
        gui.show();
    }
}