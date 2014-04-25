package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
 *  
 * @author Hugo Leonardo
 * @created 08/06/2010
 *  
 * 
 */
public class LiberarArquivoTextoLeituraDivisaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";

		HttpSession sessao = this.getSessao(httpServletRequest);     
	
		ConsultarArquivoTextoLeituraDivisaoPopupActionForm consultarArquivoTextoLeituraDivisaoPopupActionForm = 
			(ConsultarArquivoTextoLeituraDivisaoPopupActionForm) actionForm;

		// Saber se vai liberar ou nao liberar
		String liberar = (String) httpServletRequest.getParameter("liberar");
		if (consultarArquivoTextoLeituraDivisaoPopupActionForm.getIdsRegistros() != null) {
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < consultarArquivoTextoLeituraDivisaoPopupActionForm
					.getIdsRegistros().length; i++) {
				v.add(new Integer(consultarArquivoTextoLeituraDivisaoPopupActionForm
						.getIdsRegistros()[i]));

			}
			
			if(liberar == null){
				liberar = (String) sessao.getAttribute("liberar");
			}
			
			if ( liberar.equals( SituacaoTransmissaoLeitura.LIBERADO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
				descricaoSituacaoNova = "LIBERADO";				
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.EM_CAMPO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
				descricaoSituacaoNova = "EM CAMPO";
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.DISPONIVEL+"" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.DISPONIVEL;
				descricaoSituacaoNova = "DISPONÍVEL";				
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.FINALIZADO_USUARIO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.FINALIZADO_USUARIO;
				descricaoSituacaoNova = "FINALIZADO PELO USUÁRIO";				
			}			
			
			fachada.atualizarListaArquivoTextoDivisao(v, situacaoNova);
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto para Leitura Divisão Alterado para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar outra Manutenção de Arquivo Texto para Leitura Divisão",
				"exibirConsultarArquivoTextoLeituraDivisaoPopupAction.do");

		consultarArquivoTextoLeituraDivisaoPopupActionForm.setIdsRegistros(null);

		return retorno;
	}

}
