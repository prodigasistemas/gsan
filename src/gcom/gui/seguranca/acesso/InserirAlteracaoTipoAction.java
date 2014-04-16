package gcom.gui.seguranca.acesso;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 17/04/2008
 */
public class InserirAlteracaoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Tipo de Alteracao
	 * 
	 * Inserir Tipo de Alteracao
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 14/05/2008
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

		InserirAlteracaoTipoActionForm inserirAlteracaoTipoActionForm = (InserirAlteracaoTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirAlteracaoTipoActionForm.getDescricao();
		String descricaoAbreviada = inserirAlteracaoTipoActionForm
				.getDescricaoAbreviada();
		
		AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirAlteracaoTipoActionForm.getDescricao())) {
			alteracaoTipo.setDescricao(inserirAlteracaoTipoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		// Descrição Abreviada
		if (!"".equals(inserirAlteracaoTipoActionForm.getDescricaoAbreviada())) {
			alteracaoTipo.setDescricaoAbreviada(inserirAlteracaoTipoActionForm
					.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição abreviada");
		}
		
		alteracaoTipo.setUltimaAlteracao(new Date());

		Short iu = 1;
		alteracaoTipo.setIndicadorUso(iu);

		FiltroAlteracaoTipo filtroAlteracaoTipo = new FiltroAlteracaoTipo();

		filtroAlteracaoTipo.adicionarParametro(new ParametroSimples(
				FiltroAlteracaoTipo.DESCRICAO, alteracaoTipo.getDescricao()));
		filtroAlteracaoTipo.adicionarParametro(new ParametroSimples(
				FiltroAlteracaoTipo.DESCRICAOABREVIADA, alteracaoTipo.getDescricaoAbreviada()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroAlteracaoTipo, AlteracaoTipo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.tipo_alteracao_ja_cadastrada", null,
					alteracaoTipo.getDescricao());
		} else {
			alteracaoTipo.setDescricao(descricao);
			alteracaoTipo.setDescricaoAbreviada(descricaoAbreviada);

			Integer idAlteracaoTipo = (Integer) fachada
					.inserir(alteracaoTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo de Alteração " + descricao
							+ " inserido com sucesso.",
					"Inserir outro tipo de alteração",
					"exibirInserirAlteracaoTipoAction.do?menu=sim",
					"exibirAtualizarAlteracaoTipoAction.do?idRegistroAtualizacao="
							+ idAlteracaoTipo,
					"Atualizar Tipo de Alteração Inserida");

			sessao.removeAttribute("InserirAlteracaoTipoActionForm");

			return retorno;
		}

	}
}
