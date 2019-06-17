package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Bloco {
	private int numeroBloco;
	private int maxPalavras;
	private int numeroFila;
	private int contador;
	private HashMap<Integer, Dado> palavras;
	public Bloco(int numeroBloco,int maxPalavras) {
		this.numeroBloco = numeroBloco;
		this.maxPalavras = maxPalavras;
		palavras = new HashMap<Integer,Dado>();
		for(int i =0; i<maxPalavras;i++) {
			palavras.put(i+(numeroBloco*maxPalavras),new Dado(i+(numeroBloco*maxPalavras),0));
		}
	}
	
	public void addPalavra(int endereco,Dado dado) {
			palavras.replace(endereco, dado);
		
	}

	public int getNumeroBloco() {
		return numeroBloco;
	}

	public void setNumeroBloco(int numeroBloco) {
		this.numeroBloco = numeroBloco;
	}

	public int getMaxPalavras() {
		return maxPalavras;
	}

	public void setMaxPalavras(int maxPalavras) {
		this.maxPalavras = maxPalavras;
	}

	public int getNumeroFila() {
		return numeroFila;
	}

	public void setNumeroFila(int numeroFila) {
		this.numeroFila = numeroFila;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public HashMap<Integer,Dado> getPalavras() {
		return palavras;
	}
}
