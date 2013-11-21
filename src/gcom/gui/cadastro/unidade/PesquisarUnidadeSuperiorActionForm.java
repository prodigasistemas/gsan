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
package gcom.gui.cadastro.unidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class PesquisarUnidadeSuperiorActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String unidadeTipoFilho;
    private String nivelHierarquicoFilho;
    private String idLocalidadeFilho;
    private String descricaoLocalidadeFilho;
    private String gerenciaRegionalFilho;
    private String descricaoFilho;
    private String siglaFilho;
    private String idEmpresaFilho;

    private String idUnidadeCentralizadoraFilho;
    
    private String unidadeEsgotoFilho;
    private String unidadeAbreRegistroFilho;
    private String unidadeAceitaFilho;
    private String meioSolicitacaoFilho;

	public String getDescricaoFilho() {
		return descricaoFilho;
	}

	public void setDescricaoFilho(String descricaoFilho) {
		this.descricaoFilho = descricaoFilho;
	}

	public String getDescricaoLocalidadeFilho() {
		return descricaoLocalidadeFilho;
	}

	public void setDescricaoLocalidadeFilho(String descricaoLocalidadeFilho) {
		this.descricaoLocalidadeFilho = descricaoLocalidadeFilho;
	}

	public String getGerenciaRegionalFilho() {
		return gerenciaRegionalFilho;
	}

	public void setGerenciaRegionalFilho(String gerenciaRegionalFilho) {
		this.gerenciaRegionalFilho = gerenciaRegionalFilho;
	}

	public String getIdEmpresaFilho() {
		return idEmpresaFilho;
	}

	public void setIdEmpresaFilho(String idEmpresaFilho) {
		this.idEmpresaFilho = idEmpresaFilho;
	}

	public String getIdLocalidadeFilho() {
		return idLocalidadeFilho;
	}

	public void setIdLocalidadeFilho(String idLocalidadeFilho) {
		this.idLocalidadeFilho = idLocalidadeFilho;
	}

	public String getIdUnidadeCentralizadoraFilho() {
		return idUnidadeCentralizadoraFilho;
	}

	public void setIdUnidadeCentralizadoraFilho(String idUnidadeCentralizadoraFilho) {
		this.idUnidadeCentralizadoraFilho = idUnidadeCentralizadoraFilho;
	}

	public String getMeioSolicitacaoFilho() {
		return meioSolicitacaoFilho;
	}

	public void setMeioSolicitacaoFilho(String meioSolicitacaoFilho) {
		this.meioSolicitacaoFilho = meioSolicitacaoFilho;
	}

	public String getNivelHierarquicoFilho() {
		return nivelHierarquicoFilho;
	}

	public void setNivelHierarquicoFilho(String nivelHierarquicoFilho) {
		this.nivelHierarquicoFilho = nivelHierarquicoFilho;
	}

	public String getSiglaFilho() {
		return siglaFilho;
	}

	public void setSiglaFilho(String siglaFilho) {
		this.siglaFilho = siglaFilho;
	}

	public String getUnidadeAbreRegistroFilho() {
		return unidadeAbreRegistroFilho;
	}

	public void setUnidadeAbreRegistroFilho(String unidadeAbreRegistroFilho) {
		this.unidadeAbreRegistroFilho = unidadeAbreRegistroFilho;
	}

	public String getUnidadeAceitaFilho() {
		return unidadeAceitaFilho;
	}

	public void setUnidadeAceitaFilho(String unidadeAceitaFilho) {
		this.unidadeAceitaFilho = unidadeAceitaFilho;
	}

	public String getUnidadeEsgotoFilho() {
		return unidadeEsgotoFilho;
	}

	public void setUnidadeEsgotoFilho(String unidadeEsgotoFilho) {
		this.unidadeEsgotoFilho = unidadeEsgotoFilho;
	}

	public String getUnidadeTipoFilho() {
		return unidadeTipoFilho;
	}

	public void setUnidadeTipoFilho(String unidadeTipoFilho) {
		this.unidadeTipoFilho = unidadeTipoFilho;
	}

}
