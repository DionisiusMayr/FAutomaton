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
	alfabeto.insert('0');
	alfabeto.insert('1');

	/* Estado inicial */
	estadoAtual = "q0";

	/* Conjunto de estados finais */
	estadosFinais.insert("q2");

	/* Funcao de transicao */
	transicao[make_pair("q0", '0')] = "q1";
	transicao[make_pair("q0", '1')] = "q5";
	transicao[make_pair("q1", '0')] = "q6";
	transicao[make_pair("q1", '1')] = "q2";
	transicao[make_pair("q2", '0')] = "q0";
	transicao[make_pair("q2", '1')] = "q2";
	transicao[make_pair("q3", '0')] = "q2";
	transicao[make_pair("q3", '1')] = "q6";
	transicao[make_pair("q4", '0')] = "q7";
	transicao[make_pair("q4", '1')] = "q5";
	transicao[make_pair("q5", '0')] = "q2";
	transicao[make_pair("q5", '1')] = "q6";
	transicao[make_pair("q6", '0')] = "q6";
	transicao[make_pair("q6", '1')] = "q4";
	transicao[make_pair("q7", '0')] = "q6";
	transicao[make_pair("q7", '1')] = "q2";

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
