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
package gcom.seguranca.acesso;

import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.transacao.Tabela;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorAcessoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * 
	 * Método que consulta todas as TabelaColunas que estejam ligadas a uma
	 * Operacao
	 * 
	 * @author Thiago Toscano
	 * @date 23/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection getTabelaColunaPertencenteOperacao()
			throws ControladorException;

	/**
	 * Método que pesquisa todas as tabelas colunas que tem ligacao com operacao
	 * pela operacao tabela
	 * 
	 * @author thiago toscano
	 * @date 23/03/2006
	 * 
	 * @param idOperacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection getTabelaColunaDasOperacaoTabela(Integer idOperacao)
			throws ControladorException;

	/**
	 * [UC0280] Inserir Funcionalidade
	 * 
	 * Metodo que verifica os dados da tabela e inseri a funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/04/2006
	 * 
	 * @param funcionalidade
	 * @throws ControladorException
	 */

	public Integer inserirFuncionalidade(Funcionalidade funcionalidade,
			Collection colecaoFuncionalidadeDependencia)
			throws ControladorException;

	/**
	 * [UC0281] Manter Funcionalidade [SB0001] Atualizar Funcionalidade Metodo
	 * que atualiza a funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 17/05/2006
	 * 
	 * @param funcionalidade
	 * @throws ControladorException
	 */

	public void atualizarFuncionalidade(Funcionalidade funcionalidade,
			Collection colecaoFuncionalidadeDependencia)
			throws ControladorException;

	/**
	 * Método que insere um grupo e suas funcionalidades com as operacoes
	 * 
	 * [UC0278] Inserir Grupo
	 * 
	 * @author Thiago Toscano
	 * @date 08/05/2006
	 * 
	 * @param grupo
	 * @param grupoFuncionalidadeOperacao
	 */
	public void inserirGrupo(Grupo grupo, Collection grupoFuncionalidadeOperacao, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Método que atualiza um grupo e suas funcionalidades com as operacoes
	 * 
	 * [UC0279] Atualiza Grupo
	 * 
	 * @author Thiago Toscano
	 * @date 08/05/2006
	 * 
	 * @param grupo
	 * @param grupoFuncionalidadeOperacao
	 */
	public void atualizarGrupo(Grupo grupo,
			Collection grupoFuncionalidadeOperacao, Usuario usuarioLogado) throws ControladorException;
		
	/**
	 * Remove os grupos selecionados na tela de manter gruo e os relacionamentos existentes para 
	 * o grupo(remove da tabela GrupoFuncionalidadeOperacao). 
	 *
	 * [UC0279] - Manter Grupo
	 *
	 * @author Pedro Alexandre
	 * @date 29/06/2006
	 *
	 * @param idsGrupos
	 * @throws ControladorException
	 */
	public void removerGrupo(String[] idsGrupos, Usuario usuarioLogado) throws ControladorException ;

	/**
	 * [UC0278] Inserir Situacao Usuario
	 * 
	 * Metodo que verifica os dados da tabela e inseri a Situação do Usuário
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 09/05/2006
	 * 
	 * @param Situação Usuario
	 * @throws ControladorException
	 */

	public Integer inserirSituacaoUsuario(UsuarioSituacao usuarioSituacao)
			throws ControladorException;

	/**
	 * Inseri uma operação e seus relacionamentos com as tabelas se existir
	 * 
	 * [UC0284]Inserir Operação
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2006
	 * 
	 * @param operacao
	 * @param colecaoOperacaoTabela
	 * @throws ControladorException
	 */
	public void inserirOperacao(Operacao operacao,
			Collection<Tabela> colecaoOperacaoTabela, Usuario usuarioLogado)
			throws ControladorException;



	/**
	 * [UC0297] Inserir Abrangência Usuario
	 * 
	 * Metodo que verifica os dados da tabela e inseri a Abrangência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 09/05/2006
	 * 
	 * @param inserirAbrangenciaUsuario
	 * @throws ControladorException
	 */

	public Integer inserirAbrangenciaUsuario(UsuarioAbrangencia usuarioAbrangencia)
throws ControladorException ;

	
	
	/**
	 * [UC0294] Situacao Usuario [] Atualizar Situacao do Usuario
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 * 
	 * @param Situacao Usuario
	 * @throws ControladorException
	 */

	public void atualizarSituacaoUsuario(UsuarioSituacao usuarioSituacao,
			Collection colecaoUsuarioSituacao)
			throws ControladorException;
	
	
	/**
	 * [UC0298] Abrangência Usuario [] Atualizar Abrangência do Usuario
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 * 
	 * @param Abrangência Usuario
	 * @throws ControladorException
	 */

	public void atualizarAbrangenciaUsuario(UsuarioAbrangencia usuarioAbrangencia)
			throws ControladorException;

	
	/**
	 * Constroi um menu de acesso de acordo com as permissões que o usuário que está
	 * logado no sistema conteme monta o link de retorno com o link informado.
	 *
	 * [UC0277] - Construir menu de acesso
	 *
	 * @author Pedro Alexandre
	 * @date 10/07/2006
	 *
	 * @param usuarioLogado
	 * @param linkRetorno
	 * @return
	 * @throws ControladorException
	 */
	public String construirMenuAcesso(Usuario usuarioLogado, String linkRetorno,Integer idGrupo) 
		throws ControladorException ;
		
	/**
	 * Metódo responsável por validar o login e senha do usuário, 
	 * verificando se o usuário existe no sistema.
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws ControladorException
	 */
	public Usuario validarUsuario(String login, String senha) throws ControladorException ;
	
	/**
	 * Metódo responsável por registrar o acesso do usuário incrementando o nº de acessos
	 * e atualizando a data do ultimo acesso do usuário.
	 * 
	 * [UC0287] - Efetuar Login 
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param usuario
	 * @throws ControladorException
	 */
	public void registrarAcessoUsuario(Usuario usuario)	throws ControladorException ;
	
	
	/**
	 * Metódo responsável por criar a arvore do menu com todas as permissões 
	 * do usuário de acordo com os grupos que o usuário pertence.
	 *
	 * [UC0287] - Efetuar Login
	 *
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 *
	 * @param permissoesUsuario
	 * @return
	 * @throws ControladorException
	 */
	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(Collection permissoesUsuario) throws ControladorException ;

	/**
	 * Metódo responsável por atualizar as datas de expiração do login do usuário 
	 * assim como definir uma nova senha para o login 
	 * 
	 *
	 * [UC0289] Efetuar Alteração da Senha
	 *
	 * @author Pedro Alexandre
	 * @date 13/07/2006
	 *
	 * @param usuarioLogado
	 * @param dataNascimentoString
	 * @param cpf
	 * @param lembreteSenha
	 * @param novaSenha
	 * @param confirmacaoNovaSenha
	 * @throws ControladorException
	 */
	public void efetuarAlteracaoSenha(Usuario usuarioLogado, String dataNascimentoString,String cpf,String lembreteSenha, String novaSenha,String confirmacaoNovaSenha) throws ControladorException ;

	/**
	 * [UC0287] - Efetuar Login
	 *
	 * Metódo responsável por enviar uma nova senha para o e-mail 
	 * do usuário com situação pendente
	 *
	 * [SB0002] - Lembrar senha
	 *
	 * @author Pedro Alexandre
	 * @date 14/07/2006
	 *
	 * @param login
	 * @param cpf
	 * @param dataNascimentoString
	 * @throws ControladorException
	 */
	public void lembrarSenha(String login, String cpf, String dataNascimentoString) throws ControladorException;

	/**
	 * Verifica se uma url solicitada para o servidor é uma funcionalidade ou uma operação 
	 *
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 *
	 * @param url
	 * @return
	 * @throws ControladorException
	 */
	public String verificarTipoURL(String url) throws ControladorException;
	
	/**
	 * Metódo que verifica se o usuário tem permissão para acessar a funcionalidade 
	 * que está sendo requisitada (existe ocorrência na tabela GrupoFuncionalidadeOperacao).
	 * Verifica se o(s) grupo(s) que o usuário pertence tem acesso a funcionalidade e
	 * se todas as operações desta funcionalidade não estão com restrições(existe ocorrência na tabela UsuarioGrupoRestricao)  
	 *
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 *
	 * @param usuarioLogado
	 * @param urlFuncionalidade
	 * @param colecaoGruposUsuario
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarAcessoPermitidoFuncionalidade(Usuario usuarioLogado, 
		String urlFuncionalidade, Collection colecaoGruposUsuario,
		Integer idFuncionalidade) throws ControladorException;
	
	/**
	 * Metódo que verifica se o usuário tem permissão para acessar a operação 
	 * que está sendo requisitada (existe ocorrência na tabela GrupoFuncionalidadeOperacao).
	 * Verifica se o(s) grupo(s) que o usuário pertence tem acesso a operação e
	 * se a operação desta funcionalidade não estão com restrição(existe ocorrência na tabela UsuarioGrupoRestricao)  
	 *
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 *
	 * @param usuarioLogado
	 * @param urlOperacao
	 * @param colecaoGruposUsuario
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarAcessoPermitidoOperacao(Usuario usuarioLogado, String urlOperacao, Collection colecaoGruposUsuario) throws ControladorException;

	/**
	 * [UC0285] - Manter Operação
	 *
	 * Metódo responsável por atualizar uma operação no sistema e os relacionamentos entre
	 * a tabela e a operação
	 *
	 * [SB0001] - Atualizar Operação	
	 *
	 * @author Pedro Alexandre
	 * @date 02/08/2006
	 *
	 * @param operacao
	 * @param colecaoOperacaoTabela
	 * @throws ControladorException
	 */
	public void atualizarOperacao(Operacao operacao,Collection<OperacaoTabela> colecaoOperacaoTabela, Usuario usuarioLogado) throws ControladorException ;

	/**
	 * [UC0285] - Manter Operação
	 *
	 * Metódo responsável por remover uma operação no sistema e os relacionamentos entre
	 * a tabela e a operação
	 *
	 * [SB0002] - Excluir Operação	
	 *
	 * @author Pedro Alexandre
	 * @date 02/08/2006
	 *
	 * @param idsOperacao
	 * @throws ControladorException
	 */
	public void removerOperacao(String[] idsOperacao,Usuario usuarioLogado) throws ControladorException ;
    
     /**
     * Metódo responsável por verificar se o usuário tem abrangência sobre a operação 
     * e o nível de informação que estão sendo informados.
     *
     * [UC0XXX] Verificar Acesso Abrangência 
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param abrangencia
     * @return
     * @throws ControladorException
     */
    public boolean verificarAcessoAbrangencia(Abrangencia abrangencia) throws ControladorException;
    
	/**
	 * Metódo responsável por criar a arvore do menu com todas as permissões do
	 * usuário de acordo com os grupos que o usuário pertence.
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param permissoesUsuario
	 * @return
	 * @throws ControladorException
	 */
	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(Integer modulo) 
		throws ControladorException ;
	
	
	/**
	 * Pesquisa os favoritos do usuario
	 * 
	 * @author: Rafael Pinto
	 * @date: 01/06/2009
	 * 
	 * @param idUsuario Id do Usuario
	 * @return Colection Colecao de Usuario Favoritos
	 * @throws ControladorException
	 */	
	public Collection pesquisarUsuarioFavorito(Integer idUsuario)
		throws ControladorException ;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 * 
	 * Verifica se existe localidade que esteja fora da abrangência do usuário 
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public boolean existeLocalidadeForaDaAbrangenciaUsuario(
			FiltrarImovelInserirManterContaHelper filtro,
			Integer nivelAbrangencia,Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC1040] Gerar Relatório de Acessos por Usuário
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Collection<RelatorioAcessosPorUsuariosHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper) throws ControladorException;
	
	/**
	 * [UC1040] Gerar Relatório de Acessos por Usuário
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper filtro) throws ControladorException;
	
	/**
	 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Collection<RelatorioFuncionalidadeOperacoesPorGrupoHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper)throws ControladorException;
	
	/**
	 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper filtro) throws ControladorException;
	
	/**
	 * Informa o número total de registros do grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisarGrupos(FiltroGrupo filtroGrupo, Integer numeroPagina)
			throws ControladorException;
	
	/**
	 * [UC1047] Inserir Controle de Liberação de Permissão Especial
	 * 
	 * Metodo que verifica os dados da tabela e inseri um 
	 * Controle de Liberação de Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
	 * 
	 * @param controleLiberacaoPermissaoEspecial
	 * @param usuarioLogado
	 * @throws ControladorException
	 */

	public Integer inserirControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC1048] Manter Controle de Liberação de Permissão Especial
	 * que atualiza o Controle de Liberação de Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
	 * 
	 * @param controleLiberacaoPermissaoEspecial
	 * @throws ControladorException
	 */

	public void manterControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * Pesquisa se existe algum controle com permissão especial ativa para a funcionalidade.
	 * 
	 * @author: Daniel Alves
	 * @date: 31/08/2010
	 * @return boolean
	 */	
	public boolean existeControlePermissaoEspecialFuncionalidade(Integer idFuncionalidade) throws ControladorException;
	
	/**
	 * Remover todos os Grupos Associados a Solicitação de Acesso.
	 * [UC1093] - Manter Solicitação de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 18/11/2010
	 * 
	 * @param idsolicitacaoAcesso
	 * @return void
	 */
	public void removerGrupoDeSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException;
	
	/**
	 * Atualizar Solicitação de Acesso quando ela for Cadastrada.
	 * [UC1093] - Manter Solicitação de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 19/11/2010
	 * 
	 * @param idsolicitacaoAcesso
	 * @return void
	 */
	public void atualizarCadastroSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException;
	
	/**
	 * [UC1093] Gerar Relatório Solicitação de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 23/11/2010
	 * 
	 * @param FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @return Collection<RelatorioSolicitacaoAcessoHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioSolicitacaoAcesso(
			FiltrarRelatorioSolicitacaoAcessoHelper helper)throws ControladorException;
	
}