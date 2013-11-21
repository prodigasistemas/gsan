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
package gcom.gui.cobranca.parcelamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarParcelamentoPerfilActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	/*
	 * TODO - COSANPA
	 */
    private String percentualDescontoAcrescimoMulta;
    
    private String percentualDescontoAcrescimoJurosMora;
    
    private String percentualDescontoAcrescimoAtualizacaoMonetaria;
    // fim alteração
    
    private String percentualTarifaMinimaPrestacao;

    private String ultimaAlteracao;

    private String subcategoria;

    private String imovelSituacaoTipo;

    private String imovelPerfil;
    
    private String numeroResolucaoDiretoria;    		
   
    private String assuntoResolucaoDiretoria;
    
    private String qtdeMaximaReparcelamento;
    
    private String percentualEntradaSugerida; 
    
    private String quantidadeMinimaMesesDebito;
    
    private String percentualDescontoSemRestabelecimentoAntiguidade;
    
    private String percentualDescontoComRestabelecimentoAntiguidade;
    
    private String percentualDescontoAtivo; 

    private String quantidadeMaximaMesesInatividade;
    
    private String percentualDescontoSemRestabelecimentoInatividade;
    
    private String percentualDescontoComRestabelecimentoInatividade;

    private String consumoMinimo;
    
    private String percentualVariacaoConsumoMedio;
    
    private String indicadorParcelarChequeDevolvido;
    
    private String indicadorParcelarSancoesMaisDeUmaConta;
    
    private String categoria;
    private String numeroConsumoEconomia;
    private String numeroAreaConstruida;    
    private String indicadorRetroativoTarifaSocial;
    private String anoMesReferenciaLimiteInferior;
    private String anoMesReferenciaLimiteSuperior;
    private String percentualDescontoTarifaSocial;
    private String parcelaQuantidadeMinimaFatura;
    private String indicadorAlertaParcelaMinima;
    private String percentualDescontoSancao;
    private String quantidadeEconomias; 
    private String capacidadeHidrometro; 
    private String indicadorEntradaMinima;
    private String quantidadeMaximaReparcelamento;
    
    private String percentualDescontoAcrescimoPagamentoAVista;
    
    private String idContaMotivoRevisao;
    private String dataLimiteDescontoPagamentoAVista;
    
    private String quantidadeMaximaMesesInatividadeAVista;  
    private String percentualDescontoSemRestabelecimentoInatividadeAVista; 
    private String percentualDescontoComRestabelecimentoInatividadeAVista; 

	public String getPercentualDescontoComRestabelecimentoInatividadeAVista() {
		return percentualDescontoComRestabelecimentoInatividadeAVista;
	}

	public void setPercentualDescontoComRestabelecimentoInatividadeAVista(
			String percentualDescontoComRestabelecimentoInatividadeAVista) {
		this.percentualDescontoComRestabelecimentoInatividadeAVista = percentualDescontoComRestabelecimentoInatividadeAVista;
	}

	public String getPercentualDescontoSemRestabelecimentoInatividadeAVista() {
		return percentualDescontoSemRestabelecimentoInatividadeAVista;
	}

	public void setPercentualDescontoSemRestabelecimentoInatividadeAVista(
			String percentualDescontoSemRestabelecimentoInatividadeAVista) {
		this.percentualDescontoSemRestabelecimentoInatividadeAVista = percentualDescontoSemRestabelecimentoInatividadeAVista;
	}

	public String getQuantidadeMaximaMesesInatividadeAVista() {
		return quantidadeMaximaMesesInatividadeAVista;
	}

	public void setQuantidadeMaximaMesesInatividadeAVista(
			String quantidadeMaximaMesesInatividadeAVista) {
		this.quantidadeMaximaMesesInatividadeAVista = quantidadeMaximaMesesInatividadeAVista;
	}

	public String getIdContaMotivoRevisao() {
		return idContaMotivoRevisao;
	}

	public void setIdContaMotivoRevisao(String idContaMotivoRevisao) {
		this.idContaMotivoRevisao = idContaMotivoRevisao;
	}

	public String getPercentualDescontoAcrescimoPagamentoAVista() {
		return percentualDescontoAcrescimoPagamentoAVista;
	}

	public void setPercentualDescontoAcrescimoPagamentoAVista(
			String percentualDescontoAcrescimoPagamentoAVista) {
		this.percentualDescontoAcrescimoPagamentoAVista = percentualDescontoAcrescimoPagamentoAVista;
	}

	public String getQuantidadeMaximaReparcelamento() {
		return quantidadeMaximaReparcelamento;
	}

	public void setQuantidadeMaximaReparcelamento(
			String quantidadeMaximaReparcelamento) {
		this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
	}

	public String getQtdeMaximaReparcelamento() {
		return qtdeMaximaReparcelamento;
	}

	public void setQtdeMaximaReparcelamento(String qtdeMaximaReparcelamento) {
		this.qtdeMaximaReparcelamento = qtdeMaximaReparcelamento;
	}

	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

	public String getAssuntoResolucaoDiretoria() {
		return assuntoResolucaoDiretoria;
	}

	public void setAssuntoResolucaoDiretoria(String assuntoResolucaoDiretoria) {
		this.assuntoResolucaoDiretoria = assuntoResolucaoDiretoria;
	}

	

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getImovelSituacaoTipo() {
		return imovelSituacaoTipo;
	}

	public void setImovelSituacaoTipo(String imovelSituacaoTipo) {
		this.imovelSituacaoTipo = imovelSituacaoTipo;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getNumeroResolucaoDiretoria() {
		return numeroResolucaoDiretoria;
	}

	public void setNumeroResolucaoDiretoria(String numeroResolucaoDiretoria) {
		this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
	}

	public String getPercentualDescontoAcrescimoMulta() {
		return percentualDescontoAcrescimoMulta;
	}

	public void setPercentualDescontoAcrescimoMulta(String percentualDescontoAcrescimoMulta) {
		this.percentualDescontoAcrescimoMulta = percentualDescontoAcrescimoMulta;
	}
	
	public String getPercentualDescontoAcrescimoJurosMora() {
		return percentualDescontoAcrescimoJurosMora;
	}

	public void setPercentualDescontoAcrescimoJurosMora(String percentualDescontoAcrescimoJurosMora) {
		this.percentualDescontoAcrescimoJurosMora = percentualDescontoAcrescimoJurosMora;
	}
	
	public String getPercentualDescontoAcrescimoAtualizacaoMonetaria() {
		return percentualDescontoAcrescimoAtualizacaoMonetaria;
	}

	public void setPercentualDescontoAcrescimoAtualizacaoMonetaria(String percentualDescontoAcrescimoAtualizacaoMonetaria) {
		this.percentualDescontoAcrescimoAtualizacaoMonetaria = percentualDescontoAcrescimoAtualizacaoMonetaria;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getPercentualDescontoComRestabelecimentoAntiguidade() {
		return percentualDescontoComRestabelecimentoAntiguidade;
	}

	public void setPercentualDescontoComRestabelecimentoAntiguidade(
			String percentualDescontoComRestabelecimentoAntiguidade) {
		this.percentualDescontoComRestabelecimentoAntiguidade = percentualDescontoComRestabelecimentoAntiguidade;
	}

	public String getPercentualDescontoComRestabelecimentoInatividade() {
		return percentualDescontoComRestabelecimentoInatividade;
	}

	public void setPercentualDescontoComRestabelecimentoInatividade(
			String percentualDescontoComRestabelecimentoInatividade) {
		this.percentualDescontoComRestabelecimentoInatividade = percentualDescontoComRestabelecimentoInatividade;
	}

	public String getPercentualDescontoSemRestabelecimentoAntiguidade() {
		return percentualDescontoSemRestabelecimentoAntiguidade;
	}

	public void setPercentualDescontoSemRestabelecimentoAntiguidade(
			String percentualDescontoSemRestabelecimentoAntiguidade) {
		this.percentualDescontoSemRestabelecimentoAntiguidade = percentualDescontoSemRestabelecimentoAntiguidade;
	}

	public String getPercentualDescontoSemRestabelecimentoInatividade() {
		return percentualDescontoSemRestabelecimentoInatividade;
	}

	public void setPercentualDescontoSemRestabelecimentoInatividade(
			String percentualDescontoSemRestabelecimentoInatividade) {
		this.percentualDescontoSemRestabelecimentoInatividade = percentualDescontoSemRestabelecimentoInatividade;
	}

	public String getQuantidadeMaximaMesesInatividade() {
		return quantidadeMaximaMesesInatividade;
	}

	public void setQuantidadeMaximaMesesInatividade(
			String quantidadeMaximaMesesInatividade) {
		this.quantidadeMaximaMesesInatividade = quantidadeMaximaMesesInatividade;
	}

	public String getQuantidadeMinimaMesesDebito() {
		return quantidadeMinimaMesesDebito;
	}

	public void setQuantidadeMinimaMesesDebito(String quantidadeMinimaMesesDebito) {
		this.quantidadeMinimaMesesDebito = quantidadeMinimaMesesDebito;
	}

	public String getPercentualDescontoAtivo() {
		return percentualDescontoAtivo;
	}

	public void setPercentualDescontoAtivo(String percentualDescontoAtivo) {
		this.percentualDescontoAtivo = percentualDescontoAtivo;
	}

	public String getPercentualTarifaMinimaPrestacao() {
		return percentualTarifaMinimaPrestacao;
	}

	public void setPercentualTarifaMinimaPrestacao(
			String percentualTarifaMinimaPrestacao) {
		this.percentualTarifaMinimaPrestacao = percentualTarifaMinimaPrestacao;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getIndicadorParcelarChequeDevolvido() {
		return indicadorParcelarChequeDevolvido;
	}

	public void setIndicadorParcelarChequeDevolvido(
			String indicadorParcelarChequeDevolvido) {
		this.indicadorParcelarChequeDevolvido = indicadorParcelarChequeDevolvido;
	}

	public String getIndicadorParcelarSancoesMaisDeUmaConta() {
		return indicadorParcelarSancoesMaisDeUmaConta;
	}

	public void setIndicadorParcelarSancoesMaisDeUmaConta(
			String indicadorParcelarSancoesMaisDeUmaConta) {
		this.indicadorParcelarSancoesMaisDeUmaConta = indicadorParcelarSancoesMaisDeUmaConta;
	}

	public String getPercentualVariacaoConsumoMedio() {
		return percentualVariacaoConsumoMedio;
	}

	public void setPercentualVariacaoConsumoMedio(
			String percentualVariacaoConsumoMedio) {
		this.percentualVariacaoConsumoMedio = percentualVariacaoConsumoMedio;
	}

	public String getAnoMesReferenciaLimiteInferior() {
		return anoMesReferenciaLimiteInferior;
	}

	public void setAnoMesReferenciaLimiteInferior(
			String anoMesReferenciaLimiteInferior) {
		this.anoMesReferenciaLimiteInferior = anoMesReferenciaLimiteInferior;
	}

	public String getAnoMesReferenciaLimiteSuperior() {
		return anoMesReferenciaLimiteSuperior;
	}

	public void setAnoMesReferenciaLimiteSuperior(
			String anoMesReferenciaLimiteSuperior) {
		this.anoMesReferenciaLimiteSuperior = anoMesReferenciaLimiteSuperior;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getIndicadorAlertaParcelaMinima() {
		return indicadorAlertaParcelaMinima;
	}

	public void setIndicadorAlertaParcelaMinima(String indicadorAlertaParcelaMinima) {
		this.indicadorAlertaParcelaMinima = indicadorAlertaParcelaMinima;
	}

	public String getIndicadorEntradaMinima() {
		return indicadorEntradaMinima;
	}

	public void setIndicadorEntradaMinima(String indicadorEntradaMinima) {
		this.indicadorEntradaMinima = indicadorEntradaMinima;
	}

	public String getIndicadorRetroativoTarifaSocial() {
		return indicadorRetroativoTarifaSocial;
	}

	public void setIndicadorRetroativoTarifaSocial(
			String indicadorRetroativoTarifaSocial) {
		this.indicadorRetroativoTarifaSocial = indicadorRetroativoTarifaSocial;
	}

	public String getNumeroAreaConstruida() {
		return numeroAreaConstruida;
	}

	public void setNumeroAreaConstruida(String numeroAreaConstruida) {
		this.numeroAreaConstruida = numeroAreaConstruida;
	}

	public String getNumeroConsumoEconomia() {
		return numeroConsumoEconomia;
	}

	public void setNumeroConsumoEconomia(String numeroConsumoEconomia) {
		this.numeroConsumoEconomia = numeroConsumoEconomia;
	}

	public String getParcelaQuantidadeMinimaFatura() {
		return parcelaQuantidadeMinimaFatura;
	}

	public void setParcelaQuantidadeMinimaFatura(
			String parcelaQuantidadeMinimaFatura) {
		this.parcelaQuantidadeMinimaFatura = parcelaQuantidadeMinimaFatura;
	}

	public String getPercentualDescontoTarifaSocial() {
		return percentualDescontoTarifaSocial;
	}

	public void setPercentualDescontoTarifaSocial(String percentualDescontoTarifaSocial) {
		this.percentualDescontoTarifaSocial = percentualDescontoTarifaSocial;
	}

	public String getPercentualDescontoSancao() {
		return percentualDescontoSancao;
	}

	public void setPercentualDescontoSancao(String percentualDescontoSancao) {
		this.percentualDescontoSancao = percentualDescontoSancao;
	}

	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getPercentualEntradaSugerida() {
		return percentualEntradaSugerida;
	}

	public void setPercentualEntradaSugerida(String percentualEntradaSugerida) {
		this.percentualEntradaSugerida = percentualEntradaSugerida;
	}

	public String getDataLimiteDescontoPagamentoAVista() {
		return dataLimiteDescontoPagamentoAVista;
	}

	public void setDataLimiteDescontoPagamentoAVista(
			String dataLimiteDescontoPagamentoAVista) {
		this.dataLimiteDescontoPagamentoAVista = dataLimiteDescontoPagamentoAVista;
	}


}

