package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 06/03/2007
 */
public class ConfirmarRetornoOSFiscalizacaoAction extends GcomAction {

	/**
	 * 
	 * [UC0448] Informar Retorno Ordem de Fiscalização
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("confirmarRetornoOSFiscalizacao");

		Fachada fachada = Fachada.getInstancia();

		String msgFinal = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		String idOS = (String) sessao.getAttribute("idOS");

		Integer numeroOS = new Integer(idOS);

		// Usuário logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		if (sessao.getAttribute("msgFinal") != null) {
			msgFinal = (String) sessao.getAttribute("msgFinal");
		}

		if (httpServletRequest.getParameter("confirmado") != null) {
			if (httpServletRequest.getParameter("confirmado").equalsIgnoreCase(
					"sim")) {
				Date dataAtual = new Date();
				String indicadorExecucaoSim = "2"; // AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM

				fachada
						.encerrarOSComExecucaoSemReferencia(
								numeroOS,
								dataAtual,
								usuario,
								indicadorExecucaoSim,
								dataAtual,
								"ORDEM DE SERVICO ENCERRADA ATRAVES DA FUNCIONALIDADE DE FISCALIZACAO.",
								ServicoTipo.INDICADOR_PAVIMENTO_NAO,
								null,
								null,
								null,
								null,
								null,
								ServicoTipo.INDICADOR_VISTORIA_SERVICO_TIPO_NAO,
								null,
								null,
								null,
								null);
				retorno = actionMapping.findForward("telaSucesso");

				/*
				 * retorno = actionMapping
				 * .findForward("confirmarRetornoOSFiscalizacao");
				 */

			} else {
				if (httpServletRequest.getParameter("confirmado")
						.equalsIgnoreCase("nao")) {
					retorno = actionMapping.findForward("telaSucesso");
					// montando página de sucesso

				}
			}
		}
		montarPaginaSucesso(httpServletRequest, msgFinal,
				"Informar outra fiscalização",
				"/exibirInformarRetornoOSFiscalizacaoAction.do?menu=sim");

		return retorno;
	}
}
