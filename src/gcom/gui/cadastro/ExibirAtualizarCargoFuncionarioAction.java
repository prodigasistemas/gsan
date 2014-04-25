package gcom.gui.cadastro;


import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




/**
 * 
 * @author Arthur Carvalho
 * @date 11/08/2008
 */
public class ExibirAtualizarCargoFuncionarioAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("cargoFuncionarioAtualizar");

		AtualizarCargoFuncionarioActionForm atualizarCargoFuncionarioActionForm = (AtualizarCargoFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((FuncionarioCargo) sessao.getAttribute("funcionarioCargo")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		FuncionarioCargo funcionarioCargo= new FuncionarioCargo();
						
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroFuncionarioCargo filtroFuncionarioCargo= new FiltroFuncionarioCargo();
			filtroFuncionarioCargo.adicionarParametro(
				new ParametroSimples(FiltroFuncionarioCargo.ID, id));
			
			Collection colecaoFuncionarioCargo = fachada.pesquisar(
					filtroFuncionarioCargo, FuncionarioCargo.class.getName());
			
			if (colecaoFuncionarioCargo != null && !colecaoFuncionarioCargo.isEmpty()) {
				funcionarioCargo= (FuncionarioCargo) Util.retonarObjetoDeColecao(colecaoFuncionarioCargo);
			}

			if (id == null || id.trim().equals("")) {

				atualizarCargoFuncionarioActionForm.setCodigo(funcionarioCargo
						.getCodigo().toString());

				atualizarCargoFuncionarioActionForm
						.setDescricao(funcionarioCargo.getDescricao());

				atualizarCargoFuncionarioActionForm
						.setDescricaoAbreviada(funcionarioCargo.getDescricaoAbreviada());
				
				atualizarCargoFuncionarioActionForm
						.setIndicadorUso(funcionarioCargo.getIndicadorUso());

			}
			
			atualizarCargoFuncionarioActionForm.setCodigo(funcionarioCargo.getCodigo().toString());
			atualizarCargoFuncionarioActionForm.setDescricao(funcionarioCargo.getDescricao());
			atualizarCargoFuncionarioActionForm.setIndicadorUso(funcionarioCargo.getIndicadorUso());
			atualizarCargoFuncionarioActionForm.setDescricaoAbreviada(funcionarioCargo.getDescricaoAbreviada());

			
			sessao.setAttribute("atualizarCargoFuncionario", funcionarioCargo);

			if (sessao.getAttribute("colecaoFuncionarioCargo") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCargoFuncionarioAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCargoFuncionarioAction.do");
			}

		}
		

		return retorno;
	}
}
