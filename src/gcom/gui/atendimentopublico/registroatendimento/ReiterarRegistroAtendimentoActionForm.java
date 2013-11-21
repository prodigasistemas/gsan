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

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Vivianne Sousa
 * @date 10/05/2011
 */
public class ReiterarRegistroAtendimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numeroRA;
	private String dataPrevista;
	private String idTipoSolicitacao;
	private String descTipoSolicitacao;
	private String idEspecificacao;
	private String descEspecificacao;
	private String idUnidadeAtual;
	private String descUnidadeAtual;
	private String nomeSolicitante;
	private String idClienteSolicitante;
	private String idUnidadeSolicitante;	
	private String pontoReferencia;
	private String observacao;
	
	private String clienteEnderecoSelected;
	private String clienteFoneSelected;
	
	public String getClienteFoneSelected() {
		return clienteFoneSelected;
	}
	public void setClienteFoneSelected(String clienteFoneSelected) {
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getClienteEnderecoSelected() {
		return clienteEnderecoSelected;
	}
	public void setClienteEnderecoSelected(String clienteEnderecoSelected) {
		this.clienteEnderecoSelected = clienteEnderecoSelected;
	}
	
	public ReiterarRegistroAtendimentoActionForm(String numeroRA, String dataPrevista, String idTipoSolicitacao, String descTipoSolicitacao, String idEspecificacao, String descEspecificacao, String idUnidadeAtual, String descUnidadeAtual, String nomeSolicitante, String idClienteSolicitante, String idUnidadeSolicitante, String pontoReferencia, String observacao, String clienteEnderecoSelected, String clienteFoneSelected) {
		super();
		// TODO Auto-generated constructor stub
		this.numeroRA = numeroRA;
		this.dataPrevista = dataPrevista;
		this.idTipoSolicitacao = idTipoSolicitacao;
		this.descTipoSolicitacao = descTipoSolicitacao;
		this.idEspecificacao = idEspecificacao;
		this.descEspecificacao = descEspecificacao;
		this.idUnidadeAtual = idUnidadeAtual;
		this.descUnidadeAtual = descUnidadeAtual;
		this.nomeSolicitante = nomeSolicitante;
		this.idClienteSolicitante = idClienteSolicitante;
		this.idUnidadeSolicitante = idUnidadeSolicitante;
		this.pontoReferencia = pontoReferencia;
		this.observacao = observacao;
		this.clienteEnderecoSelected = clienteEnderecoSelected;
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public ReiterarRegistroAtendimentoActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDescEspecificacao() {
		return descEspecificacao;
	}
	public void setDescEspecificacao(String descEspecificacao) {
		this.descEspecificacao = descEspecificacao;
	}
	public String getDescTipoSolicitacao() {
		return descTipoSolicitacao;
	}
	public void setDescTipoSolicitacao(String descTipoSolicitacao) {
		this.descTipoSolicitacao = descTipoSolicitacao;
	}
	public String getDescUnidadeAtual() {
		return descUnidadeAtual;
	}
	public void setDescUnidadeAtual(String descUnidadeAtual) {
		this.descUnidadeAtual = descUnidadeAtual;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
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
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
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
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	
	
	
	
	
}
