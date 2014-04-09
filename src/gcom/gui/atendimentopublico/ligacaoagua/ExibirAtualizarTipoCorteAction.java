package gcom.gui.atendimentopublico.ligacaoagua;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ivan Sergio
 * @date 06/12/2010
 */

public class ExibirAtualizarTipoCorteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("atualizarTipoCorte");
		
		AtualizarTipoCorteActionForm atualizarTipoCorteActionForm = (AtualizarTipoCorteActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		CorteTipo corteTipo = null;
		String idTipoCorte = null;
		
		if (httpServletRequest.getParameter("idTipoCorte") != null && 
				!httpServletRequest.getParameter("idTipoCorte").equalsIgnoreCase("")) {
			//tela do manter
			idTipoCorte = (String) httpServletRequest.getParameter("idTipoCorte");
			sessao.setAttribute("idTipoCorte", idTipoCorte);
		} else if (sessao.getAttribute("idTipoCorte") != null && !sessao.getAttribute("idTipoCorte").equals("")) {
			//tela do filtrar
			idTipoCorte = (String) sessao.getAttribute("idTipoCorte");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir material
			idTipoCorte = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoCorteAction.do?menu=sim");
		}
		
		if (idTipoCorte == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				corteTipo = (CorteTipo) sessao.getAttribute("tipoCorteAtualizar");
			}else{
				idTipoCorte = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}else if (sessao.getAttribute("tipoCorteAtualizar") != null && !sessao.getAttribute("tipoCorteAtualizar").equals("")) {
			corteTipo = (CorteTipo) sessao.getAttribute("tipoCorteAtualizar");
		}else {
			if (corteTipo == null) {
				if (idTipoCorte != null && !idTipoCorte.equals("")) {
					FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
					filtroCorteTipo.adicionarParametro(new ParametroSimples(
							FiltroCorteTipo.ID, idTipoCorte));
	
					Collection colecaoTipoCorte = fachada.pesquisar(filtroCorteTipo, CorteTipo.class.getName());
	
					if (colecaoTipoCorte != null && !colecaoTipoCorte.isEmpty()) {
						corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoTipoCorte);
					}
				}
			}
		}
		
		atualizarTipoCorteActionForm.setIdTipoCorte(""+corteTipo.getId());
		atualizarTipoCorteActionForm.setDescricao(corteTipo.getDescricao());
		atualizarTipoCorteActionForm.setIndicadorUso(""+corteTipo.getIndicadorUso());
		atualizarTipoCorteActionForm.setIndicadorCorteAdministrativo(""+corteTipo.getIndicadorCorteAdministrativo());

		sessao.setAttribute("corteTipo", corteTipo);
		
		return retorno;
	}
}
