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
package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;

	private Integer idUnidadeNegocio;
	
	private String unidadeNegocioDescricaoAbreviada;
	
	private String unidadeNegocioDescricao;

	private Integer totalUnidadeNegocio;
	
	private BigDecimal totalPercentualUnidadeNegocio;
	private BigDecimal totalFatEstimadoUnidadeNegocio;
	private Integer totalQtLigacoesUnidadeNegocio;

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> getResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper = resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper(Integer idUnidadeNegocio, String unidadeNegocioDescricaoAbreviada, String unidadeNegocioDescricao, Integer totalUnidadeNegocio) {
		super();
		// TODO Auto-generated constructor stub
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalFatEstimadoUnidadeNegocio.
	 */
	public BigDecimal getTotalFatEstimadoUnidadeNegocio() {
		return totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @param totalFatEstimadoUnidadeNegocio O totalFatEstimadoUnidadeNegocio a ser setado.
	 */
	public void setTotalFatEstimadoUnidadeNegocio(
			BigDecimal totalFatEstimadoUnidadeNegocio) {
		this.totalFatEstimadoUnidadeNegocio = totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalPercentualUnidadeNegocio.
	 */
	public BigDecimal getTotalPercentualUnidadeNegocio() {
		return totalPercentualUnidadeNegocio;
	}

	/**
	 * @param totalPercentualUnidadeNegocio O totalPercentualUnidadeNegocio a ser setado.
	 */
	public void setTotalPercentualUnidadeNegocio(
			BigDecimal totalPercentualUnidadeNegocio) {
		this.totalPercentualUnidadeNegocio = totalPercentualUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalQtLigacoesUnidadeNegocio.
	 */
	public Integer getTotalQtLigacoesUnidadeNegocio() {
		return totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @param totalQtLigacoesUnidadeNegocio O totalQtLigacoesUnidadeNegocio a ser setado.
	 */
	public void setTotalQtLigacoesUnidadeNegocio(
			Integer totalQtLigacoesUnidadeNegocio) {
		this.totalQtLigacoesUnidadeNegocio = totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalUnidadeNegocio.
	 */
	public Integer getTotalUnidadeNegocio() {
		return totalUnidadeNegocio;
	}

	/**
	 * @param totalUnidadeNegocio O totalUnidadeNegocio a ser setado.
	 */
	public void setTotalUnidadeNegocio(Integer totalUnidadeNegocio) {
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricao.
	 */
	public String getUnidadeNegocioDescricao() {
		return unidadeNegocioDescricao;
	}

	/**
	 * @param unidadeNegocioDescricao O unidadeNegocioDescricao a ser setado.
	 */
	public void setUnidadeNegocioDescricao(String unidadeNegocioDescricao) {
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricaoAbreviada.
	 */
	public String getUnidadeNegocioDescricaoAbreviada() {
		return unidadeNegocioDescricaoAbreviada;
	}

	/**
	 * @param unidadeNegocioDescricaoAbreviada O unidadeNegocioDescricaoAbreviada a ser setado.
	 */
	public void setUnidadeNegocioDescricaoAbreviada(
			String unidadeNegocioDescricaoAbreviada) {
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
	}

}