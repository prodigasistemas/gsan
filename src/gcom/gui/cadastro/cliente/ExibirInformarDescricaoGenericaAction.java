package gcom.gui.cadastro.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarDescricaoGenericaAction extends GcomAction {

			public ActionForward execute(ActionMapping actionMapping,
					ActionForm actionForm, HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse) {

				// Seta o retorno
				ActionForward retorno = actionMapping
						.findForward("informarDescricaoGenerica");

				HttpSession sessao = httpServletRequest.getSession(false);

				Fachada fachada = Fachada.getInstancia();

				InformarDescricaoGenericaActionForm informarDescricaoGenericaActionForm = (InformarDescricaoGenericaActionForm) actionForm;
				
				List<DescricaoGenerica> colecaoDescricaoGenerica = new ArrayList();
				
				if (sessao.getAttribute("colecaoDescricaoGenerica") == null){
					FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
					colecaoDescricaoGenerica = (List<DescricaoGenerica>) fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
					if (colecaoDescricaoGenerica != null && !colecaoDescricaoGenerica.isEmpty()) {
						sessao.setAttribute("colecaoDescricaoGenerica", colecaoDescricaoGenerica);
					}
				} else {
					colecaoDescricaoGenerica = (List<DescricaoGenerica>) (Collection<DescricaoGenerica>) sessao.getAttribute("colecaoDescricaoGenerica");
				}

				if (httpServletRequest.getParameter("acao") != null && 
			        	httpServletRequest.getParameter("acao").equals("adicionar")
			        	&& informarDescricaoGenericaActionForm.getDescricao() != null
			        	&& !informarDescricaoGenericaActionForm.getDescricao().equals("")) {
					
					DescricaoGenerica descricaoGenerica = new DescricaoGenerica();
					descricaoGenerica.setNomeGenerico(
							informarDescricaoGenericaActionForm.getDescricao().toUpperCase());
					informarDescricaoGenericaActionForm.setDescricao("");
					colecaoDescricaoGenerica.add(descricaoGenerica);
					sessao.setAttribute("colecaoDescricaoGenerica", colecaoDescricaoGenerica);
				}
				
				if (httpServletRequest.getParameter("acao") != null && 
			        	httpServletRequest.getParameter("acao").equals("remover")
			        	&& httpServletRequest.getParameter("id") != null
			        	&& !httpServletRequest.getParameter("id").equals("")) {
					
					Integer indice = new Integer(httpServletRequest.getParameter("id")).intValue();
					
					if (colecaoDescricaoGenerica.size() >= indice) {
						colecaoDescricaoGenerica.remove(indice-1);
						sessao.setAttribute("colecaoDescricaoGenerica", colecaoDescricaoGenerica);
	            	}
				}
				
				if (httpServletRequest.getParameter("acao") != null && 
			        	httpServletRequest.getParameter("acao").equals("desfazer")) {
					
					FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
					colecaoDescricaoGenerica = (List<DescricaoGenerica>) fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
					
					if (colecaoDescricaoGenerica != null && !colecaoDescricaoGenerica.isEmpty()) {
						sessao.setAttribute("colecaoDescricaoGenerica", colecaoDescricaoGenerica);
					}
				}

				return retorno;
			}
}
