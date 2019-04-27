import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.Queue;

public class Controladora {
	
	//Constantes
	static final int MAX_VOLUME_PACOTE = 5000;
	static final float TEMPO_PRODUCAO_PACOTE = 5;
	static final float TRANSICAO_PACOTE = 0.5F;
	static final float SEGUNDOS_TRABALHADOS_POR_DIA = 32400.0F;
	static final float SEGUNDOS_ATE_ENTREGA = 14400.0F;
	//FimConstantes
	
	// ---------------COLOQUE O CAMINHO PARA O ARQUIVO CSV AQUI:---------------------
		static final String CAMINHO_ARQUIVO = "D:\\Barcelos\\Desktop\\Terceiro período\\Sistemas Operacionais\\ProjetoDaEsteira\\src\\arquivoteste.csv";
	//-------------------------------------------------------------------------------
	
	//Variáveis
	static float tempoTotal;
	static Robo bracoMecanico = new Robo();
	static Esteira esteira = new Esteira();
	static List<Produto> pacote1, pacote2, pacote3;
	static boolean eVazio;
	//FimVariaveis
	
	
	//-----FUNÇÕES-----

	//lê os produtos do CSV, chama a função de conversao em produto e armazena todos os produtos em uma List
	public static List<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}
	
	//pega o total de produtos do arquivo
	public static int totalProdutos(List<Produto> produtos) {
		return produtos.stream().mapToInt(Produto::getTotalProdutos).sum();
	}
	
	public static int[] ordenaTotalPrazosPreenchidos(List<Produto> produtos) {
		return produtos.stream().filter(p -> p.getPrazo() != 0).mapToInt(Produto::getPrazo).sorted().toArray();
	}
	
	public static int totalTempo(List<Produto> produtos) {
		return produtos.stream().mapToInt(Produto::getPrazo).sum();
	}
	
	//-----FIM-FUNÇÕES-----
	
	//-----ALGORITMOS-----
	
	/*public static void ShortestJobFirst(List<Produto> produtos) {
		int tempoPassado = 0;
		PriorityQueue<Integer> listaDePrioridades = new PriorityQueue<Integer>();
		//int tamanho = listaDePrioridades.size();
		int manipulado = 0;
		int inserir = 0;
		while(!produtos.isEmpty() || listaDePrioridades.peek() != null) {
			while(!produtos.isEmpty() && tempoPassado >= produtos.get(0).getPrazo()) {
				inserir = produtos.get(0).getPrazo();
				listaDePrioridades.add(inserir);
				produtos.remove(0);
				System.out.println("Adicionou na lista prioridades");
			}
			if(listaDePrioridades.peek() != null) {
				manipulado = listaDePrioridades.poll();
				tempoPassado += manipulado;
			} else {
				tempoPassado += produtos.get(0).getPrazo();
			}
			System.out.println("Passou no primeiro if");
		}
		System.out.println("tempo"+tempoPassado);
	}*/
	
	public static void ShortestJobFirst(List<Produto> produtos) {
		//pegar as prioridades por tempo (em minutos)
		
		//fazer pacotes dos produtos prioritarios
		
		//
	}
	
	public void RoundRobin() {
		
	}

	public static void main(String[] args) throws IOException {
		
		List<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		int total = totalProdutos(produtosConvertidosDoCSV);
		int[] totalprazos = ordenaTotalPrazosPreenchidos(produtosConvertidosDoCSV);
		int somaprazo = totalTempo(produtosConvertidosDoCSV);
		//for(int i = 0; i < totalprazos.length-1; i++) {
			//System.out.println(totalprazos[i]);
		//}
		//System.out.println(total);
		//System.out.println(produtosConvertidosDoCSV.get(0).getFornecedor().toString());
		
		pacote1 = bracoMecanico.colocaNaEsteira("Kelton", pacote1);
		tempoTotal += TEMPO_PRODUCAO_PACOTE;
		eVazio = esteira.porNaCaixa(pacote1);
		if (!eVazio) {
			tempoTotal += TRANSICAO_PACOTE;
			//System.out.println(tempoTotal);
		}
		//System.out.println(tempoTotal);
		Collections.sort(produtosConvertidosDoCSV);
		for(int i = 0; i < produtosConvertidosDoCSV.size(); i++) {
			System.out.println(produtosConvertidosDoCSV.get(i).getFornecedor());
		}
		ShortestJobFirst(produtosConvertidosDoCSV);
		
		System.out.println("prazo real"+somaprazo);
		
	}
}
