package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente
 * 
 * @author Sávio Luiz
 */
public class FiltroCliente extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroCliente() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCliente(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código da unidade de medição
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String CNPJ = "cnpj";
	
	/**
	 * Description of the Field
	 */
	public final static String NIS = "numeroNIS";
	
	/**
	 * Description of the Field
	 */
	public final static String CPF = "cpf";

	/**
	 * Description of the Field
	 */
	public final static String RG = "rg";

	/**
	 * Description of the Field
	 */
	public final static String NOME = "nome";
	
	/**
	 * Description of the Field
	 */
	public final static String NOME_ABREVIADO = "nomeAbreviado";
	
	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO_ID = "profissao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO = "profissao";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_ID = "clienteTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_TIPO = "clienteTipo";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_IPFJ = "clienteTipo.indicadorPessoaFisicaJuridica";

	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDIDOR_RG = "orgaoExpedidorRg";

	/**
	 * Description of the Field
	 */
	public final static String UNIDADE_FEDERACAO = "unidadeFederacao";
	
	/**
	 * Description of the Field
	 */
	public final static String EMAIL = "email";
	
	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE_ID = "ramoAtividade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE = "ramoAtividade";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO_RG = "dataEmissaoRg";
	
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_NASCIMENTO = "dataNascimento";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL_ID = "cliente.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL = "cliente";
	
	/**
	 * Description of the Field
	 */
	public final static String SEXO_ID = "pessoaSexo.id";
	
	public final static String SEXO = "pessoaSexo";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ENDERECOS = "clienteEnderecos";
	
	public final static String GERA_ARQUIVO_TEXTO = "indicadorGeraArquivoTexto";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_TELEFONES = "clienteFones";

	public final static String MUNICIPIO_ID = "clienteEndereco.logradouroCep.logradouro.municipio.id";

	public final static String LOGRADOUR_TITULO = "clienteEndereco.logradouroCep.logradouro.logradouroTitulo";
	
	public final static String LOGRADOUR_TIPO = "clienteEndereco.logradouroCep.logradouro.logradouroTipo";

	public final static String LOGRADOURO = "clienteEndereco.logradouroCep.logradouro.id";
	
	public final static String BAIRRO_CODIGO = "clienteEndereco.logradouroBairro.bairro.codigo";
	
	public final static String CEP = "clienteEndereco.logradouroCep.cep.codigo";
	
	public final static String IMOVEL_ID = "clienteImovel.imovel.id";
	
	public final static String NOME_MAE = "nomeMae";
	
	public final static String ESFERA_PODER_ID = "clienteTipo.esferaPoder.id";
	
	public final static String ESFERA_PODER = "clienteTipo.esferaPoder";

	
	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDIDOR_RG_ID = "orgaoExpedidorRg.id";

	/**
	 * Description of the Field
	 */
	public final static String UNIDADE_FEDERACAO_ID = "unidadeFederacao.id";
	
}
