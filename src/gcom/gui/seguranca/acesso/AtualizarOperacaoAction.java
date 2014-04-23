package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabelaPK;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por atualizar uma operação no sistema 
 * assim como seus relacionamentos
 *
 * @author Pedro Alexandre
 * @date 07/08/2006
 */
public class AtualizarOperacaoAction extends GcomAction {

	/**
	 * [UC0281] Manter Operação
	 *
	 * @author Pedro Alexandre
	 * @date 07/08/2006
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

		//Seta o mapeamento de retorno para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Recupera o form de atualizar opração
		AtualizarOperacaoActionForm atualizarOperacaoActionForm = (AtualizarOperacaoActionForm) actionForm;

		//Recupera os dados informados na página de atualizar operação
		String idTipoOperacao = atualizarOperacaoActionForm.getIdTipoOperacao();
		String idFuncionalidade = atualizarOperacaoActionForm.getIdFuncionalidade();
		String idArgumentoPesquisa = atualizarOperacaoActionForm.getIdArgumentoPesquisa();
		String idOperacaoPesquisa = atualizarOperacaoActionForm.getIdOperacaoPesquisa();
		
		//Cria as variáveis para armazear o tipo da operação a funcionalidade o
		//argumento de pesquisa e a operação de pesquisa
		OperacaoTipo operacaoTipo = null;
		Funcionalidade funcionalidade = null;
		TabelaColuna argumentoPesquisa = null;
		Operacao operacaoPesquisa = null;
		
		/*
		 * Caso o usuário tenha informado o tipo da operação
		 * seta o id do tipo da operação
		 */
		if (idTipoOperacao != null && !idTipoOperacao.trim().equals("")) {
			operacaoTipo = new OperacaoTipo();
			operacaoTipo.setId(new Integer(idTipoOperacao));
		}

		/*
		 * Caso o usuário tenha informado a funcionalidade da operação
		 * seta o id da funcionalidade
		 */
		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {
			funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(idFuncionalidade));
		}

		/*
		 * Caso o usuário tenha informado o argumento de pesquisa
		 * seta o id do argumento de pesquisa
		 */
		if (idArgumentoPesquisa != null && !idArgumentoPesquisa.trim().equals("")) {
			argumentoPesquisa = new TabelaColuna();
			argumentoPesquisa.setId(new Integer(idArgumentoPesquisa));
		}

		/*
		 * Caso o usuário tenha informado aoperação de pesquisa
		 * seta o id da operação de pesquisa
		 */		
		if (idOperacaoPesquisa != null && !idOperacaoPesquisa.trim().equals("")) {
			operacaoPesquisa = new Operacao();
			operacaoPesquisa.setId(new Integer(idOperacaoPesquisa));
		}

		//Monta a operação que vai ser atualizada
		Operacao operacao = (Operacao) sessao.getAttribute("operacaoAtualizar");
		operacao.setDescricao(atualizarOperacaoActionForm.getDescricao());
		operacao.setDescricaoAbreviada(atualizarOperacaoActionForm.getDescricaoAbreviada());
		operacao.setCaminhoUrl(atualizarOperacaoActionForm.getCaminhoUrl());
		operacao.setOperacaoTipo(operacaoTipo);
		operacao.setFuncionalidade(funcionalidade);
//		operacao.setTabelaColuna(argumentoPesquisa);
		operacao.setArgumentoPesquisa(argumentoPesquisa);
		operacao.setIdOperacaoPesquisa(operacaoPesquisa);

		//Variável que vai armazenar uma coleção de TabelaOperacao
		Collection<OperacaoTabela> colecaoOperacaoTabela = null;

		/*
		 * Caso exista a coleção de tabela operação na sessão
		 * recuperaa coleção e cria os relacionamentos entre 
		 * a operação e as tabelas informadas pelo usuário 
		 */
		if (sessao.getAttribute("colecaoOperacaoTabela") != null) {
			//Recupera a coleção de tabelas da seesão  
			Collection<Tabela> colecaoTabela = (Collection<Tabela>) sessao.getAttribute("colecaoOperacaoTabela");
			
			/*
			 * Caso a coleção detabelas não esteja vazia 
			 * colocaa coleção no iterator 
			 * para criar todos os relacionamentos entre tabela e operação 
			 */
			if(!colecaoTabela.isEmpty()){
				//Coloca a coleção no iterator
				Iterator<Tabela> iteratorTabela = colecaoTabela.iterator();
				
				//Instância a coleção
				colecaoOperacaoTabela = new ArrayList();
				
				//Laço para criar os relacionamentos entre a operação e as tabelas
				while (iteratorTabela.hasNext()) {
					Tabela tabela = iteratorTabela.next();
					OperacaoTabela operacaoTabela = new OperacaoTabela(new OperacaoTabelaPK(operacao.getId(),tabela.getId()));
					colecaoOperacaoTabela.add(operacaoTabela);
				}
			}
		}

		//Chamao metódo para atualizar a operação
		fachada.atualizarOperacao(operacao, colecaoOperacaoTabela,usuarioLogado);

		//Limpa a sessão
		sessao.removeAttribute("colecaoOperacaoTabela");

		//Monta a tela de sucesso
		montarPaginaSucesso(httpServletRequest, "Operação "
				+ operacao.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção Operação",
				"exibirFiltrarOperacaoAction.do?menu=sim");

		//Retorna o mapeamento de retorno 
		return retorno;
	}
}
