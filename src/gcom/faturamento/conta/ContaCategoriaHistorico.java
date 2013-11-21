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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;



/** @author Hibernate CodeGenerator */
public class ContaCategoriaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private ContaCategoriaHistoricoPK comp_id;

    /** persistent field */
    private short quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinimaAgua;

    /** nullable persistent field */
    private Integer consumoMinimoAgua;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinimaEsgoto;

    /** nullable persistent field */
    private Integer consumoMinimoEsgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Set contaCategoriaConsumoFaixasHistorico;

    /** full constructor */
    public ContaCategoriaHistorico(ContaCategoriaHistoricoPK comp_id, short quantidadeEconomia, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorTarifaMinimaAgua, Integer consumoMinimoAgua, BigDecimal valorTarifaMinimaEsgoto, Integer consumoMinimoEsgoto, Date ultimaAlteracao, Set contaCategoriaConsumoFaixasHistorico) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
        this.consumoMinimoAgua = consumoMinimoAgua;
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    /** default constructor */
    public ContaCategoriaHistorico() {
    }

    /** minimal constructor */
    public ContaCategoriaHistorico(ContaCategoriaHistoricoPK comp_id, short quantidadeEconomia, Date ultimaAlteracao, Set contaCategoriaConsumoFaixasHistorico) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    public Set getContaCategoriaConsumoFaixasHistorico() {
        return contaCategoriaConsumoFaixasHistorico;
    }

    public void setContaCategoriaConsumoFaixasHistorico(Set contaCategoriaConsumoFaixasHistorico) {
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    public ContaCategoriaHistoricoPK getComp_id() {
        return comp_id;
    }

    public void setComp_id(ContaCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public Integer getConsumoEsgoto() {
        return consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Integer getConsumoMinimoAgua() {
        return consumoMinimoAgua;
    }

    public void setConsumoMinimoAgua(Integer consumoMinimoAgua) {
        this.consumoMinimoAgua = consumoMinimoAgua;
    }

    public Integer getConsumoMinimoEsgoto() {
        return consumoMinimoEsgoto;
    }

    public void setConsumoMinimoEsgoto(Integer consumoMinimoEsgoto) {
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
    }

    public short getQuantidadeEconomia() {
        return quantidadeEconomia;
    }

    public void setQuantidadeEconomia(short quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorAgua() {
        return valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public BigDecimal getValorEsgoto() {
        return valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public BigDecimal getValorTarifaMinimaAgua() {
        return valorTarifaMinimaAgua;
    }

    public void setValorTarifaMinimaAgua(BigDecimal valorTarifaMinimaAgua) {
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
    }

    public BigDecimal getValorTarifaMinimaEsgoto() {
        return valorTarifaMinimaEsgoto;
    }

    public void setValorTarifaMinimaEsgoto(BigDecimal valorTarifaMinimaEsgoto) {
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ContaCategoriaHistorico) ) return false;
        ContaCategoriaHistorico castOther = (ContaCategoriaHistorico) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
}
