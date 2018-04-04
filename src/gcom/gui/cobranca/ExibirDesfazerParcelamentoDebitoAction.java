package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDesfazerParcelamentoDebitoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("telaSucesso");

		String codigo = request.getParameter("codigoParcelamento").trim();
		String motivo = request.getParameter("motivo").trim();

		Usuario usuario = getUsuarioLogado(request);

		getFachada().desfazerParcelamentosDebito(motivo, new Integer(codigo), usuario);

		montarPaginaSucesso(request, "Parcelamento de Débitos desfeito com sucesso.", 
				"Realizar outra manutenção de Parcelamento de Débitos", 
				"exibirConsultarListaParcelamentoDebitoAction.do?menu=sim");

		return retorno;
	}
}
