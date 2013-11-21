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
package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoriaHistoricoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idGuiaPagamentoHistorico;

    /** identifier field */
    private Integer idCategoria;

    public GuiaPagamentoCategoriaHistoricoPK() {
    }
    
    public GuiaPagamentoCategoriaHistoricoPK(Integer idCategoria, Integer idGuiaPagamentoHistorico) {
        this.idCategoria = idCategoria;
        this.idGuiaPagamentoHistorico = idGuiaPagamentoHistorico;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("idGuiaPagamentoHistorico", getIdGuiaPagamentoHistorico())
            .append("idCategoria", getIdCategoria())
            .toString();
    }

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idGuiaPagamento.
	 */
	public Integer getIdGuiaPagamentoHistorico() {
		return idGuiaPagamentoHistorico;
	}

	/**
	 * @param idGuiaPagamentoHistorico O idGuiaPagamentoHistorico a ser setado.
	 */
	public void setIdGuiaPagamentoHistorico(Integer idGuiaPagamentoHistorico) {
		this.idGuiaPagamentoHistorico = idGuiaPagamentoHistorico;
	}
	
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoCategoriaHistoricoPK) ) return false;
        GuiaPagamentoCategoriaHistoricoPK castOther = (GuiaPagamentoCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getIdGuiaPagamentoHistorico(), castOther.getIdGuiaPagamentoHistorico())
            .append(this.getIdCategoria(), castOther.getIdCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdGuiaPagamentoHistorico())
            .append(getIdCategoria())
            .toHashCode();
    }
	

}
