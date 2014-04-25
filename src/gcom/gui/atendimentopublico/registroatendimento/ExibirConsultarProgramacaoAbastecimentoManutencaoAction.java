package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 21/08/2006
 */
public class ExibirConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/08/2006
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
				.findForward("programacaoAbastecimentoManutencaoConsultar");

		ConsultarProgramacaoAbastecimentoManutencaoActionForm consultarProgramacaoAbastecimentoManutencaoActionForm = (ConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		String areaBairro = null;

		String idBairro = null;

		String codigoDigitadoMunicipioEnter = null;

		if (httpServletRequest.getParameter("mesAnoReferencia") == null) {

			String ano;
			String mes;

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);

			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setMesAnoReferencia(mes + "/" + ano);
		}

		String tela = (String) httpServletRequest.getParameter("tela");

		if (tela != null && !tela.equalsIgnoreCase("")) {
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setMunicipio("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setNomeMunicipio("");
			consultarProgramacaoAbastecimentoManutencaoActionForm.setBairro("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setNomeBairro("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setAreaBairro("-1");

			Collection<BairroArea> colecaoBairroArea = new ArrayList();

			httpServletRequest.setAttribute("colecaoBairroArea",
					colecaoBairroArea);

		} else {

			// [FS0001]- Verificar existência do município

			if (httpServletRequest.getParameter("idMunicipio") != null) {

				codigoDigitadoMunicipioEnter = (String) httpServletRequest
						.getParameter("idMunicipio");

			} else {

				codigoDigitadoMunicipioEnter = (String) consultarProgramacaoAbastecimentoManutencaoActionForm
						.getMunicipio();
			}

			if (httpServletRequest.getParameter("codigoBairro") != null) {

				idBairro = (String) httpServletRequest
						.getParameter("codigoBairro");
			} else {
				idBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
						.getBairro();
			}

			if (httpServletRequest.getParameter("tipoConsulta") != null) {
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"municipio")) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setMunicipio(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setNomeMunicipio(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}

				// Verifica se o tipo da consulta de cliente é de bairro
				// se for os parametros de enviarDadosParametros serão mandados
				// para
				// a pagina cliente_pesuisar.jsp
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"bairro")) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setBairro(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setNomeBairro(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
			}

			// ****Parte relativa ao campo Área de Bairro******

			if (idBairro == null || idBairro.equalsIgnoreCase("")) {

				Collection<BairroArea> colecaoBairroArea = new ArrayList();

				httpServletRequest.setAttribute("colecaoBairroArea",
						colecaoBairroArea);

			}

			// ***************************************************

			String menu = (String) httpServletRequest.getParameter("menu");

			if (menu == null || !menu.equals("sim")) {

				if (areaBairro == null) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setAreaBairro("");

				} else {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setAreaBairro(areaBairro);

				}

				// Verifica se o código foi digitado
				if (codigoDigitadoMunicipioEnter != null
						&& !codigoDigitadoMunicipioEnter.trim().equals("")
						&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

					filtroMunicipio.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));
					filtroMunicipio.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection municipioEncontrado = fachada.pesquisar(
							filtroMunicipio, Municipio.class.getName());

					if (municipioEncontrado != null
							&& !municipioEncontrado.isEmpty()) {
						// O municipio foi encontrado
						Municipio municipio = (Municipio) municipioEncontrado
								.iterator().next();
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setMunicipio(municipio.getId().toString());
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeMunicipio(municipio.getNome());
						// httpServletRequest.setAttribute("municipioNaoEncontrado",
						// "true");

					} else {
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setMunicipio("");
						httpServletRequest.setAttribute(
								"municipioNaoEncontrado", "exception");
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeMunicipio("Município inexistente");
					}
				}

				// [FS0003]- Verificar existência do bairro

				// Código do Bairro

				String codigoDigitadoBairroEnter = null;
				if (httpServletRequest.getParameter("codigoBairro") != null) {

					codigoDigitadoBairroEnter = (String) httpServletRequest
							.getParameter("codigoBairro");

				} else {

					codigoDigitadoBairroEnter = (String) consultarProgramacaoAbastecimentoManutencaoActionForm
							.getBairro();
				}

				// Verifica se o código foi digitado
				if (codigoDigitadoBairroEnter != null
						&& !codigoDigitadoBairroEnter.trim().equals("")
						&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {
					FiltroBairro filtroBairro = new FiltroBairro();

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.CODIGO, codigoDigitadoBairroEnter));

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
					// Adiciona a busca por município se ele foi digitado na
					// página
					if (codigoDigitadoMunicipioEnter != null
							&& !codigoDigitadoMunicipioEnter
									.equalsIgnoreCase("")) {
						filtroBairro.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
					}

					Collection bairroEncontrado = fachada.pesquisar(
							filtroBairro, Bairro.class.getName());

					if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
						// O bairro foi encontrado

						Bairro bairro = (Bairro) bairroEncontrado.iterator()
								.next();

						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setBairro("" + bairro.getCodigo());
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeBairro(bairro.getNome());

						// ****Parte relativa ao campo Área de Bairro******
						FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

						filtroBairroArea
								.adicionarParametro(new ParametroSimples(
										FiltroBairroArea.ID_BAIRRO, bairro
												.getId().toString()));

						Collection<BairroArea> colecaoBairroArea = fachada
								.pesquisar(filtroBairroArea, BairroArea.class
										.getName());

						if (colecaoBairroArea == null
								|| colecaoBairroArea.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Tabela Bairro Área ");
						}

						httpServletRequest.setAttribute("colecaoBairroArea",
								colecaoBairroArea);

						// Fim Parte relativa ao campo Área de Bairro
					} else {
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setBairro("");
						httpServletRequest.setAttribute("bairroNaoEncontrado",
								"exception");
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeBairro("Bairro inexistente");
						
						Collection<BairroArea> colecaoBairroArea = new ArrayList();

						httpServletRequest.setAttribute("colecaoBairroArea",
								colecaoBairroArea);
					}
				}
			}
		}
		return retorno;
	}
}
