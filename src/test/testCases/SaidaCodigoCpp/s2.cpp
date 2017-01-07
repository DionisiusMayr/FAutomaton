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
