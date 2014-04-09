package gcom.gui.cadastro;

import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar comando
 * 
 * @author Vivianne Sousa
 * @created 05/04/2011
 */
public class ExibirConsultarComandoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarComando");

		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();

		String idTarifaSocialComandoCarta = httpServletRequest.getParameter("comando");

		TarifaSocialComandoCarta tscc = fachada.pesquisarTarifaSocialComandoCarta(new Integer(idTarifaSocialComandoCarta));

		sessao.setAttribute("tarifaSocialComandoCarta",tscc);	
		
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaConta") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaConsultaConta",
				httpServletRequest.getParameter("caminhoRetornoTelaConsultaConta"));
		}
		
	
		return retorno;
	}
	
	
	
	
}
