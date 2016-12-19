package FAutomaton;

import junit.framework.TestCase;

public class AnalisadorSemanticoTest extends TestCase {
    private AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico();

    private final String pathErrosSemanticos = "src/test/testCases/ErrosSemanticos/";

    // Testa símbolo repetido, gerando um warning
    public void testSemanticoErro1() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m1.dfa").a);
    }
    public void testSemanticoWarning1() throws Exception {
        assertEquals("Simbolo 1 ja utilizado anteriormente.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m1.dfa").b);
    }

    // Testa lista de símbolos vazia, gerando também um warning
    public void testSemanticoErro2() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m2.dfa").a);
    }
    public void testSemanticoWarning2() throws Exception {
        assertEquals("Alfabeto vazio. A unica string aceita sera a string vazia.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m2.dfa").b);
    }

    // Testa estados repetidos
    public void testSemanticoErro3() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m3.dfa").a);
    }
    public void testSemanticoWarning3() throws Exception {
        assertEquals("Estado s1 ja existente. Sera ignorado.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m3.dfa").b);
    }
}
