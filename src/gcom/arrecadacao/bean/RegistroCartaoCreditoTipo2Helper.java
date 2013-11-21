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
package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Movimento do Tipo 1 do TXT do retorno do cartão de crédito
 * 
 * @author Raphael Rossiter
 * @date 29/01/2010
 */
public class RegistroCartaoCreditoTipo2Helper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoSubmissor;
	
	private String numeroResumoOperacao;
	
	private String numeroCartao;
	
	private Date dataCompraAjuste;
	
	private String sinalValorCompraParcela;
	
	private BigDecimal valorCompraParcela;
	
	private Integer parcela;
	
	private Integer totalParcelas;
	
	private String motivoRejeicao;
	
	private String codigoAutorizacao;
	
	private String tid;
	
	private String nsu;
	
	private BigDecimal valorTrocoFacil;
	
	private String numeroDigitosCartao;
	
	private String numeroNotaFiscal;
	
	private String codigoPaisEmissorCartao;
	
	private String numeroLogicoTerminal;
	
	private String identificadorTaxaEmbarqueOUValorEntrada;
	
	
	//CONSTANTE TIPO MOVIMENTO 2
	public final static Short CODIGO_MOVIMENTO_TIPO_2 = new Short("2");
	
	
	public RegistroCartaoCreditoTipo2Helper(){}


	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}


	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}


	public String getCodigoPaisEmissorCartao() {
		return codigoPaisEmissorCartao;
	}


	public void setCodigoPaisEmissorCartao(String codigoPaisEmissorCartao) {
		this.codigoPaisEmissorCartao = codigoPaisEmissorCartao;
	}


	public Date getDataCompraAjuste() {
		return dataCompraAjuste;
	}


	public void setDataCompraAjuste(Date dataCompraAjuste) {
		this.dataCompraAjuste = dataCompraAjuste;
	}


	public String getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}


	public void setEstabelecimentoSubmissor(String estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}


	public String getIdentificadorTaxaEmbarqueOUValorEntrada() {
		return identificadorTaxaEmbarqueOUValorEntrada;
	}


	public void setIdentificadorTaxaEmbarqueOUValorEntrada(
			String identificadorTaxaEmbarqueOUValorEntrada) {
		this.identificadorTaxaEmbarqueOUValorEntrada = identificadorTaxaEmbarqueOUValorEntrada;
	}


	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}


	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}


	public String getNsu() {
		return nsu;
	}


	public void setNsu(String nsu) {
		this.nsu = nsu;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public String getNumeroDigitosCartao() {
		return numeroDigitosCartao;
	}


	public void setNumeroDigitosCartao(String numeroDigitosCartao) {
		this.numeroDigitosCartao = numeroDigitosCartao;
	}


	public String getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}


	public void setNumeroLogicoTerminal(String numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}


	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}


	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}


	public String getNumeroResumoOperacao() {
		return numeroResumoOperacao;
	}


	public void setNumeroResumoOperacao(String numeroResumoOperacao) {
		this.numeroResumoOperacao = numeroResumoOperacao;
	}


	public String getSinalValorCompraParcela() {
		return sinalValorCompraParcela;
	}


	public void setSinalValorCompraParcela(String sinalValorCompraParcela) {
		this.sinalValorCompraParcela = sinalValorCompraParcela;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public Short getTipoRegistro() {
		return tipoRegistro;
	}


	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}


	public BigDecimal getValorCompraParcela() {
		return valorCompraParcela;
	}


	public void setValorCompraParcela(BigDecimal valorCompraParcela) {
		this.valorCompraParcela = valorCompraParcela;
	}


	public BigDecimal getValorTrocoFacil() {
		return valorTrocoFacil;
	}


	public void setValorTrocoFacil(BigDecimal valorTrocoFacil) {
		this.valorTrocoFacil = valorTrocoFacil;
	}


	public Integer getParcela() {
		return parcela;
	}


	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}


	public Integer getTotalParcelas() {
		return totalParcelas;
	}


	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
}
