import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Controladora {

	// Constantes
	static final int MAX_VOLUME_PACOTE = 5000;
	static final float TEMPO_PRODUCAO_PACOTE = 5;
	static final float TRANSICAO_PACOTE = 0.5F;
	static final float SEGUNDOS_TRABALHADOS_POR_DIA = 32400.0F;
	static final float SEGUNDOS_ATE_ENTREGA = 14400.0F;
	// FimConstantes

	// ---------------COLOQUE O CAMINHO PARA O ARQUIVO CSV
	// AQUI:---------------------
	static final String CAMINHO_ARQUIVO = "D:\\Barcelos\\Desktop\\Terceiro período\\Sistemas Operacionais\\ProjetoDaEsteira\\src\\arquivoteste.csv";
	// -------------------------------------------------------------------------------

	// Variáveis
	static float tempoTotal;
	static Robo bracoMecanico = new Robo();
	static Esteira esteira = new Esteira();
	static List<Produto> pacote1, pacote2, pacote3;
	static boolean eVazio;
	// FimVariaveis

	// -----FUNÇÕES-----

	// lê os produtos do CSV, chama a função de conversao em produto e armazena
	// todos os produtos em uma List
	public static List<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}

	// pega o total de produtos do arquivo
	public static int totalProdutos(List<Produto> produtos) {
		return produtos.stream().mapToInt(Produto::getTotalProdutos).sum();
	}

	// pega o total de prazos de todos os produtos;
	public static int totalTempo(List<Produto> produtos) {
		return produtos.stream().mapToInt(Produto::getPrazo).sum();
	}

	// -----FIM-FUNÇÕES-----

	// -----ALGORITMOS-----

	public static void ShortestJobFirst(List<Produto> produtos) {
		//pegar as prioridades por tempo (em minutos)
		Collections.sort(produtos);
		int prazo;
		boolean verifica;
		
		//fazer pacotes dos produtos prioritarios
		pacote1 = new LinkedList<Produto>();
		
		for(int i = 0; i<produtos.size(); i++) {
			prazo = produtos.get(i).getPrazo();
			pacote1.removeAll(pacote1);
			bracoMecanico.colocaNaEsteira(produtos.get(i).getFornecedor(), pacote1);
			if(prazo != 0) {
				while(produtos.get(i).getTotalProdutos() != 0) {
					verifica = bracoMecanico.adicionaNoPacote(pacote1, produtos.get(i), MAX_VOLUME_PACOTE);
					if(verifica == false) {
						pacote1.removeAll(pacote1);
						bracoMecanico.colocaNaEsteira(produtos.get(i).getFornecedor(), pacote1);
					} else
						produtos.get(i).setTotalProdutos(produtos.get(i).getTotalProdutos()-1);
				}
			}
		}
		
		
	}

	public void RoundRobin() {

	}

	public static void main(String[] args) throws IOException {

		List<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		// int total = totalProdutos(produtosConvertidosDoCSV);
		//int somaprazo = totalTempo(produtosConvertidosDoCSV);
		// for(int i = 0; i < totalprazos.length-1; i++) {
		// System.out.println(totalprazos[i]);
		// }
		// System.out.println(total);
		// System.out.println(produtosConvertidosDoCSV.get(0).getFornecedor().toString());

		/*pacote1 = bracoMecanico.colocaNaEsteira("Kelton", pacote1);
		tempoTotal += TEMPO_PRODUCAO_PACOTE;
		eVazio = esteira.porNaCaixa(pacote1);
		if (!eVazio) {
			tempoTotal += TRANSICAO_PACOTE;
			// System.out.println(tempoTotal);
		}*/
		// System.out.println(tempoTotal);
		//Collections.sort(produtosConvertidosDoCSV);
		//for (int i = 0; i < produtosConvertidosDoCSV.size(); i++) {
			//System.out.println(produtosConvertidosDoCSV.get(i).getFornecedor());
		//}
		ShortestJobFirst(produtosConvertidosDoCSV);

		//System.out.println("prazo real" + somaprazo);

	}
}
