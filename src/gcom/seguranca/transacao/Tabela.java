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
package gcom.seguranca.transacao;

import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Tabela extends TabelaAuxiliarAbreviada {
	private static final long serialVersionUID = 1L;
	
	public static final Integer IMOVEL_ATUALIZACAO_CADASTRAL = new Integer(661);
	public static final Integer CLIENTE_ATUALIZACAO_CADASTRAL = new Integer(662);
	public static final Integer CLIENTE_FONE_ATUALIZACAO_CADASTRAL = new Integer(663);
	public static final Integer IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL = new Integer(664);
	public static final Integer IMOVEL_RAMO_ATIVIDADE_ATUALIZACAO_CADASTRAL = new Integer(665);
    
    /** nullable persistent field */
    private String nomeTabela;

    /** tabelaColunas */
    private Set tabelaColunas;

    /** full constructor */
    public Tabela(String descricaoTabela, String nomeTabela, Date ultimaAlteracao) {
        this.descricao = descricaoTabela;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	/** default constructor */
    public Tabela() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo tabelaColunas.
	 */
	public Set getTabelaColunas() {
		return tabelaColunas;
	}

	/**
	 * @param tabelaColunas O tabelaColunas a ser setado.
	 */
	public void setTabelaColunas(Set tabelaColunas) {
		this.tabelaColunas = tabelaColunas;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Tabela))
			return false;
		Tabela castOther = (Tabela) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

}
