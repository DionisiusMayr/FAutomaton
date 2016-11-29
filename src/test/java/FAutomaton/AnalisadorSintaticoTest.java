package FAutomaton;

import junit.framework.TestCase;

public class AnalisadorSintaticoTest extends TestCase {
    AnalisadorSintatico analisadorSintatico     = new AnalisadorSintatico();

    private final String pathErrosLexicos       = "src/test/testCases/ErrosLexicos/";
    private final String pathErrosSintaticos    = "src/test/testCases/ErrosSintaticos/";
    private final String pathSemErrosSintaticos = "src/test/testCases/SemErrosSintaticos/";

    public void testSemErro1() throws Exception {
        assertEquals(analisadorSintatico.analisaArquivo(pathSemErrosSintaticos + "s1.dfa"), "");
    }
}
