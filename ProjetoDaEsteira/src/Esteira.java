import java.util.LinkedList;
public class Esteira {
	public Esteira() {
		
	}
	
	public boolean porNaCaixa(LinkedList<Produto> pacote) {
		if(pacote.size() == 0) {
			System.out.println("Pacote vazio");
			return false;
		} else {
			System.out.println("Colocou o pacote de "+ pacote.get(0).getFornecedor() +" na caixa");
			pacote.removeAll(pacote);
			return true;
		}
	}
}
