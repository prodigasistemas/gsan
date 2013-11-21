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

public class RelatorioManterPerfilParcelamentoBean implements RelatorioBean {
	private String idPerfil;

	private String rd;

	private String tipoSituacaoImovel;

	private String perfilImovel;

	private String subcategoria;

	/*
	 * TODO - COSANPA
	 */
	private String percentualDescontoImpontualidadeMulta;
	
	private String percentualDescontoImpontualidadeJurosMora;
	
	private String percentualDescontoImpontualidadeAtualizacaoMonetaria;
	// fim alteração

	private String percentualTarifaMinima;

	private String idReparcelamento;

	private String qtdeReparcelamentosConsecutivos;
	
	private String idPrestacao;

	private String qtdePrestacoesParcelamento;

	private String juros;

	private String percentualEntrada;

	private String idDescontoAntiguidade;

	private String qtdeMinimaMesesDebito;

	private String descontoSemRestabelecimentoAntiguidade;

	private String descontoComRestabelecimentoAntiguidade;

	private String descontoAtivo;

	private String idDescontoInatividade;

	private String qtdeMaximaMesesInatividade;

	private String descontoSemRestabelecimentoInatividade;

	private String descontoComRestabelecimentoInatividade;


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

	public RelatorioManterPerfilParcelamentoBean(String idPerfil, String rd,
			String tipoSituacaoImovel, String perfilImovel,
			String subcategoria, String percentualDescontoImpontualidadeMulta,
			String percentualDescontoImpontualidadeJurosMora,
			String percentualDescontoImpontualidadeAtualizacaoMonetaria,
			String percentualTarifaMinima, String idReparcelamento,
			String qtdeReparcelamentosConsecutivos, String idPrestacao,
			String qtdePrestacoesParcelamento, String juros,
			String percentualEntrada, String idDescontoAntiguidade,
			String qtdeMinimaMesesDebito,
			String descontoSemRestabelecimentoAntiguidade,
			String descontoComRestabelecimentoAntiguidade,
			String descontoAtivo, String idDescontoInatividade,
			String qtdeMaximaMesesInatividade,
			String descontoSemRestabelecimentoInatividade,
			String descontoComRestabelecimentoInatividade) {
		this.idPerfil = idPerfil;
		this.rd = rd;
		this.tipoSituacaoImovel = tipoSituacaoImovel;
		this.perfilImovel = perfilImovel;
		this.subcategoria = subcategoria;
		this.percentualDescontoImpontualidadeMulta = percentualDescontoImpontualidadeMulta;
		this.percentualDescontoImpontualidadeJurosMora = percentualDescontoImpontualidadeJurosMora;
		this.percentualDescontoImpontualidadeAtualizacaoMonetaria = percentualDescontoImpontualidadeAtualizacaoMonetaria;
		this.percentualTarifaMinima = percentualTarifaMinima;
		this.idReparcelamento = idReparcelamento;
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
		this.idPrestacao = idPrestacao;
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
		this.juros = juros;
		this.percentualEntrada = percentualEntrada;
		this.idDescontoAntiguidade = idDescontoAntiguidade;
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
		this.descontoAtivo = descontoAtivo;
		this.idDescontoInatividade = idDescontoInatividade;
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoAtivo() {
		return descontoAtivo;
	}

	public void setDescontoAtivo(String descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}

	public String getDescontoComRestabelecimentoAntiguidade() {
		return descontoComRestabelecimentoAntiguidade;
	}

	public void setDescontoComRestabelecimentoAntiguidade(
			String descontoComRestabelecimentoAntiguidade) {
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
	}

	public String getDescontoComRestabelecimentoInatividade() {
		return descontoComRestabelecimentoInatividade;
	}

	public void setDescontoComRestabelecimentoInatividade(
			String descontoComRestabelecimentoInatividade) {
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoSemRestabelecimentoAntiguidade() {
		return descontoSemRestabelecimentoAntiguidade;
	}

	public void setDescontoSemRestabelecimentoAntiguidade(
			String descontoSemRestabelecimentoAntiguidade) {
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
	}

	public String getDescontoSemRestabelecimentoInatividade() {
		return descontoSemRestabelecimentoInatividade;
	}

	public void setDescontoSemRestabelecimentoInatividade(
			String descontoSemRestabelecimentoInatividade) {
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getPercentualDescontoImpontualidadeMulta() {
		return percentualDescontoImpontualidadeMulta;
	}

	public void setPercentualDescontoImpontualidadeMulta(
			String percentualDescontoImpontualidadeMulta) {
		this.percentualDescontoImpontualidadeMulta = percentualDescontoImpontualidadeMulta;
	}
	
	public String getPercentualDescontoImpontualidadeJurosMora() {
		return percentualDescontoImpontualidadeJurosMora;
	}

	public void setPercentualDescontoImpontualidadeJurosMora(
			String percentualDescontoImpontualidadeJurosMora) {
		this.percentualDescontoImpontualidadeJurosMora = percentualDescontoImpontualidadeJurosMora;
	}
	
	public String getPercentualDescontoImpontualidadeAtualizacaoMonetaria() {
		return percentualDescontoImpontualidadeAtualizacaoMonetaria;
	}

	public void setPercentualDescontoImpontualidadeAtualizacaoMonetaria(
			String percentualDescontoImpontualidadeAtualizacaoMonetaria) {
		this.percentualDescontoImpontualidadeAtualizacaoMonetaria = percentualDescontoImpontualidadeAtualizacaoMonetaria;
	}

	public String getPercentualEntrada() {
		return percentualEntrada;
	}

	public void setPercentualEntrada(String percentualEntrada) {
		this.percentualEntrada = percentualEntrada;
	}

	public String getPercentualTarifaMinima() {
		return percentualTarifaMinima;
	}

	public void setPercentualTarifaMinima(String percentualTarifaMinima) {
		this.percentualTarifaMinima = percentualTarifaMinima;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQtdeMaximaMesesInatividade() {
		return qtdeMaximaMesesInatividade;
	}

	public void setQtdeMaximaMesesInatividade(String qtdeMaximaMesesInatividade) {
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
	}

	public String getQtdeMinimaMesesDebito() {
		return qtdeMinimaMesesDebito;
	}

	public void setQtdeMinimaMesesDebito(String qtdeMinimaMesesDebito) {
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
	}

	public String getQtdePrestacoesParcelamento() {
		return qtdePrestacoesParcelamento;
	}

	public void setQtdePrestacoesParcelamento(String qtdePrestacoesParcelamento) {
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
	}

	public String getQtdeReparcelamentosConsecutivos() {
		return qtdeReparcelamentosConsecutivos;
	}

	public void setQtdeReparcelamentosConsecutivos(
			String qtdeReparcelamentosConsecutivos) {
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getTipoSituacaoImovel() {
		return tipoSituacaoImovel;
	}

	public void setTipoSituacaoImovel(String tipoSituacaoImovel) {
		this.tipoSituacaoImovel = tipoSituacaoImovel;
	}

	public String getIdDescontoAntiguidade() {
		return idDescontoAntiguidade;
	}

	public void setIdDescontoAntiguidade(String idDescontoAntiguidade) {
		this.idDescontoAntiguidade = idDescontoAntiguidade;
	}

	public String getIdDescontoInatividade() {
		return idDescontoInatividade;
	}

	public void setIdDescontoInatividade(String idDescontoInatividade) {
		this.idDescontoInatividade = idDescontoInatividade;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdReparcelamento() {
		return idReparcelamento;
	}

	public void setIdReparcelamento(String idReparcelamento) {
		this.idReparcelamento = idReparcelamento;
	}

	public String getIdPrestacao() {
		return idPrestacao;
	}

	public void setIdPrestacao(String idPrestacao) {
		this.idPrestacao = idPrestacao;
	}

}
