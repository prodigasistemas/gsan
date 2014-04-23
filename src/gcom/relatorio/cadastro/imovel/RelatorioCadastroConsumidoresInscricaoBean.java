package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;


/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 11 de Novembro de 2005
 * @version 1.0
 */

public class RelatorioCadastroConsumidoresInscricaoBean implements RelatorioBean {

	private String tipoFaturamento;
	
	private String descricaoAbreviadaCategoria;
	
	private String quantidadeEconomia;

	private String idGerenciaRegional;

	private String nomeGerenciaRegional;

	private String idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private String idLocalidade;

	private String nomeLocalidade;

	private String idSetorComercial;

	private String nomeSetorComercial;

	private String inscricao;

	private String matricula;

	private String codigoCliente;

	private String nomeCliente;

	private String codigoClienteResponsavel;

	private String nomeClienteResponsavel;

	private String endereco;

	private String indicadorImovelCondominio;

	private String matriculaImovelCondominio;

	private String matriculaImovelPrincipal;

	private String perfilImovel;

	private String subcategoria;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String pavimentoCalcada;

	private String pavimentoRua;

	private String tipoDespejo;

	private String volumeReservatorioSuperior;

	private String volumeReservatorioInferior;

	private String volumePiscina;

	private String mediaConsumo;

	private String areaConstruida;

	private String numeroMoradores;

	private String numeroPontos;

	private String poco;

	private String jardim;

	private String dataLigacaoAgua;

	private String diametroLigacaoAgua;

	private String materialLigacaoAgua;

	private String consumoMinimoAgua;

	private String dataLigacaoEsgoto;

	private String diametroLigacaoEsgoto;

	private String materialLigacaoEsgoto;

	private String percentualColeta;

	private String percentualEsgoto;

	private String consumoMinimoEsgoto;

	private String numeroHidrometroAgua;

	private String anoFabricacaoHidrometroAgua;

	private String capacidadeHidrometroAgua;

	private String marcaHidrometroAgua;

	private String diametroHidrometroAgua;

	private String tipoHidrometroAgua;

	private String dataInstalacaoHidrometroAgua;

	private String localInstalacaoHidrometroAgua;

	private String tipoProtecaoHidrometroAgua;

	private String indicadorCavaleteHidrometroAgua;

	private String numeroHidrometroPoco;

	private String anoFabricacaoHidrometroPoco;

	private String capacidadeHidrometroPoco;

	private String marcaHidrometroPoco;

	private String diametroHidrometroPoco;

	private String tipoHidrometroPoco;

	private String dataInstalacaoHidrometroPoco;

	private String localInstalacaoHidrometroPoco;

	private String tipoProtecaoHidrometroPoco;

	private String indicadorCavaleteHidrometroPoco;
	
	private String rota;
	
	private String sequencialRota;
	
	private String idLogradouro;
	
	private String fone;
	
	private String rotaSeqRota;
	
	private String testadaLote;
	
	/**
	 * @return Retorna o campo rotaSeqRota.
	 */
	public String getRotaSeqRota() {
		return rotaSeqRota;
	}

	/**
	 * @param rotaSeqRota O rotaSeqRota a ser setado.
	 */
	public void setRotaSeqRota(String rotaSeqRota) {
		this.rotaSeqRota = rotaSeqRota;
	}

