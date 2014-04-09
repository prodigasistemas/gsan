package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Manter Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 18/11/2010
 */

public class AtualizarSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		AtualizarSolicitacaoAcessoActionForm form = 
			(AtualizarSolicitacaoAcessoActionForm) actionForm;
		
		// Seta Objeto Solicitacao Acesso
		SolicitacaoAcesso solicitacaoAcesso = setSolicitacaoAcesso(form, sessao);
		
		SolicitacaoAcesso solicitacaoAcessoBase = null;
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcesso.LOGIN, solicitacaoAcesso.getLogin()));
		
		Collection colecaoSolicitacaoAcesso = this.getFachada().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName());
	
		if(!Util.isVazioOrNulo(colecaoSolicitacaoAcesso)){
			
			solicitacaoAcessoBase = (SolicitacaoAcesso) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcesso);
			solicitacaoAcesso.setId( solicitacaoAcessoBase.getId());
		}
		
		// atualizar Solicitação de Acesso.
		this.getFachada().atualizar(solicitacaoAcesso);
		
		// remover todos os grupos associado a Solicitação de Acesso.
		this.getFachada().removerGrupoDeSolicitacaoAcesso(solicitacaoAcesso.getId());
		
		if (form.getIdsGrupo() != null && !form.getIdsGrupo().equals("-1") ){
			
			List lista = Arrays.asList(form.getIdsGrupo());  
			Collection<Grupo> colecaoGrupo = new ArrayList();
			
			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimplesIn( 
					FiltroGrupo.ID, lista));	
			
			colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
			
			Grupo grupo = null;
			SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
			SolicitacaoAcessoGrupoPK solicitacaoAcessoGrupoPK = null;
			
			if(colecaoGrupo != null && !colecaoGrupo.isEmpty() ){
				
				Iterator iterator = colecaoGrupo.iterator();
			
				while (iterator.hasNext()) {
					
					grupo = (Grupo) iterator.next();
					
					solicitacaoAcessoGrupoPK = new SolicitacaoAcessoGrupoPK(
							solicitacaoAcesso, grupo);
					
					solicitacaoAcessoGrupo = new SolicitacaoAcessoGrupo(
							solicitacaoAcessoGrupoPK, new Date());
					
					Fachada.getInstancia().inserir(solicitacaoAcessoGrupo);
				}
			}
		}
		
		// Enviar Email
		EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(
				EnvioEmail.INSERIR_SOLICITACAO_ACESSO);

		String emailRemetente = envioEmail.getEmailRemetente();

		String tituloMensagem = "Solicitação de Acesso.";

		String emailReceptor = form.getEmail();

		String mensagem = "";
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			mensagem += " O Funcionário, Matrícula: "+ solicitacaoAcesso.getFuncionario().getId()
				+ " necessita de sua liberação de acesso.";
		}else{
		
			mensagem += " O Prestador de serviços, CPF: "+ solicitacaoAcesso.getCpf()
				+ " necessita de sua liberação de acesso.";
		}
		
		if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
			
			try {
				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
						tituloMensagem, mensagem);
				
			} catch (ErroEmailException erroEnviarEmail) {
				erroEnviarEmail.printStackTrace();
			}
		}

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Solicitação de Acesso para usuário: " 
				+  solicitacaoAcesso.getNomeUsuario()
				+ " Atualizado com sucesso. ",
				"Atualizar outra Solicitação de Acesso",
				"exibirFiltrarSolicitacaoAcessoAction.do?objeto=atualizar&menu=sim");

		return retorno;
	
	}
	
	/**
	 * Preenche objeto com informações vindas da tela
	 * 
	 * @author Hugo Leonardo
	 * @date 11/11/2010
	 * 
	 * @param form
	 * @return SolicitacaoAcesso
	 */
	private SolicitacaoAcesso setSolicitacaoAcesso(AtualizarSolicitacaoAcessoActionForm form, 
			HttpSession sessao) {		
		
		SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
		
		// funcionário solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSolicitante()));
			solicitacaoAcesso.setFuncionarioSolicitante(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Funcionário Solicitante");
		}
		
		// funcionário responsável
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSuperior()));
			solicitacaoAcesso.setFuncionarioResponsavel(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Responsável pela Autorização");
		}
		
		// Indicador Notificar por E-mail
		if(Util.verificarNaoVazio(form.getIcNotificar())){
			
			if(form.getIcNotificar().equals("0")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("1"));
			}else if(form.getIcNotificar().equals("1")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("2"));
			}
		}
		
		// tipo Usuário
		if(Util.verificarNaoVazio(form.getIdTipoUsuario())){
			UsuarioTipo usuarioTipo = new UsuarioTipo();
			usuarioTipo.setId(new Integer(form.getIdTipoUsuario()));
			solicitacaoAcesso.setUsuarioTipo(usuarioTipo);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Tipo de Usuário");
		}
		
		// funcionário usuário
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionario()));
			solicitacaoAcesso.setFuncionario(funcionario);
		}

		// Empresa, Nome Usuário
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
		
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(
					FiltroFuncionario.UNIDADE_EMPRESA);
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));

			Collection<Funcionario> funcionarioPesquisado = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getName());
			
			if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
				
				solicitacaoAcesso.setEmpresa(funcionario.getEmpresa());
				
				// CPF
				if(funcionario.getNumeroCpf() != null){
					
					if(Util.validacaoCPF(funcionario.getNumeroCpf())){
						solicitacaoAcesso.setCpf(funcionario.getNumeroCpf());
					}else{
						throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
					}
				}
				
				// Data Nascimento
				if(funcionario.getDataNascimento() != null){
					solicitacaoAcesso.setDataNascimento(funcionario.getDataNascimento());
				}

				// Nome Usuário
				int tamanhoMaximoCampo = 50;
				if (tamanhoMaximoCampo < funcionario.getNome().length()) {
					// trunca a String
					String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
					solicitacaoAcesso.setNomeUsuario(strTruncado);
				}else{
					solicitacaoAcesso.setNomeUsuario(funcionario.getNome());
				}
			}
		}else if(Util.verificarNaoVazio(form.getIdEmpresa())){
			Empresa empresa = new Empresa();
			empresa.setId(new Integer(form.getIdEmpresa()));
			solicitacaoAcesso.setEmpresa(empresa);
		}
		
		// nome Usuário
		if(!Util.verificarNaoVazio(form.getIdFuncionario()) 
				&& Util.verificarNaoVazio(form.getNomeUsuario())){
			
			solicitacaoAcesso.setNomeUsuario(form.getNomeUsuario());
		}
		
		// CPF
		if(solicitacaoAcesso.getCpf() == null 
				&& Util.verificarNaoVazio(form.getCpf())){
			
			if(Util.validacaoCPF(form.getCpf())){
				solicitacaoAcesso.setCpf(form.getCpf());
			}else{
				throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
			}
			
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())){
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Número do CPF");
		}
		
		// data Nascimento
		if(solicitacaoAcesso.getDataNascimento() == null 
				&& Util.verificarNaoVazio(form.getDataNascimento())
				&& !Util.validarDiaMesAno(form.getDataNascimento())){
			
			solicitacaoAcesso.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())) {
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data de Nascimento");
		}
		
		// Unidade de Lotação
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(new Integer(form.getIdLotacao()));
			solicitacaoAcesso.setUnidadeOrganizacional(unidadeOrganizacional);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Unidade de Lotação");
		}
		
		// Login
		if(Util.verificarNaoVazio(form.getLogin())){
			
			solicitacaoAcesso.setLogin(form.getLogin());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Login");
		}
		
		// Email
		if(Util.verificarNaoVazio(form.getEmail())){
			
			solicitacaoAcesso.setEmail(form.getEmail());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "E-Mail");
		}
		
		//Período Inicial e Período Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				
				solicitacaoAcesso.setPeriodoInicial(Util.converteStringParaDate(form.getDataInicial()));
				
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					solicitacaoAcesso.setPeriodoFinal(Util.converteStringParaDate(form.getDataFinal()));
					if(Util.compararData(solicitacaoAcesso.getPeriodoInicial(),solicitacaoAcesso.getPeriodoFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
		}else{
			solicitacaoAcesso.setPeriodoInicial(null);
			solicitacaoAcesso.setPeriodoFinal(null);
			
		}
		
		// Solicitação Acesso Situação
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.adicionarParametro( new ParametroSimples(
				FiltroGrupo.INDICADOR_SUPERINTENDENCIA, 
					ConstantesSistema.INDICADOR_SUPERINTENDENCIA));
		filtroGrupo.adicionarParametro( new ParametroSimples(
				FiltroGrupo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, 
				Grupo.class.getName());
	
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		
		if(!Util.isVazioOrNulo(colecaoGrupo)){
			
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
							SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
					SolicitacaoAcessoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
				
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
					(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				
				solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
				solicitacaoAcesso.setDataAutorizacao(new Date());
			}else{
				
				throw new ActionServletException("atencao.solicitacao_acesso_situacao.ag_cadastramento");
			}
		}else{
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
							SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
					SolicitacaoAcessoSituacao.class.getName());
			if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
				
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
					(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				
				solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
			}else{
				
				throw new ActionServletException("atencao.solicitacao_acesso_situacao.ag_autorizacao");
			}
		}
		
		// Grupo
		if (form.getIdsGrupo() == null || form.getIdsGrupo().equals("-1") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Grupo");
		}
		
		// Data Solicitação
		solicitacaoAcesso.setDataSolicitacao(new Date());
		
		// Ultima Alteração
		solicitacaoAcesso.setUltimaAlteracao(new Date());

		return solicitacaoAcesso;

	}

}
