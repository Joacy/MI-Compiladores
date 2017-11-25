import java.io.File;

public class Executar {

	public static void main(String args[]) throws Exception {
		File folder = new File("E:\\eclipse-workspace\\Analisador_Sintático_Fabio_Joacy\\entrada");
		File[] listOfFiles = folder.listFiles();
		int n = 1;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				AnalisadorSintatico x = new AnalisadorSintatico("E:\\eclipse-workspace\\Analisador_Sintático_Fabio_Joacy\\entrada\\" + listOfFiles[i].getName());
				x.analisarGramatica(n);
				n = n + 1;
			}
		}
		System.out.println("Analise completa! Sucesso!");
	}
}