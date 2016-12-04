// Esboço da gramática para a linguagem FAutomaton.
grammar FAutomaton;

options {
	language = Java;
}

@members {
    public void stop(String msg) {
        throw new ParseCancellationException(msg);
    }
}

automato		: 'automato' NOME '{' alfabeto listaEstados listaTransicoes '}';

/*** Alfabeto ***/
alfabeto		: 'alfabeto' '{' listaSimbolos '}';

listaSimbolos	: SIMBOLO (',' SIMBOLO)*;

/*** Estados ***/
listaEstados	: 'estados' '{' (estadoSF (',' estadoSF)*)? '}';  // É possível não ter nenhum estado,
                                                                  // mas tendo ao menos um estado é obrigatório ter também um estado inicial
                                                                  // (aqui o estado inicial é sempre o primeiro estado definido, que pode também ser um estado final).

estadoSF		: estadoSimples
				| estadoFinal;

estadoSimples	: NOME;

estadoFinal		: NOME '*';

/*** Transições ***/
listaTransicoes : 'transicoes' '{' transicao* '}'; // É possível não ter transições em um autômato

transicao		: NOME '{' transicaoParcial (',' transicaoParcial)* '}';

transicaoParcial: SIMBOLO '->' NOME;

WS	            : (' ' | '\t' | '\r' | '\n') -> skip;

SIMBOLO			: LETRA
				| NUMERO;

NOME			: LETRA (LETRA | NUMERO)*;

COMENTARIO      : '/*' (.)*? '*/' -> skip;

fragment
LETRA			: ('a' .. 'z')
				| ('A' .. 'Z');

fragment
NUMERO			: ('0' .. '9');

ERRO            : . {stop(getLine() + ": Erro lexico: " + getText() + " - simbolo nao identificado" + System.lineSeparator());};