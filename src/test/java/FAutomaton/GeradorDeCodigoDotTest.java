package FAutomaton;

import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GeradorDeCodigoDotTest extends TestCase {
    private AnalisadorSemantico analisadorSemantico;
    private GeradorDeCodigoDot geradorDeCodigoDot;

    private final String pathSemErros = "src/test/testCases/SemErros/";
    private final String pathSaidasCorretas     = "src/test/testCases/SaidaCodigoDot/";

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

    private String geraCodigoParaTeste(String nomeArquivo) throws Exception {
        String arquivo = pathSemErros + nomeArquivo;
        analisadorSemantico = new AnalisadorSemantico();
        analisadorSemantico.analisaArquivo(arquivo);
        geradorDeCodigoDot = new GeradorDeCodigoDot(analisadorSemantico.getAI());
        return geradorDeCodigoDot.geraCodigoDot(arquivo);
    }

    public void testCodigoDot1() throws Exception {
        assertEquals(leArquivo("d1.gv"), geraCodigoParaTeste("s1.dfa"));
    }

    public void testCodigoDot2() throws Exception {
        assertEquals(leArquivo("d2.gv"), geraCodigoParaTeste("s2.dfa"));
    }

    public void testCodigoDot3() throws Exception {
        assertEquals(leArquivo("d3.gv"), geraCodigoParaTeste("s3.dfa"));
    }

    public void testCodigoDot4() throws Exception {
        assertEquals(leArquivo("d4.gv"), geraCodigoParaTeste("s4.dfa"));
    }

    public void testCodigoDot5() throws Exception {
        assertEquals(leArquivo("d5.gv"), geraCodigoParaTeste("s5.dfa"));
    }

    public void testCodigoDot6() throws Exception {
        assertEquals(leArquivo("d6.gv"), geraCodigoParaTeste("s6.dfa"));
    }

    public void testCodigoDot7() throws Exception {
        assertEquals(leArquivo("d7.gv"), geraCodigoParaTeste("s7.dfa"));
    }

}
