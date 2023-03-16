package model;

public class Carro {
	private int codigo;
	private String nome;
	private String descricao;
	private String cor;
	private float preco;
	static int codigoR=0;
	public Carro() {
		this.codigo = -1;
		this.nome = "";
		this.descricao = "";
		this.cor = "";
		this.preco= 0;
	}
	
	public Carro(int codigo, String nome, String cor,String descricao, float preco) {
	
		this.codigo = codigo;
		this.nome = nome;
		this.cor = cor;
		this.descricao = descricao;
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

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
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
