package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
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
 * Filtrar Tipo de Alteracao
 * 
 * @author Vinícius Medeiros
 * @date 14/05/2008
 */

public class FiltrarAlteracaoTipoAction extends GcomAction {

	@SuppressWarnings("static-access")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterAlteracaoTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarAlteracaoTipoActionForm filtrarAlteracaoTipoActionForm = (FiltrarAlteracaoTipoActionForm) actionForm;

		FiltroAlteracaoTipo filtroAlteracaoTipo = new FiltroAlteracaoTipo();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarAlteracaoTipoActionForm.getId();
		String descricao = filtrarAlteracaoTipoActionForm.getDescricao();
		String descricaoAbreviada = filtrarAlteracaoTipoActionForm.getDescricaoAbreviada();
		String tipoPesquisa = filtrarAlteracaoTipoActionForm.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroAlteracaoTipo.adicionarParametro(new ParametroSimples(
						FiltroAlteracaoTipo.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroAlteracaoTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								filtroAlteracaoTipo.DESCRICAO, descricao));
			} else {

				filtroAlteracaoTipo.adicionarParametro(new ComparacaoTexto(
						filtroAlteracaoTipo.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroAlteracaoTipo
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroAlteracaoTipo.DESCRICAOABREVIADA,
							descricaoAbreviada));
		} else {

			filtroAlteracaoTipo.adicionarParametro(new ComparacaoTexto(
					filtroAlteracaoTipo.DESCRICAOABREVIADA,
					descricaoAbreviada));
		}
		
		Collection<AlteracaoTipo> colecaoAlteracaoTipo = fachada
				.pesquisar(filtroAlteracaoTipo, AlteracaoTipo.class
						.getName());

		// Verificar a existencia de um Grupo de Faturamento
		if (colecaoAlteracaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Tipo de Alteração");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoAlteracaoTipo == null
				|| colecaoAlteracaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Tipo de Alteração");
		} else {
			httpServletRequest.setAttribute("colecaoAlteracaoTipo",
					colecaoAlteracaoTipo);
			AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
			alteracaoTipo = (AlteracaoTipo) Util
					.retonarObjetoDeColecao(colecaoAlteracaoTipo);
			String idRegistroAtualizacao = alteracaoTipo.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroAlteracaoTipo", filtroAlteracaoTipo);

		httpServletRequest.setAttribute("filtroAlteracaoTipo",
				filtroAlteracaoTipo);

		return retorno;

	}
}
