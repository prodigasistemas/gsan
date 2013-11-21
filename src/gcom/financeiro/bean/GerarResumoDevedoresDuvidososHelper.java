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
package gcom.financeiro.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;

public class GerarResumoDevedoresDuvidososHelper {

	private Integer idLocalidade;
	
	private Integer idGerenciaRegional;
	
	private Integer idCategoria;
	
	private Integer idLancamentoTipo;
	
	private Integer idLancamentoItem;
	
	private Integer idLancamentoItemContabil;
	
	private Short numeroSequenciaTipoLancamento;
	
	private Short numeroSequencialItemTipoLancamento;
	
	private BigDecimal valorBaixado;
	
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdLancamentoItem() {
		return idLancamentoItem;
	}

	public void setIdLancamentoItem(Integer idLancamentoItem) {
		this.idLancamentoItem = idLancamentoItem;
	}

	public Integer getIdLancamentoItemContabil() {
		return idLancamentoItemContabil;
	}

	public void setIdLancamentoItemContabil(Integer idLancamentoItemContabil) {
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public Integer getIdLancamentoTipo() {
		return idLancamentoTipo;
	}

	public void setIdLancamentoTipo(Integer idLancamentoTipo) {
		this.idLancamentoTipo = idLancamentoTipo;
	}

	public Short getNumeroSequencialItemTipoLancamento() {
		return numeroSequencialItemTipoLancamento;
	}

	public void setNumeroSequencialItemTipoLancamento(
			Short numeroSequencialItemTipoLancamento) {
		this.numeroSequencialItemTipoLancamento = numeroSequencialItemTipoLancamento;
	}

	public Short getNumeroSequenciaTipoLancamento() {
		return numeroSequenciaTipoLancamento;
	}

	public void setNumeroSequenciaTipoLancamento(
			Short numeroSequenciaTipoLancamento) {
		this.numeroSequenciaTipoLancamento = numeroSequenciaTipoLancamento;
	}

	public BigDecimal getValorBaixado() {
		return valorBaixado;
	}

	public void setValorBaixado(BigDecimal valorBaixado) {
		this.valorBaixado = valorBaixado;
	}

	
	
	/**
	 * Construtor de GerarResumoDevedoresDuvidososHelper 
	 * 
	 * @param idLocalidade
	 * @param idGerenciaRegional
	 * @param idCategoria
	 * @param idLancamentoTipo
	 * @param idLancamentoItem
	 * @param idLancamentoItemContabil
	 * @param numeroSequenciaTipoLancamento
	 * @param numeroSequencialItemTipoLancamento
	 * @param valorBaixado
	 */
	public GerarResumoDevedoresDuvidososHelper(Integer idLocalidade, Integer idGerenciaRegional, Integer idCategoria, Integer idLancamentoTipo, Integer idLancamentoItem, Integer idLancamentoItemContabil, Short numeroSequenciaTipoLancamento, Short numeroSequencialItemTipoLancamento, BigDecimal valorBaixado) {
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idCategoria = idCategoria;
		this.idLancamentoTipo = idLancamentoTipo;
		this.idLancamentoItem = idLancamentoItem;
		this.idLancamentoItemContabil = idLancamentoItemContabil;
		this.numeroSequenciaTipoLancamento = numeroSequenciaTipoLancamento;
		this.numeroSequencialItemTipoLancamento = numeroSequencialItemTipoLancamento;
		this.valorBaixado = valorBaixado;
	}

	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	/*private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  return true;
	}	*/
	
	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	/*private boolean propriedadesIguais( Short pro1, Short pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  return true;
	}	*/
	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	/*public boolean equals( Object obj ){
		
		boolean retorno = false;
		
		if ( !( obj instanceof GerarResumoDevedoresDuvidososHelper ) ){
			return retorno;			
		} else {
			GerarResumoDevedoresDuvidososHelper temp = ( GerarResumoDevedoresDuvidososHelper ) obj;
		   
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			retorno = 
				!propriedadesIguais( this.idLocalidade, temp.idLocalidade ) ||
				!propriedadesIguais( this.idGerenciaRegional, temp.idGerenciaRegional ) ||
				!propriedadesIguais( this.idCategoria, temp.idCategoria ) ||
				!propriedadesIguais( this.idLancamentoTipo, temp.idLancamentoTipo ) ||
				!propriedadesIguais( this.idLancamentoItem, temp.idLancamentoItem ) ||
				!propriedadesIguais( this.idLancamentoItemContabil, temp.idLancamentoItemContabil ) ||
				!propriedadesIguais( this.numeroSequenciaTipoLancamento, temp.numeroSequenciaTipoLancamento ) ||
				!propriedadesIguais( this.numeroSequencialItemTipoLancamento, temp.numeroSequencialItemTipoLancamento) ;
			
			return retorno;
		}			
	}*/
	
	
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof GerarResumoDevedoresDuvidososHelper)) {
            return false;
        }
        GerarResumoDevedoresDuvidososHelper castOther = (GerarResumoDevedoresDuvidososHelper) other;

        return new EqualsBuilder()
        .append(this.getIdLocalidade(),castOther.getIdLocalidade())
        .append(this.getIdGerenciaRegional(), castOther.getIdGerenciaRegional())
        .append(this.getIdCategoria(), castOther.getIdCategoria())
        .append(this.getIdLancamentoTipo(), castOther.getIdLancamentoTipo())
        .append(this.getIdLancamentoItem(), castOther.getIdLancamentoItem())
        .append(this.getIdLancamentoItemContabil(), castOther.getIdLancamentoItemContabil())                
        .append(this.getNumeroSequenciaTipoLancamento(),castOther.getNumeroSequenciaTipoLancamento())
        .append(this.getNumeroSequencialItemTipoLancamento(), castOther.getNumeroSequencialItemTipoLancamento())
        .isEquals();
    }

}
