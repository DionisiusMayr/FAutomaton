package FAutomaton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AutomatoInfo {
    private Set<String> alfabeto;
    private Set<String> estados;
    private Set<String> estadosFinais;
    private String      estadoInicial;
    private String      nomeDoAutomato;
    private Map<Par<String, String>, String> transicoes;

    AutomatoInfo() {
        this("A");  // Nome default: A
    }

    AutomatoInfo(String nome) {
        alfabeto       = new HashSet<>();
        estados        = new HashSet<>();
        estadosFinais  = new HashSet<>();
        estadoInicial  = "";
        nomeDoAutomato = nome;
        transicoes     = new HashMap<>();
    }

    boolean existeSimbolo(String simbolo) {
        return alfabeto.contains(simbolo);
    }

    void insereSimbolo(String simbolo) {
        alfabeto.add(simbolo);
    }

    boolean existeEstado() {
        return !estados.isEmpty() || !estadosFinais.isEmpty();
    }

    boolean existeEstado(String estado) {
        return (estados.contains(estado) || estadosFinais.contains(estado));
    }

    void insereEstado(String estado) {
        estados.add(estado);
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

    @Override
    public String toString() {
        String s = "Alfabeto:\n";
        for(String ss : alfabeto)
            s += ss + " ";

        s += "\nEstados:\n";
        for(String ss : estados)
            s += ss + " ";

        s += "\nEstados Finais:\n";
        for(String ss : estadosFinais)
            s += ss + " ";

        s += "\n";

        return s;
    }
}
