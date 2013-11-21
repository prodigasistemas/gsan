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
package gcom.cobranca;

import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumentoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorItemCobrado;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private DebitoACobrarGeral debitoACobrarGeral;

    /** persistent field */
    private gcom.cobranca.CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gcom.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private ContaGeral contaGeral;

    /** persistent field */
    private GuiaPagamentoGeral guiaPagamentoGeral;
    
    /** nullable persistent field */
    private Date dataSituacaoDebito;
    
    private BigDecimal  valorAcrescimos;

    /** persistent field */
    private CobrancaDebitoSituacao cobrancaDebitoSituacao;
    
    /** persistent field */
    private CreditoARealizarGeral creditoARealizarGeral;
    
    private Integer numeroParcelasAntecipadas;

    private PrestacaoContratoParcelamento prestacaoContratoParcelamento;

    /** full constructor */
    public CobrancaDocumentoItem(BigDecimal valorItemCobrado, Date ultimaAlteracao, DebitoACobrarGeral debitoACobrarGeral, gcom.cobranca.CobrancaDocumento cobrancaDocumento, gcom.cobranca.DocumentoTipo documentoTipo, ContaGeral contaGeral, GuiaPagamentoGeral guiaPagamentoGeral) {
        this.valorItemCobrado = valorItemCobrado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
    }

    /** default constructor */
    public CobrancaDocumentoItem() {
    }

    /** minimal constructor */
    public CobrancaDocumentoItem(DebitoACobrarGeral debitoACobrarGeral, gcom.cobranca.CobrancaDocumento cobrancaDocumento, gcom.cobranca.DocumentoTipo documentoTipo, ContaGeral contaGeral, GuiaPagamentoGeral guiaPagamentoGeral) {
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorItemCobrado() {
        return this.valorItemCobrado;
    }

    public void setValorItemCobrado(BigDecimal valorItemCobrado) {
        this.valorItemCobrado = valorItemCobrado;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public gcom.cobranca.CobrancaDocumento getCobrancaDocumento() {
        return this.cobrancaDocumento;
    }

    public void setCobrancaDocumento(gcom.cobranca.CobrancaDocumento cobrancaDocumento) {
        this.cobrancaDocumento = cobrancaDocumento;
    }
    
    

    public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo cobrancaDebitoSituacao.
	 */
	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	/**
	 * @param cobrancaDebitoSituacao O cobrancaDebitoSituacao a ser setado.
	 */
	public void setCobrancaDebitoSituacao(
			CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

	public Integer getNumeroParcelasAntecipadas() {
		return numeroParcelasAntecipadas;
	}

	public void setNumeroParcelasAntecipadas(Integer numeroParcelasAntecipadas) {
		this.numeroParcelasAntecipadas = numeroParcelasAntecipadas;
	}

	public PrestacaoContratoParcelamento getPrestacaoContratoParcelamento() {
		return prestacaoContratoParcelamento;
	}

	public void setPrestacaoContratoParcelamento(
			PrestacaoContratoParcelamento prestacaoContratoParcelamento) {
		this.prestacaoContratoParcelamento = prestacaoContratoParcelamento;
	}


}
