package gcom.util;

import java.util.AbstractList;
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

}
