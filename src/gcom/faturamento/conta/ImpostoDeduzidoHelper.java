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

import java.math.BigDecimal;

/**
 * Imposto Deduzidos
 * @author Fernanda Paiva
 * @since 22/09/2006
 */
public class ImpostoDeduzidoHelper {
	
	/**
	 * idImpostoDeduzido	 
	 * */
	private Integer idImpostoTipo;
	
	/**
	 * valor total dos impostos	 
	 */
	private BigDecimal valor;

	private String descricaoImposto;
		
	/**
	 * percentual da aliquota 
	 */
	private BigDecimal percentualAliquota;
	
	private Integer idCliente;
	
	private String nomeCliente;
	
	private BigDecimal valorFatura;
	
	private Integer idImovel;
	
	private String cnpjCliente;
	
	/*
	 * Coleção de Notas Promissorias?
	 */
	//private Collection<NotaPromissoria> colecaoNotasPromissorias;

	/**
	 * Constructor
	 */
	public ImpostoDeduzidoHelper() {
		
	}
	
	/**
	 * @param idImpostoTipo
	 * @param valorTotalImposto
	 * @param percentualAliquota
	 */
	public ImpostoDeduzidoHelper(Integer idImpostoTipo, BigDecimal valor,
			BigDecimal percentualAliquota) {
		this.idImpostoTipo = idImpostoTipo;
		this.valor = valor;
		this.percentualAliquota = percentualAliquota;
	}
	
	

	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
	}

	/**
	 * @return Returns the idImpostoTipo.
	 */
	public Integer getIdImpostoTipo() {
		return idImpostoTipo;
	}

	/**
	 * @param idImpostoTipo The idImpostoTipo to set.
	 */
	public void setIdImpostoTipo(
			Integer idImpostoTipo) {
		this.idImpostoTipo = idImpostoTipo;
	}

	/**
	 * @return Returns the valorTotalImposto.
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valorTotalImposto The valorTotalImposto to set.
	 */
	public void setValor(
			BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return Returns the percentualAliquota.
	 */
	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * @param percentualAliquota The percentualAliquota to set.
	 */
	public void setPercentualAliquota(
			BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoImposto == null) ? 0 : descricaoImposto.hashCode());
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result
				+ ((idImpostoTipo == null) ? 0 : idImpostoTipo.hashCode());
		result = prime * result
				+ ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime
				* result
				+ ((percentualAliquota == null) ? 0 : percentualAliquota
						.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImpostoDeduzidoHelper other = (ImpostoDeduzidoHelper) obj;
		if (descricaoImposto == null) {
			if (other.descricaoImposto != null)
				return false;
		} else if (!descricaoImposto.equals(other.descricaoImposto))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (idImpostoTipo == null) {
			if (other.idImpostoTipo != null)
				return false;
		} else if (!idImpostoTipo.equals(other.idImpostoTipo))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (percentualAliquota == null) {
			if (other.percentualAliquota != null)
				return false;
		} else if (!percentualAliquota.equals(other.percentualAliquota))
			return false;
		
		return true;
	}
	
}
