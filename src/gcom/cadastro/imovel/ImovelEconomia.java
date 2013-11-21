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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelEconomia extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {

		FiltroImovelEconomia filtro = new FiltroImovelEconomia();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroImovelEconomia.ID, this.getId()));

		
		filtro.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");

		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria");
		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String complementoEndereco;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
    private Short numeroMorador;

    /** nullable persistent field */
    private Short numeroPontosUtilizacao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private BigDecimal numeroIptu;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private Long numeroCelpe;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private BigDecimal areaConstruida;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA, 
    		funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa;

    /** persistent field */
    private gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria;

    /** persistent field */
    private Set tarifaSocialDadoEconomias;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEconomia.CLIENTE_IMOVEL_ECONOMIA, 
    		funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private Set clienteImovelEconomias;
    
    private int codigoModificado;

    /** full constructor */
    public ImovelEconomia(String complementoEndereco, Short numeroMorador, Short numeroPontosUtilizacao, 
    		BigDecimal numeroIptu, Long numeroCelpe, BigDecimal areaConstruida, Date ultimaAlteracao, gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa, gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria, Set tarifaSocialDadoEconomias, Set clienteImovelEconomias) {
        this.complementoEndereco = complementoEndereco;
        this.numeroMorador = numeroMorador;
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
        this.numeroIptu = numeroIptu;
        this.numeroCelpe = numeroCelpe;
        this.areaConstruida = areaConstruida;
        this.ultimaAlteracao = ultimaAlteracao;
        this.areaConstruidaFaixa = areaConstruidaFaixa;
        this.imovelSubcategoria = imovelSubcategoria;
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    /** default constructor */
    public ImovelEconomia() {
    }

    /** minimal constructor */
    public ImovelEconomia(gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa, gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria, Set tarifaSocialDadoEconomias, Set clienteImovelEconomias) {
        this.areaConstruidaFaixa = areaConstruidaFaixa;
        this.imovelSubcategoria = imovelSubcategoria;
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Short getNumeroMorador() {
        return this.numeroMorador;
    }

    public void setNumeroMorador(Short numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    public Short getNumeroPontosUtilizacao() {
        return this.numeroPontosUtilizacao;
    }

    public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
    }

    public BigDecimal getNumeroIptu() {
        return this.numeroIptu;
    }

    public void setNumeroIptu(BigDecimal numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    public Long getNumeroCelpe() {
        return this.numeroCelpe;
    }

    public void setNumeroCelpe(Long numeroCelpe) {
        this.numeroCelpe = numeroCelpe;
    }

    public BigDecimal getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.imovel.AreaConstruidaFaixa getAreaConstruidaFaixa() {
        return this.areaConstruidaFaixa;
    }

    public void setAreaConstruidaFaixa(gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa) {
        this.areaConstruidaFaixa = areaConstruidaFaixa;
    }

    public gcom.cadastro.imovel.ImovelSubcategoria getImovelSubcategoria() {
        return this.imovelSubcategoria;
    }

    public void setImovelSubcategoria(gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria) {
        this.imovelSubcategoria = imovelSubcategoria;
    }

    public Set getTarifaSocialDadoEconomias() {
        return this.tarifaSocialDadoEconomias;
    }

    public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
    }

    public Set getClienteImovelEconomias() {
        return this.clienteImovelEconomias;
    }

    public void setClienteImovelEconomias(Set clienteImovelEconomias) {
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /**
      * Gets the hashCode attribute of the ImovelSubcategoria object
      * 
      * @return The hashCode value
      */
     public int getHashCode() {
         return this.hashCode();
     }

    /**
      * Description of the Method
      * 
      * @return Description of the Return Value
      */
     public int hashCode() {
         return new HashCodeBuilder().append(getId()).append(
                 getComplementoEndereco()).append(getNumeroMorador()).append(
                 getNumeroPontosUtilizacao()).append(getNumeroIptu()).append(
                 getNumeroCelpe()).append(getAreaConstruida()).append(
                 getUltimaAlteracao()).append(getAreaConstruidaFaixa()).append(
                 getImovelSubcategoria()).toHashCode();
     }
 
     /**
      * Description of the Method
      * 
      * @param other
      *            Description of the Parameter
      * @return Description of the Return Value
      */
     public boolean equals(Object other) {
         if ((this == other)) {
             return true;
         }
         if (!(other instanceof ImovelEconomia)) {
             return false;
         }
         ImovelEconomia castOther = (ImovelEconomia) other;
 
         return new EqualsBuilder().append(this.getComplementoEndereco(),
                 castOther.getComplementoEndereco()).append(
                 this.getNumeroMorador(), castOther.getNumeroMorador()).append(
                 this.getNumeroPontosUtilizacao(),
                 castOther.getNumeroPontosUtilizacao()).append(
                 this.getNumeroIptu(), castOther.getNumeroIptu()).append(
                 this.getNumeroCelpe(), castOther.getNumeroCelpe()).append(
                 this.getAreaConstruida(), castOther.getAreaConstruida())
                 .append(this.getAreaConstruidaFaixa(),
                         castOther.getAreaConstruidaFaixa()).append(
                         this.getImovelSubcategoria(),
                         castOther.getImovelSubcategoria()).isEquals();
     }

     /**
       * Gets the codigoModificado attribute of the ImovelEconomia object
       * 
       * @return The codigoModificado value
       */
      public int getCodigoModificado() {
          return codigoModificado;
      }
  
      /**
       * Sets the codigoModificado attribute of the ImovelEconomia object
       * 
       * @param codigoModificado
       *            The new codigoModificado value
       */
      public void setCodigoModificado(int codigoModificado) {
          this.codigoModificado = codigoModificado;
      }

     
    /*
	 * Construtor Anterior ao Mapeamento da Iteração 5 
	 */
    
     public ImovelEconomia(String complementoEndereco, Short numeroMorador,
             Short numeroPontosUtilizacao, BigDecimal numeroIptu,
             Long numeroCelpe, BigDecimal areaConstruida, Date ultimaAlteracao,
             gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
             gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria,
             Set clienteImovelEconomias) {
         this.complementoEndereco = complementoEndereco;
         this.numeroMorador = numeroMorador;
         this.numeroPontosUtilizacao = numeroPontosUtilizacao;
         this.numeroIptu = numeroIptu;
         this.numeroCelpe = numeroCelpe;
         this.areaConstruida = areaConstruida;
         this.ultimaAlteracao = ultimaAlteracao;
         this.areaConstruidaFaixa = areaConstruidaFaixa;
         this.imovelSubcategoria = imovelSubcategoria;
         this.clienteImovelEconomias = clienteImovelEconomias;
     }
     
     @Override
    public String getDescricaoParaRegistroTransacao() {
    	 Cliente cliente = getClienteUsuario();
    	 if (cliente != null){
    		return cliente.getDescricaoParaRegistroTransacao(); 
    	 }
    	 if (getImovelSubcategoria() != null){
    		 return getImovelSubcategoria().getDescricaoParaRegistroTransacao();	 
    	 }
    	 return "";    	 
    }
     
     @Override
    public void initializeLazy() {
    	 if (getImovelSubcategoria() != null){
    		 getImovelSubcategoria().initializeLazy();
    	 }
    	 if (getAreaConstruidaFaixa() != null){
    		 getAreaConstruidaFaixa().initializeLazy();
    	 }
    	 getDescricaoParaRegistroTransacao();
    	 initilizarCollectionLazy(clienteImovelEconomias);
    }
     
    public Cliente getClienteUsuario(){
    	if (clienteImovelEconomias != null){
    		for (Iterator iterator = clienteImovelEconomias.iterator(); iterator.hasNext();) {
				ClienteImovelEconomia clienteImovel = (ClienteImovelEconomia) iterator.next();
				if (clienteImovel != null && 
					clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()
					&& clienteImovel.getDataFimRelacao() == null){
					return clienteImovel.getCliente(); 
				}				
			} 
    	}
    	return null;
    }
}