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
package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoCobradoCategoria extends ObjetoTransacao{
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.debito.DebitoCobradoCategoriaPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** nullable persistent field */
    private gcom.faturamento.debito.DebitoCobrado debitoCobrado;

    /** nullable persistent field */
    private Categoria categoria;

    /** full constructor */
    public DebitoCobradoCategoria(gcom.faturamento.debito.DebitoCobradoCategoriaPK comp_id, Integer quantidadeEconomia, Date ultimaAlteracao, BigDecimal valorCategoria, gcom.faturamento.debito.DebitoCobrado debitoCobrado, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorCategoria = valorCategoria;
        this.debitoCobrado = debitoCobrado;
        this.categoria = categoria;
    }

    /** default constructor */
    public DebitoCobradoCategoria() {
    }

    /** minimal constructor */
    public DebitoCobradoCategoria(gcom.faturamento.debito.DebitoCobradoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.faturamento.debito.DebitoCobradoCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.debito.DebitoCobradoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorCategoria() {
        return this.valorCategoria;
    }

    public void setValorCategoria(BigDecimal valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public gcom.faturamento.debito.DebitoCobrado getDebitoCobrado() {
        return this.debitoCobrado;
    }

    public void setDebitoCobrado(gcom.faturamento.debito.DebitoCobrado debitoCobrado) {
        this.debitoCobrado = debitoCobrado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoCobradoCategoria) ) return false;
        DebitoCobradoCategoria castOther = (DebitoCobradoCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDebitoCobradoCategoria filtro = new FiltroDebitoCobradoCategoria();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroDebitoCobradoCategoria.DEBITO_COBRADO_ID, this.getComp_id()));
		return filtro; 
	}
	   
}
