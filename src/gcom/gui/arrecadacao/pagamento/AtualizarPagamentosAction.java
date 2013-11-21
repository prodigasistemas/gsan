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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que finaliza a página de atualizar pagamento
 * 
 * @author Pedro Alexandre
 * @created 22/03/2006
 */
public class AtualizarPagamentosAction extends GcomAction {

	/**
	 * @author Pedro Alexandre
	 * @date 22/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Cria a variável de retorno e seta o mapeamento para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		 
		// Recupera o form
		ManterPagamentoActionForm manterPagamentoActionForm = 
			(ManterPagamentoActionForm) actionForm;

		// Recupera o pagamento que vai ser atualizado da sessão
		Pagamento pagamentoAtualizacao = 
			(Pagamento) this.getSessao(httpServletRequest).getAttribute("pagamento");

		// Recupera o valor do pagamento atual e seta na variável que vai
		// armazenar
		// o valor do pagamento anteriro
		BigDecimal valorPagamentoAnteriror = 
			pagamentoAtualizacao.getValorPagamento();

		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de confirmação
		String confirmado = httpServletRequest.getParameter("confirmado");

		// [FS0019] - Validar data do pagamento
		// Recupera a data de pagamento e verifica se a data é uma data válida
		String dataPagamentoString = 
			manterPagamentoActionForm.getDataPagamento();
		Date dataPagamento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataPagamento = dataFormato.parse(dataPagamentoString);
		} catch (ParseException ex) {
			throw new ActionServletException("atencao.data_pagamento_invalida");
		}

		// Recupera o código da forma de arrecadação e pesquisa o objeto no
		// sistema
		String idFormaArrecadacao = manterPagamentoActionForm.getIdFormaArrecadacao();
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(new Integer(idFormaArrecadacao));

		// Recupera o código do aviso bancário e pesquisa o objeto no sistema
		String idAvisoBancario = manterPagamentoActionForm.getIdAvisoBancario();
		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
		filtroAvisoBancario.adicionarParametro(
			new ParametroSimples(
				FiltroAvisoBancario.ID, 
				idAvisoBancario));
		
		AvisoBancario avisoBancario = 
			(AvisoBancario) (this.getFachada().pesquisar(filtroAvisoBancario, 
				AvisoBancario.class.getName())).iterator().next();

		// Recupera o código da situação atual do pagamento
		String idSituacaoAtualPagamento = 
			manterPagamentoActionForm.getIdSituacaoAtualPagamento();

		// cria as variáveis que vão armazenar as situações atual e anterior do
		// pagamento
		PagamentoSituacao situacaoPagamentoAtual = null;
		PagamentoSituacao situacaoPagamentoAnterior = null;

		// Caso a nova situação atual do pagamento tenha sido informada na
		// página
		if (idSituacaoAtualPagamento != null && 
			!idSituacaoAtualPagamento.trim().equalsIgnoreCase("")) {
			
			// Cria o filtro da situação de pagamento e pesquisa a situação
			// informada no sistema
			FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
			
			/*
			 * Colocado por Raphael Rossiter em 19/10/2007
			 * OBJ:Selecionar apenas a situacao escolhida pelo usuario
			 */
			filtroPagamentoSituacao.adicionarParametro(
				new ParametroSimples(FiltroPagamentoSituacao.CODIGO,
				new Integer(idSituacaoAtualPagamento)));
			
			//Situação atual
			situacaoPagamentoAtual = 
				(PagamentoSituacao) Util.retonarObjetoDeColecao(
					this.getFachada().pesquisar(filtroPagamentoSituacao, 
						PagamentoSituacao.class.getName()));

