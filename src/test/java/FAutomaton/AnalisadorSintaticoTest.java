package FAutomaton;

import junit.framework.TestCase;

public class AnalisadorSintaticoTest extends TestCase {
    private AnalisadorSintatico analisadorSintatico     = new AnalisadorSintatico();

    private final String pathErrosLexicos       = "src/test/testCases/ErrosLexicos/";
    private final String pathErrosSintaticos    = "src/test/testCases/ErrosSintaticos/";
    private final String pathSemErrosSintaticos = "src/test/testCases/SemErrosSintaticos/";

    /*
        SEM ERROS
        Para os testes sem erros, espera-se que o analisador sintático retorne uma string vazia (nenhum erro)
    */

    // Teste trivial
    public void testSemErro1() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s1.dfa"), "");
    }

    // Testa o comentário multiline, contendo a definição do mesmo autômato
    public void testSemErro2() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s2.dfa"), "");
    }

    // Testa um autômato sem nenhuma transicao
    public void testSemErro3() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s3.dfa"), "");
    }

    // Testa um autômato definido em uma única linha (contendo comentários no meio)
    public void testSemErro4() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s4.dfa"), "");
    }

    // Testa um autômato definido em uma única linha, sem ter espacos (a menos do único espaço obrigatório, logo após "automato"
    public void testSemErro5() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s5.dfa"), "");
    }

    // Testa um autômato sem estados e sem transicoes
    public void testSemErro6() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s6.dfa"), "");
    }

    // Testa um autômato com estados de uma única letra
    public void testSemErro7() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s7.dfa"), "");
    }

    /*
        COM ERROS LÉXICOS
        Espera-se que o compilador retorne uma string informando a linha onde ocorre o erro e qual símbolo está causando este erro léxico.
    */

    // Símbolo inválido no símbolo do alfabeto
    public void testComErroLexico1() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosLexicos + "l1.dfa"), "3: Erro lexico: @ - simbolo nao identificado\n");
    }

    // Símbolo inválido no nome do estado
    public void testComErroLexico2() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosLexicos + "l2.dfa"), "7: Erro lexico: $ - simbolo nao identificado\n");
    }

    // Símbolo inválido no estado alvo da transição
    public void testComErroLexico3() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosLexicos + "l3.dfa"), "12: Erro lexico: ^ - simbolo nao identificado\n");
    }

    // Símbolo inválido no símbolo de entrada para a transição
    public void testComErroLexico4() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosLexicos + "l4.dfa"), "13: Erro lexico: ( - simbolo nao identificado\n");
    }

    // Múltiplos erros léxicos e uma chave faltando, acusa apenas o primeiro erro léxico e para a execução
    public void testComErroLexico5() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosLexicos + "l5.dfa"), "7: Erro lexico: $ - simbolo nao identificado\n");
    }

    /*
        COM ERROS SINTÁTICOS
        Espera-se que o compilador retorne uma string informando a linha onde ocorre o erro e
    */

    // Palavra "automato" com erro de digitação
    public void testComErroSintatico1() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s1.dfa"), "1 : Erro sintatico proximo a 'a'\n");
    }

    // Autômato sem nome
    public void testComErroSintatico2() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s2.dfa"), "1 : Erro sintatico proximo a '{'\n");
    }

    // Autômato sem chave depois do nome
    public void testComErroSintatico3() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s3.dfa"), "2 : Erro sintatico proximo a 'alfabeto'\n");
    }

    // Palavra "alfabeto" com erro de digitação
    public void testComErroSintatico4() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s4.dfa"), "2 : Erro sintatico proximo a 'a'\n");
    }

    // Autômato sem chave depois do alfabeto
    public void testComErroSintatico5() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s5.dfa"), "3 : Erro sintatico proximo a '0'\n");
    }

    // Autômato sem definição de alfabeto
    public void testComErroSintatico6() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s6.dfa"), "4 : Erro sintatico proximo a 'estados'\n");
    }

    // Transição sem definição
    public void testComErroSintatico7() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s7.dfa"), "12 : Erro sintatico proximo a '}'\n");
    }

    // Autômato sem alfabeto
    public void testComErroSintatico8() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s8.dfa"), "4 : Erro sintatico proximo a 'estados'\n");
    }

    // Transição sem alfabeto, estados ou transições
    public void testComErroSintatico9() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s9.dfa"), "3 : Erro sintatico proximo a '}'\n");
    }

    // Transição incompleta
    public void testComErroSintatico10() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s10.dfa"), "13 : Erro sintatico proximo a '}'\n");
    }

    // Autômato sem chave final (})
    public void testComErroSintatico11() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s11.dfa"), "21 : Erro sintatico proximo a 'EOF'\n");
    }

    // Alfabeto definido sem todas as vírgulas
    public void testComErroSintatico12() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s12.dfa"), "3 : Erro sintatico proximo a 'c'\n");
    }

    // Transição sem símbolo para transição
    public void testComErroSintatico13() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s13.dfa"), "12 : Erro sintatico proximo a '->'\n");
    }

    // Transição sem estado posterior
    public void testComErroSintatico14() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s14.dfa"), "12 : Erro sintatico proximo a ','\n");
    }

    // Separação de estados sem vírgula
    public void testComErroSintatico15() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s15.dfa"), "7 : Erro sintatico proximo a 's'\n");
    }

    // Separação de transições sem vírgula
    public void testComErroSintatico16() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s16.dfa"), "13 : Erro sintatico proximo a '1'\n");
    }

    // Vírgula a mais na aba de "transições"
    public void testComErroSintatico17() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s17.dfa"), "14 : Erro sintatico proximo a ','\n");
    }

    // Vírgula a mais em uma transição
    public void testComErroSintatico18() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s18.dfa"), "19 : Erro sintatico proximo a '}'\n");
    }
}
