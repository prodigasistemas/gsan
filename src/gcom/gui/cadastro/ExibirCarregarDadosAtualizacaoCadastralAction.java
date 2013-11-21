package gcom.gui.cadastro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * 
 * Carregar Dados para Atualizacao Cadastral
 *
 * @author ana maria
 * @date 18/05/2009
 */

public class ExibirCarregarDadosAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("CarregarDadosAtualizacaoCadastralAction");
		return retorno;
	}
}
