package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
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
 * @author Vinicius Medeiros
 * @date 07/04/2008
 */
public class ExibirAtualizarMotivoCorteAction  extends GcomAction {

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
    	
    	// Seta o caminho de retorno
		ActionForward retorno = actionMapping
			.findForward("atualizarMotivoCorte");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver um esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarMotivoCorteActionForm atualizarMotivoCorteActionForm = (AtualizarMotivoCorteActionForm) actionForm;
		
		String idMotivoCorte = httpServletRequest
			.getParameter("idRegistroAtualizacao");
		
		// Verifica se veio do Filtrar ou Manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		
		if (idMotivoCorte == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idMotivoCorte = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idMotivoCorte = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		MotivoCorte motivoCorte = new MotivoCorte();
		
		
		if (idMotivoCorte != null && !idMotivoCorte.trim().equals("")
				&& Integer.parseInt(idMotivoCorte) > 0) {

			FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(
					FiltroMotivoCorte.ID, idMotivoCorte));
			Collection colecaoMotivoCorte = fachada.pesquisar(
					filtroMotivoCorte, MotivoCorte.class.getName());
			if (colecaoMotivoCorte != null && !colecaoMotivoCorte.isEmpty()) {
				motivoCorte = (MotivoCorte) Util
						.retonarObjetoDeColecao(colecaoMotivoCorte);
			}
			
			if (idMotivoCorte == null || idMotivoCorte.trim().equals("")) {

				atualizarMotivoCorteActionForm.setIdMotivoCorte(motivoCorte
						.getId().toString());

				atualizarMotivoCorteActionForm.setDescricao(motivoCorte
						.getDescricao());

			}
			
			atualizarMotivoCorteActionForm.setIdMotivoCorte(idMotivoCorte);
			
			atualizarMotivoCorteActionForm.setDescricao(motivoCorte.getDescricao());

			atualizarMotivoCorteActionForm.setIndicadorUso(""+motivoCorte
					.getIndicadorUso());

			sessao.setAttribute("motivoCorteAtualizar", motivoCorte);

			if(sessao.getAttribute("colecaoMotivoCorte") != null){
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/filtrarMotivoCorteAction.do");
			}else{
				sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirFiltrarMotivoCorteAction.do");
			}

		}
		
    	return retorno;
}
}
