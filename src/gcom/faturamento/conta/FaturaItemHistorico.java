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

import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturaItemHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer itemSequencia;

    /** nullable persistent field */
    //private Integer indicadorUso;

    /** nullable persistent field */
    private BigDecimal valorConta;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    //private Short flag;
    
    private Integer numeroConsumo;

    /** persistent field */
    private gcom.faturamento.conta.Fatura fatura;

    /** persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;
    
    private gcom.seguranca.acesso.usuario.Usuario usuario;
    
    private Imovel imovel;
    
    /** nullable persistent field */
    private BigDecimal valorImposto;
    
    private Integer indicadorOperacao;
    
    public final static Integer QTD_ITENS_RELATORIO_FATURA = new Integer(20);
    

    /** full constructor */
    public FaturaItemHistorico(Integer itemSequencia, BigDecimal valorConta, Date ultimaAlteracao, Integer numeroConsumo,  gcom.faturamento.conta.Fatura fatura, gcom.faturamento.conta.ContaGeral contaGeral, Imovel imovel) {
        this.itemSequencia = itemSequencia;
        this.valorConta = valorConta;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroConsumo = numeroConsumo;
        this.fatura = fatura;
        this.contaGeral = contaGeral;
        this.imovel = imovel;
    }
    
    public FaturaItemHistorico(FaturaItem faturaItem, Usuario usuarioLogado){
    	this.id = faturaItem.getId();
    	this.itemSequencia = faturaItem.getItemSequencia();
        this.valorConta = faturaItem.getValorConta();
        this.ultimaAlteracao = faturaItem.getUltimaAlteracao();
        this.numeroConsumo = faturaItem.getNumeroConsumo();
        this.fatura = faturaItem.getFatura();
        this.contaGeral = faturaItem.getContaGeral();
        this.imovel = faturaItem.getImovel();
        this.usuario = usuarioLogado;
    }

    /** default constructor */
    public FaturaItemHistorico() {
    }

    /** minimal constructor */
    public FaturaItemHistorico(Integer itemSequencia, gcom.faturamento.conta.Fatura fatura, gcom.faturamento.conta.ContaGeral contaGeral) {
        this.itemSequencia = itemSequencia;
        this.fatura = fatura;
        this.contaGeral = contaGeral;
    }

    /**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo numeroConsumo.
	 */
	public Integer getNumeroConsumo() {
		return numeroConsumo;
	}

	/**
	 * @param numeroConsumo O numeroConsumo a ser setado.
	 */
	public void setNumeroConsumo(Integer numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemSequencia() {
        return this.itemSequencia;
    }

    public void setItemSequencia(Integer itemSequencia) {
        this.itemSequencia = itemSequencia;
    }

    public BigDecimal getValorConta() {
        return this.valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
/*
    public Short getFlag() {
        return this.flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }
*/
    public gcom.faturamento.conta.Fatura getFatura() {
        return this.fatura;
    }

    public void setFatura(gcom.faturamento.conta.Fatura fatura) {
        this.fatura = fatura;
    }

    public gcom.faturamento.conta.ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public void setContaGeral(gcom.faturamento.conta.ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}

	public Integer getIndicadorOperacao() {
		return indicadorOperacao;
	}

	public void setIndicadorOperacao(Integer indicadorOperacao) {
		this.indicadorOperacao = indicadorOperacao;
	}

	public gcom.seguranca.acesso.usuario.Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(gcom.seguranca.acesso.usuario.Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
