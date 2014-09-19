package gcom.gui.relatorio.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.portal.EfetuarParcelamentoDebitosPortalActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioExtratoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 */
@SuppressWarnings("unchecked")
public class GerarRelatorioExtratoDebitoAction extends ExibidorProcessamentoTarefaRelatorio {

	private Usuario usuarioLogado = null;
	
	private Imovel imovel = null;
	private String inscricao = "";    	
	private String matricula = "";
	private String nomeUsuario = "";
	private String cpfCnpj = "";
	private String enderecoImovel = "";
	
	private Collection<ContaValoresHelper> colecaoContas =  null;
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
	private Collection<DebitoACobrar> colecaoDebitosACobrar = null;
	private Collection<CreditoARealizar> colecaoCreditoARealizar = null;
	private Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
	private Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
	private BigDecimal valorAcrescimosImpontualidade = BigDecimal.ZERO;
	private BigDecimal valorDocumento = BigDecimal.ZERO;
	private BigDecimal valorDesconto =  BigDecimal.ZERO;
	private BigDecimal valorDescontoCredito =  BigDecimal.ZERO;
	
	private Short indicadorGeracaoTaxaCobranca = new Short("2") ;
	
	private BigDecimal parcelamentoValorDebitoACobrarServico = null;
	private BigDecimal parcelamentoValorDebitoACobrarParcelamento = null;
	
	private ResolucaoDiretoria resolucaoDiretoria = null;
	
	private static Logger logger = Logger.getLogger(GerarRelatorioExtratoDebitoAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		HttpSession sessao = request.getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
		try {
			if (request.getParameter("extratoDebito") != null) {
					setDadosExtratoDebito(fachada, sessao);
			} else if (request.getParameter("parcelamento") != null) {
				setDadosEfetuarParcelamento(form, request, sessao);
			} else if (request.getParameter("consultarDebito") != null) {
				setDadosConsultarDebitoPorImovel(fachada, sessao);
			} else {
				setDadosConsultarImovelAbaDebitos(fachada, sessao);
			}
			
			// Caso não seja possível emitir documento de cobrança
			if (imovel.getCobrancaSituacaoTipo() != null) {
				if (imovel.getCobrancaSituacaoTipo().getIndicadorEmitirDocumentoCobranca().equals(ConstantesSistema.NAO)) {
					throw new ActionServletException("atencao.extratonaoemitido_imovel_situacaoespecial");
				}
			}
	
			if (valorDocumento.compareTo(BigDecimal.ZERO) < 0) {
				throw new ActionServletException("atencao.resultado.extrato.negativo");
			}
	
			if (valorAcrescimosImpontualidade == null) {
				valorAcrescimosImpontualidade = BigDecimal.ZERO;
			}
	
			if (valorDesconto == null) {
				valorDesconto = BigDecimal.ZERO;
			}
			
			ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = gerarDocumentoCobranca(fachada);
			CobrancaDocumento documentoCobranca = getDocumentoCobranca(fachada, sessao, extratoDebitoRelatorioHelper);
	
			return gerarRelatorio(actionMapping, request, response, fachada,
					sistemaParametro, extratoDebitoRelatorioHelper, documentoCobranca);
		} catch (ControladorException e) {
			
			e.printStackTrace();
		} finally {
			limparSessao(request);
		}
		return null;
	}

