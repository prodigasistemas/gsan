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

import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Objeto utilizado no retorno do metódo: do [SB0004] Verificar Critério de
 * Cobrança para Imóvel do [UC 0251]
 * 
 * @author Pedro Alexandre
 * @since 09/02/2006
 */
public class VerificarCriterioCobrancaParaImovelHelper {

	/**
	 * Quantidade de itens cobrados nos documentos
	 */
	private Integer quantidadeItensEmDebito;

	/**
	 * Valor dos débitos do imóvel
	 */
	private BigDecimal valorDebitoImovel;

	/**
	 * Flag que indica se o imóvel satifaz o critério de cobrança
	 */
	private boolean flagCriterioCobrancaImovel;

	/**
	 * Coleção de valores de conta do  débito do imóvel
	 */
	private Collection<ContaValoresHelper> colecaoContasValores;
	
	/**
	 * Coleção de débito a cobrar do  débito do imóvel
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	
	/**
	 * Coleção de valores de guia de pagamento do  débito do imóvel
	 */
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	
	private int motivoRejeicao;
	
	public static int SITUACAO_LIGACAO_AGUA_INVALIDA = 1;
	
	public static int SITUACAO_LIGACAO_ESGOTO_INVALIDA = 1;
	
	public static int SITUACAO_ESPECIAL_COBRANCA = 3;
	
	public static int SITUACAO_COBRANCA = 4;
	
	public static int SITUACAO_COBRANCA_DIFERENTE_SELECIONADAS = 5;
	
	public static int PERFIL_SEM_CRITERIO = 6;
	
	public static int IMOVEL_SEM_DEBITO = 7;
	
	public static int NAO_CONSIDERAR_CONTA_MES = 8;
	
	public static int NAO_CONSIDERAR_CONTA_ANTIGA = 9;
	
	public static int APENAS_CONTA_ANTIGA_CONTA_MES = 10;
	
	public int getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(int motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public VerificarCriterioCobrancaParaImovelHelper() {}

	public boolean isFlagCriterioCobrancaImovel() {
		return flagCriterioCobrancaImovel;
	}

	public void setFlagCriterioCobrancaImovel(boolean flagCriterioCobrancaImovel) {
		this.flagCriterioCobrancaImovel = flagCriterioCobrancaImovel;
	}

	public Integer getQuantidadeItensEmDebito() {
		return quantidadeItensEmDebito;
	}

	public void setQuantidadeItensEmDebito(Integer quantidadeItensEmDebito) {
		this.quantidadeItensEmDebito = quantidadeItensEmDebito;
	}

	public BigDecimal getValorDebitoImovel() {
		return valorDebitoImovel;
	}

	public void setValorDebitoImovel(BigDecimal valorDebitoImovel) {
		this.valorDebitoImovel = valorDebitoImovel;
	}

	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

}
