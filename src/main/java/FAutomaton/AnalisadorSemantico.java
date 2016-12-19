package FAutomaton;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;

class AnalisadorSemantico extends FAutomatonBaseVisitor<String> {
    private String          erros       = "";
    private String          warnings    = "";
    private AutomatoInfo    ai          = new AutomatoInfo();

    Par<String, String> analisaArquivo(String fileLocation) throws Exception {
        ANTLRInputStream  input  = new ANTLRFileStream(fileLocation);
        FAutomatonLexer   lexer  = new FAutomatonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FAutomatonParser  parser = new FAutomatonParser(tokens);

        visit(parser.automato());

        return new Par(erros, warnings);
    }

    @Override
    public String visitListaSimbolos(FAutomatonParser.ListaSimbolosContext ctx) {
        // Esse loop itera sobre todos os simbolos da lista
        int i = 0;
        if (ctx.SIMBOLO(i) == null) { // Se não existe nenhum símbolo
            warnings += "Alfabeto vazio. A unica string aceita sera a string vazia.\n";
        }
        else {
            while (ctx.SIMBOLO(i) != null) {
                String simbolo = ctx.SIMBOLO(i).getText();
                if(ai.existeSimbolo(simbolo))
                    warnings += "Simbolo " + simbolo + " ja utilizado anteriormente.\n";
                else
                    ai.insereSimbolo(simbolo);
                ++i;
            }
        }


        return super.visitListaSimbolos(ctx);
    }

    //TODO gerar um warning em listaEstados para lista de estados vazia e determinar o estado inicial

    @Override
    public String visitEstadoSimples(FAutomatonParser.EstadoSimplesContext ctx) {
        String estado = ctx.NOME().getText();
        if(ai.existeEstado(estado))
            warnings += "Estado " + estado + " ja existente. Sera ignorado.\n";
        else
            ai.insereEstado(estado);

        return super.visitEstadoSimples(ctx);
    }

    @Override
    public String visitEstadoFinal(FAutomatonParser.EstadoFinalContext ctx) {
        String estado = ctx.NOME().getText();
        if(ai.existeEstado(estado))
            warnings += "Estado " + estado + " ja existente. Sera ignorado.\n";
        else
            ai.insereEstadoFinal(estado);

        return super.visitEstadoFinal(ctx);
    }
}
