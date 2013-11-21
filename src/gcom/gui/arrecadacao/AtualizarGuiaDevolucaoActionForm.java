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
package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarGuiaDevolucaoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idGuiaDevolucao;
	private String idRegistroAtendimento;
	private String nomeRegistroAtendimento;
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String documentoTipo;
	private String documentoTipoHidden;
	private String idLocalidade;
	private String idLocalidadeHidden;
	private String descricaoLocalidade;
	private String idImovel;
	private String descricaoImovel;
	private String codigoCliente;
	private String nomeCliente;
	private String referenciaConta;
	private String descricaoConta;
	private String idGuiaPagamento;
	private String descricaoGuiaPagamento;
	private String valorGuiaPagamento;
	private String idDebitoACobrar;
	private String descricaoDebitoACobrar;
	private String valorDebitoACobrar;
	private String idTipoDebito;
	private String idTipoDebitoHidden;
	private String descricaoTipoDebito;
	private String dataValidade;
	private String valorDevolucao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getDescricaoConta() {
		return descricaoConta;
	}
	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}
	public String getDescricaoDebitoACobrar() {
		return descricaoDebitoACobrar;
	}
	public void setDescricaoDebitoACobrar(String descricaoDebitoACobrar) {
		this.descricaoDebitoACobrar = descricaoDebitoACobrar;
	}
	public String getDescricaoGuiaPagamento() {
		return descricaoGuiaPagamento;
	}
	public void setDescricaoGuiaPagamento(String descricaoGuiaPagamento) {
		this.descricaoGuiaPagamento = descricaoGuiaPagamento;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getDocumentoTipo() {
		return documentoTipo;
	}
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}
	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}
	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}
	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}
	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}
	public String getReferenciaConta() {
		return referenciaConta;
	}
	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}
	public String getValorDebitoACobrar() {
		return valorDebitoACobrar;
	}
	public void setValorDebitoACobrar(String valorDebitoACobrar) {
		this.valorDebitoACobrar = valorDebitoACobrar;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	public String getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}
	public void setValorGuiaPagamento(String valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getIdGuiaDevolucao() {
		return idGuiaDevolucao;
	}

	public void setIdGuiaDevolucao(String idGuiaDevolucao) {
		this.idGuiaDevolucao = idGuiaDevolucao;
	}

	public String getDocumentoTipoHidden() {
		return documentoTipoHidden;
	}

	public void setDocumentoTipoHidden(String documentoTipoHidden) {
		this.documentoTipoHidden = documentoTipoHidden;
	}

	public String getIdLocalidadeHidden() {
		return idLocalidadeHidden;
	}

	public void setIdLocalidadeHidden(String idLocalidadeHidden) {
		this.idLocalidadeHidden = idLocalidadeHidden;
	}

	public String getIdTipoDebitoHidden() {
		return idTipoDebitoHidden;
	}

	public void setIdTipoDebitoHidden(String idTipoDebitoHidden) {
		this.idTipoDebitoHidden = idTipoDebitoHidden;
	}
	
	
}
