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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Esta classe tem a finalidade para facilitar a visualização dos dados na tela 
 * [UC0472] Consultar Imovel
 * 
 * @author Rafael Santos
 * @date 25/09/2006
 */
public class ImovelClientesEspeciaisRelatorioHelper {
	
	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;
	
	private Integer idLocalidade;
	
	private String nomeLocalidade;
	
	private Integer idCategoria;
	
	private String descricaoCategoria;
	
	private Integer idSubcategoria;
	
	private String descricaoSubcategoria;
	
	private String inscricaoImovel;
	
	private Integer idImovel;
	
	private Integer idClienteUsuario;
	
	private String nomeClienteUsuario;
	
	private Short qtdeEconomias;
	
	private String descricaoSituacaoLigacaoAgua;
	
	private String descricaoSituacaoLigacaoEsgoto;
	
	private Integer qtdeDebitosVencidos;
	
	private BigDecimal valorDebitosVencidos;
	
	private Integer qtdeFaturasEmAtraso;
	
	private BigDecimal valorFaturasEmAtraso;
	
	private String descricaoCapacidadeHidrometro;
	
	private Date dataInstalacaoHidrometro;
	
	private Integer idClienteResponsavel;
	
	private String nomeClienteResponsavel;
	
	private Integer consumoAgua;
	
	private Integer consumoEsgoto;
	
	private Integer consumoMinimoEsgoto;
	
	private String descricaoTarifaConsumo;
	
	private BigDecimal valorAgua;
	
	private BigDecimal valorEsgoto;
	
	private BigDecimal valorConta;
	
	private Integer codigoSetor;
	
	private String descricaoSetor;
	
	private Integer codigoRota;
	
	private Short indicadorCobrarMulta;
	
	
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

	/**
	 * @return Retorna o campo valorConta.
	 */
	public BigDecimal getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta O valorConta a ser setado.
	 */
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
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
	 * @return Retorna o campo consumoMinimoEsgoto.
	 */
	public Integer getConsumoMinimoEsgoto() {
		return consumoMinimoEsgoto;
	}

	/**
	 * @param consumoMinimoEsgoto O consumoMinimoEsgoto a ser setado.
	 */
	public void setConsumoMinimoEsgoto(Integer consumoMinimoEsgoto) {
		this.consumoMinimoEsgoto = consumoMinimoEsgoto;
	}

	/**
	 * @return Retorna o campo dataInstalacaoHidrometro.
	 */
	public Date getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	/**
	 * @param dataInstalacaoHidrometro O dataInstalacaoHidrometro a ser setado.
	 */
	public void setDataInstalacaoHidrometro(Date dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	/**
	 * @return Retorna o campo decricaoSituacaoLigacaoAgua.
	 */
	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}

