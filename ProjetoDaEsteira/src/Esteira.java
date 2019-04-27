
import java.util.List;
public class Esteira {
	public Esteira() {
		
	}
	
	public boolean porNaCaixa(List<Produto> pacote) {
		if(pacote.size() == 0) {
			System.out.println("Pacote vazio");
			return true;
		} else {
			System.out.println("Colocou o pacote de "+ pacote.get(0).getFornecedor() +" na caixa");
			pacote.removeAll(pacote);
			return false;
		}
	}
}
