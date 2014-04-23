package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFaturamentoGrupo extends Filtro {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroFaturamentoGrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Construtor da classe FiltroAreaConstruidaFaxia
	 */
	public FiltroFaturamentoGrupo() {
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

	public final static String INDICADOR_VENCIMENTO_MES_FATURA= "indicadorVencimentoMesFatura";
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
	
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	
	public final static String DIA_VENCIMENTO = "diaVencimento";
}
