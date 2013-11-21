package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Exibe o Filtrar Imovel Perfil>>
 *
 * @author: Wallace Thierre
 * @Data: 30/09/2010
 * 
 */

public class ExibirFiltrarImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){


		ActionForward retorno = actionMapping
		.findForward("filtrarImovelPerfil");


		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;

		//Quando for acessado pela primeira vez
		String primeiraVez = httpServletRequest.getParameter("menu");

		if(primeiraVez != null && !primeiraVez.equals("")){
			sessao.setAttribute("indicadorAtualizar", "1");   
			filtrarImovelPerfilActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}

		if(filtrarImovelPerfilActionForm.getIndicadorAtualizar()==null){
			filtrarImovelPerfilActionForm.setIndicadorAtualizar("1");
		}
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){

			filtrarImovelPerfilActionForm.setId("");
			filtrarImovelPerfilActionForm.setDescricao("");
			filtrarImovelPerfilActionForm.setIndicadorUso("");
			filtrarImovelPerfilActionForm.setIndicadorGeracaoAutomatica("");	
			filtrarImovelPerfilActionForm.setIndicadorInserirManterPerfil("");
			filtrarImovelPerfilActionForm.setIndicadorGerarDadosLeitura("");
			filtrarImovelPerfilActionForm.setIndicadorBloquearRetificacao("");
			filtrarImovelPerfilActionForm.setIndicadorGrandeConsumidor("");
			filtrarImovelPerfilActionForm.setIndicadorBloquearDadosSocial("");
			filtrarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta("");	
			filtrarImovelPerfilActionForm.setUltimaAlteracao("");			
		}


		return retorno;



	}


}
