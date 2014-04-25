package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
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
 * Exibe a pesquisa de serviço tipo atividade
 * 
 * @author lms
 * @date 03/08/2006
 */
public class ExibirPesquisarServicoTipoAtividadeAction extends GcomAction {

	/**
	 * Efetua pesquisa de serviço tipo atividade
	 * 
	 * [UC0410] Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 03/08/2006
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
		PesquisarServicoTipoAtividadeActionForm form = (PesquisarServicoTipoAtividadeActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();		
		ActionForward retorno = actionMapping.findForward("exibirServicoTipoAtividadePopup");
		
		boolean erro = false;
		
		//Atividade
		
		Integer idAtividade = Util.converterStringParaInteger(form.getIdAtividade());
		String atividadeUnica = httpServletRequest.getParameter("atividadeUnica");
		if(atividadeUnica == null || atividadeUnica.trim().equals("")){
			atividadeUnica = (String)sessao.getAttribute("atividadeUnica");
		}else{
			sessao.setAttribute("atividadeUnica",httpServletRequest.getParameter("atividadeUnica"));
		}
		
		if (Util.validarNumeroMaiorQueZERO(idAtividade)) {
			try {
				Atividade atividade = fachada.pesquisarAtividade(idAtividade, atividadeUnica);
				form.setIdAtividade(atividade.getId().toString());
				form.setDescricaoAtividade(atividade.getDescricao());
				httpServletRequest.setAttribute("atividade", atividade);
			} catch (FachadaException e) {
				form.setDescricaoAtividade("Atividade Inexistente");
				erro = true;
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null 
				&& httpServletRequest.getParameter("tipoConsulta").trim().length() > 0) {			
			if (httpServletRequest.getParameter("tipoConsulta").equals("atividade")) {					
				form.setIdAtividade(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDescricaoAtividade(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}		
		}		
		
		if ("inserir".equals(form.getMethod())) {
			if (!erro) {
				form.setMethod(null);
				Collection colecaoServicoTipoAtividade = (Collection) sessao.getAttribute("colecaoServicoTipoAtividade");
				fachada.validarAdicionarAtividade(colecaoServicoTipoAtividade, idAtividade);
				fachada.validarOrdemExecucao(colecaoServicoTipoAtividade, Short.parseShort(form.getOrdemExecucao()));
				httpServletRequest.setAttribute("close", "true");
			}
		} else {
			form.setMethod(null);
			httpServletRequest.removeAttribute("close");
		}
		
        if (httpServletRequest.getParameter("limparCampos") != null) {
        	form.reset();	
         }
		return retorno;
	}
	
}
