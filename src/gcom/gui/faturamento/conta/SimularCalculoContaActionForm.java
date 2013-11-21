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
package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorActionForm;

public class SimularCalculoContaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	String mesAnoReferencia;
	String ligacaoAguaSituacaoID;
	String ligacaoEsgotoSituacaoID;
	String consumoFaturadoAgua;
	String consumoFaturadoEsgoto;
	String percentualEsgoto;
	String consumoTarifaID;
	String faturamentoGrupoID;
	String area;
	Integer totalEconomia;
	String situacaoMensagem;
	String percentualColeta;
	String consumoFaturadoPoco;
	String numeroMoradores;
	String pontosUtilizacao;
	
	public String getPercentualColeta() {
		return percentualColeta;
	}
	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}
	public String getConsumoFaturadoAgua() {
		return consumoFaturadoAgua;
	}
	public void setConsumoFaturadoAgua(String consumoFaturadoAgua) {
		this.consumoFaturadoAgua = consumoFaturadoAgua;
	}
	public String getConsumoFaturadoEsgoto() {
		return consumoFaturadoEsgoto;
	}
	public void setConsumoFaturadoEsgoto(String consumoFaturadoEsgoto) {
		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}
	public String getConsumoTarifaID() {
		return consumoTarifaID;
	}
	public void setConsumoTarifaID(String consumoTarifaID) {
		this.consumoTarifaID = consumoTarifaID;
	}
	public String getFaturamentoGrupoID() {
		return faturamentoGrupoID;
	}
	public void setFaturamentoGrupoID(String faturamentoGrupoID) {
		this.faturamentoGrupoID = faturamentoGrupoID;
	}
	public String getLigacaoAguaSituacaoID() {
		return ligacaoAguaSituacaoID;
	}
	public void setLigacaoAguaSituacaoID(String ligacaoAguaSituacaoID) {
		this.ligacaoAguaSituacaoID = ligacaoAguaSituacaoID;
	}
	public String getLigacaoEsgotoSituacaoID() {
		return ligacaoEsgotoSituacaoID;
	}
	public void setLigacaoEsgotoSituacaoID(String ligacaoEsgotoSituacaoID) {
		this.ligacaoEsgotoSituacaoID = ligacaoEsgotoSituacaoID;
	}
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo totalEconomia.
	 */
	public Integer getTotalEconomia() {
		return totalEconomia;
	}
	/**
	 * @param totalEconomia O totalEconomia a ser setado.
	 */
	public void setTotalEconomia(Integer totalEconomia) {
		this.totalEconomia = totalEconomia;
	}
	/**
	 * @return Retorna o campo situacaoMensagem.
	 */
	public String getSituacaoMensagem() {
		return situacaoMensagem;
	}
	/**
	 * @param situacaoMensagem O situacaoMensagem a ser setado.
	 */
	public void setSituacaoMensagem(String situacaoMensagem) {
		this.situacaoMensagem = situacaoMensagem;
	}
	/**
	 * @return Retorna o campo area.
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area O area a ser setado.
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getConsumoFaturadoPoco() {
		return consumoFaturadoPoco;
	}
	
	public void setConsumoFaturadoPoco(String consumoFaturadoPoco) {
		this.consumoFaturadoPoco = consumoFaturadoPoco;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getPontosUtilizacao() {
		return pontosUtilizacao;
	}
	public void setPontosUtilizacao(String pontosUtilizacao) {
		this.pontosUtilizacao = pontosUtilizacao;
	}

}
