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
package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição dos dados na tela do 
 * 	desfazer cancelamento e/ou retificação da conta. 
 * 
 * [UC0327] Desfazer Cancelamento e/ou Retificação de Conta 
 * 
 * @param actionMapping
 *            Descrição do parâmetro
 * @param actionForm
 *            Descrição do parâmetro
 * @param httpServletRequest
 *            Descrição do parâmetro
 * @param httpServletResponse
 *            Descrição do parâmetro
 * @return Descrição do retorno
 */
public class ExibirManterDesfazerCancelamentoRetificacaoContaAction extends
		GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("desfazerCancelamentoRetificacaoConta");

		DesfazerCancelamentoRetificacaoContaActionForm desfazerCancelamentoRetificacaoContaActionForm = (DesfazerCancelamentoRetificacaoContaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// -------Parte que trata do código quando o usuário tecla enter
		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = (String) desfazerCancelamentoRetificacaoContaActionForm
				.getIdImovel();

		// Se o código do imóvel tiver sido digitado seta no form os dados do imóvel
		if (codigoDigitadoImovelEnter != null
				&& !codigoDigitadoImovelEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoImovelEnter) > 0) {

			Imovel imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(codigoDigitadoImovelEnter));
			
			if (imovel != null) {

				// O imovel foi encontrado
				desfazerCancelamentoRetificacaoContaActionForm.setIdImovel(""
						+ imovel.getId());

				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(imovel.getInscricaoFormatada());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua(imovel.getLigacaoAguaSituacao()
								.getDescricao());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao()
								.getDescricao());

				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(codigoDigitadoImovelEnter));

				// Manda os dados do cliente para a página
				if (cliente != null) {
					desfazerCancelamentoRetificacaoContaActionForm.setNomeClienteUsuario(cliente
							.getNome());
				}
				

				Collection contas = fachada.obterContasImovelManter(imovel, DebitoCreditoSituacao.CANCELADA,
						DebitoCreditoSituacao.CANCELADA, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO);

				/**
				 * Só sera enviada contas que ta na situação retificada ou cancelada
				 * Alterado por Arthur Carvalho
				 * Analista Eduardo Rosa
				 * Data:14/05/2010
				 */
				Iterator iteratorContas = contas.iterator();
				Collection colecaoContas = new ArrayList();
				while ( iteratorContas.hasNext() ) {
					
					Conta conta = (Conta) iteratorContas.next();
					//RETIFICADA
					if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO ) ) {
					
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.RETIFICADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
						//CANCELADA
					} else if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA ) ) {
						
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.CANCELADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
					}
					
				}
				// Manda os dados da conta para a página
				if (colecaoContas != null && !colecaoContas.isEmpty()) {
					sessao.setAttribute("contas", colecaoContas);
				}
				else
				{
					throw new ActionServletException(
							"atencao.pesquisa.nenhuma.conta_cancelada_retificada_imovel", null, ""
									+ codigoDigitadoImovelEnter);
				}
			} else {
				httpServletRequest.setAttribute("corImovel", "exception");
				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				desfazerCancelamentoRetificacaoContaActionForm
						.setIdImovel("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setNomeClienteUsuario("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto("");
				sessao.removeAttribute("contas");
			}
		}
		return retorno;
	}
}
