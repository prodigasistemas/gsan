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
* Thiago Vieira de Melo
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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Classe que representa o fomrulário da pagina de exibição do filtro 
 * para pesquisa de comandos de negativação
 * 
 * @author: Thiago Vieira
 * @date: 27/12/2007
 */

public class FiltrarComandoNegativacaoPorCriterioActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String tituloComando;
	
	private String tipoBuscaTituloComando;
	
	private String comandoSimulado;
	
	private String dataGeracaoComandoInicial;
	
	private String dataGeracaoComandoFinal;
	
	private String dataExecucaoComandoInicial;
	
	private String dataExecucaoComandoFinal;
	
	private String usuarioResponsavelId;
	
	private String usuarioResponsavelNome;
	
	private String checkAtualizar;
	
	private String usuarioOk;
	
	private String idNegativador;

	private Collection colecaoNegativador;
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.tituloComando = "";
    	this.tipoBuscaTituloComando = "";
    	this.comandoSimulado = "";
    	this.dataGeracaoComandoInicial = "";
    	this.dataGeracaoComandoFinal = "";
    	this.dataExecucaoComandoInicial = "";
    	this.dataExecucaoComandoFinal = "";
    	this.usuarioResponsavelId = "";
    	this.usuarioResponsavelNome = "";
    	this.checkAtualizar = "";
    	this.usuarioOk = "";
    	this.idNegativador = "";
    	this.colecaoNegativador = new ArrayList();

    }
	
	/**
	 * @return Retorna o campo checkAtualizar.
	 */
	public String getCheckAtualizar() {
		return checkAtualizar;
	}

	/**
	 * @return Retorna o campo usuarioOk.
	 */
	public String getUsuarioOk() {
		return usuarioOk;
	}

	/**
	 * @param usuarioOk O usuarioOk a ser setado.
	 */
	public void setUsuarioOk(String usuarioOk) {
		this.usuarioOk = usuarioOk;
	}

	/**
	 * @param checkAtualizar O checkAtualizar a ser setado.
	 */
	public void setCheckAtualizar(String checkAtualizar) {
		this.checkAtualizar = checkAtualizar;
	}

	/**
	 * @return Retorna o campo comandoSimulado.
	 */
	public String getComandoSimulado() {
		return comandoSimulado;
	}

	/**
	 * @param comandoSimulado O comandoSimulado a ser setado.
	 */
	public void setComandoSimulado(String comandoSimulado) {
		this.comandoSimulado = comandoSimulado;
	}

	/**
	 * @return Retorna o campo dataExecucaoComandoFinal.
	 */
	public String getDataExecucaoComandoFinal() {
		return dataExecucaoComandoFinal;
	}

	/**
	 * @param dataExecucaoComandoFinal O dataExecucaoComandoFinal a ser setado.
	 */
	public void setDataExecucaoComandoFinal(String dataExecucaoComandoFinal) {
		this.dataExecucaoComandoFinal = dataExecucaoComandoFinal;
	}

	/**
	 * @return Retorna o campo dataExecucaoComandoInicial.
	 */
	public String getDataExecucaoComandoInicial() {
		return dataExecucaoComandoInicial;
	}

	/**
	 * @param dataExecucaoComandoInicial O dataExecucaoComandoInicial a ser setado.
	 */
	public void setDataExecucaoComandoInicial(String dataExecucaoComandoInicial) {
		this.dataExecucaoComandoInicial = dataExecucaoComandoInicial;
	}

	/**
	 * @return Retorna o campo dataGeracaoComandoFinal.
	 */
	public String getDataGeracaoComandoFinal() {
		return dataGeracaoComandoFinal;
	}

	/**
	 * @param dataGeracaoComandoFinal O dataGeracaoComandoFinal a ser setado.
	 */
	public void setDataGeracaoComandoFinal(String dataGeracaoComandoFinal) {
		this.dataGeracaoComandoFinal = dataGeracaoComandoFinal;
	}

	/**
	 * @return Retorna o campo dataGeracaoComandoInicial.
	 */
	public String getDataGeracaoComandoInicial() {
		return dataGeracaoComandoInicial;
	}

	/**
	 * @param dataGeracaoComandoInicial O dataGeracaoComandoInicial a ser setado.
	 */
	public void setDataGeracaoComandoInicial(String dataGeracaoComandoInicial) {
		this.dataGeracaoComandoInicial = dataGeracaoComandoInicial;
	}

	/**
	 * @return Retorna o campo tipoBuscaTituloComando.
	 */
	public String getTipoBuscaTituloComando() {
		return tipoBuscaTituloComando;
	}

	/**
	 * @param tipoBuscaTituloComando O tipoBuscaTituloComando a ser setado.
	 */
	public void setTipoBuscaTituloComando(String tipoBuscaTituloComando) {
		this.tipoBuscaTituloComando = tipoBuscaTituloComando;
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
	 * @return Retorna o campo usuarioResponsavelId.
	 */
	public String getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}

	/**
	 * @param usuarioResponsavelId O usuarioResponsavelId a ser setado.
	 */
	public void setUsuarioResponsavelId(String usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}

	/**
	 * @return Retorna o campo usuarioResponsavelNome.
	 */
	public String getUsuarioResponsavelNome() {
		return usuarioResponsavelNome;
	}

	/**
	 * @param usuarioResponsavelNome O usuarioResponsavelNome a ser setado.
	 */
	public void setUsuarioResponsavelNome(String usuarioResponsavelNome) {
		this.usuarioResponsavelNome = usuarioResponsavelNome;
	}

	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}
	
}