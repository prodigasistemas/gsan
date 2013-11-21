/**
 * 
 */
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

import gcom.cobranca.parcelamento.Parcelamento;

import java.math.BigDecimal;

/**
 * Objeto do caso de uso [UC0216] Calcular Acrescimo por Impontualidade
 *  Valor Multa
 *  Valor Juros de Mora
 *  Valor Atualizacao Monetaria
 * @author Rafael Santos
 * @since 05/01/2006
 *
 */
public class CalcularAcrescimoPorImpontualidadeHelper {
	
	/**
	 * Valor de Multa
	 */
	private BigDecimal valorMulta;
	
	/**
	 * Valor de Juros de Mora
	 */
	private BigDecimal valorJurosMora;
	
	/**
	 * Valor de Atualizacao Monetaria
	 */
	private BigDecimal valorAtualizacaoMonetaria;

	/**
	 * @return Returns the valorAtualizacaoMonetaria.
	 */
	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	/**
	 * 
	 */
	public CalcularAcrescimoPorImpontualidadeHelper() {
		
	}
	
	/**
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valorAtualizacaoMonetaria
	 */
	public CalcularAcrescimoPorImpontualidadeHelper(BigDecimal valorMulta, BigDecimal valorJurosMora, BigDecimal valorAtualizacaoMonetaria) {
		super();
		// TODO Auto-generated constructor stub
		this.valorMulta = valorMulta;
		this.valorJurosMora = valorJurosMora;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	/**
	 * @param valorAtualizacaoMonetaria The valorAtualizacaoMonetaria to set.
	 */
	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	/**
	 * @return Returns the valorJurosMora.
	 */
	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	/**
	 * @param valorJurosMora The valorJurosMora to set.
	 */
	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	/**
	 * @return Returns the valorMulta.
	 */
	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	/**
	 * @param valorMulta The valorMulta to set.
	 */
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	
	/*
     * [UC0214] - Efetuar Parcelamento de Débitos
     * (multa + juros de mora + atualização monetária ) com o arredondamento de Parcelamento
     * @author Vivianne Sousa
     * @created 31/01/2007
     */
	public BigDecimal getValorTotalAcrescimosImpontualidade() {

		BigDecimal retorno = new BigDecimal("0.00");

		// Valor de Multa
		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}

		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);

		return retorno;
	}

}
