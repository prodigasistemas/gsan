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
package gcom.arrecadacao.pagamento.bean;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.pagamento.Pagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Objeto helper utilizado para armazenar os dados necessário para inserir 
 * um pagamento por leitura optica de código de barras
 *
 * @author Pedro Alexandre
 * @date 16/02/2006
 */
public class InserirPagamentoViaCanetaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public InserirPagamentoViaCanetaHelper() {
	}

	private String codigoBarra;

	private BigDecimal valorPagamento;
	
	private Collection<Pagamento> colecaoPagamento;
	
	private Collection<Devolucao> colecaoDevolucao;
	
	//adicionado por Vivianne Sousa - 22/12/2009
	//[UC0971] Inserir Pagamentos para Faturas Especiais
	private RegistroHelperCodigoBarras registroHelperCodigoBarras;
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String cobigoBarra) {
		this.codigoBarra = cobigoBarra;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getCodigoBarraFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 44){
		  return  codigoBarra.substring(0,11) + " " + codigoBarra.substring(11,22) + " " + codigoBarra.substring(22,33) + " " + codigoBarra.substring(33,44); 
		}else{
			return "";
		}
	}
	
	public String getCodigoBarraFichaCompensacaoFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 47){
		  return  codigoBarra.substring(0,5) + "." + codigoBarra.substring(5,10) + " " 
		  + codigoBarra.substring(10,15) + "." + codigoBarra.substring(15,21) + " "
		  + codigoBarra.substring(21,26) + "." + codigoBarra.substring(26,32) + " "
		  + codigoBarra.substring(32,33) + " " + codigoBarra.substring(33,47);
		}
		else{
			return "";
		}
	}

	public Collection<Pagamento> getColecaoPagamento() {
		return colecaoPagamento;
	}

	public void setColecaoPagamento(Collection<Pagamento> colecaoPagamento) {
		this.colecaoPagamento = colecaoPagamento;
	}

	public Collection<Devolucao> getColecaoDevolucao() {
		return colecaoDevolucao;
	}

	public void setColecaoDevolucao(Collection<Devolucao> colecaoDevolucao) {
		this.colecaoDevolucao = colecaoDevolucao;
	}

	public RegistroHelperCodigoBarras getRegistroHelperCodigoBarras() {
		return registroHelperCodigoBarras;
	}

	public void setRegistroHelperCodigoBarras(
			RegistroHelperCodigoBarras registroHelperCodigoBarras) {
		this.registroHelperCodigoBarras = registroHelperCodigoBarras;
	}

		
}
