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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterCriterioCobrancaBean implements RelatorioBean {
	private String descricao;

	private String dataInicio;

	private String numeroAnos;

	private String situacaoEspecialCobranca;

	private String situacaoCobranca;

	private String contasRevisao;

	private String imovelDebitoContaMes;

	private String inquilinoDebitoContaMes;

	private String imovelDebitoContasAntigas;
	
	private String indicadorUso;

	private String perfil;

	private String categoria;

	private String intervaloValorDebito;

	private String intervaloQuantidadeContas;

	private String valorMinimoConta;

	private String valorMinimoDebito;

	private String quantidadeMinimaContas;

	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descrição do parâmetro
	 * @param mesAno
	 *            Descrição do parâmetro
	 * @param atividade
	 *            Descrição do parâmetro
	 * @param predecessora
	 *            Descrição do parâmetro
	 * @param obrigatoria
	 *            Descrição do parâmetro
	 * @param dataPrevista
	 *            Descrição do parâmetro
	 * @param dataRealizacao
	 *            Descrição do parâmetro
	 */

	public RelatorioManterCriterioCobrancaBean(String descricao,
			String dataInicio, String numeroAnos,
			String situacaoEspecialCobranca, String situacaoCobranca,
			String contasRevisao, String imovelDebitoContaMes,
			String inquilinoDebitoContaMes, String imovelDebitoContasAntigas, String indicadorUso,
			String perfil, String categoria, String intervaloValorDebito,
			String intervaloQuantidadeContas, String valorMinimoConta,
			String valorMinimoDebito, String quantidadeMinimaContas) {
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.numeroAnos = numeroAnos;
		this.situacaoEspecialCobranca = situacaoEspecialCobranca;
		this.situacaoCobranca = situacaoCobranca;
		this.contasRevisao = contasRevisao;
		this.imovelDebitoContaMes = imovelDebitoContaMes;
		this.inquilinoDebitoContaMes = inquilinoDebitoContaMes;
		this.imovelDebitoContasAntigas = imovelDebitoContasAntigas;
		this.indicadorUso = indicadorUso;
		this.perfil = perfil;
		this.categoria = categoria;
		this.intervaloValorDebito = intervaloValorDebito;
		this.intervaloQuantidadeContas = intervaloQuantidadeContas;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMinimoDebito = valorMinimoDebito;
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getContasRevisao() {
		return contasRevisao;
	}

	public void setContasRevisao(String contasRevisao) {
		this.contasRevisao = contasRevisao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImovelDebitoContaMes() {
		return imovelDebitoContaMes;
	}

	public void setImovelDebitoContaMes(String imovelDebitoContaMes) {
		this.imovelDebitoContaMes = imovelDebitoContaMes;
	}

	public String getImovelDebitoContasAntigas() {
		return imovelDebitoContasAntigas;
	}

	public void setImovelDebitoContasAntigas(String imovelDebitoContasAntigas) {
		this.imovelDebitoContasAntigas = imovelDebitoContasAntigas;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getInquilinoDebitoContaMes() {
		return inquilinoDebitoContaMes;
	}

	public void setInquilinoDebitoContaMes(String inquilinoDebitoContaMes) {
		this.inquilinoDebitoContaMes = inquilinoDebitoContaMes;
	}

	public String getIntervaloQuantidadeContas() {
		return intervaloQuantidadeContas;
	}

	public void setIntervaloQuantidadeContas(String intervaloQuantidadeContas) {
		this.intervaloQuantidadeContas = intervaloQuantidadeContas;
	}

	public String getIntervaloValorDebito() {
		return intervaloValorDebito;
	}

	public void setIntervaloValorDebito(String intervaloValorDebito) {
		this.intervaloValorDebito = intervaloValorDebito;
	}

	public String getNumeroAnos() {
		return numeroAnos;
	}

	public void setNumeroAnos(String numeroAnos) {
		this.numeroAnos = numeroAnos;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public String getSituacaoEspecialCobranca() {
		return situacaoEspecialCobranca;
	}

	public void setSituacaoEspecialCobranca(String situacaoEspecialCobranca) {
		this.situacaoEspecialCobranca = situacaoEspecialCobranca;
	}

	public String getValorMinimoConta() {
		return valorMinimoConta;
	}

	public void setValorMinimoConta(String valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public String getValorMinimoDebito() {
		return valorMinimoDebito;
	}

	public void setValorMinimoDebito(String valorMinimoDebito) {
		this.valorMinimoDebito = valorMinimoDebito;
	}
	
	
	
}
