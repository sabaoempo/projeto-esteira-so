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
	static ArrayList<Produto> aux = new ArrayList<Produto>();
	static ArrayList<Produto> aux2 = new ArrayList<Produto>();
	static List<Produto> pacote2 = new ArrayList<Produto>();
	static List<Produto> pacote3 = new ArrayList<Produto>();
	public static ArrayList<Produto> lerProdutos(String arquivo, String separador) throws IOException {
		return (ArrayList<Produto>) Files.readAllLines(new File(arquivo).toPath(), StandardCharsets.UTF_8).stream().skip(1)
				.map(p -> Produto.converterLinhaCSVEmProduto(p, separador)).collect(Collectors.toList());
	}
	
//	public static void RoundRobin2() throws IOException {
//		ArrayList<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
//		ArrayList<Produto> prioridade0=new ArrayList<Produto>();
//		ArrayList<Produto> aux = new ArrayList<Produto>();
//		Produto p=new Produto();
//		Collections.sort(produtosConvertidosDoCSV);
//		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
//		if(produtosConvertidosDoCSV.get(i).getPrazo()==0) {
//			prioridade0.add(produtosConvertidosDoCSV.get(i));
//			produtosConvertidosDoCSV.remove(i);
//		}
//		p=produtosConvertidosDoCSV.get(i);
//		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
//		double resto=0;
//		double quant=0;
//		if(totalVolume<MAX_VOLUME_PACOTE) {
//			List<Produto> pacote1  = new ArrayList<Produto>();
//			if(p.getPrazo()!=0) 
//			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
//			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
//		}
//		if(totalVolume>MAX_VOLUME_PACOTE) {
//			List<Produto> pacote1  = new ArrayList<Produto>();
//			resto=totalVolume-MAX_VOLUME_PACOTE;
//			quant=resto/p.getVolumePorProduto();
//			p.setTotalProdutos((int)quant);
//			
//			if(p.getPrazo()!=0) {
//				System.out.println("PASEEI AQUI 1");
//			aux.add(p);
//			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
//			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
//			}}
//	//	p=aux.get(i);
//		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
//		double resto2=0;
//		double quant2=0;
//		if(totalVolume>MAX_VOLUME_PACOTE) {
//			
//			System.out.println("PASEEI AQUI 2");
//			resto2=totalVolume-MAX_VOLUME_PACOTE;
//			quant2=resto2/p.getVolumePorProduto();
//			p.setTotalProdutos((int)quant2);
//			aux.add(p);
//			bracoMecanico.adicionaNoPacoteRR(bracoMecanico.colocaNaEsteira(aux.get(i).getFornecedor(),pacote1), aux.get(i));
//			System.out.println("TAMANHO PACOTE  "+pacote1.size());
//			//bracoMecanico.adicionaNoPacoteRR(pacote1, aux.get(j));
//		}
//		
//		//p=prioridade0.get(i);
//		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
//		double resto3=0;
//		double quant3=0;
//		if(totalVolume>MAX_VOLUME_PACOTE) {
//			System.out.println("PASEEI AQUI 3");
//			resto3=totalVolume-MAX_VOLUME_PACOTE;
//			quant3=resto3/p.getVolumePorProduto();
//			p.setTotalProdutos((int)quant3);
//			aux.add(p);
//			bracoMecanico.adicionaNoPacoteRR(bracoMecanico.colocaNaEsteira(prioridade0.get(i).getFornecedor(),pacote1), prioridade0.get(i));
//			//bracoMecanico.adicionaNoPacoteRR(pacote1, prioridade0.get(i));
//		}
//		
//		}
//		
//	}
	
public static void primeiroPasso2(Produto p,ArrayList<Produto>aux) {
		
		//Produto p=new Produto();
		//for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		//p=produtosConvertidosDoCSV.get(i);
		totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
		double resto=0;
		double quant=0;
		if(totalVolume<MAX_VOLUME_PACOTE) {
			List<Produto> pacote1  = new ArrayList<Produto>();
			if(p.getPrazo()!=0) 
				System.out.println("SO PRA SABER MSM");
			bracoMecanico.colocaNaEsteira(p.getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, p);
		}
		if(totalVolume>MAX_VOLUME_PACOTE) {
			List<Produto> pacote1  = new ArrayList<Produto>();
			resto=totalVolume-MAX_VOLUME_PACOTE;
			quant=resto/p.getVolumePorProduto();
			p.setTotalProdutos((int)quant);
			
			if(p.getPrazo()!=0) {
				System.out.println("PASEEI AQUI 1");
			aux.add(p);
			bracoMecanico.colocaNaEsteira(p.getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, p);
			}}
//		if(produtosConvertidosDoCSV.get(i).getTotalProdutos()!=0) {
//			
//			primeiroPasso(aux,produtosConvertidosDoCSV);
//		}
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
			if(p.getPrazo()!=0) 
				System.out.println("SO PRA SABER MSM");
			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
		}
		if(totalVolume>MAX_VOLUME_PACOTE) {
			List<Produto> pacote1  = new ArrayList<Produto>();
			resto=totalVolume-MAX_VOLUME_PACOTE;
			quant=resto/p.getVolumePorProduto();
			p.setTotalProdutos((int)quant);
			
			if(p.getPrazo()!=0) {
				System.out.println("PASEEI AQUI 1");
			aux.add(p);
			bracoMecanico.colocaNaEsteira(produtosConvertidosDoCSV.get(i).getFornecedor(), pacote1);
			bracoMecanico.adicionaNoPacoteRR(pacote1, produtosConvertidosDoCSV.get(i));
			}}
