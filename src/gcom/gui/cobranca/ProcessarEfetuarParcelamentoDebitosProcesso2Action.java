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
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ProcessarEfetuarParcelamentoDebitosProcesso2Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo2");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		Integer indicadorGuiasPagamento = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento")));
		Integer indicadorDebitosACobrar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar")));
		Integer indicadorCreditoARealizar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar")));
		String valorDebitoACobrarParcelamentoImovel =( (String)efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoImovel"));
		String valorCreditoARealizar =( (String)efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar"));
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm
		.get("indicadorAcrescimosImpotualidade");
		Integer indicadorContasRevisao = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao"));
		Integer indicadorDividaAtiva = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva"));
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		
		
		String inicioIntervaloParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		Integer fimIntervaloParcelamentoFormatado = null;
		Integer inicioIntervaloParcelamentoFormatado = null;
		if(inicioIntervaloParcelamento !=null && !inicioIntervaloParcelamento.equalsIgnoreCase("")){
			fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		}
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(indicadorContasRevisao);
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(indicadorDebitosACobrar);
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(indicadorCreditoARealizar);
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(indicadorGuiasPagamento);
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(indicadorAcrescimosImpotualidade));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(indicadorDividaAtiva));
		
		
		if (valorDebitoACobrarParcelamentoImovel != null && !valorDebitoACobrarParcelamentoImovel.equals("") ){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
		// Cria um boolean para verificar se todos os radio buttons do jsp anterior
		// foi escolhido, caso seja então não deixa passar para a aba 3.
		boolean verificaRadioButton = true;

		// Armazena a Coleção de Contas
		BigDecimal valorEntradaParcelamento = new BigDecimal("0.00");
		boolean marcadaEP = false;
		boolean marcadaNB = false;

		Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
		
		// Guias de Pagamento
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoDebito = null;
		if( indicadorGuiasPagamento.equals(new Integer("1")) ){
			colecaoGuiaPagamentoDebito = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
		}
		// Debitos A Cobrar
		Collection<DebitoACobrar> colecaoDebitoACobrarDebito = null; 
		if( indicadorDebitosACobrar.equals(new Integer("1")) ){
			colecaoDebitoACobrarDebito = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoContaValores");
		}	

		// Verifica se as contas foram marcadas
		Collection<Integer> idsContaEP = new ArrayList<Integer>();
		
		Collection<ContaValoresHelper> colecaoContaValoresSemContasNB = new ArrayList<ContaValoresHelper>();
		
		if( colecaoContaValores != null && ! colecaoContaValores.isEmpty() ){
			Iterator contaValores = colecaoContaValores.iterator();
			while(contaValores.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
				if( httpServletRequest.getParameter("indicadorContasDebito"+
						contaValoresHelper.getConta().getId().toString()) != null ){
					String indice = httpServletRequest.getParameter("indicadorContasDebito"+contaValoresHelper.getConta().getId().toString());
					contaValoresHelper.setIndicadorContasDebito(new Integer(indice));
					
					// Verifica se a conta foi marcada como EP
					if( contaValoresHelper.getIndicadorContasDebito().equals(new Integer("1")) ){
						valorEntradaParcelamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorEntradaParcelamento = valorEntradaParcelamento
							.add(contaValoresHelper.getValorTotalConta());
						marcadaEP = true;
						idsContaEP.add(contaValoresHelper.getConta().getId());
						colecaoContaValoresSemContasNB.add(contaValoresHelper);
					// Verifica se as contas foram marcadas como NB	
					}else if( contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2")) ){
						marcadaNB = true;
						if( contaValoresHelper.getConta().getDataRevisao() != null ){
							throw new ActionServletException(
									"atencao.conta.em.revisao");
						}
					}
				}else{
					verificaRadioButton = false;
					contaValoresHelper.setIndicadorContasDebito(null);
					colecaoContaValoresSemContasNB.add(contaValoresHelper);
				}
			}
			
			// Verifica se tem algum débito a parcelar de acordo com as opções marcadas
			if(verificaRadioButton 
				&& indicadorGuiasPagamento.equals(new Integer("1"))
				&& indicadorCreditoARealizar.equals(new Integer("1"))
				&& ( ( colecaoGuiaPagamentoDebito == null || colecaoGuiaPagamentoDebito.equals("") )
				|| ( colecaoDebitoACobrarDebito == null || colecaoDebitoACobrarDebito.equals("") ) )){
				throw new ActionServletException(
						"atencao.nao.efetuar.parcelamento");
			}
		}
		
		sessao.setAttribute("idsContaEP",idsContaEP);
		sessao.setAttribute("colecaoContaValoresSemContasNB" , colecaoContaValoresSemContasNB);
		
		// Verifica se as entradas escolhidas são menores que a entrada minima
		if( marcadaEP ){
			// Pega variáveis do formulário
			String codigoImovel = (String) 
				efetuarParcelamentoDebitosActionForm.get("matriculaImovel");
			Integer situacaoAguaId = new Integer( (String) 
				efetuarParcelamentoDebitosActionForm.get("situacaoAguaId"));
			Integer situacaoEsgotoId = new Integer( (String) 
				efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId"));
			Integer perfilImovel = new Integer( (String) 
				efetuarParcelamentoDebitosActionForm.get("perfilImovel"));
			Integer numeroReparcelamentoConsecutivos = new Integer( (String) 
				efetuarParcelamentoDebitosActionForm.get("numeroReparcelamentoConsecutivos"));
			String resolucaoDiretoria = (String) 
				efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria");
			
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao
				.getAttribute("valorDebitoTotalAtualizado");

			// O indicador só será usado caso a situação de Água do Imóvel seja
			// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
			Integer indicadorRestabelecimento = new Integer("0");
			if (efetuarParcelamentoDebitosActionForm
					.get("indicadorRestabelecimento") != null
					&& !efetuarParcelamentoDebitosActionForm.get(
							"indicadorRestabelecimento").equals("")) {
				indicadorRestabelecimento = new Integer(String
						.valueOf(efetuarParcelamentoDebitosActionForm
								.get("indicadorRestabelecimento")));
			}
	
			BigDecimal valorTotalMultas = 
				new BigDecimal("0.00");
			BigDecimal valorTotalJurosMora = 
				new BigDecimal("0.00");
			BigDecimal valorTotalAtualizacoesMonetarias = 
				new BigDecimal("0.00");
			
			Collection<GuiaPagamento> colecaoGuiaPagamento = (Collection<GuiaPagamento>) sessao
				.getAttribute("colecaoGuiaPagamentoValores");
			
			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				Iterator contaValores = colecaoContaValores.iterator();
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = 
						(ContaValoresHelper) contaValores.next();
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso não tenha marcado a conta
						if (contaValoresHelper.getIndicadorContasDebito() == null) {
							if (contaValoresHelper.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas
										.add(contaValoresHelper
												.getValorMulta());
							}
							if (contaValoresHelper
									.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora
										.add(contaValoresHelper
												.getValorJurosMora());
							}
							if (contaValoresHelper
									.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelper
												.getValorAtualizacaoMonetaria());
							}
						}
					}
				}
			}
			
			BigDecimal valorEntradaInformado = new BigDecimal("0.00");
			if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado") != null 
					&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("")
						&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("0.00")){
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(
					(String) efetuarParcelamentoDebitosActionForm
						.get("valorEntradaInformado"));
			}	
			
			//[SB0002] - Obter Opções de Parcelamento de acordo com a entrada informada
			BigDecimal valorCreditoARealizarBigDecimal = new BigDecimal("0.00");
			if (valorCreditoARealizar != null){
				valorCreditoARealizarBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorCreditoARealizar);
			}
			
			
			//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), new BigDecimal("0.00"), situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValores, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizarBigDecimal);
			
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
			
			// [SB0002] - Obter Opções de Parcelamento
			/*opcoesParcelamento = fachada.obterOpcoesDeParcelamento(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), new BigDecimal("0.00"),
			situacaoAguaId, situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento,
			indicadorRestabelecimento, colecaoContaValores, valorDebitoTotalAtualizado, 
			valorTotalMultas, valorTotalJurosMora, valorTotalAtualizacoesMonetarias,
			numeroReparcelamentoConsecutivos, colecaoGuiaPagamento,usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper);*/
	
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();

			// Atualizando o valor do débito total
			efetuarParcelamentoDebitosActionForm
				.set("valorDebitoTotalAtualizado",
					Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			
			// Verifica se existe valor de entrada de parcelamento marcada pelas EP
			if (valorEntradaParcelamento != null 
					&& !valorEntradaParcelamento.equals(new BigDecimal("0.00")) ){
				
				//Verificar permissão especial
				boolean temPermissaoValMinimoEntrada = fachada.verificarPermissaoValMinimoEntrada(usuario);
				
				if (valorEntradaParcelamento.compareTo(
						valorEntradaMinima.setScale(2,BigDecimal.ROUND_HALF_UP)) == -1
						&& !temPermissaoValMinimoEntrada) {
					throw new ActionServletException(
						"atencao.valor.entrada.deve.ser.maior.igual.minima", null, 
							Util.formatarMoedaReal(valorEntradaMinima));
				}else{
					valorEntradaMinima = valorEntradaParcelamento;
				}
			}else{
				valorEntradaMinima = valorEntradaInformado;
			}
			
			// Colocando o valor da entrada na sessão caso tenha sido marcada EP
			sessao.setAttribute("valorEntradaMinima", valorEntradaMinima);
		}else{
			// Colocando o valor da entrada na sessão caso não tenha sido marcada EP
			if( ! marcadaNB ){
				sessao.setAttribute("valorEntradaMinima", new BigDecimal("0.00"));
			}	
		}
		
		// Faz os cálculos quando as contas NB forem marcadas, quando o botão calcular não for acionado
		// Apenas as NB são retiradas do débito
		String calcula = (String) sessao.getAttribute("calcula");
		String verificaCalcula = "session";

		if(  calcula == null && marcadaNB ){
			// Pega variáveis da sessão
			BigDecimal valorTotalContaValores = (BigDecimal) sessao
				.getAttribute("valorTotalContaValores");
			BigDecimal valorAcrescimosImpontualidade = (BigDecimal) sessao
				.getAttribute("valorAcrescimosImpontualidade");
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao
				.getAttribute("valorDebitoTotalAtualizado");

			// Atribui 1 a calcula na sessão
			sessao.setAttribute("calcula", "1");

			colecaoContaValores = (Collection<ContaValoresHelper>) sessao
					.getAttribute("colecaoContaValores");

			BigDecimal valorContaNB = new BigDecimal("0.00");
			BigDecimal valorAcrescimosNB = new BigDecimal("0.00");

			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				Iterator contaValores = colecaoContaValores.iterator();
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores
							.next();
					if (verificaCalcula != null
							&& verificaCalcula.equals("request")) {
						if (httpServletRequest.getParameter("indicadorContasDebito"
								+contaValoresHelper.getConta().getId().toString()) != null) {
							String indice = httpServletRequest
								.getParameter("indicadorContasDebito"
									+ contaValoresHelper.getConta().getId().toString());
							
							contaValoresHelper
									.setIndicadorContasDebito(new Integer(indice));
							
							// Caso as contas sejam não baixadas
							if (indice.equals("2")) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB
										.add(contaValoresHelper.getConta()
												.getValorTotal());
								if( indicadorAcrescimosImpotualidade.equals("1") ){
									valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorAcrescimosNB = valorAcrescimosNB
											.add(contaValoresHelper
													.getValorTotalContaValoresParcelamento());
								}	
							}
						}
					} else {
						if (contaValoresHelper.getIndicadorContasDebito() != null) {
							if (contaValoresHelper.getIndicadorContasDebito()
									.equals(2)) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB
									.add(contaValoresHelper.getConta()
											.getValorTotal());
								
								valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorAcrescimosNB = valorAcrescimosNB
									.add(contaValoresHelper
											.getValorTotalContaValoresParcelamento());
							}
						}
					}
				}
				valorTotalContaValores.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalContaValores = valorTotalContaValores
						.subtract(valorContaNB);
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					valorAcrescimosImpontualidade = valorAcrescimosImpontualidade
							.subtract(valorAcrescimosNB);
				}	

				// Calcula sempre em cima do valor do debito
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorContaNB);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorAcrescimosNB);
				
				efetuarParcelamentoDebitosActionForm
					.set("valorTotalContaValores",
						Util.formatarMoedaReal(valorTotalContaValores));
				
				efetuarParcelamentoDebitosActionForm
					.set("valorAcrescimosImpontualidade",
						Util.formatarMoedaReal(valorAcrescimosImpontualidade));
				
				if( valorDebitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
						|| valorDebitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					throw new ActionServletException(
							"atencao.nao.existe.debito.a.parcelar");
				}
				efetuarParcelamentoDebitosActionForm
					.set("valorDebitoTotalAtualizado",
						Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			}
		}
		
		return retorno;
	}
}