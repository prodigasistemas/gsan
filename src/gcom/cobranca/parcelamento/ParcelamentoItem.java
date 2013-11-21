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
package gcom.cobranca.parcelamento;

import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    //private CreditoARealizarHistorico creditoARealizarHistorico;

    /** persistent field */
    //private DebitoACobrarHistorico debitoACobrarHistorico;

    /** persistent field */
    private gcom.cobranca.parcelamento.Parcelamento parcelamento;

    /** persistent field */
    private DebitoACobrarGeral debitoACobrarGeral;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private ContaGeral contaGeral;

    /** persistent field */
    //private ContaHistorico contaHistorico;

    /** persistent field */
    private GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private CreditoARealizarGeral creditoARealizarGeral;

    /** full constructor */
    //public ParcelamentoItem(Date ultimaAlteracao, CreditoARealizarHistorico creditoARealizarHistorico, DebitoACobrarHistorico debitoACobrarHistorico, gcom.cobranca.parcelamento.Parcelamento parcelamento, DebitoACobrar debitoACobrar, DocumentoTipo documentoTipo, Conta conta, ContaHistorico contaHistorico, GuiaPagamento guiaPagamento, CreditoARealizar creditoARealizar) {
    public ParcelamentoItem(Date ultimaAlteracao,gcom.cobranca.parcelamento.Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        this.ultimaAlteracao = ultimaAlteracao;
        //this.creditoARealizarHistorico = creditoARealizarHistorico;
        //this.debitoACobrarHistorico = debitoACobrarHistorico;
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        //this.contaHistorico = contaHistorico;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.creditoARealizarGeral = creditoARealizarGeral;
    }

    /** default constructor */
    public ParcelamentoItem() {
    }

    /** minimal constructor */
    //public ParcelamentoItem(CreditoARealizarHistorico creditoARealizarHistorico, DebitoACobrarHistorico debitoACobrarHistorico, gcom.cobranca.parcelamento.Parcelamento parcelamento, DebitoACobrar debitoACobrar, DocumentoTipo documentoTipo, Conta conta, Quadra quadra, ContaHistorico contaHistorico, GuiaPagamento guiaPagamento, CreditoARealizar creditoARealizar) {
    public ParcelamentoItem(Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		Quadra quadra, GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        //this.creditoARealizarHistorico = creditoARealizarHistorico;
        //this.debitoACobrarHistorico = debitoACobrarHistorico;
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        //this.contaHistorico = contaHistorico;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.creditoARealizarGeral = creditoARealizarGeral;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
/*
    public CreditoARealizarHistorico getCreditoARealizarHistorico() {
        return this.creditoARealizarHistorico;
    }

    public void setCreditoARealizarHistorico(CreditoARealizarHistorico creditoARealizarHistorico) {
        this.creditoARealizarHistorico = creditoARealizarHistorico;
    }

    public DebitoACobrarHistorico getDebitoACobrarHistorico() {
        return this.debitoACobrarHistorico;
    }

    public void setDebitoACobrarHistorico(DebitoACobrarHistorico debitoACobrarHistorico) {
        this.debitoACobrarHistorico = debitoACobrarHistorico;
    }
*/
    public gcom.cobranca.parcelamento.Parcelamento getParcelamento() {
        return this.parcelamento;
    }

    public void setParcelamento(gcom.cobranca.parcelamento.Parcelamento parcelamento) {
        this.parcelamento = parcelamento;
    }

    

    public DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

  

   /* public ContaHistorico getContaHistorico() {
        return this.contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }*/

    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
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

}
