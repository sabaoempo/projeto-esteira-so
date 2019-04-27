import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Dinesh Krishnan
 *
 */

public class RoundRobinTest {
	static final String CAMINHO_ARQUIVO = "C:\\Users\\Karol - PC\\Downloads\\projeto-esteira-so-master\\ProjetoDaEsteira\\src\\arquivoteste.csv";
	static final int MAX_VOLUME_PACOTE = 5000;
	static Robo bracoMecanico = new Robo();
	public static List<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}
	public static List<Produto> getResources() throws IOException {
		List<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		List<Produto> resourceList = new ArrayList<Produto>();
		List<Produto> pacote1 = new ArrayList<Produto>();
		List<Produto> pacote2 = new ArrayList<Produto>();
		List<Produto> pacote3 = new ArrayList<Produto>();
		

		Collections.sort(produtosConvertidosDoCSV);
		bracoMecanico.adicionaNoPacote(pacote1,produtosConvertidosDoCSV.get(0) , MAX_VOLUME_PACOTE);

//		resourceList.add(produtosConvertidosDoCSV.get(0));
//		resourceList.add(produtosConvertidosDoCSV.get(1));
//		resourceList.add(produtosConvertidosDoCSV.get(2));
		for(int i = 0; i < produtosConvertidosDoCSV.size(); i++) {
			bracoMecanico.adicionaNoPacote(pacote1,produtosConvertidosDoCSV.get(i) , MAX_VOLUME_PACOTE);
			bracoMecanico.adicionaNoPacote(pacote2,produtosConvertidosDoCSV.get(i+1) , MAX_VOLUME_PACOTE);
			bracoMecanico.adicionaNoPacote(pacote3,produtosConvertidosDoCSV.get(i+2) , MAX_VOLUME_PACOTE);
		}
		return pacote1;
	}

	public static void main(String[] args) throws IOException {

		// creating container with resources
		RoundRobin<Produto> rr = new RoundRobinImpl<Produto>(getResources());
		
		try {

			// selecting the resource
			System.out.println(rr.select().getFornecedor());
			System.out.println(rr.select().getFornecedor());
			System.out.println(rr.select().getFornecedor());

			System.out.println(rr.select().getFornecedor());
			System.out.println(rr.select().getFornecedor());
			System.out.println(rr.select().getFornecedor());

		} catch (RoundRobinException e) {

			System.out.println(e.getMessage());
		}

	}
}
