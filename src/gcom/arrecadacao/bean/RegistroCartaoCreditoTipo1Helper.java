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
public class RegistroCartaoCreditoTipo1Helper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoSubmissor;
	
	private String numeroResumoOperacao;
	
	private String parcela;
	
	private String filler;
	
	private String plano;
	
	private String situacao;
	
	private String status;
	
	private Date dataDeposito;
	
	private Date dataPrevistaPagamento;
	
	private Date dataEnvioBanco;
	
	private String sinalValorBruto;
	
	private BigDecimal valorBruto;
	
	private String sinalComissao;
	
	private BigDecimal valorComissao;
	
	private String sinalValorRejeitado;
	
	private BigDecimal valorRejeitado;
	
	private String sinalValorLiguido;
	
	private BigDecimal valorLiquido;
	
	private String codigoBanco;
	
	private String codigoAgencia;
	
	private String contaCorrente;
	
	private Integer quantidadeVendasAceitas;
	
	private String identificadorProduto;
	
	private Integer quantidadeVendasRejeitadas;
	
	private String identificadorRevendaAceleracao;
	
	private String identificadorOpcaoExtrato;
	
	private Date dataCapturaTransacao;
	
	private String origemAjuste;
	
	private BigDecimal valorTrocoFacil;
	
	
	//CONSTANTE TIPO MOVIMENTO 1
	public final static Short CODIGO_MOVIMENTO_TIPO_1 = new Short("1");
	
	public final static String STATUS_LIGUIDACAO = "14";
	
	public final static String MOTIVO_REJEICAO_01 = "01";
	public final static String DESCRICAO_MOTIVO_REJEICAO_01 = "CANCELAMENTO SOLIC. ESTABELECIMENTO";
	
	public final static String MOTIVO_REJEICAO_02 = "02";
	public final static String DESCRICAO_MOTIVO_REJEICAO_02 = "CANCELAMENTO SOLIC. CLIENTE";
	
	public final static String MOTIVO_REJEICAO_03 = "03";
	public final static String DESCRICAO_MOTIVO_REJEICAO_03 = "OUTROS MOTIVOS";
	
	
	public RegistroCartaoCreditoTipo1Helper(){}


	public String getCodigoAgencia() {
		return codigoAgencia;
	}


	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}


	public String getCodigoBanco() {
		return codigoBanco;
	}


	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}


	public String getContaCorrente() {
		return contaCorrente;
	}


	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}


	public Date getDataCapturaTransacao() {
		return dataCapturaTransacao;
	}


	public void setDataCapturaTransacao(Date dataCapturaTransacao) {
		this.dataCapturaTransacao = dataCapturaTransacao;
	}


	public Date getDataDeposito() {
		return dataDeposito;
	}


	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}


	public Date getDataEnvioBanco() {
		return dataEnvioBanco;
	}


	public void setDataEnvioBanco(Date dataEnvioBanco) {
		this.dataEnvioBanco = dataEnvioBanco;
	}


	public Date getDataPrevistaPagamento() {
		return dataPrevistaPagamento;
	}


	public void setDataPrevistaPagamento(Date dataPrevistaPagamento) {
		this.dataPrevistaPagamento = dataPrevistaPagamento;
	}


	public String getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}


	public void setEstabelecimentoSubmissor(String estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}


	public String getFiller() {
		return filler;
	}


	public void setFiller(String filler) {
		this.filler = filler;
	}


	public String getIdentificadorOpcaoExtrato() {
		return identificadorOpcaoExtrato;
	}


	public void setIdentificadorOpcaoExtrato(String identificadorOpcaoExtrato) {
		this.identificadorOpcaoExtrato = identificadorOpcaoExtrato;
	}


	public String getIdentificadorProduto() {
		return identificadorProduto;
	}


	public void setIdentificadorProduto(String identificadorProduto) {
		this.identificadorProduto = identificadorProduto;
	}


	public String getIdentificadorRevendaAceleracao() {
		return identificadorRevendaAceleracao;
	}


	public void setIdentificadorRevendaAceleracao(
			String identificadorRevendaAceleracao) {
		this.identificadorRevendaAceleracao = identificadorRevendaAceleracao;
	}


	public String getNumeroResumoOperacao() {
		return numeroResumoOperacao;
	}


	public void setNumeroResumoOperacao(String numeroResumoOperacao) {
		this.numeroResumoOperacao = numeroResumoOperacao;
	}


	public String getOrigemAjuste() {
		return origemAjuste;
	}


	public void setOrigemAjuste(String origemAjuste) {
		this.origemAjuste = origemAjuste;
	}


	public String getParcela() {
		return parcela;
	}


	public void setParcela(String parcela) {
		this.parcela = parcela;
	}


	public String getPlano() {
		return plano;
	}


	public void setPlano(String plano) {
		this.plano = plano;
	}


	public Integer getQuantidadeVendasAceitas() {
		return quantidadeVendasAceitas;
	}


	public void setQuantidadeVendasAceitas(Integer quantidadeVendasAceitas) {
		this.quantidadeVendasAceitas = quantidadeVendasAceitas;
	}


	public Integer getQuantidadeVendasRejeitadas() {
		return quantidadeVendasRejeitadas;
	}


	public void setQuantidadeVendasRejeitadas(Integer quantidadeVendasRejeitadas) {
		this.quantidadeVendasRejeitadas = quantidadeVendasRejeitadas;
	}


	public String getSinalComissao() {
		return sinalComissao;
	}


	public void setSinalComissao(String sinalComissao) {
		this.sinalComissao = sinalComissao;
	}


	public String getSinalValorBruto() {
		return sinalValorBruto;
	}


	public void setSinalValorBruto(String sinalValorBruto) {
		this.sinalValorBruto = sinalValorBruto;
	}


	public String getSinalValorLiguido() {
		return sinalValorLiguido;
	}


	public void setSinalValorLiguido(String sinalValorLiguido) {
		this.sinalValorLiguido = sinalValorLiguido;
	}


	public String getSinalValorRejeitado() {
		return sinalValorRejeitado;
	}


	public void setSinalValorRejeitado(String sinalValorRejeitado) {
		this.sinalValorRejeitado = sinalValorRejeitado;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Short getTipoRegistro() {
		return tipoRegistro;
	}


	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}


	public BigDecimal getValorBruto() {
		return valorBruto;
	}


	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}


	public BigDecimal getValorComissao() {
		return valorComissao;
	}


	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}


	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}


	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}


	public BigDecimal getValorRejeitado() {
		return valorRejeitado;
	}


	public void setValorRejeitado(BigDecimal valorRejeitado) {
		this.valorRejeitado = valorRejeitado;
	}


	public BigDecimal getValorTrocoFacil() {
		return valorTrocoFacil;
	}


	public void setValorTrocoFacil(BigDecimal valorTrocoFacil) {
		this.valorTrocoFacil = valorTrocoFacil;
	}
}
