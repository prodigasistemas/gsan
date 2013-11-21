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
package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtaualizarDadosCartaoCreditoDebitoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Sera o caminho de retorno
		ActionForward retorno = actionMapping.findForward("consultarDadosParcelamentoCartaoCredito");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm = (ParcelamentoCartaoConfirmarForm) actionForm;

		//ATUALIZANDO A TRANSAÇÃO SELECIONADA PELO USUÁRIO
		this.atualizarParcelamentoPagamentoCartaoCreditoNaSessao(httpServletRequest, sessao, 
		parcelamentoCartaoConfirmarForm);
		
		
		String concluir = (String) httpServletRequest.getParameter("concluir");
		
		if (concluir != null && concluir.equals("ok")){
			
			Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			Collection colecaoParcelamentoPagamentoCartaoCredito = (Collection) sessao.getAttribute("colecaoParcelamentos");
			
			fachada.atualizarParcelamentoPagamentoCartaoCredito(colecaoParcelamentoPagamentoCartaoCredito, usuarioLogado);
			
			httpServletRequest.setAttribute("fechaPopup", "SIM");
		}
		

		return retorno;
	}
	
	
	public void atualizarParcelamentoPagamentoCartaoCreditoNaSessao(HttpServletRequest httpServletRequest, HttpSession sessao,
			ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm){
		
		String atualizar = (String) httpServletRequest.getParameter("atualizar");
		
		if (atualizar != null && atualizar.equalsIgnoreCase("ok")){
			
			//PARCELAMENTO PAGAMENTO CARTÃO CRÉDITO SELECIONADO
			ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCredito = 
			(ParcelamentoPagamentoCartaoCredito) sessao.getAttribute("parcelamentoAtualizacao");
			
			if (parcelamentoPagamentoCartaoCredito != null){
				
				//COLEÇÃO COM TODAS AS TRANSAÇÕES DE CARTÃO DE CRÉDITO ATIVAS PARA O PARCELAMENTO
				//Collection colecaoParcelamentoPagamentoCartaoCreditoNew = new ArrayList();
				Collection colecaoParcelamentoPagamentoCartaoCredito = (Collection) sessao.getAttribute("colecaoParcelamentos");
				
				if (colecaoParcelamentoPagamentoCartaoCredito != null && !colecaoParcelamentoPagamentoCartaoCredito.isEmpty()){
					
					Iterator iterator = colecaoParcelamentoPagamentoCartaoCredito.iterator();
					
					while(iterator.hasNext()){
						
						ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCreditoColecao = (ParcelamentoPagamentoCartaoCredito)
						iterator.next();
						
						//IDENTIFICANDO A TRANSAÇÃO QUE SERÁ ALTERADA
						if (parcelamentoPagamentoCartaoCreditoColecao.getId().equals(parcelamentoPagamentoCartaoCredito.getId())){
							
							if(parcelamentoCartaoConfirmarForm.getNumeroCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getNumeroCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setNumeroCartaoCredito(
								Util.encrypt(parcelamentoCartaoConfirmarForm.getNumeroCartao()));
							}
							
							if(parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao() != null && 
								!parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setIdentificacaoTransacao(
								parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getValidadeCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getValidadeCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setAnoMesValidade(
								Util.formatarMesAnoComBarraParaAnoMes(parcelamentoCartaoConfirmarForm.getValidadeCartao()));
							}
							
							if(parcelamentoCartaoConfirmarForm.getNomeCliente() != null && 
								!parcelamentoCartaoConfirmarForm.getNomeCliente().equals("")){
								
								Cliente cliente = new Cliente();
								cliente.setId(new Integer(parcelamentoCartaoConfirmarForm.getIdCliente()));
								cliente.setNome(parcelamentoCartaoConfirmarForm.getNomeCliente());
								parcelamentoPagamentoCartaoCreditoColecao.setCliente(cliente);
							}
							
							if(parcelamentoCartaoConfirmarForm.getDocumentoCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getDocumentoCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setDocumentoCartaoCredito(parcelamentoCartaoConfirmarForm.getDocumentoCartao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getAutorizacaoCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getAutorizacaoCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setNumeroAutorizacao(parcelamentoCartaoConfirmarForm.getAutorizacaoCartao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getValorTransacao() != null && 
								!parcelamentoCartaoConfirmarForm.getValorTransacao().equals("")){
									
								parcelamentoPagamentoCartaoCreditoColecao.setValorParcelado(Util.formatarMoedaRealparaBigDecimal(
								parcelamentoCartaoConfirmarForm.getValorTransacao()));
							}
							
							break;
						}
					}
				}
			}
		}
	}
}
