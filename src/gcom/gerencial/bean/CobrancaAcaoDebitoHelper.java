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
package gcom.gerencial.bean;

import gcom.util.Util;

import java.math.BigDecimal;

public class CobrancaAcaoDebitoHelper {

	private Integer id;

	private String descricao;

	private Integer quantidadeDocumento;

	private BigDecimal valorDocumento;

	private Short indicadorAntesApos;

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo somatorioQuantidade.
	 */

	public CobrancaAcaoDebitoHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo somatorioQuantidade.
	 */

	public CobrancaAcaoDebitoHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento,
			Short indicadorAntesApos) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public String getPercentualValor(String valorTotal) {
		if (valorTotal != null && 
				new BigDecimal(valorTotal).compareTo(new BigDecimal("0.0")) != 0){
			String valorPercentual = Util.calcularPercentual(getValorDocumento()
					.toString(), valorTotal);
			valorPercentual = Util
					.formatarMoedaReal(new BigDecimal(valorPercentual));
			return valorPercentual;			
		}
		return Util.formatarMoedaReal(new BigDecimal("0.0"));
	}

	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(
				getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(
				quantidadePercentual));
		return quantidadePercentual;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento
	 *            O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @param valorDocumento
	 *            O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @return Retorna o campo indicadorAntesApos.
	 */
	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}

	/**
	 * @param indicadorAntesApos
	 *            O indicadorAntesApos a ser setado.
	 */
	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public String getDescricaoIndicador() {

		// Alterado por Sávio Luiz data:28/03/2007
		/*
		 * if(getIndicadorAntesApos() != null &&
		 * !getIndicadorAntesApos().equals("")){ if(getIndicadorAntesApos() ==
		 * ResumoCobrancaAcao.INDICADOR_ANTES){ descricao = getDescricao() + "
		 * ANTES"; }else if(getIndicadorAntesApos() ==
		 * ResumoCobrancaAcao.INDICADOR_APOS){ descricao = getDescricao() + "
		 * APOS"; } }else{ descricao = getDescricao(); }
		 */
		return descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
