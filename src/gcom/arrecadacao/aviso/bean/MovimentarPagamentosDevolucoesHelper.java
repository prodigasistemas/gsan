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
package gcom.arrecadacao.aviso.bean;

import gcom.util.Util;

import java.math.BigDecimal;



public class MovimentarPagamentosDevolucoesHelper {
	
	private Integer id;
	private String tipoDocumento;
	private String mesAnoReferencia;
	private String valor;
	private String data;
	private String tipoDebito;
	private BigDecimal valorTotal;
	
	public MovimentarPagamentosDevolucoesHelper() {
	}
	/**
	 * @return Retorna o campo data.
	 */

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	/**
	 * @param mesAnoReferencia O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}
	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	/**
	 * @return Retorna o campo tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento O tipoDocumento a ser setado.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Retorna o campo data.
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data O data a ser setado.
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo idAvisoBancario.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param idAvisoBancario O idAvisoBancario a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	} 	
	
	/**
	 * @return Retorna o campo valorTotal.
	 */
	public String getValorTotal() {
		return Util.formatarMoedaReal(valorTotal);
	}
	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valor) {
		if(valorTotal == null || valorTotal.equals("")){
			valorTotal = new BigDecimal("0.00");	
		}
		this.valorTotal = valorTotal.add(valor);
	}
	
	public boolean equals(Object obj) {

		if (!(obj instanceof MovimentarPagamentosDevolucoesHelper)) {
			return false;
		} else {
			MovimentarPagamentosDevolucoesHelper resumoTemp = (MovimentarPagamentosDevolucoesHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return propriedadesIguais(this.id,resumoTemp.id);

		}
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(Integer pro1, Integer pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
}
