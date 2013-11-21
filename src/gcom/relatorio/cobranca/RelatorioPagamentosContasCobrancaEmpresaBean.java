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
* Rômulo Aurélio de Melo Souza Filho
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
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * @author Rômulo Aurélio 
 * @date 08/01/2009
 */
public class RelatorioPagamentosContasCobrancaEmpresaBean implements RelatorioBean {
	
	private String idEmpresa;
	
	private String nomeEmpresa;
	
	private String periodoPagamentoInicial;
	
	private String periodoPagamentoFinal;
	
	private String matricula;
	
	private String nomeCliente;
	
	private String valorConta;
	
	private String anoMesReferenciaPagamento;
	
	private String valorPrincipal;
	
	private String valorEncargos;
	
	private String valorTotalPagamentos;
	
	private String percentualEmpresa;
	
	private String valorEmpresa;
	
	private String idImovel;
	
	private String anoMesConta;
	
	private String totalValorPrincipal;
	
	private String totalValorEncargos;
	
	private String totalValorTotalPagamentos;
	
	private String totalPercentualEmpresa;
	
	private String totalValorEmpresa;
	
	private String descricaoGerencia;
	
	private String idGerenciaRegional ;
	
	private String descricaoLocalidade ;
	
	private String idLocalidade ;
	
	private String nomeLocalidade ;

	private String descricaoUnidadeNegocio ;
	
	private String idUnidadeNegocio ;
	
	private String nomeUnidadeNegocio ;
	
	private String nomeGerenciaRegional ;
	
	private String idRota ;
	
	private String indicadorTipoPagamento;
	
	private String tipoPagamento;
	
	private String numeroParcelaAtual;
	
	private String numeroTotalParcelas;
	
	private String codigoQuebra;
	
	private String descricaoQuebra;
	
	private String codigoQuebra2;
	
	private String descricaoQuebra2;
	

	public String getAnoMesConta() {
		return anoMesConta;
	}

	public void setAnoMesConta(String anoMesConta) {
		this.anoMesConta = anoMesConta;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	public void setAnoMesReferenciaPagamento(String anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getPercentualEmpresa() {
		return percentualEmpresa;
	}

	public void setPercentualEmpresa(String percentualEmpresa) {
		this.percentualEmpresa = percentualEmpresa;
	}

	public String getPeriodoPagamentoFinal() {
		return periodoPagamentoFinal;
	}

	public void setPeriodoPagamentoFinal(String periodoPagamentoFinal) {
		this.periodoPagamentoFinal = periodoPagamentoFinal;
	}

	public String getPeriodoPagamentoInicial() {
		return periodoPagamentoInicial;
	}

	public void setPeriodoPagamentoInicial(String periodoPagamentoInicial) {
		this.periodoPagamentoInicial = periodoPagamentoInicial;
	}

	public String getValorConta() {
		return valorConta;
	}

	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}

	public String getValorEmpresa() {
		return valorEmpresa;
	}

	public void setValorEmpresa(String valorEmpresa) {
		this.valorEmpresa = valorEmpresa;
	}

	public String getValorEncargos() {
		return valorEncargos;
	}

	public void setValorEncargos(String valorEncargos) {
		this.valorEncargos = valorEncargos;
	}

	public String getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(String valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	public String getValorTotalPagamentos() {
		return valorTotalPagamentos;
	}

	public void setValorTotalPagamentos(String valorTotalPagamentos) {
		this.valorTotalPagamentos = valorTotalPagamentos;
	}

	public String getTotalPercentualEmpresa() {
		return totalPercentualEmpresa;
	}

	public void setTotalPercentualEmpresa(String totalPercentualEmpresa) {
		this.totalPercentualEmpresa = totalPercentualEmpresa;
	}

	public String getTotalValorEmpresa() {
		return totalValorEmpresa;
	}

	public void setTotalValorEmpresa(String totalValorEmpresa) {
		this.totalValorEmpresa = totalValorEmpresa;
	}

	public String getTotalValorEncargos() {
		return totalValorEncargos;
	}

	public void setTotalValorEncargos(String totalValorEncargos) {
		this.totalValorEncargos = totalValorEncargos;
	}

	public String getTotalValorPrincipal() {
		return totalValorPrincipal;
	}

	public void setTotalValorPrincipal(String totalValorPrincipal) {
		this.totalValorPrincipal = totalValorPrincipal;
	}

	public String getTotalValorTotalPagamentos() {
		return totalValorTotalPagamentos;
	}

	public void setTotalValorTotalPagamentos(String totalValorTotalPagamentos) {
		this.totalValorTotalPagamentos = totalValorTotalPagamentos;
	}

	
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getIdRota() {
		return idRota;
	}

	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}

	public String getCodigoQuebra() {
		return codigoQuebra;
	}

	public void setCodigoQuebra(String codigoQuebra) {
		this.codigoQuebra = codigoQuebra;
	}

	public String getDescricaoQuebra() {
		return descricaoQuebra;
	}

	public void setDescricaoQuebra(String descricaoQuebra) {
		this.descricaoQuebra = descricaoQuebra;
	}

	public String getCodigoQuebra2() {
		return codigoQuebra2;
	}

	public void setCodigoQuebra2(String codigoQuebra2) {
		this.codigoQuebra2 = codigoQuebra2;
	}

	public String getDescricaoQuebra2() {
		return descricaoQuebra2;
	}

	public void setDescricaoQuebra2(String descricaoQuebra2) {
		this.descricaoQuebra2 = descricaoQuebra2;
	}

	public String getIndicadorTipoPagamento() {
		return indicadorTipoPagamento;
	}

	public void setIndicadorTipoPagamento(String indicadorTipoPagamento) {
		this.indicadorTipoPagamento = indicadorTipoPagamento;
	}

	public String getNumeroParcelaAtual() {
		return numeroParcelaAtual;
	}

	public void setNumeroParcelaAtual(String numeroParcelaAtual) {
		this.numeroParcelaAtual = numeroParcelaAtual;
	}

	public String getNumeroTotalParcelas() {
		return numeroTotalParcelas;
	}

	public void setNumeroTotalParcelas(String numeroTotalParcelas) {
		this.numeroTotalParcelas = numeroTotalParcelas;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	
	
	
	
	

}
