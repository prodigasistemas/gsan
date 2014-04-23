package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 16/11/2010
 */
public class ExibirFiltrarSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC0984] Filtrar tipo de débito vigência
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Solicitação Acesso.
	 * 
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarSolicitacaoAcesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	

		FiltrarSolicitacaoAcessoActionForm form = (FiltrarSolicitacaoAcessoActionForm) actionForm;
		
		String objeto = "";
		
		if(sessao.getAttribute("objeto") == null){
			objeto = httpServletRequest.getParameter("objeto");
			
			if(objeto != null && (objeto.equals("autorizar") || objeto.equals("cadastrar")
					|| objeto.equals("atualizar") || objeto.equals("relatorio"))){
				
				sessao.setAttribute("objeto", objeto);
			}
		}else{
			
			objeto = (String) sessao.getAttribute("objeto");
		}

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			if(objeto.equals("atualizar")){
				form.setAtualizar("1");
			}
			
			if(usuario.getFuncionario() != null){
				
				if(objeto.equals("atualizar")){
					form.setIdFuncionarioSolicitante(""+usuario.getFuncionario().getId());
					form.setNomeFuncionarioSolicitante(usuario.getFuncionario().getNome());
				
				}else{
					if(!objeto.equals("cadastrar") && !objeto.equals("relatorio")){
						form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
						form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
					}
				}
			}else{
				
				throw new ActionServletException("atencao.acesso.solicitacao_funcionario");
			}
			
			//Pesquisar Empresa
			if(sessao.getAttribute("colecaoEmpresa") == null){
				this.pesquisarEmpresa(sessao, form);
			}
			
			// Pesquisar Situação
			if(sessao.getAttribute("colecaoSituacao") == null){
				this.pesquisarSolicitacaoAcessoSituacao(sessao, form);
			}
		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("2")
						|| objetoConsulta.trim().equals("3"))) {

			// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))
					|| (form.getIdFuncionarioSolicitante() != null && !form.getIdFuncionarioSolicitante().trim().equals(""))) {
				
				this.pesquisarFuncionario( httpServletRequest, form, objetoConsulta);
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lotação
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){
			
			this.pesquisarLotacao(httpServletRequest, form);
		}

		return retorno;
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if(colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}else{
			
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("3")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}else if(objetoConsulta.trim().equals("1")){
			
			local = form.getIdFuncionarioSolicitante();
		}

		// Pesquisa a usuário na base
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSolicitante("" + funcionario.getId());
				form.setNomeFuncionarioSolicitante( funcionario.getNome());
			}

		} else {
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(null);
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
	
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
	
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente2","true");
	
			}
		}
	}
	
	/**
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a usuário na base
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdFuncionario("");
			form.setNomeFuncionario("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
	/**
	 * Pesquisar Situação
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarSolicitacaoAcessoSituacao(HttpSession sessao,	FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		
		filtroSolicitacaoAcessoSituacao.setConsultaSemLimites(true);
		filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("atualizar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			}else if(objeto.equals("autorizar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			}else if(objeto.equals("cadastrar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			}
		}
		
		filtroSolicitacaoAcessoSituacao.setCampoOrderBy(FiltroSolicitacaoAcessoSituacao.DESCRICAO);
		
		Collection colecaoSituacao = this.getFachada().pesquisar(
				filtroSolicitacaoAcessoSituacao, SolicitacaoAcessoSituacao.class.getName());
		
		if(colecaoSituacao == null || colecaoSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Solicitação Acesso Situação");
		}else{
			
			sessao.setAttribute("colecaoSituacao", colecaoSituacao);
		}
	}

}
