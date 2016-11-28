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
listaEstados	: 'estados' '{' estadoSF (',' estadoSF)* '}';

estadoSF		: estadoSimples
				| estadoFinal;

estadoSimples	: NOME;

estadoFinal		: NOME '*';

/*** Transições ***/
listaTransicoes : 'transicoes' '{' transicao+ '}';

transicao		: NOME '{' transicaoParcial (',' transicaoParcial)* '}';

transicaoParcial: SIMBOLO '->' NOME;

WS	            : (' ' | '\t' | '\r' | '\n') -> skip;

SIMBOLO			: LETRA
				| NUMERO;

NOME			: LETRA (LETRA | NUMERO)*;

fragment
LETRA			: ('a' .. 'z')
				| ('A' .. 'Z');

fragment
NUMERO			: ('0' .. '9');

ERRO            : . {stop("Linha " + getLine() + ": " + getText() + " - simbolo nao identificado" + System.lineSeparator());};