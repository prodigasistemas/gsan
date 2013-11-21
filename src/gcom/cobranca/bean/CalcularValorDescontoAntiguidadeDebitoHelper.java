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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CalcularValorDescontoAntiguidadeDebitoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal valorDescontoAntiguidade;
	
	/*
     * TODO - COSANPA - Felipe Santos
     * 
     */
	private BigDecimal valorTotalAcrescimosImpontualidadeMulta;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeJurosMora;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	// fim alteração
	
	private BigDecimal maiorQuantidadeMinimaMesesAntiguidade;
	
	private Collection colecaoContasEmAntiguidade;
	
	private Collection colecaoContasParaParcelamento;
	
	public CalcularValorDescontoAntiguidadeDebitoHelper(BigDecimal valorDescontoAntiguidade, 
			BigDecimal valorTotalAcrescimosImpontualidadeMulta, BigDecimal valorTotalAcrescimosImpontualidadeJurosMora, BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria, 
			BigDecimal maiorQuantidadeMinimaMesesAntiguidade, Collection colecaoContasEmAntiguidade, Collection colecaoContasParaParcelamento) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.valorTotalAcrescimosImpontualidadeMulta = valorTotalAcrescimosImpontualidadeMulta;
		this.valorTotalAcrescimosImpontualidadeJurosMora = valorTotalAcrescimosImpontualidadeJurosMora;
		this.valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public BigDecimal getMaiorQuantidadeMinimaMesesAntiguidade() {
		return maiorQuantidadeMinimaMesesAntiguidade;
	}

	public void setMaiorQuantidadeMinimaMesesAntiguidade(
			BigDecimal maiorQuantidadeMinimaMesesAntiguidade) {
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadeMulta() {
		return valorTotalAcrescimosImpontualidadeMulta;
	}

	public void setValorTotalAcrescimosImpontualidadeMulta(
			BigDecimal valorTotalAcrescimosImpontualidadeMulta) {
		this.valorTotalAcrescimosImpontualidadeMulta = valorTotalAcrescimosImpontualidadeMulta;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadeJurosMora() {
		return valorTotalAcrescimosImpontualidadeJurosMora;
	}

	public void setValorTotalAcrescimosImpontualidadeJurosMora(
			BigDecimal valorTotalAcrescimosImpontualidadeJurosMora) {
		this.valorTotalAcrescimosImpontualidadeJurosMora = valorTotalAcrescimosImpontualidadeJurosMora;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadeAtualizacaoMonetaria() {
		return valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	}
	
	public void setValorTotalAcrescimosImpontualidadeAtualizacaoMonetaria(
			BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria) {
		this.valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(
			Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}
}
