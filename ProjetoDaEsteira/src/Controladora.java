import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Controladora {
	static final int MAX_VOLUME_PACOTE = 5000;
	static final float TEMPO_PRODUCAO_PACOTE = 5;
	static final float TRANSICAO_PACOTE = 0.5F;
	static float tempoTotal;
	static Robo bracoMecanico = new Robo();
	static Esteira esteira = new Esteira();
	static LinkedList<Produto> pacote1, pacote2, pacote3;
	static LinkedList<Produto[]> produtos = new LinkedList<Produto[]>();
	static boolean eVazio;

	public static void main(String[] args) throws IOException {
		pacote1 = bracoMecanico.colocarNaEsteira("Kelton", pacote1);
		tempoTotal += TEMPO_PRODUCAO_PACOTE;
		eVazio = esteira.porNaCaixa(pacote1);
		if (!eVazio) {
			tempoTotal += TRANSICAO_PACOTE;
			System.out.println(tempoTotal);
		}
		//mude o caminho do arquivo para o local do arquivo no seu computador
		Files.lines(Paths.get(
				"D:\\Barcelos\\Desktop\\Terceiro período\\Sistemas Operacionais\\ProjetoDaEsteira\\src\\arquivoteste.csv"))
				.skip(1).map(line -> line.split(";")).map(col -> new Produto(col[0], Integer.parseInt(col[1]),
						Integer.parseInt(col[2]), Integer.parseInt(col[3])));
		System.out.println(produtos.size());
	}
}