	/**
	 * Constructor for the RelatorioManterImovelBean object
	 * 
	 * @param idGerenciaRegional
	 *            Description of the Parameter
	 * @param nomeGerenciaRegional
	 *            Description of the Parameter
	 * @param idLocalidade
	 *            Description of the Parameter
	 * @param nomeLocalidade
	 *            Description of the Parameter
	 * @param idSetorComercial
	 *            Description of the Parameter
	 * @param nomeSetorComercial
	 *            Description of the Parameter
	 * @param inscricao
	 *            Description of the Parameter
	 * @param matricula
	 *            Description of the Parameter
	 * @param codigoCliente
	 *            Description of the Parameter
	 * @param nomeCliente
	 *            Description of the Parameter
	 * @param endereco
	 *            Description of the Parameter
	 * @param indicadorImovelCondominio
	 *            Description of the Parameter
	 * @param matriculaImovelCondominio
	 *            Description of the Parameter
	 * @param matriculaImovelPrincipal
	 *            Description of the Parameter
	 * @param perfilImovel
	 *            Description of the Parameter
	 * @param subcategoria
	 *            Description of the Parameter
	 * @param situacaoAgua
	 *            Description of the Parameter
	 * @param situacaoEsgoto
	 *            Description of the Parameter
	 * @param pavimentoCalcada
	 *            Description of the Parameter
	 * @param pavimentoRua
	 *            Description of the Parameter
	 * @param tipoDespejo
	 *            Description of the Parameter
	 * @param volumeReservatorioSuperior
	 *            Description of the Parameter
	 * @param volumeReservatorioInferior
	 *            Description of the Parameter
	 * @param volumePiscina
	 *            Description of the Parameter
	 * @param mediaConsumo
	 *            Description of the Parameter
	 * @param areaConstruida
	 *            Description of the Parameter
	 * @param numeroMoradores
	 *            Description of the Parameter
	 * @param numeroPontos
	 *            Description of the Parameter
	 * @param poco
	 *            Description of the Parameter
	 * @param dataLigacaoAgua
	 *            Description of the Parameter
	 * @param diametroLigacaoAgua
	 *            Description of the Parameter
	 * @param materialLigacaoAgua
	 *            Description of the Parameter
	 * @param consumoMinimoAgua
	 *            Description of the Parameter
	 * @param dataLigacaoEsgoto
	 *            Description of the Parameter
	 * @param diametroLigacaoEsgoto
	 *            Description of the Parameter
	 * @param materialLigacaoEsgoto
	 *            Description of the Parameter
	 * @param percentualColeta
	 *            Description of the Parameter
	 * @param percentualEsgoto
	 *            Description of the Parameter
	 * @param consumoMinimoEsgoto
	 *            Description of the Parameter
	 * @param mumeroHidrometroAgua
	 *            Description of the Parameter
	 * @param anoFabricacaoHidrometroAgua
	 *            Description of the Parameter
	 * @param capacidadeHidrometroAgua
	 *            Description of the Parameter
	 * @param marcaHidrometroAgua
	 *            Description of the Parameter
	 * @param diametroHidrometroAgua
	 *            Description of the Parameter
	 * @param tipoHidrometroAgua
	 *            Description of the Parameter
	 * @param dataInstalacaoHidrometroAgua
	 *            Description of the Parameter
	 * @param localInstalacaoHidrometroAgua
	 *            Description of the Parameter
	 * @param tipoProtecaoHidrometroAgua
	 *            Description of the Parameter
	 * @param indicadorCavaleteHidrometroAgua
	 *            Description of the Parameter
	 * @param mumeroHidrometroPoco
	 *            Description of the Parameter
	 * @param anoFabricacaoHidrometroPoco
	 *            Description of the Parameter
	 * @param capacidadeHidrometroPoco
	 *            Description of the Parameter
	 * @param marcaHidrometroPoco
	 *            Description of the Parameter
	 * @param diametroHidrometroPoco
	 *            Description of the Parameter
	 * @param tipoHidrometroPoco
	 *            Description of the Parameter
	 * @param dataInstalacaoHidrometroPoco
	 *            Description of the Parameter
	 * @param localInstalacaoHidrometroPoco
	 *            Description of the Parameter
	 * @param tipoProtecaoHidrometroPoco
	 *            Description of the Parameter
	 * @param indicadorCavaleteHidrometroPoco
	 *            Description of the Parameter
	 */
	public RelatorioCadastroConsumidoresInscricaoBean(String idGerenciaRegional,
			String nomeGerenciaRegional, String idUnidadeNegocio, 
			String nomeUnidadeNegocio, String idLocalidade,
			String nomeLocalidade, String idSetorComercial,
			String nomeSetorComercial, String inscricao, String matricula,
			String codigoCliente, String nomeCliente,
			String codigoClienteResponsavel, String nomeClienteResponsavel,
			String endereco, String indicadorImovelCondominio,
			String matriculaImovelCondominio, String matriculaImovelPrincipal,
			String perfilImovel, String subcategoria, String situacaoAgua,
			String situacaoEsgoto, String pavimentoCalcada,
			String pavimentoRua, String tipoDespejo,
			String volumeReservatorioSuperior,
			String volumeReservatorioInferior, String volumePiscina,
			String mediaConsumo, String areaConstruida, String numeroMoradores,
			String numeroPontos, String poco, String jardim, 
			String dataLigacaoAgua,
			String diametroLigacaoAgua, String materialLigacaoAgua,
			String consumoMinimoAgua, String dataLigacaoEsgoto,
			String diametroLigacaoEsgoto, String materialLigacaoEsgoto,
			String percentualColeta, String percentualEsgoto,
			String consumoMinimoEsgoto, String numeroHidrometroAgua,
			String anoFabricacaoHidrometroAgua,
			String capacidadeHidrometroAgua, String marcaHidrometroAgua,
			String diametroHidrometroAgua, String tipoHidrometroAgua,
			String dataInstalacaoHidrometroAgua,
			String localInstalacaoHidrometroAgua,
			String tipoProtecaoHidrometroAgua,
			String indicadorCavaleteHidrometroAgua,
			String numeroHidrometroPoco, String anoFabricacaoHidrometroPoco,
			String capacidadeHidrometroPoco, String marcaHidrometroPoco,
			String diametroHidrometroPoco, String tipoHidrometroPoco,
			String dataInstalacaoHidrometroPoco,
			String localInstalacaoHidrometroPoco,
			String tipoProtecaoHidrometroPoco,
			String indicadorCavaleteHidrometroPoco, String rota, String sequencialRota,
			String testadaLote) {
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.nomeSetorComercial = nomeSetorComercial;
		this.inscricao = inscricao;
		this.matricula = matricula;
		this.codigoCliente = codigoCliente;
		this.nomeCliente = nomeCliente;
		this.codigoClienteResponsavel = codigoClienteResponsavel;
		this.nomeClienteResponsavel = nomeClienteResponsavel;
		this.endereco = endereco;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.matriculaImovelCondominio = matriculaImovelCondominio;
		this.matriculaImovelPrincipal = matriculaImovelPrincipal;
		this.perfilImovel = perfilImovel;
		this.subcategoria = subcategoria;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
		this.pavimentoCalcada = pavimentoCalcada;
		this.pavimentoRua = pavimentoRua;
		this.tipoDespejo = tipoDespejo;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumePiscina = volumePiscina;
		this.mediaConsumo = mediaConsumo;
		this.areaConstruida = areaConstruida;
		this.numeroMoradores = numeroMoradores;
		this.numeroPontos = numeroPontos;
		this.poco = poco;
		this.jardim = jardim;
		this.dataLigacaoAgua = dataLigacaoAgua;
		this.diametroLigacaoAgua = diametroLigacaoAgua;
		this.materialLigacaoAgua = materialLigacaoAgua;
		this.consumoMinimoAgua = consumoMinimoAgua;
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
		this.diametroLigacaoEsgoto = diametroLigacaoEsgoto;
		this.materialLigacaoEsgoto = materialLigacaoEsgoto;
		this.percentualColeta = percentualColeta;
		this.percentualEsgoto = percentualEsgoto;
		this.consumoMinimoEsgoto = consumoMinimoEsgoto;
		this.numeroHidrometroAgua = numeroHidrometroAgua;
		this.anoFabricacaoHidrometroAgua = anoFabricacaoHidrometroAgua;
		this.capacidadeHidrometroAgua = capacidadeHidrometroAgua;
		this.marcaHidrometroAgua = marcaHidrometroAgua;
		this.diametroHidrometroAgua = diametroHidrometroAgua;
		this.tipoHidrometroAgua = tipoHidrometroAgua;
		this.dataInstalacaoHidrometroAgua = dataInstalacaoHidrometroAgua;
		this.localInstalacaoHidrometroAgua = localInstalacaoHidrometroAgua;
		this.tipoProtecaoHidrometroAgua = tipoProtecaoHidrometroAgua;
		this.indicadorCavaleteHidrometroAgua = indicadorCavaleteHidrometroAgua;
		this.numeroHidrometroPoco = numeroHidrometroPoco;
		this.anoFabricacaoHidrometroPoco = anoFabricacaoHidrometroPoco;
		this.capacidadeHidrometroPoco = capacidadeHidrometroPoco;
		this.marcaHidrometroPoco = marcaHidrometroPoco;
		this.diametroHidrometroPoco = diametroHidrometroPoco;
		this.tipoHidrometroPoco = tipoHidrometroPoco;
		this.dataInstalacaoHidrometroPoco = dataInstalacaoHidrometroPoco;
		this.localInstalacaoHidrometroPoco = localInstalacaoHidrometroPoco;
		this.tipoProtecaoHidrometroPoco = tipoProtecaoHidrometroPoco;
		this.indicadorCavaleteHidrometroPoco = indicadorCavaleteHidrometroPoco;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		this.testadaLote = testadaLote;
	}

