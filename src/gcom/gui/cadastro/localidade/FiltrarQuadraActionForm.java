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
package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarQuadraActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String localidadeID;

    private String localidadeNome;

    private String[] quadraID;

    private String quadraMensagem;

    private String quadraNM;

    private String setorComercialCD;

    private String setorComercialID;

    private String setorComercialNome;
    
    private String idRota;
    
    private String codigoRota;

    private String indicadorUso;
    
    private String tipoPesquisa;

    public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getLocalidadeID() {
        return localidadeID;
    }

    public void setLocalidadeID(String localidadeID) {
        this.localidadeID = localidadeID;
    }

    public String getLocalidadeNome() {
        return localidadeNome;
    }

    public void setLocalidadeNome(String localidadeNome) {
        this.localidadeNome = localidadeNome;
    }

    public String[] getQuadraID() {
        return quadraID;
    }

    public void setQuadraID(String[] quadraID) {
        this.quadraID = quadraID;
    }

    public String getQuadraMensagem() {
        return quadraMensagem;
    }

    public void setQuadraMensagem(String quadraMensagem) {
        this.quadraMensagem = quadraMensagem;
    }

    public String getQuadraNM() {
        return quadraNM;
    }

    public void setQuadraNM(String quadraNM) {
        this.quadraNM = quadraNM;
    }

    public String getSetorComercialCD() {
        return setorComercialCD;
    }

    public void setSetorComercialCD(String setorComercialCD) {
        this.setorComercialCD = setorComercialCD;
    }

    public String getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

    public String getSetorComercialNome() {
        return setorComercialNome;
    }

    public void setSetorComercialNome(String setorComercialNome) {
        this.setorComercialNome = setorComercialNome;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo idRota.
	 */
	public String getIdRota() {
		return idRota;
	}

	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}

}