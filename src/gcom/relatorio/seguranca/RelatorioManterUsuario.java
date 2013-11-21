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
package gcom.relatorio.seguranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de usuário
 * 
 * @author Arthur Carvalho
 * @created 09/04/2008
 */
public class RelatorioManterUsuario extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterUsuario(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_USUARIO_MANTER);
	}

	@Deprecated
	public RelatorioManterUsuario() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroUsuarioGrupo filtroUsuarioGrupo = (FiltroUsuarioGrupo) getParametro("filtroUsuarioGrupo");
		UsuarioTipo usuarioTipo = (UsuarioTipo) getParametro("usuarioTipo");
		Empresa empresa = (Empresa) getParametro("empresa");
		Funcionario funcionario = (Funcionario) getParametro("funcionario");
		String nomeUsuario = (String) getParametro("nomeUsuario");
		UnidadeOrganizacional unidadeLotacao = (UnidadeOrganizacional) getParametro("unidadeLotacao");
		UsuarioSituacao usuarioSituacao = (UsuarioSituacao) getParametro("usuarioSituacao");
		String loginPesquisa = (String) getParametro("login");
		UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) getParametro("usuarioAbrangencia");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		Localidade elo = (Localidade) getParametro("elo");
		Localidade localidade = (Localidade) getParametro("localidade");
		Collection<Grupo> colecaoGruposParametro = (Collection<Grupo>) getParametro("colecaoGruposParametro");
		Date dataCadastramentoInicial = (Date) getParametro("dataCadastramentoInicial");
		Date dataCadastramentoFinal = (Date) getParametro("dataCadastramentoFinal");
		Date dataExpiracaoInicial = (Date) getParametro("dataExpiracaoInicial");
		Date dataExpiracaoFinal = (Date) getParametro("dataExpiracaoFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterUsuarioBean relatorioBean = null;

		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.unidadeOrganizacional");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioSituacao");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.empresa");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.gerenciaRegional");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.localidadeElo");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.localidade");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioTipo");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioAbrangencia");
		filtroUsuarioGrupo
				.adicionarCaminhoParaCarregamentoEntidade("usuario.funcionario");
		filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade("grupo");
		filtroUsuarioGrupo.setConsultaSemLimites(true);

		Collection<UsuarioGrupo> colecaoUsuariosGrupos = fachada.pesquisar(
				filtroUsuarioGrupo, UsuarioGrupo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoUsuariosGrupos != null && !colecaoUsuariosGrupos.isEmpty()) {

			for (UsuarioGrupo usuarioGrupo : colecaoUsuariosGrupos) {
				
				Usuario usuario = usuarioGrupo.getUsuario();
				Grupo grupo = usuarioGrupo.getGrupo();

				// Nome do Usuário
				String nome = "";
				
				if (usuario.getNomeUsuario() != null && !usuario.getNomeUsuario().equals("")) {
					nome = usuario.getNomeUsuario();
				}
				
				// Tipo do Usuário
				String tipo = "";
				
				if (usuario.getUsuarioTipo() != null) {
					tipo = usuario.getUsuarioTipo().getDescricao();
				}
				
				// Unidade Organizacional
				String unidadeOrganizacional = "";
				
				if (usuario.getUnidadeOrganizacional() != null) {
					unidadeOrganizacional = usuario.getUnidadeOrganizacional().getSigla();
				}
				
				// Situação do Usuário
				String situacao = "";
				
				if (usuario.getUsuarioSituacao() != null) {
					situacao = usuario.getUsuarioSituacao().getDescricaoAbreviada();
				}
				
				// Abrangência do Acesso
				String abrangencia = "";
				
				if (usuario.getUsuarioAbrangencia() != null) {
					abrangencia = usuario.getUsuarioAbrangencia().getDescricaoAbreviada();
				}
				
				// Login
				String login = "";
				
				if (usuario.getLogin() != null) {
					login = usuario.getLogin();
				}
								
				// Data Final de Cadastramento
				String dataCadastroFim = "";
				
				if (usuario.getDataCadastroFim() != null) {
					dataCadastroFim = Util.formatarData(usuario.getDataCadastroFim());
				}
				
				relatorioBean = new RelatorioManterUsuarioBean(
						// Nome do Usuário
						nome,
						
						// Tipo do Usuário
						tipo,
						
						// Unidade Organizacional 
						unidadeOrganizacional,
						
						// Situação do Usuário
						situacao,
						
						// Abrangência
						abrangencia,
						
						// Login
						login,
						
						// Data Final de Cadastramento
						dataCadastroFim,
						
						// Grupo
						grupo.getDescricaoAbreviada());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (usuarioTipo != null) {
			parametros.put("tipoUsuario", usuarioTipo.getDescricao());
		} else {
			parametros.put("tipoUsuario", "");
		}
		
		if (empresa != null) {
			parametros.put("empresa", empresa.getDescricao());
		} else {
			parametros.put("empresa", "");
		}
		
		if (loginPesquisa != null) {
			parametros.put("login", loginPesquisa);
		} else {
			parametros.put("login", "");
		}
		
		if (funcionario != null) {
			parametros.put("funcionario", funcionario.getId().toString());
		} else {
			parametros.put("funcionario", "");
		}

		if (nomeUsuario != null) {
			parametros.put("nomeUsuario", nomeUsuario);
		} else {
			parametros.put("nomeUsuario", "");
		}
		
		if (unidadeLotacao != null) {
			parametros.put("unidadeLotacao", unidadeLotacao.getDescricao());
		} else {
			parametros.put("unidadeLotacao", "");
		}
		
		if (usuarioSituacao != null) {
			parametros.put("situacaoUsuario", usuarioSituacao.getDescricaoUsuarioSituacao());
		} else {
			parametros.put("situacaoUsuario", "");
		}
		
		if (usuarioAbrangencia != null) {
			parametros.put("abrangenciaAcesso", usuarioAbrangencia.getDescricao());
		} else {
			parametros.put("abrangenciaAcesso", "");
		}
		
		if (gerenciaRegional != null) {
			parametros.put("gerenciaRegional", gerenciaRegional.getNomeAbreviado());
		} else {
			parametros.put("gerenciaRegional", "");
		}
		
		if (unidadeNegocio != null) {
			parametros.put("unidadeNegocio", unidadeNegocio.getNomeAbreviado());
		} else {
			parametros.put("unidadeNegocio", "");
		}
		
		if (elo != null) {
			parametros.put("eloPolo", elo.getDescricao());
		} else {
			parametros.put("eloPolo", "");
		}
		
		if (localidade != null) {
			parametros.put("localidade", localidade.getDescricao());
		} else {
			parametros.put("localidade", "");
		}
		
		if (dataCadastramentoInicial != null && dataCadastramentoFinal != null) {
			parametros.put("periodoCadastramento", Util
					.formatarData(dataCadastramentoInicial)
					+ " a " + Util.formatarData(dataCadastramentoFinal));
		} else {
			parametros.put("periodoCadastramento", "");
		}
		
		if (dataExpiracaoInicial != null && dataExpiracaoFinal != null) {
			parametros.put("periodoExpiracao", Util
					.formatarData(dataExpiracaoInicial)
					+ " a " + Util.formatarData(dataExpiracaoFinal));
		} else {
			parametros.put("periodoExpiracao", "");
		}
		
		if (colecaoGruposParametro != null && !colecaoGruposParametro.isEmpty()) {
			
			String grupoParametro = "";
			
			for (Grupo grupo : colecaoGruposParametro) {
				grupoParametro = grupoParametro + grupo.getDescricaoAbreviada() + ", ";
			}
			
			grupoParametro = Util.removerUltimosCaracteres(grupoParametro, 2);
			
			parametros.put("grupo", grupoParametro);
			
		} else {
			parametros.put("grupo", "");
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_USUARIO_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_BAIRRO,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroUsuarioGrupo) getParametro("filtroUsuarioGrupo"),
//				UsuarioGrupo.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterUsuario", this);

	}

}