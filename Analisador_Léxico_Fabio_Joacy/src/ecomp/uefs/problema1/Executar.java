package ecomp.uefs.problema1;

import java.io.File;

public class Executar {

	public static void main(String args[]) throws Exception {
		File folder = new File("E:\\eclipse-workspace\\Analisador_Léxico_Fabio_Joacy\\entrada");
		File[] listOfFiles = folder.listFiles();
		int n=1;
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		
		AnalisadorLexico x = new AnalisadorLexico("E:\\eclipse-workspace\\Analisador_Léxico_Fabio_Joacy\\entrada\\"+listOfFiles[i].getName());
		x.encontraToken(n);
		
		n=n+1;
		      }
		}
		System.out.println("Analise completa! Sucesso!");
	}
}