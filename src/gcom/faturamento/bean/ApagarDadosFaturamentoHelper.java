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
package gcom.faturamento.bean;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;

import java.util.Date;

/**
 * Parâmetros para apagar as contas geradas pelo faturamento
 *
 * @author Raphael Rossiter
 * 
 * @date 25/03/2008
 */
public class ApagarDadosFaturamentoHelper {

	private FaturamentoGrupo faturamentoGrupo;
	
	private Rota rota;
	
	private Integer anoMesFaturamento;
	
	private Integer idDebitoCreditoSituacaoAtual;
	
	private Date dataEmissaoInicial;
	
	private Date dataEmissaoFinal;
	
	private Integer idImovel;
	
	/*
	 * Utilizado apenas para o batch [UC0876] - Gerar Crédito Situação Especial Faturamento que faz parte do
	 * processo de faturamento da CAERN
	 */
	private Integer idCreditoTipo;
	
	public ApagarDadosFaturamentoHelper(){}

	public Integer getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(Integer anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Integer getIdDebitoCreditoSituacaoAtual() {
		return idDebitoCreditoSituacaoAtual;
	}

	public void setIdDebitoCreditoSituacaoAtual(Integer idDebitoCreditoSituacaoAtual) {
		this.idDebitoCreditoSituacaoAtual = idDebitoCreditoSituacaoAtual;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Integer getIdCreditoTipo() {
		return idCreditoTipo;
	}

	public void setIdCreditoTipo(Integer idCreditoTipo) {
		this.idCreditoTipo = idCreditoTipo;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	
}
