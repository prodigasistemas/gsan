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
package gcom.cobranca.bean;

import gcom.util.ConstantesSistema;

import java.util.Date;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */

public class ComandoNegativacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String tituloComando;
	private Short tipoPesquisaTituloComando;
	private Short indicadorComandoSimulado;
	private Date geracaoComandoInicio;
	private Date geracaoComandoFim;
	private Date execucaoComandoInicio;
	private Date execucaoComandoFim;
	private Integer idUsuarioResponsavel;
	
	private Integer idComandoNegativacao;
	private Integer quantidadeInclusoes;
	private String nomeUsuarioResponsavel;
	
	private String nomeClienteNegativador;
	private Integer idNegativador;
	
	private String identificacaoCI;
	private Short tipoPesquisaIdentificacaoCI;
	
	private String popup;
	
	
	
	
	/**
	 * @return Retorna o campo identificacaoCI.
	 */
	public String getIdentificacaoCI() {
		return identificacaoCI;
	}

	/**
	 * @param identificacaoCI O identificacaoCI a ser setado.
	 */
	public void setIdentificacaoCI(String identificacaoCI) {
		this.identificacaoCI = identificacaoCI;
	}

	/**
	 * @return Retorna o campo tipoPesquisaIdentificacaoCI.
	 */
	public Short getTipoPesquisaIdentificacaoCI() {
		return tipoPesquisaIdentificacaoCI;
	}

	/**
	 * @param tipoPesquisaIdentificacaoCI O tipoPesquisaIdentificacaoCI a ser setado.
	 */
	public void setTipoPesquisaIdentificacaoCI(Short tipoPesquisaIdentificacaoCI) {
		this.tipoPesquisaIdentificacaoCI = tipoPesquisaIdentificacaoCI;
	}

	public ComandoNegativacaoHelper() {
	}

	public ComandoNegativacaoHelper(
			String tituloComando,
			Short tipoPesquisaTituloComando,
			Short indicadorComandoSimulado,
			Date geracaoComandoInicio,
			Date geracaoComandoFim,
			Date execucaoComandoInicio,
			Date execucaoComandoFim,
			Integer idUsuarioResponsavel,
			Integer idComandoNegativacao,
			Integer quantidadeInclusoes,
			String nomeUsuarioResponsavel
			) {
		
		this.tituloComando = tituloComando;
		this.tipoPesquisaTituloComando = tipoPesquisaTituloComando;
		this.indicadorComandoSimulado = indicadorComandoSimulado;
		this.geracaoComandoInicio = geracaoComandoInicio;
		this.geracaoComandoFim = geracaoComandoFim;
		this.execucaoComandoInicio = execucaoComandoInicio;
		this.execucaoComandoFim = execucaoComandoFim;
		this.idUsuarioResponsavel = idUsuarioResponsavel;
		this.idComandoNegativacao = idComandoNegativacao;
		this.quantidadeInclusoes = quantidadeInclusoes;
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
		
		
	}

	public ComandoNegativacaoHelper(
			String tituloComando,
			Short tipoPesquisaTituloComando,
			Short indicadorComandoSimulado,
			Date geracaoComandoInicio,
			Date geracaoComandoFim,
			Date execucaoComandoInicio,
			Date execucaoComandoFim,
			Integer idUsuarioResponsavel,
			Integer idComandoNegativacao,
			Integer quantidadeInclusoes,
			String nomeUsuarioResponsavel,
			String nomeClienteNegativador
			) {
		
		this.tituloComando = tituloComando;
		this.tipoPesquisaTituloComando = tipoPesquisaTituloComando;
		this.indicadorComandoSimulado = indicadorComandoSimulado;
		this.geracaoComandoInicio = geracaoComandoInicio;
		this.geracaoComandoFim = geracaoComandoFim;
		this.execucaoComandoInicio = execucaoComandoInicio;
		this.execucaoComandoFim = execucaoComandoFim;
		this.idUsuarioResponsavel = idUsuarioResponsavel;
		this.idComandoNegativacao = idComandoNegativacao;
		this.quantidadeInclusoes = quantidadeInclusoes;
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
		this.nomeClienteNegativador = nomeClienteNegativador;
		
	}
	
	/**
	 * @return Retorna o campo execucaoComandoFim.
	 */
	public Date getExecucaoComandoFim() {
		return execucaoComandoFim;
	}

	/**
	 * @param execucaoComandoFim O execucaoComandoFim a ser setado.
	 */
	public void setExecucaoComandoFim(Date execucaoComandoFim) {
		this.execucaoComandoFim = execucaoComandoFim;
	}

	/**
	 * @return Retorna o campo execucaoComandoInicio.
	 */
	public Date getExecucaoComandoInicio() {
		return execucaoComandoInicio;
	}

	/**
	 * @param execucaoComandoInicio O execucaoComandoInicio a ser setado.
	 */
	public void setExecucaoComandoInicio(Date execucaoComandoInicio) {
		this.execucaoComandoInicio = execucaoComandoInicio;
	}

	/**
	 * @return Retorna o campo geracaoComandoFim.
	 */
	public Date getGeracaoComandoFim() {
		return geracaoComandoFim;
	}

	/**
	 * @param geracaoComandoFim O geracaoComandoFim a ser setado.
	 */
	public void setGeracaoComandoFim(Date geracaoComandoFim) {
		this.geracaoComandoFim = geracaoComandoFim;
	}

	/**
	 * @return Retorna o campo geracaoComandoInicio.
	 */
	public Date getGeracaoComandoInicio() {
		return geracaoComandoInicio;
	}

	/**
	 * @param geracaoComandoInicio O geracaoComandoInicio a ser setado.
	 */
	public void setGeracaoComandoInicio(Date geracaoComandoInicio) {
		this.geracaoComandoInicio = geracaoComandoInicio;
	}

	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public Integer getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}

	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(Integer idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}

	/**
	 * @return Retorna o campo indicadorComandoSimulado.
	 */
	public Short getIndicadorComandoSimulado() {
		return indicadorComandoSimulado;
	}

	/**
	 * @param indicadorComandoSimulado O indicadorComandoSimulado a ser setado.
	 */
	public void setIndicadorComandoSimulado(Short indicadorComandoSimulado) {
		this.indicadorComandoSimulado = indicadorComandoSimulado;
	}

	/**
	 * @return Retorna o campo tipoPesquisaTituloComando.
	 */
	public Short getTipoPesquisaTituloComando() {
		return tipoPesquisaTituloComando;
	}

	/**
	 * @param tipoPesquisaTituloComando O tipoPesquisaTituloComando a ser setado.
	 */
	public void setTipoPesquisaTituloComando(Short tipoPesquisaTituloComando) {
		this.tipoPesquisaTituloComando = tipoPesquisaTituloComando;
	}

	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}

	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	/**
	 * @return Retorna o campo idComandoNegativacao.
	 */
	public Integer getIdComandoNegativacao() {
		return idComandoNegativacao;
	}

	/**
	 * @param idComandoNegativacao O idComandoNegativacao a ser setado.
	 */
	public void setIdComandoNegativacao(Integer idComandoNegativacao) {
		this.idComandoNegativacao = idComandoNegativacao;
	}

	/**
	 * @return Retorna o campo nomeUsuarioResponsavel.
	 */
	public String getNomeUsuarioResponsavel() {
		return nomeUsuarioResponsavel;
	}

	/**
	 * @param nomeUsuarioResponsavel O nomeUsuarioResponsavel a ser setado.
	 */
	public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
	}

	/**
	 * @return Retorna o campo quantidadeInclusoes.
	 */
	public Integer getQuantidadeInclusoes() {
		return quantidadeInclusoes;
	}

	/**
	 * @param quantidadeInclusoes O quantidadeInclusoes a ser setado.
	 */
	public void setQuantidadeInclusoes(Integer quantidadeInclusoes) {
		this.quantidadeInclusoes = quantidadeInclusoes;
	}

	public String getIndicadorComandoSimuladoString(){
		String indicadorComandoSimuladoString = "Não";
		if(getIndicadorComandoSimulado()!= null 
				&& getIndicadorComandoSimulado().equals(ConstantesSistema.SIM)){
			indicadorComandoSimuladoString = "Sim";
		}
		return indicadorComandoSimuladoString;
	}
	
	public String getIndicadorQtdInclusoesString(){
		
		String indicadorQtdInclusoesString = ConstantesSistema.SIM.toString();
		if (getQuantidadeInclusoes()!=null && getIndicadorComandoSimulado().equals(ConstantesSistema.NAO)
			&& !getQuantidadeInclusoes().equals(new Integer(ConstantesSistema.ZERO))){
							
				indicadorQtdInclusoesString = ConstantesSistema.NAO.toString();
			}
		return indicadorQtdInclusoesString;
	}

	/**
	 * @return Retorna o campo nomeClienteNegativador.
	 */
	public String getNomeClienteNegativador() {
		return nomeClienteNegativador;
	}

	/**
	 * @param nomeClienteNegativador O nomeClienteNegativador a ser setado.
	 */
	public void setNomeClienteNegativador(String nomeClienteNegativador) {
		this.nomeClienteNegativador = nomeClienteNegativador;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public Integer getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Returns the popup.
	 */
	public String getPopup() {
		return popup;
	}

	/**
	 * @param popup The popup to set.
	 */
	public void setPopup(String popup) {
		this.popup = popup;
	}
	
	
	
	
}