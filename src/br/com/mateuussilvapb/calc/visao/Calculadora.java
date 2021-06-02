package br.com.mateuussilvapb.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Calculadora extends JFrame {

    public Calculadora() {
        /*
        Nesse caso, tanto pode ser utilizado o "DISPOSE_ON_CLOSE" quanto o
        "EXIT_ON_CLOSE"
         */
        organizarLayout();
        setSize(232, 322);
        /*
        Para deixar a aplicação sem a barra de cima: setUndecorated(true);        
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Calculadora();
    }

    private void organizarLayout() {
        setLayout(new BorderLayout());

        Display display = new Display();
        display.setPreferredSize(new Dimension(233, 60));
        add(display, BorderLayout.NORTH);

        Teclado teclado = new Teclado();
        add(teclado, BorderLayout.CENTER);
    }
}
