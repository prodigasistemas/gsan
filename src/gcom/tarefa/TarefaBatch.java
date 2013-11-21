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
package gcom.tarefa;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa batch no sistema
 * 
 * @author Rodrigo Silveira
 * @date 20/07/2006
 */
public abstract class TarefaBatch extends Tarefa {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	protected Collection unidadesJaExecutadas = new ArrayList();
	
	public TarefaBatch(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);

	}

	/**
	 * Pesquisa no sistema para descobrir todas as unidades de processamento
	 * referentes à tarefa
	 * 
	 * @author Rodrigo Silveira
	 * @date 20/07/2006
	 * 
	 * @return coleção com todas as unidades de processamento para o projeto
	 */
	protected abstract Collection<Object> pesquisarTodasUnidadeProcessamentoBatch();

	/**
	 * Pesquisa no sistema para descobrir quais unidades de processamento restam
	 * processar caso a tarefa seja reiniciada
	 * 
	 * @author Rodrigo Silveira
	 * @date 20/07/2006
	 * 
	 * @return coleção com todas as unidades de processamento para o projeto
	 */
	protected abstract Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch();

	protected void enviarMensagemControladorBatch(String queueMDB,
			Object[] parametros) {

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			locator.enviarMensagemJms(queueMDB, parametros);

		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public final void execute(JobExecutionContext arg0)
			throws JobExecutionException {

		this.setIdFuncionalidadeIniciada((Integer) arg0.getJobDetail()
				.getJobDataMap().get("idFuncionalidadeIniciada"));
		this.setUsuario((Usuario) arg0.getJobDetail().getJobDataMap().get(
				"usuario"));
		this.setParametroTarefa((Set) arg0.getJobDetail().getJobDataMap().get(
				"parametroTarefa"));
		this.executar();
	}

	public Collection getUnidadesJaExecutadas() {
		return unidadesJaExecutadas;
	}

	public void setUnidadesJaExecutadas(Collection unidadesJaExecutadas) {
		this.unidadesJaExecutadas = unidadesJaExecutadas;
	}

}