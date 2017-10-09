package ecomp.uefs.problema1;

public class Executar {

	public static void main(String args[]) throws Exception {
		AnalisadorLexico x = new AnalisadorLexico("/home/joacy/eclipse-workspace/Analisador_Léxico/entrada/entrada.txt");
		x.encontraToken();
	}
}