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

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class IndicesAcrescimosImpontualidade extends ObjetoTransacao  {
	
	private static final long serialVersionUID = 1L;
	


	public Filtro retornaFiltro(){
		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();

		filtroIndicesAcrescimosImpontualidade.adicionarParametro(new ParametroSimples(FiltroIndicesAcrescimosImpontualidade.ID,
				this.getId()));

		
		return filtroIndicesAcrescimosImpontualidade;
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private BigDecimal percentualMulta;

    /** nullable persistent field */
    private BigDecimal percentualJurosMora;

    /** nullable persistent field */
    private BigDecimal fatorAtualizacaoMonetaria;
    
    /** nullable persistent field */
    private BigDecimal percentualLimiteJuros;

    /** nullable persistent field */
    private BigDecimal percentualLimiteMulta;
    
    /** nullable persistent field */
    private Short indicadorJurosMensal;
    
    /** nullable persistent field */
    private Short indicadorMultaMensal;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
	public IndicesAcrescimosImpontualidade(Integer id, Integer anoMesReferencia, BigDecimal percentualMulta, BigDecimal percentualJurosMora, BigDecimal fatorAtualizacaoMonetaria, BigDecimal percentualLimiteJuros, BigDecimal percentualLimiteMulta, Short indicadorJurosMensal, Short indicadorMultaMensal, Date ultimaAlteracao) {
		this.anoMesReferencia = anoMesReferencia;
		this.percentualMulta = percentualMulta;
		this.percentualJurosMora = percentualJurosMora;
		this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
		this.percentualLimiteJuros = percentualLimiteJuros;
		this.percentualLimiteMulta = percentualLimiteMulta;
		this.indicadorJurosMensal = indicadorJurosMensal;
		this.indicadorMultaMensal = indicadorMultaMensal;
		this.ultimaAlteracao = ultimaAlteracao;
	}

    /** default constructor */
    public IndicesAcrescimosImpontualidade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public BigDecimal getPercentualMulta() {
        return this.percentualMulta;
    }

    public void setPercentualMulta(BigDecimal percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public BigDecimal getPercentualJurosMora() {
        return this.percentualJurosMora;
    }

    public void setPercentualJurosMora(BigDecimal percentualJurosMora) {
        this.percentualJurosMora = percentualJurosMora;
    }

    public BigDecimal getFatorAtualizacaoMonetaria() {
        return this.fatorAtualizacaoMonetaria;
    }

    public void setFatorAtualizacaoMonetaria(BigDecimal fatorAtualizacaoMonetaria) {
        this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo indicadorJurosMensal.
	 */
	public Short getIndicadorJurosMensal() {
		return indicadorJurosMensal;
	}

	/**
	 * @param indicadorJurosMensal O indicadorJurosMensal a ser setado.
	 */
	public void setIndicadorJurosMensal(Short indicadorJurosMensal) {
		this.indicadorJurosMensal = indicadorJurosMensal;
	}

	/**
	 * @return Retorna o campo indicadorMultaMensal.
	 */
	public Short getIndicadorMultaMensal() {
		return indicadorMultaMensal;
	}

	/**
	 * @param indicadorMultaMensal O indicadorMultaMensal a ser setado.
	 */
	public void setIndicadorMultaMensal(Short indicadorMultaMensal) {
		this.indicadorMultaMensal = indicadorMultaMensal;
	}

	/**
	 * @return Retorna o campo percentualLimiteJuros.
	 */
	public BigDecimal getPercentualLimiteJuros() {
		return percentualLimiteJuros;
	}

	/**
	 * @param percentualLimiteJuros O percentualLimiteJuros a ser setado.
	 */
	public void setPercentualLimiteJuros(BigDecimal percentualLimiteJuros) {
		this.percentualLimiteJuros = percentualLimiteJuros;
	}

	/**
	 * @return Retorna o campo percentualLimiteMulta.
	 */
	public BigDecimal getPercentualLimiteMulta() {
		return percentualLimiteMulta;
	}

	/**
	 * @param percentualLimiteMulta O percentualLimiteMulta a ser setado.
	 */
	public void setPercentualLimiteMulta(BigDecimal percentualLimiteMulta) {
		this.percentualLimiteMulta = percentualLimiteMulta;
	}

}
