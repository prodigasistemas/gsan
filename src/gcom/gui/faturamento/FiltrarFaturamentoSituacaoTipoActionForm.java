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
package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0846]FILTRAR TIPO DA SITUACAO DE FATURAMENTO
 * 
 * @author Arthur Carvalho
 * @date 20/08/2008
 */

public class FiltrarFaturamentoSituacaoTipoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String indicadorParalisacaoFaturamento;
	private String indicadorParalisacaoLeitura;
	private String indicadorValidoAgua;
	private String indicadorValidoEsgoto;
	private String indicadorInformarConsumo;
	private String indicadorInformarVolume;
	private String indicadorUso;
	private String leituraAnormalidadeLeituraComLeitura;
	private String leituraAnormalidadeLeituraSemLeitura;
	private String leituraAnormalidadeConsumoComLeitura;
	private String leituraAnormalidadeConsumoSemLeitura;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}
	public void setIndicadorInformarConsumo(String indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}
	public String getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}
	public void setIndicadorInformarVolume(String indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}
	public String getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}
	public void setIndicadorParalisacaoFaturamento(
			String indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}
	public String getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}
	public void setIndicadorParalisacaoLeitura(String indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}
	public void setIndicadorValidoAgua(String indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}
	public String getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}
	public void setIndicadorValidoEsgoto(String indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}
	public String getLeituraAnormalidadeConsumoComLeitura() {
		return leituraAnormalidadeConsumoComLeitura;
	}
	public void setLeituraAnormalidadeConsumoComLeitura(
			String leituraAnormalidadeConsumoComLeitura) {
		this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
	}
	public String getLeituraAnormalidadeConsumoSemLeitura() {
		return leituraAnormalidadeConsumoSemLeitura;
	}
	public void setLeituraAnormalidadeConsumoSemLeitura(
			String leituraAnormalidadeConsumoSemLeitura) {
		this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
	}
	public String getLeituraAnormalidadeLeituraComLeitura() {
		return leituraAnormalidadeLeituraComLeitura;
	}
	public void setLeituraAnormalidadeLeituraComLeitura(
			String leituraAnormalidadeLeituraComLeitura) {
		this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
	}
	public String getLeituraAnormalidadeLeituraSemLeitura() {
		return leituraAnormalidadeLeituraSemLeitura;
	}
	public void setLeituraAnormalidadeLeituraSemLeitura(
			String leituraAnormalidadeLeituraSemLeitura) {
		this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
}	