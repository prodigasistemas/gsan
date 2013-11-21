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
package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.util.Util;

import java.util.Collection;


/**
 * [UC0424] Consultar Registro de Atendimento
 * 
 * @author Vivianne Sousa
 * @date 16/05/2011
 */
public class DadosRAReiteracaoHelper {
	
	private String nomeSolicitante;
	private Integer idClienteSolicitante;
	private Integer idUnidadeSolicitante;
	private RAReiteracao raReiteracao;
	private Collection colecaoRAReiteracaoFone;
	private String fonePrincipal;
	private String nomeClienteSolicitante;
	private String nomeUnidadeSolicitante;
	private String enderecoSolicitante;
	
	public String getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(String enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public Collection getColecaoRAReiteracaoFone() {
		return colecaoRAReiteracaoFone;
	}

	public void setColecaoRAReiteracaoFone(Collection colecaoRAReiteracaoFone) {
		this.colecaoRAReiteracaoFone = colecaoRAReiteracaoFone;
	}

	public String getFonePrincipal() {
		return fonePrincipal;
	}

	public void setFonePrincipal(String fonePrincipal) {
		this.fonePrincipal = fonePrincipal;
	}

	public Integer getIdClienteSolicitante() {
		return idClienteSolicitante;
	}

	public void setIdClienteSolicitante(Integer idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}

	public Integer getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}

	public void setIdUnidadeSolicitante(Integer idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public RAReiteracao getRaReiteracao() {
		return raReiteracao;
	}

	public void setRaReiteracao(RAReiteracao raReiteracao) {
		this.raReiteracao = raReiteracao;
	}

	public String getDataReiteracaoFormatada() {
		return Util.formatarDataComHora(this.raReiteracao.getUltimaAlteracao());
	}

	public String getNomeClienteSolicitante() {
		return nomeClienteSolicitante;
	}

	public void setNomeClienteSolicitante(String nomeClienteSolicitante) {
		this.nomeClienteSolicitante = nomeClienteSolicitante;
	}

	public String getNomeUnidadeSolicitante() {
		return nomeUnidadeSolicitante;
	}

	public void setNomeUnidadeSolicitante(String nomeUnidadeSolicitante) {
		this.nomeUnidadeSolicitante = nomeUnidadeSolicitante;
	}

}
