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
package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoAnormalidadeLeituraBean implements RelatorioBean {

	private String estado;

	private String idGerenciaRegional;

	private String descricaoGerenciaRegional;

	private String idEloPolo;

	private String descricaoEloPolo;

	private String idLocalidade;

	private String descricaoLocalidade;

	private String idSetorComercial;

	private String descricaoSetorComercial;

	private String numeroQuadra;

	private String tipoLigacao;

	private String anormalidade;

	private String ligacoes;

	private String percentual;

	public RelatorioResumoAnormalidadeLeituraBean(String estado,
			String idGerenciaRegional, String descricaoGerenciaRegional,
			String idEloPolo, String descricaoEloPolo, String idLocalidade,
			String descricaoLocalidade, String idSetorComercial,
			String descricaoSetorComercial, String numeroQuadra,
			String tipoLigacao, String anormalidade, String ligacoes,
			String percentual) {

		this.estado = estado;
		this.idGerenciaRegional = idGerenciaRegional;
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
		this.idEloPolo = idEloPolo;
		this.descricaoEloPolo = descricaoEloPolo;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.descricaoSetorComercial = descricaoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.tipoLigacao = tipoLigacao;
		this.anormalidade = anormalidade;
		this.ligacoes = ligacoes;
		this.percentual = percentual;
		
	}

	public String getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdEloPolo() {
		return idEloPolo;
	}

	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

}
