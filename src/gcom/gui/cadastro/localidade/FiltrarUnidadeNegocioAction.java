package gcom.gui.cadastro.localidade;

import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
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
 * [UC0???]FILTRAR Unidade Negocio
 * 
 * @author Rômulo Aurélio
 * @date 30/09/2008
 */

public class FiltrarUnidadeNegocioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterUnidadeNegocio");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarUnidadeNegocioActionForm form = (FiltrarUnidadeNegocioActionForm) actionForm;

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		boolean peloMenosUmParametroInformado = false;

		String id = form.getId();
		String nome = form.getNome();
		String nomeAbreviado = form.getNomeAbreviado();
		String idCliente = form.getIdCliente();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String cnpj = form.getNumeroCnpj();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, id));
			}
		}
		// Nome
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroUnidadeNegocio
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroUnidadeNegocio.NOME, nome));
			} else {

				filtroUnidadeNegocio.adicionarParametro(new ComparacaoTexto(
						FiltroUnidadeNegocio.NOME, nome));
			}
		}

		// Nome Abreviado
		if (nomeAbreviado != null && !nomeAbreviado.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroUnidadeNegocio.NOME_ABREVIADO, nomeAbreviado));
		}
		// Cliente
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.CLIENTE_ID, idCliente));
		}

		// Gerencia Regional
		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID_GERENCIA, idGerenciaRegional));

		}
		// Cnpj

		if (cnpj != null && !cnpj.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.CNPJ, cnpj));

		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO, indicadorUso));
		}

		filtroUnidadeNegocio
				.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.CLIENTE);
		filtroUnidadeNegocio
				.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		// Verificar a existencia
		if (colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Unidade de Negocio");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Pesquisa sem registros

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Unidade de Negocio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			String idRegistroAtualizar = unidadeNegocio.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroUnidadeNegocio", filtroUnidadeNegocio);

		httpServletRequest.setAttribute("filtroUnidadeNegocio",
				filtroUnidadeNegocio);

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionalidadeAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null
				&& form.getAtualizar().equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());

		}

		return retorno;
	}

}
