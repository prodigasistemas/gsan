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
package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * Objeto que pode ser um dos 4 SF desses dependendo do tipo de pagamento
 * 
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0264] - Distribuir Dados do Código de Barras. 
 * [SF0001] - Distribuir Pagamento de Conta 
 * [SF0002] - Distribuir Pagamento de Guia de Pagamento 
 * [SF0003] - Distribuir Pagamento de Documento de Cobramça 
 * [SF0004] - Distribuir Pagamento de Fatura do Cliente Responsável
 */
public class RegistroHelperCodigoBarrasTipoPagamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoBarrasTipoPagamento() {
	}

	// campos do idPagamento que pode ser de um dos 4 SF,como podem ser campos
	// diferentes
	// dependendo do SF então tem esses números
	private String idPagamento1;

	private String idPagamento2;

	private String idPagamento3;

	private String idPagamento4;

	private String idPagamento5;

	private String idPagamento6;
	
	/*---------------------------------------------------------------------------------------------------------------
	 * [UC0264] - Distribuir Dados do Código de Barras
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Conta(valor = 3).
	 *   .idPagamento1 = Código da Localidade.
	 *   .idPagamento2 = Mátricula do Imóvel.
	 *   .idPagamento3 = Não utilizado.
	 *   .idPagamento4 = Mês e Ano de Referência da Conta (MMAAAA).
	 *   .idPagamento5 = Digito Verificador da Conta no modulo 10.
	 *   .idPagamento6 = Não Utilizado.
	 *   
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Guia Pagamento(valor = 4)
	 *   .idPagamento1 = Código da Localidade.
	 *   .idPagamento2 = Mátricula do Imóvel.
	 *   .idPagamento3 = Não utilizado.
	 *   .idPagamento4 = Código do tipo do débito(DBTP_ID).
	 *   .idPagamento5 = Ano da Emissão da Guia de Pagamento(AAAA).
	 *   .idPagamento6 = Não Utilizado.
	 * 
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Documento de Cobrança(valor = 5)
	 *   .idPagamento1 = Código da Localidade.
	 *   .idPagamento2 = Mátricula do Imóvel.
	 *   .idPagamento3 = Sequencia do Documento de Cobrança.
	 *   .idPagamento4 = Código do tipo de Documento(DOTP_ID).
	 *   .idPagamento5 = Não Utilizado.
	 * 
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Fatura do Cliente Responsável (valor = 7)
	 *   .idPagamento1 = Não Utilizado.
	 *   .idPagamento2 = Código do Cliente Responsável(CLIE_ID).
	 *   .idPagamento3 = Não Utilizado.
	 *   .idPagamento4 = Mês e Ano de Referência da Conta (MMAAAA).
	 *   .idPagamento5 = Digito Verificador da Conta no modulo 10.
	 *   .idPagamento6 = Sequencial da Fatura do Cliente Responsável.
	 *
	 * - Identificação da Empresa for CAER (campo G05.6 = 0004)
	 * 
	 * 		.idPagamento1 = Tipo do Documento (G05.7.1).
	 * 		.idPagamento2 = Identificação.
	 *
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Conta e Segunda Via (valor = 1)
	 *   		.idPagamento3 = Matricula do Imovel (IMOV_ID).
	 *   		.idPagamento4 = Ano e Mês de Referência da Conta (AAAAMM).
	 *   		.idPagamento5 = Codigo Origem da Conta.
	 *   		.idPagamento6 = Numero do Documento + campo G05.8.  
	 * 		
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Fatura (valor = 2)
	 *   		.idPagamento3 = Qualificação.
	 *   		.idPagamento4 = Ano e Mês de Referência da Conta (AAAAMM).
	 *   		.idPagamento5 = Numero do Documento + campo G05.8.   
	 * 		
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Reaviso de Debito (valor = 3)
	 *   		.idPagamento3 = Matricula do Imovel (IMOV_ID).
	 *   		.idPagamento4 = Numero do Documento + campo G05.8. 
	 *   		    
	 *   
	 -----------------------------------------------------------------------------------------------------------------*/

	public String getIdPagamento1() {
		return idPagamento1;
	}

	public void setIdPagamento1(String idPagamento1) {
		this.idPagamento1 = idPagamento1;
	}

	public String getIdPagamento2() {
		return idPagamento2;
	}

	public void setIdPagamento2(String idPagamento2) {
		this.idPagamento2 = idPagamento2;
	}

	public String getIdPagamento3() {
		return idPagamento3;
	}

	public void setIdPagamento3(String idPagamento3) {
		this.idPagamento3 = idPagamento3;
	}

	public String getIdPagamento4() {
		return idPagamento4;
	}

	public void setIdPagamento4(String idPagamento4) {
		this.idPagamento4 = idPagamento4;
	}

	public String getIdPagamento5() {
		return idPagamento5;
	}

	public void setIdPagamento5(String idPagamento5) {
		this.idPagamento5 = idPagamento5;
	}

	public String getIdPagamento6() {
		return idPagamento6;
	}

	public void setIdPagamento6(String idPagamento6) {
		this.idPagamento6 = idPagamento6;
	}

}
