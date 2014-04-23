package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroImovel extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroImovel() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovel(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "localidade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE = "localidade";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_ID = "setorComercial.id";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
	
	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL = "setorComercial";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ID = "quadra.id";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA = "quadra";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA_FACE = "quadraFace";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_NUMERO = "quadra.numeroQuadra";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_CODIGO = "clienteImoveis.cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String CEP_CODIGO = "logradouroCep.cep.codigo";
	
	/**
	 * Description of the Field
	 */
	public final static String CEP = "logradouroCep.cep";

	/**
	 * Description of the Field
	 */
	public final static String MUNICIPIO_ID = "logradouroBairro.bairro.municipio.id";
	
	/**
	 * Description of the Field
	 */
	public final static String MUNICIPIO = "logradouroBairro.bairro.municipio";

	/**
	 * Description of the Field
	 */
	//public final static String UNIDADE_FEDERACAO = "quadra.bairro.municipio.unidadeFederacao.id";
	public final static String UNIDADE_FEDERACAO = "logradouroBairro.bairro.municipio.unidadeFederacao";

	/**
	 * Description of the Field
	 */
	public final static String BAIRRO_ID = "logradouroBairro.bairro.id";
	
	/**
	 * Description of the Field
	 */
	public final static String BAIRRO = "logradouroBairro.bairro";

	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO_NOME = "logradouroCep.logradouro.nome";

	/**
	 * Description of the Field
	 */
	public final static String IPTU = "numeroIptu";

	/**
	 * Description of the Field
	 */
	public final static String NUMERO_CELPE = "numeroCelpe";

	/**
	 * Description of the Field
	 */
	public final static String LIGACAO_AGUA_SITUACAO_ID = "ligacaoAguaSituacao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
	

	public final static String LIGACAO_AGUA_ID = "ligacaoAgua.id";
	
	public final static String LIGACAO_AGUA = "ligacaoAgua";

	/**
	 * Description of the Field
	 */
	public final static String LIGACAO_ESGOTO_SITUACAO_ID = "ligacaoEsgotoSituacao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ROTA_FATURAMENTO_GRUPO_ID = "quadra.rota.faturamento_grupo.id";
	/**
	 * Description of the Field
	 */
	public final static String FATURAMENTO_GRUPO = "quadra.rota.faturamentoGrupo";

	/**
	 * Description of the Field
	 */
	public final static String LOTE = "lote";

	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_SITUACAO = "cobrancaSituacao";

	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_SITUACAO_ID = "cobrancaSituacao.id";
	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_SITUACAO_TIPO = "cobrancaSituacaoTipo";
	
	
	/**
	 * Description of the Field
	 */
	public final static String SUBLOTE = "subLote";

	/**
	 * Description of the Field
	 */
	public final static String COLECAO_CLIENTE_IMOVEL = "clienteImoveis";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_IMOVEL_CONDOMINIO = "indicadorImovelCondominio";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ROTA_ID = "quadra.rota.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_INSTALACAO_HISTORICO_ID = "hidrometroInstalacaoHistorico.id";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_CONDOMINIO_ID = "imovelCondominio.id";
	
	public final static String IMOVEL_CONDOMINIO = "imovelCondominio";

	public final static String INDICADOR_IMOVEL_AREA_COMUM = "indicadorImovelAreaComum";
	
	/**
	 * Description of the Field
	 */
	public final static String MATRICULA = "matricula";

	public final static String SETOR_COMERCIAL_MUNICIPIO_ID = "setorComercial.municipio.id";
	
	public final static String SETOR_COMERCIAL_MUNICIPIO = "setorComercial.municipio";
	
	public final static String SETOR_COMERCIAL_MUNICIPIO_UF_ID = "setorComercial.municipio.unidadeFederacao.id";
	
	public final static String SETOR_COMERCIAL_MUNICIPIO_UF = "setorComercial.municipio.unidadeFederacao";
	
	public final static String GERENCIA_REGIONAL_ID = "quadra.setorComercial.localidade.gerenciaRegional.id";
	
	public final static String LOGRADOURO_ID = "logradouroCep.logradouro.id";
	
	public final static String LOGRADOURO = "logradouroCep.logradouro";

	public final static String LOGRADOURO_CEP = "logradouroCep.cep";
	
	public final static String LOGRADOURO_TIPO_ID = "logradouroCep.logradouro.logradouroTipo.id";
	
	public final static String LOGRADOURO_TIPO = "logradouroCep.logradouro.logradouroTipo";
	
	public final static String LOGRADOURO_TITULO_ID = "logradouroCep.logradouro.logradouroTitulo.id";
	
	public final static String LOGRADOURO_TITULO = "logradouroCep.logradouro.logradouroTitulo";
	
	public final static String LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_ID = "ligacaoAgua.hidrometroInstalacaoHistorico.id";
	
	public final static String IMOVEL_HIDROMETRO_INSTALACAO_HISTORICO_ID = "imovel.hidrometroInstalacaoHistorico.id";
	
	public final static String ID_IMOVEL_PRINCIPAL = "imovelPrincipal.id";
	
	public final static String IMOVEL_PRINCIPAL = "imovelPrincipal";	
	
	public final static String ID_NOME_CONTA = "nomeConta.id";
	
	public final static String ID_IMOVEL_CONDOMINIO = "imovelCondominio.id";
	
	public final static String ID_CLIENTES_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "clienteImovel.clienteRelacoTipo.id";

	public final static String ID_CLIENTE_IMOVEIS_CLIENTE_ID = "clienteImoveis.cliente.id";
	
	public final static String INDICADOR_IMOVEL_EXCLUIDO = "indicadorExclusao";
	
	public final static String ENDERECO_REFERENCIA = "enderecoReferencia";
	
	public final static String CONSUMO_TARIFA = "consumoTarifa";
	
	public final static String AREA_CONSTRUIDA_FAIXA = "areaConstruidaFaixa";
	
	public final static String PAVIMENTO_CALCADA = "pavimentoCalcada";
	
	public final static String PAVIMENTO_RUA = "pavimentoRua";
	
	public final static String IMOVEL_PERFIL = "imovelPerfil";
	
	public final static String RESERVATORIO_VOLUME_FAIXA_SUPERIOR = "reservatorioVolumeFaixaSuperior";
	
	public final static String RESERVATORIO_VOLUME_FAIXA_INFERIOR = "reservatorioVolumeFaixaInferior";
	
	public final static String POCO_TIPO = "pocoTipo";
	
	public final static String DESPEJO = "despejo";
	
	public final static String PISCINA_VOLUME_FAIXA = "piscinaVolumeFaixa";
	
	public final static String IMOVEL_SUBCATEGORIAS = "imovelSubcategorias";
	
	public final static String CLIENTES_IMOVEIS = "clienteImoveis";
	
	public final static String IMOVEL_CONTA_ENVIO = "imovelContaEnvio";
	
	public final static String FUNCIONARIO = "funcionario";
	
	public final static String ROTA_ENTREGA = "rotaEntrega";
	
	public final static String ROTA_ALTERNATIVA = "rotaAlternativa";
	
	public final static String IMOVEL_TIPO_HABITACAO = "imovelTipoHabitacao";
	public final static String IMOVEL_TIPO_PROPRIEDADE = "imovelTipoPropriedade";
	public final static String IMOVEL_TIPO_CONSTRUCAO = "imovelTipoConstrucao";
	public final static String IMOVEL_TIPO_COBERTURA = "imovelTipoCobertura";
	public final static String IMOVEL = "imovel";
	
	public final static String NUMERO_SEQUENCIAL_ROTA = "numeroSequencialRota";
	
	public final static String LOGRADOURO_TIPO_PERIMETRO_INICIAL = "perimetroInicial.logradouroTipo";
	public final static String LOGRADOURO_TITULO_PERIMETRO_INICIAL = "perimetroInicial.logradouroTitulo";
	public final static String LOGRADOURO_TIPO_PERIMETRO_FINAL = "perimetroFinal.logradouroTipo";
	public final static String LOGRADOURO_TITULO_PERIMETRO_FINAL = "perimetroFinal.logradouroTitulo";
	
	public final static String FATURAMENTO_SITUACAO_TIPO = "faturamentoSituacaoTipo";
	
	public final static String INFORMACOES_COMPLEMENTARES = "informacoesComplementares";

	
	public final static String LOGRADOURO_BAIRRO = "logradouroBairro";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_UNIDADE_NEGOCIO = "localidade.unidadeNegocio";
	
	public final static String IMOVEL_MEDIDOR_ENERGIA = "numeroMedidorEnergia";
	
	public final static String CADASTRO_OCORRENCIA = "cadastroOcorrencia";
	
	public final static String CODIGO_DEBITO_AUTOMATICO = "codigoDebitoAutomatico";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA_FACE_ID = "quadraFace.id";
	
	public final static String LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO = "ligacaoAgua.hidrometroInstalacaoHistorico";
	
	public final static String LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_HIDROMETRO = "ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro";

    public final static String ANORMALIDADE_INFORMADA = "leituraAnormalidade";
    
    public final static String NOME_IMOVEL = "nomeImovel";

}
