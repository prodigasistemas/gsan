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

import org.apache.struts.action.ActionForm;

public class PesquisarLeituraAnormalidadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String anormalidadeRelativaHidrometro;

	private String anormalidadeSemHidrometro;

	private String anormalidadeRestritoSistema;

	private String anormalidadePerdaTarifaSocial;

	private String anormalidadeOrdemServicoAutomatica;

	private String tipoServico;

	private String consumoCobradoLeituraNaoInformada;

	private String consumoCobradoLeituraInformada;

	private String leituraFaturamentoLeituraNaoInformada;

	private String leituraFaturamentoLeituraInformada;

	public String getAnormalidadeOrdemServicoAutomatica() {
		return anormalidadeOrdemServicoAutomatica;
	}

	public void setAnormalidadeOrdemServicoAutomatica(
			String anormalidadeOrdemServicoAutomatica) {
		this.anormalidadeOrdemServicoAutomatica = anormalidadeOrdemServicoAutomatica;
	}

	public String getAnormalidadePerdaTarifaSocial() {
		return anormalidadePerdaTarifaSocial;
	}

	public void setAnormalidadePerdaTarifaSocial(
			String anormalidadePerdaTarifaSocial) {
		this.anormalidadePerdaTarifaSocial = anormalidadePerdaTarifaSocial;
	}

	public String getAnormalidadeRelativaHidrometro() {
		return anormalidadeRelativaHidrometro;
	}

	public void setAnormalidadeRelativaHidrometro(
			String anormalidadeRelativaHidrometro) {
		this.anormalidadeRelativaHidrometro = anormalidadeRelativaHidrometro;
	}

	public String getAnormalidadeRestritoSistema() {
		return anormalidadeRestritoSistema;
	}

	public void setAnormalidadeRestritoSistema(
			String anormalidadeRestritoSistema) {
		this.anormalidadeRestritoSistema = anormalidadeRestritoSistema;
	}

	public String getAnormalidadeSemHidrometro() {
		return anormalidadeSemHidrometro;
	}

	public void setAnormalidadeSemHidrometro(String anormalidadeSemHidrometro) {
		this.anormalidadeSemHidrometro = anormalidadeSemHidrometro;
	}

	public String getConsumoCobradoLeituraInformada() {
		return consumoCobradoLeituraInformada;
	}

	public void setConsumoCobradoLeituraInformada(
			String consumoCobradoLeituraInformada) {
		this.consumoCobradoLeituraInformada = consumoCobradoLeituraInformada;
	}

	public String getConsumoCobradoLeituraNaoInformada() {
		return consumoCobradoLeituraNaoInformada;
	}

	public void setConsumoCobradoLeituraNaoInformada(
			String consumoCobradoLeituraNaoInformada) {
		this.consumoCobradoLeituraNaoInformada = consumoCobradoLeituraNaoInformada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLeituraFaturamentoLeituraInformada() {
		return leituraFaturamentoLeituraInformada;
	}

	public void setLeituraFaturamentoLeituraInformada(
			String leituraFaturamentoLeituraInformada) {
		this.leituraFaturamentoLeituraInformada = leituraFaturamentoLeituraInformada;
	}

	public String getLeituraFaturamentoLeituraNaoInformada() {
		return leituraFaturamentoLeituraNaoInformada;
	}

	public void setLeituraFaturamentoLeituraNaoInformada(
			String leituraFaturamentoLeituraNaoInformada) {
		this.leituraFaturamentoLeituraNaoInformada = leituraFaturamentoLeituraNaoInformada;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}
