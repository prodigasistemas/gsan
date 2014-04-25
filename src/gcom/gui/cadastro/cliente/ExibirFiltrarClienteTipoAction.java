package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 18 de junho de 2007
 */
public class ExibirFiltrarClienteTipoAction extends GcomAction {
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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarClienteTipoActionForm filtrarClienteTipoActionForm = (FiltrarClienteTipoActionForm) actionForm;
		// Código para checar ou não o ATUALIZAR

		filtrarClienteTipoActionForm.setAtualizar("1");
		filtrarClienteTipoActionForm.setTipoPessoa("1");

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarClienteTipoActionForm.setAtualizar("1");
			filtrarClienteTipoActionForm.setDescricao("");
			filtrarClienteTipoActionForm.setTipoPessoa("1");
			filtrarClienteTipoActionForm.setEsferaPoder("");
			sessao.setAttribute("atualizar", "1");

		}

		filtrarClienteTipoActionForm.setIndicadorAtualizar("1");

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarClienteTipoActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());

		}

		if (filtrarClienteTipoActionForm.getEsferaPoder() == null
				|| filtrarClienteTipoActionForm.getEsferaPoder().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			filtroEsferaPoder.adicionarParametro(new ParametroSimples(
					FiltroEsferaPoder.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna banco
			colecaoPesquisa = fachada.pesquisar(filtroEsferaPoder,
					EsferaPoder.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela ClienteTipo_porte foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Esfera Poder");
			} else {
				sessao.setAttribute("colecaoEsferaPoder", colecaoPesquisa);
			}

		}

		// Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				filtrarClienteTipoActionForm.setTipoPesquisa(sessao
						.getAttribute("tipoPesquisa").toString());
			}
		}

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarClienteTipo");

		return retorno;

	}
}
