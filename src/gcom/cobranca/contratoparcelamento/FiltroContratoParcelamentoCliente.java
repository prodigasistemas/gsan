package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroContratoParcelamentoCliente extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroContratoParcelamentoCliente() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContratoParcelamentoCliente(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";
	
	/*********************/
		
	public final static String CONTRATO = "contrato";
	
	public final static String CLIENTE = "cliente";
	
	public final static String CLIENTE_TIPO = "cliente.clienteTipo";
	
	public final static String NUMERO_CONTRATO = "contrato.numero";
	
	public final static String NUMERO_ANTERIOR = "contrato.contratoAnterior.numero";
	
	public final static String DATA_CONTRATO = "contrato.dataContrato";
	
	public final static String DATA_IMPLANTACAO = "contrato.dataImplantacao";
	
	public final static String DATA_CANCELAMENTO = "contrato.dataCancelamento";
	
	public final static String MOTIVO_DESFAZER = "contrato.motivoDesfazer";
	
	public final static String PARCEL_SITUACAO = "contrato.parcelamentoSituacao";
	
	public final static String PARCEL_SITUACAO_ID = "contrato.parcelamentoSituacao.id";
	
	public final static String VALOR_A_COBRAR = "contrato.valorParcelamentoACobrar";
	
	public final static String USUARIO_RESPONSAVEL = "contrato.usuarioResponsavel";
	
	public final static String USUARIO_RESPONSAVEL_ID = "contrato.usuarioResponsavel.id";

	public final static String RELACAO_TIPO = "contrato.relacaoCliente";
	
	public final static String RELACAO_TIPO_DESCRICAO = "contrato.relacaoCliente.descricao";
	
	/*******************************/
		
	public final static String ID_CONTRATO = "contrato.id";
	
	public final static String ID_CLIENTE = "cliente.id";
	
	public final static String CLIENTE_SUPERIOR = "clienteSuperior";
	
	public final static String ID_CLIENTE_SUPERIOR = "clienteSuperior.id";
	
	public final static String INDICADOR_CLIENTE_SUPERIOR = "indicadorClienteSuperior";

	
	public final static String CONTRATO_PARCELAMENTO_ID = "contrato.id";
	
	


}
