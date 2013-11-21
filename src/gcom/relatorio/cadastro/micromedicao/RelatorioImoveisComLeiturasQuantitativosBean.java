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
package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras
 * [SB0001] Gerar relatório tipo 1
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class RelatorioImoveisComLeiturasQuantitativosBean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private Integer grupoFaturamento;

	private String empresa;

	private String localidade;

	private String setorComercial;

	private Integer rota;

	private String leiturista;

	private Integer qtdImoveis;

	public RelatorioImoveisComLeiturasQuantitativosBean() {
	}

	public RelatorioImoveisComLeiturasQuantitativosBean(String mesAnoReferencia,
			Integer grupoFaturamento, String empresa, String localidade,
			String setorComercial, Integer rota, String leiturista,
			Integer qtdImoveis) {
		this.mesAnoReferencia = mesAnoReferencia;
		this.grupoFaturamento = grupoFaturamento;
		this.empresa = empresa;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.leiturista = leiturista;
		this.qtdImoveis = qtdImoveis;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getQtdImoveis() {
		return qtdImoveis;
	}

	public void setQtdImoveis(Integer qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}

	public Integer getRota() {
		return rota;
	}

	public void setRota(Integer rota) {
		this.rota = rota;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
}