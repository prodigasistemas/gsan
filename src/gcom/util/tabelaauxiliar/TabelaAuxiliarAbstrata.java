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
package gcom.util.tabelaauxiliar;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Classe abstrata que serve como base para todas as tabelas auxiliares do
 * sistema. Classes abstratas não podem ser instanciadas.
 * 
 * @author Administrador
 */
public abstract class TabelaAuxiliarAbstrata extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * De acordo com o manual do hibernate o id deve ser do tipo Integer
     */
    protected Integer id;

    /**
     * Description of the Field
     */
    protected Short indicadorUso;

    /**
     * persistent field
     */
    protected Date ultimaAlteracao;

    /**
     * Construtor da classe TabelaAuxiliarAbstrata
     * 
     * @param id
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public TabelaAuxiliarAbstrata(Integer id, Short indicadorUso,
            Date ultimaAlteracao) {
        this.id = id;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliarAbstrata() {
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * < <Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof TabelaAuxiliarAbstrata)) {
            return false;
        }
        TabelaAuxiliarAbstrata castOther = (TabelaAuxiliarAbstrata) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroTabelaAuxiliar();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliar.ID, this.getId()));
		return filtro;
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}
