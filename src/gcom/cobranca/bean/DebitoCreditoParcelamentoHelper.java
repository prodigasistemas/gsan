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
package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC0630] - Solicitar Emissão do Extrato de Débitos
 * @author Vivianne Sousa
 * @since 21/08/2007
 */
public class DebitoCreditoParcelamentoHelper {

	private Parcelamento parcelamento;
	/**
	 * Coleção de Debitos a Cobrar do Parcelamento
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrarParcelamento;
	/**
	 * Coleção de Creditos a Realizar do Parcelamento
	 */
	private Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento;
	
	private BigDecimal valorTotalDebito;
	
	private BigDecimal valorTotalCredito;
	
	private Short numeroPrestacaoCobradas;
	
	private boolean antecipacaoParcela;
	
	private Integer quantidadeAntecipacaoParcelas;
	
	public Collection<CreditoARealizar> getColecaoCreditoARealizarParcelamento() {
		return colecaoCreditoARealizarParcelamento;
	}

	public void setColecaoCreditoARealizarParcelamento(
			Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento) {
		this.colecaoCreditoARealizarParcelamento = colecaoCreditoARealizarParcelamento;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrarParcelamento() {
		return colecaoDebitoACobrarParcelamento;
	}

	public void setColecaoDebitoACobrarParcelamento(
			Collection<DebitoACobrar> colecaoDebitoACobrarParcelamento) {
		this.colecaoDebitoACobrarParcelamento = colecaoDebitoACobrarParcelamento;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}
	
	
	public BigDecimal getValorTotal() {
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalDebito = BigDecimal.ZERO;
		BigDecimal valorTotalCredito = BigDecimal.ZERO;
		
		if(this.valorTotalDebito != null){
			valorTotalDebito = this.valorTotalDebito;
		}
		
		if(this.valorTotalCredito != null){
			valorTotalCredito = this.valorTotalCredito;
		}
		
		valorTotal = valorTotalDebito.subtract(valorTotalCredito);
		
		return valorTotal;
	}

	public Short getNumeroPrestacaoCobradas() {
		return numeroPrestacaoCobradas;
	}

	public void setNumeroPrestacaoCobradas(Short numeroPrestacaoCobradas) {
		this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
	}
	
	public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        if(getParcelamento() != null && getNumeroPrestacaoCobradas() != null){
            prestacaoFormatada = getNumeroPrestacaoCobradas() + " / " 
            + getParcelamento().getNumeroPrestacoes().toString();
        }
        
        return  prestacaoFormatada ;
    }
	
	public short getNumeroPrestacaoRestante() {
        
		short retorno = getParcelamento().getNumeroPrestacoes();
        
        if (getNumeroPrestacaoCobradas() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroPrestacaoCobradas().shortValue()));
        }
             
        return retorno;
    }

	public boolean isAntecipacaoParcela() {
		return antecipacaoParcela;
	}

	public void setAntecipacaoParcela(boolean antecipacaoParcela) {
		this.antecipacaoParcela = antecipacaoParcela;
	}

	public Integer getQuantidadeAntecipacaoParcelas() {
		return quantidadeAntecipacaoParcelas;
	}

	public void setQuantidadeAntecipacaoParcelas(
			Integer quantidadeAntecipacaoParcelas) {
		this.quantidadeAntecipacaoParcelas = quantidadeAntecipacaoParcelas;
	}
	
	public BigDecimal getValorAntecipacaoParcela(){
    	
    	BigDecimal valorAntecipacaoParcela = BigDecimal.ZERO;
    	
    	if (this.getParcelamento() != null && this.getQuantidadeAntecipacaoParcelas() != null){
    		
    		BigDecimal valorJaPago = BigDecimal.ZERO;
    		
    		if (this.getNumeroPrestacaoCobradas() != null && this.getNumeroPrestacaoCobradas().intValue() > 0){
    			valorJaPago = this.getParcelamento().getValorPrestacao().multiply(BigDecimal.valueOf(this.getNumeroPrestacaoCobradas()));
    		}
    		
    		BigDecimal valorNaoPagoComJuros = this.getParcelamento().getValorParcelado().subtract(valorJaPago);
    		
    		BigDecimal valorJurosPorParcela =  this.getParcelamento().getValorJurosParcelamento().divide(
    		BigDecimal.valueOf(this.getParcelamento().getNumeroPrestacoes()),2,BigDecimal.ROUND_DOWN);
    		
    		int qtdPrestacoesRestantes = this.getParcelamento().getNumeroPrestacoes().intValue() - this.getNumeroPrestacaoCobradas().intValue();
    		
    		BigDecimal valorJurosNaoPago = valorJurosPorParcela.multiply(BigDecimal.valueOf(qtdPrestacoesRestantes));
    		
    		BigDecimal valorRestanteParcelamentoSemJuros = valorNaoPagoComJuros.subtract(valorJurosNaoPago);
    		
    		BigDecimal valorParcelaSemJuros = valorRestanteParcelamentoSemJuros.divide(new BigDecimal(qtdPrestacoesRestantes),2,BigDecimal.ROUND_DOWN);
    		
    		valorAntecipacaoParcela = valorParcelaSemJuros.multiply(new BigDecimal(this.getQuantidadeAntecipacaoParcelas().intValue()));
    	}
    	
    	return valorAntecipacaoParcela;
    }
}
