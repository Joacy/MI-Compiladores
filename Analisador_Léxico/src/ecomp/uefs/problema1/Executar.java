package ecomp.uefs.problema1;

public class Executar {

	public static void main(String args[]) {
		AnalisadorLexico x = new AnalisadorLexico("/home/joacy/eclipse-workspace/Analisador_Léxico/src/ecomp/uefs/problema1/entrada.txt");
		x.encontraToken();
	}
}