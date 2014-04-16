package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da pesquisa de Observação do Registro Atendimento (Solicitação da CAER)
 *
 * @author Rafael Pinto
 * @date 14/03/2007
 */
public class ExibirPesquisarObservacaoRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirPesquisarObservacaoRegistroAtendimento");
		
		PesquisarObservacaoRegistroAtendimentoActionForm form = 
        	(PesquisarObservacaoRegistroAtendimentoActionForm) actionForm;
		
		if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			
			String inscricaoImovel = 
				this.getFachada().pesquisarInscricaoImovel(new Integer(form.getMatriculaImovel()));
			
			if(inscricaoImovel != null){
				form.setInscricaoImovel(inscricaoImovel);
			}else{
				httpServletRequest.setAttribute("imovelNaoEncontrado",true);
				form.setInscricaoImovel("Imóvel inexistente");
				form.setMatriculaImovel(null);
				
			}
		}

		return retorno;
	}

}
