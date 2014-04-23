package gcom.integracao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da Ordem de Serviço
 * @author Rafael Santos
 * @since 09/1/2006
 *
 */
public class FiltroServicoTerceiroAcompanhamentoServico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroServicoTerceiroAcompanhamentoServico() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroServicoTerceiroAcompanhamentoServico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	
		/**
	 * Description of the Field
	 */
	//este campo ainda não foi defenido (só na Iteração 7)
	public final static String INDICADOR_EXCEDENTE = "indicadorExcedente";
	
	
}

