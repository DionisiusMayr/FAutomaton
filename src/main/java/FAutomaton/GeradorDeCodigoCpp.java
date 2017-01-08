package FAutomaton;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

class GeradorDeCodigoCpp extends FAutomatonBaseVisitor<String> {
    private String          codigo;
    private String          alfabeto;
    private String          estadoInicial;
    private String          estadosFinais;
    private String          transicoes;
    private AutomatoInfo    ai;

    GeradorDeCodigoCpp(AutomatoInfo a) {
        ai              = a;    // Recebe todas as informações necessárias a respeito do autômato.
        codigo          = "";
        alfabeto        = "";
        estadoInicial   = "";
        estadosFinais   = "";
        transicoes      = "";
    }

    String geraCodigoCpp(String fileLocation) throws Exception {
        ANTLRInputStream  input  = new ANTLRFileStream(fileLocation);
        FAutomatonLexer   lexer  = new FAutomatonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FAutomatonParser  parser = new FAutomatonParser(tokens);

        estadoInicial = ai.getEstadoInicial();

        for(String estadoFinal : ai.getEstadosFinais())
            estadosFinais += "\testadosFinais.insert(\"" + estadoFinal + "\");\n";

        for(Par<String, String> transicao : ai.getTransicoes().keySet())
            transicoes += "\ttransicao[make_pair(\"" + transicao.a + "\", '" + transicao.b + "')] = \"" + ai.getTransicoes().get(transicao) + "\";\n";

        codigo += "#include <iostream>\n" +
                "#include <set>\n" +
                "#include <map>\n" +
                "\n" +
                "using namespace std;\n" +
                "\n" +
                "int main() {\n" +
                "\tbool \t\trecusa = false;\n" +
                "\tset<char> \talfabeto;\n" +
                "\tset<string>\testadosFinais;\n" +
                "\tstring \t\testadoAtual;\n" +
                "\tmap<pair<string, char>, string> transicao;\n" +
                "\n" +
                "\t/* Le a fita de entrada */\n" +
                "\tstring entrada;\n" +
                "\tcin >> entrada;\n\n";

        codigo += "\t/* Simbolos do alfabeto */\n";

        visit(parser.automato());

        codigo += alfabeto + "\n";

        //estado inicial
        codigo += "\t/* Estado inicial */\n" +
                "\testadoAtual = \"" + estadoInicial + "\";\n" +
                "\n";

        //conjunto de estados finais
        codigo += "\t/* Conjunto de estados finais */\n" + estadosFinais + "\n";

        //funcao de transicao
        codigo += "\t/* Funcao de transicao */\n" + transicoes + "\n";

        //transicoes
        codigo += "\t/* Para cada simbolo da fita de entrada, realize a transicao. */\n" +
                "\tfor(const char & c : entrada) {\n" +
                "\t\tif((alfabeto.find(c) != alfabeto.cend()) || (transicao.find(make_pair(estadoAtual, c)) != transicao.cend()))\n" +
                "\t\t\t/* Funcao de transicao, toma como parametro o estado atual e o\n" +
                "\t\t\t * simbolo lido e atualiza o estado atual com o estado de destino */\n" +
                "\t\t\testadoAtual = transicao[make_pair(estadoAtual, c)];\n" +
                "\t\telse {\n" +
                "\t\t\t/* Símbolo lido nao pertencente ao alfabeto ou transicao nao definida */\n" +
                "\t\t\trecusa = true;\n" +
                "\t\t\tbreak;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\n" +
                "\tif(recusa || (estadosFinais.find(estadoAtual) == estadosFinais.cend()))\n" +
                "\t\tcout << \"Rejeita: A cadeia \" << entrada << \" nao pertence a linguagem.\" << endl;\n" +
                "\telse\n" +
                "\t\tcout << \"Aceita: A cadeia \" << entrada << \" pertence a linguagem.\" << endl;\n";

        codigo += "\n\treturn 0;\n" +
                "}\n";

        return codigo;
    }

    @Override
    public String visitListaSimbolos(FAutomatonParser.ListaSimbolosContext ctx) {
        String[] simbolos = ctx.getText().split(",");
        for(String simbolo : simbolos) {
            alfabeto += "\talfabeto.insert('" + simbolo + "');\n";
        }

        return super.visitListaSimbolos(ctx);
    }
}
