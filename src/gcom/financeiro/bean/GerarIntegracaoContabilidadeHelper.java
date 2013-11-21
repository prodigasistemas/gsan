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
package gcom.financeiro.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GerarIntegracaoContabilidadeHelper {

	private Short numeroCartao;
	
	private String mesDiaInformado;
	
	private Integer idTipoLancamento;
	
	private Integer folha;
	
	private Integer indicadorLinha;
	
	private String numeroPrefixoContabil;
	
	private Integer numeroRazaoSocial;
	
	private Integer resto;
	
	private Integer cont;
	
	private Integer custo;
	
	private Integer analise;
	
	private Integer digito;
	
	private Integer terceiros;
	
	private Integer referencial;
	
	private String mesInformado;
	
	private BigDecimal valorLancamento;
	
	private Integer indicadorDebitoConta;
	
	private Integer cartao2;
	
	private Date dataInformada;
	
	private Integer versao;
	
	private Integer diaInformado;
	
	private Integer sequencial;
	
	private Integer idLocalidade;
	
	private Integer contaCredito;
	
	private Integer contaDebito;
	
	private String creditoDebito;
	
	private Integer codigoCentroCustoCredito;
	
	private Integer codigoCentroCustoDebito;
	
	private String numeroContaCredito;
	
	private String numeroContaDebito;
	
	private String indicadorCentroCusto;
	
	private String numeroHistoricoCreditoOuCredito;
	
	
	
	public String getNumeroContaCredito() {
		return numeroContaCredito;
	}

	public void setNumeroContaCredito(String numeroContaCredito) {
		this.numeroContaCredito = numeroContaCredito;
	}

	public String getNumeroContaDebito() {
		return numeroContaDebito;
	}

	public void setNumeroContaDebito(String numeroContaDebito) {
		this.numeroContaDebito = numeroContaDebito;
	}

	public Integer getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Integer contaCredito) {
		this.contaCredito = contaCredito;
	}

	public Integer getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Integer contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public GerarIntegracaoContabilidadeHelper(){}

	public Integer getAnalise() {
		return analise;
	}

	public void setAnalise(Integer analise) {
		this.analise = analise;
	}

	public Integer getCartao2() {
		return cartao2;
	}

	public void setCartao2(Integer cartao2) {
		this.cartao2 = cartao2;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

	public Integer getCusto() {
		return custo;
	}

	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	public Date getDataInformada() {
		return dataInformada;
	}

	public void setDataInformada(Date dataInformada) {
		this.dataInformada = dataInformada;
	}

	public Integer getDiaInformado() {
		return diaInformado;
	}

	public void setDiaInformado(Integer diaInformado) {
		this.diaInformado = diaInformado;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Integer getFolha() {
		return folha;
	}

	public void setFolha(Integer folha) {
		this.folha = folha;
	}

	public Integer getIdTipoLancamento() {
		return idTipoLancamento;
	}

	public void setIdTipoLancamento(Integer idTipoLancamento) {
		this.idTipoLancamento = idTipoLancamento;
	}

	public Integer getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Integer indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public Integer getIndicadorLinha() {
		return indicadorLinha;
	}

	public void setIndicadorLinha(Integer indicadorLinha) {
		this.indicadorLinha = indicadorLinha;
	}

	public String getMesDiaInformado() {
		return mesDiaInformado;
	}

	public void setMesDiaInformado(String mesDiaInformado) {
		this.mesDiaInformado = mesDiaInformado;
	}

	public String getMesInformado() {
		return mesInformado;
	}

	public void setMesInformado(String mesInformado) {
		this.mesInformado = mesInformado;
	}

	public Short getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Short numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroPrefixoContabil() {
		return numeroPrefixoContabil;
	}

	public void setNumeroPrefixoContabil(String numeroPrefixoContabil) {
		this.numeroPrefixoContabil = numeroPrefixoContabil;
	}

	public Integer getNumeroRazaoSocial() {
		return numeroRazaoSocial;
	}

	public void setNumeroRazaoSocial(Integer numeroRazaoSocial) {
		this.numeroRazaoSocial = numeroRazaoSocial;
	}

	public Integer getReferencial() {
		return referencial;
	}

	public void setReferencial(Integer referencial) {
		this.referencial = referencial;
	}

	public Integer getResto() {
		return resto;
	}

	public void setResto(Integer resto) {
		this.resto = resto;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getTerceiros() {
		return terceiros;
	}

	public void setTerceiros(Integer terceiros) {
		this.terceiros = terceiros;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public String getCreditoDebito() {
		return creditoDebito;
	}

	public void setCreditoDebito(String creditoDebito) {
		this.creditoDebito = creditoDebito;
	}

	public Integer getCodigoCentroCustoCredito() {
		return codigoCentroCustoCredito;
	}

	public void setCodigoCentroCustoCredito(Integer codigoCentroCustoCredito) {
		this.codigoCentroCustoCredito = codigoCentroCustoCredito;
	}

	public Integer getCodigoCentroCustoDebito() {
		return codigoCentroCustoDebito;
	}

	public void setCodigoCentroCustoDebito(Integer codigoCentroCustoDebito) {
		this.codigoCentroCustoDebito = codigoCentroCustoDebito;
	}

	public String getIndicadorCentroCusto() {
		return indicadorCentroCusto;
	}

	public void setIndicadorCentroCusto(String indicadorCentroCusto) {
		this.indicadorCentroCusto = indicadorCentroCusto;
	}

	public String getNumeroHistoricoCreditoOuCredito() {
		return numeroHistoricoCreditoOuCredito;
	}

	public void setNumeroHistoricoCreditoOuCredito(
			String numeroHistoricoCreditoOuCredito) {
		this.numeroHistoricoCreditoOuCredito = numeroHistoricoCreditoOuCredito;
	}


}
