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
package gcom.gui.micromedicao.hidrometro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

public class PesquisarHidrometroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String anoFabricacao;

	private String dataAquisicao;

	private String faixaFinal;

	private String faixaInicial;

	private String fixo;

	private String finalidade;

	private String idHidrometroCapacidade;

	private String idHidrometroClasseMetrologica;

	private String idHidrometroDiametro;

	private String idHidrometroTipo;

	private String idLocalArmazenamento;

	private String descricaoLocalArmazenamento;

	private String idHidrometroMarca;

	private String idHidrometroSituacao;

	private String numeroHidrometro;

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	/*	anoFabricacao = null;

		dataAquisicao = null;

		faixaFinal = null;

		faixaInicial = null;

		fixo = null;

		idHidrometroCapacidade = null;

		idHidrometroClasseMetrologica = null;

		idHidrometroDiametro = null;

		idHidrometroTipo = null;

		idLocalArmazenamento = null;

		idHidrometroMarca = null;

		idHidrometroSituacao = null;

		numeroHidrometro = null;

		finalidade = null;

		descricaoLocalArmazenamento = null;
*/
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(String dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public String getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getIdHidrometroCapacidade() {
		return idHidrometroCapacidade;
	}

	public void setIdHidrometroCapacidade(String idHidrometroCapacidade) {
		this.idHidrometroCapacidade = idHidrometroCapacidade;
	}

	public String getIdHidrometroClasseMetrologica() {
		return idHidrometroClasseMetrologica;
	}

	public void setIdHidrometroClasseMetrologica(
			String idHidrometroClasseMetrologica) {
		this.idHidrometroClasseMetrologica = idHidrometroClasseMetrologica;
	}

	public String getIdHidrometroDiametro() {
		return idHidrometroDiametro;
	}

	public void setIdHidrometroDiametro(String idHidrometroDiametro) {
		this.idHidrometroDiametro = idHidrometroDiametro;
	}

	public String getIdHidrometroMarca() {
		return idHidrometroMarca;
	}

	public void setIdHidrometroMarca(String idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

	public String getIdHidrometroSituacao() {
		return idHidrometroSituacao;
	}

	public void setIdHidrometroSituacao(String idHidrometroSituacao) {
		this.idHidrometroSituacao = idHidrometroSituacao;
	}

	public String getIdHidrometroTipo() {
		return idHidrometroTipo;
	}

	public void setIdHidrometroTipo(String idHidrometroTipo) {
		this.idHidrometroTipo = idHidrometroTipo;
	}

	public String getIdLocalArmazenamento() {
		return idLocalArmazenamento;
	}

	public void setIdLocalArmazenamento(String idLocalArmazenamento) {
		this.idLocalArmazenamento = idLocalArmazenamento;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getDescricaoLocalArmazenamento() {
		return descricaoLocalArmazenamento;
	}

	public void setDescricaoLocalArmazenamento(
			String descricaoLocalArmazenamento) {
		this.descricaoLocalArmazenamento = descricaoLocalArmazenamento;
	}

}
