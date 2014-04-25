package gcom.gui.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ana Maria
 * @date 22/11/2006
 */
public class AtualizarUnidadeOrganizacionalAction extends GcomAction {
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
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();
		
        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
		
		/*// ------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_HIDROMETRO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_HIDROMETRO_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------*/

    	AtualizarUnidadeOrganizacionalActionForm form = (AtualizarUnidadeOrganizacionalActionForm) actionForm;
		
    	//UnidadeOrganizacional unidadeNova = new UnidadeOrganizacional();
    	
    	UnidadeOrganizacional unidadeNova = (UnidadeOrganizacional)sessao.getAttribute("unidadeOrganizacional");
    	
    	//unidadeNova.setId(new Integer(form.getId()));
    	 
    	unidadeNova.setIndicadorEsgoto(new Short(form.getUnidadeEsgoto()).shortValue());
		
    	unidadeNova.setIndicadorTramite(new Short(form.getUnidadeAceita()).shortValue());
		
    	unidadeNova.setIndicadorAberturaRa(new Short(form.getUnidadeAbreRegistro()).shortValue());
		
    	unidadeNova.setIndicadorUso(new Short(form.getIndicadorUso()).shortValue());
		
    	if(form.getSigla()!= null){
    	  unidadeNova.setSigla(form.getSigla());
    	}else{
    	  unidadeNova.setSigla(null);	
    	}
    	
    	unidadeNova.setDescricao(form.getDescricao());
		
		String idUnidadeTipo = form.getUnidadeTipo();
		unidadeNova.setUnidadeTipo(this.pesquisarUnidadeTipo(idUnidadeTipo));
		
		String idEmpresa = form.getIdEmpresa();
		unidadeNova.setEmpresa(this.pesquisarEmpresa(idEmpresa));
		
		String idUnidadeSuperior = form.getIdUnidadeSuperior();
		if(idUnidadeSuperior != null && !idUnidadeSuperior.equals("")){
			unidadeNova.setUnidadeSuperior(this.pesquisarUnidadeOrganizacional(idUnidadeSuperior));	
		}else{
			unidadeNova.setUnidadeSuperior(null);		
		}

		String idUnidadeCentralizadora = form.getIdUnidadeCentralizadora();
		if(idUnidadeCentralizadora != null && 
			!idUnidadeCentralizadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeCentralizadora.equals("")){
			
			unidadeNova.setUnidadeCentralizadora(this.pesquisarUnidadeOrganizacional(idUnidadeCentralizadora));	
		}else{
			unidadeNova.setUnidadeCentralizadora(null);	
		}
		
		
		//...........................................................................................
		// 06/03/2008 - Alteração solicitada por Fabíola Araújo. 
		// Yara Taciane de Souza.
		//Inclusão de opção de tratamento pra Unidade Repavimentadora.
		
		String idUnidadeRepavimentadora = form.getIdUnidadeRepavimentadora();
		if(idUnidadeRepavimentadora != null && 
			!idUnidadeRepavimentadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeRepavimentadora.equals("")){
			
			unidadeNova.setUnidadeRepavimentadora(this.pesquisarUnidadeOrganizacional(idUnidadeRepavimentadora));	
		}else{
			unidadeNova.setUnidadeRepavimentadora(null);	
		}
	
		//......................................................................................

