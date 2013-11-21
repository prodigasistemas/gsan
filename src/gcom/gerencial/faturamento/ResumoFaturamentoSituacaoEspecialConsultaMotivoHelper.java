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

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper {

	private Integer idMotivo;
	private String motivoDescricao;
	private Integer anoMesInicio;
	private Integer anoMesFim;
	private Integer qtParalisada;
	private Integer qtLigacoes;
	private BigDecimal percentual;
	private BigDecimal faturamentoEstimado;
	private String valorFaturamentoEstimadoFormatado;
	
	
	public Integer getAnoMesFim() {
		return anoMesFim;
	}
	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}
	public Integer getAnoMesInicio() {
		return anoMesInicio;
	}
	public void setAnoMesInicio(Integer anoMesInicio) {
		this.anoMesInicio = anoMesInicio;
	}
	public BigDecimal getFaturamentoEstimado() {
		return faturamentoEstimado;
	}
	public void setFaturamentoEstimado(BigDecimal faturamentoEstimado) {
		this.faturamentoEstimado = faturamentoEstimado;
	}
	public Integer getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}
	public String getMotivoDescricao() {
		return motivoDescricao;
	}
	public void setMotivoDescricao(String motivoDescricao) {
		this.motivoDescricao = motivoDescricao;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	public Integer getQtLigacoes() {
		return qtLigacoes;
	}
	public void setQtLigacoes(Integer qtLigacoes) {
		this.qtLigacoes = qtLigacoes;
	}
	public Integer getQtParalisada() {
		return qtParalisada;
	}
	public void setQtParalisada(Integer qtParalisada) {
		this.qtParalisada = qtParalisada;
	}
	
	public ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(Integer idMotivo, String motivoDescricao, Integer anoMesInicio, Integer anoMesFim, Integer qtParalisada) {
		super();
		// TODO Auto-generated constructor stub
		this.idMotivo = idMotivo;
		this.motivoDescricao = motivoDescricao;
		this.anoMesInicio = anoMesInicio;
		this.anoMesFim = anoMesFim;
		this.qtParalisada = qtParalisada;
	}
	public String getValorFaturamentoEstimadoFormatado() {
		return valorFaturamentoEstimadoFormatado;
	}
	public void setValorFaturamentoEstimadoFormatado(
			String valorFaturamentoEstimadoFormatado) {
		this.valorFaturamentoEstimadoFormatado = valorFaturamentoEstimadoFormatado;
	}
	
	public String getFormatarAnoMesParaMesAnoInicio() {

		String anoMesRecebido = "" + this.getAnoMesInicio();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoInicio = mes + "/" + ano;

		return anoMesFormatadoInicio.toString();
	}
	
	public String getFormatarAnoMesParaMesAnoFim() {

		String anoMesRecebido = "" + this.getAnoMesFim();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoFim = mes + "/" + ano;

		return anoMesFormatadoFim.toString();
	}
	
}