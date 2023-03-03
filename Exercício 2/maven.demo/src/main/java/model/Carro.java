package model;

public class Carro {
	private int codigo;
	private String nome;
	private String cor;
	private float preco;
	
	public Carro() {
		this.codigo = -1;
		this.nome = "";
		this.cor = "";
		this.preco = '*';
	}
	
	public Carro(int codigo, String nome, String cor, float preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.cor = cor;
		this.preco = preco;
	}

	

	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public float getPreco() {
		return this.preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome do carro=" + nome + ", cor=" + cor + ", preço= R$" + preco + "]";
	}	
}
