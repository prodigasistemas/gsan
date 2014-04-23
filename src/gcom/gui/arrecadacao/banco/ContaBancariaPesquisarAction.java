package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContaBancariaPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirContaBancariaPesquisar");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		ContaBancariaPesquisarActionForm form = (ContaBancariaPesquisarActionForm) actionForm;
		
		if (form.getIdBancoRecebido() != null && !form.getIdBancoRecebido().equals("-1")) {
			form.setIdBanco(form.getIdBancoRecebido());
		} else {
			form.setIdBancoRecebido(null);
		}
		
		String tipoPesquisa = null;
		
		if(httpServletRequest.getParameter("tipoPesquisa") != null && !httpServletRequest.getParameter("tipoPesquisa").equals("")) {
			tipoPesquisa = httpServletRequest.getParameter("tipoPesquisa");
			sessao.setAttribute("tipoPesquisa", tipoPesquisa);
		
		} else if (sessao.getAttribute("tipoPesquisa") != null && !sessao.getAttribute("tipoPesquisa").equals("")) {
			tipoPesquisa = (String) sessao.getAttribute("tipoPesquisa");
		}
		
		String idArrecadador = (String) sessao.getAttribute("idArrecadador");
		
		sessao.setAttribute("idArrecadador", idArrecadador);
		
		String limparCollections = (String)httpServletRequest.getParameter("limpar");
		if(limparCollections != null && limparCollections.trim().equals("sim")){
			httpServletRequest.removeAttribute("collectionAgencia");
			sessao.removeAttribute("collectionContaBancaria");
			httpServletRequest.removeAttribute("collectionAgencia");
			form.setIdAgencia("");
			form.setIdBanco("");
			form.setNumeroConta("");
		}
			
		FiltroBanco filtroBanco = new FiltroBanco();
		Collection<Banco> collectionBanco = fachada.pesquisar(filtroBanco,
				Banco.class.getName());
		
		String idBanco = form.getIdBanco();
		if(idBanco != null && !idBanco.trim().equals("")){
		
		FiltroAgencia filtroAgencia = new FiltroAgencia(FiltroAgencia.NOME_AGENCIA);
		filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.BANCO_ID, idBanco));
		Collection<Agencia> collectionAgencia = fachada.pesquisar(
				filtroAgencia, Agencia.class.getName());
		
		httpServletRequest.setAttribute("collectionAgencia", collectionAgencia);
		}
		
		
		httpServletRequest.setAttribute("collectionBanco", collectionBanco);


		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaContaBancaria") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaContaBancaria",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaContaBancaria"));
		}
		
		
//		if(httpServletRequest.getParameter("novaPesquisa") == null){
//			sessao.removeAttribute("caminhoRetornoTelaPesquisaContaBancaria");
//		}
		
		return retorno;
	}

}
