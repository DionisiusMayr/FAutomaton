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
}