		String idMeioSolicitacao = form.getMeioSolicitacao();
		if(idMeioSolicitacao != null && 
				!idMeioSolicitacao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
				!idMeioSolicitacao.equals("")){
				
			unidadeNova.setMeioSolicitacao(this.pesquisarMeioSolicitacao(idMeioSolicitacao));	
		}

		
		if(unidadeNova.getGerenciaRegional() != null){
			
			String idGerenciaRegional = String.valueOf(unidadeNova.getGerenciaRegional().getId());
			
			if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
				unidadeNova.setGerenciaRegional(this.pesquisarGerenciaRegional(idGerenciaRegional));
			}
		}
		
		
		
		
		fachada.atualizarUnidadeOrganizacional(unidadeNova, this.getUsuarioLogado(httpServletRequest));
		
		//Inserir os novos municipios 
		if ( sessao.getAttribute("colecaoMunicipioSelecionado") != null &&
				!sessao.getAttribute("colecaoMunicipioSelecionado").equals("") ) {
			ArrayList colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
			
			Iterator iteratorColecaoMunicipiosSelecionados = colecaoMunicipioSelecionado.iterator();
			while (iteratorColecaoMunicipiosSelecionados.hasNext() ) {
			
				Municipio municipio = (Municipio) iteratorColecaoMunicipiosSelecionados.next();
				UnidadeOrganizacional unidadeRepav = new UnidadeOrganizacional();
				unidadeRepav.setId(new Integer(unidadeNova.getId()));
				
				UnidadeOrganizacionalMunicipio unidadeOrgMunicipio = new UnidadeOrganizacionalMunicipio();
	
				unidadeOrgMunicipio.setIdMunicipio(municipio);
				unidadeOrgMunicipio.setIdUnidadeRepavimentadora(unidadeRepav);
				unidadeOrgMunicipio.setDataVinculacao(new Date());
				unidadeOrgMunicipio.setDataDesvinculacao(null);
				unidadeOrgMunicipio.setUltimaAlteracao(new Date());
				
				
				FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
				filtro.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, municipio.getId()));
				filtro.adicionarParametro( new ParametroNulo(
						FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
				Collection colecao = fachada.pesquisar(filtro , UnidadeOrganizacionalMunicipio.class.getName());
				if (colecao != null && !colecao.isEmpty()) {
					UnidadeOrganizacionalMunicipio unid = (UnidadeOrganizacionalMunicipio) 
						Util.retonarObjetoDeColecao(colecao);
					
					unidadeOrgMunicipio.setId(unid.getId());
					
					fachada.atualizar(unidadeOrgMunicipio);	
				} else {
					fachada.inserir(unidadeOrgMunicipio);
				}
			}
		}
		//Atauliza os Municipios Desvinculados
		if ( sessao.getAttribute("colecaoMunicipiosDesvinculados") != null &&
				!sessao.getAttribute("colecaoMunicipiosDesvinculados").equals("") ) {
			
			ArrayList colecaoMunicipiosDesvinculados = (ArrayList) sessao.getAttribute("colecaoMunicipiosDesvinculados");
			Iterator iteratorColecaoMunicipiosDesvinculados = colecaoMunicipiosDesvinculados.iterator();
			while (iteratorColecaoMunicipiosDesvinculados.hasNext() ) {
			
				UnidadeOrganizacionalMunicipio unidade = (UnidadeOrganizacionalMunicipio)
					iteratorColecaoMunicipiosDesvinculados.next();
				unidade.setDataDesvinculacao(new Date());
				unidade.setUltimaAlteracao(new Date());
				fachada.atualizar(unidade);
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Unidade Organizacional "+unidadeNova.getDescricao()+" atualizada com sucesso.",
				"Realizar outra Manutenção de Unidade Organizacional", "exibirFiltrarUnidadeOrganizacionalAction.do?menu=sim");

		return retorno;
	}
	
	
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional(String id) {
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID, id));

		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		}
		
		return unidadeOrganizacional;
	}

	private Empresa pesquisarEmpresa(String id) {
		
		Empresa empresa = null;
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(
			new ParametroSimples(FiltroEmpresa.ID, id));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoEmpresa = 
			Fachada.getInstancia().pesquisar(filtroEmpresa,Empresa.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			empresa = 
				(Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

		}
		
		return empresa;
	}

	private GerenciaRegional pesquisarGerenciaRegional(String id) {
		
		GerenciaRegional gerenciaRegional = null;
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.adicionarParametro(
			new ParametroSimples(FiltroGerenciaRegional.ID, id));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoGerenciaRegional = 
			Fachada.getInstancia().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			gerenciaRegional = 
				(GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);

		}
		
		return gerenciaRegional;
	}

	private UnidadeTipo pesquisarUnidadeTipo(String id) {
		
		UnidadeTipo unidadeTipo = null;
		
		FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();

		filtroUnidadeTipo.adicionarParametro(
			new ParametroSimples(FiltroGerenciaRegional.ID, id));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidadeTipo = 
			Fachada.getInstancia().pesquisar(filtroUnidadeTipo,UnidadeTipo.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidadeTipo != null && !colecaoUnidadeTipo.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			unidadeTipo = 
				(UnidadeTipo) Util.retonarObjetoDeColecao(colecaoUnidadeTipo);

		}
		
		return unidadeTipo;
	}

	private MeioSolicitacao pesquisarMeioSolicitacao(String id) {
		
		MeioSolicitacao meioSolicitacao = null;
		
		FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();

		filtroMeioSolicitacao.adicionarParametro(
			new ParametroSimples(FiltroMeioSolicitacao.ID, id));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoMeioSolicitacao = 
			Fachada.getInstancia().pesquisar(filtroMeioSolicitacao,MeioSolicitacao.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			meioSolicitacao = 
				(MeioSolicitacao) Util.retonarObjetoDeColecao(colecaoMeioSolicitacao);

		}
		
		return meioSolicitacao;
	}	
}
