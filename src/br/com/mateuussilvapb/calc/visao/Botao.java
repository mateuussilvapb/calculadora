package br.com.mateuussilvapb.calc.visao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Botao extends JButton {

    public Botao(String label, Color cor) {
        setText(label);
        setOpaque(true);
        setBackground(cor);
        setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
