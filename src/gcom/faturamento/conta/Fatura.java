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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Fatura implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;
    
    /** nullable persistent field */
    private Date vencimento;

    /** nullable persistent field */
    private Date emissao;

    /** nullable persistent field */
    private Date validade;

    /** nullable persistent field */
    //private Integer indicadorUso;

    /** nullable persistent field */
    private BigDecimal debito;

    /** nullable persistent field */
    private BigDecimal taxa;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Integer sequencial;

    /** nullable persistent field */
    private Short flag;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;
    
    /** persistent field */
    private Integer codigoQualifica;
    
    private String numeroFatura;

    /** full constructor */
    public Fatura(Date vencimento, Date emissao, Date validade, BigDecimal debito, BigDecimal taxa, Date ultimaAlteracao, Integer anoMesReferencia, Integer sequencial, Short flag, Cliente cliente) {
    	this.vencimento = vencimento;
        this.emissao = emissao;
        this.validade = validade;
        //this.indicadorUso = indicadorUso;
        this.debito = debito;
        this.taxa = taxa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferencia = anoMesReferencia;
        this.sequencial = sequencial;
        this.flag = flag;
        this.cliente = cliente;
    }

    /**
	 * @return Retorna o campo vencimento.
	 */
	public Date getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento O vencimento a ser setado.
	 */
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	/** default constructor */
    public Fatura() {
    }

    /** minimal constructor */
    public Fatura(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEmissao() {
        return this.emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getValidade() {
        return this.validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

//    public Integer getIndicadorUso() {
//        return this.indicadorUso;
//    }
//
//    public void setIndicadorUso(Integer indicadorUso) {
//        this.indicadorUso = indicadorUso;
//    }

    public BigDecimal getDebito() {
        return this.debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getTaxa() {
        return this.taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getSequencial() {
        return this.sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Short getFlag() {
        return this.flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getCodigoQualifica() {
		return codigoQualifica;
	}

	public void setCodigoQualifica(Integer codigoQualifica) {
		this.codigoQualifica = codigoQualifica;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}
}
