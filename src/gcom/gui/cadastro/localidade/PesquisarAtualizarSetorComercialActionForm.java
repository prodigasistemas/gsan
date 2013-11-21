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

public class PesquisarAtualizarSetorComercialActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String indicadorUso;

    private String localidadeID;
    private String localidadeNome;

    private String municipioID;
    private String municipioNome;

    private String setorComercialCD;
    private String setorComercialID;
    private String setorComercialNome;
    
    private String tipoPesquisaDescricao;
    private String tipoPesquisaMunicipio;
    
    private String fonteCaptacao;
    private String descricaoFonteCaptacao;
    
    private String indicadorSetorAlternativo;
    
    private String indicadorBloqueio;


	public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
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

    public String getMunicipioID() {
        return municipioID;
    }

    public void setMunicipioID(String municipioID) {
        this.municipioID = municipioID;
    }

    public String getMunicipioNome() {
        return municipioNome;
    }

    public void setMunicipioNome(String municipioNome) {
        this.municipioNome = municipioNome;
    }

    public String getSetorComercialCD() {
        return setorComercialCD;
    }

    public void setSetorComercialCD(String setorComercialCD) {
        this.setorComercialCD = setorComercialCD;
    }

    public String getSetorComercialNome() {
        return setorComercialNome;
    }

    public void setSetorComercialNome(String setorComercialNome) {
        this.setorComercialNome = setorComercialNome;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

	/**
	 * @return Retorna o campo tipoPesquisaDescricao.
	 */
	public String getTipoPesquisaDescricao() {
		return tipoPesquisaDescricao;
	}

	/**
	 * @param tipoPesquisaDescricao O tipoPesquisaDescricao a ser setado.
	 */
	public void setTipoPesquisaDescricao(String tipoPesquisaDescricao) {
		this.tipoPesquisaDescricao = tipoPesquisaDescricao;
	}

	/**
	 * @return Retorna o campo tipoPesquisaMunicipio.
	 */
	public String getTipoPesquisaMunicipio() {
		return tipoPesquisaMunicipio;
	}

	/**
	 * @param tipoPesquisaMunicipio O tipoPesquisaMunicipio a ser setado.
	 */
	public void setTipoPesquisaMunicipio(String tipoPesquisaMunicipio) {
		this.tipoPesquisaMunicipio = tipoPesquisaMunicipio;
	}

	public String getDescricaoFonteCaptacao() {
		return descricaoFonteCaptacao;
	}

	public void setDescricaoFonteCaptacao(String descricaoFonteCaptacao) {
		this.descricaoFonteCaptacao = descricaoFonteCaptacao;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIndicadorSetorAlternativo() {
		return indicadorSetorAlternativo;
	}

	public void setIndicadorSetorAlternativo(String indicadorSetorAlternativo) {
		this.indicadorSetorAlternativo = indicadorSetorAlternativo;
	}
	
	public String getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(String indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}
	
	
}