			// Seta a situação atual do pagamento para a situação anterior
			situacaoPagamentoAnterior = pagamentoAtualizacao.getPagamentoSituacaoAtual();
			 
		} else {
			// Caso a nova situação do pagamento não tenha sido informada
			// o pagamento continua com as mesmas situações inalteradas
			if (pagamentoAtualizacao.getPagamentoSituacaoAtual() != null) {
				situacaoPagamentoAtual = pagamentoAtualizacao.getPagamentoSituacaoAtual();
			}

			if (pagamentoAtualizacao.getPagamentoSituacaoAnterior() != null) {
				situacaoPagamentoAnterior = pagamentoAtualizacao.getPagamentoSituacaoAnterior();
			}
		}

		// Recupera o valor do pagamento
		BigDecimal valorPagamentoNovo = 
			Util.formatarMoedaRealparaBigDecimal(manterPagamentoActionForm.getValorPagamento());

		// Recupera o código do imóvel
		String idImovel = manterPagamentoActionForm.getIdImovel();

		// Cria a variável que vai armazenar o imóvel informado
		Imovel imovel = null;

		// Caso o usuário tenha informado o imóvel
		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {

			// [FS0004] - Verificar existência da matrícula do imóvel
			imovel = this.getFachada().pesquisarImovelDigitado(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException("atencao.naocadastrado", 
					null,
					"Matrícula do imóvel");
			}

		}

		// Recupera o tipo de documento
		String idTipoDocumento = manterPagamentoActionForm.getIdTipoDocumento();

		// Recupera o código da localidade
		String idLocalidade = manterPagamentoActionForm.getIdLocalidade();

		// Cria a variável que vai armazenar a localidade informada
		Localidade localidade = null;

		// Caso o tipo de documento não seja conta
		if (!idTipoDocumento.equals(DocumentoTipo.CONTA.toString())) {
			if (idLocalidade != null && !idLocalidade.equalsIgnoreCase("")) {
				// [FS0003] - Verificar existência da localidade
				localidade = 
					this.getFachada().pesquisarLocalidadeDigitada(
						new Integer(idLocalidade));
			}
		}

		// Caso o imóvel tenha sido informado verificar se a localidade
		// informada é igual a do imóvel
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
			if (idLocalidade == null || idLocalidade.equalsIgnoreCase("")) {
				idLocalidade = "" + imovel.getLocalidade().getId();
			} else {
				// [FS0005] - Verificar localidade da matrícula do imóvel
				if (!this.getFachada().verificarLocalidadeMatriculaImovel(idLocalidade,imovel)) {
					
					throw new ActionServletException(
						"atencao.localidade_imovel_diferente", 
						imovel.getLocalidade().getId().toString(),
						idLocalidade);
				}
			}
		}

		// Recupera o tipo de débito
		String idTipoDebito = manterPagamentoActionForm.getIdTipoDebito();

		// Cria a variável que vai armazenar o tipo de débito
		DebitoTipo debitoTipo = null;

		// Caso nenhum tipo de documento informado, levanta uma exceção para o
		// usuário
		// indicando que o tipo de documento não foi informado
		if (idTipoDocumento == null || idTipoDocumento.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.naoinformado", 
				null,
				"Tipo de Documento");
		}
		
		//[FS0025] – Verificar valor do aviso bancário
        //Caso o valor calculado do aviso bancário seja maior que valor informado 
        if ((avisoBancario.getValorArrecadacaoCalculado().subtract(avisoBancario.getValorDevolucaoCalculado()))
        	.compareTo(avisoBancario.getValorArrecadacaoInformado().subtract(avisoBancario.getValorDevolucaoInformado())) == 1 ){
        	
        	throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
        }
        
		// Cria o objeto que vai armazenar o tipo de documento
		DocumentoTipo documentoTipo = new DocumentoTipo();

		// Caso o tipo de documento seja conta
		if (idTipoDocumento.equals(DocumentoTipo.CONTA.toString())) {
			// Seta o tipo de documento para conta
			documentoTipo.setId(new Integer(DocumentoTipo.CONTA));

			// Recupera a referência da conta
			String referenciaConta = 
				manterPagamentoActionForm.getReferenciaConta();

			// [FS0008] - Verificar existência da conta
			Conta conta = 
				this.getFachada().pesquisarContaDigitada(idImovel,referenciaConta);
			
			ContaHistorico contaHistorico = 
				this.getFachada().pesquisarContaHistoricoDigitada(idImovel,referenciaConta);

			// Caso a conta com a referência informada não esteja cadastrada
			// no sistema
			if (conta == null) {
				// Caso o usuário não tenha passado pela página de
				// confirmação
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

					httpServletRequest.setAttribute(
						"caminhoActionConclusao",
						"/gsan/atualizarPagamentosAction.do");

					// Monta a página de confirmação para perguntar se o
					// usuário quer atualizar
					// o pagamento mesmo sem a conta existir para a
					// referência e o imóvel informados
					return montarPaginaConfirmacao(
						"atencao.referencia.naocadastrada",
						httpServletRequest, 
						actionMapping,
						referenciaConta);
				}
			}

			// Formata a referência da conta para AAAAMM
			Integer anoMesReferencia = 
				new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta));

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para conta
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(anoMesReferencia);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(null);
			
			ContaGeral contaGeral = new ContaGeral();
			if (conta != null) {
				contaGeral.setId(conta.getId());
				contaGeral.setConta(conta);
			}else if( contaHistorico != null ){
				
				contaGeral.setId(contaHistorico.getId());
				contaGeral.setContaHistorico(contaHistorico);
				
			}
			
			pagamentoAtualizacao.setContaGeral(contaGeral);
			pagamentoAtualizacao.setGuiaPagamento(null);
			pagamentoAtualizacao.setDebitoACobrarGeral(null);
			pagamentoAtualizacao.setLocalidade(imovel.getLocalidade());
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(null);

			// Caso o tipo de documento seja guia de pagamento
		} else if (idTipoDocumento.equals(DocumentoTipo.GUIA_PAGAMENTO.toString())) {

			// Seta o tipo de documento para guia de pagamento
			documentoTipo.setId(new Integer(DocumentoTipo.GUIA_PAGAMENTO));

			// Recupera o código do cliente
			String idCliente = manterPagamentoActionForm.getIdCliente();

			// [FS0006] - Verificar preenchimento do imóvel e do cliente
			this.getFachada().verificarPreeenchimentoImovelECliente(idImovel,idCliente);

			// Caso o usuário tenha informado o cliente
			// Recupera o cliente informado do sistema
			Cliente cliente = null;
			if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
				// [FS0007] - Verificar existência do código do cliente
				cliente = 
					this.getFachada().pesquisarClienteDigitado(new Integer(idCliente));
			}

			// Recupera o código da guia de pagamento
			String idGuiaPagamento = manterPagamentoActionForm.getIdGuiaPagamento();

			// [FS0021] - Verificar preenchimento da guia de pagamento e do
			// tipo de débito
			this.getFachada().verificarPreeenchimentoGuiaPagamentoETipoDebito(
				idGuiaPagamento, idTipoDebito);

			// Caso o usuário informou a guia de pagamento
			// Recupera a guia de pagamento informada do sistema
			GuiaPagamento guiaPagamento = null;
			if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {

				// [FS0022] - Verificar existência da guia de pagamento
				guiaPagamento = 
					this.getFachada().pesquisarGuiaPagamentoDigitada(
						idImovel, 
						idCliente, 
						idGuiaPagamento);

				// Caso exista guia de pagamento, seta o tipo de débito da
				// guia de pagamento
				// para o tipo de débito do pagamento que vai ser inserido
				debitoTipo = guiaPagamento.getDebitoTipo();

				// [FS0010] - Verificar localidade da guia de pagamento
				this.getFachada().verificarLocalidadeGuiaPagamento(guiaPagamento,idLocalidade);
			}

			// Caso o tipo de débito tenha sido informado
			if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

				// [FS0020] - Verificar existência do tipo de débito
				debitoTipo = 
					this.getFachada().pesquisarTipoDebitoDigitado(
						new Integer(idTipoDebito));

				// [FS0009] - Verificar existência de guia de pagamento com
				// o tipo de débito informado
				if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
					
					guiaPagamento = 
						this.getFachada().verificarExistenciaGuiaPagamentoComTipoDebito(
							debitoTipo, 
							idImovel, 
							null);
				}

				if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
					
					guiaPagamento = 
						this.getFachada().verificarExistenciaGuiaPagamentoComTipoDebito(
							debitoTipo, 
							null, 
							idCliente);
				}

				// Caso não exista nenhuma guia de pagamento com o tipo de
				// débito informado
				if (guiaPagamento == null) {
					
					// Caso o usuário não tenha passado pela página de
					// confirmação do wizard
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

						// Monta a página de confirmação para perguntar se o
						// usuário quer inserir
						// o pagamento mesmo sem existir guia de pagamento
						// para o tipo de débito e o imóvel
						// ou cliente informados
						if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
							
							httpServletRequest.setAttribute(
								"caminhoActionConclusao",
								"/gsan/atualizarPagamentosAction.do");

							return montarPaginaConfirmacao(
								"atencao.guia_pagamento.naocadastrada",
								httpServletRequest, 
								actionMapping,
								debitoTipo.getDescricao(), 
								"Imóvel",
								idImovel);
						}
						return montarPaginaConfirmacao(
							"atencao.guia_pagamento.naocadastrada",
							httpServletRequest, 
							actionMapping,
							debitoTipo.getDescricao(), 
							"Cliente",
							idCliente);
					}
				}
			}

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para a guia de pagamento
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(null);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(debitoTipo);
			pagamentoAtualizacao.setContaGeral(null);
			pagamentoAtualizacao.setGuiaPagamento(guiaPagamento);
			pagamentoAtualizacao.setDebitoACobrarGeral(null);
			pagamentoAtualizacao.setLocalidade(localidade);
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(cliente);

			// Caso o tipo de documento seja débito a cobrar
		} else if (idTipoDocumento.equals(DocumentoTipo.DEBITO_A_COBRAR.toString())) {

			// Seta o tipo de documento do pagamento para débito a cobrar
			documentoTipo.setId(new Integer(DocumentoTipo.DEBITO_A_COBRAR));

			// Recupera o código do débito a cobrar
			String idDebitoACobrar = 
				manterPagamentoActionForm.getIdDebitoACobrar();

			// [FS0023] - Verificar preenchimento do débito a cobrar e do
			// tipo de débito
			this.getFachada().verificarPreeenchimentoDebitoACobrarETipoDebito(
					idDebitoACobrar, 
					idTipoDebito);
			
			BigDecimal valorInformado = 
				Util.formatarMoedaRealparaBigDecimal(
					manterPagamentoActionForm.getValorPagamento());
			
			// Caso o usuário informou o débito a cobrar
			// Recupera o débito a cobrar informado do sistema
			DebitoACobrarGeral debitoACobrarGeral = null;
			
			if (idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")) {

				// [FS0024] - Verificar existência do débito a cobrar
				debitoACobrarGeral = 
					this.getFachada().pesquisarDebitoACobrarGeralDigitado(
						idImovel, 
						idDebitoACobrar);

				if(debitoACobrarGeral.getDebitoACobrar() != null){

					// Caso exista o débito a cobrar, seta o tipo de débito do
					// débito a cobrar
					// para o tipo de débito do pagamento que vai ser inserido
					debitoTipo = debitoACobrarGeral.getDebitoACobrar().getDebitoTipo();
				
					//[FS0013] - Verificar localidade do débito a cobrar
					this.getFachada().verificarLocalidadeDebitoACobrarGeral(debitoACobrarGeral,idLocalidade);
				
					BigDecimal valorFaltaDebito = BigDecimal.ZERO;
					BigDecimal valorDebito = debitoACobrarGeral.getDebitoACobrar().getValorDebito();
					
					short numeroPrestacaoDebito = 
						debitoACobrarGeral.getDebitoACobrar().getNumeroPrestacaoDebito();
					
					short numeroPrestacaoCobrada = 
						debitoACobrarGeral.getDebitoACobrar().getNumeroPrestacaoCobradas();
			      
//					valorDebito = 
//						valorDebito.divide(new BigDecimal(numeroPrestacaoDebito+""));
					
					valorDebito = Util.dividirArredondando(valorDebito, new BigDecimal(numeroPrestacaoDebito+"") );
			      
					valorFaltaDebito = valorFaltaDebito.add(debitoACobrarGeral.getDebitoACobrar().getValorDebito());
					valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
					valorFaltaDebito = 
						valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));
		      
					if(!valorInformado.equals(valorFaltaDebito)){
						debitoACobrarGeral = null;
					}
				
				}else if(debitoACobrarGeral.getDebitoACobrarHistorico() !=null){
				
					// Caso exista o débito a cobrar, seta o tipo de débito do
					// débito a cobrar
					// para o tipo de débito do pagamento que vai ser inserido
					debitoTipo = debitoACobrarGeral.getDebitoACobrarHistorico().getDebitoTipo();
				
					//[FS0013] - Verificar localidade do débito a cobrar
					this.getFachada().verificarLocalidadeDebitoACobrarGeral(
						debitoACobrarGeral,idLocalidade);
				
					BigDecimal valorFaltaDebito = BigDecimal.ZERO;
					BigDecimal valorDebito = debitoACobrarGeral.getDebitoACobrarHistorico().getValorDebito();
					
					short numeroPrestacaoDebito = 
						debitoACobrarGeral.getDebitoACobrarHistorico().getPrestacaoDebito();
					short numeroPrestacaoCobrada = 
						debitoACobrarGeral.getDebitoACobrarHistorico().getPrestacaoCobradas();
			      
					valorDebito = valorDebito.divide(new BigDecimal(numeroPrestacaoDebito+""));
					valorFaltaDebito = valorFaltaDebito.add(debitoACobrarGeral.getDebitoACobrarHistorico().getValorDebito());
					valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
					valorFaltaDebito = valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));
		      
		      
					if(!valorInformado.equals(valorFaltaDebito)){
						debitoACobrarGeral = null;
					}
				
				}
			}
			
			// Caso o tipo de débito tenha sido informado
			if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

				// [FS0020] - Verificar existência do tipo de débito
				debitoTipo = 
					this.getFachada().pesquisarTipoDebitoDigitado(
						new Integer(idTipoDebito));

				// [FS0012] - Verificar existência de débito a cobrar com o
				// tipo de débito
				debitoACobrarGeral = 
					this.getFachada().verificarExistenciaDebitoACobrarGeralComTipoDebito(
						debitoTipo, 
						idImovel, 
						valorInformado);

				// Caso não exista nenhum débito a cobrar com o tipo de
				// débito informado
				if (debitoACobrarGeral.getDebitoACobrar() == null && 
					debitoACobrarGeral.getDebitoACobrarHistorico() == null) {
					
					// Caso o usuário não tenha passado pela página de
					// confirmação do wizard
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
						
						httpServletRequest.setAttribute(
							"caminhoActionConclusao",
							"/gsan/atualizarPagamentosAction.do");

						// Monta a página de confirmação do wizard para
						// perguntar se o usuário quer inserir
						// o pagamento mesmo sem existir débito a cobrar
						// para o tipo de débito e o imóvel informados
						return montarPaginaConfirmacao(
							"atencao.debito_a_cobrar.naocadastrado",
							httpServletRequest, 
							actionMapping,
							debitoTipo.getDescricao(), idImovel);
					}
				}
			}
			

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para o débito a cobrar
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(null);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(debitoTipo);
			pagamentoAtualizacao.setContaGeral(null);
			pagamentoAtualizacao.setGuiaPagamento(null);
			
			//DebitoACobrarGeral debitoACobrarGeral = null;
			if(debitoACobrarGeral != null){
				if(debitoACobrarGeral.getDebitoACobrar() != null){
					debitoACobrarGeral.setId(debitoACobrarGeral.getDebitoACobrar().getId());
				}else if(debitoACobrarGeral.getDebitoACobrarHistorico() != null){
					debitoACobrarGeral.setId(debitoACobrarGeral.getDebitoACobrarHistorico().getId());
				}
			}
			
			pagamentoAtualizacao.setDebitoACobrarGeral(debitoACobrarGeral);
			
			pagamentoAtualizacao.setLocalidade(localidade);
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(null);
		}

		this.getFachada().atualizarPagamento(pagamentoAtualizacao);

		// Alterado por Sávio Luiz data:16/03/2007
		BigDecimal valorArrecadacaoCalculado = avisoBancario.getValorArrecadacaoCalculado();
		valorArrecadacaoCalculado = valorArrecadacaoCalculado.subtract(valorPagamentoAnteriror);
		valorArrecadacaoCalculado = valorArrecadacaoCalculado.add(valorPagamentoNovo);

		avisoBancario.setValorArrecadacaoCalculado(valorArrecadacaoCalculado);
		avisoBancario.setUltimaAlteracao(new Date());
		
		this.getFachada().atualizar(avisoBancario);

		// Caso o retorno seja para a tela de sucesso,
		// Monta a tela de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(httpServletRequest,
					"Pagamento atualizado com sucesso.",
					"Realizar outra Manutenção de Pagamento",
					"exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim");

		}

		// Retorna o mapeamento contido na variável retorno
		return retorno;
	}
	

}
