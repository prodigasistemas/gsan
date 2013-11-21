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
package gcom.gerencial.cadastro.localidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GSetorComercial implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int codigo;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set UnResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerRotas;

    /** persistent field */
    private Set gerQuadras;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;
    

    /** full constructor */
    public GSetorComercial(int codigo, String descricao, Short indicadorUso, Date ultimaAlteracao, gcom.gerencial.cadastro.localidade.GLocalidade gLocalidade, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set UnResumoArrecadacao, Set unResumoLigacaoEconomias, Set gRotas, Set gQuadras) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLocalidade = gLocalidade;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.UnResumoArrecadacao = UnResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerRotas = gRotas;
        this.gerQuadras = gQuadras;
    }

    /** default constructor */
    public GSetorComercial() {
    }

    /** minimal constructor */
    public GSetorComercial(int codigo, String descricao, gcom.gerencial.cadastro.localidade.GLocalidade gLocalidade, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set UnResumoArrecadacao, Set unResumoLigacaoEconomias, Set gRotas, Set gQuadras) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.gerLocalidade = gLocalidade;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.UnResumoArrecadacao = UnResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerRotas = gRotas;
        this.gerQuadras = gQuadras;
    }

  
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public gcom.gerencial.cadastro.localidade.GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public Set getGerQuadras() {
		return gerQuadras;
	}

	public void setGerQuadras(Set gerQuadras) {
		this.gerQuadras = gerQuadras;
	}

	public Set getGerRotas() {
		return gerRotas;
	}

	public void setGerRotas(Set gerRotas) {
		this.gerRotas = gerRotas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	

	public Set getUnResumoArrecadacao() {
		return UnResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		UnResumoArrecadacao = unResumoArrecadacao;
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
