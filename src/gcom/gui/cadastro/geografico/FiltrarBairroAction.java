package gcom.gui.cadastro.geografico;

import java.util.Collection;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
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
import org.apache.struts.validator.DynaValidatorForm;
import gcom.fachada.*;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 30 de Junho de 2004
 */
public class FiltrarBairroAction extends GcomAction {
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

		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String codigoBairro = (String) pesquisarActionForm.get("codigoBairro");
		String nome = (String) pesquisarActionForm.get("nomeBairro");
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipio");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		// String atualizar = (String) pesquisarActionForm.get("atualizar");
		String atualizar = httpServletRequest.getParameter("atualizar");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

		Fachada fachada = Fachada.getInstancia();

		FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, new Integer(codigoBairro)));
		}
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroBairro.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroBairro.NOME, nome));
			} else {
				filtroBairro.adicionarParametro(new ComparacaoTexto(
						FiltroBairro.NOME, nome));
			}
		}
		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, new Integer(idMunicipio)));
		}

		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoBairro = (Collection) fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
			if (atualizar != null && colecaoBairro.size() == 1) {
				Bairro bairro = (Bairro) colecaoBairro.iterator().next();
				httpServletRequest.setAttribute("idRegistroAtualizacao", bairro
						.getId());

				retorno = actionMapping.findForward("exibirAtualizarBairro");

			} else {
				retorno = actionMapping.findForward("retornarFiltroBairro");
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// Manda o filtro pelo request para o ExibirManterClienteAction
		httpServletRequest.setAttribute("filtroBairro", filtroBairro);
		sessao.setAttribute("filtroBairro", filtroBairro);

		return retorno;

	}
}
