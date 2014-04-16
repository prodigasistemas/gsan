package gcom.gui.cadastro.unidade;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Rafael Pinto
 * @created 26/07/2006
 */
public class PesquisarUnidadeSuperiorAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaUnidadeSuperiorResultado");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm 
			= (PesquisarUnidadeSuperiorActionForm) actionForm;

		// Recupera os parâmetros do form
		String unidadeTipo 	= pesquisarUnidadeSuperiorActionForm.getUnidadeTipoFilho();
		String nivel 		=  pesquisarUnidadeSuperiorActionForm.getNivelHierarquicoFilho();
		String localidade	=  pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho();
		
		String gerenciaRegional	=  pesquisarUnidadeSuperiorActionForm.getGerenciaRegionalFilho();
		String descricao		=  pesquisarUnidadeSuperiorActionForm.getDescricaoFilho();
		
		String sigla	=  pesquisarUnidadeSuperiorActionForm.getSiglaFilho();
		String empresa	=  pesquisarUnidadeSuperiorActionForm.getIdEmpresaFilho();

		String meioSolicitacao	=  pesquisarUnidadeSuperiorActionForm.getMeioSolicitacaoFilho();
		
		String unidadeEsgoto = pesquisarUnidadeSuperiorActionForm.getUnidadeEsgotoFilho();
		String unidadeAbreRegistro = pesquisarUnidadeSuperiorActionForm.getUnidadeAbreRegistroFilho();
		String unidadeAceita = pesquisarUnidadeSuperiorActionForm.getUnidadeAceitaFilho();
		
		// filtro para a pesquisa da unidade organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (unidadeTipo != null && !unidadeTipo.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeTipo));
		}

		if (nivel != null && !nivel.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.UNIDADE_TIPO_NIVEL, nivel));
		}

		if (localidade != null && !localidade.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_LOCALIDADE, localidade));
		}

		if (gerenciaRegional != null && 
			!gerenciaRegional.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.GERENCIAL_REGIONAL, gerenciaRegional));
		}

				
		if (descricao != null && !descricao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTexto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
		}

		if (sigla != null && !sigla.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.SIGLA, sigla));
		}

		if (empresa != null && !empresa.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.EMPRESA, empresa));
		}

		if (unidadeEsgoto != null && !unidadeEsgoto.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;

			if(!unidadeEsgoto.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ESGOTO,unidadeEsgoto));
			}
			
		}

		if (unidadeAbreRegistro != null && !unidadeAbreRegistro.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!unidadeAbreRegistro.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ABERTURA_RA,unidadeAbreRegistro));
			}			

		}

		if (unidadeAceita != null && !unidadeAceita.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!unidadeAceita.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){			
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_TRAMITE,unidadeAceita));
			}
			
		}

		if (meioSolicitacao != null && 
				!meioSolicitacao.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.MEIO_SOLICITACAO, meioSolicitacao));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// adiciona as dependências para serem mostradas na página
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		
		Collection colecaoUnidadeOrganizacionalSuperior = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		colecaoUnidadeOrganizacionalSuperior = 
			fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());


		if (colecaoUnidadeOrganizacionalSuperior == null || colecaoUnidadeOrganizacionalSuperior.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade organizacional");
		} else if (colecaoUnidadeOrganizacionalSuperior.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoUnidadeOrganizacionalSuperior",colecaoUnidadeOrganizacionalSuperior);
		}

		return retorno;
	}

}
