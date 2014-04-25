package gcom.gui.cadastro.unidade;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
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
 * @author Rafael Pinto
 * 
 * @created 26/07/2006
 */
public class PesquisarUnidadeOrganizacionalAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaUnidadeOrganizacionalResultado");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		

		PesquisarUnidadeOrganizacionalActionForm pesquisarUnidadeOrganizacionalActionForm 
			= (PesquisarUnidadeOrganizacionalActionForm) actionForm;
		
		String tipoUnidade = (String) sessao.getAttribute("tipoUnidade");
		
		String indicadorTramite = (String) sessao.getAttribute("indicadorTramite");
		
		// Recupera os parâmetros do form
		String unidadeTipo 	= pesquisarUnidadeOrganizacionalActionForm.getUnidadeTipo();
		String nivel 		=  pesquisarUnidadeOrganizacionalActionForm.getNivelHierarquico();
		String localidade	=  pesquisarUnidadeOrganizacionalActionForm.getIdLocalidade();
		
		String gerenciaRegional	=  pesquisarUnidadeOrganizacionalActionForm.getGerenciaRegional();
		String descricao		=  pesquisarUnidadeOrganizacionalActionForm.getDescricao();
		
		String sigla	=  pesquisarUnidadeOrganizacionalActionForm.getSigla();
		String empresa	=  pesquisarUnidadeOrganizacionalActionForm.getIdEmpresa();
		
		String unidadeSuperior	=  pesquisarUnidadeOrganizacionalActionForm.getIdUnidadeSuperior();
		String unidadeCentralizadora	=  pesquisarUnidadeOrganizacionalActionForm.getIdUnidadeCentralizadora();
		String meioSolicitacao	=  pesquisarUnidadeOrganizacionalActionForm.getMeioSolicitacao();
		
		String unidadeEsgoto = pesquisarUnidadeOrganizacionalActionForm.getUnidadeEsgoto();
		String unidadeAbreRegistro = pesquisarUnidadeOrganizacionalActionForm.getUnidadeAbreRegistro();
		String unidadeAceita = pesquisarUnidadeOrganizacionalActionForm.getUnidadeAceita();
		
		String tipoPesquisa = pesquisarUnidadeOrganizacionalActionForm.getTipoPesquisa();
		
		// filtro para a pesquisa da unidade organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if ( tipoUnidade != null && !tipoUnidade.equals("") ) {
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, tipoUnidade));
		}else {
			if (unidadeTipo != null && !unidadeTipo.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				peloMenosUmParametroInformado = true;
				
				filtroUnidadeOrganizacional.adicionarParametro(
						new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO, unidadeTipo));
			}
		}
		
		if(indicadorTramite != null && indicadorTramite.equals("1")){
			filtroUnidadeOrganizacional.adicionarParametro( new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_TRAMITE, ConstantesSistema.SIM));
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
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
			} else {
				filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTexto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
			}
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

		if (unidadeSuperior != null && !unidadeSuperior.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, unidadeSuperior));
		}

		if (unidadeCentralizadora != null && 
			!unidadeCentralizadora.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_CENTRALIZADORA, 
					unidadeCentralizadora));
		}

		if (unidadeEsgoto != null && !unidadeEsgoto.trim().equals("") && !unidadeEsgoto.trim().equals("3")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!unidadeEsgoto.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ESGOTO,unidadeEsgoto));
			}
		}
		

		if (unidadeAbreRegistro != null && !unidadeAbreRegistro.trim().equals("") && !unidadeAbreRegistro.trim().equals("3")) {
			
			peloMenosUmParametroInformado = true;
		
			if(!unidadeAbreRegistro.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ABERTURA_RA,unidadeAbreRegistro));
			}			
			
		}

		if (unidadeAceita != null && !unidadeAceita.trim().equals("") && !unidadeAceita.trim().equals("3")) {
			
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
		
		Collection colecaoUnidadeOrganizacional = null;
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		colecaoUnidadeOrganizacional = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		// Coloca a coleção na sessão			
		sessao.setAttribute("colecaoUnidadeOrganizacional",colecaoUnidadeOrganizacional);
		
		if(resultado.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade organizacional");
		}
		
		/*colecaoUnidadeOrganizacional = 
			fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrganizacional == null || colecaoUnidadeOrganizacional.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade organizacional");
		//} else if (colecaoUnidadeOrganizacional.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			//throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {

			
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			colecaoUnidadeOrganizacional = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			// Coloca a coleção na sessão			
			sessao.setAttribute("colecaoUnidadeOrganizacional",colecaoUnidadeOrganizacional);
		}*/

		return retorno;
	}

}
