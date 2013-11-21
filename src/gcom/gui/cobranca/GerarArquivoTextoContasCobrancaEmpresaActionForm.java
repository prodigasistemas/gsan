/**
 * 
 */
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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0896] Gerar Arquivo Texto das Contas em Cobrança por Empresa
 * 
 * @author Rômulo Aurélio
 * @since 29/10/2008
 */
public class GerarArquivoTextoContasCobrancaEmpresaActionForm extends
		ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoComandoInicial;

	private String periodoComandoFinal;

	private String dataExecucaoComando;

	private String periodoReferenciaContasInicial;

	private String periodoReferenciaContasFinal;

	private String periodoVencimentoContasInicial;

	private String periodoVencimentoContasFinal;

	private String intervaloValorContasInicial;

	private String intervaloValorContasFinal;
	
	private String qtdContasInicial;
	
	private String qtdContasFinal;
	
	private String qtdDeDiasVencimento;

	private String idImovel;

	private String idCliente;

	private String nomeCliente;

	private String idUnidadeNegocio;

	private String nomeUnidadeNegocio;
	
	private String idLigacaoAguaSituacao;

	private String nomeLigacaoAguaSituacao;

	private String intervaloLocalizacaoInicial;

	private String intervaloLocalizacaoFinal;

	private String intervaloSetorComercialInicial;
	
	private String intervaloSetorComercialFinal;

	private String intervaloRotasInicial;

	private String numeroSequencialRotaInicial;

	private String intervaloRotasFinal;

	private String numeroSequencialRotaFinal;

	private String qtdeTotalContasCobranca;

	private String valorTotalContasCobranca;

	private String qtdeContasCriterioComando;

	private String valorContasCriterioComando;
	
	private Integer[] idRegistros;
	
	private String idImovelPerfil;
	
	private String dsImovelPerfil;
	
	private String idGerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private String intervaloQuadraInicial;
	
	private String intervaloQuadraFinal;

	public String getDataExecucaoComando() {
		return dataExecucaoComando;
	}

	public void setDataExecucaoComando(String dataExecucaoComando) {
		this.dataExecucaoComando = dataExecucaoComando;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIntervaloLocalizacaoFinal() {
		return intervaloLocalizacaoFinal;
	}

	public void setIntervaloLocalizacaoFinal(String intervaloLocalizacaoFinal) {
		this.intervaloLocalizacaoFinal = intervaloLocalizacaoFinal;
	}

	public String getIntervaloLocalizacaoInicial() {
		return intervaloLocalizacaoInicial;
	}

	public void setIntervaloLocalizacaoInicial(String intervaloLocalizacaoInicial) {
		this.intervaloLocalizacaoInicial = intervaloLocalizacaoInicial;
	}

	public String getIntervaloRotasFinal() {
		return intervaloRotasFinal;
	}

	public void setIntervaloRotasFinal(String intervaloRotasFinal) {
		this.intervaloRotasFinal = intervaloRotasFinal;
	}

	public String getIntervaloRotasInicial() {
		return intervaloRotasInicial;
	}

	public void setIntervaloRotasInicial(String intervaloRotasInicial) {
		this.intervaloRotasInicial = intervaloRotasInicial;
	}

	public String getIntervaloSetorComercialInicial() {
		return intervaloSetorComercialInicial;
	}

	public void setIntervaloSetorComercialInicial(
			String intervaloSetorComercialInicial) {
		this.intervaloSetorComercialInicial = intervaloSetorComercialInicial;
	}

	public String getIntervaloValorContasFinal() {
		return intervaloValorContasFinal;
	}

	public void setIntervaloValorContasFinal(String intervaloValorContasFinal) {
		this.intervaloValorContasFinal = intervaloValorContasFinal;
	}

	public String getIntervaloValorContasInicial() {
		return intervaloValorContasInicial;
	}

	public void setIntervaloValorContasInicial(String intervaloValorContasInicial) {
		this.intervaloValorContasInicial = intervaloValorContasInicial;
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

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getNumeroSequencialRotaFinal() {
		return numeroSequencialRotaFinal;
	}

	public void setNumeroSequencialRotaFinal(String numeroSequencialRotaFinal) {
		this.numeroSequencialRotaFinal = numeroSequencialRotaFinal;
	}

	public String getNumeroSequencialRotaInicial() {
		return numeroSequencialRotaInicial;
	}

	public void setNumeroSequencialRotaInicial(String numeroSequencialRotaInicial) {
		this.numeroSequencialRotaInicial = numeroSequencialRotaInicial;
	}

	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	public String getPeriodoReferenciaContasFinal() {
		return periodoReferenciaContasFinal;
	}

	public void setPeriodoReferenciaContasFinal(String periodoReferenciaContasFinal) {
		this.periodoReferenciaContasFinal = periodoReferenciaContasFinal;
	}

	public String getPeriodoReferenciaContasInicial() {
		return periodoReferenciaContasInicial;
	}

	public void setPeriodoReferenciaContasInicial(
			String periodoReferenciaContasInicial) {
		this.periodoReferenciaContasInicial = periodoReferenciaContasInicial;
	}

	public String getPeriodoVencimentoContasFinal() {
		return periodoVencimentoContasFinal;
	}

	public void setPeriodoVencimentoContasFinal(String periodoVencimentoContasFinal) {
		this.periodoVencimentoContasFinal = periodoVencimentoContasFinal;
	}

	public String getPeriodoVencimentoContasInicial() {
		return periodoVencimentoContasInicial;
	}

	public void setPeriodoVencimentoContasInicial(
			String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}

	public String getQtdeContasCriterioComando() {
		return qtdeContasCriterioComando;
	}

	public void setQtdeContasCriterioComando(String qtdeContasCriterioComando) {
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
	}

	public String getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}

	public void setQtdeTotalContasCobranca(String qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}

	public String getValorContasCriterioComando() {
		return valorContasCriterioComando;
	}

	public void setValorContasCriterioComando(String valorContasCriterioComando) {
		this.valorContasCriterioComando = valorContasCriterioComando;
	}

	public String getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}

	public void setValorTotalContasCobranca(String valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public Integer[] getIdRegistros() {
		return idRegistros;
	}

	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}

	public String getIntervaloSetorComercialFinal() {
		return intervaloSetorComercialFinal;
	}

	public void setIntervaloSetorComercialFinal(String intervaloSetorComercialFinal) {
		this.intervaloSetorComercialFinal = intervaloSetorComercialFinal;
	}

	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public String getDsImovelPerfil() {
		return dsImovelPerfil;
	}

	public void setDsImovelPerfil(String dsImovelPerfil) {
		this.dsImovelPerfil = dsImovelPerfil;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getIntervaloQuadraFinal() {
		return intervaloQuadraFinal;
	}

	public void setIntervaloQuadraFinal(String intervaloQuadraFinal) {
		this.intervaloQuadraFinal = intervaloQuadraFinal;
	}

	public String getIntervaloQuadraInicial() {
		return intervaloQuadraInicial;
	}

	public void setIntervaloQuadraInicial(String intervaloQuadraInicial) {
		this.intervaloQuadraInicial = intervaloQuadraInicial;
	}

	public String getQtdContasInicial() {
		return qtdContasInicial;
	}

	public void setQtdContasInicial(String qtdContasInicial) {
		this.qtdContasInicial = qtdContasInicial;
	}

	public String getQtdContasFinal() {
		return qtdContasFinal;
	}

	public void setQtdContasFinal(String qtdContasFinal) {
		this.qtdContasFinal = qtdContasFinal;
	}

	public String getQtdDeDiasVencimento() {
		return qtdDeDiasVencimento;
	}

	public void setQtdDeDiasVencimento(String qtdDeDiasVencimento) {
		this.qtdDeDiasVencimento = qtdDeDiasVencimento;
	}

	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public String getNomeLigacaoAguaSituacao() {
		return nomeLigacaoAguaSituacao;
	}

	public void setNomeLigacaoAguaSituacao(String nomeLigacaoAguaSituacao) {
		this.nomeLigacaoAguaSituacao = nomeLigacaoAguaSituacao;
	}

}
