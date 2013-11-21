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
package gcom.batch.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarAtividadeAcaoCobrancaMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarAtividadeAcaoCobrancaMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {

				/*
				 * gerarAtividadeAcaoCobranca( CobrancaGrupo grupoCobranca, int
				 * anoMesReferenciaCicloCobranca, Integer
				 * idCronogramaAtividadeAcaoCobranca, Integer
				 * idComandoAtividadeAcaoCobranca, Collection<Rota> rotas,
				 * CobrancaAcao acaoCobranca, CobrancaAtividade
				 * atividadeCobranca, Integer indicadorCriterio,
				 * CobrancaCriterio criterioCobranca, Cliente cliente,
				 * ClienteRelacaoTipo relacaoClienteImovel, String
				 * anoMesReferenciaInicial, String anoMesReferenciaFinal, Date
				 * dataVencimentoInicial, Date dataVencimentoFinal)
				 */

//				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
//								(CobrancaGrupo) ((Object[]) objectMessage.getObject())[0],
//								(Integer) ((Object[]) objectMessage.getObject())[1],
//								(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[2],
//								(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[3],
//								(Collection) ((Object[]) objectMessage.getObject())[4],
//								(CobrancaAcao) ((Object[]) objectMessage.getObject())[5],
//								(CobrancaAtividade) ((Object[]) objectMessage.getObject())[6],
//								(Integer) ((Object[]) objectMessage.getObject())[7],
//								(CobrancaCriterio) ((Object[]) objectMessage.getObject())[8],
//								(Cliente) ((Object[]) objectMessage.getObject())[9],
//								(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[10],
//								(String) ((Object[]) objectMessage.getObject())[11],
//								(String) ((Object[]) objectMessage.getObject())[12],
//								(Date) ((Object[]) objectMessage.getObject())[13],
//								(Date) ((Object[]) objectMessage.getObject())[14],
//								(Date) ((Object[]) objectMessage.getObject())[15],
//								(Integer) ((Object[]) objectMessage.getObject())[16],
//								(Cliente) ((Object[]) objectMessage.getObject())[17]
//
//						);

				
				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
						(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[0],
						(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[1],
						(Rota) ((Object[]) objectMessage.getObject())[2],
						(CobrancaAcao) ((Object[]) objectMessage.getObject())[3],
						(CobrancaAtividade) ((Object[]) objectMessage.getObject())[4],
						(Integer) ((Object[]) objectMessage.getObject())[5],
						(CobrancaCriterio) ((Object[]) objectMessage.getObject())[6],
						(Cliente) ((Object[]) objectMessage.getObject())[7],
						(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[8],
						(String) ((Object[]) objectMessage.getObject())[9],
						(String) ((Object[]) objectMessage.getObject())[10],
						(Date) ((Object[]) objectMessage.getObject())[11],
						(Date) ((Object[]) objectMessage.getObject())[12],
						(Date) ((Object[]) objectMessage.getObject())[13],
						(Integer) ((Object[]) objectMessage.getObject())[14],
						(Cliente) ((Object[]) objectMessage.getObject())[15],
						(Integer) ((Object[]) objectMessage.getObject())[16]

				);
				
				

				
				
				
				
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	// this.enviarMensagemControladorBatch(
	// MetodosBatch.ENDERECO_INSERIR_CEP_IMPORTADOS,
	// ConstantesJNDI.QUEUE_CONTROLADOR_FATURAMENTO_MDB,
	// new Object[] { cepsImportados });

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
}
