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
 * 
 * Este caso de uso gera relatorio de anormalidade de consumo
 * 
 * [UC0638]Gerar Relatorio Anormalidade Consumo
 * 
 * @author Kassia Albuquerque
 * @date 24/09/2006
 * 
 */


public class ComandarEncerramentoRegistroAtendimentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dataAtendimentoInicial;
	private String dataAtendimentoFinal;
	private String motivoEncerramento;
	private String idUsuario;
	private String nomeUsuario;
	private String idUnidadeAtendimento;
	private String nomeUnidadeAtendimento;
	private String idUnidadeAtual;
	private String nomeUnidadeAtual;
	private String idUnidadeSuperior;
	private String nomeUnidadeSuperior;
	private String[] idsSolicitacaoTipoEspecificcacoes;
	
	/**
	 * @return Retorna o campo idsSolicitacaoTipoEspecificcacoes.
	 */
	public String[] getIdsSolicitacaoTipoEspecificcacoes() {
		return idsSolicitacaoTipoEspecificcacoes;
	}
	/**
	 * @param idsSolicitacaoTipoEspecificcacoes O idsSolicitacaoTipoEspecificcacoes a ser setado.
	 */
	public void setIdsSolicitacaoTipoEspecificcacoes(
			String[] idsSolicitacaoTipoEspecificcacoes) {
		this.idsSolicitacaoTipoEspecificcacoes = idsSolicitacaoTipoEspecificcacoes;
	}
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
	 * @return Retorna o campo idUnidadeAtendimento.
	 */
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}
	/**
	 * @param idUnidadeAtendimento O idUnidadeAtendimento a ser setado.
	 */
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}
	/**
	 * @return Retorna o campo idUnidadeAtual.
	 */
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}
	/**
	 * @param idUnidadeAtual O idUnidadeAtual a ser setado.
	 */
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
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
	 * @return Retorna o campo idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return Retorna o campo motivoEncerramento.
	 */
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	/**
	 * @param motivoEncerramento O motivoEncerramento a ser setado.
	 */
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	/**
	 * @return Retorna o campo nomeUnidadeAtendimento.
	 */
	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}
	/**
	 * @param nomeUnidadeAtendimento O nomeUnidadeAtendimento a ser setado.
	 */
	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
	}
	/**
	 * @return Retorna o campo nomeUnidadeAtual.
	 */
	public String getNomeUnidadeAtual() {
		return nomeUnidadeAtual;
	}
	/**
	 * @param nomeUnidadeAtual O nomeUnidadeAtual a ser setado.
	 */
	public void setNomeUnidadeAtual(String nomeUnidadeAtual) {
		this.nomeUnidadeAtual = nomeUnidadeAtual;
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
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
