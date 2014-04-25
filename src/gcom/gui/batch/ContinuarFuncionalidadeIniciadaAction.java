package gcom.gui.batch;

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Continua um processamento batch de onde parou
 * 
 * @author Rodrigo Silveira
 * @created 13 de Agosto de 2008
 */
public class ContinuarFuncionalidadeIniciadaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosProcessoIniciado");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		if (ids.length > 1) {
			throw new ActionServletException(
				"atencao.batch.opcao_unica");
		}

		Integer idProcessoIniciado = (Integer) sessao
				.getAttribute("idProcessoIniciado");
		
		fachada.continuarFuncionalidadesIniciadas(ids, idProcessoIniciado);

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada(
				FiltroFuncionalidadeIniciada.SEQUENCIAL_EXECUCAO);
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.PROCESSO_INICIADO_ID,
				idProcessoIniciado));

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processoSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

		Collection colecaoFuncionalidadeIniciada = fachada.pesquisar(
				filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class
						.getName());

		if (colecaoFuncionalidadeIniciada == null
				|| colecaoFuncionalidadeIniciada.isEmpty()) {
			throw new ActionServletException(
					"atencao.processo_iniciado.funcionalidade_iniciada.inexistente");

		}

		httpServletRequest
				.setAttribute("mesAnoReferencia", Util
						.formatarAnoMesParaMesAno(fachada
								.pesquisarParametrosDoSistema()
								.getAnoMesFaturamento()));

		httpServletRequest.setAttribute("dataCorrente", new Date());

		httpServletRequest.setAttribute("processoIniciado",
				((FuncionalidadeIniciada) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeIniciada))
						.getProcessoIniciado());

		httpServletRequest.setAttribute("concluido", new Integer(
				ProcessoSituacao.CONCLUIDO));
		httpServletRequest.setAttribute("concluidoComErro", new Integer(
				ProcessoSituacao.CONCLUIDO_COM_ERRO));
		httpServletRequest.setAttribute("execucaoCancelada", new Integer(
				ProcessoSituacao.EXECUCAO_CANCELADA));

		httpServletRequest.setAttribute("colecaoFuncionalidadeIniciada",
				colecaoFuncionalidadeIniciada);

		return retorno;
	}

}
