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
 * @author Thiago Toscano, Bruno Barros
 * @date 20/04/2006 17/04/2007
 */
public class ResumoLigacaoEconomiaHelper {

	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idSubCategoria;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idHidrometro;
	private Integer idHidrometroPoco;
	private Integer idVolFixadoAgua;
	private Integer idVolFixadoEsgoto;
	private Integer idPoco;
	private Integer idTipoTarifaConsumo;
	private Integer qtdLigacoes = new Integer(0);
	private Integer qtdEconomias = new Integer(0);
	private Integer qtdLigacoesNovasAgua = new Integer(0);
	private Integer qtdLigacoesNovasEsgoto = new Integer(0);
	private Short   codigoRota;
	private Integer qtLigacoesCortesMes = new Integer(0);
	private Integer qtLigacoesReligadasMes = new Integer(0);
	
	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso UC[0275] - Gerar resumo das ligacoes/economias
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria e idSubCatergoria, pois no momento da criacao deste objeto
	 * essas informacoes nao estao disponiveis. 
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idPerfilImovel
	 * @param idEsfera
	 * @param idTipoClienteResponsavel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idHidrometro
	 * @param idHidrometroPoco
	 * @param idVolFixadoAgua
	 * @param idVolFixadoEsgoto
	 * @param idPoco
	 * @param idTipoTarifaConsumo
	 */
	public ResumoLigacaoEconomiaHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade,
			Integer idElo,
			Integer idSetorComercial,
			Integer idRota,
			Integer idQuadra,
			Integer codigoSetorComercial,
			Integer numeroQuadra,
			Integer idPerfilImovel,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idHidrometro,
			Integer idHidrometroPoco,
			Integer idVolFixadoAgua,
			Integer idVolFixadoEsgoto,
			Integer idPoco,
			Integer idTipoTarifaConsumo, 
			Short codigoRota){
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra; 
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.idHidrometro = idHidrometro;
		this.idHidrometroPoco = idHidrometroPoco;
		this.idVolFixadoAgua = idVolFixadoAgua;
		this.idVolFixadoEsgoto = idVolFixadoEsgoto;
		this.idPoco = idPoco;
		this.idTipoTarifaConsumo = idTipoTarifaConsumo;
		this.codigoRota = codigoRota;		
	}

	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
	  return true;
	}	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals( Object obj ){
		
		if ( !( obj instanceof ResumoLigacaoEconomiaHelper ) ){
			return false;			
		} else {
			ResumoLigacaoEconomiaHelper resumoTemp = ( ResumoLigacaoEconomiaHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsfera, resumoTemp.idEsfera ) &&
			  propriedadesIguais( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria ) &&
			  propriedadesIguais( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua ) &&
			  propriedadesIguais( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto ) &&
			  propriedadesIguais( this.idHidrometro, resumoTemp.idHidrometro ) &&
			  propriedadesIguais( this.idHidrometroPoco, resumoTemp.idHidrometroPoco ) &&
			  propriedadesIguais( this.idVolFixadoAgua, resumoTemp.idVolFixadoAgua ) &&
			  propriedadesIguais( this.idVolFixadoEsgoto, resumoTemp.idVolFixadoEsgoto ) &&
			  propriedadesIguais( this.idPoco, resumoTemp.idPoco ) && 
			  propriedadesIguais( this.idTipoTarifaConsumo, resumoTemp.idTipoTarifaConsumo );			  
		}		
	}

	
	
	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Integer getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public Integer getQtdLigacoes() {
		return qtdLigacoes;
	}

	public void setQtdLigacoes(Integer qtdLigacoes) {
		this.qtdLigacoes = qtdLigacoes;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public Integer getIdEsfera() {
		return idEsfera;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public Integer getIdHidrometro() {
		return idHidrometro;
	}

	public Integer getIdHidrometroPoco() {
		return idHidrometroPoco;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public Integer getIdPerfilLigacaoAgua() {
		return idPerfilLigacaoAgua;
	}

	public Integer getIdPerfilLigacaoEsgoto() {
		return idPerfilLigacaoEsgoto;
	}

	public Integer getIdPoco() {
		return idPoco;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public Integer getIdVolFixadoAgua() {
		return idVolFixadoAgua;
	}

	public Integer getIdVolFixadoEsgoto() {
		return idVolFixadoEsgoto;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
	}

	public Integer getIdTipoTarifaConsumo() {
		return idTipoTarifaConsumo;
	}

	public void setIdTipoTarifaConsumo(Integer idTipoTarifaConsumo) {
		this.idTipoTarifaConsumo = idTipoTarifaConsumo;
	}

	public Integer getQtdLigacoesNovasAgua() {
		return qtdLigacoesNovasAgua;
	}

	public void setQtdLigacoesNovasAgua(Integer qtdLigacoesNovasAgua) {
		this.qtdLigacoesNovasAgua = qtdLigacoesNovasAgua;
	}

	public Integer getQtdLigacoesNovasEsgoto() {
		return qtdLigacoesNovasEsgoto;
	}

	public void setQtdLigacoesNovasEsgoto(Integer qtdLigacoesNovasEsgoto) {
		this.qtdLigacoesNovasEsgoto = qtdLigacoesNovasEsgoto;
	}

	public Integer getQtLigacoesCortesMes() {
		return qtLigacoesCortesMes;
	}

	public void setQtLigacoesCortesMes(Integer qtLigacoesCortesMes) {
		this.qtLigacoesCortesMes = qtLigacoesCortesMes;
	}

	public Integer getQtLigacoesReligadasMes() {
		return qtLigacoesReligadasMes;
	}

	public void setQtLigacoesReligadasMes(Integer qtLigacoesReligadasMes) {
		this.qtLigacoesReligadasMes = qtLigacoesReligadasMes;
	}
	
}