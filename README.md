# Compilador para a Linguagem FAutomaton
O compilador aqui implementado parte de uma representação de um **autômato finito determinístico** para gerar código **C++** para reconhecer a linguagem aceita pelo autômato e também código **dot** para visualizá-lo.

## Implementação
O compilador foi implementado em **Java**, com auxílio da ferramenta para geração de *parser* automatizada chamada **[ANTLR]**. Para gerar uma imagem a partir do arquivo *dot* pode-se utilizar a ferramenta **[Graphviz]** ou, disponível online em [WebGraphviz].

## Instruções de Uso
Além de Java 7, é necessário ter a ferramenta **[Maven]** instalada.

Ele deve estar disponível no repositório oficial de qualquer distribuição Linux.
```sh
sudo apt-get install maven
```
Caso contrarío, instruções de como instalá-lo podem ser encontradas em https://maven.apache.org/install.html

**Opcional**: instalar o graphviz para poder gerar as imagens a partir do *dot* sem utilizar a ferramenta online:
```sh
sudo apt-get install graphviz
```

Com o *maven* instalado, não é necessário baixar mais nenhuma dependência, o próprio *maven* irá gerenciá-las automaticamente. Para compilar o código, basta ir ao diretório raiz (aquele que contém o arquivo *pom.xml*) e executar
```sh
mvn compile
```

É possível também gerar um *jar* contendo todas as dependências do projeto, para tanto execute
```sh
mvn package
```
e então o *jar* e o *fat jar* serão gerados no diretório *./target* com os nomes *FAutomaton-1.1.jar* e *FAutomaton-1.1-jar-with-dependencies.jar*, respectivamente.

### Utilizando o Compilador
Para utilizar o compilador basta passar como argumento para o *jar* o arquivo *.dfa*:
```sh
java -jar    target/FAutomaton-1.1-jar-with-dependencies.jar    /path_para_o_aquivo/s1.dfa
```
Teremos como output dois outros arquivos, no mesmo diretório com as extensões *.cpp* e *.gv*.
Para compilar o código C++ basta especificar que a versão utilizada é a **versão 11**:
```sh
g++ -std=c++11    /path_para_o_aquivo/s1.dfa
```
Para gerar uma imagem *.png* a partir do *.gv* basta executar 
```sh
dot -Tpng    /path_para_o_aquivo/s1.gv    >    /path_para_o_aquivo/s1.png
```

## Casos de Teste
Utiliza-se a ferramenta **[JUnit]** para fazer os **62** testes automatizados, encontrados na pasta *src/test/*.
Tais testes são subdivididos em:
- 6 testes para erros léxicos
- 18 testes para erros sintáticos
- 18 testes para erros semânticos
- 7 testes para geração de código C++
- 7 testes para geração de código Dot
- 6 testes sem erros

Para executar os testes basta executar diretório raiz:
```sh
mvn test
```
[ANTLR]: http://www.antlr.org/
[JUnit]: http://junit.org/junit4/
[Maven]: https://maven.apache.org/
[Graphviz]: http://www.graphviz.org/
[WebGraphviz]: http://www.webgraphviz.com/
