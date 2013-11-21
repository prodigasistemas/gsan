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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipeComponentes;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosEquipeActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// Dados da Equipe
	private String idEquipe;
    private String nomeEquipe;
    private String placaVeiculo;
    private String cargaTrabalhoDia;
    private String unidadeOrganizacionalId;
    private String unidadeOrganizacionalDescricao;
    private String tipoPerfilServicoId;
    private String tipoPerfilServicoDescricao;
    private String codigoDdd;
    private String numeroTelefone;
    private String numeroImei;
    
    // Equipe Componente
    private Collection<EquipeComponentes> equipeComponentes = new ArrayList<EquipeComponentes>();
    
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    
    	this.idEquipe = null;
        this.nomeEquipe = null;
        this.placaVeiculo = null;
        this.cargaTrabalhoDia = null;
        this.unidadeOrganizacionalId = null;
        this.unidadeOrganizacionalDescricao = null;
        this.tipoPerfilServicoId = null;
        this.tipoPerfilServicoDescricao = null;
        this.codigoDdd = null;
        this.numeroTelefone = null;
        this.numeroImei = null;
    }

    /**
	 * @return Retorna a carga de trabalho por dia.
	 */
	public String getCargaTrabalhoDia() {
		return cargaTrabalhoDia;
	}
	/**
	 * @param cargaTrabalhoDia A carga de trabalho por dia a ser setada.
	 */
	public void setCargaTrabalhoDia(String cargaTrabalhoDia) {
		this.cargaTrabalhoDia = cargaTrabalhoDia;
	}
    /**
	 * @return Retorna o nome da equipe.
	 */
	public String getNomeEquipe() {
		return nomeEquipe;
	}
	/**
	 * @param nomeEquipe O nome da equipe a ser setado.
	 */
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}
    /**
	 * @return Retorna a placa do veículo.
	 */
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	/**
	 * @param placaVeiculo A placa do veículo a ser setada.
	 */
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
    /**
	 * @return Retorna a descrição do tipo de perfil do serviço.
	 */
	public String getTipoPerfilServicoDescricao() {
		return tipoPerfilServicoDescricao;
	}
	/**
	 * @param tipoPerfilServicoDescricao A descrição do tipo de perfil de serviço a ser setada.
	 */
	public void setTipoPerfilServicoDescricao(String tipoPerfilServicoDescricao) {
		this.tipoPerfilServicoDescricao = tipoPerfilServicoDescricao;
	}
    /**
	 * @return Retorna o id do tipo de perfil do serviço.
	 */
	public String getTipoPerfilServicoId() {
		return tipoPerfilServicoId;
	}
	/**
	 * @param tipoPerfilServicoDescricao O id do tipo de perfil de serviço a ser setado.
	 */
	public void setTipoPerfilServicoId(String tipoPerfilServicoId) {
		this.tipoPerfilServicoId = tipoPerfilServicoId;
	}
    /**
	 * @return Retorna a descrição da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}
	/**
	 * @param unidadeOrganizacionalDescricao A descrição da unidade organizacional a ser setada.
	 */
	public void setUnidadeOrganizacionalDescricao(
			String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}
    /**
	 * @return Retorna o id da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}
	/**
	 * @param unidadeOrganizacionalId O id da unidade organizacional a ser setado.
	 */
	public void setUnidadeOrganizacionalId(String unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}

	public Collection<EquipeComponentes> getEquipeComponentes() {
		return equipeComponentes;
	}
	public void setEquipeComponentes(Collection<EquipeComponentes> equipeComponentes) {
		this.equipeComponentes = equipeComponentes;
	}
	public String getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(String numeroImei) {
		this.numeroImei = numeroImei;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	
}