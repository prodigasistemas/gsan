package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.AtualizarItemServicoActionForm;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
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
 *
 * @author Rodrigo Cabral
 * @date 04/08/2010
 */

public class ExibirAtualizarItemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("itemServicoAtualizar");				
		
		AtualizarItemServicoActionForm form = (AtualizarItemServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			codigo = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			codigo = ""+((ItemServico)sessao.getAttribute("atualizarItemServico")).getId();
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
		
		ItemServico itemServico = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			
			filtroItemServico.adicionarParametro(new ParametroSimples(FiltroItemServico.ID, codigo));
			
			Collection colecaoItemServico = fachada.pesquisar
				(filtroItemServico,ItemServico.class.getName());
			
			if(colecaoItemServico != null && !colecaoItemServico.isEmpty()){
				
				itemServico = (ItemServico)Util.retonarObjetoDeColecao(colecaoItemServico);
				
				form.setId(itemServico.getId().toString());
				form.setDescricao(itemServico.getDescricao());
				form.setDescricaoAbreviada(itemServico.getDescricaoAbreviada());
				form.setIndicadorUso(itemServico.getIndicadorUso().toString());
				
				if (itemServico.getCodigoConstanteCalculo() != null 
						&& !itemServico.getCodigoConstanteCalculo().toString().equals("")){
					
					form.setCodigoConstanteCalculo(itemServico.getCodigoConstanteCalculo().toString());
				}
				
				if (itemServico.getCodigoItem() != null && !itemServico.getCodigoItem().toString().equals("")){
					
					form.setCodigoItem(itemServico.getCodigoItem().toString());
				}
			
			}
			
			
			sessao.setAttribute("atualizarItemServico", itemServico);

			if (sessao.getAttribute("colecaoItemServico") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarItemServicoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarItemServicoAction.do");
			}
			
		}

		return retorno;
	
	}

}
