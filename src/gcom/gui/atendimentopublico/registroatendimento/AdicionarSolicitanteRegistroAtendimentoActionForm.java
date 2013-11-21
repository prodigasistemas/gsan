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
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da inserção de um solicitante em uma R.A já existente
 * 
 * @author Raphael Rossiter
 * @date 18/08/2006
 */
public class AdicionarSolicitanteRegistroAtendimentoActionForm extends
		ValidatorActionForm {

	
	private static final long serialVersionUID = 1L;

	// Dados da RA

	private String idRA;

	private String idSolicitacaoTipo;

	private String descricaoSolicitacaoTipo;

	private String idEspecificacao;

	private String descricaoEspecificacao;

	private String dataAtendimento;

	private String horaAtendimento;

	private String idMeioSolicitacao;

	private String descricaoMeioSolicitacao;

	private String idUnidadeAtendimento;

	private String descricaoUnidadeAtendimento;

	private String dataPrevista;

	private String idClienteSolcitante;

	private String nomeClienteSolicitante;

	private String idUnidadeSolicitante;

	private String descricaoUnidadeSolicitante;

	private String nomeSolicitante;

	private String enderecoOcorrencia;

	private String pontoReferencia;

	private String idBairro;

	private String descricaoBairro;

	private String idBairroArea;

	private String descricaoBairroArea;

	private String idLocalidade;

	private String codigoSetorComercial;

	private String numeroQuadra;

	private String idUnidadeAtual;

	private String descricaoUnidadeAtual;

	// Dados do Solicitante

	private String idCliente;

	private String nomeCliente;

	private String idUnidadeSolicitanteInformar;

	private String descricaoUnidadeSolicitanteInformar;

	private String idFuncionarioResponsavel;

	private String descricaoFuncionarioResponsavel;

	private String nomeSolicitanteInformar;

	private String clienteEnderecoSelected;

	private String pontoReferenciaSolicitante;

	private String clienteFoneSelected;

	private String indicadorClienteEspecificacao;
	
	private String protocoloAtendimento;
	
	private String enviarEmailSatisfacao;
	
	private String enderecoEmail;

	//dados do telefone
	
	private String idTipoTelefone;

	private String dddTelefone;

	private String numeroTelefone;

	private String ramal;

	public String getIndicadorClienteEspecificacao() {
		return indicadorClienteEspecificacao;
	}

	public void setIndicadorClienteEspecificacao(
			String indicadorClienteEspecificacao) {
		this.indicadorClienteEspecificacao = indicadorClienteEspecificacao;
	}

	public String getClienteFoneSelected() {
		return clienteFoneSelected;
	}

	public void setClienteFoneSelected(String clienteFoneSelected) {
		this.clienteFoneSelected = clienteFoneSelected;
	}

	public String getPontoReferenciaSolicitante() {
		return pontoReferenciaSolicitante;
	}

	public void setPontoReferenciaSolicitante(String pontoReferenciaSolicitante) {
		this.pontoReferenciaSolicitante = pontoReferenciaSolicitante;
	}

	public String getClienteEnderecoSelected() {
		return clienteEnderecoSelected;
	}

	public void setClienteEnderecoSelected(String clienteEnderecoSelected) {
		this.clienteEnderecoSelected = clienteEnderecoSelected;
	}

	public String getDescricaoFuncionarioResponsavel() {
		return descricaoFuncionarioResponsavel;
	}

	public void setDescricaoFuncionarioResponsavel(
			String descricaoFuncionarioResponsavel) {
		this.descricaoFuncionarioResponsavel = descricaoFuncionarioResponsavel;
	}

	public String getDescricaoUnidadeSolicitanteInformar() {
		return descricaoUnidadeSolicitanteInformar;
	}

	public void setDescricaoUnidadeSolicitanteInformar(
			String descricaoUnidadeSolicitanteInformar) {
		this.descricaoUnidadeSolicitanteInformar = descricaoUnidadeSolicitanteInformar;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	public void setIdFuncionarioResponsavel(String idFuncionarioResponsavel) {
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	public String getIdUnidadeSolicitanteInformar() {
		return idUnidadeSolicitanteInformar;
	}

	public void setIdUnidadeSolicitanteInformar(
			String idUnidadeSolicitanteInformar) {
		this.idUnidadeSolicitanteInformar = idUnidadeSolicitanteInformar;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeSolicitanteInformar() {
		return nomeSolicitanteInformar;
	}

	public void setNomeSolicitanteInformar(String nomeSolicitanteInformar) {
		this.nomeSolicitanteInformar = nomeSolicitanteInformar;
	}

	public String getDescricaoBairroArea() {
		return descricaoBairroArea;
	}

	public void setDescricaoBairroArea(String descricaoBairroArea) {
		this.descricaoBairroArea = descricaoBairroArea;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getDescricaoEspecificacao() {
		return descricaoEspecificacao;
	}

	public void setDescricaoEspecificacao(String descricaoEspecificacao) {
		this.descricaoEspecificacao = descricaoEspecificacao;
	}

	public String getDescricaoMeioSolicitacao() {
		return descricaoMeioSolicitacao;
	}

	public void setDescricaoMeioSolicitacao(String descricaoMeioSolicitacao) {
		this.descricaoMeioSolicitacao = descricaoMeioSolicitacao;
	}

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	public void setDescricaoUnidadeAtendimento(
			String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	public String getDescricaoUnidadeAtual() {
		return descricaoUnidadeAtual;
	}

	public void setDescricaoUnidadeAtual(String descricaoUnidadeAtual) {
		this.descricaoUnidadeAtual = descricaoUnidadeAtual;
	}

	public String getDescricaoUnidadeSolicitante() {
		return descricaoUnidadeSolicitante;
	}

	public void setDescricaoUnidadeSolicitante(
			String descricaoUnidadeSolicitante) {
		this.descricaoUnidadeSolicitante = descricaoUnidadeSolicitante;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(String idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public String getIdClienteSolcitante() {
		return idClienteSolcitante;
	}

	public void setIdClienteSolcitante(String idClienteSolcitante) {
		this.idClienteSolcitante = idClienteSolcitante;
	}

	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}

	public void setIdMeioSolicitacao(String idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}

	public String getIdRA() {
		return idRA;
	}

	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	public String getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(String idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
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

	public String getNomeClienteSolicitante() {
		return nomeClienteSolicitante;
	}

	public void setNomeClienteSolicitante(String nomeClienteSolicitante) {
		this.nomeClienteSolicitante = nomeClienteSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getIdTipoTelefone() {
		return idTipoTelefone;
	}

	public void setIdTipoTelefone(String idTipoTelefone) {
		this.idTipoTelefone = idTipoTelefone;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}

	public String getEnviarEmailSatisfacao() {
		return enviarEmailSatisfacao;
	}

	public void setEnviarEmailSatisfacao(String enviarEmailSatisfacao) {
		this.enviarEmailSatisfacao = enviarEmailSatisfacao;
	}

	public String getEnderecoEmail() {
		return enderecoEmail;
	}

	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}
}
