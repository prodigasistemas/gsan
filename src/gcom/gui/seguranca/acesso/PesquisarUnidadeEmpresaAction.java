package gcom.gui.seguranca.acesso;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Thiago Tenório
 * @created 21 de Julho de 2005
 */
public class PesquisarUnidadeEmpresaAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaUnidadeEmpresaResultado");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		UnidadeEmpresaActionForm unidadeEmpresaActionForm = (UnidadeEmpresaActionForm) actionForm;

		// Recupera os parâmetros do form
		String idUnidade = unidadeEmpresaActionForm.getCodigoUnidade();
		String nomeUnidade = unidadeEmpresaActionForm.getNomeUnidade();
		String siglaUnidade =  unidadeEmpresaActionForm.getSiglaUnidade();
		String nivelHiearquia =  unidadeEmpresaActionForm.getNivelHiearquia();
		String idUnidadeSuperior = unidadeEmpresaActionForm.getIdUnidadeSuperior();
		
		// filtro para a pesquisa de endereco do cliente
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

		filtroUnidadeEmpresa.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idUnidade != null
				&& !idUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, idUnidade));
		}

		if (nomeUnidade != null && !nomeUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ComparacaoTexto(
					FiltroUnidadeOrganizacional.DESCRICAO, nomeUnidade));
		}

		if (siglaUnidade != null && !siglaUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.SIGLA, siglaUnidade));
		}

		if (nivelHiearquia != null && !nivelHiearquia.equals("" +(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, nivelHiearquia));
		}

		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidadeSuperior));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroUnidadeEmpresa
				.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeEmpresa
				.adicionarCaminhoParaCarregamentoEntidade("unidadeNivel");

		Collection colecaoUnidadesEmpresas = null;

		// Obtém a instância da Fachada
		//Fachada fachada = Fachada.getInstancia();

		// pesquisa os endereços do cliente
//		colecaoUnidadesEmpresas = fachada
//				.pesquisar(filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());

		colecaoUnidadesEmpresas = (Collection) resultado
				.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		

		if (colecaoUnidadesEmpresas == null || colecaoUnidadesEmpresas.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade empresa");
		} else if (colecaoUnidadesEmpresas.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoUnidadesEmpresas",
					colecaoUnidadesEmpresas);

		}

		return retorno;
	}

}
