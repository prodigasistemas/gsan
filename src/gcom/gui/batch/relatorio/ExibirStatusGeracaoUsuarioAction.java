package gcom.gui.batch.relatorio;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de status dos relatórios batch
 * por usuário
 * 
 * @author Rodrigo Silveira
 * @created 25/10/2006
 */
public class ExibirStatusGeracaoUsuarioAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("statusGeracaoUsuario");

		Fachada fachada = Fachada.getInstancia();

		int idProcesso = converterStringToInt(httpServletRequest
				.getParameter("idProcesso"));

		FiltroProcesso filtroProcesso = new FiltroProcesso();
		filtroProcesso.adicionarParametro(new ParametroSimples(
				FiltroProcesso.ID, idProcesso));

		//Passa para a página o nome do processo
		Processo processo = (Processo) Util.retonarObjetoDeColecao(fachada
				.pesquisar(filtroProcesso, Processo.class.getName()));

		httpServletRequest.setAttribute("nomeProcesso", processo
				.getDescricaoProcesso());

		// Pesquisar todos as funcionalidades iniciadas que representam os
		// relatórios batch do sistema por usuário
		httpServletRequest.setAttribute("colecaoRelatoriosDadosUsuario",
				fachada.pesquisarRelatoriosBatchPorUsuarioSistema(idProcesso));

		return retorno;
	}
}
