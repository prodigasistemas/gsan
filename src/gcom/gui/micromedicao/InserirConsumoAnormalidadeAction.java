package gcom.gui.micromedicao;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
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
 * @date 02/06/2008
 */
public class InserirConsumoAnormalidadeAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma anormalidade de Consumo
	 * 
	 * [UC0807] Inserir Anormalidade de Consumo
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 02/06/2008
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

		InserirConsumoAnormalidadeActionForm inserirConsumoAnormalidadeActionForm = (InserirConsumoAnormalidadeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirConsumoAnormalidadeActionForm.getDescricao();
		String descricaoAbreviada = inserirConsumoAnormalidadeActionForm
				.getDescricaoAbreviada();
		String mensagemConta = inserirConsumoAnormalidadeActionForm
				.getMensagemConta().toUpperCase();
		
		String indicadorRevisaoPermissaoEspecial = inserirConsumoAnormalidadeActionForm
			.getIndicadorRevisaoComPermissaoEspecial();
		
		ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirConsumoAnormalidadeActionForm.getDescricao())) {
			consumoAnormalidade.setDescricao(inserirConsumoAnormalidadeActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		consumoAnormalidade.setUltimaAlteracao(new Date());

		Short iu = 1;
		consumoAnormalidade.setIndicadorUso(iu);
		
		consumoAnormalidade.setIndicadorRevisaoPermissaoEspecial(new Short(indicadorRevisaoPermissaoEspecial));

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();

		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
				FiltroConsumoAnormalidade.DESCRICAO, consumoAnormalidade.getDescricao()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.consumo_anormalidade_ja_cadastrada", null,
					consumoAnormalidade.getDescricao());
		} else {
			consumoAnormalidade.setDescricao(descricao);
			consumoAnormalidade.setDescricaoAbreviada(descricaoAbreviada);
			consumoAnormalidade.setMensagemConta(mensagemConta);

			Integer idConsumoAnormalidade = (Integer) fachada
					.inserir(consumoAnormalidade);

			montarPaginaSucesso(httpServletRequest,
					"Anormalidade de Consumo " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Anormalidade de Consumo",
					"exibirInserirConsumoAnormalidadeAction.do?menu=sim",
					"exibirAtualizarConsumoAnormalidadeAction.do?idRegistroAtualizacao="
							+ idConsumoAnormalidade,
					"Atualizar Anormalidade de Consumo Inserida");

			sessao.removeAttribute("InserirConsumoAnormalidadeActionForm");

			return retorno;
		}

	}
}
