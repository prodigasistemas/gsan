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
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ProcessarEfetuarParcelamentoDebitosProcesso3Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo3");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		// Pega variáveis do formulário
		String codigoImovel = (String)efetuarParcelamentoDebitosActionForm.get("matriculaImovel");
		Integer situacaoAguaId = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("situacaoAguaId"));
		Integer situacaoEsgotoId = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId"));
		Integer perfilImovel = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("perfilImovel"));
		Integer numeroReparcelamentoConsecutivos = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("numeroReparcelamentoConsecutivos"));
		String resolucaoDiretoria = (String)efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria");
		String inicioIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		BigDecimal valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado"));
		String valorDebitoACobrarParcelamentoImovel =( (String)efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoImovel"));
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		Integer fimIntervaloParcelamentoFormatado = 
			fimIntervaloParcelamento !=null && !fimIntervaloParcelamento.equalsIgnoreCase("") 
			? Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento): null;
		
		Integer inicioIntervaloParcelamentoFormatado =
			inicioIntervaloParcelamento !=null && !inicioIntervaloParcelamento.equalsIgnoreCase("") 
			? Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento): null;
			//Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");
		
		Integer indicadorGuiasPagamento = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento")));
		Integer indicadorDebitosACobrar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar")));
		Integer indicadorCreditoARealizar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar")));
		Integer indicadorAcrescimosImpotualidade = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade"));
		Integer indicadorContasRevisao = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao"));
		Integer indicadorDividaAtiva = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva"));
		
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(indicadorContasRevisao);
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(indicadorDebitosACobrar);
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(indicadorCreditoARealizar);
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(indicadorGuiasPagamento);
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(indicadorAcrescimosImpotualidade);
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(indicadorDividaAtiva);
		
		
		
		if (valorDebitoACobrarParcelamentoImovel != null){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
		
		// Valor de Entrada
		//*********************************************************
		// Por: Iavn Sergio
		// 01/06/2010
		// CRC4062
		//*********************************************************
		if (efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", "o Valor da Entrada");
		}
		//*********************************************************
		BigDecimal valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(
				(String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado")).setScale(2);

		BigDecimal valorEntradaInformadoAntes = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes").equals("")){
			valorEntradaInformadoAntes = Util.formatarMoedaRealparaBigDecimal(
				(String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformadoAntes")).setScale(2);
		}
		
		// Verifica se o botão calcular foi acionado quando o valor de entrada for alterado
		if( !valorEntradaInformadoAntes.equals(valorEntradaInformado) ){
			throw new ActionServletException("atencao.valor.entrada.alterado.necessario.calcular");
		}
		
		// Armazena a Coleção de Opções de Parcelamento
		Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>)
			sessao.getAttribute("colecaoOpcoesParcelamento");
		

		Short numeroPrestacoesSelecionada = new Short("0");
//		BigDecimal valorPrestacaoSelecionada = new BigDecimal("0.00");
		// Verifica se alguma opção de parcelamento foi marcada
	    if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			boolean opcaoMarcada = false;
			while(opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamentoHelper = 
					(OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if(efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas") != null ){
					if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas"))
							.equals(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()) ){
						opcaoMarcada = true;
						opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(new Short(
								opcoesParcelamentoHelper.getQuantidadePrestacao().toString()));
						
						numeroPrestacoesSelecionada = opcoesParcelamentoHelper.getQuantidadePrestacao();
//						valorPrestacaoSelecionada = opcoesParcelamentoHelper.getValorPrestacao();
					}
				}	
			}
			if( ! opcaoMarcada ){
				if( httpServletRequest.getParameter("destino")== null ){
					throw new ActionServletException("atencao.pelo.menos.uma.opcao.parcelamento.marcada");
				}else if (httpServletRequest.getParameter("destino").equals("4") ){
					throw new ActionServletException("atencao.pelo.menos.uma.opcao.parcelamento.marcada");
				}	
			}
		}
	    
	    //verificar valor da entrada mínima permitida
	    verificarValorEntradaMinimaPermitida( numeroPrestacoesSelecionada,
	    		valorEntradaInformado, valorDebitoTotalAtualizado, parcelamentoPerfil);

		BigDecimal valorTotalMultas = new BigDecimal("0.00");
		BigDecimal valorTotalJurosMora = new BigDecimal("0.00");
		BigDecimal valorTotalAtualizacoesMonetarias = new BigDecimal("0.00");
		BigDecimal valorEntradaMinima = new BigDecimal("0.00"); 

		// Faz os cálculos quando a entrada for modificada
		String calculaOpcaoParcelamento = httpServletRequest.getParameter("calculaOpcaoParcelamento");

		// O indicador só será usado caso a situação de Água do Imóvel seja
		// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
		Integer indicadorRestabelecimento = new Integer("0");
		if (efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento") != null
				&& !efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento").equals("")) {
			indicadorRestabelecimento = new Integer(String.valueOf(efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento")));
		}
		
		Collection<ContaValoresHelper> colecaoContaValoresNegociacao = null;
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValoresNegociacao = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
		} else {
			colecaoContaValoresNegociacao = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValoresImovel");
		}

		// Guia de Pagamento
		Collection<GuiaPagamento> colecaoGuiaPagamento = (Collection<GuiaPagamento>) sessao.getAttribute("colecaoGuiaPagamentoValores");

		if ( calculaOpcaoParcelamento != null && calculaOpcaoParcelamento.equals("1") ){
			// Verifica se a entrada informada é menor que a mínima caso venha da aba 2
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
							if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias.add(
										contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
							}
						}
					}
				}
			}

			// Limpando as opções da sessão
			sessao.removeAttribute("opcoesParcelamento");
			sessao.removeAttribute("colecaoOpcoesParcelamento");

			//[SB0002] - Obter Opções de Parcelamento de acordo com a entrada informada
			BigDecimal valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal((String)  efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar"));
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

			valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();

			colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
			
			//Verificar permissão especial
			boolean temPermissaoValMinimoEntrada = fachada.verificarPermissaoValMinimoEntrada(usuario);
			
			if (valorEntradaInformado.compareTo(valorEntradaMinima.setScale(2,BigDecimal.ROUND_HALF_UP)) == -1
					&& !temPermissaoValMinimoEntrada) {
				throw new ActionServletException("atencao.valor.entrada.deve.ser.maior.igual.minima", null, Util.formatarMoedaReal(valorEntradaMinima));
			}else{
				valorEntradaMinima = valorEntradaInformado;
			}
			
			sessao.setAttribute("opcoesParcelamento", opcoesParcelamento);
			
			// Limpa os EP da Coleção de Contas
			Iterator contaValores = colecaoContaValoresNegociacao.iterator();
			while(contaValores.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
				contaValoresHelper.setIndicadorContasDebito(null);
			}
			
			// Limpando a opção de parcelamento
			if( colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.equals("") ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(null);
				}
			}	
		}
		
		// Verifica se a entrada informada é menor que a mínima caso venha da aba 2
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
		}

		if (valorEntradaInformado.compareTo(valorEntradaMinima.setScale(2,BigDecimal.ROUND_HALF_UP)) == -1) {
			throw new ActionServletException("atencao.valor.entrada.deve.ser.maior.igual.minima", null, Util.formatarMoedaReal(valorEntradaMinima));
		}else{
			valorEntradaMinima = valorEntradaInformado; 
		}
			
		sessao.setAttribute("colecaoOpcoesParcelamento", colecaoOpcoesParcelamento);
		
		return retorno;
	}
	
	//Vivianne Sousa 10/07/2008
	public void verificarValorEntradaMinimaPermitida(Short numeroPrestacoesSelecionada,
    		BigDecimal valorEntrada, BigDecimal valorDebitoTotalAtualizado,
    		ParcelamentoPerfil parcelamentoPerfil){
		
		//caso o indicador de entrada mínima seja SIM
		if (parcelamentoPerfil.getIndicadorEntradaMinima() != null &&
			parcelamentoPerfil.getIndicadorEntradaMinima().equals(ConstantesSistema.SIM) &&
			numeroPrestacoesSelecionada != null &&
			numeroPrestacoesSelecionada.intValue() != 0){
			
			BigDecimal valorPrestacao = Util.dividirArredondando(
					valorDebitoTotalAtualizado, new BigDecimal(numeroPrestacoesSelecionada));
			
			valorPrestacao = valorPrestacao.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
			
			//o sistema verificará se a entrada informada está menor 
			//que o valor da prestação calculada da opção de qtde de parcelas selecionada
			if(valorPrestacao.compareTo(valorEntrada) == 1){
				throw new ActionServletException("atencao.valor.entrada.menor.possivel");
			}
			
		}
		
	}
}