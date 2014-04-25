package gcom.gui.arrecadacao.banco;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class VerificarExistenciaContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarContaBancaria");
		//Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		//String idArrecadador = (String) httpServletRequest
		//.getParameter("idArrecadador");

		String idConta = (String) httpServletRequest
				.getParameter("idContaBancaria");
		
		//fachada.verificarExistenciaContaParaAvisoBancario(idArrecadador, idConta);
		
		String idBanco = (String) httpServletRequest
		.getParameter("idBanco");
		String codigoAgencia = (String) httpServletRequest
		.getParameter("codigoAgencia");
		String numeroConta = (String) httpServletRequest
		.getParameter("numeroConta");
		
		sessao.setAttribute("idContaBancaria", idConta);
		httpServletRequest.setAttribute("idBanco", idBanco);
		httpServletRequest.setAttribute("codigoAgencia", codigoAgencia);
		httpServletRequest.setAttribute("numeroConta", numeroConta);
				
		httpServletRequest.setAttribute("enviarDados", "enviarDados");
		sessao.removeAttribute("tipoPesquisa");
		sessao.removeAttribute("idArrecadador");

		return retorno;
	}
}
