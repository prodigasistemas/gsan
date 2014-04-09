package gcom.gui.atendimentopublico.ligacaoagua;

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
 * @author Ivan Sergio
 * @date 02/12/2010
 */
public class InserirTipoCorteAction extends GcomAction {

	/**
	 * [UC1102] Inserir Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @date 03/12/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirTipoCorteActionForm form = (InserirTipoCorteActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		String descricao = form.getDescricao();
		String indicadorUso = form.getIndicadorUso();
		String indicadorCorteAdministrativo = form.getIndicadorCorteAdministrativo();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Inserir na base de dados o Tipo de Corte
		Integer idTipoCorte = fachada.inserirTipoCorte(descricao, indicadorUso, indicadorCorteAdministrativo, usuarioLogado);
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoCorteAction.do");
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Tipo de Corte " + descricao
				+ " inserido com sucesso.", "Inserir outro Tipo de Corte",
				"exibirInserirTipoCorteAction.do?menu=sim",
				"exibirAtualizarTipoCorteAction.do?idRegistroInseridoAtualizar="+
				idTipoCorte ,"Atualizar Tipo de Corte Inserido");
		
		sessao.removeAttribute("InserirMaterialActionForm");
		
		return retorno;
	}

}
