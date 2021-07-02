import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Trilho {
	//Lista duplamente encadeada que será os trilho e guardará o trem
	DoublyLinkedList<Trem> trilho = new DoublyLinkedList<>();
	int qtddNos;
	
	int qtddEstacoes;  
	List<Integer> estacoes = new ArrayList<>();
	
	
	List<Integer> desviosABList = new ArrayList<>(); 
	List<Integer> desviosBAList = new ArrayList<>();

	Trem tremReserva;
	Trem tremDesvio;
	
	Trem[] trensAB = new Trem[20];
	Trem[] trensBA = new Trem[20];
	int nTrens = 0;
	
	public Trilho() {
		//sorteia um numero de 10 até 30
		this.qtddEstacoes = (int) ( 10 + Math.random() * 21);

		//guarda as posicoes das estacoes
		estacoes.add(0, 22);
		for (int i = 1; i < qtddEstacoes; i++) {
			estacoes.add(i, estacoes.get(i-1) + 23);
		}
				
		//guarda as posicoes dos desvios
		//Desvios de A -> B
		for (int i = 0; i < qtddEstacoes; i++) {
			desviosABList.add(i, estacoes.get(i)-1);
		}
		desviosABList.add(desviosABList.size(), desviosABList.get(desviosABList.size()-1)+23);
		
		//Desvios de B -> A
		desviosBAList.add(0, 0);
		for (int i = 0; i < qtddEstacoes; i++) {
			desviosBAList.add(i+1, estacoes.get(i)+1);
		}
		
		//calcula a quantidade de posicoes do trilho
		this.qtddNos = (20 * qtddEstacoes) + (20 +(qtddEstacoes - 1) + desviosABList.size() + desviosBAList.size());
				
				
		//enche a lista 
		this.tremReserva = new Trem("_");
		this.tremDesvio = new Trem(" ");
		for (int i = 0; i <= qtddNos; i++) {
			if(desviosABList.contains(i)) {
				trilho.insertFirst(tremDesvio);
			}
			else if(desviosBAList.contains(i)) {
				trilho.insertFirst(tremDesvio);
				}
			else
				trilho.insertFirst(tremReserva);
		}
			
		System.out.println(toString());
	}
	
	public void novoTremAB() {
		String nome = "AB0" + nTrens; 
		Trem t = new Trem(nome);
		trensAB[nTrens] = t;
		trilho.remove(1);
		trilho.insert(t, 1);
	}
	
	public void novoTremBA() {
		String nome = "BA0" + nTrens; 
		Trem t = new Trem(nome);
		trensBA[nTrens++] = t;
		trilho.remove(qtddNos-1);
		trilho.insert(t, qtddNos-1);
	}	
	
	public void moveTremAB() {
		for (int i = 0; i < nTrens; i++) {
			if (!(trilho.search(trensAB[i]) == -1)) {
		
				if (trensAB[i].atraso == 0) {
					
					//pega a posicao atual,
					int pos = trilho.search(trensAB[i]);
					
					//verifica o próximo é desvio
					if(verificaDesvioAB(pos+1)) {
						if(verificadisponibilidadeAB(trensAB[i], false)) {
							trilho.remove(pos);
							trilho.insert(tremReserva, pos);
							trilho.remove(pos+2);
							trilho.insert(trensAB[i], pos+2);
							
							tremNaEstacao(trensAB[i]);
						}
						else {
							trilho.remove(pos);
							trilho.insert(tremReserva, pos);
							
							//adiciona o tem atual
							trilho.remove(pos+1);
							trilho.insert(trensAB[i], pos+1);
						}
					}
					else if (estaNoDesvioAB(pos)){
						if(verificadisponibilidadeAB(trensAB[i], true)) {
							trilho.remove(pos);
							trilho.insert(tremDesvio, pos);
							
							//adiciona o tem atual
							trilho.remove(pos+1);
							trilho.insert(trensAB[i], pos+1);
							
							tremNaEstacao(trensAB[i]);
	
						}
					}
					else if (estaNaEstacao(pos)){
						if (trilho.search(trensAB[i]) > -1)
							trilho.remove(pos);
						trilho.insert(tremReserva, pos);
						
						//adiciona o tem atual
						trilho.remove(pos+2);
						trilho.insert(trensAB[i], pos+2);
					}
					else {
						if (trilho.search(trensBA[i]) > -1)
							trilho.remove(pos);
						trilho.insert(tremReserva, pos);
						
						//adiciona o tem atual
						trilho.remove(pos+1);
						trilho.insert(trensAB[i], pos+1);
					}
				}
				else trensAB[i].atraso--;
			}
		}
	}
	
	public void moveTremBA() {
		for (int i = 0; i < nTrens; i++) {
			if (!(trilho.search(trensBA[i]) == -1)) {
				if (trensBA[i].atraso == 0) {
	
					//pega a posicao atual,
					int pos = trilho.search(trensBA[i]);
					
					if(verificaDesvioBA(pos-1)) {
						if(verificadisponibilidadeBA(trensBA[i], false)) {
							trilho.remove(pos);
							trilho.insert(tremReserva, pos);
							trilho.remove(pos-2);
							trilho.insert(trensBA[i], pos-2);
							
							tremNaEstacao(trensBA[i]);
						}
						else {
							trilho.remove(pos);
							trilho.insert(tremReserva, pos);
							
							//adiciona o tem atual
							trilho.remove(pos-1);
							trilho.insert(trensBA[i], pos-1);
						}
					}
					else if (estaNoDesvioBA(pos)){
						if(verificadisponibilidadeBA(trensBA[i], true)) {
							trilho.remove(pos);
							trilho.insert(tremDesvio, pos);
							
							//adiciona o tem atual
							trilho.remove(pos-1);
							trilho.insert(trensBA[i], pos-1);
							
							tremNaEstacao(trensBA[i]);
						}
					}
					else if (estaNaEstacao(pos)){
						if (trilho.search(trensBA[i]) > -1)
								trilho.remove(pos);
						trilho.insert(tremReserva, pos);
						
						//adiciona o tem atual
						trilho.remove(pos-2);
						trilho.insert(trensBA[i], pos-2);
					}
					else {
						if (trilho.search(trensBA[i]) > -1) {
							trilho.remove(pos);
							trilho.insert(tremReserva, pos);
						}
						//adiciona o tem atual
						trilho.remove(pos-1);
						trilho.insert(trensBA[i], pos-1);
					}
				}
				else trensBA[i].atraso--;
			
			}

		}
	}
	
	public boolean verificadisponibilidadeAB(Trem t, boolean taNoDesvio) {
		//posicao do trem
		int posicao = trilho.search(t);
		if (taNoDesvio)	
			posicao -= 1;

		//verifica se a estação está vazia
		if (trilho.get(posicao+2).nome.equals("_")) {
			int posicaoDpsEstacao = posicao+3;
			int posicaoAteOndeVerifica = posicaoDpsEstacao + 24;
			for (int i = posicaoDpsEstacao; i <= posicaoAteOndeVerifica; i++) {
				if(!(desviosBAList.contains(i)))
					if(!(trilho.get(i).nome.equals("_")))
						if 	(!(trilho.get(i).nome.equals(" ")))
							if(!(trilho.get(i).nome.contains("AB")))  return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean verificadisponibilidadeBA(Trem t, boolean taNoDesvio ) {
		//posicao do trem
		int posicao = trilho.search(t);
		if (taNoDesvio)	
			posicao += 1;
		
		//verifica se a estação está vazia
		if (trilho.get(posicao-2).nome.equals("_")) {
			int posicaoDpsEstacao = posicao-3;
			int posicaoAteOndeVerifica = posicaoDpsEstacao - 24;
			for (int i = posicaoDpsEstacao; i >= posicaoAteOndeVerifica; i--) {
				if(!(desviosABList.contains(i)))
					if(!(trilho.get(i).nome.equals("_")))
						if 	(!(trilho.get(i).nome.equals(" ")))
							if(!(trilho.get(i).nome.contains("BA")))  return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean estaNoDesvioAB(int pos) {
		if (desviosABList.contains(pos)) return true;
		return false;
	}
	
	public boolean estaNoDesvioBA(int pos) {
		if (desviosBAList.contains(pos)) return true;
		return false;
	}
	
	public boolean verificaDesvioAB(int pos) {
		if (desviosABList.contains(pos)) return true;
		return false;
	}
	
	public boolean verificaDesvioBA(int pos) {
		if (desviosBAList.contains(pos)) return true;
		return false;
	}
		
	public boolean estaNaEstacao(int pos) {
		if (estacoes.contains(pos)) return true;
		return false;
	}
	
	public void tremNaEstacao(Trem t) {
		int nDesceram = t.descemPassageiros();
		int nSubiram = t.sobemPassageiros(nDesceram);
	}
	
	public String toString() {
		String s = "(A) ";
		//for que passa por todos os trilhos
		for (int i = 0; i < trilho.numElements(); i++) {
			if(desviosBAList.contains(i))
				s+="{" + trilho.get(i).nome + "}  ";
			else if (estacoes.contains(i)) 
				if(trilho.get(i).nome.equals("_"))
					s+="[ ]\n    ";
				else s+="[~]\n   ";
		
			else if(desviosABList.contains(i))
				s+="{" + trilho.get(i).nome + "}  ";
			else 
				s+= trilho.get(i).nome+ " ";
			
		}
		s+= "(B)";
		return s;
	}
		
}
