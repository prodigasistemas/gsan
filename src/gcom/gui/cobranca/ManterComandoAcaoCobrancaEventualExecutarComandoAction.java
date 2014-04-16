package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * Execuar o Comando, qdo o usuário clica no botão para executar estando na tela
 * de comandar_acao_cobranca_eventual_manter_processo2.jsp
 * 
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ManterComandoAcaoCobrancaEventualExecutarComandoAction extends
		GcomAction {

	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;
		String idComando = httpServletRequest.getParameter("idComando");

		HttpSession sessao = httpServletRequest.getSession(false);
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;

		if (sessao.getAttribute("cobrancaAcaoAtividadeComando") != null) {
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) sessao
					.getAttribute("cobrancaAcaoAtividadeComando");
		}

		fachada.executarComandoManterAcaoCobranca(
				manterComandoAcaoCobrancaDetalhesActionForm
						.getPeriodoInicialConta(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getPeriodoFinalConta(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getPeriodoVencimentoContaInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getPeriodoVencimentoContaFinal(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCobrancaAcao(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getCobrancaAtividade(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCobrancaGrupo(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getGerenciaRegional(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeOrigemID(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeDestinoID(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialOrigemCD(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialDestinoCD(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIdCliente(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getClienteRelacaoTipo(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIndicador(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaFinal(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialOrigemID(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialDestinoID(), idComando,
				cobrancaAcaoAtividadeComando.getId().toString(),
				cobrancaAcaoAtividadeComando.getUltimaAlteracao(),
				cobrancaAcaoAtividadeComando.getComando(),
				cobrancaAcaoAtividadeComando.getRealizacao(),
				cobrancaAcaoAtividadeComando.getUsuario(),
				cobrancaAcaoAtividadeComando.getEmpresa(),
				cobrancaAcaoAtividadeComando.getQuantidadeDocumentos(),
				cobrancaAcaoAtividadeComando.getValorDocumentos(),
				cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados(),
				manterComandoAcaoCobrancaDetalhesActionForm.getTitulo(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getDescricaoSolicitacao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPrazoExecucao(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getQuantidadeMaximaDocumentos(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getIndicadorImoveisDebito(),
				manterComandoAcaoCobrancaDetalhesActionForm
						.getIndicadorGerarBoletimCadastro(),
						manterComandoAcaoCobrancaDetalhesActionForm
						.getCodigoClienteSuperior(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaFinal());

		// pesquisar cobranca atividade
		CobrancaAtividade cobrancaAtividade = fachada
				.consultarCobrancaAtividade(manterComandoAcaoCobrancaDetalhesActionForm
						.getCobrancaAtividade());

		// pesquisar cobranca acao
		CobrancaAcao cobrancaAcao = fachada
				.consultarCobrancaAcao(manterComandoAcaoCobrancaDetalhesActionForm
						.getCobrancaAcao());

		montarPaginaSucesso(httpServletRequest, "A Ação "
				+ cobrancaAcao.getDescricaoCobrancaAcao()
				+ " para a atividade "
				+ cobrancaAtividade.getDescricaoCobrancaAtividade()
				+ " executada com sucesso",
				"Manter outro Comando de Ação de Cobrança",
				"exibirManterComandoAcaoCobrancaAction.do?menu=sim");
		return retorno;
	}
}
