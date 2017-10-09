package ecomp.uefs.problema1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	String numero = "-?[\\x09|\\x0A|\\x0D|\\x20]*?\\b[0-9]+(\\x2E[0-9]+)?\\b";
	String digito = "\\b[0-9]\\b";
	String letra = "\\b[a-z|A-Z]\\b";
	String operadorAritmetico = "\\+|-|\\*|\\x2F|%";
	String operadorRelacional = "!=|=|<=|<|>=|>";
	String operadorLogico = "\\x21|\\x26\\x26|\\x7C\\x7C";
	String delimitador = "\\x3B|\\x2C|\\x28|\\x29|\\x5B|\\x5D|\\x7B|\\x7D|\\x3A";
	String cadeiaCaracteres = "\\x22([\\x20-\\x21]|[\\x23-\\x7E])+\\x22";
	String espaco = "[\\x09|\\x20]+";
	String comentarioLinha = "\\x2F\\x2F(.)*[\\r\\n|\\n]";
	String comentarioBloco = "\\x2F\\*((.)|\\x2F\\*|[\\r\\n|\\n])*\\*\\x2F";
	String quebraDeLinha = "[\\r\\n|\\n]";

	String erroCadeia = "(\\x22(([a-zA-Z]|[0-9]|\\x5C\\x22|[\\x20-\\x21]|[\\x23-\\x7E])*\\ç([a-zA-Z]|[0-9]|\\x5C\\x22|[\\x20-\\x21]|[\\x23-\\x7E])*)+\\x22)|(\\x22([a-zA-Z]|[0-9]|\\x5C\\x22|[\\x20-\\x21]|[\\x23-\\x7E]|\\ç)*)";
	String erroComentarioBloco = "\\x2F\\*((.)|[\\r\\n|\\n])*\\**";
	String erroIdentificador = "(([0-9]|_)\\w+)|(([a-z|A-Z])+(\\x22|\\x2E|\\x23|\\x24|\\x26|\\x27|\\x3F|\\x40|\\x5C|\\x5E|\\x5F|\\x60|\\x7C|\\x7E)+)";
	String erroNumero = "([0-9]+\\x2E)";
	String erroSimbolo = "(\\x2E|\\x23|\\x24|\\x26|\\x27|\\x3F|\\x40|\\x5C|\\x5E|\\x5F|\\x60|\\x7C|\\x7E)+";

	String expressoes[] = new String[100];
		
	public AnalisadorLexico(String caminhoEntrada) {
		this.preencheExpressoes();
		try (Stream<String> stream = Files.lines(Paths.get(caminhoEntrada))) {
			stream.forEach(l -> arquivoEntrada.append(l).append(System.lineSeparator()));
		} catch (IOException ex) {
			System.out.println("Arquivo não encontrado: " + caminhoEntrada);
			return;
		}
	}

	public void preencheExpressoes() {
		expressoes[0] = comentarioLinha;
		expressoes[1] = comentarioBloco;
		expressoes[2] = erroComentarioBloco;
		expressoes[3] = palavraReservada;
		expressoes[14] = identificador;
		expressoes[5] = numero;
		expressoes[6] = operadorAritmetico;
		expressoes[7] = operadorRelacional;
		expressoes[8] = operadorLogico;
		expressoes[9] = delimitador;
		expressoes[10] = cadeiaCaracteres;
		expressoes[11] = erroCadeia;
		expressoes[12] = espaco;
		expressoes[13] = quebraDeLinha;
		expressoes[4] = erroIdentificador;
		expressoes[15] = erroNumero;
		expressoes[16] = erroSimbolo;
	}

	public void encontraToken() throws Exception {

		FileWriter saida = new FileWriter("/home/joacy/eclipse-workspace/Analisador_Léxico/entrada/saida.txt"); 
		PrintWriter gravar = new PrintWriter(saida);

		String token = "null";
		int j = 1;

		for (int i = 0; i < 17; i++) {
			if (i == 0) {
				token = "Comentario de Linha";
			}	
			else if (i == 1) {
				token = "Comentario de Bloco";
			}
			else if (i == 2) {
				token = "Erro de Comentario de Bloco";
			}
			else if (i == 3) {
				token = "Palavra Reservada";
			}	
			else if (i == 4) {
				token = "Identificador";
			}	
			else if (i == 5) {
				token = "Número";
			}	
			else if (i == 6) {
				token = "Operador Aritmético";
			}	
			else if (i == 7) {
				token = "Operador Relacional";
			}	
			else if (i == 8) {
				token = "Operador Lógico";
			}	
			else if (i == 9) {
				token = "Delimitador";
			}
			else if (i == 10) {
				token = "Cadeia de Caracteres";
			}
			else if(i == 11) {
				token = "Erro de Cadeia de Caracteres";
			}
			else if (i == 12) {
				token = "Espaço";
			}
			else if(i == 14) {
				token = "Erro de Identificador";
			}
			else if(i == 15) {
				token = "Erro de Número";
			}
			else if(i == 16) {
				token = "Erro de Símbolo";
			}

			Pattern p = Pattern.compile(expressoes[i]);
			Matcher m = p.matcher(arquivoEntrada.toString());

			if (m.lookingAt()) {
							
				if (i == 13 || i == 0) {
					j = j + 1;
				}
				else {
					System.out.println("<"+token+", "+m.group()+"> " + j);
					gravar.printf("<%s, %s>  %s %n", token, m.group(), j);
				}
				if(i==1) {	
					String s = m.group();
					if(s.contains("\n")){
						int quantidade = s.length() - s.replaceAll("\n", "").length();
						System.out.println("aki");
						j=j+quantidade;
					}
				}
				arquivoEntrada.delete(0, m.end());
				i = i - (i + 1);
			}
		}
		gravar.close();
	}
}