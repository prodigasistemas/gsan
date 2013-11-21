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

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos débitos de um imóvel
 * 
 * [UC0214] Efetuar Parcelamento de Débitos
 * 
 * Pré-processamento da terceira página
 * 
 * @author Roberta Costa
 * @date 10/03/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso3Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo3");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;

		// Faz os cálculos quando a entrada for modificada
		String calculaOpcaoParcelamento = null;
		if( httpServletRequest.getParameter("calculaOpcaoParcelamento") != null ){
			calculaOpcaoParcelamento = httpServletRequest.getParameter("calculaOpcaoParcelamento");
		}else if(sessao.getAttribute("calculaOpcaoParcelamento") != null ){
			calculaOpcaoParcelamento = (String) sessao.getAttribute("calculaOpcaoParcelamento");
		}
		
		// Pega variáveis do formulário
		String codigoImovel = (String) efetuarParcelamentoDebitosActionForm.get("matriculaImovel");
		String codigoImovelAntes = (String) efetuarParcelamentoDebitosActionForm.get("codigoImovelAntes");
		Integer situacaoAguaId = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("situacaoAguaId"));
		Integer situacaoEsgotoId = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId"));
		Integer perfilImovel = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("perfilImovel"));
		Integer numeroReparcelamentoConsecutivos = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("numeroReparcelamentoConsecutivos"));
		String dataParcelamento = (String)(efetuarParcelamentoDebitosActionForm.get("dataParcelamento"));
		String resolucaoDiretoria = (String) efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria");
		String inicioIntervaloParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		String indicadorContasRevisao = (String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar");
		String valorDebitoACobrarParcelamentoImovel =( (String)efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoImovel"));
		String indicadorDividaAtiva = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva");
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		
		String bloqueiaIntervaloParcelamento = (String) sessao.getAttribute("bloqueiaIntervaloParcelamento");
		
		Integer fimIntervaloParcelamentoFormatado = null;
		
		Integer inicioIntervaloParcelamentoFormatado = null;
		
		if (bloqueiaIntervaloParcelamento != null 
				&& bloqueiaIntervaloParcelamento.equalsIgnoreCase("nao")){	
			fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		}
		
		if (valorDebitoACobrarParcelamentoImovel != null){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
			
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer(indicadorContasRevisao));
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer(indicadorDebitosACobrar));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer(indicadorCreditoARealizar));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer(indicadorGuiasPagamento));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(indicadorAcrescimosImpotualidade));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(indicadorDividaAtiva));
		
		Boolean indicadorContas = true;
		//se o intervalo de parcelamento estiver igual a null
		//não se deve levar em consideração no parcelamento a coleão de contas 
		if ((inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals(""))
				&& (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals(""))){
			indicadorContas = false;
		}
		
		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		
		// Valor Entrada Informado
		BigDecimal valorEntradaInformado = null;
		BigDecimal valorEntradaInformadoAntes = new BigDecimal("0.00");
		//*********************************************************
		// Por: Iavn Sergio
		// 01/06/2010
		// CRC4062
		//*********************************************************
		if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado") != null 
				&& efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("")) {
			valorEntradaInformado = BigDecimal.ZERO;
			efetuarParcelamentoDebitosActionForm.set("valorEntradaInformado",Util.formatarMoedaReal(valorEntradaInformado));
		}
		//*********************************************************
		if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("")
					&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("0.00")){
			
			if (efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("0") ){
				valorEntradaInformado = BigDecimal.ZERO;
				efetuarParcelamentoDebitosActionForm.set("valorEntradaInformado",Util.formatarMoedaReal(valorEntradaInformado));
			}else{
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado"));
			}
			
		}
		if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes").equals("")
					&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes").equals("0.00")){
			valorEntradaInformadoAntes = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes"));
		}
		
		// O indicador só será usado caso a situação de Água do Imóvel seja
		// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
		Integer indicadorRestabelecimento = new Integer("0");
		if (efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento") != null
				&& !efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento").equals("")) {
			indicadorRestabelecimento = new Integer(String.valueOf(efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento")));
		}

		
		//Caso o periodo inicial do intervalo do parcelamento não seja informado
		if (inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("")){
			inicioIntervaloParcelamento = "01/0001";
		}
		
		// Caso o periodo final do intervalo do parcelamento não seja informado
		if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")){
			fimIntervaloParcelamento = "12/9999";
		}
				
		// Verifica se a chamada é pela aba 2(colecaoContaValores) ou pela aba 1(colecaoContaValoresImovel)
		Collection<ContaValoresHelper> colecaoContaValoresNegociacao = null;
		Collection<GuiaPagamento> colecaoGuiaPagamento = null;
		BigDecimal valorDebitoTotalAtualizado = new BigDecimal("0.00");
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValoresNegociacao = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			colecaoGuiaPagamento = (Collection<GuiaPagamento>) sessao.getAttribute("colecaoGuiaPagamentoValores");
			valorDebitoTotalAtualizado = BigDecimal.ZERO;
			
			if (!((String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado")).equals("")){
				
				valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado"));
				
			}
			
			valorAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorAcrescimosImpontualidade"));
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal((String)  efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar"));

		} 
		else {
			// Pesquisa os débitos do imóvel
			Object[] debitosImovel = fachada.pesquisarDebitosImovel(codigoImovel, codigoImovelAntes,
					dataParcelamento, resolucaoDiretoria, fimIntervaloParcelamento, 
					inicioIntervaloParcelamento , indicadorContasRevisao, 
					indicadorGuiasPagamento, indicadorAcrescimosImpotualidade,
					indicadorDebitosACobrar, indicadorCreditoARealizar,indicadorContas, indicadorDividaAtiva);
			
			// Valores dos indíces definidos no controlador
			colecaoContaValoresNegociacao = (Collection) debitosImovel[0];
			colecaoGuiaPagamento = (Collection) debitosImovel[2];
			//valorDebitoTotalAtualizado = (BigDecimal) debitosImovel[14];

			//Obter todo o débito do imóvel para exibição na ABA 4(Conclusão)
			//ou para inserir a partir da aba 3 
			ObterDebitoImovelOuClienteHelper colecaoDebitoCliente = fachada
					.obterDebitoImovelOuCliente(
							1, // Indicador de débito do imóvel
							codigoImovel, // Matrícula do imóvel
							null, // Código do cliente
							null, // Tipo de relação cliente imóvel
							Util.formatarMesAnoParaAnoMesSemBarra(inicioIntervaloParcelamento), // Referência inicial do débito
							Util.formatarMesAnoParaAnoMesSemBarra(fimIntervaloParcelamento), // Fim do débito
							Util.converteStringParaDate("01/01/0001"), // Inicio vencimento
							Util.converteStringParaDate("31/12/9999"), // Fim vencimento
							1, // Indicador de pagamento
							new Integer(indicadorContasRevisao), //  conta em revisão
							new Integer(indicadorDebitosACobrar), // Débito a cobrar
							new Integer(indicadorCreditoARealizar), // crédito a realizar
							1, // Indicador de notas promissórias
							new Integer(indicadorGuiasPagamento), //guias pagamento
							new Integer(indicadorAcrescimosImpotualidade), // acréscimos impontualidade
							indicadorContas, new Integer(indicadorDividaAtiva));

			// Para o cálculo do Débito Total Atualizado
			BigDecimal valorTotalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
			BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
			BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
			BigDecimal valorJurosMora = new BigDecimal("0.00");
			BigDecimal valorMulta = new BigDecimal("0.00");
			
			// Dados do Débito do Imóvel - Contas
			Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoCliente.getColecaoContasValores();
			
			ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");
			//[SB0011] Verificar Única Fatura
			fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);

			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				
				int quantidadeMinimaMesesAntiguidade = 0;
				int maiorQuantidadeMinimaMesesAntiguidade = 0;
				Iterator contaValores = colecaoContaValores.iterator();
				
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper)contaValores.next();
					
					//Colocado por Raphael Rossiter em 04/12/2008
					//=============================================================================================
					Collection<ParcelamentoDescontoAntiguidade> colecaoParcelamentoDescontoAntiguidade = 
					fachada.obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil,
					contaValoresHelper.getConta());

					ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

					// Caso nenhuma ocorrência tenha sido selecionada passar para a próxima conta
					if (colecaoParcelamentoDescontoAntiguidade != null && 
						!colecaoParcelamentoDescontoAntiguidade.isEmpty()) {
						
						Iterator parcelamentoDescontoAntiguidadeValores = colecaoParcelamentoDescontoAntiguidade
						.iterator();

						quantidadeMinimaMesesAntiguidade = 0;
						maiorQuantidadeMinimaMesesAntiguidade = 0;

						// 2.4 Determina o percentual de desconto por antiguidade do débito
						while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
							ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores
									.next();
							quantidadeMinimaMesesAntiguidade = parcelamentoDescontoAntiguidade
									.getQuantidadeMinimaMesesDebito();
							if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
								maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
								parcelamentoDescontoAntiguidadeMaior = parcelamentoDescontoAntiguidade;
							}
						}
						
						/*
						 * Colocado por Raphael Rossiter em 03/12/2008
						 * As contas onde o perfil de parcelamento para desconto de antiguidade estiver com
						 * o motivo de revisão informado NÃO entrarão no parcelamento.
						 */
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());
						
						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
						
						if (parcelamentoDescontoAntiguidadeMaior.getContaMotivoRevisao() != null){
							
							//CONTA ENTRARÁ EM REVISÃO
							contaValoresHelper.setRevisao(1);
							
						}
					}
					else{
						
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
					//=============================================================================================
				}
				
				efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", Util.formatarMoedaReal(valorTotalContas));

				sessao.setAttribute("valorTotalContaValores",valorTotalContas);
				
				// Pega os dados do Débito do Cliente
				if( sessao.getAttribute("colecaoContaValores") == null ){
					sessao.setAttribute("colecaoContaValores",colecaoContaValores);
				}	
			} 
			else {
				
				efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", "0,00");
				
				sessao.setAttribute("valorTotalContaValores",valorTotalContas);
			}


			// Guias de Pagamento
			if( indicadorGuiasPagamento.equals("1") ){
				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoCliente.getColecaoGuiasPagamentoValores();
				if (colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty() ){
					Iterator guiaPagamentoValores = colecaoGuiaPagamentoValores.iterator();
					while (guiaPagamentoValores.hasNext()) {
						GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();
						valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
						
						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias.add(guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade());
					}
					efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento",Util.formatarMoedaReal(valorTotalGuiasPagamento));

					// Pega as Guias de Pagamento em Débito
					sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);
				} else {
					efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento", "0,00");
				}
			}else{
				efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento", "0,00");
			}
			
			// Acrescimos por Impotualidade
			BigDecimal retornoSoma = new BigDecimal("0.00");
			if( indicadorAcrescimosImpotualidade.equals("1") ){
				retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);

				efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade", Util.formatarMoedaReal(retornoSoma));
				sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);
			}else{
				efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade", "0,00");
				sessao.setAttribute("valorAcrescimosImpontualidade", new BigDecimal("0.00"));
			}

			efetuarParcelamentoDebitosActionForm.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
			efetuarParcelamentoDebitosActionForm.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
			efetuarParcelamentoDebitosActionForm.set("valorMulta", Util.formatarMoedaReal(valorMulta));
			
			// Para o cálculo do Débito Total Atualizado
			valorTotalAcrescimoImpontualidade = retornoSoma;
			valorAcrescimosImpontualidade = valorTotalAcrescimoImpontualidade;

			
			
			
			
			
			// Debitos A Cobrar
			if( indicadorDebitosACobrar.equals("1") ){
				//[FS0022]-Verificar existência de juros sobre imóvel
				Collection colecaoDebitoACobrar = colecaoDebitoCliente.getColecaoDebitoACobrar();
				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
	
					final int indiceCurtoPrazo = 0;
					final int indiceLongoPrazo = 1;
	
					while (debitoACobrarValores.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
						
						//[FS0022]-Verificar existência de juros sobre imóvel
						if(debitoACobrar.getDebitoTipo().getId() != null && 
								!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					
							//Debitos A Cobrar - Serviço
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
		
								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
								debitoACobrar.getNumeroPrestacaoDebito(),debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),valorRestanteACobrar);
								valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								
								valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}
		
							// Debitos A Cobrar - Parcelamento
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
		
								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
								debitoACobrar.getNumeroPrestacaoDebito(),debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),valorRestanteACobrar);
								
								valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								
								valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}
		
							
						}
						
						
					}
	
					sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
	
					// Serviços
					valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo.add(valorTotalRestanteServicosACobrarLongoPrazo);
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
					// Parcelamentos
					valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", "0,00");
					
					// Alterado por Rafael Corrêa
					// Data: 26/08/2009
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
				}
			}else{
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", "0,00");
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", "0,00");
				
				// Alterado por Rafael Corrêa
				// Data: 26/08/2009
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
				efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
			}
			
			// Crédito A Realizar
			if( indicadorCreditoARealizar.equals("1") ){
				Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoCliente.getColecaoCreditoARealizar();
				if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty() ) {
					Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
					while (creditoARealizarValores.hasNext()) {
						CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
						valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
					}
					sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar",Util.formatarMoedaReal(valorCreditoARealizar));
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
				}
			}else{
				efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
			}	
			
			// Débito Total Atualizado
