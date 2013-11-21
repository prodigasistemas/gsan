/*
 * 
 */
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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0253] Pesquisar Comando de Ação de Cobrança
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class PesquisarComandoAcaoCobrancaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String cobrancaAtividade;
	
	private String cobrancaAcao;
	
	private String periodoExecucaoComandoInicial;
	
	private String periodoExecucaoComandoFinal;
	
	private String periodoGeracaoComandoInicial;
	
	private String periodoGeracaoComandoFinal;
	
	private String usuario;
	
	private String idUsuario;
	
	private String tituloComando;
	
	private String tipoPesquisa;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		cobrancaAtividade = null;
		
		cobrancaAcao = null;
		
		periodoExecucaoComandoInicial = null;
		
		periodoExecucaoComandoFinal = null;
		
		periodoGeracaoComandoInicial = null;
		
		periodoGeracaoComandoFinal = null;
		
		usuario = null;
		
		idUsuario = null;
		
		tituloComando = null;
		
		tipoPesquisa = null;

	
	}

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	/**
	 * @return Retorna o campo cobrancaAtividade.
	 */
	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	/**
	 * @param cobrancaAtividade O cobrancaAtividade a ser setado.
	 */
	public void setCobrancaAtividade(String cobrancaAtividade) {
		this.cobrancaAtividade = cobrancaAtividade;
	}

	/**
	 * @return Retorna o campo periodoExecucaoComandoFinal.
	 */
	public String getPeriodoExecucaoComandoFinal() {
		return periodoExecucaoComandoFinal;
	}

	/**
	 * @param periodoExecucaoComandoFinal O periodoExecucaoComandoFinal a ser setado.
	 */
	public void setPeriodoExecucaoComandoFinal(String periodoExecucaoComandoFinal) {
		this.periodoExecucaoComandoFinal = periodoExecucaoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoExecucaoComandoInicial.
	 */
	public String getPeriodoExecucaoComandoInicial() {
		return periodoExecucaoComandoInicial;
	}

	/**
	 * @param periodoExecucaoComandoInicial O periodoExecucaoComandoInicial a ser setado.
	 */
	public void setPeriodoExecucaoComandoInicial(
			String periodoExecucaoComandoInicial) {
		this.periodoExecucaoComandoInicial = periodoExecucaoComandoInicial;
	}

	/**
	 * @return Retorna o campo periodoGeracaoComandoInicial.
	 */
	public String getPeriodoGeracaoComandoInicial() {
		return periodoGeracaoComandoInicial;
	}

	/**
	 * @param periodoGeracaoComandoInicial O periodoGeracaoComandoInicial a ser setado.
	 */
	public void setPeriodoGeracaoComandoInicial(String periodoGeracaoComandoInicial) {
		this.periodoGeracaoComandoInicial = periodoGeracaoComandoInicial;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo periodoGeracaoComandoFinal.
	 */
	public String getPeriodoGeracaoComandoFinal() {
		return periodoGeracaoComandoFinal;
	}

	/**
	 * @param periodoGeracaoComandoFinal O periodoGeracaoComandoFinal a ser setado.
	 */
	public void setPeriodoGeracaoComandoFinal(String periodoGeracaoComandoFinal) {
		this.periodoGeracaoComandoFinal = periodoGeracaoComandoFinal;
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

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}