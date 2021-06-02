package br.com.mateuussilvapb.calc.modelo;

public class Memoria {

    private static final Memoria INSTANCIA = new Memoria();
    private String textoAtual = "";

    private Memoria() {

    }

    public static Memoria getINSTANCIA() {
        return INSTANCIA;
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

}
