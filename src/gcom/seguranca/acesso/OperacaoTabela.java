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
package gcom.seguranca.acesso;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.Tabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoTabela extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private OperacaoTabelaPK comp_id;

    /** nullable persistent field */
    private Tabela tabela;

    /** nullable persistent field */
    private Operacao operacao;

    /** nullable persistent field */
	private Date ultimaAlteracao;
	
    /** full constructor */
    public OperacaoTabela(OperacaoTabelaPK comp_id, Tabela tabela, Operacao operacao) {
        this.comp_id = comp_id;
        this.tabela = tabela;
        this.operacao = operacao;
    }

    /** default constructor */
    public OperacaoTabela() {
    }

    /** minimal constructor */
    public OperacaoTabela(OperacaoTabelaPK comp_id) {
        this.comp_id = comp_id;
    }

    public OperacaoTabelaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(OperacaoTabelaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Tabela getTabela() {
        return this.tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

    public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OperacaoTabela) ) return false;
        OperacaoTabela castOther = (OperacaoTabela) other;
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
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
		
		filtroOperacaoTabela. adicionarCaminhoParaCarregamentoEntidade("operacao");
		filtroOperacaoTabela. adicionarCaminhoParaCarregamentoEntidade("tabela");
		new ParametroSimples(FiltroOperacaoTabela.OPERACAO_ID, this.getOperacao().getId());
		new ParametroSimples(FiltroOperacaoTabela.TABELA_ID, this.getTabela().getId());
		return filtroOperacaoTabela; 
	}
}