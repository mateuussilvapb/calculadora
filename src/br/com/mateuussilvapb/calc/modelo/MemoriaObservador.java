package br.com.mateuussilvapb.calc.modelo;

@FunctionalInterface
public interface MemoriaObservador {

    public void valorAlterado(String novoValor);
}
