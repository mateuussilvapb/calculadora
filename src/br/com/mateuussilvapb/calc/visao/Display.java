package br.com.mateuussilvapb.calc.visao;

import br.com.mateuussilvapb.calc.modelo.Memoria;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel {

    private final JLabel LABEL;

    public Display() {
        setBackground(new Color(49, 49, 49));
        LABEL = new JLabel(Memoria.getINSTANCIA().getTextoAtual());
        LABEL.setForeground(Color.WHITE);
        LABEL.setFont(new Font("Helvetica Neue", Font.PLAIN, 40));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 15));
        add(LABEL);
    }

}
