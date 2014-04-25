package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento
 * @author Rafael Santos
 * @since 09/01/2006
 * 
 */
public class FiltroRegistroAtendimento  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroRegistroAtendimento() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";	
    public final static String IMOVEL = "imovel"; 
	public final static String CLIENTE_ID = "cliente.id";	
	public final static String ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento.id";
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento";
	
	public final static String ID_SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao.id";
	
	public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao";
	public final static String SOLICITACAO_TIPO = "solicitacaoTipoEspecificacao.solicitacaoTipo";
	public final static String SOLICITACAO_TIPO_ESPECIFIC = "solicitacaoTipoEspecificacao";
	public final static String CODIGO_SITUACAO = "codigoSituacao";
	
	//public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao";
	
	
}
