
public class Produto {

	private String fornecedor;
	private int totalProdutos;
	private int volumePorProduto;
	private int prazo;

	public Produto() {

	}

	public Produto(String fornecedor, int totalProdutos, int volumePorProduto, int prazo) {
		this.fornecedor = fornecedor;
		this.totalProdutos = totalProdutos;
		this.volumePorProduto = volumePorProduto;
		this.prazo = prazo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public int getVolumePorProduto() {
		return volumePorProduto;
	}

	public void setVolumePorProduto(int volumePorProduto) {
		this.volumePorProduto = volumePorProduto;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	
	

}