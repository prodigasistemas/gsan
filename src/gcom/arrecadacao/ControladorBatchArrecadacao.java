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
package gcom.arrecadacao;

import java.util.Collection;
import java.util.Map;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.MetodosBatch;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ControladorBatchArrecadacao implements MessageDrivenBean,
		MessageListener {
	private static final long serialVersionUID = 1L;

	public ControladorBatchArrecadacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				switch (objectMessage.getIntProperty("nomeMetodo")) {
					case (MetodosBatch.REGISTRAR_MOVIMENTOS_ARRECADADORES) : {
						this
								.getControladorArrecadacao()
								.registrarMovimentoArrecadadores(
										(StringBuilder) ((Object[]) objectMessage
												.getObject())[0],
										(Short) ((Object[]) objectMessage
												.getObject())[1],
										(String) ((Object[]) objectMessage
												.getObject())[2],
										(String) ((Object[]) objectMessage
												.getObject())[3],
										(Integer) ((Object[]) objectMessage
												.getObject())[4],
										(Usuario) ((Object[]) objectMessage
												.getObject())[5],
										(Integer) ((Object[]) objectMessage
												.getObject())[6],
										(ArrecadadorContrato) ((Object[]) objectMessage
												.getObject())[7]
												);
						break;

					}
					case (MetodosBatch.GERAR_MOVIMENTO_DEBITO_AUTOMATICO_BANCO) : {
						this
								.getControladorArrecadacao()
								.gerarMovimentoDebitoAutomaticoBanco(
										(Map<Banco, Collection<DebitoAutomaticoMovimento>>) ((Object[]) objectMessage
												.getObject())[0],
										(Usuario) ((Object[]) objectMessage
												.getObject())[1]);
						break;

					}
					case (MetodosBatch.REGERAR_MOVIMENTO_DEBITO_AUTOMATICO_BANCO) : {
						this
								.getControladorArrecadacao()
								.regerarArquivoTxtMovimentoDebitoAutomatico(
										(ArrecadadorMovimento) ((Object[]) objectMessage
												.getObject())[0],
												(String) ((Object[]) objectMessage
														.getObject())[1],
										(Usuario) ((Object[]) objectMessage
												.getObject())[2]);
						break;

					}
					
					case (MetodosBatch.GERAR_RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES) : {
						this
								.getControladorArrecadacao()
								.gerarResumoAcompanhamentoMovimentoArrecadadores(
										(Usuario) ((Object[]) objectMessage
												.getObject())[0],
												(String) ((Object[]) objectMessage
														.getObject())[1],
										(Arrecadador) ((Object[]) objectMessage
												.getObject())[2],
												(ArrecadacaoForma) ((Object[]) objectMessage
														.getObject())[3]);
						break;

					}


				}
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {
		// TODO Auto-generated method stub
	}
}
