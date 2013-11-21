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


import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Filtrar Registro Atendimento
 * 
 * @author Leonardo Regis
 */
public class FiltrarRegistroAtendimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroRA;
	private String numeroRAManual;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String situacao;
	private String[] tipoSolicitacao;
	private String[] especificacao;
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String periodoTramitacaoInicial;
	private String periodoTramitacaoFinal;
	private String unidadeAtendimentoId;
	private String unidadeAtendimentoDescricao;	
	private String unidadeAtualId;
	private String unidadeAtualDescricao;	
	private String unidadeSuperiorId;
	private String unidadeSuperiorDescricao;
	private String municipioId;	
	private String municipioDescricao;
	private String bairroId;	
	private String bairroCodigo;
	private String bairroDescricao;
	private String areaBairroId;	
	private String logradouroId;	
	private String logradouroDescricao;
	private String loginUsuario;
	private String nomeUsuario;
	private String idUsuario;
	private String quantidadeRAReiteradasInicial;
	private String quantidadeRAReiteradasFinal;
	private String[] colecaoAtendimentoMotivoEncerramento;
	private String indicCoordSemLogr;
	private String[] colecaoPerfilImovel;
	
	private String numeroProtocoloAtendimento;
	
	private String unidadeAnteriorId;
	private String unidadeAnteriorDescricao;
	
	// Controle
	private String validaImovel = "false";
	private String validaUnidadeAtendimento = "false";
	private String validaUnidadeAtual = "false";
	private String validaUnidadeSuperior = "false";
	private String validaMunicipio = "false";
	private String validaBairro = "false";
	private String validaLogradouro = "false";
	private String selectedTipoSolicitacaoSize = "0";
	private String situacaoId;
	private RegistroAtendimento ra = new RegistroAtendimento();
	private String validaUnidadeAnterior = "false";
	
	public String getPeriodoTramitacaoFinal() {
		return periodoTramitacaoFinal;
	}


	public void setPeriodoTramitacaoFinal(String periodoTramitacaoFinal) {
		this.periodoTramitacaoFinal = periodoTramitacaoFinal;
	}


	public String getPeriodoTramitacaoInicial() {
		return periodoTramitacaoInicial;
	}


	public void setPeriodoTramitacaoInicial(String periodoTramitacaoInicial) {
		this.periodoTramitacaoInicial = periodoTramitacaoInicial;
	}


	public String getBairroCodigo() {
		return bairroCodigo;
	}


	public void setBairroCodigo(String bairroCodigo) {
		this.bairroCodigo = bairroCodigo;
	}


	public String getNumeroRAManual() {
		return numeroRAManual;
	}


	public void setNumeroRAManual(String numeroRAManual) {
		this.numeroRAManual = numeroRAManual;
	}


	public RegistroAtendimento getRa() {
		return ra;
	}


	public void setRa(RegistroAtendimento ra) {
		this.ra = ra;
	}


	public String getSelectedTipoSolicitacaoSize() {
		return selectedTipoSolicitacaoSize;
	}


	public void setSelectedTipoSolicitacaoSize(String selectedTipoSolicitacaoSize) {
		this.selectedTipoSolicitacaoSize = selectedTipoSolicitacaoSize;
	}


	public void resetRA() {
		numeroRA = null;
		matriculaImovel = null;
		inscricaoImovel = null;
		situacao = null;
		tipoSolicitacao = null;
		especificacao = null;
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;
		periodoEncerramentoInicial = null;
		periodoEncerramentoFinal = null;
		unidadeAtendimentoId = null;
		unidadeAtendimentoDescricao = null;
		unidadeAtualId = null;
		unidadeAtualDescricao = null;
		unidadeSuperiorId = null;
		unidadeSuperiorDescricao = null;
		municipioId = null;
		municipioDescricao = null;
		bairroId = null;
		bairroDescricao = null;
		areaBairroId = null;
		logradouroId = null;
		logradouroDescricao = null;
		loginUsuario = null;
		nomeUsuario = null;
	}


	public String getAreaBairroId() {
		return areaBairroId;
	}


	public void setAreaBairroId(String areaBairroId) {
		this.areaBairroId = areaBairroId;
	}


	public String getBairroDescricao() {
		return bairroDescricao;
	}


	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}


	public String getBairroId() {
		return bairroId;
	}


	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}


	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}


	public String getLogradouroDescricao() {
		return logradouroDescricao;
	}


	public void setLogradouroDescricao(String logradouroDescricao) {
		this.logradouroDescricao = logradouroDescricao;
	}


	public String getLogradouroId() {
		return logradouroId;
	}


	public void setLogradouroId(String logradouroId) {
		this.logradouroId = logradouroId;
	}


	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}


	public String getMunicipioDescricao() {
		return municipioDescricao;
	}


	public void setMunicipioDescricao(String municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
	}


	public String getMunicipioId() {
		return municipioId;
	}


	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}


	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}


	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}


	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}


	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}


	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}


	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}


	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}


	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}


	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}


	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}


	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}


	public String getUnidadeAtualDescricao() {
		return unidadeAtualDescricao;
	}


	public void setUnidadeAtualDescricao(String unidadeAtualDescricao) {
		this.unidadeAtualDescricao = unidadeAtualDescricao;
	}


	public String getUnidadeAtualId() {
		return unidadeAtualId;
	}


	public void setUnidadeAtualId(String unidadeAtualId) {
		this.unidadeAtualId = unidadeAtualId;
	}


	public String getUnidadeSuperiorDescricao() {
		return unidadeSuperiorDescricao;
	}


	public void setUnidadeSuperiorDescricao(String unidadeSuperiorDescricao) {
		this.unidadeSuperiorDescricao = unidadeSuperiorDescricao;
	}


	public String getUnidadeSuperiorId() {
		return unidadeSuperiorId;
	}


	public void setUnidadeSuperiorId(String unidadeSuperiorId) {
		this.unidadeSuperiorId = unidadeSuperiorId;
	}


	public String getNumeroRA() {
		return numeroRA;
	}


	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}


	public String getValidaBairro() {
		return validaBairro;
	}


	public void setValidaBairro(String validaBairro) {
		this.validaBairro = validaBairro;
	}


	public String getValidaImovel() {
		return validaImovel;
	}


	public void setValidaImovel(String validaImovel) {
		this.validaImovel = validaImovel;
	}


	public String getValidaLogradouro() {
		return validaLogradouro;
	}


	public void setValidaLogradouro(String validaLogradouro) {
		this.validaLogradouro = validaLogradouro;
	}


	public String getValidaMunicipio() {
		return validaMunicipio;
	}


	public void setValidaMunicipio(String validaMunicipio) {
		this.validaMunicipio = validaMunicipio;
	}


	public String getValidaUnidadeAtendimento() {
		return validaUnidadeAtendimento;
	}


	public void setValidaUnidadeAtendimento(String validaUnidadeAtendimento) {
		this.validaUnidadeAtendimento = validaUnidadeAtendimento;
	}


	public String getValidaUnidadeAtual() {
		return validaUnidadeAtual;
	}


	public void setValidaUnidadeAtual(String validaUnidadeAtual) {
		this.validaUnidadeAtual = validaUnidadeAtual;
	}


	public String getValidaUnidadeSuperior() {
		return validaUnidadeSuperior;
	}


	public void setValidaUnidadeSuperior(String validaUnidadeSuperior) {
		this.validaUnidadeSuperior = validaUnidadeSuperior;
	}


	public String[] getEspecificacao() {
		return especificacao;
	}


	public void setEspecificacao(String[] especificacao) {
		this.especificacao = especificacao;
	}


	public String[] getTipoSolicitacao() {
		return tipoSolicitacao;
	}


	public void setTipoSolicitacao(String[] tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}


	public String getSituacaoId() {
		return situacaoId;
	}


	public void setSituacaoId(String situacaoId) {
		this.situacaoId = situacaoId;
	}


	public String getLoginUsuario() {
		return loginUsuario;
	}


	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	/**
	 * @return Retorna o campo quantidadeRAReiteradasFinal.
	 */
	public String getQuantidadeRAReiteradasFinal() {
		return quantidadeRAReiteradasFinal;
	}


	/**
	 * @param quantidadeRAReiteradasFinal O quantidadeRAReiteradasFinal a ser setado.
	 */
	public void setQuantidadeRAReiteradasFinal(String quantidadeRAReiteradasFinal) {
		this.quantidadeRAReiteradasFinal = quantidadeRAReiteradasFinal;
	}


	/**
	 * @return Retorna o campo quantidadeRAReiteradasInicial.
	 */
	public String getQuantidadeRAReiteradasInicial() {
		return quantidadeRAReiteradasInicial;
	}


	/**
	 * @param quantidadeRAReiteradasInicial O quantidadeRAReiteradasInicial a ser setado.
	 */
	public void setQuantidadeRAReiteradasInicial(String quantidadeRAReiteradasInicial) {
		this.quantidadeRAReiteradasInicial = quantidadeRAReiteradasInicial;
	}


	public String[] getColecaoAtendimentoMotivoEncerramento() {
		return colecaoAtendimentoMotivoEncerramento;
	}


	public void setColecaoAtendimentoMotivoEncerramento(
			String[] colecaoAtendimentoMotivoEncerramento) {
		this.colecaoAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerramento;
	}


	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	/**
	 * @return Retorna o campo indicCoordSemLogr.
	 */
	public String getIndicCoordSemLogr() {
		return indicCoordSemLogr;
	}


	/**
	 * @param indicCoordSemLogr O indicCoordSemLogr a ser setado.
	 */
	public void setIndicCoordSemLogr(String indicCoordSemLogr) {
		this.indicCoordSemLogr = indicCoordSemLogr;
	}


	public String getNumeroProtocoloAtendimento() {
		return numeroProtocoloAtendimento;
	}


	public void setNumeroProtocoloAtendimento(String numeroProtocoloAtendimento) {
		this.numeroProtocoloAtendimento = numeroProtocoloAtendimento;
	}


	public String[] getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}


	public void setColecaoPerfilImovel(String[] colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}


	public String getUnidadeAnteriorDescricao() {
		return unidadeAnteriorDescricao;
	}


	public void setUnidadeAnteriorDescricao(String unidadeAnteriorDescricao) {
		this.unidadeAnteriorDescricao = unidadeAnteriorDescricao;
	}


	public String getUnidadeAnteriorId() {
		return unidadeAnteriorId;
	}


	public void setUnidadeAnteriorId(String unidadeAnteriorId) {
		this.unidadeAnteriorId = unidadeAnteriorId;
	}


	public String getValidaUnidadeAnterior() {
		return validaUnidadeAnterior;
	}


	public void setValidaUnidadeAnterior(String validaUnidadeAnterior) {
		this.validaUnidadeAnterior = validaUnidadeAnterior;
	}
		
}