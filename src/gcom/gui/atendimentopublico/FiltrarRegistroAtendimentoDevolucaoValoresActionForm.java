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
package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarRegistroAtendimentoDevolucaoValoresActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String idImovel;
	
	private String idImovelHidden;

	private String descricaoImovel;

	private String dataAtendimentoInicio;

	private String dataAtendimentoFim;
	
	private String numeroRA;
	
	private String[] perfilImovel;
	
	private String idRAConsulta;
	private String idImovelSelecionado;
	private String nomeClienteUsuarioImovelSelecionado;
	private String idsConta;
	private String totalPagamentoSelecionado = "0,00";
	private String totalCreditoAbatido = "0,00";
		
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	public String getDataAtendimentoFim() {
		return dataAtendimentoFim;
	}
	public void setDataAtendimentoFim(String dataAtendimentoFim) {
		this.dataAtendimentoFim = dataAtendimentoFim;
	}
	public String getDataAtendimentoInicio() {
		return dataAtendimentoInicio;
	}
	public void setDataAtendimentoInicio(String dataAtendimentoInicio) {
		this.dataAtendimentoInicio = dataAtendimentoInicio;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdImovelHidden() {
		return idImovelHidden;
	}
	public void setIdImovelHidden(String idImovelHidden) {
		this.idImovelHidden = idImovelHidden;
	}
	public String getIdImovelSelecionado() {
		return idImovelSelecionado;
	}
	public void setIdImovelSelecionado(String idImovelSelecionado) {
		this.idImovelSelecionado = idImovelSelecionado;
	}
	public String getIdRAConsulta() {
		return idRAConsulta;
	}
	public void setIdRAConsulta(String idRAConsulta) {
		this.idRAConsulta = idRAConsulta;
	}
	public String getIdsConta() {
		return idsConta;
	}
	public void setIdsConta(String idsConta) {
		this.idsConta = idsConta;
	}
	public String getNomeClienteUsuarioImovelSelecionado() {
		return nomeClienteUsuarioImovelSelecionado;
	}
	public void setNomeClienteUsuarioImovelSelecionado(
			String nomeClienteUsuarioImovelSelecionado) {
		this.nomeClienteUsuarioImovelSelecionado = nomeClienteUsuarioImovelSelecionado;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getTotalPagamentoSelecionado() {
		return totalPagamentoSelecionado;
	}
	public void setTotalPagamentoSelecionado(String totalPagamentoSelecionado) {
		this.totalPagamentoSelecionado = totalPagamentoSelecionado;
	}
	public String getTotalCreditoAbatido() {
		return totalCreditoAbatido;
	}
	public void setTotalCreditoAbatido(String totalCreditoAbatido) {
		this.totalCreditoAbatido = totalCreditoAbatido;
	}
	
	
}
