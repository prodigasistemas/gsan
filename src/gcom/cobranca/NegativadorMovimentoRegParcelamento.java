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
* Thiago Silva Toscano de Brito
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

package gcom.cobranca;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.interceptor.ControleAlteracao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class NegativadorMovimentoRegParcelamento  implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    
    private BigDecimal valorParceladoEntrada;
    
    private BigDecimal valorParcelado;
    
    private BigDecimal valorParceladoEntradaPago;
    
    private BigDecimal valorParceladoCancelado;
    
    private BigDecimal valorParceladoNaoCobrado;
    
    private BigDecimal valorParceladoCobradoPago;
    
    private BigDecimal valorParceladoCobradoNaoPago;
    
    private Short numeroPrestacoes;
    
    private Short numeroPrestacoesNaoCobradas;
    
    private Short numeroPrestacoesCobradasPagas;
    
    private Short numeroPrestacoesCobradasNaoPagas;
    
    private Short indicadorParcelamentoAtivo;
    
    private Date ultimaAlteracao;

    private NegativadorMovimentoReg negativadorMovimentoReg;
    
    private Parcelamento parcelamento;
    
    /** full constructor */
    public NegativadorMovimentoRegParcelamento(Integer id,
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
    		Short indicadorParcelamentoAtivo,
    		Date ultimaAlteracao,
    		NegativadorMovimentoReg negativadorMovimentoReg,
    		Parcelamento parcelamento) {

    	this.id = id;
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
    	this.indicadorParcelamentoAtivo = indicadorParcelamentoAtivo;
    	this.ultimaAlteracao = ultimaAlteracao;
    	this.negativadorMovimentoReg = negativadorMovimentoReg;
    	this.parcelamento = parcelamento;
    }

    /** default constructor */
    public NegativadorMovimentoRegParcelamento() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorParcelamentoAtivo() {
		return indicadorParcelamentoAtivo;
	}

	public void setIndicadorParcelamentoAtivo(Short indicadorParcelamentoAtivo) {
		this.indicadorParcelamentoAtivo = indicadorParcelamentoAtivo;
	}

	public NegativadorMovimentoReg getNegativadorMovimentoReg() {
		return negativadorMovimentoReg;
	}

	public void setNegativadorMovimentoReg(
			NegativadorMovimentoReg negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
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

	public Short getNumeroPrestacoesCobradasPagas() {
		return numeroPrestacoesCobradasPagas;
	}

	public void setNumeroPrestacoesCobradasPagas(Short numeroPrestacoesCobradasPagas) {
		this.numeroPrestacoesCobradasPagas = numeroPrestacoesCobradasPagas;
	}

	public Short getNumeroPrestacoesNaoCobradas() {
		return numeroPrestacoesNaoCobradas;
	}

	public void setNumeroPrestacoesNaoCobradas(Short numeroPrestacoesNaoCobradas) {
		this.numeroPrestacoesNaoCobradas = numeroPrestacoesNaoCobradas;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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

	public BigDecimal getValorParceladoCobradoNaoPago() {
		return valorParceladoCobradoNaoPago;
	}

	public void setValorParceladoCobradoNaoPago(
			BigDecimal valorParceladoCobradoNaoPago) {
		this.valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago;
	}

	public BigDecimal getValorParceladoCobradoPago() {
		return valorParceladoCobradoPago;
	}

	public void setValorParceladoCobradoPago(BigDecimal valorParceladoCobradoPago) {
		this.valorParceladoCobradoPago = valorParceladoCobradoPago;
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

	public BigDecimal getValorParceladoNaoCobrado() {
		return valorParceladoNaoCobrado;
	}

	public void setValorParceladoNaoCobrado(BigDecimal valorParceladoNaoCobrado) {
		this.valorParceladoNaoCobrado = valorParceladoNaoCobrado;
	}

	
}
