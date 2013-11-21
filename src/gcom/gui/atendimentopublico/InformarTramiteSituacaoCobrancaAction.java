package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.EspecificacaoUnidadeCobranca;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1159] Informar Tramite por Situação de Cobrança
 * 
 * Action responsável pelo processamento dos dados informados na tela de Informar Tramite por Situação de Cobrança
 * 
 * @author Mariana Victor
 * @date 14/04/2011
 */
public class InformarTramiteSituacaoCobrancaAction extends GcomAction {
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		List<EspecificacaoUnidadeCobranca> colecaoEspecificacaoUnidadeCobranca = new ArrayList();
		List<EspecificacaoUnidadeCobranca> colecaoEspecificacaoUnidadeCobrancaRemover = new ArrayList();

		if(sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca") != null
				&& !sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca").equals("")){
			
			colecaoEspecificacaoUnidadeCobranca = (ArrayList) 
    			sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca");
			
    	}
		if(sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover") != null
				&& !sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover").equals("")){
			
			colecaoEspecificacaoUnidadeCobrancaRemover = (ArrayList) 
    			sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover");
			
    	}
		
		//remover os registros
		if (colecaoEspecificacaoUnidadeCobrancaRemover != null
				&& !colecaoEspecificacaoUnidadeCobrancaRemover.isEmpty()) {
			Iterator iterator = colecaoEspecificacaoUnidadeCobrancaRemover.iterator();
			
			while (iterator.hasNext()) {
				EspecificacaoUnidadeCobranca especificacaoUnidadeCobranca = (EspecificacaoUnidadeCobranca) iterator.next();
				fachada.remover(especificacaoUnidadeCobranca);
			}
		}
		
		//inserir os novos registros
		if (colecaoEspecificacaoUnidadeCobranca != null
				&& !colecaoEspecificacaoUnidadeCobranca.isEmpty()) {
			Iterator iterator = colecaoEspecificacaoUnidadeCobranca.iterator();
			
			while (iterator.hasNext()) {
				EspecificacaoUnidadeCobranca especificacaoUnidadeCobranca = (EspecificacaoUnidadeCobranca) iterator.next();
				
				if (especificacaoUnidadeCobranca.getUltimaAlteracao() == null) {
					especificacaoUnidadeCobranca.setUltimaAlteracao(new Date());
					fachada.inserir(especificacaoUnidadeCobranca);
				}
				
			}
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Tramite por Situação de Cobrança informado com sucesso",
				"Informar outro Tramite por Situação de Cobrança",
				"exibirInformarTramiteSituacaoCobrancaAction.do?menu=sim");
		
		return retorno;
	}

}
