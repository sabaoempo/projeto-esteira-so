import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class RoundRobinImpl {
	static final String CAMINHO_ARQUIVO = "C:\\Users\\Karol\\Desktop\\so\\projeto-esteira-so-karol\\ProjetoDaEsteira\\src\\arquivoteste.csv";
	static final int MAX_VOLUME_PACOTE = 5000;
	static Robo bracoMecanico = new Robo();
	static double totalVolume = 0;
	static ArrayList<Produto> aux = new ArrayList<Produto>();
	static ArrayList<Produto> prioridade0=new ArrayList<Produto>();
	public static ArrayList<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return (ArrayList<Produto>) Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}
	
	public static void primeiroPasso(ArrayList<Produto> produtosConvertidosDoCSV,ArrayList<Produto>aux) {
		
		Produto p=new Produto();
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		p=produtosConvertidosDoCSV.get(i);
		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
		double resto=0;
		double quant=0;
		if(totalVolume<MAX_VOLUME_PACOTE) {
			List<Produto> pacote1  = new ArrayList<Produto>();
			if(produtosConvertidosDoCSV.get(i).getPrazo()!=0) 
				System.out.println("PRAZO: " + produtosConvertidosDoCSV.get(i).getPrazo());
				System.out.println("SO PRA SABER MSM\n");
			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
			produtosConvertidosDoCSV.remove(i);
		}
		if(totalVolume>MAX_VOLUME_PACOTE) {
			List<Produto> pacote1  = new ArrayList<Produto>();
			resto=totalVolume-MAX_VOLUME_PACOTE;
			quant=resto/p.getVolumePorProduto();
			p.setTotalProdutos((int)quant);
			
			if(produtosConvertidosDoCSV.get(i).getPrazo()!=0) {
				System.out.println("PASEEI AQUI 1");
			aux.add(p);
			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
			}}
		}
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
			if(produtosConvertidosDoCSV.get(i).getTotalProdutos()!=0) {
				System.out.println("\n\n________________________________________________\n\n");
				primeiroPasso(produtosConvertidosDoCSV,aux);
			}
		}
	}
	
	public static void RoundRobin() throws IOException {
		ArrayList<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		Produto p=new Produto();
		Collections.sort(produtosConvertidosDoCSV);
		
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		if(produtosConvertidosDoCSV.get(i).getPrazo()==0) {
			prioridade0.add(produtosConvertidosDoCSV.get(i));
			produtosConvertidosDoCSV.remove(i);
		}
		}
		
		primeiroPasso(produtosConvertidosDoCSV,aux);
		
		primeiroPasso(prioridade0,aux);

		
	}

		

	public static void main(String[] args) throws IOException {
		
		RoundRobin();

	}
}
