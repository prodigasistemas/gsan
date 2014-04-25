package gcom.gui.cobranca;

import java.math.BigDecimal;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar débito cobrado
 * 
 * @author Fernanda Paiva
 * @created 30 de Março de 2006
 */
public class ExibirValorAtualizacaoConsultarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar débito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirValorAtualizacaoConsultarPopup");

		ValorAtualizacaoConsultarActionForm valorAtualizacaoConsultarActionForm = (ValorAtualizacaoConsultarActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		/*
		 * Alterado por Raphael Rossiter em 01/09/2007
		 * OBJ: Calcular atualização tarifária
		 */
		if (sistemaParametros.getIndicadorAtualizacaoTarifaria() == 
			ConstantesSistema.NAO){
			
			httpServletRequest.setAttribute("label", "Valor da Atualização Monetária:");
		}
		else{
			httpServletRequest.setAttribute("label", "Valor da Atualização Tarifária:");
		}
		
		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o código da conta do request
		String multa = httpServletRequest.getParameter("multa");

		String juros = httpServletRequest.getParameter("juros");

		String atualizacao = httpServletRequest.getParameter("atualizacao");
		
		//Envia os valores para o relatorio
		sessao.setAttribute("multa", multa);
		sessao.setAttribute("juros", juros);
		sessao.setAttribute("atualizacao", atualizacao);
		
		if (multa != null) {
			if (Util.verificaSeBigDecimal(multa)) {
				valorAtualizacaoConsultarActionForm.setMulta(multa);
			} else {
				valorAtualizacaoConsultarActionForm.setMulta(Util
						.formatarMoedaReal(new BigDecimal(multa)));
			}
		}
		if (juros != null) {
			if (Util.verificaSeBigDecimal(juros)) {
				valorAtualizacaoConsultarActionForm.setJuros(juros);
			} else {
				valorAtualizacaoConsultarActionForm.setJuros(Util
						.formatarMoedaReal(new BigDecimal(juros)));
			}

		}
		if (atualizacao != null) {
			if (Util.verificaSeBigDecimal(atualizacao)) {
				valorAtualizacaoConsultarActionForm.setAtualizacao(atualizacao);
			} else {
				valorAtualizacaoConsultarActionForm.setAtualizacao(Util
						.formatarMoedaReal(new BigDecimal(atualizacao)));
			}

		}
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaAcrescimos") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaAcrescimos",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaAcrescimos"));
		}
		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
