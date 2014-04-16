package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da atualização da observação de um arquivo anexo ao RA (Aba nº 04 - Anexos) 
 *
 * @author Raphael Rossiter
 * @date 06/08/2009
 */
public class ExibirAtualizarRegistroAtendimentoObservacaoArquivoAnexoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarRegistroAtendimentoObservacaoArquivoAnexoAction");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        AtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm form = 
        (AtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm) actionForm;
        
        String idRegistroAtendimentoAnexo = httpServletRequest.getParameter("idRegistroAtendimentoAnexo");
        String acaoAtualizar = httpServletRequest.getParameter("acaoAtualizar");
        
        RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(
        idRegistroAtendimentoAnexo, sessao);
        
        if (registroAtendimentoAnexo != null){
        	
        	form.setIdRegistroAtendimentoAnexo(String.valueOf(GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo)));
        	form.setObservacaoAnexoAtualizacao(registroAtendimentoAnexo.getDescricaoDocumento());
        }
        
        //ATUALIZANDO OBSERVAÇÃO
        this.atualizarObservacao(form.getIdRegistroAtendimentoAnexo(), sessao, acaoAtualizar, 
        form.getObservacaoAnexoAtualizacao(), httpServletRequest);
        
        //MONTANDO URL DE RETORNO
		if (httpServletRequest.getParameter("telaRetorno") != null){
    		sessao.setAttribute("telaRetorno", 
    		(String.valueOf(httpServletRequest.getParameter("telaRetorno"))) + ".do");
    	}
        
        httpServletRequest.setAttribute("nomeCampo", "observacaoAnexoAtualizacao");
        
        return retorno;
	}
	
	/**
	 * Obter arquivo para atualização da observação
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, HttpSession sessao){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}
	
	/**
	 * Atualizar observação
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private void atualizarObservacao(String identificacao, HttpSession sessao,
			String acaoAtualizar, String observacao, HttpServletRequest httpServletRequest){
		
		if (acaoAtualizar != null && !acaoAtualizar.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					
					anexoColecao.setDescricaoDocumento(observacao);
					break;
				}
			}
			
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}
}
