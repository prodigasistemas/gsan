package gcom.financeiro.lancamento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroLancamentoItemContabil extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroLancamentoItemContabil object
	 */
	public FiltroLancamentoItemContabil() {
	}

	/**
	 * Constructor for the FiltroLancamentoItemContabil object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroLancamentoItemContabil(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";
	
}
