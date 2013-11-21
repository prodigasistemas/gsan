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
package gcom.cobranca.bean;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;


/**
 * Débitos de um imóvel ou de um cliente
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class ObterDebitoImovelOuClienteHelper {
	
	/**
	 * Contas em Débito e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValores;
	
	/**
	 * Contas em Débito e Valores Para exibição na aba de Imóvel de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValoresImovel;

	/**
	 * Coleção de Debitos a Cobrar
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	/**
	 * Coleção de Creditos a Realizar
	 */
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	/**
	 * Guias de Pagamento e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	
	
	//----------------------------------------------------------
	//[UC0630] - Solicitar Emissão do Extrato de Débitos
	//Vivianne Sousa - 21/08/2007
	/**
	 * Coleção de Debitos/Creditos do Parcelamento
	 */
	private Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper;

	//----------------------------------------------------------
	

	/*
	 * Coleção de Notas Promissorias?
	 */
	//private Collection<NotaPromissoria> colecaoNotasPromissorias;

	/**
	 * Constructor
	 */
	public ObterDebitoImovelOuClienteHelper() {
		
	}
	
	/**
	 * @param colecaoContasValores
	 * @param colecaoDebitoACobrar
	 * @param colecaoCreditoARealizar
	 * @param colecaoGuiasPagamentoValores
	 */
	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<ContaValoresHelper> colecaoContasValoresImovel,
		Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores,
			Collection<ContaValoresHelper> colecaoContasValoresImovel,
			Collection<DebitoACobrar> colecaoDebitoACobrar, 
			Collection<CreditoARealizar> colecaoCreditoARealizar, 
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores,
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	
	/**
	 * @return Returns the colecaoContasValores.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	/**
	 * @param colecaoContasValores The colecaoContasValores to set.
	 */
	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	/**
	 * @return Returns the colecaoCreditoARealizar.
	 */
	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	/**
	 * @param colecaoCreditoARealizar The colecaoCreditoARealizar to set.
	 */
	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	/**
	 * @return Returns the colecaoDebitoACobrar.
	 */
	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	/**
	 * @param colecaoDebitoACobrar The colecaoDebitoACobrar to set.
	 */
	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	/**
	 * @return Returns the colecaoGuiasPagamentoValores.
	 */
	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	/**
	 * @param colecaoGuiasPagamentoValores The colecaoGuiasPagamentoValores to set.
	 */
	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	/**
	 * @return Retorna o campo colecaoContasValoresImovel.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValoresImovel() {
		return colecaoContasValoresImovel;
	}

	/**
	 * @param colecaoContasValoresImovel O colecaoContasValoresImovel a ser setado.
	 */
	public void setColecaoContasValoresImovel(
			Collection<ContaValoresHelper> colecaoContasValoresImovel) {
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
	}

	public Collection<DebitoCreditoParcelamentoHelper> getColecaoDebitoCreditoParcelamentoHelper() {
		return colecaoDebitoCreditoParcelamentoHelper;
	}

	public void setColecaoDebitoCreditoParcelamentoHelper(
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	public BigDecimal obterValorImpostosDasContas(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
}
