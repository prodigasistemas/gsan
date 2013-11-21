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
public class ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;

	private Integer codigoSetorComercial;

	private String setorComercialDescricao;

	private Integer totalSetorComercial;
	
	private BigDecimal totalPercentualSetorComercial;
	private BigDecimal totalFatEstimadoSetorComercial;
	private Integer totalQtLigacoesSetorComercial;

	public ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(
			Integer codigoSetorComercial, String setorComercialDescricao,
			Integer totalSetorComercial) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoSetorComercial = codigoSetorComercial;
		this.setorComercialDescricao = setorComercialDescricao;
		this.totalSetorComercial = totalSetorComercial;
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> getResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper = resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
	}

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
	 * @return Retorna o campo setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	/**
	 * @param setorComercialDescricao O setorComercialDescricao a ser setado.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	/**
	 * @return Retorna o campo totalFatEstimadoSetorComercial.
	 */
	public BigDecimal getTotalFatEstimadoSetorComercial() {
		return totalFatEstimadoSetorComercial;
	}

	/**
	 * @param totalFatEstimadoSetorComercial O totalFatEstimadoSetorComercial a ser setado.
	 */
	public void setTotalFatEstimadoSetorComercial(
			BigDecimal totalFatEstimadoSetorComercial) {
		this.totalFatEstimadoSetorComercial = totalFatEstimadoSetorComercial;
	}

	/**
	 * @return Retorna o campo totalPercentualSetorComercial.
	 */
	public BigDecimal getTotalPercentualSetorComercial() {
		return totalPercentualSetorComercial;
	}

	/**
	 * @param totalPercentualSetorComercial O totalPercentualSetorComercial a ser setado.
	 */
	public void setTotalPercentualSetorComercial(
			BigDecimal totalPercentualSetorComercial) {
		this.totalPercentualSetorComercial = totalPercentualSetorComercial;
	}

	/**
	 * @return Retorna o campo totalQtLigacoesSetorComercial.
	 */
	public Integer getTotalQtLigacoesSetorComercial() {
		return totalQtLigacoesSetorComercial;
	}

	/**
	 * @param totalQtLigacoesSetorComercial O totalQtLigacoesSetorComercial a ser setado.
	 */
	public void setTotalQtLigacoesSetorComercial(
			Integer totalQtLigacoesSetorComercial) {
		this.totalQtLigacoesSetorComercial = totalQtLigacoesSetorComercial;
	}

	/**
	 * @return Retorna o campo totalSetorComercial.
	 */
	public Integer getTotalSetorComercial() {
		return totalSetorComercial;
	}

	/**
	 * @param totalSetorComercial O totalSetorComercial a ser setado.
	 */
	public void setTotalSetorComercial(Integer totalSetorComercial) {
		this.totalSetorComercial = totalSetorComercial;
	}

	

}