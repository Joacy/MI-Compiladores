package ecomp.uefs.problema1;

import java.util.*;

public class AnalisadorLexico {

	// String para teste
	String entrada = "asdfags";

	// Expressões Regulares
	String identificador = "[a-zA-Z][a-zA-Z0-9_]*";
	String palavraReservada = "\\b[class|final|if|else|for|scan|print|int|float|bool|true|false|string]\\b";
	String numero = "-?[\\x09|\\x0A|\\x0D|\\x20]*?[0-9][0-9]*(\\x2E?[0-9]+)*";
	String digito = "[0-9]";
	String letra = "[a-zA-Z]";
	String operadorAritmetico = "\\+|-|\\*|/|%";
	String operadorRelacional = "!=|=|<|<=|>|>=";
	String operadorLogico = "\\x21|\\x26\\x26|\\x7C\\x7C";
	String delimitador = "\\x3B|\\x2C|\\x28|\\x29|\\x5B|\\x5D|\\x7B|\\x7D|\\x3A";
	String cadeiaCaracteres = "\\x22[[a-zA-Z]|[0-9]|[\\x20-\\x7E && [^\\x22]]|\\x5C\\x22]+\\x22";
	String espaco = "[\\x09|\\x0A|\\x0D|\\x20]+";
	String comentarioLinha = "\\/\\/(.)*\\n";
	String comentarioBloco = "\\/\\*(.|\\x09|\\x0A|\\x0D|\\x20)*\\*\\/";
	
	public static void main(String args[]) {
		Scanner testador = new Scanner("entrada.txt");
		//System.out.println(testador);
		AnalisadorLexico x = new AnalisadorLexico();
		String teste = "//hjsakjcvllav\n";
		
		x.verificaEntrada(teste);
	}

	public void verificaEntrada(String entrada) {
		if (entrada.matches(identificador)) {
			if (entrada.matches(palavraReservada)) {
				System.out.println("Palavra Reservada");
				System.out.println(entrada);
			} else {
				System.out.println("Identificador");
				System.out.println(entrada);
			}
		}
		else if (entrada.matches(numero)) {
			System.out.println("Número");
			System.out.println(entrada);
		}
		else if (entrada.matches(digito)) {
			System.out.println("Dígito");
			System.out.println(entrada);
		}
		else if (entrada.matches(letra)) {
			System.out.println("Letra");
			System.out.println(entrada);
		}
		else if (entrada.matches(operadorAritmetico)) {
			System.out.println("Operador Aritmético");
			System.out.println(entrada);
		}
		else if (entrada.matches(operadorRelacional)) {
			System.out.println("Operador Relacional");
			System.out.println(entrada);
		}
		else if (entrada.matches(operadorLogico)) {
			System.out.println("Operador Lógico");
			System.out.println(entrada);
		}
		else if (entrada.matches(delimitador)) {
			System.out.println("Delimitador");
			System.out.println(entrada);
		}
		else if (entrada.matches(cadeiaCaracteres)) {
			System.out.println("Cadeia de Caracteres");
			System.out.println(entrada);
		}
		else if (entrada.matches(espaco)) {
			System.out.println("Espaço");
			System.out.println(entrada);
		}
		else if (entrada.matches(comentarioLinha)) {
			System.out.println("Comentário de Linha");
			System.out.println(entrada);
		}
		else if (entrada.matches(comentarioBloco)) {
			System.out.println("Comentário de Bloco");
			System.out.println(entrada);
		}
	}
}