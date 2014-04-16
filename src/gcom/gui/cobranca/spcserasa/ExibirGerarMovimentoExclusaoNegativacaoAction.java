package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ExibirGerarMovimentoExclusaoNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarMovimentoExclusaoNegativacao");

		GerarMovimentoExclusaoNegativacaoActionForm egmenActionForm = (GerarMovimentoExclusaoNegativacaoActionForm) actionForm;

		
		if (egmenActionForm.getOpcao() != null && egmenActionForm.getOpcao().equals("1")) {

			Collection coll = Fachada.getInstancia().consultarNegativadoresParaExclusaoMovimento();
			egmenActionForm.setCollNegativadores(coll);
		}

		return retorno;
	}
}
