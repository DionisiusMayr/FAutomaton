package FAutomaton;

import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GeradorDeCodigoCppTest extends TestCase {
    private AnalisadorSemantico analisadorSemantico;
    private GeradorDeCodigoCpp geradorDeCodigoCpp;

    private final String pathSemErrosSintaticos = "src/test/testCases/SemErros/";
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

    public String geraCodigoParaTeste(String nomeArquivo) throws Exception {
        String arquivo = pathSemErrosSintaticos + nomeArquivo;
        analisadorSemantico = new AnalisadorSemantico();
        analisadorSemantico.analisaArquivo(arquivo);
        geradorDeCodigoCpp = new GeradorDeCodigoCpp(analisadorSemantico.getAI());
        return geradorDeCodigoCpp.geraCodigoCpp(arquivo);
    }

    public void testCodigoCpp1() throws Exception {
        assertEquals(leArquivo("s1.cpp"), geraCodigoParaTeste("s1.dfa"));
    }

    public void testCodigoCpp2() throws Exception {
        assertEquals(leArquivo("s2.cpp"), geraCodigoParaTeste("s2.dfa"));
    }

    public void testCodigoCpp3() throws Exception {
        assertEquals(leArquivo("s3.cpp"), geraCodigoParaTeste("s3.dfa"));
    }

    public void testCodigoCpp4() throws Exception {
        assertEquals(leArquivo("s4.cpp"), geraCodigoParaTeste("s4.dfa"));
    }

    public void testCodigoCpp5() throws Exception {
        assertEquals(leArquivo("s5.cpp"), geraCodigoParaTeste("s5.dfa"));
    }

    public void testCodigoCpp6() throws Exception {
        assertEquals(leArquivo("s6.cpp"), geraCodigoParaTeste("s6.dfa"));
    }

    public void testCodigoCpp7() throws Exception {
        assertEquals(leArquivo("s7.cpp"), geraCodigoParaTeste("s7.dfa"));
    }
}
