package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * 
 * @author Vivianne Sousa
 * @date 15/09/2006
 */

public class Relatorio2ViaConta extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;

	/**
	 * Quantidade máxima de linhas do detail da primeira página da conta
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 13;
	
	/**
	 * Quantidade máxima de linhas do detail a partir da segunda página do relátorio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 43;
	
	/**
	 * Quantidade máxima de linhas do detail da primeira página do boleto bancario
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 8;
		
	public Relatorio2ViaConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA);
	}
	
	public Relatorio2ViaConta(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public Relatorio2ViaConta() {
		super(null, "");
	}
	
	
	protected Collection<Relatorio2ViaContaBean> inicializarBeanRelatorio(
			Collection colecaoEmitirContaHelper, SistemaParametro sistemaParametro, String empresa, Fachada fachada) {
		
		String enderecoEmpresa = null;
		
		Collection<Relatorio2ViaContaBean> retorno = new ArrayList<Relatorio2ViaContaBean>();
		String cedente = sistemaParametro.getNomeAbreviadoEmpresa() + "-" + sistemaParametro.getNomeEmpresa();
		
		Iterator iter = colecaoEmitirContaHelper.iterator();
		while (iter.hasNext()) {
			
			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) iter.next();
			
			enderecoEmpresa = null;
			
			if(sistemaParametro.getNomeAbreviadoEmpresa().toString().equals("CAEMA")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID,
						emitirContaHelper.getIdLocalidade()));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				Collection cLocalidade = 
					(Collection) fachada.pesquisar(
						filtroLocalidade,Localidade.class.getName());
				
				Localidade localidade = (Localidade) cLocalidade.iterator().next();
				
				String numeroFatura = emitirContaHelper.getIdConta()+ "/" + 
					Util.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper.getAmReferencia());
				String endereco = localidade.getEnderecoFormatadoTituloAbreviado();
				String telefone = Util.completaString(localidade.getFone(), 9);
				String cnpj = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
				String inscricaoEstadual = Util.formatarInscricaoEstadualCaema(sistemaParametro.getInscricaoEstadual());
				String dataEmissao = Util.formatarData(new Date());
				/*
				empresa = "Nº Fatura:"+numeroFatura+"    "+endereco+"\n"+
					"Emitida em:"+dataEmissao+"   Cnpj:"+cnpj+"    Fone:"+telefone+"\n" +
					" Insc. Estadual:"+inscricaoEstadual;
				*/
				enderecoEmpresa = "NºFatura:"+numeroFatura+"\n"+ 
				"Emitida em:"+dataEmissao+"\n";
				
				empresa = endereco+"\n"+
					"Cnpj:"+cnpj+"  Fone:"+telefone+"\n" +
					"Insc. Estadual:"+inscricaoEstadual;
				
				
			}
			
			String codigoRota = null;
			
			if(emitirContaHelper.getRotaEntrega()!= null &&	!emitirContaHelper.getRotaEntrega().equals("")){
				codigoRota = emitirContaHelper.getRotaEntrega();
			}
			
			String debitoCreditoSituacaoAtualConta = "";
			if (emitirContaHelper.getDebitoCreditoSituacaoAtualConta()!= null){
				debitoCreditoSituacaoAtualConta = emitirContaHelper.getDebitoCreditoSituacaoAtualConta().toString();
			}
			
			String contaPaga = null;
			if(emitirContaHelper.getContaPaga() != null && !emitirContaHelper.getContaPaga().equals("")){
				contaPaga = emitirContaHelper.getContaPaga();
			}
			
			String enderecoClienteResponsavel = "";
			String enderecoClienteResponsavelLinha2 = "";
			if(emitirContaHelper.getEnderecoClienteResponsavel() != null && !emitirContaHelper.getEnderecoClienteResponsavel().equals("")){
				
				if(emitirContaHelper.getEnderecoClienteResponsavel().length() >= 64){
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel().substring(0,64);
					enderecoClienteResponsavelLinha2 = emitirContaHelper.getEnderecoClienteResponsavel().substring(64);
				}else{
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel();
				}
			}
			
