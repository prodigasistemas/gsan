package gcom.gui.batch;

import gcom.batch.FiltroProcessoTipo;
import gcom.batch.ProcessoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirInserirProcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirProcesso");

		// Pesquisa todos os tipos de processos iniciados no sistema
		FiltroProcessoTipo filtroProcessoTipo = new FiltroProcessoTipo();
		filtroProcessoTipo.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroProcessoTipo.ID, ProcessoTipo.RELATORIO));

		Collection<ProcessoTipo> colecaoProcessoTipo = Fachada.getInstancia()
				.pesquisar(filtroProcessoTipo, ProcessoTipo.class.getName());

		httpServletRequest.setAttribute("colecaoProcessoTipo",
				colecaoProcessoTipo);

		return retorno;
	}

}
