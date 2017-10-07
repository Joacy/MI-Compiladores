package ecomp.uefs.problema1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AnalisadorLexico {

	private StringBuilder arquivoEntrada = new StringBuilder();
	
	String entrada = "null";
	
	// Expressões Regulares
	String identificador = "\\b[a-z|A-Z]\\w*\\b";
	String palavraReservada = "\\b(class|final|if|else|for|scan|print|int|float|bool|true|false|string)\\b";
	String numero = "-?[\\x09|\\x0A|\\x0D|\\x20]*?[0-9]+(\\x2E[0-9]+)?";
	String digito = "\\b[0-9]\\b";
	String letra = "\\b[a-z|A-Z]\\b";
	String operadorAritmetico = "\\+|-|\\*|\\x2F|%";
	String operadorRelacional = "!=|=|<=|<|>=|>";
	String operadorLogico = "\\x21|\\x26\\x26|\\x7C\\x7C";
	String delimitador = "\\x3B|\\x2C|\\x28|\\x29|\\x5B|\\x5D|\\x7B|\\x7D|\\x3A";
	String cadeiaCaracteres = "\\x22([a-zA-Z]|[0-9]|[\\x20-\\x21]|[\\x23-\\x7E]|\\x5C\\x22)+\\x22";
	String espaco = "[\\x09|\\x0A|\\x0D|\\x20]+";
	String comentarioLinha = "\\x2F\\x2F(.|\\x09|\\x0D|\\x20)*\\x0A";
	String comentarioBloco = "\\x2F\\*(.|\\x09|\\x0A|\\x0D|\\x20)*\\*\\x2F";

	String expressoes[] = new String[11];

	public AnalisadorLexico(String caminhoEntrada) {
		this.preencheExpressoes();
		try (Stream<String> stream = Files.lines(Paths.get(caminhoEntrada))) {
			stream.forEach(arquivoEntrada::append);
		} catch (IOException ex) {
			System.out.println("Arquivo não encontrado: " + caminhoEntrada);
			return;
		}
		// continua análise
	}

	public void preencheExpressoes() {
		expressoes[0] = comentarioLinha;
		expressoes[1] = comentarioBloco;
		expressoes[2] = palavraReservada;
		expressoes[3] = identificador;
		expressoes[4] = numero;
		expressoes[5] = operadorAritmetico;
		expressoes[6] = operadorRelacional;
		expressoes[7] = operadorLogico;
		expressoes[8] = delimitador;
		expressoes[9] = cadeiaCaracteres;
		expressoes[10] = espaco;
	}

	public void encontraToken() {
		for (int i = 2; i < 11; i++) {
			Pattern p = Pattern.compile(expressoes[i]);
			Matcher m = p.matcher(entrada.toString());

			while (m.find()) {
				// Obtendo o inicio do que foi encontrado
				System.out.println(i + " " + m.start() + " " + m.end());
			}
		}
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
		} else if (entrada.matches(numero)) {
			System.out.println("Número");
			System.out.println(entrada);
		} else if (entrada.matches(digito)) {
			System.out.println("Dígito");
			System.out.println(entrada);
		} else if (entrada.matches(letra)) {
			System.out.println("Letra");
			System.out.println(entrada);
		} else if (entrada.matches(operadorAritmetico)) {
			System.out.println("Operador Aritmético");
			System.out.println(entrada);
		} else if (entrada.matches(operadorRelacional)) {
			System.out.println("Operador Relacional");
			System.out.println(entrada);
		} else if (entrada.matches(operadorLogico)) {
			System.out.println("Operador Lógico");
			System.out.println(entrada);
		} else if (entrada.matches(delimitador)) {
			System.out.println("Delimitador");
			System.out.println(entrada);
		} else if (entrada.matches(cadeiaCaracteres)) {
			System.out.println("Cadeia de Caracteres");
			System.out.println(entrada);
		} else if (entrada.matches(espaco)) {
			System.out.println("Espaço");
			System.out.println(entrada);
		} else if (entrada.matches(comentarioLinha)) {
			System.out.println("Comentário de Linha");
			System.out.println(entrada);
		} else if (entrada.matches(comentarioBloco)) {
			System.out.println("Comentário de Bloco");
			System.out.println(entrada);
		}
	}

}