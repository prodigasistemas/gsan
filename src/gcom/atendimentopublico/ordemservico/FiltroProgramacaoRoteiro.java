package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtrar Programação Roteiro
 * @author Rafael Pinto
 * @since 13/09/2006
 *
 */
public class FiltroProgramacaoRoteiro extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroProgramacaoRoteiro() { }

	/**
	 * @param campoOrderBy
	 */
	public FiltroProgramacaoRoteiro(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String DATA_ROTEIRO = "dataRoteiro";
	
	public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";	
}
