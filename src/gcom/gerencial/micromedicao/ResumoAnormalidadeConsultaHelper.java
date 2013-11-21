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
package gcom.gerencial.micromedicao;

/**
 * Classe responsável por ajudar o caso de uso [UC0343] Resumo de Anormalidades 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ResumoAnormalidadeConsultaHelper {

	private String idImovel;
	private String descricaoGerenciaRegional;
	private String descricaoLocalidade;
	private String codigoSetorComercial;
	private String descricaoSetorComercial;
	private String idMedicaoTipo;
	private String numeroQuadra;
	private String descricaoLeituraAnormalidadeFaturada;
	private String descricaoConsumoAnormalidade;
	private String idLocalidade;
	private String idElo;
	private String descricaoElo;
	private String idGerenciaRegional;

	private String quantidadeMedicao;
	
	public ResumoAnormalidadeConsultaHelper(){
		
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getDescricaoLeituraAnormalidadeFaturada() {
		return descricaoLeituraAnormalidadeFaturada;
	}

	public void setDescricaoLeituraAnormalidadeFaturada(
			String descricaoLeituraAnormalidadeFaturada) {
		this.descricaoLeituraAnormalidadeFaturada = descricaoLeituraAnormalidadeFaturada;
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

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getQuantidadeMedicao() {
		return quantidadeMedicao;
	}

	public void setQuantidadeMedicao(String quantidadeMedicao) {
		this.quantidadeMedicao = quantidadeMedicao;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdMedicaoTipo() {
		return idMedicaoTipo;
	}

	public void setIdMedicaoTipo(String idMedicaoTipo) {
		this.idMedicaoTipo = idMedicaoTipo;
	}

	public String getDescricaoConsumoAnormalidade() {
		return descricaoConsumoAnormalidade;
	}

	public void setDescricaoConsumoAnormalidade(String descricaoConsumoAnormalidade) {
		this.descricaoConsumoAnormalidade = descricaoConsumoAnormalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoElo() {
		return descricaoElo;
	}

	public void setDescricaoElo(String descricaoElo) {
		this.descricaoElo = descricaoElo;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
}