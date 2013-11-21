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
package gcom.cobranca;

import java.math.BigDecimal;
import java.util.Date;

public class InformarContasEmCobrancaHelper {
	
	private Integer idImovel;
	private Integer idCliente;
	private Integer idUnidadeNegocio;
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer codigoSetorComercialInicial;
	private Integer codigoSetorComercialFinal;
	private Integer referenciaInicial;
	private Integer referenciaFinal;
	private Date dataVencimentoInicial;
	private Date dataVencimentoFinal;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	
	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	/**
	 * @return Retorna o campo dataVencimentoFinal.
	 */
	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	/**
	 * @param dataVencimentoFinal O dataVencimentoFinal a ser setado.
	 */
	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	/**
	 * @return Retorna o campo dataVencimentoInicial.
	 */
	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	/**
	 * @param dataVencimentoInicial O dataVencimentoInicial a ser setado.
	 */
	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	/**
	 * @return Retorna o campo idCliente.
	 */
	public Integer getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public Integer getReferenciaFinal() {
		return referenciaFinal;
	}
	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(Integer referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}
	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public Integer getReferenciaInicial() {
		return referenciaInicial;
	}
	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(Integer referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}
	/**
	 * @return Retorna o campo valorMaximo.
	 */
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}
	/**
	 * @param valorMaximo O valorMaximo a ser setado.
	 */
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	/**
	 * @return Retorna o campo valorMinimo.
	 */
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	/**
	 * @param valorMinimo O valorMinimo a ser setado.
	 */
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}


}
