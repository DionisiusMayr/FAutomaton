package FAutomaton;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

class AnalisadorSintatico {
    String analisaArquivo(String fileLocation) throws Exception {
        ANTLRInputStream    input  = new ANTLRFileStream(fileLocation);
        FAutomatonLexer     lexer  = new FAutomatonLexer(input);
        CommonTokenStream   tokens = new CommonTokenStream(lexer);
        FAutomatonParser    parser = new FAutomatonParser(tokens);

        StringBuilder erros = new StringBuilder();

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int col, String msg, RecognitionException e) {
                Token t = (Token) offendingSymbol;
                String erro = String.format("%d : Erro sintatico proximo a '%s'", line, !t.getText().equals("<EOF>") ? t.getText() : "EOF");

                throw new ParseCancellationException(erro + System.lineSeparator());
            }
        });

        // Tenta rodar o analisador sintático, caso haja algum erro, será inserido na variável "erros".
        try {
            parser.automato();
        } catch(ParseCancellationException pce) {
            erros.append(pce.getMessage());
        }

        // Se não houve nenhum erro.
        if(erros.length() == 0)
            return "";
        else {
            return new String(erros);
        }
    }
}
