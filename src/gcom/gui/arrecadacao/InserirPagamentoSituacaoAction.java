package gcom.gui.arrecadacao;


import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirPagamentoSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Pagamento
	 * 
	 * [UC0767] Inserir Situação de  pagamento
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 28/04/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirPagamentoSituacaoActionForm inserirPagamentoSituacaoActionForm = (InserirPagamentoSituacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirPagamentoSituacaoActionForm.getDescricao();
		String descricaoAbreviada = inserirPagamentoSituacaoActionForm
				.getDescricaoAbreviada();
		
		
		PagamentoSituacao pagamentoSituacao = new PagamentoSituacao();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirPagamentoSituacaoActionForm.getDescricao())) {
			pagamentoSituacao.setDescricao(inserirPagamentoSituacaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		// Descrição Abreviada
		if (!"".equals(inserirPagamentoSituacaoActionForm.getDescricaoAbreviada())) {
			pagamentoSituacao.setDescricaoAbreviada(inserirPagamentoSituacaoActionForm
					.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição Abreviada");
		}
		pagamentoSituacao.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		// Ultima alteração
		pagamentoSituacao.setUltimaAlteracao(new Date());

		FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();

		filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(
				FiltroPagamentoSituacao.DESCRICAO, pagamentoSituacao.getDescricao()));

		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroPagamentoSituacao, PagamentoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.pagamentosituacao_ja_cadastrada", null,
					pagamentoSituacao.getDescricao());
			
		
		} else {
			pagamentoSituacao.setDescricao(descricao);
			pagamentoSituacao.setDescricaoAbreviada(descricaoAbreviada);

			Integer idPagamentoSituacao = (Integer) fachada
					.inserir(pagamentoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situacao Pagamento " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Situacao de Pagamento",
					"exibirInserirPagamentoSituacaoAction.do?menu=sim",
					"exibirAtualizarPagamentoSituacaoAction.do?idRegistroAtualizacao="
							+ idPagamentoSituacao,
					"Atualizar Situacao de Pagamento Inserida");

			sessao.removeAttribute("InserirPagamentoSituacaoActionForm");

			return retorno;
		}

	}
}
