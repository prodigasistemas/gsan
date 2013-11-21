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

package gcom.micromedicao.bean;

import java.io.Serializable;

/**
 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
 * 
 * @author Raphael Rossiter
 * @date 25/01/2008
 */
public class AssociarConjuntoRotasCriterioCobrancaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCriterioCobranca;
	private Integer idCobrancaAcao;
	private Integer idGrupoCobranca;
	private Integer idGerencialRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer cdSetorComercialInicial;
	private Integer cdSetorComercialFinal;
	private Integer nnRotaInicial;
	private Integer nnRotaFinal;
	
	private boolean validarCriterioCobranca;
	
	public AssociarConjuntoRotasCriterioCobrancaHelper(){}

	public Integer getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public void setCdSetorComercialFinal(Integer cdSetorComercialFinal) {
		this.cdSetorComercialFinal = cdSetorComercialFinal;
	}

	public Integer getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(Integer cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public Integer getIdGerencialRegional() {
		return idGerencialRegional;
	}

	public void setIdGerencialRegional(Integer idGerencialRegional) {
		this.idGerencialRegional = idGerencialRegional;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getNnRotaFinal() {
		return nnRotaFinal;
	}

	public void setNnRotaFinal(Integer nnRotaFinal) {
		this.nnRotaFinal = nnRotaFinal;
	}

	public Integer getNnRotaInicial() {
		return nnRotaInicial;
	}

	public void setNnRotaInicial(Integer nnRotaInicial) {
		this.nnRotaInicial = nnRotaInicial;
	}

	public Integer getIdCobrancaAcao() {
		return idCobrancaAcao;
	}

	public void setIdCobrancaAcao(Integer idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public Integer getIdCriterioCobranca() {
		return idCriterioCobranca;
	}

	public void setIdCriterioCobranca(Integer idCriterioCobranca) {
		this.idCriterioCobranca = idCriterioCobranca;
	}

	public boolean isValidarCriterioCobranca() {
		return validarCriterioCobranca;
	}

	public void setValidarCriterioCobranca(boolean validarCriterioCobranca) {
		this.validarCriterioCobranca = validarCriterioCobranca;
	}

	
	
	
}
