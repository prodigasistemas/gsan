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
package gcom.cadastro.dadocensitario;

import gcom.cadastro.localidade.Localidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LocalidadeDadosCensitario implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Integer numeroPopulacaoUrbana;

    /** nullable persistent field */
    private BigDecimal taxaAnualCrescimentoPopulacaoUrbana;

    /** nullable persistent field */
    private BigDecimal taxaOcupacaoUrbana;

    /** nullable persistent field */
    private Integer numeroPopulacaoRural;

    /** nullable persistent field */
    private BigDecimal taxaCrescimentoPopulacaoRural;

    /** nullable persistent field */
    private BigDecimal taxaOcupacaoRural;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario;

    /** full constructor */
    public LocalidadeDadosCensitario(
            int anoMesReferencia,
            Integer numeroPopulacaoUrbana,
            BigDecimal taxaAnualCrescimentoPopulacaoUrbana,
            BigDecimal taxaOcupacaoUrbana,
            Integer numeroPopulacaoRural,
            BigDecimal taxaCrescimentoPopulacaoRural,
            BigDecimal taxaOcupacaoRural,
            Date ultimaAlteracao,
            Localidade localidade,
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.anoMesReferencia = anoMesReferencia;
        this.numeroPopulacaoUrbana = numeroPopulacaoUrbana;
        this.taxaAnualCrescimentoPopulacaoUrbana = taxaAnualCrescimentoPopulacaoUrbana;
        this.taxaOcupacaoUrbana = taxaOcupacaoUrbana;
        this.numeroPopulacaoRural = numeroPopulacaoRural;
        this.taxaCrescimentoPopulacaoRural = taxaCrescimentoPopulacaoRural;
        this.taxaOcupacaoRural = taxaOcupacaoRural;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.fonteDadosCensitario = fonteDadosCensitario;
    }

    /** default constructor */
    public LocalidadeDadosCensitario() {
    }

    /** minimal constructor */
    public LocalidadeDadosCensitario(
            int anoMesReferencia,
            Localidade localidade,
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.anoMesReferencia = anoMesReferencia;
        this.localidade = localidade;
        this.fonteDadosCensitario = fonteDadosCensitario;
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

    public Integer getNumeroPopulacaoUrbana() {
        return this.numeroPopulacaoUrbana;
    }

    public void setNumeroPopulacaoUrbana(Integer numeroPopulacaoUrbana) {
        this.numeroPopulacaoUrbana = numeroPopulacaoUrbana;
    }

    public BigDecimal getTaxaAnualCrescimentoPopulacaoUrbana() {
        return this.taxaAnualCrescimentoPopulacaoUrbana;
    }

    public void setTaxaAnualCrescimentoPopulacaoUrbana(
            BigDecimal taxaAnualCrescimentoPopulacaoUrbana) {
        this.taxaAnualCrescimentoPopulacaoUrbana = taxaAnualCrescimentoPopulacaoUrbana;
    }

    public BigDecimal getTaxaOcupacaoUrbana() {
        return this.taxaOcupacaoUrbana;
    }

    public void setTaxaOcupacaoUrbana(BigDecimal taxaOcupacaoUrbana) {
        this.taxaOcupacaoUrbana = taxaOcupacaoUrbana;
    }

    public Integer getNumeroPopulacaoRural() {
        return this.numeroPopulacaoRural;
    }

    public void setNumeroPopulacaoRural(Integer numeroPopulacaoRural) {
        this.numeroPopulacaoRural = numeroPopulacaoRural;
    }

    public BigDecimal getTaxaCrescimentoPopulacaoRural() {
        return this.taxaCrescimentoPopulacaoRural;
    }

    public void setTaxaCrescimentoPopulacaoRural(
            BigDecimal taxaCrescimentoPopulacaoRural) {
        this.taxaCrescimentoPopulacaoRural = taxaCrescimentoPopulacaoRural;
    }

    public BigDecimal getTaxaOcupacaoRural() {
        return this.taxaOcupacaoRural;
    }

    public void setTaxaOcupacaoRural(BigDecimal taxaOcupacaoRural) {
        this.taxaOcupacaoRural = taxaOcupacaoRural;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public gcom.cadastro.dadocensitario.FonteDadosCensitario getFonteDadosCensitario() {
        return this.fonteDadosCensitario;
    }

    public void setFonteDadosCensitario(
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.fonteDadosCensitario = fonteDadosCensitario;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
