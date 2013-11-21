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
package gcom.arrecadacao.aviso.bean;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;


/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos avisos bancários de um
 * determinado movimento do arrecadador 
 *
 * @author Raphael Rossiter
 * @date 08/03/2006
 */
public class AvisoBancarioHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idMovimentoArrecadador;
	private Integer idAvisoBancario;
	private Date dataLancamento;
	private Short numeroSequencial;
	private short indicadorCreditoDebito;
	private String descricaoIndicadorCreditoDebito;
	private Date dataRealizada;
	private BigDecimal valorRealizado;
	private BigDecimal valorArrecadacao;
	private BigDecimal valorTotalPagamento;
	private String situacao;
	
	private AvisoBancario avisoBancario;
	private String tipoAviso;
	private Date dataLancamentoInicial;
	private Date dataLancamentoFinal;
	private int anoMesReferenciaArrecadacaoInicial;
	private int anoMesReferenciaArrecadacaoFinal;
	private BigDecimal valorPrevistoInicial;
	private BigDecimal valorPrevistoFinal;
	private Date dataPrevistaInicial;
	private Date dataPrevistaFinal;
	private BigDecimal valorRealizadoInicial;
	private BigDecimal valorRealizadoFinal;
	private Date dataRealizadaInicial;
	private Date dataRealizadaFinal;
	private Integer idContaBancaria;
	private Short codigoAgenteArrecadador;
	private String nomeCliente;
	private String descricaoArrecadacaoForma;
	private Integer idBancoContaBancaria;
	private String codigoAgenciaContaBancaria;
	private String numeroContaBancaria;
	private BigDecimal valorSomatorioDeducoes;
	private BigDecimal valorSomatorioAcertosArrecadacao;
	private BigDecimal valorSomatorioAcertosDevolucao;
	private BigDecimal valorDiferencaArrecadacaoDevolucao;
	
	private BigDecimal valorArrecadacaoCalculado;
	private BigDecimal valorArrecadacaoInformado;
	private BigDecimal valorDevolucaoCalculado;
	private BigDecimal valorDevolucaoInformado;
	
	private Collection<Pagamento> colecaoPagamentos;
	private Collection<Devolucao> colecaoDevolucoes;
	
	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}

	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public AvisoBancarioHelper() {
	}

	public AvisoBancarioHelper(
			Integer idAvisoBancario,
			Date dataLancamento,
			Short numeroSequencial,
			short indicadorCreditoDebito,
			Date dataRealizada,
			BigDecimal valorRealizado, BigDecimal valorArrecadacao,
			BigDecimal valorTotalPagamento, String situacao,
			BigDecimal valorArrecadacaoCalculado, BigDecimal valorArrecadacaoInformado,
			BigDecimal valorDevolucaoCalculado, BigDecimal valorDevolucaoInformado) {
		
		this.idAvisoBancario = idAvisoBancario; 
		this.dataLancamento = dataLancamento;
		this.numeroSequencial = numeroSequencial;
		this.indicadorCreditoDebito = indicadorCreditoDebito;
		this.dataRealizada = dataRealizada;
		this.valorRealizado = valorRealizado;
		this.valorArrecadacao = valorArrecadacao;
		this.valorTotalPagamento = valorTotalPagamento;
		this.situacao = situacao;
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public Integer getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(Integer idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public short getIndicadorCreditoDebito() {
		return indicadorCreditoDebito;
	}

	public void setIndicadorCreditoDebito(short indicadorCreditoDebito) {
		this.indicadorCreditoDebito = indicadorCreditoDebito;
	}

	public Short getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(Short numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValorArrecadacao() {
		return valorArrecadacao;
	}

	public void setValorArrecadacao(BigDecimal valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	public BigDecimal getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public BigDecimal getValorTotalPagamento() {
		return valorTotalPagamento;
	}

	public void setValorTotalPagamento(BigDecimal valorTotalPagamento) {
		this.valorTotalPagamento = valorTotalPagamento;
	}

	public String getTipoAviso() {
		return tipoAviso;
	}

	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}

	public Collection<Pagamento> getColecaoPagamentos() {
		return colecaoPagamentos;
	}

	public void setColecaoPagamentos(Collection<Pagamento> colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}

	public Collection<Devolucao> getColecaoDevolucoes() {
		return colecaoDevolucoes;
	}

	public void setColecaoDevolucoes(Collection<Devolucao> colecaoDevolucoes) {
		this.colecaoDevolucoes = colecaoDevolucoes;
	}

	public String getDescricaoIndicadorCreditoDebito() {
		return descricaoIndicadorCreditoDebito;
	}

	public void setDescricaoIndicadorCreditoDebito(
			String descricaoIndicadorCreditoDebito) {
		this.descricaoIndicadorCreditoDebito = descricaoIndicadorCreditoDebito;
	}

	public Integer getIdMovimentoArrecadador() {
		return idMovimentoArrecadador;
	}

	public void setIdMovimentoArrecadador(Integer idMovimentoArrecadador) {
		this.idMovimentoArrecadador = idMovimentoArrecadador;
	}

	public int getAnoMesReferenciaArrecadacaoFinal() {
		return anoMesReferenciaArrecadacaoFinal;
	}

	public void setAnoMesReferenciaArrecadacaoFinal(
			int anoMesReferenciaArrecadacaoFinal) {
		this.anoMesReferenciaArrecadacaoFinal = anoMesReferenciaArrecadacaoFinal;
	}

	public int getAnoMesReferenciaArrecadacaoInicial() {
		return anoMesReferenciaArrecadacaoInicial;
	}

	public void setAnoMesReferenciaArrecadacaoInicial(
			int anoMesReferenciaArrecadacaoInicial) {
		this.anoMesReferenciaArrecadacaoInicial = anoMesReferenciaArrecadacaoInicial;
	}

	public Date getDataLancamentoFinal() {
		return dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(Date dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

	public Date getDataLancamentoInicial() {
		return dataLancamentoInicial;
	}

	public void setDataLancamentoInicial(Date dataLancamentoInicial) {
		this.dataLancamentoInicial = dataLancamentoInicial;
	}

	public Date getDataPrevistaFinal() {
		return dataPrevistaFinal;
	}

	public void setDataPrevistaFinal(Date dataPrevistaFinal) {
		this.dataPrevistaFinal = dataPrevistaFinal;
	}

	public Date getDataPrevistaInicial() {
		return dataPrevistaInicial;
	}

	public void setDataPrevistaInicial(Date dataPrevistaInicial) {
		this.dataPrevistaInicial = dataPrevistaInicial;
	}

	public Date getDataRealizadaFinal() {
		return dataRealizadaFinal;
	}

	public void setDataRealizadaFinal(Date dataRealizadaFinal) {
		this.dataRealizadaFinal = dataRealizadaFinal;
	}

	public Date getDataRealizadaInicial() {
		return dataRealizadaInicial;
	}

	public void setDataRealizadaInicial(Date dataRealizadaInicial) {
		this.dataRealizadaInicial = dataRealizadaInicial;
	}


	public BigDecimal getValorRealizadoFinal() {
		return valorRealizadoFinal;
	}

	public void setValorRealizadoFinal(BigDecimal valorRealizadoFinal) {
		this.valorRealizadoFinal = valorRealizadoFinal;
	}

	public BigDecimal getValorRealizadoInicial() {
		return valorRealizadoInicial;
	}

	public void setValorRealizadoInicial(BigDecimal valorRealizadoInicial) {
		this.valorRealizadoInicial = valorRealizadoInicial;
	}

	public BigDecimal getValorPrevistoFinal() {
		return valorPrevistoFinal;
	}

	public void setValorPrevistoFinal(BigDecimal valorPrevistoFinal) {
		this.valorPrevistoFinal = valorPrevistoFinal;
	}

	public BigDecimal getValorPrevistoInicial() {
		return valorPrevistoInicial;
	}

	public void setValorPrevistoInicial(BigDecimal valorPrevistoInicial) {
		this.valorPrevistoInicial = valorPrevistoInicial;
	}

	public Integer getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(Integer idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public Short getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}

	public void setCodigoAgenteArrecadador(Short codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public BigDecimal getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}

	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(BigDecimal valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public BigDecimal getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}

	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(BigDecimal valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public BigDecimal getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}

	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(BigDecimal valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public BigDecimal getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}

	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(BigDecimal valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}

	public String getCodigoAgenciaContaBancaria() {
		return codigoAgenciaContaBancaria;
	}

	public void setCodigoAgenciaContaBancaria(String codigoAgenciaContaBancaria) {
		this.codigoAgenciaContaBancaria = codigoAgenciaContaBancaria;
	}

	public Integer getIdBancoContaBancaria() {
		return idBancoContaBancaria;
	}

	public void setIdBancoContaBancaria(Integer idBancoContaBancaria) {
		this.idBancoContaBancaria = idBancoContaBancaria;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroContaBancaria() {
		return numeroContaBancaria;
	}

	public void setNumeroContaBancaria(String numeroContaBancaria) {
		this.numeroContaBancaria = numeroContaBancaria;
	}

	public String getCodigoNomeArrecadador(){
		return getCodigoAgenteArrecadador()+ " - " + getNomeCliente(); 
	}
	
	public String getCodigoDescricaoArrecadacaoForma() {
		if (descricaoArrecadacaoForma != null && !descricaoArrecadacaoForma.equals("")) {
			return avisoBancario.getArrecadacaoForma().getId() + " - " + descricaoArrecadacaoForma;
		}
		return "";
	}

	public BigDecimal getValorSomatorioDeducoes() {
		return valorSomatorioDeducoes;
	}

	public void setValorSomatorioDeducoes(BigDecimal valorSomatorioDeducoes) {
		this.valorSomatorioDeducoes = valorSomatorioDeducoes;
	}

	public BigDecimal getValorSomatorioAcertosArrecadacao() {
		return valorSomatorioAcertosArrecadacao;
	}

	public void setValorSomatorioAcertosArrecadacao(
			BigDecimal valorSomatorioAcertosArrecadacao) {
		this.valorSomatorioAcertosArrecadacao = valorSomatorioAcertosArrecadacao;
	}

	public BigDecimal getValorSomatorioAcertosDevolucao() {
		return valorSomatorioAcertosDevolucao;
	}

	public void setValorSomatorioAcertosDevolucao(
			BigDecimal valorSomatorioAcertosDevolucao) {
		this.valorSomatorioAcertosDevolucao = valorSomatorioAcertosDevolucao;
	}

	public BigDecimal getValorDiferencaArrecadacaoDevolucao() {
		return valorDiferencaArrecadacaoDevolucao;
	}

	public void setValorDiferencaArrecadacaoDevolucao(
			BigDecimal valorDiferencaArrecadacaoDevolucao) {
		this.valorDiferencaArrecadacaoDevolucao = valorDiferencaArrecadacaoDevolucao;
	}

	public String getDescricaoArrecadacaoForma() {
		return descricaoArrecadacaoForma;
	}

	public void setDescricaoArrecadacaoForma(String descricaoArrecadacaoForma) {
		this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
	}

}
