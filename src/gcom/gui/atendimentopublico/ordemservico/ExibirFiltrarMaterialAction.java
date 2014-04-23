package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0382]FILTRAR MATERIAL
 * 
 * @author Kássia Albuquerque
 * @date 14/11/2006
 */



public class ExibirFiltrarMaterialAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Aponta o caminho de retorno do Action no struts
		ActionForward retorno = actionMapping.findForward("filtrarMaterial");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		FiltrarMaterialActionForm form = (FiltrarMaterialActionForm) actionForm;
	
		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();
		
		filtroMaterialUnidade.setCampoOrderBy(FiltroMaterialUnidade.DESCRICAO);
		
		Collection colecaoMaterialUnidade = fachada.pesquisar(filtroMaterialUnidade,MaterialUnidade.class.getName());
		
		// Coloca na sessao os valores da consulta do filtro
		sessao.setAttribute("colecaoMaterialUnidade",colecaoMaterialUnidade);
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar","1");
		}
		return retorno;
	}
}