//			BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");
			valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalContas);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalGuiasPagamento);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorCreditoARealizar);
			
		}
		
		// Variáveis
		BigDecimal valorTotalMultas = new BigDecimal("0.00");
		BigDecimal valorTotalJurosMora = new BigDecimal("0.00");
		BigDecimal valorTotalAtualizacoesMonetarias = new BigDecimal("0.00");
		BigDecimal descontoAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal descontoAntiguidadeDebito = new BigDecimal("0.00");
		BigDecimal descontoInatividadeLigacaoAgua = new BigDecimal("0.00");
		BigDecimal percentualDescontoAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal percentualDescontoAntiguidadeDebito = new BigDecimal("0.00");
		BigDecimal percentualDescontoInatividadeLigacaoAgua = new BigDecimal("0.00");
		//BigDecimal valorDescontoPagamentoAVista =  new BigDecimal("0.00");
		BigDecimal valorPagamentoAVista = new BigDecimal("0.00");
		ParcelamentoPerfil parcelamentoPerfil = null;
		BigDecimal valorMinimoPrestacao = new BigDecimal("0.00");
		BigDecimal valorTotalImpostosConta = new BigDecimal("0.00");
		BigDecimal descontoSancoesRDEspecial = new BigDecimal("0.00");
		BigDecimal descontoTarifaSocialRDEspecial = new BigDecimal("0.00");
		BigDecimal descontoTotalPagamentoAVista = new BigDecimal("0.00");
		
		// Quando não é pelo botão Calcular
		if ( calculaOpcaoParcelamento == null ){
			// Verifica se alguma EP foi marcada
			boolean marcadaEP = false;
			//boolean marcadaNB = false;
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();
					if( contaValoresHelperNegociacao.getIndicadorContasDebito() != null ){
						if( contaValoresHelperNegociacao.getIndicadorContasDebito().equals(new Integer("1")) ){
							marcadaEP = true;
							sessao.removeAttribute("calculaNegociacao");
						}else if( contaValoresHelperNegociacao.getIndicadorContasDebito().equals(new Integer("2")) ){
							//marcadaNB = true;
							sessao.removeAttribute("calculaNegociacao");
						}
					}
				}
			}	
			
			BigDecimal valorEntradaDebitos = new BigDecimal("0.00"); // Valor de Entrada da Aba 2
			if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado") != null
					&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("") && !marcadaEP ){
				valorEntradaDebitos = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado"));
			}else{
				valorEntradaDebitos = (BigDecimal) sessao.getAttribute("valorEntradaMinima");	
			}
			
			// Caso não esteja marcada EP e não tenha sido calculado 
			if( !marcadaEP && sessao.getAttribute("calculaNegociacao") == null ){
				valorEntradaInformado = null; //new BigDecimal("0.00"); 
			// Caso tenha EP marcada e seja valor diferente do anterior	
			}else if( !( valorEntradaDebitos.compareTo(valorEntradaInformadoAntes) == 0) ){
				valorEntradaInformado = (BigDecimal) sessao.getAttribute("valorEntradaMinima");	
			}
			
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();

					// Caso tenha vindo da aba 2
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso não tenha marcado a conta
						if (contaValoresHelperNegociacao.getIndicadorContasDebito() == null ){
							if (contaValoresHelperNegociacao.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
							}
							if (contaValoresHelperNegociacao.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
							}
							if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
							}
						} 
					} else {
						// Caso tenha vindo direto da aba 1
						if (contaValoresHelperNegociacao.getValorMulta() != null) {
							valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
						}
						if (contaValoresHelperNegociacao.getValorJurosMora() != null) {
							valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
						}
						if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
							valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
									.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
						}
					}
				}
			} 
			
			// Caso o início do intervalo seja vazio preencher com o padrão da aba 1
			if( inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("") ){
				inicioIntervaloParcelamento = "01/0001";
			}

			
			//[SB0002] - Obter Opções de Parcelamento
			
			//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar);
			
			Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento =  null;
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento =  null;
			
			opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
			
			// [SB0002] - Obter Opções de Parcelamento
			/*opcoesParcelamento = fachada.obterOpcoesDeParcelamento(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado,
			situacaoAguaId, situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento,
			indicadorRestabelecimento, colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, 
			valorTotalMultas, valorTotalJurosMora, valorTotalAtualizacoesMonetarias,
			numeroReparcelamentoConsecutivos, colecaoGuiaPagamento,usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper);*/
			
			colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
			
			// Verifica se alguma opção de parcelamento foi marcada
			if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					if(efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas") != null ){
						if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas")).equals(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()) ){
							opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(new Short(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()));
						}
					}	
				}
			}
			
			// Colocando na sessão
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);
			
			descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
			descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
			descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
			descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
			descontoTotalPagamentoAVista  = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
			percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
			percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
			percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
			parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
			
			
			// Inicia o valor da Entrada com a Miníma caso não tenha marcardo nenhuma conta
			if( marcadaEP ){
				efetuarParcelamentoDebitosActionForm.set("valorEntradaInformado", Util.formatarMoedaReal(valorEntradaInformado));
				efetuarParcelamentoDebitosActionForm.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaInformado));
			}else{
				if( (valorEntradaInformado != null && ! (valorEntradaMinima.compareTo(valorEntradaInformado) == 0))
						&& sessao.getAttribute("calculaNegociacao") != null ){
					valorEntradaMinima = valorEntradaInformado;
				}
				efetuarParcelamentoDebitosActionForm.set("valorEntradaInformado", Util.formatarMoedaReal(valorEntradaMinima));
				efetuarParcelamentoDebitosActionForm.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaMinima));
			}
		
		// Caso tenha acionado o botão calcular da aba 3
		}else{
			// Ver uma maneira de guardar o valor da entrada quando acionar o botão calcular
			sessao.setAttribute("calculaNegociacao", "1");
			
			// Verifica se a entrada informada é menor que a mínima 
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso não tenha marcado a conta
						if (contaValoresHelperNegociacao.getIndicadorContasDebito() == null) {
							if (contaValoresHelperNegociacao.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
							}
							if (contaValoresHelperNegociacao
									.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
							}
							if (contaValoresHelperNegociacao
									.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
							}
						}
					}
				}
			}

			// Limpando as opções da sessão
			sessao.removeAttribute("opcoesParcelamento");
			sessao.removeAttribute("colecaoOpcoesParcelamento");

			//[SB0002] - Obter Opções de Parcelamento de acordo com a entrada informada
			
			//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar);
			
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
			
			// [SB0002] - Obter Opções de Parcelamento
			/*opcoesParcelamento = fachada.obterOpcoesDeParcelamento(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado,
			situacaoAguaId, situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento,
			indicadorRestabelecimento, colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, 
			valorTotalMultas, valorTotalJurosMora, valorTotalAtualizacoesMonetarias,
			numeroReparcelamentoConsecutivos, colecaoGuiaPagamento,usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper);*/

			descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade();
			descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
			descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
			descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
			descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
			descontoTotalPagamentoAVista = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
			percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
			percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
			percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
			parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
			
			Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
			
			if (valorEntradaInformado != null && valorEntradaInformado.compareTo(
					valorEntradaMinima.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1) {
				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoValMinimoEntrada = fachada.verificarPermissaoValMinimoEntrada(usuario);
				// -----------------------------------------------------------
				if(!temPermissaoValMinimoEntrada){
					throw new ActionServletException(
							"atencao.valor.entrada.deve.ser.maior.igual.minima", null, Util.formatarMoedaReal(valorEntradaMinima));
				}else{
					valorEntradaMinima = valorEntradaInformado;
				}
				
			}else{
				valorEntradaMinima = valorEntradaInformado;
			}
			
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);
			
			// Limpa os EP da Coleção de Contas
			if(colecaoContaValoresNegociacao !=null && !colecaoContaValoresNegociacao.isEmpty()){
				Iterator contaValores = colecaoContaValoresNegociacao.iterator();
				while(contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (contaValoresHelper.getIndicadorContasDebito() != null && !contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2"))){
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}
			
			// Limpando a opção de parcelamento
			if( colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.equals("") ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(null);
					efetuarParcelamentoDebitosActionForm.set("indicadorQuantidadeParcelas", null);
				}
			}
			
			// Atribui ao hidden o valor da entrada digitado
			efetuarParcelamentoDebitosActionForm.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaInformado));
		}
		
		// Verifica se o valor do débito menos o valor dos descontos é menor que o valor minimo da parcela
		BigDecimal valorTotalDescontos = new BigDecimal("0.00");
		BigDecimal resultadoDiferenca = new BigDecimal("0.00");
		valorTotalDescontos.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorTotalDescontos = descontoAcrescimosImpontualidade.add(descontoAntiguidadeDebito);
		valorTotalDescontos = valorTotalDescontos.add(descontoInatividadeLigacaoAgua);
		valorTotalDescontos = valorTotalDescontos.add(descontoSancoesRDEspecial);
		valorTotalDescontos = valorTotalDescontos.add(descontoTarifaSocialRDEspecial);
		
		resultadoDiferenca.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		resultadoDiferenca = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);

		
		if (!fachada.verificarQtdeReparcelamentoPerfil(parcelamentoPerfil.getId(),new Short(numeroReparcelamentoConsecutivos.shortValue()))){
			throw new ActionServletException("atencao.nao.existe.condicao.por.quantidade.reparcelamentos.perfil");
		}
		
		// Coloca os valores no formulário
		efetuarParcelamentoDebitosActionForm.set("descontoAcrescimosImpontualidade", Util.formatarMoedaReal(descontoAcrescimosImpontualidade));
		efetuarParcelamentoDebitosActionForm.set("descontoAntiguidadeDebito",Util.formatarMoedaReal(descontoAntiguidadeDebito));
		efetuarParcelamentoDebitosActionForm.set("descontoInatividadeLigacaoAgua", Util.formatarMoedaReal(descontoInatividadeLigacaoAgua));
		efetuarParcelamentoDebitosActionForm.set("percentualDescontoAcrescimosImpontualidade", Util.formatarMoedaReal(percentualDescontoAcrescimosImpontualidade));
		efetuarParcelamentoDebitosActionForm.set("valorTotalDescontos", Util.formatarMoedaReal(valorTotalDescontos));
		efetuarParcelamentoDebitosActionForm.set("descontoSancoesRDEspecial",Util.formatarMoedaReal(descontoSancoesRDEspecial));
		efetuarParcelamentoDebitosActionForm.set("descontoTarifaSocialRDEspecial",Util.formatarMoedaReal(descontoTarifaSocialRDEspecial));
		
		/*if(parcelamentoPerfil.getPercentualDescontoAVista() != null){
			valorDescontoPagamentoAVista = valorDescontoPagamentoAVista.add(descontoPagamentoAVistaRDEspecial);
			valorDescontoPagamentoAVista = valorDescontoPagamentoAVista.add(valorTotalDescontos);
		}else{
			// Valor desconto para pagamento à vista (maior valor entre o valor total dos descontos e
			// o valor dos acréscimos por impontualidade)
			if(valorTotalDescontos.compareTo(valorAcrescimosImpontualidade) == 1){
				valorDescontoPagamentoAVista = valorTotalDescontos;
			}else{
				valorDescontoPagamentoAVista = valorAcrescimosImpontualidade;
			}
		}*/
		
		valorTotalImpostosConta = obterValorImpostosDasContasDoParcelamento(colecaoContaValoresNegociacao);
		
		valorPagamentoAVista.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(descontoTotalPagamentoAVista);

		valorPagamentoAVista = valorPagamentoAVista.subtract(valorTotalImpostosConta);
        
