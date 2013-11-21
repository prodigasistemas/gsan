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
package gcom.gerencial.arrecadacao;

import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacaoOutro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id;

    /** persistent field */
    private BigDecimal volumeDocumentosRecebidos;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** nullable persistent field */
    private gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto;

    /** nullable persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;

    /** full constructor */
    public UnResumoArrecadacaoOutro(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id, BigDecimal volumeDocumentosRecebidos, Date ultimaAlteracao, GLancamentoItemContabil gLancamentoItemContabil, gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto, GFinanciamentoTipo gFinanciamentoTipo) {
        this.comp_id = comp_id;
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLancamentoItemContabil = gLancamentoItemContabil;
        this.unResumoArrecadacaoAguaEsgoto = unResumoArrecadacaoAguaEsgoto;
        this.gerFinanciamentoTipo = gFinanciamentoTipo;
    }

    /** default constructor */
    public UnResumoArrecadacaoOutro() {
    }

    /** minimal constructor */
    public UnResumoArrecadacaoOutro(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id, BigDecimal volumeDocumentosRecebidos, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getVolumeDocumentosRecebidos() {
        return this.volumeDocumentosRecebidos;
    }

    public void setVolumeDocumentosRecebidos(BigDecimal volumeDocumentosRecebidos) {
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GLancamentoItemContabil getGLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

 
    public gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto getUnResumoArrecadacaoAguaEsgoto() {
        return this.unResumoArrecadacaoAguaEsgoto;
    }

    public void setUnResumoArrecadacaoAguaEsgoto(gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto) {
        this.unResumoArrecadacaoAguaEsgoto = unResumoArrecadacaoAguaEsgoto;
    }

 
    
    public GFinanciamentoTipo getGerFinanciamentoTipo() {
		return gerFinanciamentoTipo;
	}

	public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
		this.gerFinanciamentoTipo = gerFinanciamentoTipo;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabil() {
		return gerLancamentoItemContabil;
	}

	public void setGerLancamentoItemContabil(
			GLancamentoItemContabil gerLancamentoItemContabil) {
		this.gerLancamentoItemContabil = gerLancamentoItemContabil;
	}

	public void setGFinanciamentoTipo(GFinanciamentoTipo gFinanciamentoTipo) {
        this.gerFinanciamentoTipo = gFinanciamentoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoArrecadacaoOutro) ) return false;
        UnResumoArrecadacaoOutro castOther = (UnResumoArrecadacaoOutro) other;
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
