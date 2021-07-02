public class Trem {
	public int passageiros;
	public String nome;
	
	public int atraso;
	
	public Trem(String nome) {
		this.nome = nome;
		this.passageiros = (int) ( 1 + Math.random() * 50);
		
		this.atraso = 0;
	}
	
	public int sobemPassageiros(int nDesceram) {
		int nAnterior = passageiros;
		int assentosLivres = 50 - passageiros;
		
		if(assentosLivres != 0)
			if(assentosLivres >= 10)
			passageiros += (int) (1 + Math.random() * 10);
			else 
			passageiros += (int) (1 + Math.random() * assentosLivres);
		
		int nSubiram = passageiros - nAnterior;
		if ((nDesceram%2 == 1 && nSubiram%2 == 0) || (nDesceram%2 == 0 && nSubiram%2 == 1))
			passageiros -= 1;	
		atraso = (((passageiros - nAnterior) + nDesceram )/2);
		if (atraso == 0)
			atraso = 1;
		return (passageiros - nAnterior);
	} 
	
	public int descemPassageiros() {
		int nAnterior = passageiros;
		if (passageiros >= 10) {
			int assentosMinimos = passageiros-10;
			int a =  (int) (1 + Math.random() * assentosMinimos-1);
			int b = (int) (1 + Math.random() * 10);
			if (assentosMinimos < 10)
				passageiros -= a;	
			else 
				passageiros -= (int) (1 + Math.random() * 10);
		}
		return nAnterior - passageiros;
	}
	

	
	@Override
	public String toString() {
		return nome;
	}
}
