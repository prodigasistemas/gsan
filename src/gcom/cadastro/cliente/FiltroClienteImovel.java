package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroClienteImovel extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroClienteImovel() {}

	public FiltroClienteImovel(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String CLIENTE_ID = "cliente.id";
	public final static String CLIENTE = "cliente";
	public final static String CLIENTE_NOME = "cliente.nome";
	public final static String IMOVEL_ID = "imovel.id";
	public final static String IMOVEL = "imovel";
	public final static String IMOVEL_INDICADOR_IMOVEL_AREA_COMUM = "imovel.indicadorImovelAreaComum";
	public final static String LOCALIDADE_ID = "imovel.localidade.id";
	public final static String LOCALIDADE = "imovel.localidade";
	public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";
	public final static String SETOR_COMERCIAL = "imovel.setorComercial";
	public final static String SETOR_COMERCIAL_CODIGO = "imovel.setorComercial.codigo";
	public final static String QUADRA_ID = "imovel.quadra.id";
	public final static String QUADRA = "imovel.quadra";
	public final static String QUADRA_NUMERO = "imovel.quadra.numeroQuadra";
	public final static String CEP_CODIGO = "imovel.logradouroCep.cep.codigo";
	public final static String CEP_ID = "imovel.logradouroCep.cep.cepId";
	public final static String CEP = "imovel.logradouroCep.cep";
	public final static String MUNICIPIO_CODIGO = "imovel.logradouroBairro.bairro.municipio.id";
	public final static String MUNICIPIO = "imovel.logradouroBairro.bairro.municipio";
	public final static String MUNICIPIO_SETOR_COMERICAL_CODIGO = "imovel.setorComercial.municipio.id";
	public final static String BAIRRO_ID = "imovel.logradouroBairro.bairro.id";
	public final static String BAIRRO = "imovel.logradouroBairro.bairro";
	public final static String LOTE = "imovel.lote";
	public final static String SUBLOTE = "imovel.subLote";
	public final static String BAIRRO_CODIGO = "imovel.logradouroBairro.bairro.codigo";
	public final static String LOGRADOURO_NOME = "imovel.logradouroCep.logradouro.nome";
	public final static String INDICADOR_USO = "cliente.indicadorUso";
	public final static String IMOVEL_PERFIL = "imovel.imovelPerfil.id";
	public final static String IMOVEL_PERFIL_INDICADOR_USO = "imovel.imovelPerfil.indicadorUso";
	public final static String CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo";
	public final static String FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo.id";
	public final static String DATA_FIM_RELACAO = "dataFimRelacao";
	public final static String DATA_INICIO_RELACAO = "dataInicioRelacao";
	public final static String GERA_ARQUIVO_TEXTO = "cliente.indicadorGeraArquivoTexto";
	public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo";
	public final static String CLIENTE_RELACAO_TIPO_DESCRICAO = "clienteRelacaoTipo.descricao";
	public final static String CLIENTE_RELACAO_TIPO_ID = "clienteRelacaoTipo.id";
	public final static String SETOR_COMERCIAL_MUNICIPIO_ID = "imovel.setorComercial.municipio.id";// tem
	public final static String GERENCIA_REGIONAL_ID = "imovel.quadra.setorComercial.localidade.gerenciaRegional.id";
	public final static String LOGRADOURO_ID = "imovel.logradouroCep.logradouro.id";
	public final static String LOGRADOURO = "imovel.logradouroCep.logradouro";
	public final static String LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_ID = "imovel.ligacaoAgua.hidrometroInstalacaoHistorico.id";
	public final static String IMOVEL_HIDROMETRO_INSTALACAO_HISTORICO_ID = "imovel.hidrometroInstalacaoHistorico.id";
	public final static String ID_IMOVEL_PRINCIPAL = "imovel.imovelPrincipal.id";
	public final static String ID_NOME_CONTA = "imovel.nomeConta.id";
	public final static String ID_IMOVEL_CONDOMINIO = "imovel.imovelCondominio.id";
	public final static String ID_CLIENTES_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "clienteRelacoTipo.id";
	public final static String ID_CLIENTE_IMOVEIS_CLIENTE_ID = "cliente.id";
	public final static String MUNICIPIO_ID = "imovel.logradouroBairro.bairro.municipio.id";
	public final static String IMOVEL_POCO_TIPO_ID = "imovel.pocoTipo.id";
	public final static String IMOVEL_IMOVEL_PERFIL_ID = "imovel.imovelPerfil.id";
	public final static String IMOVEL_CATEGORIA_IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria.id";
	public final static String IMOVEL_SUBCATEGORIA_IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.id";
	public final static String IMOVEL_SITUACAOAGUA_ID = "imovel.situacaoAgua.id";
	public final static String IMOVEL_LIGACAO_ESGOTO_SITUACAO_ID = "imovel.ligacaoEsgotoSituacao.id";
	public final static String IMOVEL_MEDICAO_HISTORICOS_MEDICAO_TIPO_ID = "imovel.medicaoHistoricos.medicaoTipo.id";
	public final static String IMOVEL_LIGACAO_ESGOTO_CONSUMO_MINIMO = "imovel.ligacaoEsgoto.consumoMinimo";
	public final static String IMOVEL_LIGACAO_AGUA_CONSUMO_MINIMO = "imovel.ligacaoAgua.numeroConsumoMinimoAgua";
	public final static String IMOVEL_LIGACAO_ESGOTO_PERCENTUAL = "imovel.ligacaoEsgoto.percentual";
	public final static String INDICADOR_IMOVEL_EXCLUIDO = "imovel.indicadorExclusao";
	public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
	public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
	public final static String CLIENTE_TIPO = "cliente.clienteTipo";
	public final static String INDICADOR_NOME_CONTA = "indicadorNomeConta";
	public final static String UNIDADE_FEDERACAO = "cliente.unidadeFederacao";
	public final static String CLIENTE_FONE = "cliente.clienteFones";
	public final static String CLIENTE_FONE_INDICADOR_TELEFONE_PADRAO = "cliente.clienteFones.indicadorTelefonePadrao";
	public final static String CLIENTE_FONE_DDD = "cliente.clienteFones.ddd";
	public final static String CLIENTE_FONE_TELEFONE = "cliente.clienteFones.telefone";
	public final static String FONE_TIPO = "cliente.clienteFones.foneTipo";
	public final static String ORGAO_EXPEDIDOR_RG = "cliente.orgaoExpedidorRg";
}
