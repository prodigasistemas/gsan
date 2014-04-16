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
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
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
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */
public class ExibirInserirSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoAcessoAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			if(usuario.getFuncionario() != null){
				form.setIdFuncionarioSolicitante(""+usuario.getFuncionario().getId());
				form.setNomeFuncionarioSolicitante(usuario.getFuncionario().getNome());
			}else{
				
				throw new ActionServletException("atencao.acesso.solicitacao_funcionario");
			}
			
			// Verifica se o Usuário pertence ao grupo "Superintendente"
			boolean superintendente = false;
			
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
			
			filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUsuarioGrupo.GRUPO);
			
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			
			Collection colecaoUsuarioGrupo = this.getFachada().pesquisar(filtroUsuarioGrupo, 
					UsuarioGrupo.class.getName());
			
			if(colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()){
				
				Iterator colecaoIterator = colecaoUsuarioGrupo.iterator();

				while (colecaoIterator.hasNext()) {
					
					UsuarioGrupo usuarioGrupo = (UsuarioGrupo) colecaoIterator.next();
				
					if(usuarioGrupo.getGrupo().getIndicadorSuperintendencia()
							.compareTo(ConstantesSistema.INDICADOR_SUPERINTENDENCIA) == 0){
						
						superintendente = true;
						break;
					}
				}
			}
			
			// Caso Usuário pertença ao grupo "Superintendente"
			if(superintendente){

				form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
				form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
				form.setIcNotificar("1");
			}else{
				form.setIcNotificar("0");
			}
			
			// Pesquisar Grupo
			this.pesquisarGrupo(sessao, form);
			
			//Pesquisar Tipo Usuario
			this.pesquisarTipoUsuario(sessao, form);

			//Pesquisar Empresa
			this.pesquisarEmpresa(sessao, form);
			
		}
		
		if(httpServletRequest.getParameter("usuarioTipo") != null && 
	        	!httpServletRequest.getParameter("usuarioTipo").equals("") ){
			
			String idUsuarioTipo = httpServletRequest.getParameter("usuarioTipo").toString();
			
			// Se não for "Prestador de Serviços"
			if(!idUsuarioTipo.equals("8") && Util.verificarNaoVazio(form.getIdFuncionario())){
				
				// Pesquisa a fincionário
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));

				Collection<Funcionario> funcionarioPesquisado = this.getFachada().pesquisar(
						filtroFuncionario, Funcionario.class.getName());

				// Se nenhum usuário for encontrado a mensagem é enviada para a página
				if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
					
					Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
					
					if(funcionario.getNumeroCpf() != null){
						form.setCpf(""+funcionario.getNumeroCpf());
					}
					if(funcionario.getDataNascimento() != null){
						
						form.setDataNascimento(""+Util.formatarData(funcionario.getDataNascimento()));
					}
					
					int tamanhoMaximoCampo = 50;
					
					if (tamanhoMaximoCampo < funcionario.getNome().length()) {
						// trunca a String
						String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
						form.setNomeUsuario(strTruncado);
					}else{
						
						form.setNomeUsuario(funcionario.getNome());
					}
				}
			}else{
				
				form.setCpf("");
				form.setDataNascimento("");
				form.setNomeUsuario("");
			}
		}

		httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
		
		// IDADE MÍNIMA
        httpServletRequest.setAttribute("idadeMinimaUsuario", "18");
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2"))) {

			// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))) {
				
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
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
				
				if(funcionario.getNumeroCpf() != null){
					form.setCpf(funcionario.getNumeroCpf());
				}
				
				if(funcionario.getDataNascimento() != null){
					form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				}
				
				if(funcionario.getUnidadeOrganizacional() != null){
					form.setIdLotacao("" + funcionario.getUnidadeOrganizacional().getId());
					form.setNomeLotacao(funcionario.getUnidadeOrganizacional().getDescricao());
				}
			}

		} else {
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(null);
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
	
			}
			
			if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
	
			}
		}
	}
	
	/**
	 * Pesquisar Grupo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarGrupo(HttpSession sessao,	ExibirInserirSolicitacaoAcessoActionForm form) {
	
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.adicionarParametro(new ParametroSimples( 
				FiltroGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
		
		Collection colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
		
		if(colecaoGrupo == null || colecaoGrupo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Grupos");
		}else{
			
			sessao.setAttribute("colecaoGrupo", colecaoGrupo);
		}
	}
	
	/**
	 * Pesquisar Usuario Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarTipoUsuario(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		
		filtroUsuarioTipo.setConsultaSemLimites(true);
		filtroUsuarioTipo.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUsuarioTipo.setCampoOrderBy(FiltroUsuarioTipo.DESCRICAO);
		
		Collection colecaoUsuarioTipo = this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
		if(colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usuário Tipo");
		}else{
			
			sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
		}
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdLotacao("");
			form.setNomeLotacao("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
}
