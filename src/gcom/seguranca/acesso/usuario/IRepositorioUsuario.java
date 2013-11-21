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
package gcom.seguranca.acesso.usuario;

import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 * @created 27 de Julho de 2006
 */
public interface IRepositorioUsuario {

	/**
	 * Método que consulta os grupos do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * Método que consulta as abrangências dos usuário pelos os ids das
	 * abrangências superiores e com o id da abrangência diferente do id da
	 * abrangência do usuário que está inserindo(usuário logado)
	 * 
	 * @author Sávio Luiz
	 * @date 28/06/2006
	 */
	public Collection pesquisarUsuarioAbrangenciaPorSuperior(
			Collection colecaoUsuarioAbrangencia,
			Integer idUsuarioAbrangenciaLogado) throws ErroRepositorioException;

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public int totalRegistrosPesquisaUsuarioGrupo(
			FiltroUsuarioGrupo filtroUsuarioGrupo)
			throws ErroRepositorioException;

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisarUsuariosDosGruposUsuarios(
			FiltroUsuarioGrupo filtroUsuarioGrupo, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadesOperacoes(Integer[] idsGrupos)
			throws ErroRepositorioException;

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * Método que consulta os usuários restrinção passando os ids dos grupos , o
	 * id da funcionalidade e o id do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * Método que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ErroRepositorioException;

	/**
	 * Método que consulta as operações da(s) funcionalidade(s)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ErroRepositorioException;

	/**
	 * Método que consulta as permissões especiais do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuario(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * Método que consulta as permissões especiais do usuário com os parametros
	 * das permissões de outro usuário
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuarioComPermissoes(
			Integer idUsuario, Collection permissoesEspeciais)
			throws ErroRepositorioException;

	/**
	 * Método que consulta as permissões especiais do usuário sem os parametros
	 * das permissões de outro usuário.Recupera todas as permissões do usuario
	 * que não tem a permissão de outro usuário
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuarioSemPermissoes(
			Integer idUsuario, Collection permissoesEspeciais)
			throws ErroRepositorioException;

	/**
	 * Essa verificação é preciso para quando for, [SB0011]- Atualizar Controles
	 * de Acesso no [SB0230]-Manter Usuário ,saber que grupos daquela
	 * funcionalidade daquela operação serão inseridos na tabela
	 * UsuarioGrupoRestrincao
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarIdsGruposPelaFuncionalidadeGruposOperacao(
			Integer[] idsGrupos, Integer idFuncionalidade, Integer idOperacao)
			throws ErroRepositorioException;
	
	/**
	 * Método que consulta os grupos do usuário da tabela grupoAcessos
	 * 
	 * @author Sávio Luiz
	 * @date 21/02/2007
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)
			throws ErroRepositorioException; 
	
	
	/**
	* Método que consulta o nome do usuário de uma guia de devolução,
	* passando por parâmetro o id da guia de devolucao
	*
	* @author Daniel Alves
	* @date 22/02/2010
	*/
	public String pesquisarUsuarioPorGuiaDevolucao(	Integer idGuiaDevolucao)
	throws ErroRepositorioException;
	
	
	/**
	 * Método para pesquisar os usuários de uma Unidade Organizacional
	 * 
	 * @author Daniel Alves
	 * @date 11/06/2010
	 */
	public Collection pesquisarUsuariosUnidadeOrganizacional(Integer idUnidadeOrganizacional) 
	      throws ErroRepositorioException;
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)
			throws ErroRepositorioException ;
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ErroRepositorioException;	


	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ErroRepositorioException;
	
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0020] Verificar existência de usuário batch
	 * [FS0021] Verificar usuário batch
	 * 
	 * @author Paulo Diniz
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioRotinaBatch()throws ErroRepositorioException;
	
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0022] Verificar existência de usuário internet
	 * [FS0023] Verificar usuário internet
	 * 
	 * @author Paulo Diniz
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioInternet()throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Filtra os Usuarios por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteUsuario(String valor)throws ErroRepositorioException;

}
