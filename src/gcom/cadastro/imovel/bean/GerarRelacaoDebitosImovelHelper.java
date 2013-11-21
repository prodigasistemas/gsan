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
package gcom.cadastro.imovel.bean;

/**
 * Débitos de um imóvel
 * @author Rafael Santos
 * @since 01/06/2006
 */
public class GerarRelacaoDebitosImovelHelper {
	
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String gerenciaRegional;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String nomeLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String lote;
	private String subLote;
	private String idImovel;
	private String inscricaoImovel;
	private String nomeClienteUsuario;
	private String nomeClienteResponsavel;
	private String endereco;
	private String quantidadeEconomias;
	private String categoriaPrincipal;
	private String subcategoriaPrincipal;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String percentualEsgoto;
	private String dataCorte;
	private String consumoMediaImovel;
	private String rota;
	private String sequencialRota;
	
	/**
	 * @return Retorna o campo categoriaPrincipal.
	 */
	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}
	/**
	 * @param categoriaPrincipal O categoriaPrincipal a ser setado.
	 */
	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}
	/**
	 * @return Retorna o campo consumoMediaImovel.
	 */
	public String getConsumoMediaImovel() {
		return consumoMediaImovel;
	}
	/**
	 * @param consumoMediaImovel O consumoMediaImovel a ser setado.
	 */
	public void setConsumoMediaImovel(String consumoMediaImovel) {
		this.consumoMediaImovel = consumoMediaImovel;
	}
	/**
	 * @return Retorna o campo dataCorte.
	 */
	public String getDataCorte() {
		return dataCorte;
	}
	/**
	 * @param dataCorte O dataCorte a ser setado.
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}
	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setIdImovel(String matriculaImovel) {
		this.idImovel = matriculaImovel;
	}
	/**
	 * @return Retorna o campo nomeClienteResponsavel.
	 */
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	/**
	 * @param nomeClienteResponsavel O nomeClienteResponsavel a ser setado.
	 */
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	/**
	 * @return Retorna o campo nomeClienteUsuario.
	 */
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	/**
	 * @param nomeClienteUsuario O nomeClienteUsuario a ser setado.
	 */
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	/**
	 * @return Retorna o campo situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	/**
	 * @param situacaoAgua O situacaoAgua a ser setado.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	/**
	 * @return Retorna o campo situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	/**
	 * @param situacaoEsgoto O situacaoEsgoto a ser setado.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	/**
	 * @return Retorna o campo lote.
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}
	/**
	 * @return Retorna o campo subLote.
	 */
	public String getSubLote() {
		return subLote;
	}
	/**
	 * @param subLote O subLote a ser setado.
	 */
	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	/**
	 * @return Retorna o campo subcategoriaPrincipal.
	 */
	public String getSubcategoriaPrincipal() {
		return subcategoriaPrincipal;
	}
	/**
	 * @param subcategoriaPrincipal O subcategoriaPrincipal a ser setado.
	 */
	public void setSubcategoriaPrincipal(String subcategoriaPrincipal) {
		this.subcategoriaPrincipal = subcategoriaPrincipal;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
	
	
}
