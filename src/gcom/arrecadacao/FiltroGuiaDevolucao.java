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
package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente endereco
 * 
 * @author Rafael Corrêa
 * @created 22 de Abril de 2005
 */

public class FiltroGuiaDevolucao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroGuiaDevolucao() {
	}

	/**
	 * Constructor for the FiltroDevolucao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroGuiaDevolucao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "imovel.localidade";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ID = "imovel.quadra.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOTE = "imovel.lote";

	/**
	 * Description of the Field
	 */
	public final static String SUBLOTE = "imovel.subLote";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO = "dataEmissao";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_VALIDADE = "dataValidade";
	
	/**
	 * Description of the Field
	 */
	public final static String CREDITO_TIPO_ID = "creditoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ANTERIOR_ID = "debitoCreditoSituacaoAnterior.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaContabil";
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_GUIA_DEVOLUCAO = "anoMesReferenciaGuiaDevolucao";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "conta.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_ID = "guiaPagamento.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "guiaPagamento.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID = "clienteContas.clienteRelacaoTipo.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID = "clienteContas.cliente.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "clienteImoveis.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID = "clienteImoveis.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID = "clientesGuiaPagamento.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID = "clientesGuiaPagamento.cliente.id";
	
	public final static String CONTA_ID = "conta.id";
	
	public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";
	
	public final static String DEBITO_A_COBRAR_ID = "debitoACobrarGeral.debitoACobrar.id";
	
	
}