//			String cpfCnpj = "" ;
//			
//			if ( emitirContaHelper.getCpf() != null 
//					&& !emitirContaHelper.getCpf().equals("") ) {
//				cpfCnpj = emitirContaHelper.getCpf();
//			} else if ( emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("") ) {
//				cpfCnpj = emitirContaHelper.getCnpj();
//			}
//			
			Collection colecaolinhasDescricaoServicosTarifasTotal = emitirContaHelper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();

			Collection<Relatorio2ViaContaDetailBean> colecaoDetail = new ArrayList<Relatorio2ViaContaDetailBean>();
			Collection<Relatorio2ViaContaBoletoBancarioBean> colecaoBoleto = new ArrayList<Relatorio2ViaContaBoletoBancarioBean>();

			int totalLinhasRelatorio = 0;
			int totalPaginasRelatorio = 1;
			int indicadorPrimeiraPagina = 1;

			String dataVencimentoFormatada = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
			
			Boolean ehBoleto = false;
			int boleto = 2;
			int numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA;
			
			if((sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)
				&& emitirContaHelper.getValorConta()!= null && emitirContaHelper.getValorConta().
				compareTo(EmitirContaHelper.VALOR_LIMITE_FICHA_COMPENSACAO) == 1) 
				|| (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)
					&& sistemaParametro.getValorContaFichaComp() != null 
					&& !sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))
					&& emitirContaHelper.getValorConta()!= null 
					&& emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) == 1) ){
				
				ehBoleto = true;
				numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
				
			}
			
			Iterator iterator = colecaolinhasDescricaoServicosTarifasTotal.iterator();		
			while (iterator.hasNext()) {
			
				ContaLinhasDescricaoServicosTarifasTotalHelper linhasDescricaoServicosTarifasTotalHelper 
					= (ContaLinhasDescricaoServicosTarifasTotalHelper) iterator.next();
				
				Relatorio2ViaContaDetailBean relatorio2ViaContaDetailBean = 
					new Relatorio2ViaContaDetailBean(linhasDescricaoServicosTarifasTotalHelper);
				
				colecaoDetail.add(relatorio2ViaContaDetailBean);
				totalLinhasRelatorio = totalLinhasRelatorio + 1;
				
				if ((totalLinhasRelatorio== numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					
						if (ehBoleto){
							
							if (indicadorPrimeiraPagina == 1){
								
								boleto = 1;
								
								Relatorio2ViaContaBoletoBancarioBean boletoBean = 
									new Relatorio2ViaContaBoletoBancarioBean(
										emitirContaHelper, indicadorPrimeiraPagina ,
										totalPaginasRelatorio, contaPaga, 
										colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);
								
								colecaoBoleto.add(boletoBean);
							}
							
						}
						
						Relatorio2ViaContaBean relatorio2ViaContaBean = 
									new Relatorio2ViaContaBean(
										emitirContaHelper, indicadorPrimeiraPagina,
										colecaoDetail, dataVencimentoFormatada,
										enderecoClienteResponsavel,	totalPaginasRelatorio,
										codigoRota,	debitoCreditoSituacaoAtualConta,
										contaPaga, enderecoClienteResponsavelLinha2,
										colecaoBoleto,boleto);
						relatorio2ViaContaBean.setEmpresa(empresa);
						relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
						retorno.add(relatorio2ViaContaBean);
						colecaoDetail.clear();
						colecaoBoleto.clear();
				}
			
				
				
				
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
				
			}	
			
			if  (totalLinhasRelatorio!= numeroMaxLinhasDetailPrimeiraPagina && 
					((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) %
							NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0)) {
	
				if (ehBoleto){
					
					if (indicadorPrimeiraPagina == 1){

						boleto = 1;
						
						Relatorio2ViaContaBoletoBancarioBean boletoBean = 
							new Relatorio2ViaContaBoletoBancarioBean(
								emitirContaHelper, indicadorPrimeiraPagina ,
								totalPaginasRelatorio, contaPaga, 
								colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);
						
						colecaoBoleto.add(boletoBean);
						
					}
					
				}
				
				Relatorio2ViaContaBean relatorio2ViaContaBean = 
					new Relatorio2ViaContaBean(
						emitirContaHelper, indicadorPrimeiraPagina,
						colecaoDetail, dataVencimentoFormatada,
						enderecoClienteResponsavel,	totalPaginasRelatorio,
						codigoRota,	debitoCreditoSituacaoAtualConta,
						contaPaga, enderecoClienteResponsavelLinha2,
						colecaoBoleto,boleto);
				
				if(emitirContaHelper.getCodigoDebitoAutomatico()!=null &&
						!emitirContaHelper.getCodigoDebitoAutomatico().equals("")){
					
					relatorio2ViaContaBean.setCodigoDebitoAutomatico(emitirContaHelper.getCodigoDebitoAutomatico() + "");
						
				}
				relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
				relatorio2ViaContaBean.setEmpresa(empresa);
				retorno.add(relatorio2ViaContaBean);

			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		Collection idsConta = (Collection) getParametro("idsConta");
		boolean cobrarTaxaEmissaoConta = (Boolean) getParametro("cobrarTaxaEmissaoConta");
		Short contaSemCodigoBarras = (Short) getParametro("contaSemCodigoBarras");
		
		Integer idContaHistorico = (Integer)getParametro("idContaHistorico");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Collection colecaoEmitirContaHelper = new ArrayList();
		if (idContaHistorico == null) {
			colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			colecaoEmitirContaHelper = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}
		

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		String nomeUsuario = "";
		
		//**********************************************************************
		// Alterado por: Ivan Sergio
		// Data: 30/04/2009
		// CRC1656
		//**********************************************************************
		if (usuario != null) {
			if (sistemaParametro.getIndicadorImprimeUsuarioSegundaVia().equals(ConstantesSistema.SIM)) {
				idUsuario = usuario.getId().toString();
				nomeUsuario = usuario.getNomeUsuario();
			}
		} else {
			idUsuario = "INTERNET";
			nomeUsuario = "INTERNET";
		}
		
		
			
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",nomeEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		parametros.put("nomeUsuario", nomeUsuario);
		
		String empresa = "\n	  	"+nomeEmpresa +" - "+cnpjEmpresa;
		
		Collection dadosRelatorio = colecaoEmitirContaHelper;

		Collection<Relatorio2ViaContaBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio,sistemaParametro,empresa,fachada);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.SEGUNDA_VIA_CONTA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Integer qtdeContas = (Integer) getParametro("qtdeContas");
		
		return qtdeContas;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("Relatorio2ViaConta", this);
	}
	
}
