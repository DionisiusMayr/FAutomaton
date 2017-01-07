package FAutomaton;

import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GeradorDeCodigoCppTest extends TestCase {
    private AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico();
    private GeradorDeCodigoCpp geradorDeCodigoCpp = new GeradorDeCodigoCpp(analisadorSemantico.getAI());

    private final String pathSemErrosSintaticos = "src/test/testCases/SemErrosSintaticos/"; //TODO mudar para "sem erros"
    private final String pathSaidasCorretas     = "src/test/testCases/SaidaCodigoCpp/";

    private String leArquivo(String nomeArquivo) throws Exception{
        File f = new File(pathSaidasCorretas + nomeArquivo);

        List<String> linhas = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
        StringBuilder outputDesejado = new StringBuilder();
        for (String s : linhas) {
            outputDesejado.append(s);
            outputDesejado.append("\n");
        }

        return outputDesejado.toString();
    }

    public void testCodigoCpp1() throws Exception {
        String nomeArquivo = "s1.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp2() throws Exception {
        String nomeArquivo = "s2.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp3() throws Exception {
        String nomeArquivo = "s3.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp4() throws Exception {
        String nomeArquivo = "s4.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp5() throws Exception {
        String nomeArquivo = "s5.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp6() throws Exception {
        String nomeArquivo = "s6.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }

    public void testCodigoCpp7() throws Exception {
        String nomeArquivo = "s7.dfa";
        assertEquals(leArquivo(nomeArquivo), geradorDeCodigoCpp.geraCodigoCpp(pathSemErrosSintaticos + nomeArquivo));
    }
}
