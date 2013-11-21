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
package gcom.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoTarifaCategoria implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer numeroConsumoMinimo;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinima;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private Subcategoria subCategoria;
    
    private Set consumoTarifaFaixas;

    /** full constructor */
    public ConsumoTarifaCategoria(Integer numeroConsumoMinimo, 
    	BigDecimal valorTarifaMinima, Date ultimaAlteracao, 
    	gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia,Categoria categoria) {
        
    	this.numeroConsumoMinimo = numeroConsumoMinimo;
        this.valorTarifaMinima = valorTarifaMinima;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoTarifaVigencia = consumoTarifaVigencia;
        this.categoria = categoria;
    }

    /** default constructor */
    public ConsumoTarifaCategoria() {
    }

    /** minimal constructor */
    public ConsumoTarifaCategoria(gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria) {
        this.consumoTarifaVigencia = consumoTarifaVigencia;
        this.categoria = categoria;
    }
    
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ConsumoTarifaCategoria)) {
            return false;
        }
        ConsumoTarifaCategoria castOther = (ConsumoTarifaCategoria) other;

        return (this.getCategoria().getId().equals(castOther.getCategoria().getId()) &&
        		this.getSubCategoria().getId().equals(castOther.getSubCategoria().getId()));
    }
    
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroConsumoMinimo() {
        return this.numeroConsumoMinimo;
    }

    public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
        this.numeroConsumoMinimo = numeroConsumoMinimo;
    }

    public BigDecimal getValorTarifaMinima() {
        return this.valorTarifaMinima;
    }

    public void setValorTarifaMinima(BigDecimal valorTarifaMinima) {
        this.valorTarifaMinima = valorTarifaMinima;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.consumotarifa.ConsumoTarifaVigencia getConsumoTarifaVigencia() {
        return this.consumoTarifaVigencia;
    }

    public void setConsumoTarifaVigencia(gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia) {
        this.consumoTarifaVigencia = consumoTarifaVigencia;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getConsumoTarifaFaixas() {
		return consumoTarifaFaixas;
	}

	public void setConsumoTarifaFaixas(Set consumoTarifaFaixas) {
		this.consumoTarifaFaixas = consumoTarifaFaixas;
	}

	public Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

}
