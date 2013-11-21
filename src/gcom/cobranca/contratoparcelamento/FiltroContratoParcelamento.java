package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroContratoParcelamento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroContratoParcelamento() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContratoParcelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String NUMERO = "numero";

	public final static String NUMERO_ANTERIOR = "contratoAnterior.numero";
	
	public final static String DATA_CONTRATO = "dataContrato";
	
	public final static String DATA_IMPLANTACAO = "dataImplantacao";
	
	public final static String DATA_CANCELAMENTO = "dataCancelamento";
	
	public final static String MOTIVO_DESFAZER = "motivoDesfazer";
	
	public final static String PARCEL_SITUACAO_ID = "parcelamentoSituacao.id";
	
	public final static String USUARIO_RESPONSAVEL_ID = "usuarioResponsavel.id";
	
	public final static String VALOR_A_COBRAR = "valorTotalDebitosCobrar";

	public final static String CONTRATO_ANTERIOR = "contratoAnterior";

	public final static String RELACAO_ANTERIOR = "relacaoAnterior";

	public final static String PARCEL_SITUACAO = "parcelamentoSituacao";
	
	public final static String USUARIO_RESPONSAVEL = "usuarioResponsavel";

	public final static String RELACAO_CLIENTE = "relacaoCliente";

	public final static String RESOLUCAO_DIRETORIA = "resolucaoDiretoria";

	public final static String COBRANCA_FORMA = "cobrancaForma";

	public final static String QUANTIDADE_PRESTACOES_RD_ESCOLHIDA = "qtdPrestacoesDaRDEscolhida";
	

}
