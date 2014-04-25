package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente endereco
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroClienteEndereco extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroClienteEndereco() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroClienteEndereco(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String CNPJ = "cliente.cnpj";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String CPF = "cliente.cpf";

	/**
	 * Description of the Field
	 */
	public final static String CEP = "logradouroCep.cep.codigo";

	/**
	 * Description of the Field
	 */
	public final static String RG = "cliente.rg";

	/**
	 * Description of the Field
	 */
	public final static String NOME = "cliente.nome";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_ID = "cliente.clienteTipo.id";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_DESCRICAO = "cliente.clienteTipo.descricao";

	/**
	 * Código da unidade de medição
	 */
	public final static String BAIRRO_ID = "logradouroBairro.bairro.id";
	
	public final static String BAIRRO_MUNICIPIO_UNIDADE_FEDERACAO = "logradouroBairro.bairro.municipio.unidadeFederacao";

	/**
	 * Código da unidade de medição
	 */
	public final static String BAIRRO_CODIGO = "logradouroBairro.bairro.codigo";

	/**
	 * Código da unidade de medição
	 */
	public final static String MUNICIPIO_ID = "logradouroCep.logradouro.municipio.id";
	
	/**
	 * Código da unidade de medição
	 */
	public final static String MUNICIPIO_ID_CEP = "logradouroCep.cep.municipio.id";

	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO = "logradouroCep.logradouro.nome";
	
	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO_ID = "logradouroCep.logradouro.id";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "cliente.indicadorUso";

	public final static String LOGRADOURO_MUNICIPIO = "logradouroCep.logradouro.municipio";
	
	public final static String CEP_MUNICIPIO = "logradouroCep.cep.municipio";
	
	/**
	 * Description of the Field
	 */
	public final static String NOME_ABREVIADO = "cliente.nomeAbreviado";

	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO = "cliente.profissao.id";

	/**
	 * Description of the Field
	 */
	public final static String EMAIL = "cliente.email";

	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE = "cliente.ramoAtividade";

	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO_RG = "cliente.dataEmissaoRg";

	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDITOR_RG = "cliente.orgaoExpedidorRg.id";

	/**
	 * Description of the Field
	 */
	public final static String DATA_NASCIMENTO = "cliente.dataNascimento";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL_ID = "cliente.cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String SEXO = "cliente.pessoaSexo.id";

	public final static String CLIENTE_CLIENTE_IMOVEL_IMOVEL_ID = "clienteImovel.imovel.id";
	
	public final static String INDICADOR_CORRESPONDENCIA = "indicadorEnderecoCorrespondencia";
	
	public final static String ID = "id";
	
	public final static String LOGRADOURO_CEP = "logradouroCep";
	
	public final static String ENDERECO_TIPO = "enderecoTipo";
	
	public final static String LOGRADOURO_BAIRRO = "logradouroBairro.bairro";
	
	public final static String ENDERECO_REFERENCIA = "enderecoReferencia";
	
}
