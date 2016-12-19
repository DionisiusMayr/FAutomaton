package FAutomaton;

import javax.sound.midi.SysexMessage;

public class Main {
    private static AnalisadorSintatico sintatico;
    private static AnalisadorSemantico semantico;
    private static String arquivo = "/home/nicolezk/IdeaProjects/FAutomaton/src/test/testCases/ErrosSemanticos/m1.dfa";

    public static void main(String[] args) throws Exception {
        sintatico = new AnalisadorSintatico();
        String errosSintaticos = sintatico.analisaArquivo(arquivo);
        if(!errosSintaticos.equals("")) { // Se houveram erros sintáticos
            System.out.println(errosSintaticos);
        }
        else {
            System.out.println("Arquvos sem erros Sintaticos.");

            semantico = new AnalisadorSemantico();
            String errosSemanticos = semantico.analisaArquivo(arquivo);
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
