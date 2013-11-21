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
package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaCategoriaConsumoFaixa implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer consumoFaixaInicio;

    /** nullable persistent field */
    private Integer consumoFaixaFim;

    /** nullable persistent field */
    private BigDecimal valorTarifaFaixa;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private gcom.faturamento.conta.ContaCategoria contaCategoria;
    
    /** persistent field */
    private Subcategoria subcategoria;


    /** full constructor */
    public ContaCategoriaConsumoFaixa(BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, Date ultimaAlteracao, Integer consumoFaixaInicio, Integer consumoFaixaFim, BigDecimal valorTarifaFaixa, Categoria categoria, gcom.faturamento.conta.ContaCategoria contaCategoria, Subcategoria subcategoria) {
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoFaixaInicio = consumoFaixaInicio;
        this.consumoFaixaFim = consumoFaixaFim;
        this.valorTarifaFaixa = valorTarifaFaixa;
        this.categoria = categoria;
        this.contaCategoria = contaCategoria;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public ContaCategoriaConsumoFaixa() {
    }

    /** minimal constructor */
    public ContaCategoriaConsumoFaixa(Categoria categoria, gcom.faturamento.conta.ContaCategoria contaCategoria) {
        this.categoria = categoria;
        this.contaCategoria = contaCategoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getConsumoFaixaInicio() {
        return this.consumoFaixaInicio;
    }

    public void setConsumoFaixaInicio(Integer consumoFaixaInicio) {
        this.consumoFaixaInicio = consumoFaixaInicio;
    }

    public Integer getConsumoFaixaFim() {
        return this.consumoFaixaFim;
    }

    public void setConsumoFaixaFim(Integer consumoFaixaFim) {
        this.consumoFaixaFim = consumoFaixaFim;
    }

    public BigDecimal getValorTarifaFaixa() {
        return this.valorTarifaFaixa;
    }

    public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
        this.valorTarifaFaixa = valorTarifaFaixa;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public gcom.faturamento.conta.ContaCategoria getContaCategoria() {
        return this.contaCategoria;
    }

    public void setContaCategoria(gcom.faturamento.conta.ContaCategoria contaCategoria) {
        this.contaCategoria = contaCategoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo subcategoria.
	 */
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria O subcategoria a ser setado.
	 */
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
