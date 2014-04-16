package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Rafael Santos
 * @since 02/03/2006
 */

public class FiltroCobrancaCriterio extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCobrancaCriterio object
	 */
	public FiltroCobrancaCriterio() {
	}

	/**
	 * Constructor for the FiltroCobrancaCriterio object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCobrancaCriterio(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";

	public final static String COBRANCA_ACAO = "cobrancaAcao";

	public final static String DESCRICAO_COBRANCA_CRITERIO = "descricaoCobrancaCriterio";

	public final static String DATA_INICIO_VIGENCIA = "dataInicioVigencia";

	public final static String NUMERO_ANOS_CONTA_ANTIGA = "numeroContaAntiga";

	public final static String INDICADOR_IMOVEL_SITUACAO_COBRANCA = "indicadorEmissaoImovelSituacaoCobranca";

	public final static String INDICADOR_DEBITO_CONTA_MES = "indicadorEmissaoDebitoContaMes";

	public final static String INDICADOR_CONTA_REVISAO = "indicadorEmissaoContaRevisao";

	public final static String INDICADOR_INQUILINO_DEBITO_CONTA_MES = "indicadorEmissaoInquilinoDebitoContaMes";

	public final static String INDICADOR_DEBITO_CONTA_ANTIGA = "indicadorEmissaoDebitoContaAntiga";

	public final static String INDICADOR_IMOVEL_PARALISACAO = "indicadorEmissaoImovelParalisacao";
	
	public final static String INDICADOR_USO  = "indicadorUso";
	
	public final static String CRITERIOS_SITUACAO_COBRANCA = "criteriosSituacaoCobranca";
	
	public final static String CRITERIOS_SITUACAO_LIGACAO_AGUA = "criteriosSituacaoLigacaoAgua";
	
	public final static String CRITERIOS_SITUACAO_LIGACAO_ESGOTO = "criteriosSituacaoLigacaoEsgoto";

}
