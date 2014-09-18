package gcom.arrecadacao.bean;

import gcom.arrecadacao.ArrecadacaoDadosDiarios;
import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.DevolucaoDadosDiarios;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.cache.HashtableCache;

/**
 * Classe helper para gerenciar os itens do acumulador de dados diarios de arrecadacao 
 * @author Francisco do Nascimento
 * @date   03/09/08
 *
 */
public class ArrecadacaoDadosDiariosAcumuladorHelper {

	// ArrecadacaoDadosDiariosItemAcumuladorHelper representa um item do acumulador
	private Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> colecaoArrecadacaoDadosDiariosValoresDiariosFinal 
		= new ArrayList<ArrecadacaoDadosDiariosItemAcumuladorHelper>();
	
	private BigDecimal valorLiquidoTotal = new BigDecimal("0.0");
	
	// 	constantes definidas no ArrecadacaoDadosDiariosItemAcumuladorHelper
	private int tipoAgrupamento = ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_DATA; 	
	
	public ArrecadacaoDadosDiariosAcumuladorHelper(int tipoAgrupamento) {
		super();
		
		this.tipoAgrupamento = tipoAgrupamento;
	}

	/**
	 * Recebe um ADDVDHelper para criar um item de acumulador helper e adiciona-lo a colecao
	 * de itens 'colecaoArrecadacaoDadosDiariosValoresDiariosFinal' 
	 *  
	 * @param arrecadacaoDadosDiariosHelper
	 */
	public void adicionarDadosDiarios(ArrecadacaoDadosDiariosValoresDiariosHelper arrecadacaoDadosDiariosHelper){
		
		ArrecadacaoDadosDiarios arrecadacaoDadosDiarios = arrecadacaoDadosDiariosHelper.getArrecadacaoDadosDiarios();
		DevolucaoDadosDiarios devolucaoDadosDiarios = arrecadacaoDadosDiariosHelper.getDevolucaoDadosDiarios();
		// com estes objetos de dados de arrecadacao e/ou devolucao dados diarios, 
		// será criado um objeto de itemAcumuladorHelper, com os dados identificadores do grupo 
		// (anomes, setor, quadra, hidrometro,datapagamento, rota, arrecadador, formaArrecadacao, etc.) 
		// e os somatorios e contadores de cada grupo (valorArrecadadoBruto, valorDescontos, valorDevolucoes, 
		// valorArrecadadoLiquido)
		
		if (colecaoArrecadacaoDadosDiariosValoresDiariosFinal.isEmpty()) {
			
			ArrecadacaoDadosDiariosItemAcumuladorHelper itemNovo = 
				new ArrecadacaoDadosDiariosItemAcumuladorHelper();

			if (arrecadacaoDadosDiarios != null){
				itemNovo.setAnoMesReferencia(arrecadacaoDadosDiarios.getAnoMesReferenciaArrecadacao());
				itemNovo.setCodigoSetorComercial(arrecadacaoDadosDiarios.getCodigoSetorComercial());
				itemNovo.setNumeroQuadra(arrecadacaoDadosDiarios.getNumeroQuadra());
				itemNovo.setIndicadorHidrometro(arrecadacaoDadosDiarios.getIndicadorHidrometro());
				itemNovo.setData(arrecadacaoDadosDiarios.getDataPagamento());
				itemNovo.setRota(arrecadacaoDadosDiarios.getRota());
				itemNovo.setArrecadador(arrecadacaoDadosDiarios.getArrecadador());
				itemNovo.setSetorComercial(arrecadacaoDadosDiarios.getSetorComercial());
				itemNovo.setArrecadacaoForma(arrecadacaoDadosDiarios.getArrecadacaoForma());
				itemNovo.setLigacaoEsgotoSituacao(arrecadacaoDadosDiarios.getLigacaoEsgotoSituacao());
				itemNovo.setDocumentoTipo(arrecadacaoDadosDiarios.getDocumentoTipo());
				itemNovo.setDocumentoTipoAgregador(arrecadacaoDadosDiarios.getDocumentoTipoAgregador());
				itemNovo.setEsferaPoder(arrecadacaoDadosDiarios.getEsferaPoder());
				itemNovo.setImovelPerfil(arrecadacaoDadosDiarios.getImovelPerfil());
				itemNovo.setQuadra(arrecadacaoDadosDiarios.getQuadra());
				itemNovo.setGerenciaRegional(arrecadacaoDadosDiarios.getGerenciaRegional());
				itemNovo.setUnidadeNegocio(arrecadacaoDadosDiarios.getUnidadeNegocio());
				itemNovo.setLocalidade(arrecadacaoDadosDiarios.getLocalidade());
				itemNovo.setLigacaoAguaSituacao(arrecadacaoDadosDiarios.getLigacaoAguaSituacao());
				itemNovo.setCategoria(arrecadacaoDadosDiarios.getCategoria());
	
				itemNovo.addQuantidadeDocumentos(arrecadacaoDadosDiarios.getQuantidadeDocumentos());
				itemNovo.addQuantidadePagamentos(arrecadacaoDadosDiarios.getQuantidadePagamentos());
	
				itemNovo.addValorArrecadadoBruto(arrecadacaoDadosDiarios.getValorPagamentos());
			} else if (devolucaoDadosDiarios != null){
				itemNovo.setAnoMesReferencia(devolucaoDadosDiarios.getAnoMesReferencia());
				itemNovo.setCodigoSetorComercial(devolucaoDadosDiarios.getCodigoSetorComercial());
				itemNovo.setNumeroQuadra(devolucaoDadosDiarios.getNumeroQuadra());
				itemNovo.setIndicadorHidrometro(devolucaoDadosDiarios.getIndicadorHidrometro());
				itemNovo.setData(devolucaoDadosDiarios.getDataDevolucao());
				itemNovo.setRota(devolucaoDadosDiarios.getRota());
				itemNovo.setArrecadador(devolucaoDadosDiarios.getArrecadador());
				itemNovo.setSetorComercial(devolucaoDadosDiarios.getSetorComercial());
				itemNovo.setArrecadacaoForma(devolucaoDadosDiarios.getArrecadacaoForma());
				itemNovo.setLigacaoEsgotoSituacao(devolucaoDadosDiarios.getLigacaoEsgotoSituacao());
				itemNovo.setDocumentoTipo(devolucaoDadosDiarios.getDocumentoTipo());
				itemNovo.setDocumentoTipoAgregador(devolucaoDadosDiarios.getDocumentoTipoAgregador());				
				itemNovo.setEsferaPoder(devolucaoDadosDiarios.getEsferaPoder());
				itemNovo.setImovelPerfil(devolucaoDadosDiarios.getImovelPerfil());
				itemNovo.setQuadra(devolucaoDadosDiarios.getQuadra());
				itemNovo.setGerenciaRegional(devolucaoDadosDiarios.getGerenciaRegional());
				itemNovo.setUnidadeNegocio(devolucaoDadosDiarios.getUnidadeNegocio());
				itemNovo.setLocalidade(devolucaoDadosDiarios.getLocalidade());
				itemNovo.setLigacaoAguaSituacao(devolucaoDadosDiarios.getLigacaoAguaSituacao());
				itemNovo.setCategoria(devolucaoDadosDiarios.getCategoria());
				
				itemNovo.addQuantidadeDocumentos(devolucaoDadosDiarios.getQuantidadeDocumentos());
				itemNovo.addQuantidadePagamentos(devolucaoDadosDiarios.getQuantidadeDevolucoes());
			}
			if (devolucaoDadosDiarios != null){
				if (devolucaoDadosDiarios.getDevolucaoTipo() != null){
					if (devolucaoDadosDiarios.getDevolucaoTipo().equals("C")
							|| devolucaoDadosDiarios.getDevolucaoTipo().equals("D")){
						itemNovo.addValorDescontos(devolucaoDadosDiarios.getValorDevolucoes());
					} else {
						itemNovo.addValorDevolucoes(devolucaoDadosDiarios.getValorDevolucoes());								
					}							
				}				
			}
			
			itemNovo.setValorArrecadadoLiquido(itemNovo.getValorArrecadadoBruto()
					.subtract(itemNovo.getValorDescontos()).subtract(itemNovo.getValorDevolucoes()));
			
			colecaoArrecadacaoDadosDiariosValoresDiariosFinal.add(itemNovo);
			
			valorLiquidoTotal = valorLiquidoTotal.add(itemNovo.getValorArrecadadoLiquido());
			
		} else {
			
			Iterator iteratorArrecadacaoDadosDiarios = colecaoArrecadacaoDadosDiariosValoresDiariosFinal
				.iterator();

			boolean achou = false;
			while (iteratorArrecadacaoDadosDiarios.hasNext()) {
				
				ArrecadacaoDadosDiariosItemAcumuladorHelper arrecadacaoItem =
					(ArrecadacaoDadosDiariosItemAcumuladorHelper) iteratorArrecadacaoDadosDiarios.next();
								
				if (arrecadacaoItem.equalsAgrupamento(arrecadacaoDadosDiarios, devolucaoDadosDiarios, 
						this.tipoAgrupamento)){
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoItem.addValorArrecadadoBruto(arrecadacaoDadosDiarios.getValorPagamentos());	
					}
					
					
					achou = true;
					
					if (devolucaoDadosDiarios != null){
						if (devolucaoDadosDiarios.getDevolucaoTipo() != null){
							if (devolucaoDadosDiarios.getDevolucaoTipo().equals("C")
									|| devolucaoDadosDiarios.getDevolucaoTipo().equals("D")){
								arrecadacaoItem.addValorDescontos(devolucaoDadosDiarios.getValorDevolucoes());
							} else {
								arrecadacaoItem.addValorDevolucoes(devolucaoDadosDiarios.getValorDevolucoes());								
							}							
						}				
					}

					if (arrecadacaoDadosDiarios != null){
						arrecadacaoItem.addQuantidadeDocumentos(arrecadacaoDadosDiarios.getQuantidadeDocumentos());
						arrecadacaoItem.addQuantidadePagamentos(arrecadacaoDadosDiarios.getQuantidadePagamentos());
					}
					if (devolucaoDadosDiarios != null){
						arrecadacaoItem.addQuantidadeDocumentos(devolucaoDadosDiarios.getQuantidadeDocumentos());
						arrecadacaoItem.addQuantidadePagamentos(devolucaoDadosDiarios.getQuantidadeDevolucoes());
					}
					
					BigDecimal novoValorArrecadadoLiquido = arrecadacaoItem.getValorArrecadadoBruto()
						.subtract(arrecadacaoItem.getValorDescontos())
						.subtract(arrecadacaoItem.getValorDevolucoes());
										
					valorLiquidoTotal = valorLiquidoTotal.subtract(arrecadacaoItem.getValorArrecadadoLiquido())
						.add(novoValorArrecadadoLiquido);
					
					arrecadacaoItem.setValorArrecadadoLiquido(novoValorArrecadadoLiquido);
					
				}
								
			}
			
			if (!achou){
				ArrecadacaoDadosDiariosItemAcumuladorHelper itemNovo = 
					new ArrecadacaoDadosDiariosItemAcumuladorHelper();
							
				if (arrecadacaoDadosDiarios != null){
					itemNovo.setAnoMesReferencia(arrecadacaoDadosDiarios.getAnoMesReferenciaArrecadacao());
					itemNovo.setCodigoSetorComercial(arrecadacaoDadosDiarios.getCodigoSetorComercial());
					itemNovo.setNumeroQuadra(arrecadacaoDadosDiarios.getNumeroQuadra());
					itemNovo.setIndicadorHidrometro(arrecadacaoDadosDiarios.getIndicadorHidrometro());
					itemNovo.setData(arrecadacaoDadosDiarios.getDataPagamento());
					itemNovo.setRota(arrecadacaoDadosDiarios.getRota());
					itemNovo.setArrecadador(arrecadacaoDadosDiarios.getArrecadador());
					itemNovo.setSetorComercial(arrecadacaoDadosDiarios.getSetorComercial());
					itemNovo.setArrecadacaoForma(arrecadacaoDadosDiarios.getArrecadacaoForma());
					itemNovo.setLigacaoEsgotoSituacao(arrecadacaoDadosDiarios.getLigacaoEsgotoSituacao());
					itemNovo.setDocumentoTipo(arrecadacaoDadosDiarios.getDocumentoTipo());
					itemNovo.setDocumentoTipoAgregador(arrecadacaoDadosDiarios.getDocumentoTipoAgregador());
					itemNovo.setEsferaPoder(arrecadacaoDadosDiarios.getEsferaPoder());
					itemNovo.setImovelPerfil(arrecadacaoDadosDiarios.getImovelPerfil());
					itemNovo.setQuadra(arrecadacaoDadosDiarios.getQuadra());
					itemNovo.setGerenciaRegional(arrecadacaoDadosDiarios.getGerenciaRegional());
					itemNovo.setUnidadeNegocio(arrecadacaoDadosDiarios.getUnidadeNegocio());
					itemNovo.setLocalidade(arrecadacaoDadosDiarios.getLocalidade());
					itemNovo.setLigacaoAguaSituacao(arrecadacaoDadosDiarios.getLigacaoAguaSituacao());
					itemNovo.setCategoria(arrecadacaoDadosDiarios.getCategoria());
					
					itemNovo.addQuantidadeDocumentos(arrecadacaoDadosDiarios.getQuantidadeDocumentos());
					itemNovo.addQuantidadePagamentos(arrecadacaoDadosDiarios.getQuantidadePagamentos());
	
					itemNovo.addValorArrecadadoBruto(arrecadacaoDadosDiarios.getValorPagamentos());
				} else if (devolucaoDadosDiarios != null){
					itemNovo.setAnoMesReferencia(devolucaoDadosDiarios.getAnoMesReferencia());
					itemNovo.setCodigoSetorComercial(devolucaoDadosDiarios.getCodigoSetorComercial());
					itemNovo.setNumeroQuadra(devolucaoDadosDiarios.getNumeroQuadra());
					itemNovo.setIndicadorHidrometro(devolucaoDadosDiarios.getIndicadorHidrometro());
					itemNovo.setData(devolucaoDadosDiarios.getDataDevolucao());
					itemNovo.setRota(devolucaoDadosDiarios.getRota());
					itemNovo.setArrecadador(devolucaoDadosDiarios.getArrecadador());
					itemNovo.setSetorComercial(devolucaoDadosDiarios.getSetorComercial());
					itemNovo.setArrecadacaoForma(devolucaoDadosDiarios.getArrecadacaoForma());
					itemNovo.setLigacaoEsgotoSituacao(devolucaoDadosDiarios.getLigacaoEsgotoSituacao());
					itemNovo.setDocumentoTipo(devolucaoDadosDiarios.getDocumentoTipo());
					itemNovo.setDocumentoTipoAgregador(devolucaoDadosDiarios.getDocumentoTipoAgregador());
					itemNovo.setEsferaPoder(devolucaoDadosDiarios.getEsferaPoder());
					itemNovo.setImovelPerfil(devolucaoDadosDiarios.getImovelPerfil());
					itemNovo.setQuadra(devolucaoDadosDiarios.getQuadra());
					itemNovo.setGerenciaRegional(devolucaoDadosDiarios.getGerenciaRegional());
					itemNovo.setUnidadeNegocio(devolucaoDadosDiarios.getUnidadeNegocio());
					itemNovo.setLocalidade(devolucaoDadosDiarios.getLocalidade());
					itemNovo.setLigacaoAguaSituacao(devolucaoDadosDiarios.getLigacaoAguaSituacao());
					itemNovo.setCategoria(devolucaoDadosDiarios.getCategoria());
					
					itemNovo.addQuantidadeDocumentos(devolucaoDadosDiarios.getQuantidadeDocumentos());
					itemNovo.addQuantidadePagamentos(devolucaoDadosDiarios.getQuantidadeDevolucoes());
					
				}
				if (devolucaoDadosDiarios != null){
					if (devolucaoDadosDiarios.getDevolucaoTipo() != null){
						if (devolucaoDadosDiarios.getDevolucaoTipo().equals("C")
								|| devolucaoDadosDiarios.getDevolucaoTipo().equals("D")){
							itemNovo.addValorDescontos(devolucaoDadosDiarios.getValorDevolucoes());
						} else {
							itemNovo.addValorDevolucoes(devolucaoDadosDiarios.getValorDevolucoes());								
						}							
					}				
				}
				
				itemNovo.setValorArrecadadoLiquido(itemNovo.getValorArrecadadoBruto()
						.subtract(itemNovo.getValorDescontos())
						.subtract(itemNovo.getValorDevolucoes()));
				
				colecaoArrecadacaoDadosDiariosValoresDiariosFinal.add(itemNovo);
				
				valorLiquidoTotal = valorLiquidoTotal.add(itemNovo.getValorArrecadadoLiquido());
								
			}
			
		}	
	}

