package gcom.gui.arrecadacao;


import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0774]FILTRAR Situacao de Pagamento
 * 
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class FiltrarPagamentoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterPagamentoSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarPagamentoSituacaoActionForm filtrarPagamentoSituacaoActionForm = (FiltrarPagamentoSituacaoActionForm) actionForm;

		FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarPagamentoSituacaoActionForm.getId();
		String descricao = filtrarPagamentoSituacaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarPagamentoSituacaoActionForm.getDescricaoAbreviada();
		String indicadorUso = filtrarPagamentoSituacaoActionForm
				.getIndicadorUso();
		String tipoPesquisa = filtrarPagamentoSituacaoActionForm
		.getTipoPesquisa();

		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(
						FiltroPagamentoSituacao.CODIGO, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroPagamentoSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroPagamentoSituacao.DESCRICAO, descricao));
			} else {

				filtroPagamentoSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroPagamentoSituacao.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroPagamentoSituacao
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroPagamentoSituacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} /*else {

			filtroPagamentoSituacao.adicionarParametro(new ComparacaoTexto(
					filtroPagamentoSituacao.DESCRICAO_ABREVIADA,
					descricaoAbreviada));
		}*/

				// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(
					FiltroPagamentoSituacao.INDICADOR_USO, indicadorUso));
		}
		
		Collection<PagamentoSituacao> colecaoPagamentoSituacao = fachada
				.pesquisar(filtroPagamentoSituacao, PagamentoSituacao.class
						.getName());

		// Verificar a existencia de um Grupo de arrecadacao
		if (colecaoPagamentoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Situação de Pagamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoPagamentoSituacao == null
				|| colecaoPagamentoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situacao de Pagamento");
		} else {
			httpServletRequest.setAttribute("colecaoPagamentoSituacao",
					colecaoPagamentoSituacao);
			PagamentoSituacao pagamentoSituacao = new PagamentoSituacao();
			pagamentoSituacao = (PagamentoSituacao) Util
					.retonarObjetoDeColecao(colecaoPagamentoSituacao);
			String idRegistroAtualizar = pagamentoSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroPagamentoSituacao", filtroPagamentoSituacao);

		httpServletRequest.setAttribute("filtroPagamentoSituacao",
				filtroPagamentoSituacao);

		return retorno;

	}
}
