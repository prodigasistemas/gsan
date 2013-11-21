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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 25/04/2006
 */

public class FiltrarRelatorioGestaoRAActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String dataAtendimentoInicial;
	
	private String dataAtendimentoFinal;
	
	private String situacaoRA;

	private String[] solicitacaoTipo;
	
	private String[] especificacao;
	
	private String idUnidade;
	
	private String nomeUnidade;
	
	private String idUnidadeSuperior;
	
	private String nomeUnidadeSuperior;
	
	private String idMunicipio;
	
	private String nomeMunicipio;
	
	private String idBairro;
	
	private String nomeBairro;
	
	private String selectedSolicitacaoTipoSize = "0";

	/**
	 * @return Retorna o campo dataAtendimentoFinal.
	 */
	public String getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}

	/**
	 * @param dataAtendimentoFinal O dataAtendimentoFinal a ser setado.
	 */
	public void setDataAtendimentoFinal(String dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	/**
	 * @return Retorna o campo dataAtendimentoInicial.
	 */
	public String getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	/**
	 * @param dataAtendimentoInicial O dataAtendimentoInicial a ser setado.
	 */
	public void setDataAtendimentoInicial(String dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
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
	 * @return Retorna o campo idBairro.
	 */
	public String getIdBairro() {
		return idBairro;
	}

	/**
	 * @param idBairro O idBairro a ser setado.
	 */
	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	/**
	 * @return Retorna o campo idMunicipio.
	 */
	public String getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio O idMunicipio a ser setado.
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return Retorna o campo idUnidade.
	 */
	public String getIdUnidade() {
		return idUnidade;
	}

	/**
	 * @param idUnidade O idUnidade a ser setado.
	 */
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	/**
	 * @return Retorna o campo idUnidadeSuperior.
	 */
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	/**
	 * @param idUnidadeSuperior O idUnidadeSuperior a ser setado.
	 */
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	/**
	 * @return Retorna o campo nomeBairro.
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * @param nomeBairro O nomeBairro a ser setado.
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/**
	 * @param nomeMunicipio O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	/**
	 * @return Retorna o campo nomeUnidade.
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * @param nomeUnidade O nomeUnidade a ser setado.
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	/**
	 * @return Retorna o campo nomeUnidadeSuperior.
	 */
	public String getNomeUnidadeSuperior() {
		return nomeUnidadeSuperior;
	}

	/**
	 * @param nomeUnidadeSuperior O nomeUnidadeSuperior a ser setado.
	 */
	public void setNomeUnidadeSuperior(String nomeUnidadeSuperior) {
		this.nomeUnidadeSuperior = nomeUnidadeSuperior;
	}

	/**
	 * @return Retorna o campo situacaoRA.
	 */
	public String getSituacaoRA() {
		return situacaoRA;
	}

	/**
	 * @param situacaoRA O situacaoRA a ser setado.
	 */
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	/**
	 * @return Retorna o campo solicitacaoTipo.
	 */
	public String[] getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	/**
	 * @param solicitacaoTipo O solicitacaoTipo a ser setado.
	 */
	public void setSolicitacaoTipo(String[] solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	/**
	 * @return Retorna o campo selectedSolicitacaoTipoSize.
	 */
	public String getSelectedSolicitacaoTipoSize() {
		return selectedSolicitacaoTipoSize;
	}

	/**
	 * @param selectedSolicitacaoTipoSize O selectedSolicitacaoTipoSize a ser setado.
	 */
	public void setSelectedSolicitacaoTipoSize(String selectedSolicitacaoTipoSize) {
		this.selectedSolicitacaoTipoSize = selectedSolicitacaoTipoSize;
	}
	
}
