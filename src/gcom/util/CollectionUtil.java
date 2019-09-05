package gcom.util;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Classe com métodos úteis para manipular uma Collection
 * 
 * 
 * @author Hugo Amorim
 * @data 14/10/2010	
 */
public class CollectionUtil {
	
	/*
	 * Utilizar como exemplos:
	 * 
	 * - atualizarParmsOS no RepositorioCobrancaHBM
	 * 
	 * - pesquisarServicoTipoPorRA no RepositorioOrdemServicoHBM
	 */
	public static <T> List<List<T>> particao(List<T> list, int size) {

		if (list == null)
			throw new NullPointerException("Lista precisa esta preenchida");
		if (!(size > 0))
			throw new IllegalArgumentException("Tamanho retorno precisa ser maior que 0");

		return new Particao<T>(list, size);
	}

	private static class Particao<T> extends AbstractList<List<T>> {

		final List<T> list;

		final int size;

		Particao(List<T> list, int size) {
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(int index) {
			int listSize = size();
			if (listSize < 0)
				throw new IllegalArgumentException("Tamanho negativo: " + listSize);
			if (index < 0)
				throw new IndexOutOfBoundsException("Index " + index
						+ " precisa ser não negativo");
			if (index >= listSize)
				throw new IndexOutOfBoundsException("Index " + index
						+ " precisa ser menor que " + listSize);
			int start = index * size;
			int end = Math.min(start + size, list.size());
			return list.subList(start, end);
		}

		@Override
		public int size() {
			return (list.size() + size - 1) / size;
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}
	}
	
	/**
	 * Return true se a colecao eh nula ou vazia
	 * 
	 * 
	 * @param colecao
	 * @return true - caso seja vazia ou nula, false - outros casos
	 */
	@SuppressWarnings("rawtypes")
	public static boolean ehVazia(Collection colecao) {
		return colecao == null || colecao.isEmpty();
	}
	
	/**
	 * Verifica se a colecao nao eh nula e se
	 * tem pelo menos um registro
	 * 
	 * @param colecao
	 * @return true - caso nao seja vazia, false - outros casos
	 */
	@SuppressWarnings("rawtypes")
	public static boolean naoEhVazia(Collection colecao) {
		return !ehVazia(colecao);
	}
	
	/**
	 * Retona a quantidade de vezes para se fazer o particionamento de uma colecao.
	 * Por exemplo, uma colecao de 5001 objetos, e eu desejo trabalhar com varias
	 * colecoes de tamanho 1000 no caso, o metodo retorna o valor 6.
	 * 
	 * 
	 * @param tamanhoDaColecao
	 * @param tamanhoDoPedaco
	 * @return int
	 */
	public static int quantidadeDeVezesParaQuebrarNoTamanhoDe(int tamanhoDaColecao, int tamanhoDoPedaco) {
		int resultado = tamanhoDaColecao / tamanhoDoPedaco;
		int resto = tamanhoDaColecao % tamanhoDoPedaco;
		if (resto == 0) {
			return resultado;
		}
		return resultado + 1;
	}
	
	/**
	 * Retorna a quantidade de interecao necessarias para se trabalhar
	 * apenas com listas de tamanho 1000.
	 * 
	 * Util para usar dentro de clausulas in do SQL.
	 * 
	 * 
	 * @param tamanhoDaColecao
	 * @return um inteiro com a quantidade de vezes para se iterar a colecao
	 */
	public static int quantidadeDeVezesParaParticionarNoTamanhoDeMilEmMil(int tamanhoDaColecao) {
		return quantidadeDeVezesParaQuebrarNoTamanhoDe(tamanhoDaColecao, 1000);
	}
	
	/**
	 * Quebra uma colecao em varias listas de tamanho maxido de 1000
	 * para ser usando dentro clause in do SQL;
	 * 
	 * @param colecao
	 * @return uma lista com as listas do tamanho de mil
	 */
	public static <T> List<List<T>> particionarListaNoTamanhoDeMilEmMil(List<T> colecao) {
		List<List<T>> listas = new ArrayList<List<T>>(1);
		if (naoEhVazia(colecao)) {
			if (colecao.size() < 1000) {
				listas.add(colecao);
				return listas;
			}
			
			int quantidade = quantidadeDeVezesParaQuebrarNoTamanhoDe(colecao.size(), 1000);
			listas = new ArrayList<List<T>>(quantidade);
			int fromIndex = 0;
			int toIndex = 1000;
			for (int i = 0; i < quantidade; i++) {
				listas.add(colecao.subList(fromIndex, toIndex));
				fromIndex = toIndex;
				if (i + 2 == quantidade) { // Se for a penultima iteracao
					toIndex = colecao.size();
				} else {
					toIndex += 1000;
				}
			}
		}
		return listas;
	}
	
	/**
	 * Converte lista de entidades para lista de ids {@code Integer}
	 * 
	 * 
	 * @param colecao
	 * @return
	 */
	public static List<Integer> converterListaDeObjetosParaListaDeIds(List<? extends Conversivel> colecao) {
		List<Integer> retorno = new ArrayList<Integer>();
		if (naoEhVazia(colecao)) {
			for (Conversivel conversivel : colecao) {
				retorno.add(conversivel.getId());
			}
		}
		return retorno;
	}
}
