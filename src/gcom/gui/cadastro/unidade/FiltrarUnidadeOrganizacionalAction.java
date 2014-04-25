package gcom.gui.cadastro.unidade;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
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

/**
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 * @date 20/11/2006
 */
public class FiltrarUnidadeOrganizacionalAction extends GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		UnidadeOrganizacionalActionForm form = (UnidadeOrganizacionalActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("retornarFiltroUnidadeOrganizacional");

		// Recupera os parâmetros do form
		String idUnidade = form.getIdUnidade();
		String unidadeTipo 	=  form.getIdTipoUnidade();
		String nivel 		=  form.getNivelHierarquico();
		String localidade	=  form.getIdLocalidade();
		
		String gerenciaRegional	=  form.getIdGerenciaRegional();
		String descricao		=  form.getDescricao();
		
		String sigla	=  form.getSigla();
		String empresa	=  form.getIdEmpresa();
		
		String unidadeSuperior	=  form.getIdUnidadeSuperior();
		String unidadeCentralizadora	=  form.getIdUnidadeCentralizadora();
		String unidadeRepavimentadora	=  form.getIdUnidadeRepavimentadora();
		String meioSolicitacao	=  form.getIdMeioSolicitacao();
		
		String unidadeEsgoto = form.getUnidadeEsgoto();
		String unidadeAbreRegistro = form.getUnidadeAbreRA();
		String unidadeAceita = form.getUnidadeTramitacao();
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		String ordernarPor = form.getOrdernarPor();
		
		// filtro para a pesquisa da unidade organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		if (ordernarPor != null
				&& ordernarPor.equals(ConstantesSistema.ORDENAR_POR_CODIGO)) {
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.ID);
		} else {
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);
		}
		

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idUnidade != null && !idUnidade.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));
		}
		
		if (unidadeTipo != null && !unidadeTipo.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO, unidadeTipo));
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

		if (gerenciaRegional != null && !gerenciaRegional.trim().equals("")) {
			
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
					new ComparacaoTexto(FiltroUnidadeOrganizacional.SIGLA, sigla));
		}

		if (empresa != null && !empresa.trim().equals("")) {
			
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
			!unidadeCentralizadora.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_CENTRALIZADORA, 
					unidadeCentralizadora));
		}
		
		if (unidadeRepavimentadora != null && 
				!unidadeRepavimentadora.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA, 
							unidadeRepavimentadora));
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
				!meioSolicitacao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.MEIO_SOLICITACAO, meioSolicitacao));
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!indicadorUso.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){			
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, indicadorUso));
			}
		}		

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessão para o ExibirManterUnidadeOrganizacionalAction
		sessao.setAttribute("filtroUnidadeOrganizacional", filtroUnidadeOrganizacional);
		if(indicadorAtualizar != null && !indicadorAtualizar.trim().equals("")){
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		}else{
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		
		return retorno;
	}
		
}
