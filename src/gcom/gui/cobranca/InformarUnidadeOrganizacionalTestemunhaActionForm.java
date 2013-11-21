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
 * [UC0796] Informar Unidade Negócio Testemunha
 * @author Rafael Corrêa
 * @since 16/05/2008
 */
public class InformarUnidadeOrganizacionalTestemunhaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idUnidadeOrganizacional;
	
	private String nomeUnidadeOrganizacional;
	
	private String idTestemunha;
	
	private String loginTestemunha;
	
	private String nomeTestemunha;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo idTestemunha.
	 */
	public String getIdTestemunha() {
		return idTestemunha;
	}


	/**
	 * @param idTestemunha O idTestemunha a ser setado.
	 */
	public void setIdTestemunha(String idTestemunha) {
		this.idTestemunha = idTestemunha;
	}


	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}


	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}


	/**
	 * @return Retorna o campo nomeTestemunha.
	 */
	public String getNomeTestemunha() {
		return nomeTestemunha;
	}


	/**
	 * @param nomeTestemunha O nomeTestemunha a ser setado.
	 */
	public void setNomeTestemunha(String nomeTestemunha) {
		this.nomeTestemunha = nomeTestemunha;
	}

	/**
	 * @return Retorna o campo loginTestemunha.
	 */
	public String getLoginTestemunha() {
		return loginTestemunha;
	}

	/**
	 * @param loginTestemunha O loginTestemunha a ser setado.
	 */
	public void setLoginTestemunha(String loginTestemunha) {
		this.loginTestemunha = loginTestemunha;
	}

	/**
	 * @return Retorna o campo nomeUnidadeOrganizacional.
	 */
	public String getNomeUnidadeOrganizacional() {
		return nomeUnidadeOrganizacional;
	}

	/**
	 * @param nomeUnidadeOrganizacional O nomeUnidadeOrganizacional a ser setado.
	 */
	public void setNomeUnidadeOrganizacional(String nomeUnidadeOrganizacional) {
		this.nomeUnidadeOrganizacional = nomeUnidadeOrganizacional;
	}

}
