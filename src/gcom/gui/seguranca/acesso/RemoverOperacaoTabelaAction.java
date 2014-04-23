package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.seguranca.transacao.Tabela;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Remove uma tabela da coleção das tabelas da operação
 *
 * @author Pedro Alexandre
 * @date 09/05/2006
 */
public class RemoverOperacaoTabelaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento para tela de inseri operação
		ActionForward retorno = null;
		
		
		String telaRetorno = httpServletRequest.getParameter("telaRetorno");
		
		if(telaRetorno.equalsIgnoreCase("inserirOperacao")){
			retorno = actionMapping.findForward("operacaoInserir");
		}else if(telaRetorno.equalsIgnoreCase("atualizarOperacao")){
			retorno = actionMapping.findForward("operacaoAtualizar");
		}

		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera a coleção de tabelas da operação
		Collection<Tabela> colecaoOperacaoTabela = (Collection<Tabela>) sessao.getAttribute("colecaoOperacaoTabela");

		//Cria o iterator da coleção de tabelas 
		Iterator iteratorColecaoOperacaoTabela = colecaoOperacaoTabela.iterator();
		
		//Recupera o código da tabela selecionada para ser removida
		String idTabelaExcluir = httpServletRequest.getParameter("idTabelaExcluir");
		
		//Laço pararemover a tabelaselecionada da coleção
		while (iteratorColecaoOperacaoTabela.hasNext()) {
			//Recupera a tabela atual
			Tabela tabela = (Tabela) iteratorColecaoOperacaoTabela.next();
			
			//Caso seja a tabela selecionada remove a tabela da coleção
			if (Integer.parseInt(idTabelaExcluir) == tabela.getId().intValue()) {
				iteratorColecaoOperacaoTabela.remove();
				break;
			}

		}

		//Seta a coleção alterada na sessão
		sessao.setAttribute("colecaoOperacaoTabela",colecaoOperacaoTabela);

		//Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
