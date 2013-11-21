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
* Anderson Italo Felinto de Lima
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

package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioSupressoesReligacoesReestabelecimentosBean implements RelatorioBean {
	private static final long serialVersionUID = 1L;
	
	private String estado;
	private String gerenciaRegionalNomeAbreviado;
	private String gerenciaRegionalNome;
	private String unidadeNegocio;
	private String localidade;
	private Integer religacoesAntesDiasEstado;
	private Integer religacoesAposDiasEstado;
	private Integer supressoesEstado;	
	private Integer reestabelecimentosEstado;
	private Integer cortadosEstado;
	private Integer religacoesAntesDiasGerencia;
	private Integer religacoesAposDiasGerencia;
	private Integer supressoesGerencia;
	private Integer reestabelecimentosGerencia;
	private Integer cortadosGerencia;
	private Integer religacoesAntesDiasUnidade;
	private Integer religacoesAposDiasUnidade;
	private Integer supressoesUnidade;
	private Integer reestabelecimentosUnidade;
	private Integer cortadosUnidade;
	private Integer religacoesAntesDiasLocalidade;
	private Integer religacoesAposDiasLocalidade;
	private Integer supressoesLocalidade;
	private Integer reestabelecimentosLocalidade;
	private Integer cortadosLocalidade;

	
	public Integer getReestabelecimentosLocalidade() {
		return reestabelecimentosLocalidade;
	}
	public void setReestabelecimentosLocalidade(Integer reestabelecimentosLocalidade) {
		this.reestabelecimentosLocalidade = reestabelecimentosLocalidade;
	}
	public Integer getReestabelecimentosUnidade() {
		return reestabelecimentosUnidade;
	}
	public void setReestabelecimentosUnidade(Integer reestabelecimentosUnidade) {
		this.reestabelecimentosUnidade = reestabelecimentosUnidade;
	}
	public Integer getReligacoesAntesDiasLocalidade() {
		return religacoesAntesDiasLocalidade;
	}
	public void setReligacoesAntesDiasLocalidade(
			Integer religacoesAntesDiasLocalidade) {
		this.religacoesAntesDiasLocalidade = religacoesAntesDiasLocalidade;
	}
	public Integer getReligacoesAntesDiasUnidade() {
		return religacoesAntesDiasUnidade;
	}
	public void setReligacoesAntesDiasUnidade(Integer religacoesAntesDiasUnidade) {
		this.religacoesAntesDiasUnidade = religacoesAntesDiasUnidade;
	}
	public Integer getReligacoesAposDiasLocalidade() {
		return religacoesAposDiasLocalidade;
	}
	public void setReligacoesAposDiasLocalidade(Integer religacoesAposDiasLocalidade) {
		this.religacoesAposDiasLocalidade = religacoesAposDiasLocalidade;
	}
	public Integer getReligacoesAposDiasUnidade() {
		return religacoesAposDiasUnidade;
	}
	public void setReligacoesAposDiasUnidade(Integer religacoesAposDiasUnidade) {
		this.religacoesAposDiasUnidade = religacoesAposDiasUnidade;
	}
	public Integer getSupressoesLocalidade() {
		return supressoesLocalidade;
	}
	public void setSupressoesLocalidade(Integer supressoesLocalidade) {
		this.supressoesLocalidade = supressoesLocalidade;
	}
	public Integer getSupressoesUnidade() {
		return supressoesUnidade;
	}
	public void setSupressoesUnidade(Integer supressoesUnidade) {
		this.supressoesUnidade = supressoesUnidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getGerenciaRegionalNome() {
		return gerenciaRegionalNome;
	}
	public void setGerenciaRegionalNome(String gerenciaRegionalNome) {
		this.gerenciaRegionalNome = gerenciaRegionalNome;
	}
	public String getGerenciaRegionalNomeAbreviado() {
		return gerenciaRegionalNomeAbreviado;
	}
	public void setGerenciaRegionalNomeAbreviado(
			String gerenciaRegionalNomeAbreviado) {
		this.gerenciaRegionalNomeAbreviado = gerenciaRegionalNomeAbreviado;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public Integer getReestabelecimentosEstado() {
		return reestabelecimentosEstado;
	}
	public void setReestabelecimentosEstado(Integer reestabelecimentosEstado) {
		this.reestabelecimentosEstado = reestabelecimentosEstado;
	}
	public Integer getReligacoesAntesDiasEstado() {
		return religacoesAntesDiasEstado;
	}
	public void setReligacoesAntesDiasEstado(Integer religacoesAntesDiasEstado) {
		this.religacoesAntesDiasEstado = religacoesAntesDiasEstado;
	}
	public Integer getReligacoesAposDiasEstado() {
		return religacoesAposDiasEstado;
	}
	public void setReligacoesAposDiasEstado(Integer religacoesAposDiasEstado) {
		this.religacoesAposDiasEstado = religacoesAposDiasEstado;
	}
	public Integer getSupressoesEstado() {
		return supressoesEstado;
	}
	public void setSupressoesEstado(Integer supressoesEstado) {
		this.supressoesEstado = supressoesEstado;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Integer getReestabelecimentosGerencia() {
		return reestabelecimentosGerencia;
	}
	public void setReestabelecimentosGerencia(Integer reestabelecimentosGerencia) {
		this.reestabelecimentosGerencia = reestabelecimentosGerencia;
	}
	public Integer getReligacoesAntesDiasGerencia() {
		return religacoesAntesDiasGerencia;
	}
	public void setReligacoesAntesDiasGerencia(Integer religacoesAntesDiasGerencia) {
		this.religacoesAntesDiasGerencia = religacoesAntesDiasGerencia;
	}
	public Integer getReligacoesAposDiasGerencia() {
		return religacoesAposDiasGerencia;
	}
	public void setReligacoesAposDiasGerencia(Integer religacoesAposDiasGerencia) {
		this.religacoesAposDiasGerencia = religacoesAposDiasGerencia;
	}
	public Integer getSupressoesGerencia() {
		return supressoesGerencia;
	}
	public void setSupressoesGerencia(Integer supressoesGerencia) {
		this.supressoesGerencia = supressoesGerencia;
	}
	public Integer getCortadosEstado() {
		return cortadosEstado;
	}
	public void setCortadosEstado(Integer cortadosEstado) {
		this.cortadosEstado = cortadosEstado;
	}
	public Integer getCortadosGerencia() {
		return cortadosGerencia;
	}
	public void setCortadosGerencia(Integer cortadosGerencia) {
		this.cortadosGerencia = cortadosGerencia;
	}
	public Integer getCortadosUnidade() {
		return cortadosUnidade;
	}
	public void setCortadosUnidade(Integer cortadosUnidade) {
		this.cortadosUnidade = cortadosUnidade;
	}
	public Integer getCortadosLocalidade() {
		return cortadosLocalidade;
	}
	public void setCortadosLocalidade(Integer cortadosLocalidade) {
		this.cortadosLocalidade = cortadosLocalidade;
	}
	
	
}
