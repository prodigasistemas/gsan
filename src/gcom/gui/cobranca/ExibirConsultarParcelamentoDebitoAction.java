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
package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConsultarParcelamentoDebitoAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarParcelamentoDebito");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		ParcelamentoDebitoActionForm parcelamentoDebitoActionForm = (ParcelamentoDebitoActionForm) actionForm;
		
		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		Collection<Integer> idsContaEP = new ArrayList<Integer>();

		// Pega os codigos que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = httpServletRequest.getParameter("codigoImovel");
		String codigoParcelamento = httpServletRequest.getParameter("codigoParcelamento");
		sessao.setAttribute("idParcelamento", codigoParcelamento);
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			
			//Alterado por Raphael Rossiter em 24/01/2007
			
			// Pesquisa o imovel na base
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));

			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			// Se nenhum imovel for encontrado a mensagem é enviada para a página
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("enderecoFormatado",
						"Matrícula Inexistente".toUpperCase());
				parcelamentoDebitoActionForm.setInscricao("");
				parcelamentoDebitoActionForm.setNomeCliente("");
				parcelamentoDebitoActionForm.setCpfCnpj("");
				parcelamentoDebitoActionForm.setSituacaoAgua("");
				parcelamentoDebitoActionForm.setSituacaoEsgoto("");
				parcelamentoDebitoActionForm.setImovelPerfil("");
			}
			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				
				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);
				
				//O endereço foi encontrado
				if (dadosImovel.getImovel().getId() != null) 
				{
					parcelamentoDebitoActionForm.setCodigoImovel(""
							+ dadosImovel.getImovel().getId());
				}
				if (dadosImovel.getImovel().getInscricaoFormatada() != null) 
				{
					parcelamentoDebitoActionForm.setInscricao(""
							+ dadosImovel.getImovel().getInscricaoFormatada());
				}
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoAgua(""
							+ dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoEsgoto(""
							+ dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				if (dadosImovel.getCliente().getNome() != null) 
				{
					parcelamentoDebitoActionForm.setNomeCliente(""
							+ dadosImovel.getCliente().getNome());
				}
				if (dadosImovel.getImovel().getImovelPerfil() != null) 
				{
					parcelamentoDebitoActionForm.setImovelPerfil(""
							+ dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
					if (dadosImovel.getCliente().getCpfFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCpfFormatado());
					}
				}
				else
				{
					if (dadosImovel.getCliente().getCnpjFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setParcelamento(""
							+ dadosImovel.getImovel().getNumeroParcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamento(""
							+ dadosImovel.getImovel().getNumeroReparcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamentoConsecutivo(""
							+ dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}
				// Manda a colecao pelo request
				httpServletRequest.setAttribute("imovelPesquisado",
						imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				httpServletRequest.setAttribute("enderecoFormatado",enderecoFormatado);
			}
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();

			filtroParcelamento.adicionarParametro(new ParametroSimples(
						FiltroParcelamento.ID, codigoParcelamento));

			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuarioDesfez");			
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
			
			SistemaParametro sistemaParametro = null;
	        
			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) 
			{
				httpServletRequest.setAttribute("colecaoParcelamento", colecaoParcelamento);
			
				Iterator iteratorParcelamento = colecaoParcelamento.iterator();
				while (iteratorParcelamento.hasNext()) {
		        	
		        	Parcelamento parcelamento = (Parcelamento) iteratorParcelamento.next();
		        	
		        	if (parcelamento.getCliente()!= null && parcelamento.getCliente().getNome() != null){
		        		parcelamentoDebitoActionForm.setNomeClienteResponsavel(parcelamento.getCliente().getNome());
		        	}
		        	
		        	if(parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.DESFEITO.intValue()){
		    			parcelamentoDebitoActionForm.setDataParcelamentoDesfeito(Util.formatarDataComHora(parcelamento.getUltimaAlteracao()));
		    		}else{
		    			parcelamentoDebitoActionForm.setDataParcelamentoDesfeito("");
		    		}
		        	
		        	// Retorna o único objeto da tabela sistemaParametro
		            sistemaParametro = fachada.pesquisarParametrosDoSistema();
		            
		            //pesquisa para descobrir o numero de prestações cobradas
		            FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		            filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
		            		FiltroDebitoACobrar.PARCELAMENTO_ID, codigoParcelamento));
		            
		            Collection<DebitoACobrar> colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName() );
		            short numeroPrestacaoCobradas = 0;
		            
		            if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
		            	numeroPrestacaoCobradas = colecaoDebitoACobrar.iterator().next().getNumeroPrestacaoCobradas();
		            }
		            
		            boolean itensHistoricoParcelamento = fachada.verificarItensParcelamentoNoHistorico(new Integer(codigoImovel),new Integer(codigoParcelamento) );
		            Integer anoMesEfetivacaoParcelamento = Util.getAnoMesComoInteger(parcelamento.getParcelamento());
		            
		            if((anoMesEfetivacaoParcelamento.compareTo(new Integer(sistemaParametro.getAnoMesArrecadacao())) >= 0) 
		        		&& parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue()
		        		&& numeroPrestacaoCobradas == 0 && !itensHistoricoParcelamento) {
		        		
		        		FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
		        		Collection<ParcelamentoMotivoDesfazer> collectionParcelamentoMotivoDesfazer = fachada.pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName() );
		        		 
		        		httpServletRequest.setAttribute("collectionParcelamentoMotivoDesfazer", collectionParcelamentoMotivoDesfazer);
		     
		        		// Verifica se a entrada do parcelamento tenha sido através de contas marcadas como EP
		        		FiltroConta filtroConta = new FiltroConta();
		        		filtroConta.adicionarParametro(new ParametroSimples(
								FiltroConta.IMOVEL_ID, codigoImovel));

						filtroConta.adicionarParametro(new ParametroSimples(
								FiltroConta.PARCELAMENTO_ID,codigoParcelamento));

						Collection colecaoConta2 = fachada.pesquisar(filtroConta, Conta.class.getName());

						if (colecaoConta2 != null && !colecaoConta2.isEmpty()) {

							Iterator iteratorConta = colecaoConta2.iterator();

							while (iteratorConta.hasNext()) {

								Conta conta = null;

								conta = (Conta) iteratorConta.next();

								if ((conta.getDebitoCreditoSituacaoAtual().getId()
										.intValue() == DebitoCreditoSituacao.NORMAL.intValue())
										|| (conta.getDebitoCreditoSituacaoAtual().getId()
												.intValue() == DebitoCreditoSituacao.RETIFICADA.intValue())
										|| (conta.getDebitoCreditoSituacaoAtual().getId()
												.intValue() == DebitoCreditoSituacao.INCLUIDA.intValue())) {
									
									idsContaEP.add(conta.getId());
									
								}
							}
							
						}
		        		
		        		
		        	}
		        }
			}
		}
		
		
		
		
		
		 FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		 filtroGuiaPagamento.adicionarParametro(new ParametroSimples
        			(FiltroGuiaPagamento.PARCELAMENTO_ID,new Integer(codigoParcelamento)));
		 
    	 Collection collectionGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
    	 
    	 if (collectionGuiaPagamento != null
    			 && !collectionGuiaPagamento.isEmpty()){
    		 sessao.setAttribute("btImprimirGuiaPagamentoEntrada" , 1);
    	 }else{
    		 sessao.removeAttribute("btImprimirGuiaPagamentoEntrada");
    	 }
		 sessao.setAttribute("idsContaEP",idsContaEP);
		 
		 //UC-0252(Alteração 24/07/09 Rosana Carvalho) Author:Hugo Amorim 
		 FiltroParcelamentoPagamentoCartaoCredito filtroParcelamento = new FiltroParcelamentoPagamentoCartaoCredito();
			
			filtroParcelamento.adicionarParametro(new ParametroSimples(
					FiltroParcelamentoPagamentoCartaoCredito.ID_PARCELAMENTO,codigoParcelamento));
			

			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("arrecadador");

			Collection<ParcelamentoPagamentoCartaoCredito> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, ParcelamentoPagamentoCartaoCredito.class.getName() );
			
			ParcelamentoPagamentoCartaoCredito parc = (ParcelamentoPagamentoCartaoCredito) Util.retonarObjetoDeColecao(colecaoParcelamento);
		 
		 if(parc!=null){
			 if(parc.getNumeroCartaoCredito()!=null && !parc.getNumeroCartaoCredito().equals("")){
				 sessao.setAttribute("parcelamentoCartaCredito",codigoParcelamento);
				 sessao.setAttribute("buttonCartaoCredito","true");
			 }
		 }
		 
		 /*
		  * Caso o parcelamento tenha dados de cartão de crédito não confirmados pela operadora (PACC_ICONFIRMADOOPERADORA da tabela 
		  * PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO com PARC_ID = PARC_ID do parcelamento selecionado com valor igual 2 (Não))
		  */
		 if (codigoParcelamento != null && !codigoParcelamento.equals("")){
			 
			 boolean habilitarBotaoDesfazer = fachada.parcelamentoPagamentoCartaoCreditoJaConfirmado(Integer.valueOf(codigoParcelamento));
			 
			 if (!habilitarBotaoDesfazer){
				 
				 httpServletRequest.setAttribute("habilitarBotaoDesfazer", "SIM");
			 }
		 }
		 
		return retorno;
	}
}
