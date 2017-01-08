package FAutomaton;

import java.util.Map;

class GeradorDeCodigoDot {
    private String          codigo;
    private AutomatoInfo    ai;

    GeradorDeCodigoDot(AutomatoInfo a) {
        ai              = a;    // Recebe todas as informações necessárias a respeito do autômato.
        codigo          = "";
    }

    String geraCodigoDot(String fileLocation) throws Exception {
        codigo += "digraph " + ai.getNome() + " {\n";

        //código inicial
        codigo += "\t/* configuracoes de design e layout */\n" +
                "\tnode [shape = circle];\n" +
                "\trankdir = \"LR\";\n";

        if (ai.existeEstado()) {
            //estado inicial
            codigo += "\n\t/* estado inicial */\n" +
                    "\tx [style = invis];\n" +
                    "\tx -> " + ai.getEstadoInicial() + ";\n";

            //estados finais
            if (ai.existeEstadoFinal()) {
                codigo += "\n\t/* estados finais */\n";
                for (String estadoFinal : ai.getEstadosFinais())
                        codigo += "\t" + estadoFinal + " [shape = doublecircle];\n";
            }

            //outros estados
            codigo += "\n\t/* outros estados */\n";
            for (String estado : ai.getEstadosNaoFinais())
                codigo += "\t" + estado + ";\n";
        }

        //transições
        if(ai.existeTransicao()) {
            codigo += "\n\t/* transicoes */\n";
            Map<Par<String, String>, String> tran = ai.getTransicoes();

            for (Par<String, String> par : tran.keySet())
                 codigo += "\t" + par.a + " -> " + tran.get(par) + " [label = \"" + par.b + "\"];\n";
        }

        codigo += "}\n";

        return codigo;
    }
}
