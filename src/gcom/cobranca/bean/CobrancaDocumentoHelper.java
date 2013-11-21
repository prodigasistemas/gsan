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

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;

import java.io.Serializable;
import java.util.Collection;

public class CobrancaDocumentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private CobrancaDocumento cobrancaDocumento;
	private Integer quantidadeItensCobrancaDocumento;
	
	private Integer idOrdemServico;
	
	private String situacaoOrdemServico;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento;

	public CobrancaDocumentoHelper() {
		
	}
	
	public Collection getColecaoCobrancaDocumentoItem() {
		return colecaoCobrancaDocumentoItem;
	}

	public void setColecaoCobrancaDocumentoItem(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem) {
		this.colecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem;
	}
		
	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}
	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
	public Integer getQuantidadeItensCobrancaDocumento() {
		return quantidadeItensCobrancaDocumento;
	}
	public void setQuantidadeItensCobrancaDocumento(
			Integer quantidadeItensCobrancaDocumento) {
		this.quantidadeItensCobrancaDocumento = quantidadeItensCobrancaDocumento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemConta() {
		return colecaoCobrancaDocumentoItemConta;
	}

	public void setColecaoCobrancaDocumentoItemConta(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta) {
		this.colecaoCobrancaDocumentoItemConta = colecaoCobrancaDocumentoItemConta;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitoACobrar() {
		return colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitoACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar) {
		this.colecaoCobrancaDocumentoItemDebitoACobrar = colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiaPagamento() {
		return colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiaPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento) {
		this.colecaoCobrancaDocumentoItemGuiaPagamento = colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}
	
}
