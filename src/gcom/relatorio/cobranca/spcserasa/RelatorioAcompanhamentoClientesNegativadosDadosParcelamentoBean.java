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
package gcom.relatorio.cobranca.spcserasa;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Vivianne Sousa
 * @created 30/04/2009
 * @version 1.0
 */

public class RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean implements RelatorioBean {
	  
    private BigDecimal valorParcelado;
    private BigDecimal valorParceladoEntrada;
    private BigDecimal valorParceladoEntradaPago;
    private Short numeroPrestacoes;
    private Short numeroPrestacoesCobradasPagas;
    private BigDecimal valorParceladoCobradoPago;    
    private Short numeroPrestacoesCobradasNaoPagas;
    private BigDecimal valorParceladoCobradoNaoPago;
    private Short numeroPrestacoesNaoCobradas;
    private BigDecimal valorParceladoNaoCobrado;
    private BigDecimal valorParceladoCancelado;
    private BigDecimal valorParceladoTotalParc;
    private BigDecimal valorParceladoEntradaTotalParc;
    private BigDecimal valorParceladoEntradaPagoTotalParc;
    private Short numeroPrestacoesTotalParc;
    private Short numeroPrestacoesCobradasPagasTotalParc;
    private BigDecimal valorParceladoCobradoPagoTotalParc;    
    private Short numeroPrestacoesCobradasNaoPagasTotalParc;
    private BigDecimal valorParceladoCobradoNaoPagoTotalParc;
    private Short numeroPrestacoesNaoCobradasTotalParc;
    private BigDecimal valorParceladoNaoCobradoTotalParc;
    private BigDecimal valorParceladoCanceladoTotalParc;

	public RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean(
			BigDecimal valorParceladoEntrada, 
			BigDecimal valorParcelado, 
			BigDecimal valorParceladoEntradaPago, 
			BigDecimal valorParceladoCancelado,
			BigDecimal valorParceladoNaoCobrado, 
			BigDecimal valorParceladoCobradoPago,
			BigDecimal valorParceladoCobradoNaoPago, 
			Short numeroPrestacoes,
			Short numeroPrestacoesNaoCobradas, 
			Short numeroPrestacoesCobradasPagas,
			Short numeroPrestacoesCobradasNaoPagas,
			BigDecimal valorParceladoTotalParc,
			BigDecimal valorParceladoEntradaTotalParc,
			BigDecimal valorParceladoEntradaPagoTotalParc, 
			Short numeroPrestacoesTotalParc, 
			Short numeroPrestacoesCobradasPagasTotalParc,
			BigDecimal valorParceladoCobradoPagoTotalParc, 
			Short numeroPrestacoesCobradasNaoPagasTotalParc,	
			BigDecimal valorParceladoCobradoNaoPagoTotalParc,
			Short numeroPrestacoesNaoCobradasTotalParc,	
			BigDecimal valorParceladoNaoCobradoTotalParc, 
			BigDecimal valorParceladoCanceladoTotalParc
			) {
		super();
		// TODO Auto-generated constructor stub

		this.valorParceladoEntrada = valorParceladoEntrada;
		this.valorParcelado = valorParcelado;
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
		this.valorParceladoCancelado = valorParceladoCancelado;
		this.valorParceladoNaoCobrado = valorParceladoNaoCobrado;
		this.valorParceladoCobradoPago = valorParceladoCobradoPago;
		this.valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago;
		this.numeroPrestacoes = numeroPrestacoes;
		this.numeroPrestacoesNaoCobradas = numeroPrestacoesNaoCobradas;
		this.numeroPrestacoesCobradasPagas = numeroPrestacoesCobradasPagas;
		this.numeroPrestacoesCobradasNaoPagas = numeroPrestacoesCobradasNaoPagas;
		this.valorParceladoTotalParc = valorParceladoTotalParc;
		this.valorParceladoEntradaTotalParc = valorParceladoEntradaTotalParc;
		this.valorParceladoEntradaPagoTotalParc = valorParceladoEntradaPagoTotalParc; 
		this.numeroPrestacoesTotalParc = numeroPrestacoesTotalParc;
		this.numeroPrestacoesCobradasPagasTotalParc = numeroPrestacoesCobradasPagasTotalParc; 
		this.valorParceladoCobradoPagoTotalParc = valorParceladoCobradoPagoTotalParc;    
		this.numeroPrestacoesCobradasNaoPagasTotalParc = numeroPrestacoesCobradasNaoPagasTotalParc;
		this.valorParceladoCobradoNaoPagoTotalParc = valorParceladoCobradoNaoPagoTotalParc;
		this.numeroPrestacoesNaoCobradasTotalParc = numeroPrestacoesNaoCobradasTotalParc;
		this.valorParceladoNaoCobradoTotalParc = valorParceladoNaoCobradoTotalParc;
		this.valorParceladoCanceladoTotalParc = valorParceladoCanceladoTotalParc;

	}