//        if (valorPagamentoAVista.compareTo(BigDecimal.ZERO)< 1){
//            throw new ActionServletException(
//                    "atencao.cliente_debito_zerado", null);
//        }
		
		// Colocando os valores no formulário
		efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado",Util.formatarMoedaReal(valorDebitoTotalAtualizado));
		efetuarParcelamentoDebitosActionForm.set("valorDescontoPagamentoAVista",Util.formatarMoedaReal(descontoTotalPagamentoAVista));
		efetuarParcelamentoDebitosActionForm.set("valorPagamentoAVista",Util.formatarMoedaReal(valorPagamentoAVista));
		efetuarParcelamentoDebitosActionForm.set("valorTotalImpostos",Util.formatarMoedaReal(valorTotalImpostosConta));
		
		if (percentualDescontoAntiguidadeDebito != null) {
			efetuarParcelamentoDebitosActionForm.set("percentualDescontoAntiguidadeDebito", Util.formatarMoedaReal(percentualDescontoAntiguidadeDebito));
		} else {
			efetuarParcelamentoDebitosActionForm.set("percentualDescontoAntiguidadeDebito", "0.00");
		}

		if (percentualDescontoInatividadeLigacaoAgua != null) {
			efetuarParcelamentoDebitosActionForm.set("percentualDescontoInatividadeLigacaoAgua", Util
					.formatarMoedaReal(percentualDescontoInatividadeLigacaoAgua));
		} else {
			efetuarParcelamentoDebitosActionForm.set("percentualDescontoInatividadeLigacaoAgua", "0.00");
		}

		if (parcelamentoPerfil != null) {
			efetuarParcelamentoDebitosActionForm.set("parcelamentoPerfilId",parcelamentoPerfil.getId().toString());
		} else {
			efetuarParcelamentoDebitosActionForm.set("parcelamentoPerfilId", "0");
		}
		
		// O valor do débito é menor que o valor da parcela mínima permitida.
		// Utilizar a opção Pagamento à Vista. 
		if (valorDebitoTotalAtualizado.compareTo(valorMinimoPrestacao) == -1){
			httpServletRequest.setAttribute("vlDebitoMenor","1");
		}
		efetuarParcelamentoDebitosActionForm.set("valorMinimoPrestacao", Util.formatarMoedaReal(valorMinimoPrestacao));
		
		String habilitaPagamentoAVista = "1";
		if (colecaoContaValoresNegociacao == null || colecaoContaValoresNegociacao.isEmpty()) {
			habilitaPagamentoAVista = "2";
		}
		sessao.setAttribute("habilitaPagamentoAVista", habilitaPagamentoAVista);
		
		sessao.setAttribute("colecaoContaValoresNegociacao",colecaoContaValoresNegociacao);
		sessao.setAttribute("colecaoGuiaPagamentoNegociacao", colecaoGuiaPagamento);
		sessao.setAttribute("valorAcrescimosImpontualidadeNegociacao", valorAcrescimosImpontualidade);
		
		
		return retorno;
	}
	

	private BigDecimal obterValorImpostosDasContasDoParcelamento(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
}