	/**
	 * @param decricaoSituacaoLigacaoAgua O decricaoSituacaoLigacaoAgua a ser setado.
	 */
	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo decricaoSituacaoLigacaoEsgoto.
	 */
	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}

	/**
	 * @param decricaoSituacaoLigacaoEsgoto O decricaoSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo descricaoCapacidadeHidrometro.
	 */
	public String getDescricaoCapacidadeHidrometro() {
		return descricaoCapacidadeHidrometro;
	}

	/**
	 * @param descricaoCapacidadeHidrometro O descricaoCapacidadeHidrometro a ser setado.
	 */
	public void setDescricaoCapacidadeHidrometro(
			String descricaoCapacidadeHidrometro) {
		this.descricaoCapacidadeHidrometro = descricaoCapacidadeHidrometro;
	}

	/**
	 * @return Retorna o campo descricaoCategoria.
	 */
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	/**
	 * @param descricaoCategoria O descricaoCategoria a ser setado.
	 */
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	/**
	 * @return Retorna o campo descricaoSubcategoria.
	 */
	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	/**
	 * @param descricaoSubcategoria O descricaoSubcategoria a ser setado.
	 */
	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}

	/**
	 * @return Retorna o campo descricaoTarifaConsumo.
	 */
	public String getDescricaoTarifaConsumo() {
		return descricaoTarifaConsumo;
	}

	/**
	 * @param descricaoTarifaConsumo O descricaoTarifaConsumo a ser setado.
	 */
	public void setDescricaoTarifaConsumo(String descricaoTarifaConsumo) {
		this.descricaoTarifaConsumo = descricaoTarifaConsumo;
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
	 * @return Retorna o campo idClienteResponsavel.
	 */
	public Integer getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	/**
	 * @param idClienteResponsavel O idClienteResponsavel a ser setado.
	 */
	public void setIdClienteResponsavel(Integer idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	/**
	 * @return Retorna o campo idClienteUsuario.
	 */
	public Integer getIdClienteUsuario() {
		return idClienteUsuario;
	}

	/**
	 * @param idClienteUsuario O idClienteUsuario a ser setado.
	 */
	public void setIdClienteUsuario(Integer idClienteUsuario) {
		this.idClienteUsuario = idClienteUsuario;
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
	 * @return Retorna o campo idSubcategoria.
	 */
	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}

	/**
	 * @param idSubcategoria O idSubcategoria a ser setado.
	 */
	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
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
	 * @return Retorna o campo qtdeDebitosVencidos.
	 */
	public Integer getQtdeDebitosVencidos() {
		return qtdeDebitosVencidos;
	}

	/**
	 * @param qtdeDebitosVencidos O qtdeDebitosVencidos a ser setado.
	 */
	public void setQtdeDebitosVencidos(Integer qtdeDebitosVencidos) {
		this.qtdeDebitosVencidos = qtdeDebitosVencidos;
	}

	/**
	 * @return Retorna o campo qtdeEconomias.
	 */
	public Short getQtdeEconomias() {
		return qtdeEconomias;
	}

	/**
	 * @param qtdeEconomias O qtdeEconomias a ser setado.
	 */
	public void setQtdeEconomias(Short qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}

	/**
	 * @return Retorna o campo qtdeFaturasEmAtraso.
	 */
	public Integer getQtdeFaturasEmAtraso() {
		return qtdeFaturasEmAtraso;
	}

	/**
	 * @param qtdeFaturasEmAtraso O qtdeFaturasEmAtraso a ser setado.
	 */
	public void setQtdeFaturasEmAtraso(Integer qtdeFaturasEmAtraso) {
		this.qtdeFaturasEmAtraso = qtdeFaturasEmAtraso;
	}

	/**
	 * @return Retorna o campo valorDebitosVencidos.
	 */
	public BigDecimal getValorDebitosVencidos() {
		return valorDebitosVencidos;
	}

	/**
	 * @param valorDebitosVencidos O valorDebitosVencidos a ser setado.
	 */
	public void setValorDebitosVencidos(BigDecimal valorDebitosVencidos) {
		this.valorDebitosVencidos = valorDebitosVencidos;
	}

	/**
	 * @return Retorna o campo valorFaturasEmAtraso.
	 */
	public BigDecimal getValorFaturasEmAtraso() {
		return valorFaturasEmAtraso;
	}

	/**
	 * @param valorFaturasEmAtraso O valorFaturasEmAtraso a ser setado.
	 */
	public void setValorFaturasEmAtraso(BigDecimal valorFaturasEmAtraso) {
		this.valorFaturasEmAtraso = valorFaturasEmAtraso;
	}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescricaoSetor() {
		return descricaoSetor;
	}

	public void setDescricaoSetor(String descricaoSetor) {
		this.descricaoSetor = descricaoSetor;
	}

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Short getIndicadorCobrarMulta() {
		return indicadorCobrarMulta;
	}

	public void setIndicadorCobrarMulta(Short indicadorCobrarMulta) {
		this.indicadorCobrarMulta = indicadorCobrarMulta;
	}	
}
