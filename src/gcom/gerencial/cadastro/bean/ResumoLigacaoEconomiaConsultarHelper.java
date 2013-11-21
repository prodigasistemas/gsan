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
package gcom.gerencial.cadastro.bean;

/**
 * Classe responsável por ajudar o caso de uso [UC0275] Gerar Resumo das Ligacoes/Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ResumoLigacaoEconomiaConsultarHelper {

	private String anoMesReferncia;
	private String descricaoEstado;
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private String descricaoGerenciaRegional;
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer idElo;
	private String descricaoElo;
	private Integer idSetorComercial;
	private String descricaoSetorComercial;
	private Integer idRota;
	private String descricaoRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private String numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private String descricaoSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private String descricaoSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private String descricaoCategoria;
	private Integer idEsfera;
	private Integer quantidadeEconomiaComHidrometro = new Integer(0);
	private Integer quantidadeLigacoesComHidrometro = new Integer(0);
	private Integer quantidadeEconomiaSemHidrometro = new Integer(0);
	private Integer quantidadeLigacoesSemHidrometro = new Integer(0);
	private Integer totalLigacoes = new Integer(0);
	private Integer totalEconomia = new Integer(0);

	public ResumoLigacaoEconomiaConsultarHelper(){}

	public String getAnoMesReferncia() {
		return anoMesReferncia;
	}

	public void setAnoMesReferncia(String anoMesReferncia) {
		this.anoMesReferncia = anoMesReferncia;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoElo() {
		return descricaoElo;
	}

	public void setDescricaoElo(String descricaoElo) {
		this.descricaoElo = descricaoElo;
	}

	public String getDescricaoEstado() {
		return descricaoEstado;
	}

	public void setDescricaoEstado(String descricaoEstado) {
		this.descricaoEstado = descricaoEstado;
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

	public String getDescricaoRota() {
		return descricaoRota;
	}

	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}

	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}

	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}

	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	public Integer getIdEsfera() {
		return idEsfera;
	}

	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getQuantidadeEconomiaComHidrometro() {
		return quantidadeEconomiaComHidrometro;
	}

	public void setQuantidadeEconomiaComHidrometro(
			Integer quantidadeEconomiaComHidrometro) {
		this.quantidadeEconomiaComHidrometro = quantidadeEconomiaComHidrometro;
	}

	public Integer getQuantidadeEconomiaSemHidrometro() {
		return quantidadeEconomiaSemHidrometro;
	}

	public void setQuantidadeEconomiaSemHidrometro(
			Integer quantidadeEconomiaSemHidrometro) {
		this.quantidadeEconomiaSemHidrometro = quantidadeEconomiaSemHidrometro;
	}

	public Integer getQuantidadeLigacoesComHidrometro() {
		return quantidadeLigacoesComHidrometro;
	}

	public void setQuantidadeLigacoesComHidrometro(
			Integer quantidadeLigacoesComHidrometro) {
		this.quantidadeLigacoesComHidrometro = quantidadeLigacoesComHidrometro;
	}

	public Integer getQuantidadeLigacoesSemHidrometro() {
		return quantidadeLigacoesSemHidrometro;
	}

	public void setQuantidadeLigacoesSemHidrometro(
			Integer quantidadeLigacoesSemHidrometro) {
		this.quantidadeLigacoesSemHidrometro = quantidadeLigacoesSemHidrometro;
	}

	public Integer getTotalEconomia() {
		return totalEconomia;
	}

	public void setTotalEconomia(Integer totalEconomia) {
		this.totalEconomia = totalEconomia;
	}

	public Integer getTotalLigacoes() {
		return totalLigacoes;
	}

	public void setTotalLigacoes(Integer totalLigacoes) {
		this.totalLigacoes = totalLigacoes;
	}
}