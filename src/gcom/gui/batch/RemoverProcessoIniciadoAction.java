package gcom.gui.batch;

import gcom.batch.ProcessoIniciado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove os processos iniciados selecionados na lista da funcionalidade
 * Consultar Processo Iniciado
 * 
 * @author Rodrigo Silveira
 * @date 27/07/2006
 */
public class RemoverProcessoIniciadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		fachada.remover(ids, ProcessoIniciado.class.getName(), null, null);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, ids.length
					+ " Processo(s) Iniciado(s) removido(s) com sucesso",
					"Realizar outra Consulta de Processo Iniciado",
					"exibirFiltrarProcessoAction.do?menu=sim",
					"exibirAutorizarRelatoriosBatchAction.do?menu=sim", "Autorizar Relatório"
					);
		}

		return retorno;
	}
}
