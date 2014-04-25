package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Sávio Luiz
 */
public class FiltroPemissaoEspecial extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroPemissaoEspecial() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroPemissaoEspecial(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";
	
	/**
	 * Código
	 */
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String DESCRICAO = "descricao";


}
