package analisis_genomico;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AnalisisGUI extends JFrame{
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;

    public AnalisisGUI() {
        frame = new JFrame("Análisis Genómico");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
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

        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputField.getPreferredSize().height));
        inputField.setText("Ingrese la secuencia de ADN aquí...");
        inputField.setForeground(Color.GRAY);
        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Ingrese la secuencia de ADN aquí...")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Ingrese la secuencia de ADN aquí...");
                }
            }
        });
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