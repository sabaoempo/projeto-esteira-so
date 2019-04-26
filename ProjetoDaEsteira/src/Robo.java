import java.util.LinkedList;

public class Robo {
	public Robo() {
		
	}
	
	public void empacotar(Produto produto) {
		//pega o produto e coloca dentro de um pacote
		System.out.println("Produto"+ produto.getFornecedor() +"empacotado!");
	}
	
	public static void adicionaNoPacote(LinkedList<Produto> pacote, Produto produto, int MAX_VOLUME_PACOTE) {
		int soma = 0;
		for (int i = 0; i < pacote.size(); i++) {
			soma += pacote.get(i).getVolumePorProduto();
		}
		if (soma + produto.getVolumePorProduto() < MAX_VOLUME_PACOTE) {
			pacote.add(produto);
		}
	}

	public LinkedList<Produto> colocarNaEsteira(String fornecedor, LinkedList<Produto> pacote) {
		// pega um pacote vazio e coloca na esteira;
		pacote = new LinkedList<Produto>();
		System.out.println("Pacote colocado na esteira");
		return pacote;
	}
	
}
