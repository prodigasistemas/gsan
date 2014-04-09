package gcom.gui.cobranca.spcserasa;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da Geração da Movimentacao da Negativacao 
 * 
 * 
 * @author Thiago Silva Toscano de Brito
 * @date 18/12/2007
 */
public class GerarMovimentoExclusaoNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("gerarMovimentoExclusaoNegativacao");

		HttpSession sessao = httpServletRequest.getSession(false);


		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		GerarMovimentoExclusaoNegativacaoActionForm gmenActionForm = (GerarMovimentoExclusaoNegativacaoActionForm) actionForm;

		if ("1".equals(gmenActionForm.getOpcao())) {
			String[] ids = gmenActionForm.getNegativadores();
			Integer[] idNegativadores = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				idNegativadores[i] = new Integer(ids[i]);
			}
			Collection collection =Fachada.getInstancia().gerarMovimentoExclusaoNegativacao(idNegativadores,usuarioLogado);
			gmenActionForm.setCollMovimento(collection);
		} else {
			Collection collection =Fachada.getInstancia().gerarArquivoTXTMovimentoExclusaoNegativacao(new Integer(gmenActionForm.getCodigoMovimento()));
			gmenActionForm.setCollMovimento(collection);
		}

		return retorno;
	}
}
