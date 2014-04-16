package gcom.faturamento.debito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um debito a cobrar
 * geral
 * 
 * @author Leonardo Regis
 * @since 12/09/2006
 * 
 */
public class FiltroDebitoACobrarGeral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroDebitoACobrarGeral object
	 */
	public FiltroDebitoACobrarGeral() {
	}

	/**
	 * Constructor for the FiltroDebitoACobrarGeral object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDebitoACobrarGeral(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * ID
	 */
	public final static String ID = "id";

	public final static String DEBITO_A_COBRAR = "debitoACobrar";

	public final static String DEBITO_A_COBRAR_ID = "debitoACobrar.id";

	public final static String DEBITO_A_COBRAR_HISTORICO = "debitoACobrarHistorico";

	public final static String DEBITO_A_COBRAR_HISTORICO_IMOVEL_ID = "debitoACobrarHistorico.imovel.id";

	public final static String DEBITO_A_COBRAR_IMOVEL_ID = "debitoACobrar.imovel.id";

	public final static String DEBITO_A_COBRAR_DEBITO_TIPO = "debitoACobrar.debitoTipo";

	public final static String DEBITO_A_COBRAR_HISTORICO_DEBITO_TIPO = "debitoACobrarHistorico.debitoTipo";

}
