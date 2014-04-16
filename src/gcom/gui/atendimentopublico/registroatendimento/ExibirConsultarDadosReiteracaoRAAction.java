package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.bean.DadosRAReiteracaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar dados da reiteração do RA
 * 
 * @author Vivianne Sousa
 * @created 17/05/2011
 */
public class ExibirConsultarDadosReiteracaoRAAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarDadosReiteracaoRA");

		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		String idRaReiteracao = httpServletRequest.getParameter("idRaReiteracao");
		Collection colecaoDadosReiteracao = (Collection)sessao.getAttribute("colecaoDadosReiteracao");
		
		Iterator iterDadosReiteracao = colecaoDadosReiteracao.iterator();
		while (iterDadosReiteracao.hasNext()) {
			DadosRAReiteracaoHelper helper = (DadosRAReiteracaoHelper) iterDadosReiteracao.next();
			
			if(helper.getRaReiteracao().getId().toString().equals(idRaReiteracao)){
				
				if(helper.getIdClienteSolicitante() != null || helper.getIdUnidadeSolicitante() != null){
					helper.setNomeSolicitante("");
				}
				String endereco = fachada.pesquisarEnderecoSolicitanteRAReiteracaoFormatado(
						helper.getRaReiteracao().getId());	
				
				helper.setEnderecoSolicitante(endereco);
				
				httpServletRequest.setAttribute("helper",helper);
				httpServletRequest.setAttribute("colecaoRAReiteracaoFone", helper.getColecaoRAReiteracaoFone());
				break;
			}
			
		}

		//envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsulta") != null) {
			
			httpServletRequest.setAttribute("caminhoRetornoTelaConsulta",
				httpServletRequest.getParameter("caminhoRetornoTelaConsulta"));
		}
		
		return retorno;
	}
	
}