	private ActionForward gerarRelatorio(ActionMapping actionMapping,
			HttpServletRequest request, HttpServletResponse response,
			Fachada fachada, SistemaParametro sistemaParametro,
			ExtratoDebitoRelatorioHelper extratoDebitoHelper,
			CobrancaDocumento documentoCobranca) throws NumberFormatException {
		
		BigDecimal valorTotalContas = extratoDebitoHelper.getValorTotalConta();
		valorTotalContas = valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		BigDecimal valorTotalRestanteDebitosACobrar = new BigDecimal("0.00");
		
		if (parcelamentoValorDebitoACobrarServico != null
				&& parcelamentoValorDebitoACobrarParcelamento != null) {
			valorTotalRestanteDebitosACobrar = parcelamentoValorDebitoACobrarServico.add(parcelamentoValorDebitoACobrarParcelamento);
		} else {
			valorTotalRestanteDebitosACobrar = extratoDebitoHelper.getValorTotalRestanteDebitosACobrar();
		}
		
		BigDecimal valorServicosAtualizacoes = BigDecimal.ZERO;
		valorServicosAtualizacoes = valorAcrescimosImpontualidade.add(
				extratoDebitoHelper.getValorTotalGuiaPagamento().add(valorTotalRestanteDebitosACobrar));
		
		String valorTotalContasString = Util.formatarMoedaReal(valorTotalContas);
		String valorServicosAtualizacoesString = Util.formatarMoedaReal(valorServicosAtualizacoes);
		valorDescontoCredito  = valorDescontoCredito.add(valorDesconto);
		String valorDescontoString = Util.formatarMoedaReal(valorDescontoCredito);
		String valorTotalComDescontoString = Util.formatarMoedaReal(valorDocumento); 
		 
		return processarRelatorio(actionMapping, request, response, fachada,
				sistemaParametro, extratoDebitoHelper, documentoCobranca,
				valorTotalContasString, valorServicosAtualizacoesString,
				valorDescontoString, valorTotalComDescontoString);
	}

