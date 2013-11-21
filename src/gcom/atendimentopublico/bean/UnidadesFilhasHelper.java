package gcom.atendimentopublico.bean;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnidadesFilhasHelper {
	
	/**
	 * Hash ordenada Id da UnidadeOrganizacional -> UnidadeOrganizacional.
	 * Os Ids estão ordenados de forma que uma unidade filha aparece 
	 * sempre antes de sua unidade superior
	 */
	private LinkedHashMap<Integer, UnidadeOrganizacional> unidades;
	
	/**
	 * Hash Id da UnidadeOrganizacional -> Coleção de todas as unidades filhas
	 * (direta ou indiretamente)
	 */
	private Map<Integer, Collection<UnidadeOrganizacional>> filhosDaUnidade;
	
	public UnidadesFilhasHelper() { }
	
	public UnidadesFilhasHelper(
			LinkedHashMap<Integer, UnidadeOrganizacional> unidades,
			Map<Integer, Collection<UnidadeOrganizacional>> unidadeTemFilhos) { 
		this.unidades = unidades;
		this.filhosDaUnidade = unidadeTemFilhos;
	}

	/**
	 * Retorna um Hash ordenada Id da UnidadeOrganizacional -> UnidadeOrganizacional.
	 * Os Ids estão ordenados de forma que uma unidade filha aparece 
	 * sempre antes de sua unidade superior
	 */
	public LinkedHashMap<Integer, UnidadeOrganizacional> getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades O unidades a ser setado.
	 */
	public void setUnidades(LinkedHashMap<Integer, UnidadeOrganizacional> unidades) {
		this.unidades = unidades;
	}

	/**
	 * Retorna um Hash Id da UnidadeOrganizacional -> Coleção de todas as unidades filhas
	 * (direta ou indiretamente)
	 */
	public Map<Integer, Collection<UnidadeOrganizacional>> getFilhosDaUnidade() {
		return filhosDaUnidade;
	}

	/**
	 * @param unidadeTemFilhos O unidadeTemFilhos a ser setado.
	 */
	public void setFilhosDaUnidade(
			Map<Integer, Collection<UnidadeOrganizacional>> unidadeTemFilhos) {
		this.filhosDaUnidade = unidadeTemFilhos;
	}

}
