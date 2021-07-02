import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Trilho t = new Trilho();	
		
		int min = 0;
		while(true) {
			
			String tecla = teclado.nextLine();
			simulacao(t, min++);
			printArquivo(t);
		}
		
		

	}
	
	//realiza a simulacao
	public static void simulacao(Trilho t, int minuto) {

		//a cada 30 cliques um trem novo sai
		if (minuto % 30 == 0) {
			t.novoTremAB();
			t.novoTremBA();
			System.out.println("\n\n" + t.toString());			
		}		
		else {
		t.moveTremAB();
		t.moveTremBA();
		System.out.println("\n\n" + t.toString());
		}	
	}
	
	//printa os dados no arquivo
	public static void printArquivo(Trilho t) {
		File f = new File("simulacao.txt");
		FileWriter fr;
		try {
			fr = new FileWriter(f, true);
			
			PrintWriter out = new PrintWriter(fr);	
			for (int i = 0; i < t.trensAB.length; i++) {
				//imprime só se for estação
				if(t.trensAB[i] != null)
					if(t.desviosBAList.contains(t.trilho.search(t.trensAB[i])-1)) {
					out.println("ESTAÇÃO: TREM " + t.trensAB[i].nome 
							+ ": " + t.trensAB[i].passageiros +" passageiros");
					}
			}
			for (int i = 0; i < t.trensBA.length; i++) {
				//imprime só se for estação
				if(t.trensBA[i] != null)
					if(t.desviosABList.contains(t.trilho.search(t.trensBA[i])+1))
					out.println("ESTAÇÃO: TREM " + t.trensBA[i].nome 
							+ ": " + t.trensBA[i].passageiros +" passageiros");
			}
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	
}
