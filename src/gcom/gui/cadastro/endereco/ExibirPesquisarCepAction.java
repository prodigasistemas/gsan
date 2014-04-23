package gcom.gui.cadastro.endereco;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da pesquisa de CEPs
 * 
 * @author Raphael Rossiter
 * @date 05/05/2006
 */
public class ExibirPesquisarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirPesquisarCep");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		PesquisarCepActionForm pesquisarCepActionForm = (PesquisarCepActionForm) actionForm;

		if (httpServletRequest.getParameter("voltaFiltro") == null
				&& httpServletRequest.getParameter("pesquisaMunicipio") == null) {
			pesquisarCepActionForm.setIdMunicipio("");
			pesquisarCepActionForm.setNomeMunicipio("");
			pesquisarCepActionForm.setNomeLogradouro("");
		}

		/*
		 * Caso o parâmetro "Município" seja previamente definido pelo caso de
		 * uso que chama está funcionalidade, o mesmo deverá ser mantido para
		 * realização da pesquisa dos CEPs
		 */
		String idMunicipio = null;
		if(httpServletRequest
				.getParameter("idMunicipioDefinido") != null){
		idMunicipio = httpServletRequest
				.getParameter("idMunicipioDefinido");
		}else{
			idMunicipio = (String)httpServletRequest
			.getAttribute("idMunicipioDefinido");
		}

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		Collection colecaoMunicipio = null;
		Municipio municipio = null;
		if (httpServletRequest.getParameter("indicadorUsoTodos") != null) {
			sessao.setAttribute("indicadorUsoTodos", httpServletRequest
					.getParameter("indicadorUsoTodos"));
		}
		if (idMunicipio != null && !idMunicipio.equals("")) {

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			if (sessao.getAttribute("indicadorUsoTodos") == null) {
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				sessao.removeAttribute("indicadorUsoTodos");
			}

			colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				sessao.setAttribute("municipioInformado", idMunicipio);

				municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				pesquisarCepActionForm.setIdMunicipio(String.valueOf(municipio
						.getId()));
				pesquisarCepActionForm.setNomeMunicipio(municipio.getNome());
			}

		} else if (pesquisarCepActionForm.getIdMunicipio() != null
				&& !pesquisarCepActionForm.getIdMunicipio().equals("")) {

			filtroMunicipio
					.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.ID, pesquisarCepActionForm
									.getIdMunicipio()));

			if (sessao.getAttribute("indicadorUsoTodos") == null) {
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				sessao.removeAttribute("indicadorUsoTodos");
			}

			colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				pesquisarCepActionForm.setIdMunicipio(String.valueOf(municipio
						.getId()));
				pesquisarCepActionForm.setNomeMunicipio(municipio.getNome());

				httpServletRequest.setAttribute("nomeCampo", "nomeLogradouro");
			} else {

				pesquisarCepActionForm.setIdMunicipio("");
				pesquisarCepActionForm
						.setNomeMunicipio("MUNICÍPIO INEXISTENTE");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"OK");
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
			}

		} else {
			sessao.removeAttribute("municipioInformado");
		}

		// Retorno para tela de informar endereco
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaInformarEndereco") != null) {

			sessao
					.setAttribute(
							"caminhoRetornoTelaInformarEndereco",
							httpServletRequest
									.getParameter("caminhoRetornoTelaInformarEndereco"));

		}

		// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de cliente é de municipio
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarCepActionForm.setIdMunicipio(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarCepActionForm.setNomeMunicipio(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));
			}
		}
		if (httpServletRequest.getParameter("tipoPesquisaLogradouro") == null
				|| httpServletRequest.getParameter("tipoPesquisaLogradouro").equals("")) {

			pesquisarCepActionForm.setTipoPesquisaLogradouro(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		if (httpServletRequest.getParameter("idCepInicial") != null && !httpServletRequest.getParameter("idCepInicial").equals("")){
			pesquisarCepActionForm.setIdCepInicial(Long.parseLong(httpServletRequest.getParameter("idCepInicial")));
		}
		if (httpServletRequest.getParameter("idCepFinal") != null && !httpServletRequest.getParameter("idCepFinal").equals("")){
			pesquisarCepActionForm.setIdCepFinal(Long.parseLong(httpServletRequest.getParameter("idCepFinal")));
		}

		return retorno;
	}

}
