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
package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class FiltrarAnormalidadeLeituraActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String descricao;

	String indicadorRelativoHidrometro;

	String indicadorImovelSemHidrometro;

	String usoRestritoSistema;

	String perdaTarifaSocial;

	String osAutomatico;

	String tipoServico;

	String consumoLeituraNaoInformado;

	String consumoLeituraInformado;

	String leituraLeituraNaoturaInformado;

	String leituraLeituraInformado;

	String tipoPesquisa;

	String indicadorUso;

	private String indicadorAtualizar;

	/**
	 * TODO : COSANPA Pamela Gatinho - 13/03/2012 Campo que identifica se a
	 * anormalidade será usada ou não no sistema de leitura e impressão
	 * simultanea.
	 */
	String indicadorImpressaoSimultanea;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getConsumoLeituraInformado() {
		return consumoLeituraInformado;
	}

	public void setConsumoLeituraInformado(String consumoLeituraInformado) {
		this.consumoLeituraInformado = consumoLeituraInformado;
	}

	public String getConsumoLeituraNaoInformado() {
		return consumoLeituraNaoInformado;
	}

	public void setConsumoLeituraNaoInformado(String consumoLeituraNaoInformado) {
		this.consumoLeituraNaoInformado = consumoLeituraNaoInformado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorImovelSemHidrometro() {
		return indicadorImovelSemHidrometro;
	}

	public void setIndicadorImovelSemHidrometro(
			String indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	public String getIndicadorRelativoHidrometro() {
		return indicadorRelativoHidrometro;
	}

	public void setIndicadorRelativoHidrometro(
			String indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}

	public String getLeituraLeituraInformado() {
		return leituraLeituraInformado;
	}

	public void setLeituraLeituraInformado(String leituraLeituraInformado) {
		this.leituraLeituraInformado = leituraLeituraInformado;
	}

	public String getLeituraLeituraNaoturaInformado() {
		return leituraLeituraNaoturaInformado;
	}

	public void setLeituraLeituraNaoturaInformado(
			String leituraLeituraNaoturaInformado) {
		this.leituraLeituraNaoturaInformado = leituraLeituraNaoturaInformado;
	}

	public String getOsAutomatico() {
		return osAutomatico;
	}

	public void setOsAutomatico(String osAutomatico) {
		this.osAutomatico = osAutomatico;
	}

	public String getPerdaTarifaSocial() {
		return perdaTarifaSocial;
	}

	public void setPerdaTarifaSocial(String perdaTarifaSocial) {
		this.perdaTarifaSocial = perdaTarifaSocial;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getUsoRestritoSistema() {
		return usoRestritoSistema;
	}

	public void setUsoRestritoSistema(String usoRestritoSistema) {
		this.usoRestritoSistema = usoRestritoSistema;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorImpressaoSimultanea() {
		return indicadorImpressaoSimultanea;
	}

	public void setIndicadorImpressaoSimultanea(
			String indicadorImpressaoSimultanea) {
		this.indicadorImpressaoSimultanea = indicadorImpressaoSimultanea;
	}
}
