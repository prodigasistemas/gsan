package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
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
 * [UC0792]Filtrar Diametro do Hidrometro
 * 
 * @author Vinicius Medeiros
 * @date 16/05/2008
 */

public class FiltrarHidrometroDiametroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterHidrometroDiametro");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarHidrometroDiametroActionForm filtrarHidrometroDiametroActionForm = (FiltrarHidrometroDiametroActionForm) actionForm;

		FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarHidrometroDiametroActionForm.getId();
		String descricao = filtrarHidrometroDiametroActionForm.getDescricao();
		String descricaoAbreviada = filtrarHidrometroDiametroActionForm.getDescricaoAbreviada();
		String numeroOrdem = filtrarHidrometroDiametroActionForm
				.getNumeroOrdem();
		String indicadorUso = filtrarHidrometroDiametroActionForm
				.getIndicadorUso();
		String tipoPesquisa = filtrarHidrometroDiametroActionForm
				.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
						FiltroHidrometroDiametro.ID, id));	
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroDiametro
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroDiametro.DESCRICAO, descricao));
			} else {

				filtroHidrometroDiametro.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroDiametro.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroHidrometroDiametro.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} else {

			filtroHidrometroDiametro.adicionarParametro(new ComparacaoTexto(
					FiltroHidrometroDiametro.DESCRICAO_ABREVIADA,
					descricaoAbreviada));
		}

		// Número da Ordem
		if (numeroOrdem != null
				&& !numeroOrdem.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroDiametro.NUMERO_ORDEM,
							numeroOrdem));
		} 
		


		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.INDICADOR_USO, indicadorUso));
		}
		
		Collection colecaoHidrometroDiametro = fachada
				.pesquisar(filtroHidrometroDiametro, HidrometroDiametro.class
						.getName());

		// Verificar a existencia de um Diametro do Hidrometro
		if (colecaoHidrometroDiametro.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Diâmetro do Hidrômetro");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoHidrometroDiametro == null
				|| colecaoHidrometroDiametro.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Diâmetro do Hidrômetro");
		} else {
			httpServletRequest.setAttribute("colecaoHidrometroDiametro",
					colecaoHidrometroDiametro);
			HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();
			hidrometroDiametro = (HidrometroDiametro) Util
					.retonarObjetoDeColecao(colecaoHidrometroDiametro);
			String idRegistroAtualizacao = hidrometroDiametro.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroHidrometroDiametro", filtroHidrometroDiametro);

		httpServletRequest.setAttribute("filtroHidrometroDiametro",
				filtroHidrometroDiametro);

		return retorno;

	}
}
