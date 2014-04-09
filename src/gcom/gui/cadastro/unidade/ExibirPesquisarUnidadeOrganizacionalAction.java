package gcom.gui.cadastro.unidade;


import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
 * Action que define o pré-processamento da página de pesquisa unidade organizacional
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class ExibirPesquisarUnidadeOrganizacionalAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarUnidadeOrganizacional");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarUnidadeOrganizacionalActionForm pesquisarUnidadeOrganizacionalActionForm = 
				(PesquisarUnidadeOrganizacionalActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		// Flag indicando que o usuário fez uma consulta com o UNID_ICTRAMITE = 1
		String indicadorTramite = httpServletRequest.getParameter("indicadorTramite");
		if (indicadorTramite != null && !indicadorTramite.trim().equals("") && 
				indicadorTramite.trim().equals("1")) {
			
			sessao.setAttribute("indicadorTramite", indicadorTramite);
		}
		
		//Flag indicando que o usuário fez uma consulta a partir do pesquisar Unidade Repavimentadora
		String tipoUnidade = httpServletRequest.getParameter("tipoUnidade");
		sessao.setAttribute("tipoUnidade", tipoUnidade);
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Localidade
			pesquisarLocalidade(httpServletRequest, retorno,pesquisarUnidadeOrganizacionalActionForm);

		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Unidade
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,pesquisarUnidadeOrganizacionalActionForm);

		}
		
		// Parte que passa as coleções necessárias no jsp
		Collection colecaoUnidadeTipo = (Collection) sessao.getAttribute("colecaoUnidadeTipo");
		
		if(colecaoUnidadeTipo == null){
			
			FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();
			
			filtroUnidadeTipo.adicionarParametro(new ParametroSimples(
					FiltroUnidadeTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeTipo.setCampoOrderBy(FiltroUnidadeTipo.DESCRICAO);

			colecaoUnidadeTipo = 
				fachada.pesquisar(filtroUnidadeTipo, UnidadeTipo.class.getName());

			if (colecaoUnidadeTipo != null && !colecaoUnidadeTipo.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeTipo", colecaoUnidadeTipo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Unidade Tipo");
			}
		}
		
		Collection colecaoGerenciaRegional = (Collection) sessao.getAttribute("colecaoGerenciaRegional");
		
		if(colecaoGerenciaRegional == null){
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			
			colecaoGerenciaRegional = 
				fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Gerêncial Regional");
			}
		}

		Collection colecaoMeioSolicitacao = (Collection) sessao.getAttribute("colecaoMeioSolicitacao");
		
		if(colecaoMeioSolicitacao == null){
			
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			
			filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
					FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			
			colecaoMeioSolicitacao = 
				fachada.pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());

			if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {
				sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Meio Solicitação");
			}
		}
		
		Collection colecaoUnidadeCentralizadora = 
			(Collection) sessao.getAttribute("colecaoUnidadeCentralizadora");
		
		if(colecaoUnidadeCentralizadora == null || colecaoUnidadeCentralizadora.isEmpty()){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO,
					UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA));

			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			
			colecaoUnidadeCentralizadora = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (colecaoUnidadeCentralizadora != null && !colecaoUnidadeCentralizadora.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeCentralizadora", colecaoUnidadeCentralizadora);
			} 
		}

		Collection colecaoEmpresa = 
			(Collection) sessao.getAttribute("colecaoEmpresa");
		
		if(colecaoEmpresa == null){

			// Filtro para obter empresa ativo de id informado
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			
			colecaoEmpresa = 
				fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Empresa");
			}
		}

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeOrganizacional") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeOrganizacional"));
			
		}
		
		if (httpServletRequest.getParameter("tipoUnidade") != null && 
			!httpServletRequest.getParameter("tipoUnidade").equals("")) {
			
			sessao.setAttribute("tipoUnidade",httpServletRequest.getParameter("tipoUnidade"));
			
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
		
			if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeSuperior")) {
				
				pesquisarUnidadeOrganizacionalActionForm.setIdUnidadeSuperior(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				pesquisarUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				
				httpServletRequest.setAttribute("idUnidadeEncontrada","true");
			
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("localidade")) {
				
				pesquisarUnidadeOrganizacionalActionForm.setIdLocalidade(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				pesquisarUnidadeOrganizacionalActionForm.setDescricaoLocalidade(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				
				httpServletRequest.setAttribute("idLocalidadeEncontrada","true");
			
			}		
		
		}
		
		if(httpServletRequest.getParameter("limparForm") != null){
			pesquisarUnidadeOrganizacionalActionForm.setUnidadeTipo(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
			pesquisarUnidadeOrganizacionalActionForm.setNivelHierarquico("");
			pesquisarUnidadeOrganizacionalActionForm.setIdLocalidade("");
			pesquisarUnidadeOrganizacionalActionForm.setDescricaoLocalidade("");
			pesquisarUnidadeOrganizacionalActionForm.setGerenciaRegional(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
			pesquisarUnidadeOrganizacionalActionForm.setDescricao("");
			pesquisarUnidadeOrganizacionalActionForm.setSigla("");
			pesquisarUnidadeOrganizacionalActionForm.setIdEmpresa(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
			pesquisarUnidadeOrganizacionalActionForm.setIdUnidadeSuperior("");
			pesquisarUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior("");
			pesquisarUnidadeOrganizacionalActionForm.setIdUnidadeCentralizadora(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
			pesquisarUnidadeOrganizacionalActionForm.setUnidadeEsgoto("3");
			pesquisarUnidadeOrganizacionalActionForm.setUnidadeAbreRegistro("3");
			pesquisarUnidadeOrganizacionalActionForm.setUnidadeAceita("3");
			pesquisarUnidadeOrganizacionalActionForm.setMeioSolicitacao(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
		}
		
		this.setaRequest(httpServletRequest,pesquisarUnidadeOrganizacionalActionForm);
		
		if(pesquisarUnidadeOrganizacionalActionForm.getTipoPesquisa() == null){
			pesquisarUnidadeOrganizacionalActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		return retorno;
	}
	
	private void pesquisarLocalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUnidadeOrganizacionalActionForm pesquisarUnidadeOrganizacionalActionForm) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(pesquisarUnidadeOrganizacionalActionForm.getIdLocalidade() )));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			// Exibe o código e a descrição pesquisa na página
			httpServletRequest.setAttribute("idLocalidadeEncontrada","true");
			
			pesquisarUnidadeOrganizacionalActionForm.setIdLocalidade(localidade.getId().toString());
			pesquisarUnidadeOrganizacionalActionForm.setDescricaoLocalidade(localidade.getDescricao());

		} else {

			pesquisarUnidadeOrganizacionalActionForm.setDescricaoLocalidade("Localidade inexistente");
			pesquisarUnidadeOrganizacionalActionForm.setIdLocalidade("");

		}
	}
	
	private void pesquisarUnidadeOrganizacional(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUnidadeOrganizacionalActionForm pesquisarUnidadeOrganizacionalActionForm) {
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		String idUnidade = pesquisarUnidadeOrganizacionalActionForm.getIdUnidadeSuperior();
		
		filtroUnidadeOrganizacional.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			UnidadeOrganizacional unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			// Exibe o código e a descrição pesquisa na página
			httpServletRequest.setAttribute("idUnidadeSuperiorEncontrada","true");
			
			pesquisarUnidadeOrganizacionalActionForm.setIdUnidadeSuperior(unidadeOrganizacional.getId().toString());
			pesquisarUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior(unidadeOrganizacional.getDescricao());

		} else {

			pesquisarUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior("Unidade Organizacional inexistente");
			pesquisarUnidadeOrganizacionalActionForm.setIdUnidadeSuperior("");

		}
	}
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarUnidadeOrganizacionalActionForm pesquisarUnidadeOrganizacionalActionForm){
		
		// Localidade
		if(pesquisarUnidadeOrganizacionalActionForm.getIdLocalidade() != null && 
			!pesquisarUnidadeOrganizacionalActionForm.getIdLocalidade().equals("") && 
			pesquisarUnidadeOrganizacionalActionForm.getDescricaoLocalidade() != null && 
			!pesquisarUnidadeOrganizacionalActionForm.getDescricaoLocalidade().equals("")){
					
			httpServletRequest.setAttribute("idLocalidadeEncontrada","true");
		}

		// Unidade Superior
		if(pesquisarUnidadeOrganizacionalActionForm.getIdUnidadeSuperior() != null && 
			!pesquisarUnidadeOrganizacionalActionForm.getIdUnidadeSuperior().equals("") && 
			pesquisarUnidadeOrganizacionalActionForm.getDescricaoUnidadeSuperior() != null && 
			!pesquisarUnidadeOrganizacionalActionForm.getDescricaoUnidadeSuperior().equals("")){
					
			httpServletRequest.setAttribute("idUnidadeEncontrada","true");
		}
		
	}

	
	
}
