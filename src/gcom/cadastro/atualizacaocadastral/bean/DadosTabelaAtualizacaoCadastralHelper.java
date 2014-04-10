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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ivan Sergio
 * @date 02/06/2009
 */

public class DadosTabelaAtualizacaoCadastralHelper implements Serializable{
	private static final long serialVersionUID = 7924233122387459185L;

	private Integer idTabelaAtualizacaoCadastral;
	
	private Integer idTabela;
	private String descricaoTabela;
	
	private Integer idTabelaColuna;
	private String descricaoColuna;
	
	private Integer idTabelaColunaAtualizacaoCadastral;
	private String colunaValorAnterior;
	private String colunaValorAtual;
	private Short indicadorAutorizado;
	private Date ultimaAtualizacao;
	
	private Integer idAlteracaoTipo;
	private String descricaoAlteracaoTipo;
	private Date dataValidacao;
	private String imovelSubcategoria;
	
	private String nomeUsuario;
	
	/**
	 * @return Retorna o campo colunaValorAnterior.
	 */
	public String getColunaValorAnterior() {
		return colunaValorAnterior;
	}
	/**
	 * @param colunaValorAnterior O colunaValorAnterior a ser setado.
	 */
	public void setColunaValorAnterior(String colunaValorAnterior) {
		this.colunaValorAnterior = colunaValorAnterior;
	}
	/**
	 * @return Retorna o campo colunaValorAtual.
	 */
	public String getColunaValorAtual() {
		return colunaValorAtual;
	}
	/**
	 * @param colunaValorAtual O colunaValorAtual a ser setado.
	 */
	public void setColunaValorAtual(String colunaValorAtual) {
		this.colunaValorAtual = colunaValorAtual;
	}
	/**
	 * @return Retorna o campo descricaoAlteracaoTipo.
	 */
	public String getDescricaoAlteracaoTipo() {
		return descricaoAlteracaoTipo;
	}
	/**
	 * @param descricaoAlteracaoTipo O descricaoAlteracaoTipo a ser setado.
	 */
	public void setDescricaoAlteracaoTipo(String descricaoAlteracaoTipo) {
		this.descricaoAlteracaoTipo = descricaoAlteracaoTipo;
	}
	/**
	 * @return Retorna o campo descricaoColuna.
	 */
	public String getDescricaoColuna() {
		return descricaoColuna;
	}
	/**
	 * @param descricaoColuna O descricaoColuna a ser setado.
	 */
	public void setDescricaoColuna(String descricaoColuna) {
		this.descricaoColuna = descricaoColuna;
	}
	/**
	 * @return Retorna o campo descricaoTabela.
	 */
	public String getDescricaoTabela() {
		return descricaoTabela;
	}
	/**
	 * @param descricaoTabela O descricaoTabela a ser setado.
	 */
	public void setDescricaoTabela(String descricaoTabela) {
		this.descricaoTabela = descricaoTabela;
	}
	/**
	 * @return Retorna o campo idAlteracaoTipo.
	 */
	public Integer getIdAlteracaoTipo() {
		return idAlteracaoTipo;
	}
	/**
	 * @param idAlteracaoTipo O idAlteracaoTipo a ser setado.
	 */
	public void setIdAlteracaoTipo(Integer idAlteracaoTipo) {
		this.idAlteracaoTipo = idAlteracaoTipo;
	}
	/**
	 * @return Retorna o campo idTabela.
	 */
	public Integer getIdTabela() {
		return idTabela;
	}
	/**
	 * @param idTabela O idTabela a ser setado.
	 */
	public void setIdTabela(Integer idTabela) {
		this.idTabela = idTabela;
	}
	/**
	 * @return Retorna o campo idTabelaAtualizacaoCadastral.
	 */
	public Integer getIdTabelaAtualizacaoCadastral() {
		return idTabelaAtualizacaoCadastral;
	}
	/**
	 * @param idTabelaAtualizacaoCadastral O idTabelaAtualizacaoCadastral a ser setado.
	 */
	public void setIdTabelaAtualizacaoCadastral(Integer idTabelaAtualizacaoCadastral) {
		this.idTabelaAtualizacaoCadastral = idTabelaAtualizacaoCadastral;
	}
	/**
	 * @return Retorna o campo idTabelaColuna.
	 */
	public Integer getIdTabelaColuna() {
		return idTabelaColuna;
	}
	/**
	 * @param idTabelaColuna O idTabelaColuna a ser setado.
	 */
	public void setIdTabelaColuna(Integer idTabelaColuna) {
		this.idTabelaColuna = idTabelaColuna;
	}
	/**
	 * @return Retorna o campo idTabelaColunaAtualizacaoCadastral.
	 */
	public Integer getIdTabelaColunaAtualizacaoCadastral() {
		return idTabelaColunaAtualizacaoCadastral;
	}
	/**
	 * @param idTabelaColunaAtualizacaoCadastral O idTabelaColunaAtualizacaoCadastral a ser setado.
	 */
	public void setIdTabelaColunaAtualizacaoCadastral(
			Integer idTabelaColunaAtualizacaoCadastral) {
		this.idTabelaColunaAtualizacaoCadastral = idTabelaColunaAtualizacaoCadastral;
	}
	/**
	 * @return Retorna o campo indicadorAutorizado.
	 */
	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}
	/**
	 * @param indicadorAutorizado O indicadorAutorizado a ser setado.
	 */
	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}
	/**
	 * @return Retorna o campo ultimaAtualizacao.
	 */
	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}
	/**
	 * @param ultimaAtualizacao O ultimaAtualizacao a ser setado.
	 */
	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	public Date getDataValidacao() {
		return dataValidacao;
	}
	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}
	public String getImovelSubcategoria() {
		return imovelSubcategoria;
	}
	public void setImovelSubcategoria(String imovelSubcategoria) {
		this.imovelSubcategoria = imovelSubcategoria;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String toString() {
		return "DadosTabelaAtualizacaoCadastralHelper [descricaoTabela=" + descricaoTabela + ", descricaoColuna=" + descricaoColuna + ", colunaValorAnterior="
				+ colunaValorAnterior + ", colunaValorAtual=" + colunaValorAtual + ", dataValidacao=" + dataValidacao + ", nomeUsuario=" + nomeUsuario + "]";
	}
}