//	public Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> getColecaoArrecadacaoDadosDiariosValoresDiariosFinal() {
//		return colecaoArrecadacaoDadosDiariosValoresDiariosFinal;
//	}

	public BigDecimal getValorLiquidoTotal() {
		return valorLiquidoTotal;
	}
	
	public Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> aplicarFiltroEAcumularValores(
		Collection<ArrecadacaoDadosDiariosValoresDiariosHelper> colecaoArrecadacaoDadosDiarios,
		String anoMesReferenciaFiltro,
		String idLocalidadeFiltro, String idEloFiltro, String idGerenciaRegionalFiltro,
		String idUnidadeNegocioFiltro, String idArrecadadorFiltro, String idArrecadacaoFormaFiltro, String idCategoriaFiltro,
		String idImovelPerfilFiltro, String idDocumentoTipoFiltro, String idDocumentoTipoAgregadorFiltro,
		boolean carregarArrecadadores, boolean carregarGerencias, boolean carregarCategorias, 
		boolean carregarImoveisPerfil, boolean carregarDocumentoTipo){
		
		Fachada fachada = Fachada.getInstancia();
		
		this.colecaoArrecadacaoDadosDiariosValoresDiariosFinal.clear();
		this.colecaoArrecadacaoDadosDiariosValoresDiariosFinal = 
			new ArrayList<ArrecadacaoDadosDiariosItemAcumuladorHelper>();
		
        Iterator iteratorColecaoDadosDiarios = colecaoArrecadacaoDadosDiarios.iterator();
        
        ArrecadacaoDadosDiariosValoresDiariosHelper arrecadacaoDadosDiariosHelper = null;
        
        HashtableCache cacheUnidadeNegocio = new HashtableCache("UN");
        HashtableCache cacheArrecadador = new HashtableCache("Arr");
        HashtableCache cacheLocalidade = new HashtableCache("Loca"); 
        HashtableCache cacheArrecadacaoForma = new HashtableCache("AF");
        HashtableCache cacheDocumentoTipo = new HashtableCache("DT");
        HashtableCache cacheGerenciaRegional = new HashtableCache("GR");
        HashtableCache cacheCategoria = new HashtableCache("CATG");
        HashtableCache cacheImovelPerfil = new HashtableCache("ImovPerf");
        
        while (iteratorColecaoDadosDiarios.hasNext()) {
			
        	arrecadacaoDadosDiariosHelper = (ArrecadacaoDadosDiariosValoresDiariosHelper) iteratorColecaoDadosDiarios.next();

			ArrecadacaoDadosDiarios arrecadacaoDadosDiarios = 
				arrecadacaoDadosDiariosHelper.getArrecadacaoDadosDiarios();
			DevolucaoDadosDiarios devolucaoDadosDiarios = 
				arrecadacaoDadosDiariosHelper.getDevolucaoDadosDiarios();
        	
			Integer anoMesReferenciaDadosDiarios = null;
			Integer idLocalidadeDadosDiarios = null;
			Integer idEloDadosDiarios = null;
			Integer idGerenciaRegionalDadosDiarios = null;
			Integer idUnidadeNegocioDadosDiarios = null;
			Integer idArrecadadorDadosDiarios = null;
			Integer idArrecadacaoFormaDadosDiarios = null;
			Integer idCategoriaDadosDiarios = null;
			Integer idImovelPerfilDadosDiarios = null;
			Integer idDocumentoTipoDadosDiarios = null;
			Integer idDocumentoTipoAgregadorDadosDiarios = null;
			
			if (arrecadacaoDadosDiarios != null){
				
				anoMesReferenciaDadosDiarios = arrecadacaoDadosDiarios.getAnoMesReferenciaArrecadacao();
				idEloDadosDiarios = arrecadacaoDadosDiarios.getLocalidade().getLocalidade().getId();
				idLocalidadeDadosDiarios = arrecadacaoDadosDiarios.getLocalidade().getId();
				idGerenciaRegionalDadosDiarios = arrecadacaoDadosDiarios.getGerenciaRegional().getId();
				idUnidadeNegocioDadosDiarios = arrecadacaoDadosDiarios.getUnidadeNegocio().getId();
				idArrecadadorDadosDiarios = arrecadacaoDadosDiarios.getArrecadador().getId();
				idArrecadacaoFormaDadosDiarios = arrecadacaoDadosDiarios.getArrecadacaoForma() != null ?
						arrecadacaoDadosDiarios.getArrecadacaoForma().getId() : null; 
				idCategoriaDadosDiarios = arrecadacaoDadosDiarios.getCategoria().getId();
				
				idImovelPerfilDadosDiarios = arrecadacaoDadosDiarios.getImovelPerfil() != null ? 
					arrecadacaoDadosDiarios.getImovelPerfil().getId() : null;
				
				idDocumentoTipoDadosDiarios = arrecadacaoDadosDiarios.getDocumentoTipo() != null ?
					arrecadacaoDadosDiarios.getDocumentoTipo().getId() : null;
				
				idDocumentoTipoAgregadorDadosDiarios = arrecadacaoDadosDiarios.getDocumentoTipoAgregador() != null ?
					arrecadacaoDadosDiarios.getDocumentoTipoAgregador().getId() : null;
				
			} else if (arrecadacaoDadosDiariosHelper.getDevolucaoDadosDiarios() != null){
				
				anoMesReferenciaDadosDiarios = devolucaoDadosDiarios.getAnoMesReferencia();
				idEloDadosDiarios = devolucaoDadosDiarios.getLocalidade().getLocalidade().getId();
				idLocalidadeDadosDiarios = devolucaoDadosDiarios.getLocalidade().getId();
				idGerenciaRegionalDadosDiarios = devolucaoDadosDiarios.getGerenciaRegional().getId();
				idUnidadeNegocioDadosDiarios = devolucaoDadosDiarios.getUnidadeNegocio().getId();
				idArrecadadorDadosDiarios = devolucaoDadosDiarios.getArrecadador().getId();
				idArrecadacaoFormaDadosDiarios = devolucaoDadosDiarios.getArrecadacaoForma() != null ? 
					devolucaoDadosDiarios.getArrecadacaoForma().getId() : null;
				idCategoriaDadosDiarios = devolucaoDadosDiarios.getCategoria().getId();
				idImovelPerfilDadosDiarios = devolucaoDadosDiarios.getImovelPerfil() != null ? 
					devolucaoDadosDiarios.getImovelPerfil().getId() : null;
				idDocumentoTipoDadosDiarios = devolucaoDadosDiarios.getDocumentoTipo() != null ? 
					devolucaoDadosDiarios.getDocumentoTipo().getId() : null;
				idDocumentoTipoAgregadorDadosDiarios = devolucaoDadosDiarios.getDocumentoTipoAgregador() != null ? 
					devolucaoDadosDiarios.getDocumentoTipoAgregador().getId() : null;
					
			}
			
			if (idDocumentoTipoAgregadorDadosDiarios == null){
				idDocumentoTipoAgregadorDadosDiarios = 0;
			}
			
//			 Carregando objetos complementares
			if (idGerenciaRegionalFiltro != null){ // carregar unidade de negocio
				UnidadeNegocio unidadeNegocio = null;
				if (idUnidadeNegocioDadosDiarios != null){
					
					unidadeNegocio = (UnidadeNegocio) cacheUnidadeNegocio.get(idUnidadeNegocioDadosDiarios);
					
					if (unidadeNegocio == null){
						//pesquisar na base a gerencia
						FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
						
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,
								idUnidadeNegocioDadosDiarios));
						
						Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
								UnidadeNegocio.class.getName());
						
						unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio.iterator().next();
											
						cacheUnidadeNegocio.put(idUnidadeNegocioDadosDiarios, unidadeNegocio);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setUnidadeNegocio(unidadeNegocio);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setUnidadeNegocio(unidadeNegocio);
					}
				}
				
			}
			
			if (carregarArrecadadores){ // @TODO implementar a verificar se é pra carregar o ARRECADADOR 
				
				Arrecadador arrecadador = null;
				if (idArrecadadorDadosDiarios != null){
					
					arrecadador = (Arrecadador) cacheArrecadador.get(idArrecadadorDadosDiarios);
					
					if (arrecadador == null){
						//pesquisar na base o arrecadador
						FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
						filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID,
								idArrecadadorDadosDiarios));
						filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
						
						Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
								Arrecadador.class.getName());
						
						arrecadador = (Arrecadador)colecaoArrecadador.iterator().next();
												
						cacheArrecadador.put(idArrecadadorDadosDiarios, arrecadador);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setArrecadador(arrecadador);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setArrecadador(arrecadador);
					}
				}
				
			}
			
			if (carregarGerencias){  
				
				GerenciaRegional gerenciaRegional = null;
				if (idGerenciaRegionalDadosDiarios != null){
					
					gerenciaRegional = (GerenciaRegional) cacheGerenciaRegional.get(idGerenciaRegionalDadosDiarios);
					
					if (gerenciaRegional == null){
						//pesquisar na base a gerencia Regional
						FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional ();
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,
								idGerenciaRegionalDadosDiarios));
						
						Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
								GerenciaRegional.class.getName());
						
						gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.iterator().next();
												
						cacheGerenciaRegional.put(idGerenciaRegionalDadosDiarios, gerenciaRegional);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setGerenciaRegional(gerenciaRegional);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setGerenciaRegional(gerenciaRegional);
					}
				}
				
			}
			
			if (carregarCategorias){  
				
				Categoria categoria = null;
				if (idCategoriaDadosDiarios != null){
					
					categoria = (Categoria) cacheCategoria.get(idCategoriaDadosDiarios);
					
					if (categoria == null){
						//pesquisar na base a Categoria
						FiltroCategoria filtroCategoria = new FiltroCategoria();
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
								idCategoriaDadosDiarios));
						
						Collection colecaoCategorias = fachada.pesquisar(filtroCategoria,
								Categoria.class.getName());
						
						categoria = (Categoria) colecaoCategorias.iterator().next();
												
						cacheCategoria.put(idCategoriaDadosDiarios, categoria);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setCategoria(categoria);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setCategoria(categoria);
					}
				}
				
			}
			
			if (carregarImoveisPerfil){  
				
				ImovelPerfil imovelPerfil= null;
				if (idImovelPerfilDadosDiarios != null){
					
					imovelPerfil = (ImovelPerfil) cacheImovelPerfil.get(idImovelPerfilDadosDiarios);
					
					if (imovelPerfil == null){
						//pesquisar na base o Imovel Perfil
						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,
								idImovelPerfilDadosDiarios));
						
						Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
								ImovelPerfil.class.getName());
						
						imovelPerfil = (ImovelPerfil) colecaoImovelPerfil.iterator().next();
												
						cacheImovelPerfil.put(idImovelPerfilDadosDiarios, imovelPerfil);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setImovelPerfil(imovelPerfil);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setImovelPerfil(imovelPerfil);
					}
				}
				
			}

			if (idArrecadadorFiltro != null){  
				
				ArrecadacaoForma arrecadacaoForma = null;
				if (idArrecadacaoFormaDadosDiarios != null){
					
					arrecadacaoForma = (ArrecadacaoForma) cacheArrecadacaoForma.get(idArrecadacaoFormaDadosDiarios);
					
					if (arrecadacaoForma == null){
						//pesquisar na base o arrecadacao forma
						FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
						filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO,
								idArrecadacaoFormaDadosDiarios));
						
						Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,
								ArrecadacaoForma.class.getName());
						
						if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()){
							arrecadacaoForma = (ArrecadacaoForma)colecaoArrecadacaoForma.iterator().next();
							cacheArrecadacaoForma.put(idArrecadacaoFormaDadosDiarios, arrecadacaoForma);
						}												
												
					} 
					
				} 
				if (arrecadacaoForma == null) {
					arrecadacaoForma = new ArrecadacaoForma();
					arrecadacaoForma.setId(0);
					arrecadacaoForma.setDescricao("Sem forma de arrecadação");
					cacheArrecadacaoForma.put(arrecadacaoForma.getId(), arrecadacaoForma);					
				}
				
				if (arrecadacaoDadosDiarios != null){
					arrecadacaoDadosDiarios.setArrecadacaoForma(arrecadacaoForma);
				}
				if (devolucaoDadosDiarios != null){
					devolucaoDadosDiarios.setArrecadacaoForma(arrecadacaoForma);
				}
				
			}
			
			
			if (idEloFiltro != null || idGerenciaRegionalFiltro != null || idUnidadeNegocioFiltro != null){
				
				Localidade localidade = null;
				
				if (idEloDadosDiarios != null){
					localidade = (Localidade) cacheLocalidade.get(idEloDadosDiarios);
					if (localidade == null){
						// pesquisar na base a localidade
						FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
						filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA,
							idGerenciaRegionalDadosDiarios));
						filtroLocalidade.adicionarParametro(new ParametroSimples("localidade.id",
							idEloDadosDiarios));
						filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
						
						Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
								Localidade.class.getName());
						
						localidade = (Localidade)colecaoLocalidade.iterator().next();
						
						cacheLocalidade.put(idEloDadosDiarios, localidade);						
					}
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setLocalidade(localidade);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setLocalidade(localidade);
					}
				}
			}
			
			// se o tipo de documento agregador vier preenchido no filtro, carregar os documentos tipos para 
			// exibir as descricoes destes
			if (carregarDocumentoTipo || idDocumentoTipoAgregadorFiltro != null){
				DocumentoTipo documentoTipo = null;
				if (idDocumentoTipoDadosDiarios != null){
					
					documentoTipo = (DocumentoTipo) cacheDocumentoTipo.get(idDocumentoTipoDadosDiarios);
					
					if (documentoTipo == null){
						//pesquisar na base o Documento Tipo
						FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
						filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID,
								idDocumentoTipoDadosDiarios));
						
						Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo,
								DocumentoTipo.class.getName());
						
						documentoTipo = (DocumentoTipo) colecaoDocumentoTipo.iterator().next();
												
						cacheDocumentoTipo.put(idDocumentoTipoDadosDiarios, documentoTipo);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setDocumentoTipo(documentoTipo);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setDocumentoTipo(documentoTipo);
					}
				} 

			}
			
			if (carregarDocumentoTipo || idDocumentoTipoAgregadorFiltro != null){
				DocumentoTipo documentoTipo = null;
				if (idDocumentoTipoAgregadorDadosDiarios != null){
					
					documentoTipo = (DocumentoTipo) cacheDocumentoTipo.get(idDocumentoTipoAgregadorDadosDiarios);
					
					if (documentoTipo == null){
						//pesquisar na base o Documento Tipo
						FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
						filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID,
								idDocumentoTipoAgregadorDadosDiarios));
						
						Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo,
								DocumentoTipo.class.getName());
						
						if (colecaoDocumentoTipo != null && !colecaoDocumentoTipo.isEmpty()){
							documentoTipo = (DocumentoTipo) colecaoDocumentoTipo.iterator().next();	
						} else {
							documentoTipo = new DocumentoTipo();
							documentoTipo.setDescricaoDocumentoTipo("Sem tipo de documento");
							documentoTipo.setId(0);							
						}
						
												
						cacheDocumentoTipo.put(idDocumentoTipoAgregadorDadosDiarios, documentoTipo);						
					}
					
					if (arrecadacaoDadosDiarios != null){
						arrecadacaoDadosDiarios.setDocumentoTipoAgregador(documentoTipo);
					}
					if (devolucaoDadosDiarios != null){
						devolucaoDadosDiarios.setDocumentoTipoAgregador(documentoTipo);
					}
				} 

			}
			// Efetuando a filtragem dos itens que serao usados no acumulador
			// adicionar no objeto acumulador
			if(idEloFiltro != null && idLocalidadeFiltro == null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idEloDadosDiarios.toString().equals(idEloFiltro)){
				
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
				
				}
			}else if(idEloFiltro != null && idLocalidadeFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idLocalidadeDadosDiarios.toString().equals(idLocalidadeFiltro)
						&& idEloDadosDiarios.toString().equals(idEloFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);

				}

			// GERENCIA E UNIDADE DE NEGOCIO
			}else if(idGerenciaRegionalFiltro != null && idUnidadeNegocioFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idGerenciaRegionalDadosDiarios.toString().equals(idGerenciaRegionalFiltro) 
						&& idUnidadeNegocioDadosDiarios.toString().equals(idUnidadeNegocioFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}
			// SÓ GERENCIA 				
			}else if(idGerenciaRegionalFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idGerenciaRegionalDadosDiarios.toString().equals(idGerenciaRegionalFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}
			// SÓ UNIDADE DE NEGOCIO
			}else if(idUnidadeNegocioFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idUnidadeNegocioDadosDiarios.toString().equals(idUnidadeNegocioFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}
			// ARRECADADOR E ARRECADACAO_FORMA
			}else if(idArrecadadorFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& (idArrecadadorDadosDiarios.toString().equals(idArrecadadorFiltro)
							|| idArrecadadorFiltro.equals("-1"))){
				
					if (idArrecadacaoFormaFiltro != null){
						if(idArrecadacaoFormaDadosDiarios.toString().equals(idArrecadacaoFormaFiltro)){
							this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
						}
					} else {
						this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);	
					}										
				}				
			}else if(idCategoriaFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idCategoriaDadosDiarios.toString().equals(idCategoriaFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}

			}else if(idImovelPerfilFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idImovelPerfilDadosDiarios != null
						&& idImovelPerfilDadosDiarios.toString().equals(idImovelPerfilFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}
			
			}else if(idDocumentoTipoAgregadorFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idDocumentoTipoAgregadorDadosDiarios.toString().equals(idDocumentoTipoAgregadorFiltro)){
					
					if(idDocumentoTipoFiltro != null){
						if(idDocumentoTipoDadosDiarios.toString().equals(idDocumentoTipoFiltro)){
							this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
						}
					} else {
						this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);		
					}
					
				}				
				
			}else if(idDocumentoTipoFiltro != null){
				if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro) 
						&& idDocumentoTipoDadosDiarios.toString().equals(idDocumentoTipoFiltro)){
					
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
					
				}
				
			}else{
			
				if (anoMesReferenciaFiltro != null){
					if(anoMesReferenciaDadosDiarios.toString().equals(anoMesReferenciaFiltro)){						
						this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);						
					}					
				} else {
					this.adicionarDadosDiarios(arrecadacaoDadosDiariosHelper);
				}
			
			}
					
		}

		return this.colecaoArrecadacaoDadosDiariosValoresDiariosFinal;
	}
	
}

