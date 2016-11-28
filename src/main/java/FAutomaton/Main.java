package FAutomaton;

public class Main {
    private static AnalisadorSintatico sintatico;

    public static void main(String[] args) throws Exception {
        sintatico = new AnalisadorSintatico();
        String errosSintaticos = sintatico.analisaArquivo("src/test/testCases/a1.dfa");
        System.out.println(errosSintaticos.equals("") ? "-> Arquivo sem erros Sintaticos." : errosSintaticos);
    }
}
