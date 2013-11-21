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
package gcom.gerencial.micromedicao;

import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.faturamento.GFaturamentoGrupo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GRota implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short codigoRota;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerQuadra;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;
    
    private GFaturamentoGrupo gerFaturamentoGrupo;

    public GFaturamentoGrupo getGerFaturamentoGrupo() {
		return gerFaturamentoGrupo;
	}



	public void setGerFaturamentoGrupo(GFaturamentoGrupo gerFaturamentoGrupo) {
		this.gerFaturamentoGrupo = gerFaturamentoGrupo;
	}



	/** full constructor */
    public GRota(Date ultimaAlteracao, short codigoRota, GSetorComercial GSetorComercial, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gQuadra) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoRota = codigoRota;
        this.gerSetorComercial = GSetorComercial;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerQuadra = gQuadra;
    }

    /** default constructor */
    public GRota() {
    }

  
    public short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Set getGerQuadra() {
		return gerQuadra;
	}

	public void setGerQuadra(Set gerQuadra) {
		this.gerQuadra = gerQuadra;
	}

	public GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	

	public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoColetaEsgotos() {
		return unResumoColetaEsgotos;
	}

	public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
		this.unResumoColetaEsgotos = unResumoColetaEsgotos;
	}

	public Set getUnResumoConsumoAguas() {
		return unResumoConsumoAguas;
	}

	public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
		this.unResumoConsumoAguas = unResumoConsumoAguas;
	}

	public Set getUnResumoFaturamentos() {
		return unResumoFaturamentos;
	}

	public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
		this.unResumoFaturamentos = unResumoFaturamentos;
	}

	public Set getUnResumoLigacaoEconomias() {
		return unResumoLigacaoEconomias;
	}

	public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
		this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return unResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		this.unResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

}
