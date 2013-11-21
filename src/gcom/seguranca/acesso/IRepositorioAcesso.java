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

import java.util.Collection;

import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;


/**
 * Descrição da classe 
 *
 * @author Administrador
 * @date 13/11/2006
 */
public interface IRepositorioAcesso {

    public Object pesquisarObjetoAbrangencia(String consulta) throws ErroRepositorioException ;
    
	public void atualizarRegistrarAcessoUsuario(Usuario usuario)
 		throws ErroRepositorioException ;
	
	public Collection pesquisarUsuarioFavorito(Integer idUsuario)
		throws ErroRepositorioException ;
	
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
	public Collection pesquisarLocalidadeForaDaAbrangenciaUsuario(FiltrarImovelInserirManterContaHelper filtro,
			Integer nivelAbrangencia,Usuario usuarioLogado)throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa senhas invalidas 
	 * 
	 * @author Hugo Amorim	
	 * @date 04/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSenhasInvalidas() throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar senhas anteriores do usuario 
	 * 
	 * @author Hugo Amorim	
	 * @date 08/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSenhasAnterioresUsuario(Usuario usuario)
			throws ErroRepositorioException;
	
	/**
	 * [UC1040] Gerar Relatório de Acessos por Usuário
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1040] Gerar Relatório de Acessos por Usuário
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper) throws ErroRepositorioException;
			
	/**
	 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper) throws ErroRepositorioException;
	
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
	 */
	public Collection pesquisarGrupos( FiltroGrupo filtroGrupo, Integer numeroPagina)
			throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa se existe algum controle com permissão especial ativa para a funcionalidade.
	 * 
	 * @author: Daniel Alves
	 * @date: 31/08/2010
	 * @return boolean
	 */	
	public boolean existeControlePermissaoEspecialFuncionalidade(Integer idFuncionalidade)
		throws ErroRepositorioException;
	
	/**
	 * [UC1093] Gerar Relatório Solicitação de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 23/07/2010
	 * 
	 * @param FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioSolicitacaoAcesso(
			FiltrarRelatorioSolicitacaoAcessoHelper helper) throws ErroRepositorioException;
	
}