	public Short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Short getNumeroPrestacoesCobradasNaoPagas() {
		return numeroPrestacoesCobradasNaoPagas;
	}

	public void setNumeroPrestacoesCobradasNaoPagas(
			Short numeroPrestacoesCobradasNaoPagas) {
		this.numeroPrestacoesCobradasNaoPagas = numeroPrestacoesCobradasNaoPagas;
	}

	public Short getNumeroPrestacoesCobradasNaoPagasTotalParc() {
		return numeroPrestacoesCobradasNaoPagasTotalParc;
	}

	public void setNumeroPrestacoesCobradasNaoPagasTotalParc(
			Short numeroPrestacoesCobradasNaoPagasTotalParc) {
		this.numeroPrestacoesCobradasNaoPagasTotalParc = numeroPrestacoesCobradasNaoPagasTotalParc;
	}

	public Short getNumeroPrestacoesCobradasPagas() {
		return numeroPrestacoesCobradasPagas;
	}

	public void setNumeroPrestacoesCobradasPagas(Short numeroPrestacoesCobradasPagas) {
		this.numeroPrestacoesCobradasPagas = numeroPrestacoesCobradasPagas;
	}

	public Short getNumeroPrestacoesCobradasPagasTotalParc() {
		return numeroPrestacoesCobradasPagasTotalParc;
	}

	public void setNumeroPrestacoesCobradasPagasTotalParc(
			Short numeroPrestacoesCobradasPagasTotalParc) {
		this.numeroPrestacoesCobradasPagasTotalParc = numeroPrestacoesCobradasPagasTotalParc;
	}

	public Short getNumeroPrestacoesNaoCobradas() {
		return numeroPrestacoesNaoCobradas;
	}

	public void setNumeroPrestacoesNaoCobradas(Short numeroPrestacoesNaoCobradas) {
		this.numeroPrestacoesNaoCobradas = numeroPrestacoesNaoCobradas;
	}

	public Short getNumeroPrestacoesNaoCobradasTotalParc() {
		return numeroPrestacoesNaoCobradasTotalParc;
	}

	public void setNumeroPrestacoesNaoCobradasTotalParc(
			Short numeroPrestacoesNaoCobradasTotalParc) {
		this.numeroPrestacoesNaoCobradasTotalParc = numeroPrestacoesNaoCobradasTotalParc;
	}

	public Short getNumeroPrestacoesTotalParc() {
		return numeroPrestacoesTotalParc;
	}

