package gcom.gui.cobranca;


import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário as atividades de cobrança
 * cadastradas e disponíveis para atualização ou remoção
 * 
 * @author Raphael Rossiter
 * @date 13/09/2007
 */
public class ExibirManterAtividadeCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
		.findForward("manterAtividadeCobranca");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = 
		new FiltroCobrancaAtividade(FiltroCobrancaAtividade.DESCRICAO);
		
		//Componente de Paginação
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
		filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		
		Collection colecaoCobrancaAtividade = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		
		//[FS0001] Verificar existência de dados				
		if (colecaoCobrancaAtividade == null || colecaoCobrancaAtividade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		sessao.setAttribute("colecaoCobrancaAtividade", colecaoCobrancaAtividade);
		
		
		return retorno;
	}

}
