import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class RoundRobinTest {
	static final String CAMINHO_ARQUIVO = "C:\\Users\\Karol - PC\\Downloads\\projeto-esteira-so-master\\ProjetoDaEsteira\\src\\arquivoteste.csv";
	static final int MAX_VOLUME_PACOTE = 5000;
	static Robo bracoMecanico = new Robo();
	static double totalVolume = 0;
	static List<Produto> pacote1 = new ArrayList<Produto>();
	static List<Produto> pacote2 = new ArrayList<Produto>();
	static List<Produto> pacote3 = new ArrayList<Produto>();
	public static ArrayList<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return (ArrayList<Produto>) Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}
	
	
	public static void RoundRobin() throws IOException {
		ArrayList<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		ArrayList<Produto> prioridade0=new ArrayList<Produto>();
		ArrayList<Produto> aux = new ArrayList<Produto>();
		Produto p=new Produto();
		Collections.sort(produtosConvertidosDoCSV);
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		if(produtosConvertidosDoCSV.get(i).getPrazo()==0) {
			prioridade0.add(produtosConvertidosDoCSV.get(i));
			produtosConvertidosDoCSV.remove(i);
		}}
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		p=produtosConvertidosDoCSV.get(i);
		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
		double resto=0;
		double quant=0;
		if(totalVolume<MAX_VOLUME_PACOTE) {
			
			if(p.getPrazo()!=0) 
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
		}
		if(totalVolume>MAX_VOLUME_PACOTE) {
		
			resto=totalVolume-MAX_VOLUME_PACOTE;
			quant=resto/p.getVolumePorProduto();
			p.setTotalProdutos((int)quant);
			
			if(p.getPrazo()!=0) {
				System.out.println("PASEEI AQUI 1");
			aux.add(p);
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
			}}}
		
		
		//System.out.println(aux.toString());
		for(int j = 0; j<aux.size();j++) {
			p=aux.get(j);
			totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
			double resto2=0;
			double quant2=0;
			if(totalVolume>MAX_VOLUME_PACOTE) {
				System.out.println("PASEEI AQUI 2");
				resto2=totalVolume-MAX_VOLUME_PACOTE;
				quant2=resto2/p.getVolumePorProduto();
				p.setTotalProdutos((int)quant2);
				aux.add(p);
				bracoMecanico.adicionaNoPacoteRR(pacote1, aux.get(j));
			}
		}	
		for(int i=0;i<prioridade0.size();i++) {
			p=prioridade0.get(i);
			totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
			double resto=0;
			double quant=0;
			if(totalVolume>MAX_VOLUME_PACOTE) {
				System.out.println("PASEEI AQUI 3");
				resto=totalVolume-MAX_VOLUME_PACOTE;
				quant=resto/p.getVolumePorProduto();
				p.setTotalProdutos((int)quant);
				aux.add(p);
				bracoMecanico.adicionaNoPacoteRR(pacote1, prioridade0.get(i));
			}
		}
}

		

	public static void main(String[] args) throws IOException {
		
		RoundRobin();

	}
}
