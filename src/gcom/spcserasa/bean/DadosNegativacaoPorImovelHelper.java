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
package gcom.spcserasa.bean;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0671] Gerar Movimento de Inclusão Negativação 
 *
 * @author Marcio Roberto
 * @date 21/11/2007
 */
public class DadosNegativacaoPorImovelHelper {

	private Integer idCliente;
	private Integer idImovel;
	private String cpfCliente;
	private String cnpjCliente;
	private List colecaoConta;
	private List colecaoGuias;
	private Integer qtdItensDebitoImovel;
	private BigDecimal totalDebitosImovel;
	private Integer idClienteRelacaoTipo;

	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper( 
			Integer idCliente,
			String cpfCliente,
			String cnpjCliente){
		this.idCliente = idCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
	}
	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper(){ 
	}
	
	
    /**
     *  Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof DadosNegativacaoPorImovelHelper)) {
            return false;
        }
        DadosNegativacaoPorImovelHelper castOther = (DadosNegativacaoPorImovelHelper) other;

        return new EqualsBuilder().append(this.getIdImovel(), castOther.getIdImovel())
                .isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getIdImovel())
                .toHashCode();
    }

	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getQtdItensDebitoImovel() {
		return qtdItensDebitoImovel;
	}
	public void setQtdItensDebitoImovel(Integer qtdItensDebitoImovel) {
		this.qtdItensDebitoImovel = qtdItensDebitoImovel;
	}
	public BigDecimal getTotalDebitosImovel() {
		return totalDebitosImovel;
	}
	public void setTotalDebitosImovel(BigDecimal totalDebitosImovel) {
		this.totalDebitosImovel = totalDebitosImovel;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo colecaoConta.
	 */
	public List getColecaoConta() {
		return colecaoConta;
	}
	/**
	 * @param colecaoConta O colecaoConta a ser setado.
	 */
	public void setColecaoConta(List colecaoConta) {
		this.colecaoConta = colecaoConta;
	}
	/**
	 * @return Retorna o campo colecaoGuias.
	 */
	public List getColecaoGuias() {
		return colecaoGuias;
	}
	/**
	 * @param colecaoGuias O colecaoGuias a ser setado.
	 */
	public void setColecaoGuias(List colecaoGuias) {
		this.colecaoGuias = colecaoGuias;
	}
	public Integer getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}
	public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}
	
}