	public void setNumeroPrestacoesTotalParc(Short numeroPrestacoesTotalParc) {
		this.numeroPrestacoesTotalParc = numeroPrestacoesTotalParc;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public BigDecimal getValorParceladoCancelado() {
		return valorParceladoCancelado;
	}

	public void setValorParceladoCancelado(BigDecimal valorParceladoCancelado) {
		this.valorParceladoCancelado = valorParceladoCancelado;
	}

	public BigDecimal getValorParceladoCanceladoTotalParc() {
		return valorParceladoCanceladoTotalParc;
	}

	public void setValorParceladoCanceladoTotalParc(
			BigDecimal valorParceladoCanceladoTotalParc) {
		this.valorParceladoCanceladoTotalParc = valorParceladoCanceladoTotalParc;
	}

	public BigDecimal getValorParceladoCobradoNaoPago() {
		return valorParceladoCobradoNaoPago;
	}

	public void setValorParceladoCobradoNaoPago(
			BigDecimal valorParceladoCobradoNaoPago) {
		this.valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago;
	}

	public BigDecimal getValorParceladoCobradoNaoPagoTotalParc() {
		return valorParceladoCobradoNaoPagoTotalParc;
	}

	public void setValorParceladoCobradoNaoPagoTotalParc(
			BigDecimal valorParceladoCobradoNaoPagoTotalParc) {
		this.valorParceladoCobradoNaoPagoTotalParc = valorParceladoCobradoNaoPagoTotalParc;
	}

	public BigDecimal getValorParceladoCobradoPago() {
		return valorParceladoCobradoPago;
	}

	public void setValorParceladoCobradoPago(BigDecimal valorParceladoCobradoPago) {
		this.valorParceladoCobradoPago = valorParceladoCobradoPago;
	}

	public BigDecimal getValorParceladoCobradoPagoTotalParc() {
		return valorParceladoCobradoPagoTotalParc;
	}

	public void setValorParceladoCobradoPagoTotalParc(
			BigDecimal valorParceladoCobradoPagoTotalParc) {
		this.valorParceladoCobradoPagoTotalParc = valorParceladoCobradoPagoTotalParc;
	}

	public BigDecimal getValorParceladoEntrada() {
		return valorParceladoEntrada;
	}

	public void setValorParceladoEntrada(BigDecimal valorParceladoEntrada) {
		this.valorParceladoEntrada = valorParceladoEntrada;
	}

	public BigDecimal getValorParceladoEntradaPago() {
		return valorParceladoEntradaPago;
	}

	public void setValorParceladoEntradaPago(BigDecimal valorParceladoEntradaPago) {
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
	}

	public BigDecimal getValorParceladoEntradaPagoTotalParc() {
		return valorParceladoEntradaPagoTotalParc;
	}

	public void setValorParceladoEntradaPagoTotalParc(
			BigDecimal valorParceladoEntradaPagoTotalParc) {
		this.valorParceladoEntradaPagoTotalParc = valorParceladoEntradaPagoTotalParc;
	}

	public BigDecimal getValorParceladoNaoCobrado() {
		return valorParceladoNaoCobrado;
	}

	public void setValorParceladoNaoCobrado(BigDecimal valorParceladoNaoCobrado) {
		this.valorParceladoNaoCobrado = valorParceladoNaoCobrado;
	}

	public BigDecimal getValorParceladoNaoCobradoTotalParc() {
		return valorParceladoNaoCobradoTotalParc;
	}

	public void setValorParceladoNaoCobradoTotalParc(
			BigDecimal valorParceladoNaoCobradoTotalParc) {
		this.valorParceladoNaoCobradoTotalParc = valorParceladoNaoCobradoTotalParc;
	}

	public BigDecimal getValorParceladoEntradaTotalParc() {
		return valorParceladoEntradaTotalParc;
	}

	public void setValorParceladoEntradaTotalParc(
			BigDecimal valorParceladoEntradaTotalParc) {
		this.valorParceladoEntradaTotalParc = valorParceladoEntradaTotalParc;
	}

	public BigDecimal getValorParceladoTotalParc() {
		return valorParceladoTotalParc;
	}

	public void setValorParceladoTotalParc(BigDecimal valorParceladoTotalParc) {
		this.valorParceladoTotalParc = valorParceladoTotalParc;
	}

	
	
}
