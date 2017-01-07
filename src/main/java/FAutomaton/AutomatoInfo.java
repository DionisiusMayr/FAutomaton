package FAutomaton;

import java.util.*;

class AutomatoInfo {
    private Set<String> alfabeto;
    private Set<String> estadosNaoFinais;
    private Set<String> estadosFinais;
    private String      estadoInicial;
    private String      nomeDoAutomato;
    private Map<Par<String, String>, String> transicoes;

    AutomatoInfo() {
        this("A");  // Nome default: A
    }

    AutomatoInfo(String nome) {
        alfabeto         = new HashSet<>();
        estadosNaoFinais = new HashSet<>();
        estadosFinais    = new HashSet<>();
        estadoInicial    = "";
        nomeDoAutomato   = nome;

        // Para passar nos testes, é necessário utilizar sempre a mesma ordem para as Collections, e por isso foi
            // escolhida a estrutura de TreeSet ao invés de HashSet,
        // (HashMap é uma collection unordered, então a cada execução é gerado um resultado com ordem diferente.)
        // NOTE: Isso não impacta em absolutamente nada o código C, foi utilizado apenas para facilitar o desenvolvimento.
        transicoes     = new TreeMap<>();
    }

    boolean existeSimbolo(String simbolo) {
        return alfabeto.contains(simbolo);
    }

    void insereSimbolo(String simbolo) {
        alfabeto.add(simbolo);
    }

    boolean existeEstado() {
        return !estadosNaoFinais.isEmpty() || !estadosFinais.isEmpty();
    }

    boolean existeEstado(String estado) {
        return (estadosNaoFinais.contains(estado) || estadosFinais.contains(estado));
    }

    void insereEstado(String estado) {
        estadosNaoFinais.add(estado);
    }

    void insereEstadoFinal(String estado) {
        estadosFinais.add(estado);
    }

    boolean existeEstadoFinal() {
        return !(estadosFinais.isEmpty());
    }

    String getEstadoInicial() {
        return estadoInicial;
    }

    void setEstadoInicial(String estado) {
        if(estado.endsWith("*"))
            estado = estado.substring(0, estado.length() - 1);
        estadoInicial = estado;
    }

    String getNome() {
        return nomeDoAutomato;
    }

    Set<String> getAlfabeto() {
        return alfabeto;
    }

    Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    Map<Par<String, String>, String> getTransicoes() {
        return transicoes;
    }

    void insereTransicao(String estadoOrigem, String simboloDeEntrada, String estadoDeDestino) {
        Par<String, String> p = new Par<>(estadoOrigem, simboloDeEntrada);

        transicoes.put(p, estadoDeDestino);
    }

    Set<String> getEstadosNaoFinais() {
        return estadosNaoFinais;
    }

    boolean existeTransicao() {
        return !transicoes.isEmpty();
    }

    @Override
    public String toString() {
        String s = "Alfabeto:\n";
        for(String ss : alfabeto)
            s += ss + " ";

        s += "\nEstados Nao Finais:\n";
        for(String ss : estadosNaoFinais)
            s += ss + " ";

        s += "\nEstados Finais:\n";
        for(String ss : estadosFinais)
            s += ss + " ";

        s += "\n";

        return s;
    }
}
