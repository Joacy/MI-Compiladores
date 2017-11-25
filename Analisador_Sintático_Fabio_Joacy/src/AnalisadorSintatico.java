import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AnalisadorSintatico {
	private StringBuilder arquivoEntrada = new StringBuilder();
	
	//Terminais
	String identificador = "\\b([a-z]|[A-Z])\\w*\\b";
	String numero = "(-(\\x09|\\x0A|\\x0D|\\x20)*)?[0-9]+(\\x2E[0-9]+)?";
	String cadeiaCaracteres = "\\x22((\\x5C\\x22)|[\\x20-\\x21]|[\\x23-\\x7E])+\\x22";
	String booleano = "\\b(true|false)\\b";
	
	public AnalisadorSintatico(String caminhoEntrada) {
		//this.preencheExpressoes();
		try (Stream<String> stream = Files.lines(Paths.get(caminhoEntrada))) {
			stream.forEach(l -> arquivoEntrada.append(l).append(System.lineSeparator()));
		} catch (IOException ex) {
			System.out.println("Arquivo não encontrado: " + caminhoEntrada);
			return;
		}
	}
	
	public void analisarGramatica(int n) {
		
	}
}
