package modelo;

public class Dado {
	private int endereco;
	private int conteudo;
	
	
	public Dado( int endereco, int conteudo) {
		super();
		this.endereco = endereco;
		this.conteudo = conteudo;
	}
	
	public int getEndereco() {
		return endereco;
	}
	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}
	public int getConteudo() {
		return conteudo;
	}
	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}
}
