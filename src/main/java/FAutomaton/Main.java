package FAutomaton;

import javax.sound.midi.SysexMessage;

public class Main {
    private static AnalisadorSintatico sintatico;
    private static AnalisadorSemantico semantico;
    private static String arquivo = "./src/test/testCases/ErrosSemanticos/m1.dfa";

    public static void main(String[] args) throws Exception {
        sintatico = new AnalisadorSintatico();
        String errosSintaticos = sintatico.analisaArquivo(arquivo);
        if(!errosSintaticos.equals("")) { // Se houveram erros sintáticos
            System.out.println(errosSintaticos);
        }
        else {
            System.out.println("Arquivo sem erros Sintaticos.\n");

            semantico = new AnalisadorSemantico();
            Par<String, String> pew = semantico.analisaArquivo(arquivo);
            String errosSemanticos  = pew.a;
            String warnings         = pew.b;

            if (!warnings.isEmpty()) {
                System.out.println("Warnings:");
                System.out.println(warnings);
            }

            if(!errosSemanticos.equals("")) { // Se houveram erros semânticos
                System.out.println(errosSemanticos);
            }
            else {
                System.out.println("Arquivo sem erros Semanticos.");

                System.out.println("Output:");
            }
        }
    }
}
