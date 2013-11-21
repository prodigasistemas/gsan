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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ParcelamentoDebitoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String nomeCliente;
	
	private String inscricao;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String parcelamento;
	
	private String reparcelamento;
	
	private String reparcelamentoConsecutivo;
	
	private String cpf;
	
	private String cnpj;
	
	private String cpfCnpj;
	
	private String parcelamentoMotivoDesfazer;
	
	private String imovelPerfil;
	
	private String nomeClienteResponsavel;
	
	private String dataParcelamentoDesfeito;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(String parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String getReparcelamento() {
		return reparcelamento;
	}

	public void setReparcelamento(String reparcelamento) {
		this.reparcelamento = reparcelamento;
	}

	public String getReparcelamentoConsecutivo() {
		return reparcelamentoConsecutivo;
	}

	public void setReparcelamentoConsecutivo(String reparcelamentoConsecutivo) {
		this.reparcelamentoConsecutivo = reparcelamentoConsecutivo;
	}

	public String getParcelamentoMotivoDesfazer() {
		return parcelamentoMotivoDesfazer;
	}

	public void setParcelamentoMotivoDesfazer(String parcelamentoMotivoDesfazer) {
		this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public String getDataParcelamentoDesfeito() {
		return dataParcelamentoDesfeito;
	}

	public void setDataParcelamentoDesfeito(String dataParcelamentoDesfeito) {
		this.dataParcelamentoDesfeito = dataParcelamentoDesfeito;
	}
	
	
}
