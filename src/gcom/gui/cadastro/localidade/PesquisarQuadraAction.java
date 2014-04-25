package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o processamento da página de pesquisa de Quadra
 * 
 * @author Flávio
 */

public class PesquisarQuadraAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaQuadra");

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String numeroQuadra = (String) pesquisarActionForm.get("numeroQuadra");
		String idRota = (String) pesquisarActionForm.get("idRota");
		//String nomeBairro = (String) pesquisarActionForm.get("nomeBairro");
		//String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		String idSetorComercial = (String) sessao
				.getAttribute("idSetorComercial");
		String codigoSetorComercial = (String) sessao
				.getAttribute("codigoSetorComercial");
		

		boolean peloMenosUmParametroInformado = false;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo Hibernate
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");


		if (numeroQuadra != null && !numeroQuadra.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));
			peloMenosUmParametroInformado = true;
		}
		

		if (idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, new Integer(
							idSetorComercial)));
			peloMenosUmParametroInformado = true;
		}
		if (codigoSetorComercial != null
				&& !codigoSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
							codigoSetorComercial)));
			peloMenosUmParametroInformado = true;
		}
		
		if (idRota != null && !idRota.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ROTA_ID, new Integer(idRota)));
		}

		if (sessao.getAttribute("indicadorUsoTodos") == null) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());

		if (quadras != null && !quadras.isEmpty()) {
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroQuadra, Quadra.class.getName());
			quadras = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("quadras", quadras);
		} else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "quadra");
		}

		// Passa parametros para distinguir o tipo de retorno
		sessao
				.setAttribute("tipoPesquisa", sessao
						.getAttribute("tipoPesquisa"));

		return retorno;
	}

}
