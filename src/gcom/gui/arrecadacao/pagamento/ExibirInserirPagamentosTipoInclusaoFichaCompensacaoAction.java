package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperFichaCompensacao;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.CodigoBarras;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

public class ExibirInserirPagamentosTipoInclusaoFichaCompensacaoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("inserirPagamentosTipoInclusaoFichaCompensacao");
		DynaValidatorActionForm form = (DynaValidatorActionForm) actionForm;
		HttpSession sessao = request.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String codigoBarraRemocao = request.getParameter("codigoBarraRemocao");

		String dataPagamentoString = (String) form.get("dataPagamento");
		Date dataPagamento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataPagamento = dataFormato.parse(dataPagamentoString);
		} catch (ParseException ex) {
			throw new ActionServletException("atencao.data_pagamento_invalida");
		}

		String idFormaArrecadacao = (String) form.get("idFormaArrecadacao");

		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();

		Collection<InserirPagamentoViaCanetaHelper> colecaoHelper = (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");

		if (colecaoHelper == null) {
			colecaoHelper = new ArrayList<InserirPagamentoViaCanetaHelper>();
		} else if (!colecaoHelper.isEmpty()) {

			if (codigoBarraRemocao != null && !codigoBarraRemocao.trim().equalsIgnoreCase("")) {

				Iterator<InserirPagamentoViaCanetaHelper> iterator = colecaoHelper.iterator();

				while (iterator.hasNext()) {
					InserirPagamentoViaCanetaHelper helper = (InserirPagamentoViaCanetaHelper) iterator.next();
					if (helper.getCodigoBarra().equalsIgnoreCase(codigoBarraRemocao)) {
						iterator.remove();
						break;
					}
				}
			}
		}

		BigDecimal valorPagamento = null;
		String codigoBarra = null;

		String codigoBarraDigitadoCampo1 = null;
		String codigoBarraDigitadoCampo1Parte1 = null;
		String codigoBarraDigitadoCampo1Parte2 = null;

		String codigoBarraDigitadoCampo2 = null;
		String codigoBarraDigitadoCampo2Parte1 = null;
		String codigoBarraDigitadoCampo2Parte2 = null;

		String codigoBarraDigitadoCampo3 = null;
		String codigoBarraDigitadoCampo3Parte1 = null;
		String codigoBarraDigitadoCampo3Parte2 = null;

		String codigoBarraDigitadoDigitoVerificador = null;

		String codigoBarraDigitadoCampo4 = null;

		String codigoBarraLeituraOtica = (String) form.get("codigoBarraPorLeituraOtica");

		if (codigoBarraLeituraOtica != null && !codigoBarraLeituraOtica.equalsIgnoreCase("")) {
			codigoBarra = CodigoBarras.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(codigoBarraLeituraOtica);
			codigoBarra = codigoBarra.replace(".", "");
			codigoBarra = codigoBarra.replace(" ", "");

		} else {
			// PRIMEIRO QUADRANTE
			codigoBarraDigitadoCampo1Parte1 = (String) form.get("codigoBarraDigitadoCampo1Parte1");

			if (form.get("codigoBarraDigitadoCampo1Parte2") != null && ((String) form.get("codigoBarraDigitadoCampo1Parte2")).length() == 5) {
				codigoBarraDigitadoCampo1Parte2 = (String) form.get("codigoBarraDigitadoCampo1Parte2");
				codigoBarraDigitadoCampo1 = codigoBarraDigitadoCampo1Parte1 + codigoBarraDigitadoCampo1Parte2;
			} else {
				codigoBarraDigitadoCampo1Parte2 = "";
				codigoBarraDigitadoCampo1 = "";
			}

			// SEGUNDO QUADRANTE
			codigoBarraDigitadoCampo2Parte1 = (String) form.get("codigoBarraDigitadoCampo2Parte1");
			if (form.get("codigoBarraDigitadoCampo2Parte2") != null && ((String) form.get("codigoBarraDigitadoCampo2Parte2")).length() == 6) {
				codigoBarraDigitadoCampo2Parte2 = (String) form.get("codigoBarraDigitadoCampo2Parte2");
				codigoBarraDigitadoCampo2 = codigoBarraDigitadoCampo2Parte1 + codigoBarraDigitadoCampo2Parte2;
			} else {
				codigoBarraDigitadoCampo2Parte2 = "";
				codigoBarraDigitadoCampo2 = "";
			}

			// TERCEIRO QUADRANTE
			codigoBarraDigitadoCampo3Parte1 = (String) form.get("codigoBarraDigitadoCampo3Parte1");
			if (form.get("codigoBarraDigitadoCampo3Parte2") != null && ((String) form.get("codigoBarraDigitadoCampo3Parte2")).length() == 6) {
				codigoBarraDigitadoCampo3Parte2 = (String) form.get("codigoBarraDigitadoCampo3Parte2");
				codigoBarraDigitadoCampo3 = codigoBarraDigitadoCampo3Parte1 + codigoBarraDigitadoCampo3Parte2;
			} else {
				codigoBarraDigitadoCampo3Parte2 = "";
				codigoBarraDigitadoCampo3 = "";
			}

			// DÍGITO VERIFICADOR DO MÓDULO 11
			codigoBarraDigitadoDigitoVerificador = (String) form.get("codigoBarraDigitadoDigitoVerificador");

			// FATOR DE VENCIMENTO E VALOR DO TÍTULO
			codigoBarraDigitadoCampo4 = (String) form.get("codigoBarraDigitadoCampo4");

			codigoBarra = codigoBarraDigitadoCampo1 
					+ codigoBarraDigitadoCampo2 
					+ codigoBarraDigitadoCampo3 
					+ codigoBarraDigitadoDigitoVerificador 
					+ codigoBarraDigitadoCampo4;
		}

		if (codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.trim().length() == 47) {
			if (codigoBarraRemocao == null || codigoBarraRemocao.trim().equalsIgnoreCase("")) {
				RegistroHelperFichaCompensacao helper = getFachada().distribuirDadosFichaCompensacao(codigoBarra);

				StringBuilder nossoNumero = getFachada().obterNossoNumeroFichaCompensacao(helper.getIdDocumentoTipo(), helper.getIdCobrancaDocumento(), null);

				String nossoNumeroSemDV = nossoNumero.toString().substring(0, 17);

				if (codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equalsIgnoreCase("")) {
					validarDigitoVerificador(helper, nossoNumeroSemDV);
				}

				if (verificarExistenciaDocumentoNaLista(codigoBarra, colecaoHelper)) {
					throw new ActionServletException("atencao.documento_informado");
				} else {
					PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = getFachada().processarPagamentosFichaCompensacao(
							sistemaParametro, dataPagamento, helper.getValorDocumento(), nossoNumeroSemDV, new Integer(idFormaArrecadacao), usuarioLogado);

					String descricaoOcorrenciaMovimento = pagamentoHelperCodigoBarras.getDescricaoOcorrencia();
					Integer indicadorAceitacaoRegistroMovimento = Integer.parseInt(pagamentoHelperCodigoBarras.getIndicadorAceitacaoRegistro());

					if (indicadorAceitacaoRegistroMovimento == 1 && descricaoOcorrenciaMovimento.equalsIgnoreCase("OK")) {
						InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper = new InserirPagamentoViaCanetaHelper();

						inserirPagamentoViaCanetaHelper.setColecaoPagamento(pagamentoHelperCodigoBarras.getColecaoPagamentos());
						inserirPagamentoViaCanetaHelper.setColecaoDevolucao(pagamentoHelperCodigoBarras.getColecaoDevolucao());

						valorPagamento = helper.getValorDocumento();

						if (codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equals("")) {
							inserirPagamentoViaCanetaHelper.setCodigoBarra(
									codigoBarraDigitadoCampo1 + 
									codigoBarraDigitadoCampo2 + 
									codigoBarraDigitadoCampo3 + 
									codigoBarraDigitadoDigitoVerificador + 
									codigoBarraDigitadoCampo4);
						} else {
							inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarra);
						}
						
						inserirPagamentoViaCanetaHelper.setValorPagamento(valorPagamento);

						colecaoHelper.add(inserirPagamentoViaCanetaHelper);

						form.set("codigoBarraDigitadoCampo1Parte1", "");
						form.set("codigoBarraDigitadoCampo1Parte2", "");
						form.set("codigoBarraDigitadoCampo2Parte1", "");
						form.set("codigoBarraDigitadoCampo2Parte2", "");
						form.set("codigoBarraDigitadoCampo3Parte1", "");
						form.set("codigoBarraDigitadoCampo3Parte2", "");
						form.set("codigoBarraDigitadoDigitoVerificador", "");
						form.set("codigoBarraDigitadoCampo4", "");
						form.set("codigoBarraPorLeituraOtica", "");
					} else {
						throw new ActionServletException("atencao.descricao_ocorrencia_movimento", null, descricaoOcorrenciaMovimento);
					}
				}
			}
		}

		sessao.setAttribute("colecaoInserirPagamentoViaCanetaHelper", colecaoHelper);

		return retorno;
	}

	/**
	 * Verifica se o códigode barras informado pelo usuário já está contido na
	 * lista de documentos
	 */
	private boolean verificarExistenciaDocumentoNaLista(String codigoBarra, Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper) {
		// Flag para indicar se o códigode barras já está contido na lista de
		// documentos
		boolean retorno = false;

		// Caso a lista de documentos não esteja vazia
		if (colecaoInserirPagamentoViaCanetaHelper != null && !colecaoInserirPagamentoViaCanetaHelper.isEmpty()) {

			// Laço para verificar se o documento já esta na coleção
			loopPagamento: for (InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper) {
				// Caso o código de barras do documento seja igual ao informado
				// pelo usuário
				if (inserirPagamentoViaCanetaHelper.getCodigoBarra().equals(codigoBarra)) {
					// Indica que o documento já existe na coleção
					retorno = true;

					// Para o loop
					break loopPagamento;
				}
			}
		}

		return retorno;
	}

	/**
	 * Valida o código de barras, caso esse tenha sido digitado com seus dígitos
	 * verificadores
	 */
	private void validarDigitoVerificador(RegistroHelperFichaCompensacao codigoBarraFichaCompensacaoHelper, String nossoNumeroSemDV) {
		// Cria as variáveis quevai armazenar os dígitos verificadores do módulo
		// 11 e 10 respectivamente
		String dvModulo11 = null;
		String dvModulo10 = null;

		dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo1SemDigito())).toString();
		if (!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo1())) {
			throw new ActionServletException("atencao.digitoverificador_invalido");
		}

		dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo2SemDigito())).toString();
		if (!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo2())) {
			throw new ActionServletException("atencao.digitoverificador_invalido");
		}

		dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo3SemDigito())).toString();
		if (!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo3())) {
			throw new ActionServletException("atencao.digitoverificador_invalido");
		}

		// DIGITO VERIFICADOR GERAL
		String especificacaoCodigoBarra = CodigoBarras.obterEspecificacaoCodigoBarraFichaCompensacao(ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO,
				codigoBarraFichaCompensacaoHelper.getValorDocumento(), nossoNumeroSemDV.toString(), ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, codigoBarraFichaCompensacaoHelper.getFatorVencimento());

		dvModulo11 = especificacaoCodigoBarra.substring(4, 5);
		if (!dvModulo11.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo11())) {
			throw new ActionServletException("atencao.digitoverificador_invalido");
		}
	}
}
