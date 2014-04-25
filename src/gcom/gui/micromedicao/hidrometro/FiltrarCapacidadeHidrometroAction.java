package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 18/06/2007
 */
public class FiltrarCapacidadeHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Cliente Tipo
	 * 
	 * [UC0???] Filtrar Cliente Tipo
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterCapacidadeHidrometro");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarCapacidadeHidrometroActionForm filtrarCapacidadeHidrometroActionForm = (FiltrarCapacidadeHidrometroActionForm) actionForm;

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarCapacidadeHidrometroActionForm.getDescricao();

		String codigo = filtrarCapacidadeHidrometroActionForm.getCodigo();

		String indicadorUso = filtrarCapacidadeHidrometroActionForm.getIndicadorUso();

		String tipoPesquisa = filtrarCapacidadeHidrometroActionForm.getTipoPesquisa();
		
		String atualizar = filtrarCapacidadeHidrometroActionForm.getAtualizar();
		
		httpServletRequest.setAttribute("atualizar", atualizar);

		// Verifica se o campo Descrição do Cliente Tipo foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroCapacidade
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroCapacidade.DESCRICAO, descricao));
			} else {

				filtroHidrometroCapacidade.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroCapacidade.DESCRICAO, descricao));
			}

			filtroHidrometroCapacidade.adicionarParametro(new ComparacaoTexto(
					FiltroHidrometroCapacidade.DESCRICAO, descricao));

		}
		

		// Verifica se o campo Código foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
					FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
					codigo));

		}

		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("") && !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.ID);
		
		sessao.setAttribute("filtroHidrometroCapacidade", filtroHidrometroCapacidade);

		return retorno;
	}

}
