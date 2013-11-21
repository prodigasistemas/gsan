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

import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorMovimentoRegItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** persistent field */
    private Date dataSituacaoDebito;
    
    /** persistent field */
    private Date dataSituacaoDebitoAposExclusao;
    
    /** persistent field */
    private short indicadorSituacaoDefinitiva;
    

    /** persistent field */
    private gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg;

    /** persistent field */
    private GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private ContaGeral contaGeral;

    /** persistent field */
    private gcom.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private DebitoACobrarGeral debitoACobrarGeral;

    /** persistent field */
    private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;
    
    /** persistent field */
    private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacaoAposExclusao;

    private Date ultimaAlteracao;
    
    private BigDecimal valorPago;
    
    private BigDecimal valorCancelado;
  

    /** full constructor */
    public NegativadorMovimentoRegItem(Integer id, BigDecimal valorDebito, Date dataSituacaoDebito, gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg, GuiaPagamentoGeral guiaPagamentoGeral, ContaGeral contaGeral, gcom.cobranca.DocumentoTipo documentoTipo, DebitoACobrarGeral debitoACobrarGeral, gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
        this.id = id;
        this.valorDebito = valorDebito;
        this.dataSituacaoDebito = dataSituacaoDebito;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.contaGeral = contaGeral;
        this.documentoTipo = documentoTipo;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        
    }

    /** default constructor */
    public NegativadorMovimentoRegItem() {
    }

    /** minimal constructor */
    public NegativadorMovimentoRegItem(Integer id, Date dataSituacaoDebito, gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg, GuiaPagamentoGeral guiaPagamentoGeral, ContaGeral contaGeral, gcom.cobranca.DocumentoTipo documentoTipo, DebitoACobrarGeral debitoACobrarGeral, gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
        this.id = id;
        this.dataSituacaoDebito = dataSituacaoDebito;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.contaGeral = contaGeral;
        this.documentoTipo = documentoTipo;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Date getDataSituacaoDebito() {
        return this.dataSituacaoDebito;
    }

    /**
	 * @return Retorna o campo valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago O valorPago a ser setado.
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
        this.dataSituacaoDebito = dataSituacaoDebito;
    }

    public gcom.cobranca.NegativadorMovimentoReg getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public GuiaPagamentoGeral getGuiaPagamentoGeral() {
        return this.guiaPagamentoGeral;
    }

    public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
        this.guiaPagamentoGeral = guiaPagamentoGeral;
    }

    public ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public void setContaGeral(ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public DebitoACobrarGeral getDebitoACobrarGeral() {
        return this.debitoACobrarGeral;
    }

    public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
        this.debitoACobrarGeral = debitoACobrarGeral;
    }

    public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
        return this.cobrancaDebitoSituacao;
    }

    public void setCobrancaDebitoSituacao(gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacaoAposExclusao() {
		return cobrancaDebitoSituacaoAposExclusao;
	}

	public void setCobrancaDebitoSituacaoAposExclusao(
			gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacaoAposExclusao) {
		this.cobrancaDebitoSituacaoAposExclusao = cobrancaDebitoSituacaoAposExclusao;
	}

	public Date getDataSituacaoDebitoAposExclusao() {
		return dataSituacaoDebitoAposExclusao;
	}

	public void setDataSituacaoDebitoAposExclusao(
			Date dataSituacaoDebitoAposExclusao) {
		this.dataSituacaoDebitoAposExclusao = dataSituacaoDebitoAposExclusao;
	}

	public short getIndicadorSituacaoDefinitiva() {
		return indicadorSituacaoDefinitiva;
	}

	public void setIndicadorSituacaoDefinitiva(short indicadorSituacaoDefinitiva) {
		this.indicadorSituacaoDefinitiva = indicadorSituacaoDefinitiva;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorCancelado() {
		return valorCancelado;
	}

	public void setValorCancelado(BigDecimal valorCancelado) {
		this.valorCancelado = valorCancelado;
	}

}
