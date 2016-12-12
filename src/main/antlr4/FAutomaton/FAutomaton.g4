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

automato		: 'automato' (LETRA (LETRA | NUMERO)*) '{' alfabeto listaEstados listaTransicoes '}';

/*** Alfabeto ***/
alfabeto		: 'alfabeto' '{' listaSimbolos '}';

listaSimbolos	: (LETRA | NUMERO) (',' (LETRA | NUMERO))*;

/*** Estados ***/
listaEstados	: 'estados' '{' (estadoSF (',' estadoSF)*)? '}';  // É possível não ter nenhum estado,
                                                                  // mas tendo ao menos um estado é obrigatório ter também um estado inicial
                                                                  // (aqui o estado inicial é sempre o primeiro estado definido, que pode também ser um estado final).

estadoSF		: estadoSimples
				| estadoFinal;

estadoSimples	: (LETRA (LETRA | NUMERO)*);

estadoFinal		: (LETRA (LETRA | NUMERO)*) '*';

/*** Transições ***/
listaTransicoes : 'transicoes' '{' transicao* '}'; // É possível não ter transições em um autômato

transicao		: (LETRA (LETRA | NUMERO)*) '{' transicaoParcial (',' transicaoParcial)* '}';

transicaoParcial: (LETRA | NUMERO) '->' (LETRA (LETRA | NUMERO)*);

WS	            : (' ' | '\t' | '\r' | '\n') -> skip;

COMENTARIO      : '/*' (.)*? '*/' -> skip;

LETRA			: ('a' .. 'z')
				| ('A' .. 'Z');

NUMERO			: ('0' .. '9');

ERRO            : . {stop(getLine() + ": Erro lexico: " + getText() + " - simbolo nao identificado" + System.lineSeparator());};