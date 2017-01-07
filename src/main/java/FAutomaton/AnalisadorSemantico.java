package FAutomaton;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;

class AnalisadorSemantico extends FAutomatonBaseVisitor<String> {
    private String          erros       = "";
    private String          warnings    = "";
    private AutomatoInfo    ai;

    // TODO
    // Seta o AutomatoInfo
    Par<String, String> analisaArquivo(String fileLocation) throws Exception {
        ANTLRInputStream  input  = new ANTLRFileStream(fileLocation);
        FAutomatonLexer   lexer  = new FAutomatonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FAutomatonParser  parser = new FAutomatonParser(tokens);

        visit(parser.automato());

        // TODO remover essas duas linhas, somente debug
        System.out.println("Warnings:\n" + warnings);
        System.out.println("Erros:\n" + erros);

        return new Par(erros, warnings);
    }

    AutomatoInfo getAI() {
        return ai;
    }

    /*
    getLine:    retorna a linha do contexto atual
    */
    private <T extends ParserRuleContext> int getLine(T ctx) {
        return ctx.getStart().getLine();
    }

    @Override
    public String visitAutomato(FAutomatonParser.AutomatoContext ctx) {
        String nome = ctx.NOME().getText();
        ai = new AutomatoInfo(nome);            // Cria uma estrutura para armazenar as informações do autômato,
                                                // como seus estados e transições.

        return super.visitAutomato(ctx);
    }

    @Override
    public String visitListaSimbolos(FAutomatonParser.ListaSimbolosContext ctx) {
        // Esse loop itera sobre todos os simbolos da lista
        int i = 0;
        if (ctx.SIMBOLO(i) == null) { // Se não existe nenhum símbolo
            warnings += getLine(ctx) + ": Alfabeto vazio. A unica string aceita sera a string vazia.\n";
        }
        else {
            while (ctx.SIMBOLO(i) != null) {
                String simbolo = ctx.SIMBOLO(i).getText();
                if(ai.existeSimbolo(simbolo))
                    warnings += getLine(ctx) + ": Simbolo " + simbolo + " ja adicionado anteriormente.\n";
                else
                    ai.insereSimbolo(simbolo);
                ++i;
            }
        }


        return super.visitListaSimbolos(ctx);
    }

    @Override
    public String visitEstadoSimples(FAutomatonParser.EstadoSimplesContext ctx) {
        String estado = ctx.NOME().getText();
        if(ai.existeEstado(estado))
            warnings += getLine(ctx) + ": Estado " + estado + " ja existente. Sera ignorado.\n";
        else
            ai.insereEstado(estado);

        return super.visitEstadoSimples(ctx);
    }

    @Override
    public String visitEstadoFinal(FAutomatonParser.EstadoFinalContext ctx) {
        String estado = ctx.NOME().getText();
        if(ai.existeEstado(estado))
            warnings += getLine(ctx) + ": Estado " + estado + " ja existente. Sera ignorado.\n";
        else
            ai.insereEstadoFinal(estado);

        return super.visitEstadoFinal(ctx);
    }

    //TODO gerar um warning em listaEstados para lista de estados vazia e determinar o estado inicial

    @Override
    public String visitListaEstados(FAutomatonParser.ListaEstadosContext ctx) {
        if(ctx.getText().equals("estados{}"))
            warnings += getLine(ctx) + ": \'" + ai.getNome() + "\' com conjunto de estados vazio.\n";
        else {
            ai.setEstadoInicial(ctx.estadoInicial.getText());
        }



        String s = super.visitListaEstados(ctx);

        if(!ai.existeEstadoFinal() && ai.existeEstado())
            warnings += getLine(ctx) + ": \'" + ai.getNome() + "\' sem estados finais.\n"; //TODO melhorar esse warning

        return s;
    }

    //TODO verificar como fica quando o alfabeto vem depois das transicoes
    @Override
    public String visitTransicaoParcial(FAutomatonParser.TransicaoParcialContext ctx) {
        String s = ctx.SIMBOLO().getText();
        String e = ctx.NOME().getText();

        if(!ai.existeSimbolo(s))
            erros += getLine(ctx) + ": Simbolo '" + s + "' nao pertence ao alfabeto.\n";

        if(!ai.existeEstado(e))
            erros += getLine(ctx) + ": Estado '" + e + "' nao pertence ao conjunto de estados.\n";

        return super.visitTransicaoParcial(ctx);
    }

    @Override
    public String visitTransicao(FAutomatonParser.TransicaoContext ctx) {
        String estadoOrigem = ctx.NOME().getText();

        // Para cada transicao partindo deste estado de origem:
        for(int i = 0; ctx.transicaoParcial(i) != null; ++i) {
            String simboloDeEntrada = ctx.transicaoParcial(i).SIMBOLO().getText();
            String estadoDeDestino  = ctx.transicaoParcial(i).NOME().getText();

            ai.insereTransicao(estadoOrigem, simboloDeEntrada, estadoDeDestino);
        }

        return super.visitTransicao(ctx);
    }

    @Override
    public String visitListaTransicoes(FAutomatonParser.ListaTransicoesContext ctx) {
        String s = ctx.getText();

        if(s.equals("transicoes{}"))
            warnings += getLine(ctx) + ": \'" + ai.getNome() + "\' sem transicoes.\n";

        return super.visitListaTransicoes(ctx);
    }
}
