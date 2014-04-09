package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
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
 * [UC1046] Pesquisar Permissao Especial
 * 
 * @author Daniel Alves
 * @date 22/07/2010
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarPermissaoEspecialAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoPesquisaPermissaoEspecialAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarPermissaoEspecialActionForm pesquisarPermissaoEspecialActionForm = (PesquisarPermissaoEspecialActionForm) actionForm;

		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigo = pesquisarPermissaoEspecialActionForm.getCodigo();

		String descricao = pesquisarPermissaoEspecialActionForm.getDescricao();

		String codigoOperacao = pesquisarPermissaoEspecialActionForm.getCodigoOperacao();
		
		String tipoPesquisa = pesquisarPermissaoEspecialActionForm.getTipoPesquisa();
		// Verifica se o campo codigo foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

				
		
		if (descricao != null
				&& !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			 if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {

				    filtroPermissaoEspecial
							.adicionarParametro(new ComparacaoTextoCompleto(
									FiltroPermissaoEspecial.DESCRICAO, descricao));
				} else {

					filtroPermissaoEspecial.adicionarParametro(new ComparacaoTexto(
							FiltroPermissaoEspecial.DESCRICAO, descricao));
				}

		}

		// Verifica se o campo modulo foi informado

		if (codigoOperacao != null
				&& !codigoOperacao.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& !codigoOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.OPERACAO, codigoOperacao));			
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("operacao");
		
		Collection colecaoPermissaoEspecial = (Collection) fachada.pesquisar(
				filtroPermissaoEspecial, PermissaoEspecial.class.getName());

		if (colecaoPermissaoEspecial == null || colecaoPermissaoEspecial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"PermissaoEspecial");
		}

		sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
		
		return retorno;

	}

}
