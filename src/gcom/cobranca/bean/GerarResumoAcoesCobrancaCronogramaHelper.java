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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Este caso de uso permite gerar o resumo das ações de cobrança com a 
 * atividade emitir já realizada e a atividade encerrar ainda não realizada 
 * e realizar a atividade encerrar das ações que estejam comandadas.
 *
 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
 *
 * [SB0004] - Atualizar Item do Documento de Cobrança
 * 
 * Acumula a quantidade e o valor do item, na situiaão de débito correspondente
 * Armazena a data da situação do débito do imte do documento de cobrança refrente a situação do débito do item do documento de cobrança
 *
 * Objeto composto de:
 * Id Situação do Débito
 * Quantidade de Ocorrencia das Situações de Débito
 * Valor do Item Cobrado
 * Data situação do Débito
 * 
 * @author Rafael Santos
 * @since 18/10/2006
 */
public class GerarResumoAcoesCobrancaCronogramaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Quantidade de Ocorrencia da Situaçãoi de Débito 
	 */
	private int quantidadeOcorrenciaSituacaoDebito;
	
	/**
	 * Valor do Item Pago
	 */
	private BigDecimal valorItemCobrado;

	/**
	 * Id da Situaçãoi do Débito
	 */
	private int idSituacaoDebito;
	
	/**
	 * Data da Situação do Débito
	 */
	private Date dataSituacaoDebito;

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	/**
	 * @return Retorna o campo idSituacaoDebito.
	 */
	public int getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	/**
	 * @param idSituacaoDebito O idSituacaoDebito a ser setado.
	 */
	public void setIdSituacaoDebito(int idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

	/**
	 * @return Retorna o campo quantidadeOcorrenciaSituacaoDebito.
	 */
	public int getQuantidadeOcorrenciaSituacaoDebito() {
		return quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @param quantidadeOcorrenciaSituacaoDebito O quantidadeOcorrenciaSituacaoDebito a ser setado.
	 */
	public void setQuantidadeOcorrenciaSituacaoDebito(
			int quantidadeOcorrenciaSituacaoDebito) {
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @return Retorna o campo valorItemCobrado.
	 */
	public BigDecimal getValorItemCobrado() {
		return valorItemCobrado;
	}

	/**
	 * @param valorItemCobrado O valorItemCobrado a ser setado.
	 */
	public void setValorItemCobrado(BigDecimal valorItemCobrado) {
		this.valorItemCobrado = valorItemCobrado;
	}

	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper(int quantidadeOcorrenciaSituacaoDebito, BigDecimal valorItemCobrado, int idSituacaoDebito, Date dataSituacaoDebito) {
		super();
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
		this.valorItemCobrado = valorItemCobrado;
		this.idSituacaoDebito = idSituacaoDebito;
		this.dataSituacaoDebito = dataSituacaoDebito;
	}
	
	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper() {
		super();
	}	
}