	public RelatorioCadastroConsumidoresInscricaoBean() {		
	}

	/**
	 * Gets the anoFabricacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The anoFabricacaoHidrometroAgua value
	 */
	public String getAnoFabricacaoHidrometroAgua() {
		return anoFabricacaoHidrometroAgua;
	}

	/**
	 * Gets the anoFabricacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The anoFabricacaoHidrometroPoco value
	 */
	public String getAnoFabricacaoHidrometroPoco() {
		return anoFabricacaoHidrometroPoco;
	}

	/**
	 * Gets the areaConstruida attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The areaConstruida value
	 */
	public String getAreaConstruida() {
		return areaConstruida;
	}

	/**
	 * Gets the capacidadeHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The capacidadeHidrometroAgua value
	 */
	public String getCapacidadeHidrometroAgua() {
		return capacidadeHidrometroAgua;
	}

	/**
	 * Gets the capacidadeHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The capacidadeHidrometroPoco value
	 */
	public String getCapacidadeHidrometroPoco() {
		return capacidadeHidrometroPoco;
	}

	/**
	 * Gets the codigoCliente attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The codigoCliente value
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * Gets the codigoClienteResponsavel attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The codigoClienteResponsavel value
	 */
	public String getCodigoClienteResponsavel() {
		return codigoClienteResponsavel;
	}

	/**
	 * Gets the consumoMinimoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The consumoMinimoAgua value
	 */
	public String getConsumoMinimoAgua() {
		return consumoMinimoAgua;
	}

