package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarMovimentoExclusaoNegativacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("gerarMovimentoExclusaoNegativacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		GerarMovimentoExclusaoNegativacaoActionForm form = (GerarMovimentoExclusaoNegativacaoActionForm) actionForm;

		if (form.getOpcao().equals("1")) {
			String[] ids = form.getNegativadores();
			Integer[] idNegativadores = new Integer[ids.length];
			
			for (int i = 0; i < ids.length; i++) {
				idNegativadores[i] = new Integer(ids[i]);
			}
			
			Collection collection = Fachada.getInstancia().gerarMovimentoExclusaoNegativacao(idNegativadores, usuarioLogado);
			form.setCollMovimento(collection);
		} else {
			Collection collection = Fachada.getInstancia().gerarArquivoTXTMovimentoExclusaoNegativacao(new Integer(form.getCodigoMovimento()));
			form.setCollMovimento(collection);
		}

		return retorno;
	}
}
