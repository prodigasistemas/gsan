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
package gcom.gui.gerencial.faturamento;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

/**
 * Classe responsável por ajudar o caso de uso [UC0339] Resumo de Anormalidades 
 *
 * @author Fernanda Paiva
 * @date 30/05/2006
 */
public class ResumoAnaliseFaturamentoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idEsfera;
	private Integer idLeituraAnormalidadeFaturada;
	private Integer idLigacaoAgua;
	private Integer idMedicaoTipo;
	private Integer idFaturamentoGrupo;
	private Integer quantidadeContas;
	private Integer quantidadeEconomia;
	private Integer consumoAgua;
	private Integer consumoEsgoto;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorDebitos;
	private BigDecimal valorCreditos;
	private BigDecimal valorTotal;
	private BigDecimal valorImpostos;

	private int quantidadeImovel = 1;
	
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idEsfera.
	 */
	public Integer getIdEsfera() {
		return idEsfera;
	}

	/**
	 * @param idEsfera O idEsfera a ser setado.
	 */
	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idPerfilImovel.
	 */
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	/**
	 * @param idPerfilImovel O idPerfilImovel a ser setado.
	 */
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	/**
	 * @return Retorna o campo idQuadra.
	 */
	public Integer getIdQuadra() {
		return idQuadra;
	}

	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	/**
	 * @return Retorna o campo idRota.
	 */
	public Integer getIdRota() {
		return idRota;
	}

	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoAgua.
	 */
	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	/**
	 * @param idSituacaoLigacaoAgua O idSituacaoLigacaoAgua a ser setado.
	 */
	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoEsgoto.
	 */
	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	/**
	 * @param idSituacaoLigacaoEsgoto O idSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public int getQuantidadeImovel() {
		return quantidadeImovel;
	}

	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(int quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}


	public Integer getIdLeituraAnormalidadeFaturada() {
		return idLeituraAnormalidadeFaturada;
	}


	public void setIdLeituraAnormalidadeFaturada(
			Integer idLeituraAnormalidadeFaturada) {
		this.idLeituraAnormalidadeFaturada = idLeituraAnormalidadeFaturada;
	}


	public Integer getIdLigacaoAgua() {
		return idLigacaoAgua;
	}


	public void setIdLigacaoAgua(Integer idLigacaoAgua) {
		this.idLigacaoAgua = idLigacaoAgua;
	}


	public Integer getIdMedicaoTipo() {
		return idMedicaoTipo;
	}


	public void setIdMedicaoTipo(Integer idMedicaoTipo) {
		this.idMedicaoTipo = idMedicaoTipo;
	}


	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}


	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	/**
	 * @return Retorna o campo consumoAgua.
	 */
	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	/**
	 * @param consumoAgua O consumoAgua a ser setado.
	 */
	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	/**
	 * @return Retorna o campo consumoEsgoto.
	 */
	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	/**
	 * @param consumoEsgoto O consumoEsgoto a ser setado.
	 */
	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	/**
	 * @return Retorna o campo quantidadeContas.
	 */
	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	/**
	 * @param quantidadeContas O quantidadeContas a ser setado.
	 */
	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	/**
	 * @return Retorna o campo valorAgua.
	 */
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	/**
	 * @param valorAgua O valorAgua a ser setado.
	 */
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	
	public void addValorAgua(BigDecimal valorAgua){
		this.valorAgua = this.valorAgua.add(valorAgua);
	}

	/**
	 * @return Retorna o campo valorCreditos.
	 */
	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	/**
	 * @param valorCreditos O valorCreditos a ser setado.
	 */
	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public void addValorCreditos(BigDecimal valorCreditos){
		this.valorCreditos = this.valorCreditos.add(valorCreditos);
	}
	
	/**
	 * @return Retorna o campo valorDebitos.
	 */
	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	/**
	 * @param valorDebitos O valorDebitos a ser setado.
	 */
	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public void addValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = this.valorDebitos.add(valorDebitos);
	}
	
	/**
	 * @return Retorna o campo valorEsgoto.
	 */
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	/**
	 * @param valorEsgoto O valorEsgoto a ser setado.
	 */
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public void addValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = this.valorEsgoto.add(valorEsgoto);
	}
	
	/**
	 * @return Retorna o campo valorTotal.
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
}