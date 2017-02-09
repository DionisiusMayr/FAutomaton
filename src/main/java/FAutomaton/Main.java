package FAutomaton;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        String arquivo = args[0];  // Recebe o nome do arquivo a ser analisado como primeiro parâmetro.
        if(!arquivo.endsWith(".dfa"))
            System.out.println("Passar um arquivo .dfa como argumento.");
        else {
            AnalisadorSintatico sintatico = new AnalisadorSintatico();
            String errosSintaticos = sintatico.analisaArquivo(arquivo);
            if (!errosSintaticos.equals(""))    // Se houveram erros sintáticos, mostre-os e finalize a execução.
                System.out.println(errosSintaticos);
            else {                              // Sem erros sintáticos: checar erros semânticos
                System.out.println("Arquivo sem erros Sintaticos.");

                AnalisadorSemantico semantico = new AnalisadorSemantico();
                Par<String, String> pew = semantico.analisaArquivo(arquivo);
                String errosSemanticos  = pew.a;
                String warnings         = pew.b;

                if (!warnings.isEmpty())
                    System.out.println("Warnings:\n" + warnings);

                if(!errosSemanticos.equals(""))     // Se houveram erros semânticos, mostre-os e finalize a execução.
                    System.out.println(errosSemanticos);
                else {                              // Sem erros semânticos, gerar códigos.
                    System.out.println("Arquivo sem erros Semanticos.");

                    // Gera código c++ para o arquivo.cpp
                    GeradorDeCodigoCpp gcpp = new GeradorDeCodigoCpp(semantico.getAI());
                    PrintWriter pwcpp = new PrintWriter(new FileWriter(arquivo.substring(0, arquivo.length() - 4) + ".cpp"));
                    pwcpp.print(gcpp.geraCodigoCpp(arquivo));
                    pwcpp.flush();
                    pwcpp.close();

                    // Gera código dot para o arquivo.gv
                    GeradorDeCodigoDot gdot = new GeradorDeCodigoDot(semantico.getAI());
                    PrintWriter pwdot = new PrintWriter(new FileWriter(arquivo.substring(0, arquivo.length() - 4) + ".gv"));
                    pwdot.print(gdot.geraCodigoDot(arquivo));
                    pwdot.flush();
                    pwdot.close();

                    System.out.println("Compilacao bem sucedida.");
                }
            }
        }
    }
}
