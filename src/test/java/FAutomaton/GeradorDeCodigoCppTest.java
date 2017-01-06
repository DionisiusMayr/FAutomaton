package FAutomaton;

import junit.framework.TestCase;

public class GeradorDeCodigoCppTest extends TestCase {
    private AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico();
    private GeradorDeCodigoCpp geradorDeCodigoCpp = new GeradorDeCodigoCpp();

    private final String pathSemErrosSintaticos = "src/test/testCases/SemErrosSintaticos/"; //TODO fazer test cases para sem erros sintáticos ou semânticos

    public void testCodigoCpp1() throws Exception {
        assertEquals("", geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + "s1.dfa"));
    }
}
