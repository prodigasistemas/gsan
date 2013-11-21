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
package gcom.gui.cobranca.spcserasa;

import java.math.BigDecimal;

public class NegativacaoHelper {
	
	private Integer idSituacaoCobranca;
	
	private Integer idNegativador; 
	
	private String descricao;

	private Integer somatorioQuantidadeInclusoes;

	private BigDecimal somatorioValorDebito;

	private BigDecimal somatorioValorPendente;
	
	private BigDecimal somatorioValorPago;
	
	private BigDecimal somatorioValorParcelado;
	
	private BigDecimal somatorioValorCancelado;
	
	private BigDecimal percentualQtd;
	
	private BigDecimal percentualValor;
	
	private BigDecimal valorDinamico;
	
	//****************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data 14/01/2011
	//****************************************************
	private BigDecimal somatorioValorPotencial;
	private BigDecimal somatorioValorFactivel;
	private BigDecimal somatorioValorLigado;
	private BigDecimal somatorioValorEmAnalise;
	private BigDecimal somatorioValorCortado;
	private BigDecimal somatorioValorSuprimido;
	
	private Integer quantidadeInclusao;
	//****************************************************	
	
	public Integer getQuantidadeInclusao() {
		return quantidadeInclusao;
	}
	public void setQuantidadeInclusao(Integer quantidadeInclusao) {
		this.quantidadeInclusao = quantidadeInclusao;
	}
	public NegativacaoHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	// [UC676 - SB0002]
	public NegativacaoHelper(Integer idSituacaoCobranca, String descricao, Integer somatorioQuantidadeInclusoes,
			BigDecimal somatorioValorDebito, BigDecimal somatorioValorPendente, BigDecimal somatorioValorPago,
			BigDecimal somatorioValorParcelado, BigDecimal somatoriovalorCancelado){
		this.idSituacaoCobranca = idSituacaoCobranca;
		this.descricao = descricao;
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
		this.somatorioValorDebito = somatorioValorDebito;
		this.somatorioValorPendente = somatorioValorPendente;
		this.somatorioValorPago = somatorioValorPago;
		this.somatorioValorParcelado = somatorioValorParcelado;
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}
	// [UC676 - SB0001]
	public NegativacaoHelper(Integer idNegativador, Integer somatorioQuantidadeInclusoes,
			BigDecimal somatorioValorDebito, BigDecimal somatorioValorPendente, BigDecimal somatorioValorPago,
			BigDecimal somatorioValorParcelado, BigDecimal somatoriovalorCancelado){
		this.idNegativador = idNegativador;
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
		this.somatorioValorDebito = somatorioValorDebito;
		this.somatorioValorPendente = somatorioValorPendente;
		this.somatorioValorPago = somatorioValorPago;
		this.somatorioValorParcelado = somatorioValorParcelado;
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}

	
	
	public Integer getIdSituacaoCobranca() {
		return idSituacaoCobranca;
	}

	public void setIdSituacaoCobranca(Integer idSituacaoCobranca) {
		this.idSituacaoCobranca = idSituacaoCobranca;
	}

	public Integer getSomatorioQuantidadeInclusoes() {
		return somatorioQuantidadeInclusoes;
	}

	public void setSomatorioQuantidadeInclusoes(Integer somatorioQuantidadeInclusoes) {
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
	}

	public BigDecimal getSomatorioValorCancelado() {
		return somatorioValorCancelado;
	}

	public void setSomatorioValorCancelado(BigDecimal somatoriovalorCancelado) {
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}

	public BigDecimal getSomatorioValorDebito() {
		return somatorioValorDebito;
	}

	public void setSomatorioValorDebito(BigDecimal somatorioValorDebito) {
		this.somatorioValorDebito = somatorioValorDebito;
	}

	public BigDecimal getSomatorioValorPago() {
		return somatorioValorPago;
	}

	public void setSomatorioValorPago(BigDecimal somatorioValorPago) {
		this.somatorioValorPago = somatorioValorPago;
	}

	public BigDecimal getSomatorioValorParcelado() {
		return somatorioValorParcelado;
	}

	public void setSomatorioValorParcelado(BigDecimal somatorioValorParcelado) {
		this.somatorioValorParcelado = somatorioValorParcelado;
	}

	public BigDecimal getSomatorioValorPendente() {
		return somatorioValorPendente;
	}

	public void setSomatorioValorPendente(BigDecimal somatorioValorPendente) {
		this.somatorioValorPendente = somatorioValorPendente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdNegativador() {
		return idNegativador;
	}

	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}
	
	public BigDecimal getPercentualQtd() {
		return percentualQtd;
	}

	public void setPercentualQtd(BigDecimal percentualQtd) {
		this.percentualQtd = percentualQtd;
	}

	public BigDecimal getPercentualValor() {
		return percentualValor;
	}

	public void setPercentualValor(BigDecimal percentualValor) {
		this.percentualValor = percentualValor;
	}
	
	public BigDecimal getValorDinamico() {
		return valorDinamico;
	}
	public void setValorDinamico(BigDecimal valorDinamico) {
		this.valorDinamico = valorDinamico;
	}
	public BigDecimal getSomatorioValorCortado() {
		return somatorioValorCortado;
	}
	public void setSomatorioValorCortado(BigDecimal somatorioValorCortado) {
		this.somatorioValorCortado = somatorioValorCortado;
	}
	public BigDecimal getSomatorioValorEmAnalise() {
		return somatorioValorEmAnalise;
	}
	public void setSomatorioValorEmAnalise(BigDecimal somatorioValorEmAnalise) {
		this.somatorioValorEmAnalise = somatorioValorEmAnalise;
	}
	public BigDecimal getSomatorioValorFactivel() {
		return somatorioValorFactivel;
	}
	public void setSomatorioValorFactivel(BigDecimal somatorioValorFactivel) {
		this.somatorioValorFactivel = somatorioValorFactivel;
	}
	public BigDecimal getSomatorioValorLigado() {
		return somatorioValorLigado;
	}
	public void setSomatorioValorLigado(BigDecimal somatorioValorLigado) {
		this.somatorioValorLigado = somatorioValorLigado;
	}
	public BigDecimal getSomatorioValorPotencial() {
		return somatorioValorPotencial;
	}
	public void setSomatorioValorPotencial(BigDecimal somatorioValorPotencial) {
		this.somatorioValorPotencial = somatorioValorPotencial;
	}
	public BigDecimal getSomatorioValorSuprimido() {
		return somatorioValorSuprimido;
	}
	public void setSomatorioValorSuprimido(BigDecimal somatorioValorSuprimido) {
		this.somatorioValorSuprimido = somatorioValorSuprimido;
	}
}
