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

import org.apache.struts.action.ActionForm;

/**
 * [UC0204] Consultar Transferências
 * @author Rafael Corrêa
 * @since 22/08/2008
 */
public class ConsultarTransferenciasDebitoActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idImovelOrigem;
	
	private String inscricaoImovelOrigem;
	
	private String idImovelDestino;
	
	private String inscricaoImovelDestino;
	
	private String dataInicio;
	
	private String dataFim;
	
	private String idUsuario;
	
	private String loginUsuario;
	
	private String nomeUsuario;
	
	private String tipoPesquisa;

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
	 * @return Retorna o campo loginUsuario.
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}

	/**
	 * @param loginUsuario O loginUsuario a ser setado.
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
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

	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * @return Retorna o campo dataFim.
	 */
	public String getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim O dataFim a ser setado.
	 */
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return Retorna o campo dataInicio.
	 */
	public String getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio O dataInicio a ser setado.
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public String getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(String idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public String getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(String idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo inscricaoImovelDestino.
	 */
	public String getInscricaoImovelDestino() {
		return inscricaoImovelDestino;
	}

	/**
	 * @param inscricaoImovelDestino O inscricaoImovelDestino a ser setado.
	 */
	public void setInscricaoImovelDestino(String inscricaoImovelDestino) {
		this.inscricaoImovelDestino = inscricaoImovelDestino;
	}

	/**
	 * @return Retorna o campo inscricaoImovelOrigem.
	 */
	public String getInscricaoImovelOrigem() {
		return inscricaoImovelOrigem;
	}

	/**
	 * @param inscricaoImovelOrigem O inscricaoImovelOrigem a ser setado.
	 */
	public void setInscricaoImovelOrigem(String inscricaoImovelOrigem) {
		this.inscricaoImovelOrigem = inscricaoImovelOrigem;
	}
	
	

}
