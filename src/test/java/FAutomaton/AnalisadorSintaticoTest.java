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
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s1.dfa"));
    }

    // Testa o comentário multiline, contendo a definição do mesmo autômato
    public void testSemErro2() throws Exception {
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s2.dfa"));
    }

    // Testa um autômato sem nenhuma transicao
    public void testSemErro3() throws Exception {
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s3.dfa"));
    }

    // Testa um autômato definido em uma única linha (contendo comentários no meio)
    public void testSemErro4() throws Exception {
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s4.dfa"));
    }

    // Testa um autômato definido em uma única linha, sem ter espacos (a menos do único espaço obrigatório, logo após "automato"
    public void testSemErro5() throws Exception {
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s5.dfa"));
    }

    // Testa um autômato sem estados e sem transicoes
    public void testSemErro6() throws Exception {
        assertEquals("", analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s6.dfa"));
    }

    // Testa um autômato com estados de uma única letra
    // Note: Nào é mais aceito um estado com uma única letra
//    public void testSemErro7() throws Exception {
//        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s7.dfa"), "");
//    }

    /*
        COM ERROS LÉXICOS
        Espera-se que o compilador retorne uma string informando a linha onde ocorre o erro e qual símbolo está causando este erro léxico.
    */

    // Símbolo inválido no símbolo do alfabeto
    public void testComErroLexico1() throws Exception {
        assertEquals("3: Erro lexico: @ - simbolo nao identificado\n", analisadorSintatico.analisaArquivo(pathErrosLexicos + "l1.dfa"));
    }

    // Símbolo inválido no nome do estado
    public void testComErroLexico2() throws Exception {
        assertEquals("7: Erro lexico: $ - simbolo nao identificado\n", analisadorSintatico.analisaArquivo(pathErrosLexicos + "l2.dfa"));
    }

    // Símbolo inválido no estado alvo da transição
    public void testComErroLexico3() throws Exception {
        assertEquals("12: Erro lexico: ^ - simbolo nao identificado\n", analisadorSintatico.analisaArquivo(pathErrosLexicos + "l3.dfa"));
    }

    // Símbolo inválido no símbolo de entrada para a transição
    public void testComErroLexico4() throws Exception {
        assertEquals("13: Erro lexico: ( - simbolo nao identificado\n", analisadorSintatico.analisaArquivo(pathErrosLexicos + "l4.dfa"));
    }

    // Múltiplos erros léxicos e uma chave faltando, acusa apenas o primeiro erro léxico e para a execução
    public void testComErroLexico5() throws Exception {
        assertEquals("7: Erro lexico: $ - simbolo nao identificado\n", analisadorSintatico.analisaArquivo(pathErrosLexicos + "l5.dfa"));
    }

    //TODO Nome de automato invalido

    /*
        COM ERROS SINTÁTICOS
        Espera-se que o compilador retorne uma string informando a linha onde ocorre o erro e
    */

    // Palavra "automato" com erro de digitação
    public void testComErroSintatico1() throws Exception {
        assertEquals("1 : Erro sintatico proximo a 'automat'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s1.dfa"));
    }

    // Autômato sem nome
    public void testComErroSintatico2() throws Exception {
        assertEquals("1 : Erro sintatico proximo a '{'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s2.dfa"));
    }

    // Autômato sem chave depois do nome
    public void testComErroSintatico3() throws Exception {
        assertEquals("2 : Erro sintatico proximo a 'alfabeto'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s3.dfa"));
    }

    // Palavra "alfabeto" com erro de digitação
    public void testComErroSintatico4() throws Exception {
        assertEquals("2 : Erro sintatico proximo a 'alfabeta'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s4.dfa"));
    }

    // Autômato sem chave depois do alfabeto
    public void testComErroSintatico5() throws Exception {
        assertEquals("3 : Erro sintatico proximo a '0'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s5.dfa"));
    }

    // Autômato sem definição de alfabeto
    public void testComErroSintatico6() throws Exception {
        assertEquals("4 : Erro sintatico proximo a 'estados'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s6.dfa"));
    }

    // Transição sem definição
    public void testComErroSintatico7() throws Exception {
        assertEquals("12 : Erro sintatico proximo a '}'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s7.dfa"));
    }

    // Autômato sem alfabeto
    public void testComErroSintatico8() throws Exception {
        assertEquals("4 : Erro sintatico proximo a 'estados'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s8.dfa"));
    }

    // Transição sem alfabeto, estados ou transições
    public void testComErroSintatico9() throws Exception {
        assertEquals("3 : Erro sintatico proximo a '}'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s9.dfa"));
    }

    // Transição incompleta
    public void testComErroSintatico10() throws Exception {
        assertEquals("13 : Erro sintatico proximo a '}'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s10.dfa"));
    }

    // Autômato sem chave final (})
    public void testComErroSintatico11() throws Exception {
        assertEquals("21 : Erro sintatico proximo a 'EOF'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s11.dfa"));
    }

    // Alfabeto definido sem todas as vírgulas
    public void testComErroSintatico12() throws Exception {
        assertEquals("3 : Erro sintatico proximo a 'c'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s12.dfa"));
    }

    // Transição sem símbolo para transição
    public void testComErroSintatico13() throws Exception {
        assertEquals("12 : Erro sintatico proximo a '->'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s13.dfa"));
    }

    // Transição sem estado posterior
    public void testComErroSintatico14() throws Exception {
        assertEquals("12 : Erro sintatico proximo a ','\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s14.dfa"));
    }

    // Separação de estados sem vírgula
    public void testComErroSintatico15() throws Exception {
        assertEquals("7 : Erro sintatico proximo a 's2'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s15.dfa"));
    }

    // Separação de transições sem vírgula
    public void testComErroSintatico16() throws Exception {
        assertEquals("13 : Erro sintatico proximo a '1'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s16.dfa"));
    }

    // Vírgula a mais na aba de "transições"
    public void testComErroSintatico17() throws Exception {
        assertEquals("14 : Erro sintatico proximo a ','\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s17.dfa"));
    }

    // Vírgula a mais em uma transição
    public void testComErroSintatico18() throws Exception {
        assertEquals("19 : Erro sintatico proximo a '}'\n", analisadorSintatico.analisaArquivo(pathErrosSintaticos + "s18.dfa"));
    }
}