	/**
	 * Gets the consumoMinimoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The consumoMinimoEsgoto value
	 */
	public String getConsumoMinimoEsgoto() {
		return consumoMinimoEsgoto;
	}

	/**
	 * Gets the dataInstalacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The dataInstalacaoHidrometroAgua value
	 */
	public String getDataInstalacaoHidrometroAgua() {
		return dataInstalacaoHidrometroAgua;
	}

	/**
	 * Gets the dataInstalacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The dataInstalacaoHidrometroPoco value
	 */
	public String getDataInstalacaoHidrometroPoco() {
		return dataInstalacaoHidrometroPoco;
	}

	/**
	 * Gets the dataLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The dataLigacaoAgua value
	 */
	public String getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}

	/**
	 * Gets the dataLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The dataLigacaoEsgoto value
	 */
	public String getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	/**
	 * Gets the diametroHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The diametroHidrometroAgua value
	 */
	public String getDiametroHidrometroAgua() {
		return diametroHidrometroAgua;
	}

	/**
	 * Gets the diametroHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The diametroHidrometroPoco value
	 */
	public String getDiametroHidrometroPoco() {
		return diametroHidrometroPoco;
	}

	/**
	 * Gets the diametroLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The diametroLigacaoAgua value
	 */
	public String getDiametroLigacaoAgua() {
		return diametroLigacaoAgua;
	}

	/**
	 * Gets the diametroLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The diametroLigacaoEsgoto value
	 */
	public String getDiametroLigacaoEsgoto() {
		return diametroLigacaoEsgoto;
	}

	/**
	 * Gets the endereco attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The endereco value
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Gets the idGerenciaRegional attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The idGerenciaRegional value
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * Gets the idLocalidade attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The idLocalidade value
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * Gets the idSetorComercial attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The idSetorComercial value
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * Gets the indicadorCavaleteHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The indicadorCavaleteHidrometroAgua value
	 */
	public String getIndicadorCavaleteHidrometroAgua() {
		return indicadorCavaleteHidrometroAgua;
	}

	/**
	 * Gets the indicadorCavaleteHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The indicadorCavaleteHidrometroPoco value
	 */
	public String getIndicadorCavaleteHidrometroPoco() {
		return indicadorCavaleteHidrometroPoco;
	}

	/**
	 * Gets the indicadorImovelCondominio attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The indicadorImovelCondominio value
	 */
	public String getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}

	/**
	 * Gets the inscricao attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The inscricao value
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * Gets the localInstalacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The localInstalacaoHidrometroAgua value
	 */
	public String getLocalInstalacaoHidrometroAgua() {
		return localInstalacaoHidrometroAgua;
	}

	/**
	 * Gets the localInstalacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The localInstalacaoHidrometroPoco value
	 */
	public String getLocalInstalacaoHidrometroPoco() {
		return localInstalacaoHidrometroPoco;
	}

	/**
	 * Gets the marcaHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The marcaHidrometroAgua value
	 */
	public String getMarcaHidrometroAgua() {
		return marcaHidrometroAgua;
	}

	/**
	 * Gets the marcaHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The marcaHidrometroPoco value
	 */
	public String getMarcaHidrometroPoco() {
		return marcaHidrometroPoco;
	}

	/**
	 * Gets the materialLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The materialLigacaoAgua value
	 */
	public String getMaterialLigacaoAgua() {
		return materialLigacaoAgua;
	}

	/**
	 * Gets the materialLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The materialLigacaoEsgoto value
	 */
	public String getMaterialLigacaoEsgoto() {
		return materialLigacaoEsgoto;
	}

	/**
	 * Gets the matricula attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The matricula value
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Gets the matriculaImovelCondominio attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The matriculaImovelCondominio value
	 */
	public String getMatriculaImovelCondominio() {
		return matriculaImovelCondominio;
	}

	/**
	 * Gets the matriculaImovelPrincipal attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The matriculaImovelPrincipal value
	 */
	public String getMatriculaImovelPrincipal() {
		return matriculaImovelPrincipal;
	}

	/**
	 * Gets the mediaConsumo attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The mediaConsumo value
	 */
	public String getMediaConsumo() {
		return mediaConsumo;
	}

	/**
	 * Gets the mumeroHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The mumeroHidrometroAgua value
	 */
	public String getNumeroHidrometroAgua() {
		return numeroHidrometroAgua;
	}

	/**
	 * Gets the mumeroHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The mumeroHidrometroPoco value
	 */
	public String getNumeroHidrometroPoco() {
		return numeroHidrometroPoco;
	}

	/**
	 * Gets the nomeCliente attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The nomeCliente value
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * Gets the nomeClienteResponsavel attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The nomeClienteResponsavel value
	 */
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	/**
	 * Gets the nomeGerenciaRegional attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The nomeGerenciaRegional value
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * Gets the nomeLocalidade attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The nomeLocalidade value
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * Gets the nomeSetorComercial attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The nomeSetorComercial value
	 */
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	/**
	 * Gets the numeroMoradores attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The numeroMoradores value
	 */
	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	/**
	 * Gets the numeroPontos attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The numeroPontos value
	 */
	public String getNumeroPontos() {
		return numeroPontos;
	}

	/**
	 * Gets the pavimentoCalcada attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The pavimentoCalcada value
	 */
	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	/**
	 * Gets the pavimentoRua attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The pavimentoRua value
	 */
	public String getPavimentoRua() {
		return pavimentoRua;
	}

	/**
	 * Gets the percentualColeta attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The percentualColeta value
	 */
	public String getPercentualColeta() {
		return percentualColeta;
	}

	/**
	 * Gets the percentualEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The percentualEsgoto value
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	/**
	 * Gets the perfilImovel attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The perfilImovel value
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * Gets the poco attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The poco value
	 */
	public String getPoco() {
		return poco;
	}

	/**
	 * Gets the situacaoAgua attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The situacaoAgua value
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * Gets the situacaoEsgoto attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The situacaoEsgoto value
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * Gets the subcategoria attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The subcategoria value
	 */
	public String getSubcategoria() {
		return subcategoria;
	}

	/**
	 * Gets the tipoDespejo attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The tipoDespejo value
	 */
	public String getTipoDespejo() {
		return tipoDespejo;
	}

	/**
	 * Gets the tipoHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The tipoHidrometroAgua value
	 */
	public String getTipoHidrometroAgua() {
		return tipoHidrometroAgua;
	}

	/**
	 * Gets the tipoHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @return The tipoHidrometroPoco value
	 */
	public String getTipoHidrometroPoco() {
		return tipoHidrometroPoco;
	}

	/**
	 * Gets the tipoProtecaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The tipoProtecaoHidrometroAgua value
	 */
	public String getTipoProtecaoHidrometroAgua() {
		return tipoProtecaoHidrometroAgua;
	}

	/**
	 * Gets the tipoProtecaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The tipoProtecaoHidrometroPoco value
	 */
	public String getTipoProtecaoHidrometroPoco() {
		return tipoProtecaoHidrometroPoco;
	}

	/**
	 * Gets the volumePiscina attribute of the RelatorioManterImovelBean object
	 * 
	 * @return The volumePiscina value
	 */
	public String getVolumePiscina() {
		return volumePiscina;
	}

	/**
	 * Gets the volumeReservatorioInferior attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The volumeReservatorioInferior value
	 */
	public String getVolumeReservatorioInferior() {
		return volumeReservatorioInferior;
	}

	/**
	 * Gets the volumeReservatorioSuperior attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @return The volumeReservatorioSuperior value
	 */
	public String getVolumeReservatorioSuperior() {
		return volumeReservatorioSuperior;
	}

	/**
	 * Sets the volumeReservatorioSuperior attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param volumeReservatorioSuperior
	 *            The new volumeReservatorioSuperior value
	 */
	public void setVolumeReservatorioSuperior(String volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}

	/**
	 * Sets the volumeReservatorioInferior attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param volumeReservatorioInferior
	 *            The new volumeReservatorioInferior value
	 */
	public void setVolumeReservatorioInferior(String volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}

	/**
	 * Sets the volumePiscina attribute of the RelatorioManterImovelBean object
	 * 
	 * @param volumePiscina
	 *            The new volumePiscina value
	 */
	public void setVolumePiscina(String volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	/**
	 * Sets the tipoProtecaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param tipoProtecaoHidrometroPoco
	 *            The new tipoProtecaoHidrometroPoco value
	 */
	public void setTipoProtecaoHidrometroPoco(String tipoProtecaoHidrometroPoco) {
		this.tipoProtecaoHidrometroPoco = tipoProtecaoHidrometroPoco;
	}

	/**
	 * Sets the tipoProtecaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param tipoProtecaoHidrometroAgua
	 *            The new tipoProtecaoHidrometroAgua value
	 */
	public void setTipoProtecaoHidrometroAgua(String tipoProtecaoHidrometroAgua) {
		this.tipoProtecaoHidrometroAgua = tipoProtecaoHidrometroAgua;
	}

	/**
	 * Sets the tipoHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param tipoHidrometroPoco
	 *            The new tipoHidrometroPoco value
	 */
	public void setTipoHidrometroPoco(String tipoHidrometroPoco) {
		this.tipoHidrometroPoco = tipoHidrometroPoco;
	}

	/**
	 * Sets the tipoHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param tipoHidrometroAgua
	 *            The new tipoHidrometroAgua value
	 */
	public void setTipoHidrometroAgua(String tipoHidrometroAgua) {
		this.tipoHidrometroAgua = tipoHidrometroAgua;
	}

	/**
	 * Sets the tipoDespejo attribute of the RelatorioManterImovelBean object
	 * 
	 * @param tipoDespejo
	 *            The new tipoDespejo value
	 */
	public void setTipoDespejo(String tipoDespejo) {
		this.tipoDespejo = tipoDespejo;
	}

	/**
	 * Sets the subcategoria attribute of the RelatorioManterImovelBean object
	 * 
	 * @param subcategoria
	 *            The new subcategoria value
	 */
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * Sets the situacaoEsgoto attribute of the RelatorioManterImovelBean object
	 * 
	 * @param situacaoEsgoto
	 *            The new situacaoEsgoto value
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * Sets the situacaoAgua attribute of the RelatorioManterImovelBean object
	 * 
	 * @param situacaoAgua
	 *            The new situacaoAgua value
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * Sets the poco attribute of the RelatorioManterImovelBean object
	 * 
	 * @param poco
	 *            The new poco value
	 */
	public void setPoco(String poco) {
		this.poco = poco;
	}

	/**
	 * Sets the perfilImovel attribute of the RelatorioManterImovelBean object
	 * 
	 * @param perfilImovel
	 *            The new perfilImovel value
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	/**
	 * Sets the percentualEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param percentualEsgoto
	 *            The new percentualEsgoto value
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	/**
	 * Sets the percentualColeta attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param percentualColeta
	 *            The new percentualColeta value
	 */
	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	/**
	 * Sets the pavimentoRua attribute of the RelatorioManterImovelBean object
	 * 
	 * @param pavimentoRua
	 *            The new pavimentoRua value
	 */
	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	/**
	 * Sets the pavimentoCalcada attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param pavimentoCalcada
	 *            The new pavimentoCalcada value
	 */
	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	/**
	 * Sets the numeroPontos attribute of the RelatorioManterImovelBean object
	 * 
	 * @param numeroPontos
	 *            The new numeroPontos value
	 */
	public void setNumeroPontos(String numeroPontos) {
		this.numeroPontos = numeroPontos;
	}

	/**
	 * Sets the numeroMoradores attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param numeroMoradores
	 *            The new numeroMoradores value
	 */
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	/**
	 * Sets the nomeSetorComercial attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param nomeSetorComercial
	 *            The new nomeSetorComercial value
	 */
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	/**
	 * Sets the nomeLocalidade attribute of the RelatorioManterImovelBean object
	 * 
	 * @param nomeLocalidade
	 *            The new nomeLocalidade value
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * Sets the nomeGerenciaRegional attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param nomeGerenciaRegional
	 *            The new nomeGerenciaRegional value
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * Sets the nomeClienteResponsavel attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param nomeClienteResponsavel
	 *            The new nomeClienteResponsavel value
	 */
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	/**
	 * Sets the nomeCliente attribute of the RelatorioManterImovelBean object
	 * 
	 * @param nomeCliente
	 *            The new nomeCliente value
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Sets the mumeroHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param mumeroHidrometroPoco
	 *            The new mumeroHidrometroPoco value
	 */
	public void setMumeroHidrometroPoco(String mumeroHidrometroPoco) {
		this.numeroHidrometroPoco = mumeroHidrometroPoco;
	}

	/**
	 * Sets the mumeroHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param mumeroHidrometroAgua
	 *            The new mumeroHidrometroAgua value
	 */
	public void setMumeroHidrometroAgua(String mumeroHidrometroAgua) {
		this.numeroHidrometroAgua = mumeroHidrometroAgua;
	}

	/**
	 * Sets the mediaConsumo attribute of the RelatorioManterImovelBean object
	 * 
	 * @param mediaConsumo
	 *            The new mediaConsumo value
	 */
	public void setMediaConsumo(String mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	/**
	 * Sets the matriculaImovelPrincipal attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param matriculaImovelPrincipal
	 *            The new matriculaImovelPrincipal value
	 */
	public void setMatriculaImovelPrincipal(String matriculaImovelPrincipal) {
		this.matriculaImovelPrincipal = matriculaImovelPrincipal;
	}

	/**
	 * Sets the matriculaImovelCondominio attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param matriculaImovelCondominio
	 *            The new matriculaImovelCondominio value
	 */
	public void setMatriculaImovelCondominio(String matriculaImovelCondominio) {
		this.matriculaImovelCondominio = matriculaImovelCondominio;
	}

	/**
	 * Sets the matricula attribute of the RelatorioManterImovelBean object
	 * 
	 * @param matricula
	 *            The new matricula value
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Sets the materialLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param materialLigacaoEsgoto
	 *            The new materialLigacaoEsgoto value
	 */
	public void setMaterialLigacaoEsgoto(String materialLigacaoEsgoto) {
		this.materialLigacaoEsgoto = materialLigacaoEsgoto;
	}

	/**
	 * Sets the materialLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param materialLigacaoAgua
	 *            The new materialLigacaoAgua value
	 */
	public void setMaterialLigacaoAgua(String materialLigacaoAgua) {
		this.materialLigacaoAgua = materialLigacaoAgua;
	}

	/**
	 * Sets the marcaHidrometroPoco attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param marcaHidrometroPoco
	 *            The new marcaHidrometroPoco value
	 */
	public void setMarcaHidrometroPoco(String marcaHidrometroPoco) {
		this.marcaHidrometroPoco = marcaHidrometroPoco;
	}

	/**
	 * Sets the marcaHidrometroAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param marcaHidrometroAgua
	 *            The new marcaHidrometroAgua value
	 */
	public void setMarcaHidrometroAgua(String marcaHidrometroAgua) {
		this.marcaHidrometroAgua = marcaHidrometroAgua;
	}

	/**
	 * Sets the localInstalacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param localInstalacaoHidrometroPoco
	 *            The new localInstalacaoHidrometroPoco value
	 */
	public void setLocalInstalacaoHidrometroPoco(
			String localInstalacaoHidrometroPoco) {
		this.localInstalacaoHidrometroPoco = localInstalacaoHidrometroPoco;
	}

	/**
	 * Sets the localInstalacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param localInstalacaoHidrometroAgua
	 *            The new localInstalacaoHidrometroAgua value
	 */
	public void setLocalInstalacaoHidrometroAgua(
			String localInstalacaoHidrometroAgua) {
		this.localInstalacaoHidrometroAgua = localInstalacaoHidrometroAgua;
	}

	/**
	 * Sets the inscricao attribute of the RelatorioManterImovelBean object
	 * 
	 * @param inscricao
	 *            The new inscricao value
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * Sets the indicadorImovelCondominio attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param indicadorImovelCondominio
	 *            The new indicadorImovelCondominio value
	 */
	public void setIndicadorImovelCondominio(String indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	/**
	 * Sets the indicadorCavaleteHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param indicadorCavaleteHidrometroPoco
	 *            The new indicadorCavaleteHidrometroPoco value
	 */
	public void setIndicadorCavaleteHidrometroPoco(
			String indicadorCavaleteHidrometroPoco) {
		this.indicadorCavaleteHidrometroPoco = indicadorCavaleteHidrometroPoco;
	}

	/**
	 * Sets the indicadorCavaleteHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param indicadorCavaleteHidrometroAgua
	 *            The new indicadorCavaleteHidrometroAgua value
	 */
	public void setIndicadorCavaleteHidrometroAgua(
			String indicadorCavaleteHidrometroAgua) {
		this.indicadorCavaleteHidrometroAgua = indicadorCavaleteHidrometroAgua;
	}

	/**
	 * Sets the idSetorComercial attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param idSetorComercial
	 *            The new idSetorComercial value
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * Sets the idLocalidade attribute of the RelatorioManterImovelBean object
	 * 
	 * @param idLocalidade
	 *            The new idLocalidade value
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * Sets the idGerenciaRegional attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param idGerenciaRegional
	 *            The new idGerenciaRegional value
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * Sets the endereco attribute of the RelatorioManterImovelBean object
	 * 
	 * @param endereco
	 *            The new endereco value
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Sets the diametroLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param diametroLigacaoEsgoto
	 *            The new diametroLigacaoEsgoto value
	 */
	public void setDiametroLigacaoEsgoto(String diametroLigacaoEsgoto) {
		this.diametroLigacaoEsgoto = diametroLigacaoEsgoto;
	}

	/**
	 * Sets the diametroLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param diametroLigacaoAgua
	 *            The new diametroLigacaoAgua value
	 */
	public void setDiametroLigacaoAgua(String diametroLigacaoAgua) {
		this.diametroLigacaoAgua = diametroLigacaoAgua;
	}

	/**
	 * Sets the diametroHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param diametroHidrometroPoco
	 *            The new diametroHidrometroPoco value
	 */
	public void setDiametroHidrometroPoco(String diametroHidrometroPoco) {
		this.diametroHidrometroPoco = diametroHidrometroPoco;
	}

	/**
	 * Sets the diametroHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param diametroHidrometroAgua
	 *            The new diametroHidrometroAgua value
	 */
	public void setDiametroHidrometroAgua(String diametroHidrometroAgua) {
		this.diametroHidrometroAgua = diametroHidrometroAgua;
	}

	/**
	 * Sets the dataLigacaoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param dataLigacaoEsgoto
	 *            The new dataLigacaoEsgoto value
	 */
	public void setDataLigacaoEsgoto(String dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	/**
	 * Sets the dataLigacaoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param dataLigacaoAgua
	 *            The new dataLigacaoAgua value
	 */
	public void setDataLigacaoAgua(String dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	/**
	 * Sets the dataInstalacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param dataInstalacaoHidrometroPoco
	 *            The new dataInstalacaoHidrometroPoco value
	 */
	public void setDataInstalacaoHidrometroPoco(
			String dataInstalacaoHidrometroPoco) {
		this.dataInstalacaoHidrometroPoco = dataInstalacaoHidrometroPoco;
	}

	/**
	 * Sets the dataInstalacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param dataInstalacaoHidrometroAgua
	 *            The new dataInstalacaoHidrometroAgua value
	 */
	public void setDataInstalacaoHidrometroAgua(
			String dataInstalacaoHidrometroAgua) {
		this.dataInstalacaoHidrometroAgua = dataInstalacaoHidrometroAgua;
	}

	/**
	 * Sets the consumoMinimoEsgoto attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param consumoMinimoEsgoto
	 *            The new consumoMinimoEsgoto value
	 */
	public void setConsumoMinimoEsgoto(String consumoMinimoEsgoto) {
		this.consumoMinimoEsgoto = consumoMinimoEsgoto;
	}

	/**
	 * Sets the consumoMinimoAgua attribute of the RelatorioManterImovelBean
	 * object
	 * 
	 * @param consumoMinimoAgua
	 *            The new consumoMinimoAgua value
	 */
	public void setConsumoMinimoAgua(String consumoMinimoAgua) {
		this.consumoMinimoAgua = consumoMinimoAgua;
	}

	/**
	 * Sets the codigoClienteResponsavel attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param codigoClienteResponsavel
	 *            The new codigoClienteResponsavel value
	 */
	public void setCodigoClienteResponsavel(String codigoClienteResponsavel) {
		this.codigoClienteResponsavel = codigoClienteResponsavel;
	}

	/**
	 * Sets the codigoCliente attribute of the RelatorioManterImovelBean object
	 * 
	 * @param codigoCliente
	 *            The new codigoCliente value
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * Sets the capacidadeHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param capacidadeHidrometroPoco
	 *            The new capacidadeHidrometroPoco value
	 */
	public void setCapacidadeHidrometroPoco(String capacidadeHidrometroPoco) {
		this.capacidadeHidrometroPoco = capacidadeHidrometroPoco;
	}

	/**
	 * Sets the capacidadeHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param capacidadeHidrometroAgua
	 *            The new capacidadeHidrometroAgua value
	 */
	public void setCapacidadeHidrometroAgua(String capacidadeHidrometroAgua) {
		this.capacidadeHidrometroAgua = capacidadeHidrometroAgua;
	}

	/**
	 * Sets the areaConstruida attribute of the RelatorioManterImovelBean object
	 * 
	 * @param areaConstruida
	 *            The new areaConstruida value
	 */
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	/**
	 * Sets the anoFabricacaoHidrometroPoco attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param anoFabricacaoHidrometroPoco
	 *            The new anoFabricacaoHidrometroPoco value
	 */
	public void setAnoFabricacaoHidrometroPoco(
			String anoFabricacaoHidrometroPoco) {
		this.anoFabricacaoHidrometroPoco = anoFabricacaoHidrometroPoco;
	}

	/**
	 * Sets the anoFabricacaoHidrometroAgua attribute of the
	 * RelatorioManterImovelBean object
	 * 
	 * @param anoFabricacaoHidrometroAgua
	 *            The new anoFabricacaoHidrometroAgua value
	 */
	public void setAnoFabricacaoHidrometroAgua(
			String anoFabricacaoHidrometroAgua) {
		this.anoFabricacaoHidrometroAgua = anoFabricacaoHidrometroAgua;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getJardim() {
		return jardim;
	}

	public void setJardim(String jardim) {
		this.jardim = jardim;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public void setNumeroHidrometroAgua(String numeroHidrometroAgua) {
		this.numeroHidrometroAgua = numeroHidrometroAgua;
	}

	public void setNumeroHidrometroPoco(String numeroHidrometroPoco) {
		this.numeroHidrometroPoco = numeroHidrometroPoco;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	/**
	 * @return Retorna o campo tipoFaturamento.
	 */
	public String getTipoFaturamento() {
		return tipoFaturamento;
	}

	/**
	 * @param tipoFaturamento O tipoFaturamento a ser setado.
	 */
	public void setTipoFaturamento(String tipoFaturamento) {
		this.tipoFaturamento = tipoFaturamento;
	}

	/**
	 * @return Retorna o campo descricaoAbreviadaCategoria.
	 */
	public String getDescricaoAbreviadaCategoria() {
		return descricaoAbreviadaCategoria;
	}

	/**
	 * @param descricaoAbreviadaCategoria O descricaoAbreviadaCategoria a ser setado.
	 */
	public void setDescricaoAbreviadaCategoria(String descricaoAbreviadaCategoria) {
		this.descricaoAbreviadaCategoria = descricaoAbreviadaCategoria;
	}

	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	/**
	 * @return Retorna o campo idLogradouro.
	 */
	public String getIdLogradouro() {
		return idLogradouro;
	}

	/**
	 * @param idLogradouro O idLogradouro a ser setado.
	 */
	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	/**
	 * @return Retorna o campo fone.
	 */
	public String getFone() {
		return fone;
	}

	/**
	 * @param fone O fone a ser setado.
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * @return Retorna o campo testadaLote.
	 */
	public String getTestadaLote() {
		return testadaLote;
	}

	/**
	 * @param testadaLote O testadaLote a ser setado.
	 */
	public void setTestadaLote(String testadaLote) {
		this.testadaLote = testadaLote;
	}
	
}
