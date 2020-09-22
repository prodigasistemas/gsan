package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento
 */
public class FiltroRegistroAtendimento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroRegistroAtendimento() {
	}

	public FiltroRegistroAtendimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

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
	
	public final static String SERVICO_TIPO = "solicitacaoTipoEspecificacao.servicoTipo";
	public final static String SERVICO_TIPO_ID = "solicitacaoTipoEspecificacao.servicoTipo.id";
	
	public final static String CEP_CODIGO = "logradouroCep.cep.codigo";

	public final static String CEP = "logradouroCep.cep";

	public final static String MUNICIPIO_ID = "logradouroBairro.bairro.municipio.id";

	public final static String MUNICIPIO = "logradouroBairro.bairro.municipio";

	public final static String UNIDADE_FEDERACAO = "logradouroBairro.bairro.municipio.unidadeFederacao";

	public final static String BAIRRO_ID = "logradouroBairro.bairro.id";

	public final static String BAIRRO = "logradouroBairro.bairro";

	public final static String LOGRADOURO_NOME = "logradouroCep.logradouro.nome";
	
}