//		if(produtosConvertidosDoCSV.get(i).getTotalProdutos()!=0) {
//			
//			primeiroPasso(aux,produtosConvertidosDoCSV);
//		}
		}
	
	}
	
	public static void RoundRobin() throws IOException {
		ArrayList<Produto> produtosConvertidosDoCSV = lerProdutos(CAMINHO_ARQUIVO, ";");
		ArrayList<Produto> prioridade0=new ArrayList<Produto>();
	//	ArrayList<Produto> aux = new ArrayList<Produto>();
		Produto p=new Produto();
		Collections.sort(produtosConvertidosDoCSV);
		for(int i=0; i<produtosConvertidosDoCSV.size();i++) {
		if(produtosConvertidosDoCSV.get(i).getPrazo()==0) {
			prioridade0.add(produtosConvertidosDoCSV.get(i));
			produtosConvertidosDoCSV.remove(i);
		}

		}

		
		primeiroPasso(produtosConvertidosDoCSV,aux);
		//System.out.println(aux.toString());
		for(int j = 0; j<aux.size();j++) {
			if(aux.get(j).getTotalProdutos()!=0) {
				
			System.out.println("\n\n\nTENTATIVA " + j);
				primeiroPasso2(aux.get(j),aux2);
			}}
//		System.out.println("\n\n\nSÃO OS SEGUNDOS\n\n");
//			primeiroPasso(aux,aux2);
//			aux.clear();
//			System.out.println("\n\nSão OS TERCEIROS\n\n");
//			primeiroPasso(aux2,aux);
//			aux2.clear();
//			System.out.println("\n\nSão OS QUARTOS\n\n");
//			primeiroPasso(aux,aux2);
//			aux.clear();
//			System.out.println("\n\nSão OS QUINTOS\n\n");
//			primeiroPasso(aux2,aux);
//			aux2.clear();
//			System.out.println("\n\nSão OS SEI LA\n\n");
//			primeiroPasso(aux,aux2);
//			aux.clear();
//			System.out.println("\n\nSão OS SEI LA\n\n");
//			primeiroPasso(aux2,aux);
//			p=aux.get(j);
//			totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
//			double resto2=0;
//			double quant2=0;
//			if(totalVolume>MAX_VOLUME_PACOTE) {
//				List<Produto> pacote1  = new ArrayList<Produto>();
//				System.out.println("PASEEI AQUI 3");
//				resto2=totalVolume-MAX_VOLUME_PACOTE;
//				quant2=resto2/p.getVolumePorProduto();
//				p.setTotalProdutos((int)quant2);
//				aux.add(p);
//				bracoMecanico.colocaNaEsteira(aux.get(j).getFornecedor(), pacote1);
//				bracoMecanico.adicionaNoPacoteRR(pacote1, aux.get(j));
//				
//				//System.out.println("TAMANHO PACOTE  "+pacote1.size());
//				//bracoMecanico.adicionaNoPacoteRR(pacote1, aux.get(j));
		//	}
		
		for(int i=0;i<prioridade0.size();i++) {
			
			p=prioridade0.get(i);
			totalVolume=p.getVolumePorProduto()*p.getTotalProdutos();
			double resto=0;
			double quant=0;
			if(totalVolume>MAX_VOLUME_PACOTE) {
				List<Produto> pacote1  = new ArrayList<Produto>();
				System.out.println("PASEEI AQUI 4");
				resto=totalVolume-MAX_VOLUME_PACOTE;
				quant=resto/p.getVolumePorProduto();
				p.setTotalProdutos((int)quant);
				aux.add(p);
				bracoMecanico.colocaNaEsteira(prioridade0.get(i).getFornecedor(), pacote1);
				bracoMecanico.adicionaNoPacoteRR(pacote1, prioridade0.get(i));
				//bracoMecanico.adicionaNoPacoteRR(bracoMecanico.colocaNaEsteira(prioridade0.get(i).getFornecedor(),pacote1), prioridade0.get(i));
				//bracoMecanico.adicionaNoPacoteRR(pacote1, prioridade0.get(i));
			}
		}
	
		
	}

		

	public static void main(String[] args) throws IOException {
		
		RoundRobin();

	}
}
