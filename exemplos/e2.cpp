#include <iostream>
#include <set>
#include <map>

using namespace std;

int main() {
	bool 		recusa = false;
	set<char> 	alfabeto;
	set<string>	estadosFinais;
	string 		estadoAtual;
	map<pair<string, char>, string> transicao;

	/* Le a fita de entrada */
	string entrada;
	cin >> entrada;

	/* Simbolos do alfabeto */
	alfabeto.insert('a');
	alfabeto.insert('b');

	/* Estado inicial */
	estadoAtual = "q1";

	/* Conjunto de estados finais */
	estadosFinais.insert("q6");
	estadosFinais.insert("q5");
	estadosFinais.insert("q4");
	estadosFinais.insert("q3");
	estadosFinais.insert("q9");

	/* Funcao de transicao */
	transicao[make_pair("q1", 'a')] = "q2";
	transicao[make_pair("q1", 'b')] = "q7";
	transicao[make_pair("q2", 'a')] = "q3";
	transicao[make_pair("q2", 'b')] = "q7";
	transicao[make_pair("q3", 'a')] = "q3";
	transicao[make_pair("q3", 'b')] = "q4";
	transicao[make_pair("q4", 'a')] = "q3";
	transicao[make_pair("q4", 'b')] = "q5";
	transicao[make_pair("q5", 'a')] = "q3";
	transicao[make_pair("q5", 'b')] = "q6";
	transicao[make_pair("q6", 'a')] = "q3";
	transicao[make_pair("q6", 'b')] = "q7";
	transicao[make_pair("q7", 'a')] = "q8";
	transicao[make_pair("q7", 'b')] = "q7";
	transicao[make_pair("q8", 'a')] = "q9";
	transicao[make_pair("q8", 'b')] = "q7";

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