	private ActionForward processarRelatorio(ActionMapping actionMapping,
			HttpServletRequest request, HttpServletResponse response,
			Fachada fachada, SistemaParametro sistemaParametro,
			ExtratoDebitoRelatorioHelper extratoDebitoHelper,
			CobrancaDocumento documentoCobranca, String valorTotalContasString,
			String valorServicosAtualizacoesString, String valorDescontoString,
			String valorTotalComDescontoString) {

		ActionForward retorno = null;
		
		RelatorioExtratoDebito relatorioExtratoDebito = new RelatorioExtratoDebito(usuarioLogado);

		relatorioExtratoDebito.addParametro("nomeLocalidade", documentoCobranca.getLocalidade().getDescricao());
		relatorioExtratoDebito.addParametro("inscricao", inscricao);
		relatorioExtratoDebito.addParametro("nomeUsuario", nomeUsuario);
		relatorioExtratoDebito.addParametro("matricula", matricula);
		relatorioExtratoDebito.addParametro("cpfCnpj", cpfCnpj);
		relatorioExtratoDebito.addParametro("enderecoImovel", enderecoImovel);
		relatorioExtratoDebito.addParametro("seqDocCobranca", documentoCobranca.getNumeroSequenciaDocumento() + "");
		relatorioExtratoDebito.addParametro("situacaoAgua", documentoCobranca.getImovel().getLigacaoAguaSituacao().getId().toString());
		relatorioExtratoDebito.addParametro("situacaoEsgoto", documentoCobranca.getImovel().getLigacaoEsgotoSituacao().getId().toString());

		Collection<Categoria> colecaoCategorias = fachada.obterQuantidadeEconomiasCategoria(imovel);
		relatorioExtratoDebito.addParametro("qtdResidencial",getQuantidadeEconomias(fachada, Categoria.RESIDENCIAL, colecaoCategorias));
		relatorioExtratoDebito.addParametro("qtdComercial", getQuantidadeEconomias(fachada, Categoria.COMERCIAL, colecaoCategorias));
		relatorioExtratoDebito.addParametro("qtdIndustrial", getQuantidadeEconomias(fachada, Categoria.INDUSTRIAL, colecaoCategorias));
		relatorioExtratoDebito.addParametro("qtdPublico", getQuantidadeEconomias(fachada, Categoria.PUBLICO, colecaoCategorias));

		relatorioExtratoDebito.addParametro("descPerfilImovel", documentoCobranca.getImovelPerfil().getDescricao());
		relatorioExtratoDebito.addParametro("dataEmissao", Util.formatarData(documentoCobranca.getEmissao()));

		String dataValidade = Util.formatarData(Util.adicionarNumeroDiasDeUmaData(
				new Date(), sistemaParametro.getNumeroDiasValidadeExtrato()));
		relatorioExtratoDebito.addParametro("dataValidade", dataValidade);

		relatorioExtratoDebito.addParametro("valorTotalContas", valorTotalContasString);
		relatorioExtratoDebito.addParametro("valorServicosAtualizacoes", valorServicosAtualizacoesString);
		relatorioExtratoDebito.addParametro("valorDesconto", valorDescontoString);
		relatorioExtratoDebito.addParametro("valorTotalComDesconto", valorTotalComDescontoString);
		relatorioExtratoDebito.addParametro("imovel", imovel);

		if (extratoDebitoHelper.getDocumentoCobranca().getValorDocumento() != null
				&& sistemaParametro.getValorExtratoFichaComp() != null
				&& !sistemaParametro.getValorExtratoFichaComp().equals(BigDecimal.ZERO)
				&& extratoDebitoHelper.getDocumentoCobranca().getValorDocumento().compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0) {

			relatorioExtratoDebito = obterCodigoBarrasFichaCompensacao(fachada, documentoCobranca, relatorioExtratoDebito);
		} else {
			relatorioExtratoDebito = obterCodigoBarras(fachada, documentoCobranca, relatorioExtratoDebito);
		}

		relatorioExtratoDebito.addParametro("valorAcrescimosImpontualidade", valorAcrescimosImpontualidade);
		relatorioExtratoDebito.addParametro("extratoDebitoRelatorioHelper", extratoDebitoHelper);

		String codigoRotaESequencialRota = fachada.obterRotaESequencialRotaDoImovel(imovel.getId());
		relatorioExtratoDebito.addParametro("codigoRotaESequencialRota", codigoRotaESequencialRota);

		relatorioExtratoDebito.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
		
		try {
			retorno = processarExibicaoRelatorio(relatorioExtratoDebito, TarefaRelatorio.TIPO_PDF + "",
					request, response, actionMapping);

		} catch (RelatorioVazioException ex) {
			reportarErros(request, "atencao.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		return retorno;
	}

	private RelatorioExtratoDebito obterCodigoBarras(Fachada fachada,
			CobrancaDocumento documentoCobranca,
			RelatorioExtratoDebito relatorioExtratoDebito) {
		
		String representacaoNumericaCodBarra = fachada.obterRepresentacaoNumericaCodigoBarra(5, valorDocumento,
				documentoCobranca.getLocalidade().getId(), imovel.getId(), null, null, null, null,
				documentoCobranca.getNumeroSequenciaDocumento() + "",
				documentoCobranca.getDocumentoTipo().getId(), null, null, null);

		String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
				+ "-" + representacaoNumericaCodBarra.substring(11, 12)
				+ " " + representacaoNumericaCodBarra.substring(12, 23)
				+ "-" + representacaoNumericaCodBarra.substring(23, 24)
				+ " " + representacaoNumericaCodBarra.substring(24, 35)
				+ "-" + representacaoNumericaCodBarra.substring(35, 36)
				+ " " + representacaoNumericaCodBarra.substring(36, 47)
				+ "-" + representacaoNumericaCodBarra.substring(47, 48);
		relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra", representacaoNumericaCodBarraFormatada);

		String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra.substring(0, 11)
				+ representacaoNumericaCodBarra.substring(12, 23)
				+ representacaoNumericaCodBarra.substring(24, 35)
				+ representacaoNumericaCodBarra.substring(36, 47);
		relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito", representacaoNumericaCodBarraSemDigito);
		
		return relatorioExtratoDebito;
	}

	private RelatorioExtratoDebito obterCodigoBarrasFichaCompensacao(Fachada fachada,
			CobrancaDocumento documentoCobranca,
			RelatorioExtratoDebito relatorioExtratoDebito) {
		
		StringBuilder nossoNumero = fachada.obterNossoNumeroFichaCompensacao(
				DocumentoTipo.EXTRATO_DE_DEBITO.toString(), documentoCobranca.getId().toString());
		
		String nossoNumeroSemDV = nossoNumero.toString().substring(0, 17);
		relatorioExtratoDebito.addParametro("nossoNumero", nossoNumero.toString());

		Date dataVencimentoMais75 = Util.adicionarNumeroDiasDeUmaData(new Date(), 75);
		String fatorVencimento = fachada.obterFatorVencimento(dataVencimentoMais75);

		String especificacaoCodigoBarra = fachada.obterEspecificacaoCodigoBarraFichaCompensacao(
				ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO,
				ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO,
				documentoCobranca.getValorDocumento(),
				nossoNumeroSemDV.toString(),
				ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO,
				fatorVencimento);

		String representacaoNumericaCodigoBarraFichaCompensacao = fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(
				especificacaoCodigoBarra);

		relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito", especificacaoCodigoBarra);
		relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra", representacaoNumericaCodigoBarraFichaCompensacao);
		
		return relatorioExtratoDebito;
	}

	private String getQuantidadeEconomias(Fachada fachada, Integer idCategoria, Collection<Categoria> colecaoCategorias) {
		String quantidade = "";
		for (Categoria categoria : colecaoCategorias) {
			if (categoria.getId().equals(idCategoria)) {
				quantidade = categoria.getQuantidadeEconomiasCategoria().toString();
			}
		}
		return quantidade;
	}

	private ExtratoDebitoRelatorioHelper gerarDocumentoCobranca(Fachada fachada) {
		
		return fachada.gerarEmitirExtratoDebito(imovel, indicadorGeracaoTaxaCobranca,
				colecaoContas, colecaoGuiasPagamento, colecaoDebitosACobrar,
				valorAcrescimosImpontualidade, valorDesconto, valorDocumento,
				colecaoCreditoARealizar, null, resolucaoDiretoria,
				colecaoAntecipacaoDebitosDeParcelamento,
				colecaoAntecipacaoCreditosDeParcelamento);
	}

	private CobrancaDocumento getDocumentoCobranca(Fachada fachada, HttpSession sessao,
			ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper) {
		CobrancaDocumento documentoCobranca = extratoDebitoRelatorioHelper.getDocumentoCobranca();
		documentoCobranca.setUsuario(usuarioLogado);
		fachada.atualizar(documentoCobranca);
		
		return documentoCobranca;
	}

	private void setDadosConsultarImovelAbaDebitos(Fachada fachada, HttpSession sessao) throws ControladorException {
		Integer idImovel = new Integer((String)sessao.getAttribute("idImovelPrincipalAba"));
		
		imovel = fachada.pesquisarImovel(idImovel);
		inscricao = imovel.getInscricaoFormatada();
		matricula = idImovel.toString();
		setNomeUsuarioECpfCnpj(fachada, idImovel);
		
		enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
		
		colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
		colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
		colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
		
		
		valorDesconto = (BigDecimal)sessao.getAttribute("valorTotalDescontoPagamentoAVista");
		valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorCreditoARealizar"));
		
		valorDocumento = (BigDecimal)sessao.getAttribute("valorPagamentoAVista");
		if (valorDocumento == null) {
			valorDocumento = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorToralSemAcrescimoESemJurosParcelamento"));
			valorDocumento = valorDocumento.subtract(valorDescontoCredito);
		} else {
			valorAcrescimosImpontualidade =  Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorAcrescimo")); 
		}
		
		colecaoDebitosACobrar = obterColecaoDebitosACobrarSemJurosParcelamento(colecaoDebitosACobrar);
	}

	private void setNomeUsuarioECpfCnpj(Fachada fachada, Integer idImovel) {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

		Collection<ClienteImovel> clientesImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		Cliente cliente = clientesImovel.iterator().next().getCliente();

		if (cliente != null) {
			nomeUsuario = cliente.getNome();
			if ( cliente.getCpf()!= null ) {
				cpfCnpj = cliente.getCpfFormatado();
			} else if ( cliente.getCnpj() != null ) {
				cpfCnpj = cliente.getCnpjFormatado();
			}
		}
	}

	private void setDadosConsultarDebitoPorImovel(Fachada fachada, HttpSession sessao) throws ControladorException {
		Integer idImovel = new Integer((String) sessao.getAttribute("idImovel"));

		imovel = fachada.pesquisarImovel(idImovel);
		inscricao = imovel.getInscricaoFormatada();
		matricula = idImovel.toString();
		setNomeUsuarioECpfCnpj(fachada, idImovel);
		
		enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
		
		colecaoContas = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
		colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoValores");
		colecaoCreditoARealizar = (Collection<CreditoARealizar>) sessao.getAttribute("colecaoCreditoARealizar");

		valorDescontoCredito = (BigDecimal) sessao.getAttribute("valorCreditoARealizar");
		valorDesconto = (BigDecimal) sessao.getAttribute("valorTotalDescontoPagamentoAVista");
		valorDocumento = (BigDecimal) sessao.getAttribute("valorPagamentoAVista");
		valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimo");

		colecaoDebitosACobrar = obterColecaoDebitosACobrarSemJurosParcelamento(colecaoDebitosACobrar);
	}

	private void setDadosEfetuarParcelamento(ActionForm actionForm, HttpServletRequest request, HttpSession sessao) {
		imovel = (Imovel) sessao.getAttribute("imovel");
		
		logger.info("[ " + imovel.getId() + "	- setDadosEfetuarParcelamento -  RD: " + request.getParameter("RD") + "]");
		if (request.getParameter("RD") != null) {
			resolucaoDiretoria = new ResolucaoDiretoria();
			resolucaoDiretoria.setId(new Integer(request.getParameter("RD")));
		}
		
		// Verifica se a aba 3 é chamada pela aba 2 (colecaoContaValores) ou pela aba 1 (colecaoContaValoresImovel)
		if (sessao.getAttribute("colecaoContaValoresSemContasNB") != null || sessao.getAttribute("colecaoGuiasPagamento") != null) {
			
			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValoresSemContasNB");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
			valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidade");
			
		} else if (sessao.getAttribute("colecaoContaValoresNegociacao") != null || (sessao.getAttribute("colecaoContaValoresNegociacao") != null)) {
			
			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValoresNegociacao");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoNegociacao");
			valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidadeNegociacao");
		}
		
		colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
		colecaoDebitosACobrar = obterColecaoDebitosACobrarDoParcelamento(colecaoDebitosACobrar);
		
		String parcelamentoPortal = request.getParameter("parcelamentoPortal");
		
		if (Util.verificarNaoVazio(parcelamentoPortal) && parcelamentoPortal.equalsIgnoreCase("sim")) {
			EfetuarParcelamentoDebitosPortalActionForm debitosPortalForm = (EfetuarParcelamentoDebitosPortalActionForm) sessao.getAttribute("formParcelamento");
			
			valorDocumento = Util.formatarMoedaRealparaBigDecimal(debitosPortalForm.getValorPagamentoAVista());
			valorDesconto = Util.formatarMoedaRealparaBigDecimal(debitosPortalForm.getValorDescontoPagamentoAVista());
			valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal(debitosPortalForm.getValorCreditoARealizar());
			parcelamentoValorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(debitosPortalForm.getValorDebitoACobrarServico());
			parcelamentoValorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(debitosPortalForm.getValorDebitoACobrarParcelamento());

			inscricao = debitosPortalForm.getInscricaoImovel();
			matricula = debitosPortalForm.getMatriculaImovel();
			nomeUsuario = debitosPortalForm.getNomeCliente();
			cpfCnpj = debitosPortalForm.getCpfCliente();
			enderecoImovel = debitosPortalForm.getEnderecoImovel();
		} else {
			DynaActionForm debitosForm = (DynaActionForm) actionForm;
			valorDocumento = Util.formatarMoedaRealparaBigDecimal(debitosForm.get("valorPagamentoAVista").toString());
			valorDesconto = Util.formatarMoedaRealparaBigDecimal(debitosForm.get("valorDescontoPagamentoAVista").toString());
			valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal(debitosForm.get("valorCreditoARealizar").toString());
			parcelamentoValorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(debitosForm.get("valorDebitoACobrarServico").toString());
			parcelamentoValorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(debitosForm.get("valorDebitoACobrarParcelamento").toString());
			
			inscricao = (String) debitosForm.get("inscricaoImovel");
			matricula = (String) debitosForm.get("matriculaImovel");
			nomeUsuario = (String) debitosForm.get("nomeCliente");
			cpfCnpj = (String) debitosForm.get("cpfCnpj");
			enderecoImovel = (String) debitosForm.get("endereco");
		}
	}

	private void setDadosExtratoDebito(Fachada fachada, HttpSession sessao) throws ControladorException {
		Integer idImovel = new Integer((String) sessao.getAttribute("idImovelExtrato"));
		
		logger.info("[ " + idImovel + "	- GERANDO EXTRATO DE DEBITO ]");
		imovel = fachada.pesquisarImovel(idImovel);
		inscricao = imovel.getInscricaoFormatada();
		matricula = imovel.getId().toString();
		nomeUsuario = (String) sessao.getAttribute("nomeClienteExtrato");
		cpfCnpj = (String) sessao.getAttribute("cpfCnpj");
		
		enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
		
		colecaoContas = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContasExtrato");
		colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiasPagamentoExtrato");
		colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitosACobrarExtrato");
		colecaoCreditoARealizar = (Collection<CreditoARealizar>) sessao.getAttribute("colecaoCreditoARealizarExtrato");
		colecaoAntecipacaoDebitosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoDebitosDeParcelamento");
		colecaoAntecipacaoCreditosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoCreditosDeParcelamento");
		valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidadeExtrato");
		valorDocumento = (BigDecimal) sessao.getAttribute("valorDocumentoExtrato");
		valorDesconto = (BigDecimal) sessao.getAttribute("valorDescontoExtrato");
		valorDescontoCredito = (BigDecimal) sessao.getAttribute("valorCreditoARealizar");
		logger.info("[ " + idImovel + "	- valorAcrescimosImpontualidade: " + valorAcrescimosImpontualidade + "]");
		logger.info("[ " + idImovel + "	- valorDocumento: " + valorDocumento + "]");
		logger.info("[ " + idImovel + "	- valorDesconto: " + valorDesconto + "]");
		logger.info("[ " + idImovel + "	- valorDescontoCredito: " + valorDescontoCredito + "]");
	}

	private Collection obterColecaoDebitosACobrarDoParcelamento(Collection<DebitoACobrar> colecaoDebitosACobrar) {
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();

		for (DebitoACobrar debitoACobrar : colecaoDebitosACobrar) {
			// Verificar existência de juros sobre imóvel
			if (debitoACobrar.getDebitoTipo().getId() != null 
					&& !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)) {
				// Debitos A Cobrar - Serviço
				if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
					colecaoDebitosACobrarParcelamento.add(debitoACobrar);
				}
				
				// Debitos A Cobrar - Parcelamento
				if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
					colecaoDebitosACobrarParcelamento.add(debitoACobrar);
				}
			}
		}
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	private Collection obterColecaoDebitosACobrarSemJurosParcelamento(Collection<DebitoACobrar> colecaoDebitosACobrar) {
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();

		for (DebitoACobrar debitoACobrar : colecaoDebitosACobrar) {
			// Verificar existência de juros sobre imóvel
			if (debitoACobrar.getDebitoTipo().getId() != null 
					&& !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)) {
				colecaoDebitosACobrarParcelamento.add(debitoACobrar);
			}
		}
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	private void limparSessao(HttpServletRequest request) {
		
		HttpSession sessao = request.getSession(false);
		
		sessao.removeAttribute("formParcelamento");
		sessao.removeAttribute("idImovel");
		sessao.removeAttribute("idImovelExtrato");
		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("imovel");
		sessao.removeAttribute("nomeClienteExtrato");
		sessao.removeAttribute("cpfCnpj");
		sessao.removeAttribute("colecaoAntecipacaoCreditosDeParcelamento");
		sessao.removeAttribute("colecaoAntecipacaoDebitosDeParcelamento");
		sessao.removeAttribute("colecaoCreditoARealizarExtrato");
		sessao.removeAttribute("colecaoContaValoresNegociacao");
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("colecaoContasExtrato");
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("colecaoContaValoresSemContasNB");
		sessao.removeAttribute("colecaoDebitosACobrarExtrato");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("colecaoGuiasPagamento");
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("colecaoGuiaPagamentoNegociacao");
		sessao.removeAttribute("colecaoGuiasPagamentoExtrato");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorAcrescimosImpontualidade");
		sessao.removeAttribute("valorAcrescimosImpontualidadeNegociacao");
		sessao.removeAttribute("valorAcrescimosImpontualidadeExtrato");
		sessao.removeAttribute("valorCreditoARealizar");
		sessao.removeAttribute("valorDocumentoExtrato");
		sessao.removeAttribute("valorDescontoExtrato");
		sessao.removeAttribute("valorPagamentoAVista");
		sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");
		sessao.removeAttribute("valorTotalDescontoPagamentoAVista");

		request.removeAttribute("extratoDebito");
		request.removeAttribute("parcelamento");
		request.removeAttribute("consultarDebito");
		request.removeAttribute("RD");
		request.removeAttribute("parcelamentoPortal");
	}
}
