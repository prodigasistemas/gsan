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
package gcom.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.faturamento.FaturamentoGrupo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaImpressao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referenciaConta;

    /** persistent field */
    private short indicadorImpressao;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Integer sequencialImpressao;
    
    private BigDecimal valorConta;
    
    private Short indicadorFichaCompensacao;

    /** nullable persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;
    
    private Cliente clienteResponsavel;
    
    private ContaTipo contaTipo;
    
    private Empresa empresa;

    /** full constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, ContaGeral contaGeral, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.contaGeral = contaGeral;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    /** default constructor */
    public ContaImpressao() {
    }

    /** minimal constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    
   
    public ContaTipo getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(ContaTipo contaTipo) {
		this.contaTipo = contaTipo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorImpressao() {
		return indicadorImpressao;
	}

	public void setIndicadorImpressao(short indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}

	public int getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(int referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setContaGeral(ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ContaImpressao)) {
            return false;
        }
        ContaImpressao castOther = (ContaImpressao) other;

        return (this.getId().equals(castOther.getId()));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getId())
        .toHashCode();
    }

	public Cliente getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(Cliente clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	/**
	 * @return Retorna o campo sequencialImpressao.
	 */
	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	/**
	 * @param sequencialImpressao O sequencialImpressao a ser setado.
	 */
	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public Short getIndicadorFichaCompensacao() {
        return indicadorFichaCompensacao;
    }

    public void setIndicadorFichaCompensacao(Short indicadorFichaCompensacao) {
        this.indicadorFichaCompensacao = indicadorFichaCompensacao;
    }

    
    
}
