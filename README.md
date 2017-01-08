# Compilador para a Linguagem FAutomaton
O compilador aqui implementado parte de uma representação de um **autômato finito determinístico** para gerar código **C++** para reconhecer a linguagem aceita pelo autômato e também código **dot** para visualizá-lo.

## Implementação
O compilador foi implementado em **Java**, com auxílio da ferramenta para geração de *parser* automatizada chamada **[ANTLR]**. Para gerar uma imagem a partir do arquivo *dot* pode-se utilizar a ferramenta **[Graphviz]** ou, disponível online em [WebGraphviz].

## Instruções de Uso
Além de Java 7, é necessário ter a ferramenta **[Maven]** instalada.

Ele deve estar disponível no repositório oficial de qualquer distribuição Linux.

    sudo apt-get install maven

Caso contrarío, instruções de como instalá-lo podem ser encontradas em https://maven.apache.org/install.html

**Opcional**: instalar o graphviz para poder gerar as imagens a partir do *dot* sem utilizar a ferramenta online:

    sudo apt-get install graphviz

Com o *maven* instalado, não é necessário baixar mais nenhuma dependência, o próprio *maven* irá gerenciá-las automaticamente. Para compilar o código, basta ir ao diretório raiz (aquele que contém o arquivo *pom.xml*) e executar

    mvn compile

É possível também gerar um *jar* contendo todas as dependências do projeto, para tanto execute

    mvn package

e então o *jar* e o *fat jar* serão gerados no diretório *./target* com os nomes *FAutomaton-1.1.jar* e *FAutomaton-1.1-jar-with-dependencies.jar*, respectivamente.

### Utilizando o Compilador
Para utilizar o compilador basta passar como argumento para o *jar* o arquivo *.dfa*:

    java -jar    target/FAutomaton-1.1-jar-with-dependencies.jar    /path_para_o_aquivo/s1.dfa

Teremos como output dois outros arquivos, no mesmo diretório com as extensões *.cpp* e *.gv*.
Para compilar o código C++ basta especificar que a versão utilizada é a **versão 11**:

    g++ -std=c++11    /path_para_o_aquivo/s1.dfa

Para gerar uma imagem *.png* a partir do *.gv* basta executar 

    dot -Tpng    /path_para_o_aquivo/s1.gv    >    /path_para_o_aquivo/s1.png


## Casos de Teste
Utiliza-se a ferramenta **[JUnit]** para fazer os **62** testes automatizados, encontrados na pasta *src/test/*.
Tais testes são subdivididos em:
- 6 testes para erros léxicos
- 18 testes para erros sintáticos
- 18 testes para erros semânticos
- 7 testes para geração de código C++
- 7 testes para geração de código Dot
- 6 testes sem erros

Para executar os testes basta executar no diretório raiz:

    mvn test

## Exemplo de Utilização
Para a seguinte descrição:

    automato E1 {
        alfabeto {0, 1}
    
        estados {q1, q2, q3, q4, q5*}
    
        transicoes {
                q1 { 0 -> q1, 1 -> q2 }
                q2 { 0 -> q3, 1 -> q2 }
                q3 { 0 -> q1, 1 -> q4 }
                q4 { 0 -> q5, 1 -> q2 }
                q5 { 0 -> q5, 1 -> q5 }
            }
        }
    }

Geraria-se o seguinte código C++:

```c++
#include <iostream>
#include <set>
#include <map>

using namespace std;

int main() {
	bool        recusa = false;
	set<char>   alfabeto;
	set<string> estadosFinais;
	string      estadoAtual;
	map<pair<string, char>, string> transicao;

	/* Le a fita de entrada */
	string entrada;
	cin >> entrada;

	/* Simbolos do alfabeto */
	alfabeto.insert('0');
	alfabeto.insert('1');

	/* Estado inicial */
	estadoAtual = "s1";

	/* Conjunto de estados finais */
	estadosFinais.insert("s1");

	/* Funcao de transicao */
	transicao[make_pair("s1", '0')] = "s2";
	transicao[make_pair("s1", '1')] = "s1";
	transicao[make_pair("s2", '0')] = "s1";
	transicao[make_pair("s2", '1')] = "s2";

	/* Para cada simbolo da fita de entrada, realize a transicao. */
	for(const char & c : entrada) {
		if((alfabeto.find(c) != alfabeto.cend()) || (transicao.find(make_pair(estadoAtual, c)) != transicao.cend()))
			/* Funcao de transicao, toma como parametro o estado atual e o
			 * simbolo lido e atualiza o estado atual com o estado de destino */
			estadoAtual = transicao[make_pair(estadoAtual, c)];
		else {
			/* Símbolo lido nao pertencente ao alfabeto ou transicao nao definida */
			recusa = true;
			break;
		}
	}

	if(recusa || (estadosFinais.find(estadoAtual) == estadosFinais.cend()))
		cout << "Rejeita: A cadeia " << entrada << " nao pertence a linguagem." << endl;
	else
		cout << "Aceita: A cadeia " << entrada << " pertence a linguagem." << endl;

	return 0;
}
```

O seguinte arquivo *.gv*:

    digraph E1 {
        /* configuracoes de design e layout */
        node [shape = circle];
        rankdir = "LR";
    
        /* estado inicial */
        x [style = invis];
        x -> q1;
    
        /* estados finais */
        q5 [shape = doublecircle];
    
        /* outros estados */
        q2;
        q1;
        q4;
        q3;
    
        /* transicoes */
        q1 -> q1 [label = "0"];
        q1 -> q2 [label = "1"];
        q2 -> q3 [label = "0"];
        q2 -> q2 [label = "1"];
        q3 -> q1 [label = "0"];
        q3 -> q4 [label = "1"];
        q4 -> q5 [label = "0"];
        q4 -> q2 [label = "1"];
        q5 -> q5 [label = "0"];
        q5 -> q5 [label = "1"];
    }


E a seguinte imagem (gerada pelo *graphviz*):

![E1](/exemplos/e1.png?raw=true "Autômato E1")

[ANTLR]: http://www.antlr.org/
[JUnit]: http://junit.org/junit4/
[Maven]: https://maven.apache.org/
[Graphviz]: http://www.graphviz.org/
[WebGraphviz]: http://www.webgraphviz.com/
