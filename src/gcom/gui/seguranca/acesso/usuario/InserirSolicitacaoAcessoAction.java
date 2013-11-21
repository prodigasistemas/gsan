/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
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
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
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
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 11/11/2010
 */

public class InserirSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Seta Objeto Solicitacao Acesso
		SolicitacaoAcesso solicitacaoAcesso = setSolicitacaoAcesso(form, sessao, usuario);
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		
		filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcesso.LOGIN, solicitacaoAcesso.getLogin()));
		
		Collection colecaoSolicitacaoAcesso = this.getFachada().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName());

		if(!Util.isVazioOrNulo(colecaoSolicitacaoAcesso)){
			
			throw new ActionServletException("atencao.acesso.solicitacao.existente", null, solicitacaoAcesso.getLogin());
		}
		
		Integer idSolicitacaoAcesso = (Integer) Fachada.getInstancia().inserir(solicitacaoAcesso);
		
		solicitacaoAcesso.setId(idSolicitacaoAcesso);
		
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
		montarPaginaSucesso(httpServletRequest, "Inserir Solicitação de Acesso para usuário: " 
				+  solicitacaoAcesso.getNomeUsuario()
				+ " inserida com sucesso. ",
				"Inserir outra Solicitação de Acesso",
				"exibirInserirSolicitacaoAcessoAction.do?menu=sim");

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
	private SolicitacaoAcesso setSolicitacaoAcesso(ExibirInserirSolicitacaoAcessoActionForm form, 
			HttpSession sessao, Usuario usuario) {		
		
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
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
		filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
				FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
		
		filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroUsuarioGrupo.GRUPO);
		
		Collection colecaoUsuarioGrupo = this.getFachada().pesquisar(filtroUsuarioGrupo, 
				UsuarioGrupo.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUsuarioGrupo)){
			
			UsuarioGrupo usuarioGrupo = null;
			Grupo grupo = null;
			Grupo grupoSuperintendencia = null;
			
			FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
			
			Iterator iterator = colecaoUsuarioGrupo.iterator();
			
			while (iterator.hasNext()) {
				
				usuarioGrupo = (UsuarioGrupo) iterator.next();
				
				grupo = usuarioGrupo.getGrupo();
				
				if(grupo.getIndicadorUso().compareTo(ConstantesSistema.SIM) == 0 
						&& grupo.getIndicadorSuperintendencia().compareTo(
								ConstantesSistema.INDICADOR_SUPERINTENDENCIA) == 0){
					
					grupoSuperintendencia = usuarioGrupo.getGrupo();
				}
			}
			
			if(grupoSuperintendencia != null){
				
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
				}
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
		
		if(solicitacaoAcesso.getCpf() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.CPF, solicitacaoAcesso.getCpf()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.cpf.usuario.ja_cadastrado", null, ""
								+ usuarioComCpf.getId());
			}
		}
		
		if(solicitacaoAcesso.getLogin() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, solicitacaoAcesso.getLogin()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.login.ja.existe", null, ""
								+ usuarioComCpf.getLogin());
			}
		}
		
		if(solicitacaoAcesso.getEmail() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.EMAIL, solicitacaoAcesso.getEmail()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.email.ja.existe", null, ""
								+ usuarioComCpf.getDescricaoEmail());
			}
		}
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.FUNCIONARIO_ID, solicitacaoAcesso.getFuncionario().getId()));
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUsuario.FUNCIONARIO);

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.funcionario.ja.existe", null, ""
								+ usuarioComCpf.getFuncionario().getNome());
			}
		}
		
		return solicitacaoAcesso;

	}

}
