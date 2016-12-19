package FAutomaton;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;

class AnalisadorSemantico extends FAutomatonBaseVisitor<String> {
    private String erros = "";
    private ArrayList<String> listaSimbolos = new ArrayList<>();
    private ArrayList<String> listaEstados = new ArrayList<>();

    String analisaArquivo(String fileLocation) throws Exception {
        ANTLRInputStream  input  = new ANTLRFileStream(fileLocation);
        FAutomatonLexer   lexer  = new FAutomatonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FAutomatonParser  parser = new FAutomatonParser(tokens);

        visit(parser.automato());

        return erros;
    }

    @Override
    public String visitAutomato(FAutomatonParser.AutomatoContext ctx) {
        return super.visitAutomato(ctx);
    }

    @Override
    public String visitAlfabeto(FAutomatonParser.AlfabetoContext ctx) {
        return super.visitAlfabeto(ctx);
    }

    @Override
    public String visitListaSimbolos(FAutomatonParser.ListaSimbolosContext ctx) {
        int i = 0;
        while(ctx.SIMBOLO(i) != null) {
            listaSimbolos.add(ctx.SIMBOLO(i).getText());

            ++i;
        }

        return super.visitListaSimbolos(ctx);
    }
}
