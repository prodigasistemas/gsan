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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class ConsultarRegistroAtendimentoSolicitanteActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
    private String numeroRA;
    private String situacaoRA;
    private String idTipoSolicitacao;
    private String tipoSolicitacao;
    
    private String idEspecificacao;
    private String especificacao;
    
    private String idUnidadeAtual;
    private String unidadeAtual;

    //Dados Solicitante
    private String solicitanteId;
    private String indicadorPrincipal;
    private String nomeSolicitante;
    private String clienteSolicitante;
    
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    
    private String idFuncionarioResponsavel;
    private String funcionarioResponsavel;
    
    private String enderecoSolicitante;
    private String pontoReferencia;
    
    private String protocoloAtendimento;
    
    private Collection<SolicitanteFone> colecaoSolicitanteFone = new ArrayList();
    
    private Collection<RegistroAtendimentoSolicitante> colecaoRegistroAtendimentoSolicitante = new ArrayList();

	public Collection<RegistroAtendimentoSolicitante> getColecaoRegistroAtendimentoSolicitante() {
		return colecaoRegistroAtendimentoSolicitante;
	}

	public void setColecaoRegistroAtendimentoSolicitante(Collection<RegistroAtendimentoSolicitante> colecaoRegistroAtendimentoSolicitante) {
		this.colecaoRegistroAtendimentoSolicitante = colecaoRegistroAtendimentoSolicitante;
	}

	public Collection<SolicitanteFone> getColecaoSolicitanteFone() {
		return colecaoSolicitanteFone;
	}
	public void setColecaoSolicitanteFone(Collection<SolicitanteFone> colecaoSolicitanteFone) {
		this.colecaoSolicitanteFone = colecaoSolicitanteFone;
	}


	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getClienteSolicitante() {
		return clienteSolicitante;
	}

	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}

	public String getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(String enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public String getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(String funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public String getIdFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	public void setIdFuncionarioResponsavel(String idFuncionarioResponsavel) {
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}

	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(String solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	public String getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(String indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}
}