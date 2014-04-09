package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pelo pre-processamento de inserir economia
 * 
 * @author Sávio Luiz
 * @created 1 de Junho de 2004
 */
public class ExibirInformarEconomiaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("informarEconomia");

		EconomiaActionForm economiaActionForm = (EconomiaActionForm) actionForm;

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
						"sim")) {

			httpServletRequest.setAttribute("nomeCampo", "idImovel");
		}

		// Cria Coleçao
		Collection colecaoImovelSubCategoriasCadastradas = null;

		Collection colecaoImovelEconomiasModificadas = null;

		// int i = 1;
		// Coleção vinda do exibirInserirEconomiaAcion
		// nessa coleção estão todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null
				&& !sessao
						.getAttribute("colecaoImovelSubCategoriasCadastradas")
						.equals("")) {

			colecaoImovelSubCategoriasCadastradas = (Collection) sessao
					.getAttribute("colecaoImovelSubCategoriasCadastradas");

		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// cria uma coleção para os imoveis economias inseridos e/ou modificado
		// essa coleção será responsável pela inserção ou modificação na base
		if (sessao.getAttribute("colecaoImovelEconomiasModificadas") != null
				&& !sessao.getAttribute("colecaoImovelEconomiasModificadas")
						.equals("")) {

			colecaoImovelEconomiasModificadas = (Collection) sessao
					.getAttribute("colecaoImovelEconomiasModificadas");

		} else {
			colecaoImovelEconomiasModificadas = new ArrayList();
		}

		// caso venha do popup de inserir_economia_popup não faz
		// nada so manda a coleção para a pagina de economia inserir
		if (httpServletRequest.getParameter("retornaDoPopup") == null) {

			FiltroImovel filtroImovel = new FiltroImovel();

			Fachada fachada = Fachada.getInstancia();

			String idImovel = null;

			// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
			// consulta vem do
			// imovel_resultado_pesquisa.jsp.
			// É feita essa verificação pois pode ser que ainda não tenha
			// feito a pesquisa de imovel.
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {

				// Verifica se vem algum parametro do tipo consulta, que pode
				// vim do removerEconomiaAction,
				// ou da pagina economia_informar,ou pelo enter ou pela pesquisa
				// da lupa

				idImovel = (String) economiaActionForm.getIdImovel();

				// remove da sessao o caminho da pesquisa pela lupa de imóvel
				sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");

				sessao
						.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");

				if (!httpServletRequest.getParameter("tipoConsulta").equals(
						"remover")) {
					colecaoImovelEconomiasModificadas = new ArrayList();
				}

			} else {

				sessao.removeAttribute("colecaoImovelSubCategoriasCadastradas");
				colecaoImovelSubCategoriasCadastradas = new ArrayList();

				sessao.removeAttribute("colecaoClientesImoveisEconomia");
				sessao
						.removeAttribute("clientesImoveisEconomiaSemDataFimRelacao");
				sessao.removeAttribute("colecaoImovelEconomiasModificadas");
				sessao.removeAttribute("imovel");
				sessao
						.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");

				colecaoImovelEconomiasModificadas = new ArrayList();
				economiaActionForm.setIdImovel(null);
				economiaActionForm.setEnderecoImovel(null);

			}

			// -------Parte que trata do código quando o usuário tecla enter
			// se o id do cliente for diferente de nulo
			if (idImovel != null
					&& !idImovel.toString().trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoRemovidas") != null) {
					sessao.removeAttribute("colecaoRemovidas");
				}

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				// adiciona o indicador de exclusão do imovel
				filtroImovel
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
								Imovel.IMOVEL_EXCLUIDO,
								FiltroParametro.CONECTOR_OR, 2));

				filtroImovel.adicionarParametro(new ParametroNulo(
						FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));

				/*
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				 * 
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("subLote");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("localidade");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				 */
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

				Collection imoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (imoveis == null || imoveis.isEmpty()) {

					// Caso a coleção não tenha retornado objetos
					economiaActionForm
							.setMatriculaImovel("MATRICULA INEXISTENTE");
					httpServletRequest.setAttribute("matInexistente", "ok");
					sessao
							.removeAttribute("colecaoImovelSubCategoriasCadastradas");
					colecaoImovelSubCategoriasCadastradas = new ArrayList();

					sessao.removeAttribute("colecaoClientesImoveisEconomia");
					sessao
							.removeAttribute("clientesImoveisEconomiaSemDataFimRelacao");
					sessao.removeAttribute("colecaoImovelEconomiasModificadas");
					sessao.removeAttribute("imovel");

					colecaoImovelEconomiasModificadas = new ArrayList();
					economiaActionForm.setIdImovel(null);
					economiaActionForm.setEnderecoImovel(null);
				} else {

					// retorna o imóvel que veio da coleção
					Imovel imovel = (Imovel) ((List) imoveis).get(0);

					Collection imoveisSubCategorias = fachada
							.obterColecaoImovelSubcategorias(imovel, 2);

					Iterator colecaoimoveisSubCategoriasIterator = imoveisSubCategorias
							.iterator();

					Collection colecao = new ArrayList();

					ImovelSubcategoria imovelSubcategoriaAux = null;

					while (colecaoimoveisSubCategoriasIterator.hasNext()) {

						ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) colecaoimoveisSubCategoriasIterator
								.next();
						imovelSubcategoriaAux = imovelSubcategoria;

						// idImovel
//						FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
//
//						filtroImovelEconomia.adicionarParametro(new ParametroSimples(
//								FiltroImovelEconomia.IMOVEL_ID, idImovel));
//						
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA);
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_SUB_CATEGORIA);
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.subcategoria.categoria");
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel.imovelPerfil");
//
//						filtroImovelEconomia.adicionarParametro(new ParametroSimples(
//							FiltroImovelEconomia.SUB_CATEGORIA_ID, imovelSubcategoria.getComp_id().getSubcategoria().getId()));
//
//						Collection colecaoImovelEconomia = fachada.pesquisar(
//								filtroImovelEconomia, ImovelEconomia.class.getName());
						
						
						Collection colecaoImovelEconomia = fachada.pesquisarImovelEconomia(
								new Integer(idImovel), imovelSubcategoria.getComp_id().getSubcategoria().getId());
						
						
						HashSet colecaoImovelEconomiaSet = new HashSet();
						colecaoImovelEconomiaSet.addAll(colecaoImovelEconomia);

						imovelSubcategoriaAux.setImovelEconomias(colecaoImovelEconomiaSet);
						colecao.add(imovelSubcategoriaAux);

					}
					// Imovel imovel = fachada.pe

					String enderecoFormatado = fachada
							.pesquisarEndereco(new Integer(idImovel));

					if (enderecoFormatado != null
							&& !enderecoFormatado.trim().equals("")) {
						economiaActionForm.setEnderecoImovel(enderecoFormatado);
					} else {
						economiaActionForm
								.setEnderecoImovel("Imóvel sem endereço");
					}

					colecaoImovelSubCategoriasCadastradas = new ArrayList();

					colecaoImovelSubCategoriasCadastradas.addAll(colecao);

					economiaActionForm.setMatriculaImovel(imovel
							.getInscricaoFormatada());
					sessao.setAttribute("imovel", imovel);
				}
			}
			// fim da parte que trata do codigo do usuario que tecla enter

		} else {
			sessao.removeAttribute("imovelEconomia");
		}

		sessao.setAttribute("colecaoImovelSubCategoriasCadastradas",
				colecaoImovelSubCategoriasCadastradas);

		// manda a coleção para os imoveis economias inseridos e/ou modificado
		sessao.setAttribute("colecaoImovelEconomiasModificadas",
				colecaoImovelEconomiasModificadas);

		return retorno;
	}
}
