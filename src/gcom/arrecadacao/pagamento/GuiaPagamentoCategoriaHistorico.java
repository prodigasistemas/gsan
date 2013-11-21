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
package gcom.arrecadacao.pagamento;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoriaHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private GuiaPagamentoCategoriaHistoricoPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GuiaPagamentoHistorico guiaPagamentoHistorico;

    /** nullable persistent field */
    private Categoria categoria;


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }


	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}


	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return Retorna o campo comp_id.
	 */
	public GuiaPagamentoCategoriaHistoricoPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(GuiaPagamentoCategoriaHistoricoPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo guiaPagamentoHistorico.
	 */
	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}


	/**
	 * @param guiaPagamentoHistorico O guiaPagamentoHistorico a ser setado.
	 */
	public void setGuiaPagamentoHistorico(
			GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}


	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}


	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}


	/**
	 * @return Retorna o campo ultimaAltercao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAltercao O ultimaAltercao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	/**
	 * @return Retorna o campo valorCategoria.
	 */
	public BigDecimal getValorCategoria() {
		return valorCategoria;
	}


	/**
	 * @param valorCategoria O valorCategoria a ser setado.
	 */
	public void setValorCategoria(BigDecimal valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

}
