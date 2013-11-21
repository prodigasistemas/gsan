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
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 * @date 08/01/2007
 */
public class FiltrarRegistroAtendimentoTramitacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroRa;
	private String indicadorTarifaSocial;
	private String[] tipoSolicitacao;
	private String[] especificacao;
	private String[] colecaoPerfilImovel;
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String municipioId;
	private String municipioDescricao;
	private String bairroId;
	private String bairroDescricao;
	private String areaBairroId;
	private String logradouroId;
	private String logradouroDescricao;
	private String unidadeAtualId;
	private String unidadeAtualDescricao;
	private String unidadeSuperiorId;
	private String unidadeSuperiorDescricao;
	//controle
	private String selectedTipoSolicitacaoSize = "0";
	private String validaUnidadeAtual = "false";
	private String validaUnidadeSuperior = "false";	
	private String validaMunicipio = "false";
	private String validaBairro = "false";
	private String validaLogradouro = "false";	
	
	private String[] idRegistrosManutencaoTramite;
	/**
	 * @return Retorna o campo validaBairro.
	 */
	public String getValidaBairro() {
		return validaBairro;
	}
	/**
	 * @param validaBairro O validaBairro a ser setado.
	 */
	public void setValidaBairro(String validaBairro) {
		this.validaBairro = validaBairro;
	}
	/**
	 * @return Retorna o campo validaLogradouro.
	 */
	public String getValidaLogradouro() {
		return validaLogradouro;
	}
	/**
	 * @param validaLogradouro O validaLogradouro a ser setado.
	 */
	public void setValidaLogradouro(String validaLogradouro) {
		this.validaLogradouro = validaLogradouro;
	}
	/**
	 * @return Retorna o campo validaMunicipio.
	 */
	public String getValidaMunicipio() {
		return validaMunicipio;
	}
	/**
	 * @param validaMunicipio O validaMunicipio a ser setado.
	 */
	public void setValidaMunicipio(String validaMunicipio) {
		this.validaMunicipio = validaMunicipio;
	}
	/**
	 * @return Retorna o campo validaUnidadeAtual.
	 */
	public String getValidaUnidadeAtual() {
		return validaUnidadeAtual;
	}
	/**
	 * @param validaUnidadeAtual O validaUnidadeAtual a ser setado.
	 */
	public void setValidaUnidadeAtual(String validaUnidadeAtual) {
		this.validaUnidadeAtual = validaUnidadeAtual;
	}
	/**
	 * @return Retorna o campo validaUnidadeSuperior.
	 */
	public String getValidaUnidadeSuperior() {
		return validaUnidadeSuperior;
	}
	/**
	 * @param validaUnidadeSuperior O validaUnidadeSuperior a ser setado.
	 */
	public void setValidaUnidadeSuperior(String validaUnidadeSuperior) {
		this.validaUnidadeSuperior = validaUnidadeSuperior;
	}
	/**
	 * @return Retorna o campo selectedTipoSolicitacaoSize.
	 */
	public String getSelectedTipoSolicitacaoSize() {
		return selectedTipoSolicitacaoSize;
	}
	/**
	 * @param selectedTipoSolicitacaoSize O selectedTipoSolicitacaoSize a ser setado.
	 */
	public void setSelectedTipoSolicitacaoSize(String selectedTipoSolicitacaoSize) {
		this.selectedTipoSolicitacaoSize = selectedTipoSolicitacaoSize;
	}
	/**
	 * @return Retorna o campo areaBairroId.
	 */
	public String getAreaBairroId() {
		return areaBairroId;
	}
	/**
	 * @param areaBairroId O areaBairroId a ser setado.
	 */
	public void setAreaBairroId(String areaBairroId) {
		this.areaBairroId = areaBairroId;
	}
	/**
	 * @return Retorna o campo bairroDescricao.
	 */
	public String getBairroDescricao() {
		return bairroDescricao;
	}
	/**
	 * @param bairroDescricao O bairroDescricao a ser setado.
	 */
	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}
	/**
	 * @return Retorna o campo bairroId.
	 */
	public String getBairroId() {
		return bairroId;
	}
	/**
	 * @param bairroId O bairroId a ser setado.
	 */
	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}

	/**
	 * @return Retorna o campo indicadorTarifaSocial.
	 */
	public String getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}
	/**
	 * @param indicadorTarifaSocial O indicadorTarifaSocial a ser setado.
	 */
	public void setIndicadorTarifaSocial(String indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}
	/**
	 * @return Retorna o campo logradouroDescricao.
	 */
	public String getLogradouroDescricao() {
		return logradouroDescricao;
	}
	/**
	 * @param logradouroDescricao O logradouroDescricao a ser setado.
	 */
	public void setLogradouroDescricao(String logradouroDescricao) {
		this.logradouroDescricao = logradouroDescricao;
	}
	/**
	 * @return Retorna o campo logradouroId.
	 */
	public String getLogradouroId() {
		return logradouroId;
	}
	/**
	 * @param logradouroId O logradouroId a ser setado.
	 */
	public void setLogradouroId(String logradouroId) {
		this.logradouroId = logradouroId;
	}
	/**
	 * @return Retorna o campo municipioDescricao.
	 */
	public String getMunicipioDescricao() {
		return municipioDescricao;
	}
	/**
	 * @param municipioDescricao O municipioDescricao a ser setado.
	 */
	public void setMunicipioDescricao(String municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
	}
	/**
	 * @return Retorna o campo municipioId.
	 */
	public String getMunicipioId() {
		return municipioId;
	}
	/**
	 * @param municipioId O municipioId a ser setado.
	 */
	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}
	/**
	 * @return Retorna o campo numeroRa.
	 */
	public String getNumeroRa() {
		return numeroRa;
	}
	/**
	 * @param numeroRa O numeroRa a ser setado.
	 */
	public void setNumeroRa(String numeroRa) {
		this.numeroRa = numeroRa;
	}
	/**
	 * @return Retorna o campo periodoAtendimentoFinal.
	 */
	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}
	/**
	 * @param periodoAtendimentoFinal O periodoAtendimentoFinal a ser setado.
	 */
	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}
	/**
	 * @return Retorna o campo periodoAtendimentoInicial.
	 */
	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}
	/**
	 * @param periodoAtendimentoInicial O periodoAtendimentoInicial a ser setado.
	 */
	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}
	/**
	 * @return Retorna o campo especificacao.
	 */
	public String[] getEspecificacao() {
		return especificacao;
	}
	/**
	 * @param especificacao O especificacao a ser setado.
	 */
	public void setEspecificacao(String[] especificacao) {
		this.especificacao = especificacao;
	}
	/**
	 * @return Retorna o campo tipoSolicitacao.
	 */
	public String[] getTipoSolicitacao() {
		return tipoSolicitacao;
	}
	/**
	 * @param tipoSolicitacao O tipoSolicitacao a ser setado.
	 */
	public void setTipoSolicitacao(String[] tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
	/**
	 * @return Retorna o campo unidadeAtualDescricao.
	 */
	public String getUnidadeAtualDescricao() {
		return unidadeAtualDescricao;
	}
	/**
	 * @param unidadeAtualDescricao O unidadeAtualDescricao a ser setado.
	 */
	public void setUnidadeAtualDescricao(String unidadeAtualDescricao) {
		this.unidadeAtualDescricao = unidadeAtualDescricao;
	}
	/**
	 * @return Retorna o campo unidadeAtualId.
	 */
	public String getUnidadeAtualId() {
		return unidadeAtualId;
	}
	/**
	 * @param unidadeAtualId O unidadeAtualId a ser setado.
	 */
	public void setUnidadeAtualId(String unidadeAtualId) {
		this.unidadeAtualId = unidadeAtualId;
	}
	/**
	 * @return Retorna o campo unidadeSuperiorDescricao.
	 */
	public String getUnidadeSuperiorDescricao() {
		return unidadeSuperiorDescricao;
	}
	/**
	 * @param unidadeSuperiorDescricao O unidadeSuperiorDescricao a ser setado.
	 */
	public void setUnidadeSuperiorDescricao(String unidadeSuperiorDescricao) {
		this.unidadeSuperiorDescricao = unidadeSuperiorDescricao;
	}
	/**
	 * @return Retorna o campo unidadeSuperiorId.
	 */
	public String getUnidadeSuperiorId() {
		return unidadeSuperiorId;
	}
	/**
	 * @param unidadeSuperiorId O unidadeSuperiorId a ser setado.
	 */
	public void setUnidadeSuperiorId(String unidadeSuperiorId) {
		this.unidadeSuperiorId = unidadeSuperiorId;
	}
	/**
	 * @return Retorna o campo idRegistrosManutencaoTramite.
	 */
	public String[] getIdRegistrosManutencaoTramite() {
		return idRegistrosManutencaoTramite;
	}
	/**
	 * @param idRegistrosManutencaoTramite O idRegistrosManutencaoTramite a ser setado.
	 */
	public void setIdRegistrosManutencaoTramite(
			String[] idRegistrosManutencaoTramite) {
		this.idRegistrosManutencaoTramite = idRegistrosManutencaoTramite;
	}
	public String[] getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}
	public void setColecaoPerfilImovel(String[] colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}
	
	

}
