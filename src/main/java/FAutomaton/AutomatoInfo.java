package FAutomaton;

import java.util.HashSet;
import java.util.Set;

class AutomatoInfo {
    private Set<String> alfabeto = new HashSet<>();
    private Set<String> estados  = new HashSet<>();
    private Set<String> estadosFinais = new HashSet<>();

    boolean existeSimbolo(String simbolo) {
        return alfabeto.contains(simbolo);
    }

    void insereSimbolo(String simbolo) {
        alfabeto.add(simbolo);
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
}
