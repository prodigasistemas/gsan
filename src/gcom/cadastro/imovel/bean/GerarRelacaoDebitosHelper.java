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
package gcom.cadastro.imovel.bean;

import java.math.BigDecimal;
import java.util.Collection;


/**
 * Débitos de um imóvel ou de um cliente
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class GerarRelacaoDebitosHelper {
	
	private GerarRelacaoDebitosImovelHelper gerarRelacaoDebitosImovelHelper;
	
	private Collection colecaoDebitosACobrarCreditoARealizar;
	
	private Collection colecaoContas;
	
	private Collection colecaoGuiasPagamento;
	
	private BigDecimal totalContas;
	
	private BigDecimal totalDebitosACobrar;
	
	private BigDecimal totalGuiasPagamento;
	
	private BigDecimal totalMulta;
	
	private BigDecimal totalJuros;
	
	private BigDecimal totalAtualizacaoMonetaria;
	
	private BigDecimal totalGeralAtualizado;

	private BigDecimal totalContaAtualizado;
	
	private BigDecimal totalCreditoARealizar;
	
	/**
	 * @return Retorna o campo colecaoContas.
	 */
	public Collection getColecaoContas() {
		return colecaoContas;
	}

	/**
	 * @param colecaoContas O colecaoContas a ser setado.
	 */
	public void setColecaoContas(Collection colecaoContas) {
		this.colecaoContas = colecaoContas;
	}

	/**
	 * @return Retorna o campo colecaoDebitosACobrarCreditoARealizar.
	 */
	public Collection getColecaoDebitosACobrarCreditoARealizar() {
		return colecaoDebitosACobrarCreditoARealizar;
	}

	/**
	 * @param colecaoDebitosACobrarCreditoARealizar O colecaoDebitosACobrarCreditoARealizar a ser setado.
	 */
	public void setColecaoDebitosACobrarCreditoARealizar(
			Collection colecaoDebitosACobrarCreditoARealizar) {
		this.colecaoDebitosACobrarCreditoARealizar = colecaoDebitosACobrarCreditoARealizar;
	}

	/**
	 * @return Retorna o campo colecaoGuiasPagamento.
	 */
	public Collection getColecaoGuiasPagamento() {
		return colecaoGuiasPagamento;
	}

	/**
	 * @param colecaoGuiasPagamento O colecaoGuiasPagamento a ser setado.
	 */
	public void setColecaoGuiasPagamento(Collection colecaoGuiasPagamento) {
		this.colecaoGuiasPagamento = colecaoGuiasPagamento;
	}

	/**
	 * @return Retorna o campo gerarRelacaoDebitosImovelHelper.
	 */
	public GerarRelacaoDebitosImovelHelper getGerarRelacaoDebitosImovelHelper() {
		return gerarRelacaoDebitosImovelHelper;
	}

	/**
	 * @param gerarRelacaoDebitosImovelHelper O gerarRelacaoDebitosImovelHelper a ser setado.
	 */
	public void setGerarRelacaoDebitosImovelHelper(
			GerarRelacaoDebitosImovelHelper gerarRelacaoDebitosImovelHelper) {
		this.gerarRelacaoDebitosImovelHelper = gerarRelacaoDebitosImovelHelper;
	}

	/**
	 * @return Retorna o campo totalAtualizacaoMonetaria.
	 */
	public BigDecimal getTotalAtualizacaoMonetaria() {
		return totalAtualizacaoMonetaria;
	}

	/**
	 * @param totalAtualizacaoMonetaria O totalAtualizacaoMonetaria a ser setado.
	 */
	public void setTotalAtualizacaoMonetaria(BigDecimal totalAtualizacaoMonetaria) {
		this.totalAtualizacaoMonetaria = totalAtualizacaoMonetaria;
	}

	/**
	 * @return Retorna o campo totalContaAtualizado.
	 */
	public BigDecimal getTotalContaAtualizado() {
		return totalContaAtualizado;
	}

	/**
	 * @param totalContaAtualizado O totalContaAtualizado a ser setado.
	 */
	public void setTotalContaAtualizado(BigDecimal totalContaAtualizado) {
		this.totalContaAtualizado = totalContaAtualizado;
	}

	/**
	 * @return Retorna o campo totalContas.
	 */
	public BigDecimal getTotalContas() {
		return totalContas;
	}

	/**
	 * @param totalContas O totalContas a ser setado.
	 */
	public void setTotalContas(BigDecimal totalContas) {
		this.totalContas = totalContas;
	}

	/**
	 * @return Retorna o campo totalCreditoARealizar.
	 */
	public BigDecimal getTotalCreditoARealizar() {
		return totalCreditoARealizar;
	}

	/**
	 * @param totalCreditoARealizar O totalCreditoARealizar a ser setado.
	 */
	public void setTotalCreditoARealizar(BigDecimal totalCreditoARealizar) {
		this.totalCreditoARealizar = totalCreditoARealizar;
	}

	/**
	 * @return Retorna o campo totalDebitosACobrar.
	 */
	public BigDecimal getTotalDebitosACobrar() {
		return totalDebitosACobrar;
	}

	/**
	 * @param totalDebitosACobrar O totalDebitosACobrar a ser setado.
	 */
	public void setTotalDebitosACobrar(BigDecimal totalDebitosACobrar) {
		this.totalDebitosACobrar = totalDebitosACobrar;
	}

	/**
	 * @return Retorna o campo totalGeralAtualizado.
	 */
	public BigDecimal getTotalGeralAtualizado() {
		return totalGeralAtualizado;
	}

	/**
	 * @param totalGeralAtualizado O totalGeralAtualizado a ser setado.
	 */
	public void setTotalGeralAtualizado(BigDecimal totalGeralAtualizado) {
		this.totalGeralAtualizado = totalGeralAtualizado;
	}

	/**
	 * @return Retorna o campo totalGuiasPagamento.
	 */
	public BigDecimal getTotalGuiasPagamento() {
		return totalGuiasPagamento;
	}

	/**
	 * @param totalGuiasPagamento O totalGuiasPagamento a ser setado.
	 */
	public void setTotalGuiasPagamento(BigDecimal totalGuiasPagamento) {
		this.totalGuiasPagamento = totalGuiasPagamento;
	}

	/**
	 * @return Retorna o campo totalJuros.
	 */
	public BigDecimal getTotalJuros() {
		return totalJuros;
	}

	/**
	 * @param totalJuros O totalJuros a ser setado.
	 */
	public void setTotalJuros(BigDecimal totalJuros) {
		this.totalJuros = totalJuros;
	}

	/**
	 * @return Retorna o campo totalMulta.
	 */
	public BigDecimal getTotalMulta() {
		return totalMulta;
	}

	/**
	 * @param totalMulta O totalMulta a ser setado.
	 */
	public void setTotalMulta(BigDecimal totalMulta) {
		this.totalMulta = totalMulta;
	}
	
}
