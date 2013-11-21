package gcom.gui.micromedicao.hidrometro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

/**
 * < <Exibe o Filtrar Retorno Controle de Hidrometro >>
 *
 * @author: Wallace Thierre
 * @Data: 04/08/2010
 * 
 */

public class ExibirFiltrarRetornoControleHidrometroAction extends GcomAction{
	/**
	 * < <Descrição do método>>
	 *
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){


		ActionForward retorno = actionMapping
		.findForward("filtrarRetornoControleHidrometro");


		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRetornoControleHidrometroActionForm filtrarRetornoControleHidrometroActionForm = (FiltrarRetornoControleHidrometroActionForm) actionForm;

		//Quando for acessado pela primeira vez
		String primeiraVez = httpServletRequest.getParameter("menu");

		if(primeiraVez != null && !primeiraVez.equals("")){
			sessao.setAttribute("indicadorAtualizar", "1");   
			filtrarRetornoControleHidrometroActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}

		if(filtrarRetornoControleHidrometroActionForm.getIndicadorAtualizar()==null){
			filtrarRetornoControleHidrometroActionForm.setIndicadorAtualizar("1");
		}
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){

			filtrarRetornoControleHidrometroActionForm.setId("");
			filtrarRetornoControleHidrometroActionForm.setDescricao("");
			filtrarRetornoControleHidrometroActionForm.setIndicadorGeracao("");
			filtrarRetornoControleHidrometroActionForm.setIndicadorUso("");
			filtrarRetornoControleHidrometroActionForm.setDataCorrente("");        	
		}


		return retorno;



	}


}
