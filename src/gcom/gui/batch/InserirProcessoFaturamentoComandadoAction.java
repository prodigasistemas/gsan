package gcom.gui.batch;

import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir processo
 * faturamento
 * 
 * @author Rodrigo Silveira
 * @created 11/08/2006
 */
public class InserirProcessoFaturamentoComandadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		String[] idsProcessosFaturamentoPagina = httpServletRequest.getParameterValues("idFaturamentoAtividadeCronograma");

		Collection<Integer> idsProcessosFaturamento = new ArrayList();
		
		for (int i = 0; i < idsProcessosFaturamentoPagina.length; i++) {
			idsProcessosFaturamento.add(Integer.parseInt(idsProcessosFaturamentoPagina[i]));
		}

		this.getFachada().inserirProcessoIniciadoFaturamentoComandado(
			idsProcessosFaturamento, 
			this.getUsuarioLogado(httpServletRequest));

		montarPaginaSucesso(httpServletRequest,
			"Processo(s) Iniciado(s) inserido(s) com sucesso.",
			"Inserir outro Processo", 
			"exibirInserirProcessoAction.do");

		return retorno;
	}

}
