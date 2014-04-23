package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
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
 * [UC0522] Atualizar Ramo de Atividade
 *
 * @author Fernando Fontelles Filho
 * @date 02/12/2009
 */

public class ExibirAtualizarRamoAtividadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("ramoAtividadeAtualizar");				
		
		AtualizarRamoAtividadeActionForm form = (AtualizarRamoAtividadeActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			codigo = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			codigo = ""+((RamoAtividade)sessao.getAttribute("atualizarRamoAtividade")).getCodigo();
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		RamoAtividade ramoAtividade = new RamoAtividade();
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
			
			filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.ID, codigo));
			
			Collection colecaoRamoAtividade = fachada.pesquisar
				(filtroRamoAtividade,RamoAtividade.class.getName());
			
			if(colecaoRamoAtividade != null && !colecaoRamoAtividade.isEmpty()){
			
				ramoAtividade = (RamoAtividade)Util.retonarObjetoDeColecao(colecaoRamoAtividade);
				
			}
			
			if(codigo == null || codigo.trim().equals("")){
				
				form.setCodigo(""+ramoAtividade.getCodigo());
				form.setDescricao(ramoAtividade.getDescricao());
				form.setIndicadorUso(ramoAtividade.getIndicadorUso());
				
			}
			
			form.setCodigo(""+ramoAtividade.getCodigo());
			form.setDescricao(ramoAtividade.getDescricao());
			form.setIndicadorUso(ramoAtividade.getIndicadorUso());
			
			sessao.setAttribute("atualizarRamoAtividade", ramoAtividade);

			if (sessao.getAttribute("colecaoRamoAtividade") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarRamoAtividadeAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarRamoAtividadeAction.do");
			}
			
		}

		return retorno;
	
	}

}
