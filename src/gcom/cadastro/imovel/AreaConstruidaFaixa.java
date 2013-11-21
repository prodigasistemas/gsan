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
package gcom.cadastro.imovel;

import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 */
public class AreaConstruidaFaixa  extends TabelaAuxiliarFaixaInteiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*private static final long serialVersionUID = 1L;

	*//**
     * identifier field
     *//*
    private Integer id;

    *//**
     * nullable persistent field
     *//*
    private Integer menorFaixa;

    *//**
     * nullable persistent field
     *//*
    private Integer maiorFaixa;

    *//**
     * nullable persistent field
     *//*
    private Short indicadorUso;

    *//**
     * nullable persistent field
     *//*
    private Date ultimaAlteracao;

    //atributo para fazer a junçao das faixas
    private String faixaCompleta;

    *//**
     * full constructor
     * 
     * @param menorFaixa
     *            Descrição do parâmetro
     * @param maiorFaixa
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     *//*
    public AreaConstruidaFaixa(Integer menorFaixa, Integer maiorFaixa,
            Short indicadorUso, Date ultimaAlteracao) {
        this.menorFaixa = menorFaixa;
        this.maiorFaixa = maiorFaixa;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//**
     * default constructor
     *//*
    public AreaConstruidaFaixa() {
    }

    *//**
     * Retorna o valor de id
     * 
     * @return O valor de id
     *//*
    public Integer getId() {
        return this.id;
    }

    *//**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     *//*
    public void setId(Integer id) {
        this.id = id;
    }

    *//**
     * Retorna o valor de menorFaixa
     * 
     * @return O valor de menorFaixa
     *//*
    public Integer getMenorFaixa() {
        return this.menorFaixa;
    }

    *//**
     * Seta o valor de menorFaixa
     * 
     * @param menorFaixa
     *            O novo valor de menorFaixa
     *//*
    public void setMenorFaixa(Integer menorFaixa) {
        this.menorFaixa = menorFaixa;
    }

    *//**
     * Retorna o valor de maiorFaixa
     * 
     * @return O valor de maiorFaixa
     *//*
    public Integer getMaiorFaixa() {
        return this.maiorFaixa;
    }

    *//**
     * Seta o valor de maiorFaixa
     * 
     * @param maiorFaixa
     *            O novo valor de maiorFaixa
     *//*
    public void setMaiorFaixa(Integer maiorFaixa) {
        this.maiorFaixa = maiorFaixa;
    }

    *//**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     *//*
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    *//**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     *//*
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    *//**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     *//*
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    *//**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     *//*
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     *//*
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    *//**
     * Retorna o valor de faixaCompleta
     * 
     * @return O valor de faixaCompleta
     *//*
    public String getFaixaCompleta() {
        faixaCompleta = this.getMenorFaixa() + " a " + this.getMaiorFaixa()
                + "m2";
        return faixaCompleta;
    }

    *//**
     * Seta o valor de faixaCompleta
     * 
     * @param faixaCompleta
     *            O novo valor de faixaCompleta
     *//*
    public void setFaixaCompleta(String faixaCompleta) {
        this.faixaCompleta = faixaCompleta;
    }

    *//**
     * Description of the Method
     * 
     * @return Description of the Return Value
     *//*
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getMenorFaixa())
                .append(getMaiorFaixa()).append(getFaixaCompleta()).append(
                        getIndicadorUso()).append(getUltimaAlteracao())
                .toHashCode();
    }

    *//**
     * < <Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     *//*
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof AreaConstruidaFaixa)) {
            return false;
        }
        AreaConstruidaFaixa castOther = (AreaConstruidaFaixa) other;

        return ((this.getId().equals(castOther.getId()))
                && (this.getMenorFaixa().equals(castOther.getMaiorFaixa())) && (this
                .getFaixaCompleta().equals(castOther.getFaixaCompleta()) && (this
                .getIndicadorUso().equals(castOther.getIndicadorUso()))));
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    */
}
