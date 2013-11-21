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
package gcom.gui;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Controlador ActionForm 
 * 
 * @author thiago
 * @created 20/12/2005
 */ 
public class ControladorGcomActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String acao = null;

	private Collection collObjeto = null;

	private String caminhoFuncionalidade;
	private String labelPaginaSucesso;
	private String mensagemPaginaSucesso;
	

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    	this.collObjeto = new Vector();
    	this.caminhoFuncionalidade = "";
    	this.labelPaginaSucesso = "";
    	this.mensagemPaginaSucesso = "";    	
    }

    public ObjetoTransmissaoDados getOTD(HttpServletRequest request) {
    	return (ObjetoTransmissaoDados) request.getSession(false).getAttribute(ObjetoTransmissaoDados.OBJETO_TRANSMISSAO_DADOS);
    }

    public void resetOTD(HttpServletRequest request) {
    	request.setAttribute(ObjetoTransmissaoDados.OBJETO_TRANSMISSAO_DADOS,null);
    }

	public String getCaminhoFuncionalidade() {
		return caminhoFuncionalidade;
	}

	public void setCaminhoFuncionalidade(String caminhoFuncionalidade) {
		this.caminhoFuncionalidade = caminhoFuncionalidade;
	}

	public String getLabelPaginaSucesso() {
		return labelPaginaSucesso;
	}

	public void setLabelPaginaSucesso(String labelPaginaSucesso) {
		this.labelPaginaSucesso = labelPaginaSucesso;
	}
	public String getMensagemPaginaSucesso() {
		return mensagemPaginaSucesso;
	}

	public void setMensagemPaginaSucesso(String mensagemPaginaSucesso) {
		this.mensagemPaginaSucesso = mensagemPaginaSucesso;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Collection getCollObjeto() {
		return collObjeto;
	}

	public void setCollObjeto(Collection collObjeto) {
		this.collObjeto = collObjeto;
	}
}