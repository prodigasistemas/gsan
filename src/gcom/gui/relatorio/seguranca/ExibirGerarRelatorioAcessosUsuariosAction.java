package gcom.gui.relatorio.seguranca;

import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.Modulo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 12/07/2010
 */

public class ExibirGerarRelatorioAcessosUsuariosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioAcessosUsuariosAction");
		
		GerarRelatorioAcessosUsuariosActionForm form = 
			(GerarRelatorioAcessosUsuariosActionForm) actionForm;

		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			//form.setSituacaoUsuario("0");
		}
		
		this.pesquisarUnidadeOrganizacional(httpServletRequest,form);
		this.pesquisarGrupoAcesso(httpServletRequest,form);
		this.pesquisarUsuarioSituacao(httpServletRequest,form);
		
		if(form.getIdFuncionalidade() != null && !form.getIdFuncionalidade().equals("")){
			this.pesquisarOperacoes(httpServletRequest,form);
		}
		
		this.pesquisarUsuarioTipo(httpServletRequest, form);
		this.pesquisarPermissaoEspecial(httpServletRequest, form);
		this.pesquisarModulo(httpServletRequest, form);
		
		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da funcionalidade
		if (form.getIdFuncionalidade() != null && !form.getIdFuncionalidade().trim().equals("")) {
			
			this.pesquisarFuncionalidade( httpServletRequest, form);
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta do Usuario
		if (form.getIdUsuario() != null && !form.getIdUsuario().trim().equals("")) {
			
			this.pesquisarUsuario( httpServletRequest, form);
		}
		
		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		// Funcionalidade
		if(form.getIdFuncionalidade() != null && 
			!form.getIdFuncionalidade().equals("") && 
			form.getNomeFuncionalidade() != null && 
			!form.getNomeFuncionalidade().equals("")){
					
			httpServletRequest.setAttribute("funcionalidadeEncontrada","true");
		}
		
		// Usuario
		if(form.getIdUsuario() != null && 
			!form.getIdUsuario().equals("") && 
			form.getNomeUsuario() != null && 
			!form.getNomeUsuario().equals("")){
					
			httpServletRequest.setAttribute("usuarioEncontrado","true");
		}
		
	}
	
	private void pesquisarUnidadeOrganizacional(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeOrganizacional.setConsultaSemLimites(true);
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeOrganizacional = 
			this.getFachada().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrganizacional == null || colecaoUnidadeOrganizacional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Organizacional");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
		}
	}
	
	private void pesquisarGrupoAcesso(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroGrupo.adicionarParametro(
				new ParametroSimples(FiltroGrupo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoGrupo = 
			this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());

		if (colecaoGrupo == null || colecaoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Grupo de Usuario");
		} else {
			httpServletRequest.setAttribute("colecaoGrupoAcesso", colecaoGrupo);
		}
	}
	
	private void pesquisarModulo(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroModulo filtroModulo = new FiltroModulo();
		// Verifica se os dados foram informados da tabela existem e joga numa colecao
		Collection<Modulo> colecaoModulo = this.getFachada().pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}else{
			httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);
		}	
	}

	private void pesquisarFuncionalidade(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a funcionalidade na base
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.ID, form.getIdFuncionalidade()));

		Collection<Funcionalidade> funcionalidadePesquisada = fachada.pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		// Se nenhuma funcionalidade for encontrada a mensagem é enviada para a página
		if (funcionalidadePesquisada != null && !funcionalidadePesquisada.isEmpty()) {
			Funcionalidade funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(funcionalidadePesquisada);
			form.setIdFuncionalidade("" + funcionalidade.getId());
			form.setNomeFuncionalidade( funcionalidade.getDescricao());

		} else {
			form.setIdFuncionalidade("");
			form.setNomeFuncionalidade("FUNCIONALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("funcionalidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idFuncionalidade");
		}
	}
	
	private void pesquisarOperacoes(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		
		filtroOperacao.setConsultaSemLimites(true);
		filtroOperacao.adicionarParametro( new ParametroSimples(
				FiltroOperacao.FUNCIONALIDADE, form.getIdFuncionalidade()));
		filtroOperacao.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection colecaoOperacao = 
			this.getFachada().pesquisar(filtroOperacao, Operacao.class.getName());

		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Operacao");
		} else {
			httpServletRequest.setAttribute("colecaoOperacao", colecaoOperacao);
		}
	}
	
	private void pesquisarUsuarioTipo(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {
	
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		
		filtroUsuarioTipo.setConsultaSemLimites(true);
		filtroUsuarioTipo.adicionarParametro(new ParametroSimples( FiltroUsuarioTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoUsuarioTipo = 
			this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
		if(colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usuário Tipo");
		}else{
			httpServletRequest.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
		}
	}
	
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a usuário na base
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.ID, form.getIdUsuario()));

		Collection<Usuario> usuarioPesquisado = fachada.pesquisar(
				filtroUsuario, Usuario.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (usuarioPesquisado != null && !usuarioPesquisado.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarioPesquisado);
			form.setIdUsuario("" + usuario.getId());
			form.setNomeUsuario( usuario.getNomeUsuario());

		} else {
			form.setIdUsuario("");
			form.setNomeUsuario("USUARIO INEXISTENTE");
			httpServletRequest.setAttribute("usuarioInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idUsuario");
		}
	}
	
	private void pesquisarPermissaoEspecial(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
		
		filtroPermissaoEspecial.setConsultaSemLimites(true);
		filtroPermissaoEspecial.setCampoOrderBy(FiltroPermissaoEspecial.DESCRICAO);

		filtroPermissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPermissaoEspecial.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoPermissaoEspecial = 
			this.getFachada().pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName());

		if (colecaoPermissaoEspecial == null || colecaoPermissaoEspecial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Permissao Especial");
		} else {
			httpServletRequest.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
		}
	}

	private void pesquisarUsuarioSituacao(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {
	
		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
		
		filtroUsuarioSituacao.setConsultaSemLimites(true);
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples( FiltroUsuarioSituacao.INDICADOR_DE_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoUsuarioSituacao = 
			this.getFachada().pesquisar(filtroUsuarioSituacao, UsuarioSituacao.class.getName());
		
		if(colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usuário Situação");
		}else{
			httpServletRequest.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);
		}
	}
}
