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
package gcom.batch;

import java.util.Collection;
import java.util.Iterator;

import gcom.fachada.Fachada;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.ejb.CreateException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Esta classe representa o componente que verifica no sistema a presenca de
 * ProcessosIniciados nao agendados para iniciar a execucao
 * 
 * @author Rodrigo Silveira
 * @date 21/08/2006
 */
public class VerificadorProcessosIniciados implements Job {

	public static void main(String[] args) {

	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			
			Collection colecao = Fachada.getInstancia().retornaProcessoFuncionalidadeEmExecucao();
			
			String descricaoProcessoFuncionalidade = "";
			
			if ( colecao != null && !colecao.isEmpty() ) {
				Iterator iteratorColecao = colecao.iterator();
				while ( iteratorColecao.hasNext() ) {
					
					Object[] object = (Object[]) iteratorColecao.next();
					
					if ( object[0] != null ) {
						descricaoProcessoFuncionalidade = "Processo: " + (String) object[0] + ";";
					
						if ( object[1] != null ) {
							descricaoProcessoFuncionalidade = descricaoProcessoFuncionalidade +" Funcionalidade: "+ (String) object[1] +";";
							System.out.print(descricaoProcessoFuncionalidade + 
									" Percentual de memória usada: " + 
									Util.retornaPercentualUsadoDeMemoriaJVM()+"%");
						} else {
							System.out.print(descricaoProcessoFuncionalidade + 
									" Percentual de memória usada: " + 
									Util.retornaPercentualUsadoDeMemoriaJVM()+"%");
						}
					}
				
				}
			}
			
			System.out.print("Verificador");
			getControladorBatch().verificadorProcessosSistema();
			
			//Estah aqui soh para testes -- colocar como uma vez por dia
			getControladorBatch().deletarRelatoriosBatchDataExpiracao();
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new JobExecutionException();
		}

	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
}
