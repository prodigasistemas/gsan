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
 * @author Vivianne Sousa
 * @created 07/10/2006
 */

public class FiltroDevolucaoHistorico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroDevolucaoHistorico
	 */
	public FiltroDevolucaoHistorico() {
	}

	/**
	 * Constructor for the FiltroDevolucaoHistorico object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDevolucaoHistorico(String campoOrderBy) {
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
	public final static String AVISO_BANCARIO_ID = "avisoBancario.id";

	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaArrecadacao";
		
	/**
	 * Description of the Field
	 */
	public final static String DATA_DEVOLUCAO = "dataDevolucao";

	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_CREDITO_TIPO_ID = "guiaDevolucao.creditoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID = "guiaDevolucao.documentoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEVOLUCAO_SITUACAO_ATUAL_ID = "devolucaoSituacaoAtual.id";

	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID = "guiaDevolucao.conta.clienteContas.clienteRelacaoTipo.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID = "guiaDevolucao.conta.clienteContas.cliente.id";

	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "imovel.clienteImoveis.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID = "imovel.clienteImoveis.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID = "guiaDevolucao.guiaPagamento.clientesGuiaPagamento.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID = "guiaDevolucao.guiaPagamento.clientesGuiaPagamento.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_GUIA_PAGAMENTO_ID = "guiaDevolucao.guiaPagamento.clientesGuiaPagamento.guiaPagamento.id";

	/**
	 * Description of the Field
	 */
	public final static String AVISO_BANCARIO_ARRECADADOR = "avisoBancario.arrecadador";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO = "debitoTipo.id";

	/**
	 * Description of the Field
	 */
	public final static String DEVOLUCAO_SITUACAO_ATUAL_DESCRICAO = "devolucaoSituacaoAtual.descricaoDevolucaoSituacao";
	
	/**
	 * Description of the Field
	 */
	public final static String DEVOLUCAO_SITUACAO_ANTERIOR_DESCRICAO = "devolucaoSituacaoAnterior.descricaoDevolucaoSituacao";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_ID = "guiaDevolucao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_CONTA_ID = "guiaDevolucao.conta.id";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_ID = "guiaDevolucao.guiaPagamento.id";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_ID = "guiaDevolucao.debitoACobrarGeral.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "localidade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEVOLUCAO_SITUACAO_ATUAL = "devolucaoSituacaoAtual";
	
	/**
	 * Description of the Field
	 */
	public final static String DEVOLUCAO_SITUACAO_ANTERIOR = "devolucaoSituacaoAnterior";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_DEVOLUCAO = "guiaDevolucao";
	
	/**
	 * Description of the Field
	 */
	public final static String AVISO_BANCARIO = "avisoBancario";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE = "cliente";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL = "imovel";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO_ = "debitoTipo";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE = "localidade";
	
}
