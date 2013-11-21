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
package gcom.relatorio.micromedicao;

import java.math.BigDecimal;


public class AvisoAnormalidadeRelatorioHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	private String endereco;
	private String inscricao;
	private Integer idImovel;
	private Short codigoRota;
	private Integer sequencialRota;
	private Integer anoMes;
	private String descricaoAnormalidadeConsumo;
	private String nomeMunicipio;
	private Integer consumoFaturado;
	private Integer consumoMedio;
	private Integer consumoMedido;
	private BigDecimal variacaoConsumo;
	
	/**
	 * @return Retorna o campo consumoMedido.
	 */
	public Integer getConsumoMedido() {
		return consumoMedido;
	}
	/**
	 * @param consumoMedido O consumoMedido a ser setado.
	 */
	public void setConsumoMedido(Integer consumoMedido) {
		this.consumoMedido = consumoMedido;
	}
	/**
	 * @return Retorna o campo consumoMedio.
	 */
	public Integer getConsumoMedio() {
		return consumoMedio;
	}
	/**
	 * @param consumoMedio O consumoMedio a ser setado.
	 */
	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}
	/**
	 * @return Retorna o campo consumoFaturado.
	 */
	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}
	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	/**
	 * @return Retorna o campo variacaoConsumo.
	 */
	public BigDecimal getVariacaoConsumo() {
		return variacaoConsumo;
	}
	/**
	 * @param variacaoConsumo O variacaoConsumo a ser setado.
	 */
	public void setVariacaoConsumo(BigDecimal variacaoConsumo) {
		this.variacaoConsumo = variacaoConsumo;
	}
	/**
	 * @return Retorna o campo anoMes.
	 */
	public Integer getAnoMes() {
		return anoMes;
	}
	/**
	 * @param anoMes O anoMes a ser setado.
	 */
	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
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
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	/**
	 * @return Retorna o campo descricaoAnormalidadeConsumo.
	 */
	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}
	/**
	 * @param descricaoAnormalidadeConsumo O descricaoAnormalidadeConsumo a ser setado.
	 */
	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}
	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	/**
	 * @param nomeMunicipio O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}
	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

}
