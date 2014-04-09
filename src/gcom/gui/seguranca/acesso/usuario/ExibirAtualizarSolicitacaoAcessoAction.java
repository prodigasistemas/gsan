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
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 16/11/2010
 */
public class ExibirAtualizarSolicitacaoAcessoAction extends GcomAction {
	/**
	 * [UC1093] Manter Solicitação de Acesso.
	 * 
	 * Este caso de uso permite cadastrar um novo Usuário.
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSolicitacaoAcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarSolicitacaoAcessoActionForm form = (AtualizarSolicitacaoAcessoActionForm) actionForm;
		
		SolicitacaoAcesso solicitacaoAcesso = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idSolicitacaoAcesso = "";
		
		if ( sessao.getAttribute("solicitacaoAcesso") != null ) {
			solicitacaoAcesso = (SolicitacaoAcesso) sessao.getAttribute("solicitacaoAcesso");
		
		} else if(sessao.getAttribute("objetoSolicitacaoAcesso") != null){
			solicitacaoAcesso = (SolicitacaoAcesso) sessao.getAttribute("objetoSolicitacaoAcesso");
			
		}else {
		
			idSolicitacaoAcesso = (String) httpServletRequest.getParameter("idRegistroAtualizar");
			
			if( idSolicitacaoAcesso != null ) {
				
				FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
				
				filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
						FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
				
				filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
						FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);
				
				filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
						FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);
				
				filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
						FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);
				
				filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
						FiltroSolicitacaoAcesso.EMPRESA);
				
				filtroSolicitacaoAcesso.adicionarParametro(
							new ParametroSimples(FiltroSolicitacaoAcesso.ID, new Integer(idSolicitacaoAcesso)));
				
				solicitacaoAcesso = (SolicitacaoAcesso) fachada.pesquisar(filtroSolicitacaoAcesso, 
						SolicitacaoAcesso.class.getName()).iterator().next();
			}

		}
		
		if(httpServletRequest.getParameter("filtrar") != null 
				&& httpServletRequest.getParameter("filtrar").equals("sim")){
			
			//Pesquisar Tipo Usuario
			this.pesquisarTipoUsuario(sessao, form);

			//Pesquisar Empresa
			this.pesquisarEmpresa(sessao, form);
			
			// Pesquisar Grupo
			this.pesquisarGrupo(sessao, form);
			
			// pesquisar Funcionário
			if(solicitacaoAcesso.getFuncionario() != null){
				
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, solicitacaoAcesso.getFuncionario().getId()));

				Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
						filtroFuncionario, Funcionario.class.getName());

				if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
					Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
					
					form.setIdFuncionario(funcionario.getId().toString());
					form.setNomeFuncionario(funcionario.getNome());
				}
			}
			
			// Funcionário Solicitante
			if(solicitacaoAcesso.getFuncionarioSolicitante() != null){
				
				form.setIdFuncionarioSolicitante(solicitacaoAcesso.getFuncionarioSolicitante().getId().toString());
				form.setNomeFuncionarioSolicitante(solicitacaoAcesso.getFuncionarioSolicitante().getNome());
			}
			
			// Funcionário Responsável
			if(solicitacaoAcesso.getFuncionarioResponsavel() != null){
				
				form.setIdFuncionarioSuperior(solicitacaoAcesso.getFuncionarioResponsavel().getId().toString());
				form.setNomeFuncionarioSuperior(solicitacaoAcesso.getFuncionarioResponsavel().getNome());
			}
			
			// Unidade de Lotação
			if(solicitacaoAcesso.getUnidadeOrganizacional() != null){
				
				form.setIdLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getId().toString());
				form.setNomeLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getDescricao());
			}
			
			// Empresa
			form.setIdEmpresa(solicitacaoAcesso.getEmpresa().getId().toString());
			
			// Tipo de Usuário
			form.setIdTipoUsuario(solicitacaoAcesso.getUsuarioTipo().getId().toString());
			
			// Nome Usuário
			form.setNomeUsuario(solicitacaoAcesso.getNomeUsuario());
			
			// Login
			form.setLogin(solicitacaoAcesso.getLogin());
			
			// Email
			form.setEmail(solicitacaoAcesso.getEmail());
			
			// Data Nascimento
			if(solicitacaoAcesso.getDataNascimento() != null){
				form.setDataNascimento( Util.formatarData(solicitacaoAcesso.getDataNascimento()));
			}
			
			// CPF
			if(Util.verificarNaoVazio(solicitacaoAcesso.getCpf())){
				form.setCpf(solicitacaoAcesso.getCpf());
			}
			
			// Período de Cadastramento
			if(solicitacaoAcesso.getPeriodoInicial() != null
					&& solicitacaoAcesso.getPeriodoFinal() != null){
				
				form.setDataInicial( Util.formatarData(solicitacaoAcesso.getPeriodoInicial()));
				form.setDataFinal( Util.formatarData(solicitacaoAcesso.getPeriodoFinal()));
			}
			
			// Notificar Responsável
			if(solicitacaoAcesso.getIndicadorNotificarResponsavel() != null){
				if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
					form.setIcNotificar("0");
				}else{
					form.setIcNotificar("1");
				}
			}
			
			// Grupos
			FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo = new FiltroSolicitacaoAcessoGrupo();
			
			filtroSolicitacaoAcessoGrupo.setConsultaSemLimites(true);
			filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroSolicitacaoAcessoGrupo.GRUPO);
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples( 
					FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_ID, solicitacaoAcesso.getId()) );
				
			Collection colecaoSolicitacaoAcessoGrupo = 
				Fachada.getInstancia().pesquisar(
						filtroSolicitacaoAcessoGrupo, SolicitacaoAcessoGrupo.class.getName());

			Collection<Integer> colecaoIdsGrupos = new ArrayList();
			
			if (colecaoSolicitacaoAcessoGrupo != null && !colecaoSolicitacaoAcessoGrupo.isEmpty()) {
				
				Iterator iterator = colecaoSolicitacaoAcessoGrupo.iterator();
				
				SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
				
				while (iterator.hasNext()) {
				
					solicitacaoAcessoGrupo = (SolicitacaoAcessoGrupo) iterator.next();
					
					colecaoIdsGrupos.add(solicitacaoAcessoGrupo.getComp_id().getGrupo().getId());
				}
			}
			
			if(!colecaoIdsGrupos.isEmpty()){
				
				Object[] array = colecaoIdsGrupos.toArray();
				String[] array2 = new String[array.length];
				
				for (int i = 0; i < array.length; i++){
					array2[i] = array[i].toString();
		        }

				form.setIdsGrupo(array2);
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
		
		Date timeStamp = solicitacaoAcesso.getUltimaAlteracao();

		sessao.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);
		sessao.setAttribute("solicitacaoAcesso", solicitacaoAcesso);
		sessao.setAttribute("timeStamp", timeStamp);
		
		httpServletRequest.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);		
		
		return retorno;
		
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, AtualizarSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Usuario Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarTipoUsuario(HttpSession sessao, AtualizarSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Grupo
	 *
	 * @author Hugo Leonardo
	 * @date 17/11/2010
	 */
	private void pesquisarGrupo(HttpSession sessao,	AtualizarSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			AtualizarSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
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
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			AtualizarSolicitacaoAcessoActionForm form) {

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
			form.setIdFuncionario("");
			form.setNomeFuncionario("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}

}
