package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente endereco
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroArrecadadorContrato extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroArrecadadorContrato() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroArrecadadorContrato(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_INSCRICAO_ESTATAL = "arrecadador.numeroInscricaoEstadual";

	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_CLIENTE_ID = "arrecadador.cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_LOCALIDADE_ID = "arrecadador.localidade.id";

	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_IMOVEL_ID = "arrecadador.imovel.id";
	
	public final static String DATA_CONTRATO_INICIO = "dataContratoInicio";

	public final static String DATA_CONTRATO_FIM = "dataContratoFim";
	
	public final static String DATA_CONTRATO_ENCERRAMENTO = "dataContratoEncerramento";

	public final static String CODIGO_CONVENIO = "codigoConvenio";
	
	public final static String INDICADOR_COBRANCA = "indicadorCobrancaIss";
	
	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_CODIGO_AGENTE = "arrecadador.codigoAgente";

	public final static String ARRECADADOR_ID = "arrecadador.id";
	
	public final static String ID_DEPOSITO_ARRECADACAO = "contaBancariaDepositoArrecadacao";
	
	public final static String ID_DEPOSITO_TARIFA = "contaBancariaDepositoTarifa";
	
	public final static String DESCRICAO_EMAIL = "descricaoEmail";
	
	/**
	 * Description of the Field
	 */
	public final static String NUMEROCONTRATO = "numeroContrato";

	public final static String CLIENTE_ID = "cliente.id";
	
	public final static String NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_CODIGO_BARRAS = "numeroSequecialArquivoRetornoCodigoBarras";
	
	public final static String NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_FICHA_COMPENSACAO = "numeroSequencialArquivoRetornoFichaCompensacao";
	
	public final static String NUMERO_SEQUENCIAL_ARQUIVO_ENVIO_DEBITO_AUTOMATICO = "numeroSequencialArquivoEnvioDebitoAutomatico";
	
}
