package FAutomaton;

import java.util.HashSet;
import java.util.Set;

class AutomatoInfo {
    private Set<String> alfabeto;
    private Set<String> estados;
    private Set<String> estadosFinais;
    private String      nomeDoAutomato;

    AutomatoInfo() {
        this("A");  // Nome default: A
    }

    AutomatoInfo(String nome) {
        alfabeto       = new HashSet<>();
        estados        = new HashSet<>();
        estadosFinais  = new HashSet<>();
        nomeDoAutomato = nome;
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

    String getNome() {
        return nomeDoAutomato;
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
