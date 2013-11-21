package gcom.gui.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
 * 
 * @author Mariana Victor
 * @date 14/02/2011
 */
public class ExibirGerarRelatorioAlteracoesCpfCnpjAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioAlteracoesCpfCnpj");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarRelatorioAlteracoesCpfCnpjActionForm form = (GerarRelatorioAlteracoesCpfCnpjActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		List colecaoUnidadeOrganizacional = new ArrayList();
		List colecaoUsuario = new ArrayList();

		if (sessao.getAttribute("colecaoUnidadeOrganizacional") != null
				&& !sessao.getAttribute("colecaoUnidadeOrganizacional").equals("")) {
			colecaoUnidadeOrganizacional = (List) sessao.getAttribute("colecaoUnidadeOrganizacional");
		}
		if (sessao.getAttribute("colecaoUsuario") != null
				&& !sessao.getAttribute("colecaoUsuario").equals("")) {
			colecaoUsuario = (List) sessao.getAttribute("colecaoUsuario");
		}
		
		// Carregar lista de Meios
		if (sessao.getAttribute("colecaoMeiosSolicitacao") == null
				|| sessao.getAttribute("colecaoMeiosSolicitacao").equals("")) {
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			filtroMeioSolicitacao.adicionarParametro(
					new ParametroSimples(FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			Collection<MeioSolicitacao> colecaoMeiosSolicitacao = 
				this.getFachada().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			if(!Util.isVazioOrNulo(colecaoMeiosSolicitacao)){
				sessao.setAttribute("colecaoMeiosSolicitacao",colecaoMeiosSolicitacao);
			}
		}
		
		// Carregar lista das Gerências Regionais
		if (sessao.getAttribute("colecaoGerenciaRegional") == null
				|| sessao.getAttribute("colecaoGerenciaRegional").equals("")) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
	
			Collection<GerenciaRegional> gerenciasRegionais = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());
	
			httpServletRequest.setAttribute("colecaoGerenciaRegional",
					gerenciasRegionais);
		}

		// Carregar lista das Unidades de Negócio
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null
				|| sessao.getAttribute("colecaoUnidadeNegocio").equals("")) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
	
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		}
		// Pesquisar Unidade Superior
		if(Util.verificarNaoVazio(form.getIdUnidadeSuperior())){
			pesquisarUnidadeSuperior(form,sessao);			
		}
		
		//Pesquisar Unidade Organizacional
		if(Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			pesquisarUnidadeOrganizacional(form,sessao);			
		}

		//Pesquisar Usuario
		if(Util.verificarNaoVazio(form.getIdUsuario())){
			pesquisarUsuario(form,sessao);			
		}
		
		//Pesquisar Localidade
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form,sessao);
		}

		// Adicionar Unidade Organizacional
		if (httpServletRequest.getParameter("adicionarUnidadeOrganizacional") != null
				&& httpServletRequest.getParameter("adicionarUnidadeOrganizacional").equals("OK")
				&& Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 
					new Integer(form.getIdUnidadeOrganizacional())));

			Collection colecaoUnidade = this.getFachada().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
			if (!Util.isVazioOrNulo(colecaoUnidade)) {
				
				UnidadeOrganizacional unidadeOrganizacional = 
					(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
								
				if(!contem(colecaoUnidadeOrganizacional, unidadeOrganizacional)){
					colecaoUnidadeOrganizacional.add(unidadeOrganizacional);
					sessao.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
					form.setIdUnidadeOrganizacional("");
					form.setDescUnidadeOrganizacional("");
				}
				
				
			}
		}

		// Adicionar Usuário
		if (httpServletRequest.getParameter("adicionarUsuario") != null
				&& httpServletRequest.getParameter("adicionarUsuario").equals("OK")
				&& Util.verificarNaoVazio(form.getIdUsuario())){
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, 
					new Integer(form.getIdUsuario())));

			Collection colecaoUsuarioPesquisa = this.getFachada().pesquisar(
					filtroUsuario, Usuario.class.getName());
		
			if (!Util.isVazioOrNulo(colecaoUsuarioPesquisa)) {
				
				Usuario usuario = 
					(Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioPesquisa);
				if(!colecaoUsuario.contains(usuario)){
					colecaoUsuario.add(usuario);
					sessao.setAttribute("colecaoUsuario", colecaoUsuario);
					form.setIdUsuario("");
					form.setDescUsuario("");
				}
				
			}
		}

		// Remover Unidade Organizacional
		if (httpServletRequest.getParameter("removerUnidadeOrganizacional") != null
				&& httpServletRequest.getParameter("removerUnidadeOrganizacional").equals("OK")
				&& httpServletRequest.getParameter("idRegistro") != null
				&& !httpServletRequest.getParameter("idRegistro").equals("")){
			Integer posicao = new Integer(httpServletRequest.getParameter("idRegistro"));
			
			if (posicao <= colecaoUnidadeOrganizacional.size()) {
				colecaoUnidadeOrganizacional.remove(posicao - 1);
				sessao.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
			}
			
		}
		
		//Remover Usuário
		if (httpServletRequest.getParameter("removerUsuario") != null
				&& httpServletRequest.getParameter("removerUsuario").equals("OK")
				&& httpServletRequest.getParameter("idRegistro") != null
				&& !httpServletRequest.getParameter("idRegistro").equals("")){
			Integer posicao = new Integer(httpServletRequest.getParameter("idRegistro"));
			
			if (posicao <= colecaoUsuario.size()) {
				colecaoUsuario.remove(posicao - 1);
				sessao.setAttribute("colecaoUsuario", colecaoUsuario);
			}
			
		}
		
		if(colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()){
			httpServletRequest.setAttribute("colecaoUnidadeVazia", "nao");
		}
		
		return retorno;
	}
	
	private void pesquisarUnidadeSuperior(GerarRelatorioAlteracoesCpfCnpjActionForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, 
				new Integer(form.getIdUnidadeSuperior())));
		
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(
				FiltroUnidadeOrganizacional.UNIDADE_SUPERIOR);

		Collection colecaoUnidadeSuperior = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUnidadeSuperior)) {
				form.setIdUnidadeSuperior("");
				form.setDescUnidadeSuperior("Unidade Superior Inexistente");
				sessao.removeAttribute("unidadeSuperiorEncontrada");
				return;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
		
		form.setIdUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getId().toString());
		form.setDescUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getDescricao());
		sessao.setAttribute("unidadeSuperiorEncontrada","");
	}
	
	private void pesquisarUnidadeOrganizacional(GerarRelatorioAlteracoesCpfCnpjActionForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, 
				new Integer(form.getIdUnidadeOrganizacional())));

		Collection colecaoUnidadeOrganizacional = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUnidadeOrganizacional)) {
				form.setIdUnidadeOrganizacional("");
				form.setDescUnidadeOrganizacional("Unidade Organizacional Inexistente");
				sessao.removeAttribute("unidadeOrganizacionalEncontrada");
				return;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
		form.setDescUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
		sessao.setAttribute("unidadeOrganizacionalEncontrada","");
	}
	
	private void pesquisarUsuario(GerarRelatorioAlteracoesCpfCnpjActionForm form, HttpSession sessao){

		FiltroUsuario filtroUsuario = new FiltroUsuario();

		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUsuario())));

		Collection colecaoUsuario = this.getFachada().pesquisar(
				filtroUsuario, Usuario.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUsuario)) {
				form.setIdUsuario("");
				form.setDescUsuario("Usuario Inexistente");
				sessao.removeAttribute("usuarioEncontrado");
				return;
		}
		
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
		
		form.setIdUsuario(usuario.getId().toString());
		form.setDescUsuario(usuario.getNomeUsuario());
		sessao.setAttribute("usuarioEncontrado","");
	}
	
	private void pesquisarLocalidade(GerarRelatorioAlteracoesCpfCnpjActionForm form, HttpSession sessao){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(form.getIdLocalidade())));

		Collection colecaoLocalidade = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setDescLocalidade("Localidade Inexistente");
				sessao.removeAttribute("localidadeEncontrada");
				return;
		}
		
		Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setDescLocalidade(localidade.getDescricao());
		sessao.setAttribute("localidadeEncontrada","");
	}

	private boolean contem(List colecaoUnidadeOrganizacional, UnidadeOrganizacional unidadeOrganizacional) {
		Iterator iterator = colecaoUnidadeOrganizacional.iterator();
		
		while (iterator.hasNext()) {
			UnidadeOrganizacional unidadeOrganizacionalIterator = (UnidadeOrganizacional) iterator.next();
			if (unidadeOrganizacional.getId().equals(unidadeOrganizacionalIterator.getId()))
				return true;
		}
			
		return false;
	}
	
}
