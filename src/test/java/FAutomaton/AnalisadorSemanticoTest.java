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
        assertEquals("3: Simbolo 1 ja adicionado anteriormente.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m1.dfa").b);
    }

    // Testa lista de símbolos vazia, gerando também um warning
    public void testSemanticoErro2() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m2.dfa").a);
    }
    public void testSemanticoWarning2() throws Exception {
        assertEquals("3: Alfabeto vazio. A unica string aceita sera a string vazia.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m2.dfa").b);
    }

    // Testa estados repetidos
    public void testSemanticoErro3() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m3.dfa").a);
    }
    public void testSemanticoWarning3() throws Exception {
        assertEquals("7: Estado s1 ja existente. Sera ignorado.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m3.dfa").b);
    }

    // Erro de símbolo não existente usado nas transições
    public void testSemanticoErro4() throws Exception {
        assertEquals("14: Simbolo '4' nao pertence ao alfabeto.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m4.dfa").a);
    }
    public void testSemanticoWarning4() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m4.dfa").b);
    }

    // Erro de estado não existente usado nas transições
    public void testSemanticoErro5() throws Exception {
        assertEquals("13: Estado 's3' nao pertence ao conjunto de estados.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m5.dfa").a);
    }
    public void testSemanticoWarning5() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m5.dfa").b);
    }

    // Automato sem estado final, gerando um warning
    public void testSemanticoErro6() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m6.dfa").a);
    }
    public void testSemanticoWarning6() throws Exception {
        assertEquals("Automato 'A1' sem estados finais.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m6.dfa").b);
    }

    // Automato sem transicoes, gerando um warning
    public void testSemanticoErro7() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m7.dfa").a);
    }
    public void testSemanticoWarning7() throws Exception {
        assertEquals("Automato 'A1' sem transicoes.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m7.dfa").b);
    }

    // Automato sem estados, gerando um warning
    public void testSemanticoErro8() throws Exception {
        assertEquals("", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m8.dfa").a);
    }
    public void testSemanticoWarning8() throws Exception {
        assertEquals("Automato 'A1' sem estados.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m8.dfa").b);
    }

    // Automato sem estados, mas com transicoes, gerando um warning e um erro
    public void testSemanticoErro9() throws Exception {
        assertEquals("11: Estado 's2' nao pertence ao conjunto de estados.\n" +
                "12: Estado 's1' nao pertence ao conjunto de estados.\n" +
                "16: Estado 's1' nao pertence ao conjunto de estados.\n" +
                "17: Estado 's2' nao pertence ao conjunto de estados.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m9.dfa").a);
    }
    public void testSemanticoWarning9() throws Exception {
        assertEquals("Automato 'A1' sem estados.\n", analisadorSemantico.analisaArquivo(pathErrosSemanticos + "m9.dfa").b);
    }
}
