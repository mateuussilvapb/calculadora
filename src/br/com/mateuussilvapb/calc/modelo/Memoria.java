package br.com.mateuussilvapb.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static final Memoria INSTANCIA = new Memoria();
    private TipoComando ultimaOperacao = null;
    private boolean substituir = false;
    private String textoAtual = "";
    private String textoBuffer = "";
    private final List<MemoriaObservador> OBSERVADORES = new ArrayList<>();

    private String obterResultadoOperacao() {
        if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
            return textoAtual;
        }
        double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
        double resultado = 0;
        if (ultimaOperacao == TipoComando.SOMA) {
            resultado = numeroBuffer + numeroAtual;
        } else if (ultimaOperacao == TipoComando.SUBTRACAO) {
            resultado = numeroBuffer - numeroAtual;
        } else if (ultimaOperacao == TipoComando.MULTIPLICACAO) {
            resultado = numeroBuffer * numeroAtual;
        } else if (ultimaOperacao == TipoComando.DIVISAO) {
            resultado = numeroBuffer / numeroAtual;
        }
        String texto = Double.toString(resultado).replace(".", ",");
        boolean inteiro = texto.endsWith(",0");
        return inteiro ? texto.replace(",0", "") : texto;
    }

    private enum TipoComando {
        ZERAR, NUMERO, DIVISAO, MULTIPLICACAO, SUBTRACAO, SOMA, IGUAL, VIRGULA, TROCA
    }

    private Memoria() {

    }

    public static Memoria getINSTANCIA() {
        return INSTANCIA;
    }

    public void adicionarObservador(MemoriaObservador observador) {
        OBSERVADORES.add(observador);
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void processarComando(String texto) {

        TipoComando tipoComando = detectarTipoComando(texto);

        if (tipoComando == null) {
            return;
        } else if (tipoComando == TipoComando.ZERAR) {
            textoAtual = "";
            textoBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        } else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        } else if (tipoComando == TipoComando.TROCA && textoAtual.contains("-")) {
            textoAtual = textoAtual.substring(1);
        } else if (tipoComando == TipoComando.TROCA && !textoAtual.contains("-")) {
            textoAtual = "-" + textoAtual;
        } else {
            substituir = true;
            textoAtual = obterResultadoOperacao();
            textoBuffer = textoAtual;
            ultimaOperacao = tipoComando;
        }

        OBSERVADORES.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private TipoComando detectarTipoComando(String texto) {
        if (textoAtual.isEmpty() && texto.equals("0")) {
            return null;
        }
        try {
            Integer.parseInt(texto);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            //Quando não for número
            if (texto.equals("AC")) {
                return TipoComando.ZERAR;
            } else if (texto.equals("/")) {
                return TipoComando.DIVISAO;
            } else if (texto.equals(",") && !textoAtual.contains(",")) {
                return TipoComando.VIRGULA;
            } else if (texto.equals("+")) {
                return TipoComando.SOMA;
            } else if (texto.equals("-")) {
                return TipoComando.SUBTRACAO;
            } else if (texto.equals("x")) {
                return TipoComando.MULTIPLICACAO;
            } else if (texto.equals("=")) {
                return TipoComando.IGUAL;
            } else if (texto.equals("±")) {
                return TipoComando.TROCA;
            }
            return null;
        }
    }
}
