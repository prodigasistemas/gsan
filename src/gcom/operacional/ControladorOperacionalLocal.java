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
package gcom.operacional;

import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorOperacionalLocal extends
		javax.ejb.EJBLocalObject {


	/**
	 * [UC0414] - Informar Programação de Abastecimento e Manutenção
	 * 
	 * [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programação de Manutenção na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(Collection colecaoProgramacaoAbastecimento,
		Collection colecaoProgramacaoAbastecimentoRemovidas,Collection colecaoProgramacaoManutencao,
		Collection colecaoProgramacaoManutencaoRemovidas,Usuario usuario) throws ControladorException ;
	
	/**
	 * Permite inserir um Distrito Operacional
	 * 
	 * [UC0521] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi	
	 * @date 29/01/2007
	 * 
	 */
	public Integer inserirDistritoOperacional(String descricao, String descricaoAbreviada, String setorAbastecimento,
			 Usuario usuarioLogado)throws ControladorException;	

	/**
	 * [UC0522] Manter Distrito Operacional 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC005] Manter Distrito Operacional [SB0001] Atualizar Distrito Operacional 
	 * 
	 * @author Eduardo Bianchi
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacinal
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * Permite inserir um Sistema de Esgoto
	 * 
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 */
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */

	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException;
	
	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ControladorException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ControladorException ;
}