package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Material;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.FachadaException;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<>>
 * 
 * @author lms
 * @date 03/08/2006
 */
public class ExibirPesquisarServicoTipoMaterialAction extends GcomAction {

	/**
	 * Efetua pesquisa de serviço tipo material
	 * 
	 * [UC0410] Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 08/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarServicoTipoMaterialActionForm form = (PesquisarServicoTipoMaterialActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();		
		ActionForward retorno = actionMapping.findForward("exibirServicoTipoMaterialPopup");
		
		boolean erro = false;
		
		//Material
		
		if(httpServletRequest.getParameter("limpar") != null){
			form.setDescricaoMaterial("");
			form.setIdMaterial("");
			form.setQuantidadePadrao("");
			httpServletRequest.setAttribute("idMaterial","");
			httpServletRequest.setAttribute("descricaoMaterial","");
			httpServletRequest.setAttribute("quantidadePadrao","");
		}
		
		Integer idMaterial = Util.converterStringParaInteger(form.getIdMaterial());
		
		if (Util.validarNumeroMaiorQueZERO(idMaterial)) {
			try {
				Material material = fachada.pesquisarMaterial(idMaterial);
				form.setIdMaterial(material.getId().toString());
				form.setDescricaoMaterial(material.getDescricao());
				httpServletRequest.setAttribute("material", material);				
			} catch (FachadaException e) {
				form.setDescricaoMaterial("Material Inexistente");
				erro = true;
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null 
				&& httpServletRequest.getParameter("tipoConsulta").trim().length() > 0) {			
			if (httpServletRequest.getParameter("tipoConsulta").equals("material")) {					
				form.setIdMaterial(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDescricaoMaterial(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}		
		}		
		
		if ("inserir".equals(form.getMethod())) {
			if (!erro) {
				form.setMethod(null);
				Collection colecaoServicoTipoMaterial = (Collection) sessao.getAttribute("colecaoServicoTipoMaterial");
				fachada.validarAdicionarMaterial(colecaoServicoTipoMaterial, idMaterial);
				
				httpServletRequest.setAttribute("close", "true");
			}
		} else {
			form.setMethod(null);
			httpServletRequest.removeAttribute("close");
		}
		
		return retorno;
	}
	
}
