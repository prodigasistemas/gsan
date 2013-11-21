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

import gcom.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroTipo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoHidrometro implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private short numeroAnoFabricacao;

    /** nullable persistent field */
    private Short indicadorMacro;

    /** persistent field */
    private int quantidadeHidrometro;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GHidrometroMarca gerHidrometroMarca;

    /** persistent field */
    private GHidrometroSituacao gerHidrometroSituacao;

    /** persistent field */
    private GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem;

    /** persistent field */
    private GHidrometroTipo gerHidrometroTipo;

    /** persistent field */
    private GHidrometroDiametro gerHidrometroDiametro;

    /** persistent field */
    private GHidrometroCapacidade gerHidrometroCapacidade;
    
    /** persistent field */
    private GHidrometroMotivoBaixa gerHidrometroMotivoBaixa;
    
    /** persistent field */
    private GHidrometroClasseMetrologica gerHidrometroClasseMetrologica;

    /** full constructor */
    public UnResumoHidrometro(Integer id, int anoMesReferencia, short numeroAnoFabricacao, Short indicadorMacro, int quantidadeHidrometro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.indicadorMacro = indicadorMacro;
        this.quantidadeHidrometro = quantidadeHidrometro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }

    /** default constructor */
    public UnResumoHidrometro() {
    }

    /** minimal constructor */
    public UnResumoHidrometro(Integer id, int anoMesReferencia, short numeroAnoFabricacao, int quantidadeHidrometro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.quantidadeHidrometro = quantidadeHidrometro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }
    
    /** full constructor */
    public UnResumoHidrometro(int anoMesReferencia, short numeroAnoFabricacao, Short indicadorMacro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade, int quantidadeHidrometro) {

        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.indicadorMacro = indicadorMacro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
        this.quantidadeHidrometro = quantidadeHidrometro;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public short getNumeroAnoFabricacao() {
        return this.numeroAnoFabricacao;
    }

    public void setNumeroAnoFabricacao(short numeroAnoFabricacao) {
        this.numeroAnoFabricacao = numeroAnoFabricacao;
    }

    public Short getIndicadorMacro() {
        return this.indicadorMacro;
    }

    public void setIndicadorMacro(Short indicadorMacro) {
        this.indicadorMacro = indicadorMacro;
    }

    public int getQuantidadeHidrometro() {
        return this.quantidadeHidrometro;
    }

    public void setQuantidadeHidrometro(int quantidadeHidrometro) {
        this.quantidadeHidrometro = quantidadeHidrometro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GHidrometroMarca getGerHidrometroMarca() {
        return this.gerHidrometroMarca;
    }

    public void setGerHidrometroMarca(GHidrometroMarca gerHidrometroMarca) {
        this.gerHidrometroMarca = gerHidrometroMarca;
    }

    public GHidrometroSituacao getGerHidrometroSituacao() {
        return this.gerHidrometroSituacao;
    }

    public void setGerHidrometroSituacao(GHidrometroSituacao gerHidrometroSituacao) {
        this.gerHidrometroSituacao = gerHidrometroSituacao;
    }

    public GHidrometroLocalArmazenagem getGerHidrometroLocalArmazenagem() {
        return this.gerHidrometroLocalArmazenagem;
    }

    public void setGerHidrometroLocalArmazenagem(GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem) {
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
    }

    public GHidrometroTipo getGerHidrometroTipo() {
        return this.gerHidrometroTipo;
    }

    public void setGerHidrometroTipo(GHidrometroTipo gerHidrometroTipo) {
        this.gerHidrometroTipo = gerHidrometroTipo;
    }

    public GHidrometroDiametro getGerHidrometroDiametro() {
        return this.gerHidrometroDiametro;
    }

    public void setGerHidrometroDiametro(GHidrometroDiametro gerHidrometroDiametro) {
        this.gerHidrometroDiametro = gerHidrometroDiametro;
    }

    public GHidrometroCapacidade getGerHidrometroCapacidade() {
        return this.gerHidrometroCapacidade;
    }

    public void setGerHidrometroCapacidade(GHidrometroCapacidade gerHidrometroCapacidade) {
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GHidrometroClasseMetrologica getGerHidrometroClasseMetrologica() {
		return gerHidrometroClasseMetrologica;
	}

	public void setGerHidrometroClasseMetrologica(
			GHidrometroClasseMetrologica gerHidrometroClasseMetrologica) {
		this.gerHidrometroClasseMetrologica = gerHidrometroClasseMetrologica;
	}

	public GHidrometroMotivoBaixa getGerHidrometroMotivoBaixa() {
		return gerHidrometroMotivoBaixa;
	}

	public void setGerHidrometroMotivoBaixa(
			GHidrometroMotivoBaixa gerHidrometroMotivoBaixa) {
		this.gerHidrometroMotivoBaixa = gerHidrometroMotivoBaixa;
	}

}
