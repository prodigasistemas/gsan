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
package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Flávio Cordeiro
 */
public class RelatorioAnaliticoFaturamentoActionForm extends ValidatorActionForm {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String localidadeFiltro;
	private String nomeLocalidade;
	private String setorComercialFiltro;
	private String setorComercialNome;
	private String setorComercialID;
	private String quadraNome;
	private String quadraFiltro;
	private String quadraMensagem;
	private String quadraID;
	private String indicadorLocalidadeInformatizada;
	private String idGrupoFaturamento;
	//-------Usado bara as buscas um nivel abaixo ex: idLocalidade da busca de setor
	private String idLocalidadeSetor;
	private String idSetorQuadra;

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getIndicadorLocalidadeInformatizada() {
		return indicadorLocalidadeInformatizada;
	}

	public void setIndicadorLocalidadeInformatizada(
			String indicadorLocalidadeInformatizada) {
		this.indicadorLocalidadeInformatizada = indicadorLocalidadeInformatizada;
	}

	public String getSetorComercialID() {
		return setorComercialID;
	}

	public void setSetorComercialID(String comercialID) {
		setorComercialID = comercialID;
	}

	public String getSetorComercialFiltro() {
		return setorComercialFiltro;
	}

	public void setSetorComercialFiltro(String setorComercialFiltro) {
		this.setorComercialFiltro = setorComercialFiltro;
	}

	public String getSetorComercialNome() {
		return setorComercialNome;
	}

	public void setSetorComercialNome(String setorComercialNome) {
		this.setorComercialNome = setorComercialNome;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}

	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getQuadraFiltro() {
		return quadraFiltro;
	}

	public void setQuadraFiltro(String quadraFiltro) {
		this.quadraFiltro = quadraFiltro;
	}

	public String getQuadraMensagem() {
		return quadraMensagem;
	}

	public void setQuadraMensagem(String quadraMensagem) {
		this.quadraMensagem = quadraMensagem;
	}

	public String getQuadraNome() {
		return quadraNome;
	}

	public void setQuadraNome(String quadraNome) {
		this.quadraNome = quadraNome;
	}

	public String getQuadraID() {
		return quadraID;
	}

	public void setQuadraID(String quadraID) {
		this.quadraID = quadraID;
	}

	public String getIdLocalidadeSetor() {
		return idLocalidadeSetor;
	}

	public void setIdLocalidadeSetor(String idLocalidadeSetor) {
		this.idLocalidadeSetor = idLocalidadeSetor;
	}

	public String getIdSetorQuadra() {
		return idSetorQuadra;
	}

	public void setIdSetorQuadra(String idSetorQuadra) {
		this.idSetorQuadra = idSetorQuadra;
	}

}
