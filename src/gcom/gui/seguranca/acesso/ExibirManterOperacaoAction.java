package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável pela exibição da página de manter operação 
 *
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class ExibirManterOperacaoAction extends GcomAction {

	
	/**
	 * [UC0281] - Manter Operação 
	 *
	 * @author Pedro Alexandre
	 * @date 05/08/2006
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

		//Seta o mapeamento de retorno para a tela de manter operação
        ActionForward retorno = actionMapping.findForward("exibirManterOperacao");
        
        //Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("AtualizarOperacaoActionForm",null);
		
		//Cria a variável que vai armazenar a coleção de operações filtradas
        Collection colecaoOperacao = null;

        //Recupera o filtro da operação caso tenha na sessão
        FiltroOperacao filtroOperacao = null;
		if (sessao.getAttribute("filtroOperacao") != null) {
			filtroOperacao = (FiltroOperacao) sessao.getAttribute("filtroOperacao");
		}
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		
		/*
		 * Pesquisa a coleção de operações para o esquema de paginação
		 * e recupera o mapeamnento de retorno 
		 */
		Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroOperacao, Operacao.class.getName());
		colecaoOperacao = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
			
		/*
		 * Caso a coleção de pesquisa esteja vazia 
		 * levanta a exceção para o usuário indicando que a pesquisa 
		 * não retornou nenhum registro
		 */
		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		//Recupera a flag que indica que se o usuário quer ir direto para a tela do atualizar
		String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
		/*
		 * Caso a cloeção de pesquisa tenha um único registro e a a
		 * flag do atualizar esteja marcada, recupera a operação 
		 * da coleção e seta o código da operação para ser atualizada 
		 * na sessão. 
		 * Caso contrário manda a coleção de operações para a 
		 * página do manter.
		 */
		if (colecaoOperacao.size()== 1 && identificadorAtualizar != null){
			retorno = actionMapping.findForward("atualizarOperacao");
			Operacao operacao = (Operacao)colecaoOperacao.iterator().next();
			sessao.setAttribute("idRegistroAtualizar", new Integer (operacao.getId()).toString());
		}else{
			sessao.setAttribute("colecaoOperacao", colecaoOperacao);
		}

		//Seta o tipo da pesquisa na sessão
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		//Retorna o mapeamento contido na variável retorno 
        return retorno;
    }
}
