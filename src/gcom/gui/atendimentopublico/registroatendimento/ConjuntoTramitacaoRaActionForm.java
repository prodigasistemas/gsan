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
 * @date 12/01/2007
 */
public class ConjuntoTramitacaoRaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idUnidadeDestino;
	private String descricaoUnidadeDestino;
	private String idUsuarioResponsavel;
	private String descricaoUsuarioResponsavel;
	private String dataTramitacao;
	private String horaTramitacao;
	private String parecer;
    private String[] idRegistrosTramitacao;	
    
    
    private String idOrdemServico;
    private String descricaoTipoServico;
    private String idPavimentoRua;
    private String metragemPavimentoRua;
    private String idPavimentoCalcada;
    private String metragemPavimentoCalcada;
    
	/**
	 * @return Retorna o campo dataTramitacao.
	 */
	public String getDataTramitacao() {
		return dataTramitacao;
	}
	/**
	 * @param dataTramitacao O dataTramitacao a ser setado.
	 */
	public void setDataTramitacao(String dataTramitacao) {
		this.dataTramitacao = dataTramitacao;
	}
	/**
	 * @return Retorna o campo descricaoUnidadeDestino.
	 */
	public String getDescricaoUnidadeDestino() {
		return descricaoUnidadeDestino;
	}
	/**
	 * @param descricaoUnidadeDestino O descricaoUnidadeDestino a ser setado.
	 */
	public void setDescricaoUnidadeDestino(String descricaoUnidadeDestino) {
		this.descricaoUnidadeDestino = descricaoUnidadeDestino;
	}
	/**
	 * @return Retorna o campo descricaoUsuarioResponsavel.
	 */
	public String getDescricaoUsuarioResponsavel() {
		return descricaoUsuarioResponsavel;
	}
	/**
	 * @param descricaoUsuarioResponsavel O descricaoUsuarioResponsavel a ser setado.
	 */
	public void setDescricaoUsuarioResponsavel(String descricaoUsuarioResponsavel) {
		this.descricaoUsuarioResponsavel = descricaoUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo horaTramitacao.
	 */
	public String getHoraTramitacao() {
		return horaTramitacao;
	}
	/**
	 * @param horaTramitacao O horaTramitacao a ser setado.
	 */
	public void setHoraTramitacao(String horaTramitacao) {
		this.horaTramitacao = horaTramitacao;
	}
	/**
	 * @return Retorna o campo idUnidadeDestino.
	 */
	public String getIdUnidadeDestino() {
		return idUnidadeDestino;
	}
	/**
	 * @param idUnidadeDestino O idUnidadeDestino a ser setado.
	 */
	public void setIdUnidadeDestino(String idUnidadeDestino) {
		this.idUnidadeDestino = idUnidadeDestino;
	}
	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}
	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo parecer.
	 */
	public String getParecer() {
		return parecer;
	}
	/**
	 * @param parecer O parecer a ser setado.
	 */
	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	/**
	 * @return Retorna o campo idRegistrosTramitacao.
	 */
	public String[] getIdRegistrosTramitacao() {
		return idRegistrosTramitacao;
	}
	/**
	 * @param idRegistrosTramitacao O idRegistrosTramitacao a ser setado.
	 */
	public void setIdRegistrosTramitacao(String[] idRegistrosTramitacao) {
		this.idRegistrosTramitacao = idRegistrosTramitacao;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	public String getMetragemPavimentoCalcada() {
		return metragemPavimentoCalcada;
	}
	public void setMetragemPavimentoCalcada(String metragemPavimentoCalcada) {
		this.metragemPavimentoCalcada = metragemPavimentoCalcada;
	}
	public String getMetragemPavimentoRua() {
		return metragemPavimentoRua;
	}
	public void setMetragemPavimentoRua(String metragemPavimentoRua) {
		this.metragemPavimentoRua = metragemPavimentoRua;
	}
		

}
