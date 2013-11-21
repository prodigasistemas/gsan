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

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Classe que representa o form da pagina de resposta  
 * para pesquisa de comandos de negativação por critério
 * 
 * @author: Thiago Vieira
 * @date: 21/1/2007
 */

public class ConsultarComandoNegativacaoTipoMatriculaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idNegativador;
	private String identificacaoCI;
	private String tipoPesquisaIdentificacaoCI;
	private String idUsuarioResponsavel;
	private String nomeUsuarioResponsavel;
	private String usuarioOk;
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
		
		this.idNegativador = "";
		this.identificacaoCI = "";
		this.tipoPesquisaIdentificacaoCI = "";
		this.idUsuarioResponsavel = "";
		this.nomeUsuarioResponsavel = "";
		this.usuarioOk = "";
    }

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
	 * @return Retorna o campo tipoPesquisaIdentificacaoCI.
	 */
	public String getTipoPesquisaIdentificacaoCI() {
		return tipoPesquisaIdentificacaoCI;
	}

	/**
	 * @param tipoPesquisaIdentificacaoCI O tipoPesquisaIdentificacaoCI a ser setado.
	 */
	public void setTipoPesquisaIdentificacaoCI(String tipoPesquisaIdentificacaoCI) {
		this.tipoPesquisaIdentificacaoCI = tipoPesquisaIdentificacaoCI;
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

			
}