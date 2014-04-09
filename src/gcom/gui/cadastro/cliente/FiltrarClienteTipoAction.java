package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
public class FiltrarClienteTipoAction extends GcomAction {

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
				.findForward("exibirManterClienteTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarClienteTipoActionForm filtrarClienteTipoActionForm = (FiltrarClienteTipoActionForm) actionForm;

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarClienteTipoActionForm.getDescricao();

		String tipoPessoa = filtrarClienteTipoActionForm.getTipoPessoa();

		String esferaPoder = filtrarClienteTipoActionForm.getEsferaPoder();

		String tipoPesquisa = filtrarClienteTipoActionForm.getTipoPesquisa();
		
		String indicadorUso = filtrarClienteTipoActionForm.getIndicadorUso();
		
//		 Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Verifica se o campo Descrição do Cliente Tipo foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroClienteTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroClienteTipo.DESCRICAO, descricao));
			} else {

				filtroClienteTipo.adicionarParametro(new ComparacaoTexto(
						FiltroClienteTipo.DESCRICAO, descricao));
			}

			filtroClienteTipo.adicionarParametro(new ComparacaoTexto(
					FiltroClienteTipo.DESCRICAO, descricao));

		}

		// Verifica se o campo Tipo Pessoa foi informado

		if (tipoPessoa != null
				&& !tipoPessoa.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA,
					tipoPessoa));

		}

		// Verifica se o campo Esfera Poder foi informado

		if (esferaPoder != null
				&& !esferaPoder.trim().equalsIgnoreCase(
						"")) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.ESFERA_PODER, esferaPoder));

		}
		
		// Verifica se o campo Indicador de Uso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.INDICADOR_USO,
					indicadorUso));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroClienteTipo", filtroClienteTipo);

		return retorno;
	}

}
