package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
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
 * [UC0808]Filtrar Anormalidade de Consumo
 * 
 * @author Vinícius Medeiros
 * @date 03/06/2008
 */

public class FiltrarConsumoAnormalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterConsumoAnormalidade");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarConsumoAnormalidadeActionForm filtrarConsumoAnormalidadeActionForm = (FiltrarConsumoAnormalidadeActionForm) actionForm;

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarConsumoAnormalidadeActionForm
				.getId();
		String descricao = filtrarConsumoAnormalidadeActionForm
				.getDescricao();
		String descricaoAbreviada = filtrarConsumoAnormalidadeActionForm
				.getDescricaoAbreviada();
		String tipoPesquisa = filtrarConsumoAnormalidadeActionForm
				.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidade.ID, id));
			}
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroConsumoAnormalidade.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroConsumoAnormalidade.DESCRICAO, descricao));
			} else {

				filtroConsumoAnormalidade.adicionarParametro(new ComparacaoTexto(
						FiltroConsumoAnormalidade.DESCRICAO, descricao));
			}
		}

		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroConsumoAnormalidade.adicionarParametro(new ComparacaoTextoCompleto(
					FiltroConsumoAnormalidade.DESCRICAO_ABREVIADA, descricaoAbreviada));
		} else {

			filtroConsumoAnormalidade.adicionarParametro(new ComparacaoTexto(
					FiltroConsumoAnormalidade.DESCRICAO_ABREVIADA, descricaoAbreviada));
		}

		if ( !filtrarConsumoAnormalidadeActionForm.getIndicadorUso().equals(ConstantesSistema.TODOS.toString()) ){
			filtroConsumoAnormalidade.adicionarParametro( new ParametroSimples( FiltroConsumoAnormalidade.INDICADOR_USO, filtrarConsumoAnormalidadeActionForm.getIndicadorUso() ) );
			peloMenosUmParametroInformado = true;			
		}	

		Collection colecaoConsumoAnormalidade = fachada.pesquisar(
				filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

		// Verificar a existencia de uma anormalidade de consumo
		if (colecaoConsumoAnormalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Anormalidade de Consumo");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Pesquisa sem registros

		if (colecaoConsumoAnormalidade == null || colecaoConsumoAnormalidade.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Anormalidade de Consumo");
		} else {
			httpServletRequest.setAttribute("colecaoConsumoAnormalidade",
					colecaoConsumoAnormalidade);
			ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();
			consumoAnormalidade = (ConsumoAnormalidade) Util
					.retonarObjetoDeColecao(colecaoConsumoAnormalidade);
			String idRegistroAtualizacao = consumoAnormalidade.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroConsumoAnormalidade", filtroConsumoAnormalidade);

		httpServletRequest.setAttribute("filtroConsumoAnormalidade", filtroConsumoAnormalidade);

		return retorno;

	}
}
