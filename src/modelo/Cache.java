package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class Cache {
	private int maxPalavras;
	private int quantidadeLinhas;
	private int numeroBlocosMemoria;
	private int tipoMapeamento;
	private int numeroConjuntos;
	private int tipoSubstituicao;
	private int contadorFila = 0;

	private HashMap<Integer, Bloco> linhas;
	private HashMap<Integer, Bloco> memoriaPrincipal;

	public Cache(int maxPalavras, int quantidadeLinhas, int numeroBlocosMemoria, int tipoMapeamento,
			int numeroConjuntos, int tipoSubstituicao) {
		this.maxPalavras = maxPalavras;
		this.quantidadeLinhas = quantidadeLinhas;
		this.numeroBlocosMemoria = numeroBlocosMemoria;
		this.tipoMapeamento = tipoMapeamento;
		this.numeroConjuntos = numeroConjuntos;
		this.tipoSubstituicao = tipoSubstituicao;
		linhas = new HashMap<Integer, Bloco>();
		memoriaPrincipal = new HashMap<Integer, Bloco>();

		for (int i = 0; i < numeroBlocosMemoria; i++) {
			memoriaPrincipal.put(i, new Bloco(i, maxPalavras));
		}
		for (int i = 0; i < quantidadeLinhas; i++) {
			linhas.put(i, new Bloco(-1, maxPalavras));
		}
	}

	public void ler(int endereco) {
		int valorBloco = endereco / maxPalavras;

		for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
			Bloco b = pair.getValue();
			if (b.getNumeroBloco() == valorBloco) {
				System.out.println("HIT linha " + b.getNumeroBloco());
				b.setContador(b.getContador()+1);
				return;
			}
		}

		if (tipoMapeamento == 1) {
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);
			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco());
				}else {
					
				}
			}
			linhas.replace(valorBloco % quantidadeLinhas, memoriaPrincipal.get(valorBloco));
		} else if (tipoSubstituicao == 1) {
			Random gerador = new Random();
			int linhaAleatoria = gerador.nextInt(quantidadeLinhas);
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco());
				}else {
					
				}
			}

			linhas.replace(linhaAleatoria, memoriaPrincipal.get(valorBloco));
		} else if (tipoSubstituicao == 2) {
			int menor = Integer.MAX_VALUE;
			int linhaAlterada = 0;
			for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
				Bloco bloco = pair.getValue();
				if (bloco.getNumeroFila() < menor) {
					menor = bloco.getNumeroFila();
					linhaAlterada = pair.getKey();
				}
			}
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco());
				}else {
					
				}
			}
			linhas.replace(linhaAlterada, memoriaPrincipal.get(valorBloco));
		} else if (tipoSubstituicao == 3) {
			int menor = Integer.MAX_VALUE;
			int linhaAlterada = 0;
			for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
				Bloco bloco = pair.getValue();
				if (bloco.getNumeroFila() < menor) {
					menor = bloco.getContador();
					linhaAlterada = pair.getKey();
					bloco.setContador(bloco.getContador() + 1);
				}
			}
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				System.out.println(" _> Bloco " + linhas.get(linhaAlterada).getNumeroBloco() + " Substituido");
			}

			linhas.replace(linhaAlterada, memoriaPrincipal.get(valorBloco));
		}

	}

	public void escrever(int endereco, int conteudo) {
		int valorBloco = endereco / maxPalavras;

		for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
			Bloco b = pair.getValue();
			if (b.getNumeroBloco() == valorBloco) {
				System.out.println(
						"HIT linha " + b.getNumeroBloco() + " -> novo valor endereco " + endereco + "=" + conteudo);
				b.setContador(b.getContador()+1);
				b.addPalavra(endereco, new Dado(endereco, conteudo));
				return;
			}
		}
		//miss 
		if (tipoMapeamento == 1) {
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);
			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()
							+ " Substituido" + " -> novo valor endereco " + endereco + "=" + conteudo);
				}else {
					System.out.println(" -> novo valor endereco " + endereco + "=" + conteudo);
				}
				
			}
			if (linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco() == -1) {

			} else {
				memoriaPrincipal.replace(valorBloco, linhas.get(valorBloco % quantidadeLinhas));
			}

			linhas.replace(valorBloco % quantidadeLinhas, memoriaPrincipal.get(valorBloco));
			linhas.get(valorBloco % quantidadeLinhas).addPalavra(endereco, new Dado(endereco, conteudo));
		} else if (tipoSubstituicao == 1) {
			Random gerador = new Random();
			int linhaAleatoria = gerador.nextInt(quantidadeLinhas);
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()
							+ " Substituido" + " -> novo valor endereco " + endereco + "=" + conteudo);
				}else {
					System.out.println(" -> novo valor endereco " + endereco + "=" + conteudo);
				}
			}

			if (linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco() == -1) {

			} else {
				memoriaPrincipal.replace(valorBloco, linhas.get(linhaAleatoria));
			}

			linhas.replace(linhaAleatoria, memoriaPrincipal.get(valorBloco));
			linhas.get(linhaAleatoria).addPalavra(endereco, new Dado(endereco, conteudo));
		} else if (tipoSubstituicao == 2) {
			int menor = Integer.MAX_VALUE;
			int linhaAlterada = 0;
			for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
				Bloco bloco = pair.getValue();
				if (bloco.getNumeroFila() < menor) {
					menor = bloco.getNumeroFila();
					linhaAlterada = pair.getKey();
				}
			}
			System.out.print("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()
							+ " Substituido" + " -> novo valor endereco " + endereco + "=" + conteudo);
				}else {
					System.out.println(" -> novo valor endereco " + endereco + "=" + conteudo);
				}
			}

			if (linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco() == -1) {

			} else {
				memoriaPrincipal.replace(valorBloco, linhas.get(linhaAlterada));
			}

			linhas.replace(linhaAlterada, memoriaPrincipal.get(valorBloco));
			linhas.get(linhaAlterada).setNumeroFila(contadorFila);
			contadorFila++;
			linhas.get(linhaAlterada).addPalavra(endereco, new Dado(endereco, conteudo));
		} else if (tipoSubstituicao == 3) {
			int menor = Integer.MAX_VALUE;
			int linhaAlterada = 0;
			for (Entry<Integer, Bloco> pair : linhas.entrySet()) {
				Bloco bloco = pair.getValue();
				if (bloco.getNumeroFila() < menor) {
					menor = bloco.getContador();
					linhaAlterada = pair.getKey();
					bloco.setContador(bloco.getContador() + 1);
				}
			}
			System.out.println("MISS _> Alocado na linha " + valorBloco % quantidadeLinhas);

			if (linhas.get(valorBloco % quantidadeLinhas) != null) {
				if(linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()!=-1) {
					System.out.println(" _> Bloco " + linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco()
							+ " Substituido" + " -> novo valor endereco " + endereco + "=" + conteudo);
				}else {
					System.out.println(" -> novo valor endereco " + endereco + "=" + conteudo);
				}
			}

			if (linhas.get(valorBloco % quantidadeLinhas).getNumeroBloco() == -1) {

			} else {
				memoriaPrincipal.replace(valorBloco, linhas.get(linhaAlterada));
			}
			
			linhas.replace(linhaAlterada, memoriaPrincipal.get(valorBloco));
			linhas.get(linhaAlterada).addPalavra(endereco, new Dado(endereco, conteudo));
		}
	}

	public void show() {
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("CACHE L1");
		System.out.println("Linha - Bloco - Endereço - Conteúdo");
		for (Entry<Integer, Bloco> pairLinhas : linhas.entrySet()) {
			Bloco bloco = pairLinhas.getValue();
			for (Entry<Integer, Dado> pairPalavras : bloco.getPalavras().entrySet()) {
				Dado d = pairPalavras.getValue();
				System.out.println(pairLinhas.getKey() + " - " + bloco.getNumeroBloco() + " - " + d.getEndereco()
						+ " - " + d.getConteudo());
			}
		}

		System.out.println("MEMORIA PRINCIPAL");
		System.out.println("Bloco - Endereço - Conteúdo");
		for (Entry<Integer, Bloco> pairMemoria : memoriaPrincipal.entrySet()) {
			Bloco bloco = pairMemoria.getValue();
			for (Entry<Integer, Dado> pairPalavras : bloco.getPalavras().entrySet()) {
				Dado d = pairPalavras.getValue();
				System.out.println(bloco.getNumeroBloco() + " - " + d.getEndereco() + " - " + d.getConteudo());
			}
		}
	}

}
