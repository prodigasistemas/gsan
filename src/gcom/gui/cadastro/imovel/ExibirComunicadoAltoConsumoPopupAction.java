package gcom.gui.cadastro.imovel;

import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.FiltroComunicadoEmitirConta;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirComunicadoAltoConsumoPopupAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirComunicadoAltoConsumoPopup");

		ComunicadoAltoConsumoActionForm form = (ComunicadoAltoConsumoActionForm) actionForm;

		HttpSession sessao = request.getSession(false);
		
		String idImovel = request.getParameter("imovelID");

		if (idImovel != null && !idImovel.trim().equals("")) {

			FiltroComunicadoEmitirConta filtro = new FiltroComunicadoEmitirConta();
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.IMOVEL_ID, idImovel));

			Collection<ComunicadoEmitirConta> comunicados = getFachada().pesquisar(filtro, ComunicadoEmitirConta.class.getName());

			sessao.setAttribute("comunicados", comunicados);
		}
		return retorno;
	}

}
