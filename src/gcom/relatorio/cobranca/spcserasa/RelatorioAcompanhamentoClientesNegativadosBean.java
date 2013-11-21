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
package gcom.relatorio.cobranca.spcserasa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
 * @author Yara Taciane 
 * @created 17 de março de 2008
 * @version 1.0
 */

public class RelatorioAcompanhamentoClientesNegativadosBean implements RelatorioBean {
	  
	private JRBeanCollectionDataSource arrayJRDadosParcelamento;
	private ArrayList arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	
	private JRBeanCollectionDataSource arrayJRParametrosSelecionados;
	private ArrayList arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	
	private String idNegativador;
	private String negativador;
	
    private String nomeCliente;
    private String matricula;
    private String cpfCnpj;
    private Double valorNegativado;
    
    private BigDecimal valorPago;
    private BigDecimal valorParceladoEntrada;
    private BigDecimal valorParcelado;
    private BigDecimal valorCancelado;
    private BigDecimal valorPendente;
    
    private String situacao;
	private String periodoEnvioNegativacao;
		
    private String localidade;
    private String idLocalidade;
    
    private String motivosRejeicao;
    
//    private BigDecimal valorParcelado;
//    private BigDecimal valorParceladoEntrada;
//    private BigDecimal valorParceladoEntradaPago;
//    private Short numeroPrestacoes;
//    private Short numeroPrestacoesCobradasPagas;
//    private BigDecimal valorParceladoCobradoPago;    
//    private Short numeroPrestacoesCobradasNaoPagas;
//    private BigDecimal valorParceladoCobradoNaoPago;
//    private Short numeroPrestacoesNaoCobradas;
//    private BigDecimal valorParceladoNaoCobrado;
//    private BigDecimal valorParceladoCancelado;
//    
//    private BigDecimal valorParceladoEntradaPagoTotalParc;
//    private Short numeroPrestacoesTotalParc;
//    private Short numeroPrestacoesCobradasPagasTotalParc;
//    private BigDecimal valorParceladoCobradoPagoTotalParc;    
//    private Short numeroPrestacoesCobradasNaoPagasTotalParc;
//    private BigDecimal valorParceladoCobradoNaoPagoTotalParc;
//    private Short numeroPrestacoesNaoCobradasTotalParc;
//    private BigDecimal valorParceladoNaoCobradoTotalParc;
//    private BigDecimal valorParceladoCanceladoTotalParc;
    
	private BigDecimal valorParceladoTotalLoc;
	private BigDecimal valorParceladoEntradaTotalLoc;
    private BigDecimal valorParceladoEntradaPagoTotalLoc;
    private Integer numeroPrestacoesTotalLoc;
    private Integer numeroPrestacoesCobradasPagasTotalLoc;
    private BigDecimal valorParceladoCobradoPagoTotalLoc;    
    private Integer numeroPrestacoesCobradasNaoPagasTotalLoc;
    private BigDecimal valorParceladoCobradoNaoPagoTotalLoc;
    private Integer numeroPrestacoesNaoCobradasTotalLoc;
    private BigDecimal valorParceladoNaoCobradoTotalLoc;
    private BigDecimal valorParceladoCanceladoTotalLoc;
    private String indicadorExisteDadosParcelamento;
    
    private String gerenciaRegional;
    private String idGerenciaRegional;
    private String unidadeNegocio;
    private String idUnidadeNegocio;

	public RelatorioAcompanhamentoClientesNegativadosBean(
			String nomeCliente, 
			String matricula, 
			String cpfCnpj, 
			Double valorNegativado, 
			BigDecimal valorPago,
			BigDecimal valorCancelado, 
			BigDecimal valorPendente, 
			String situacao, 
			String periodoEnvioNegativacao, 
			String localidade, 
			String idLocalidade,
			String idNegativador, 
			String negativador, 
			Collection colecaoDadosParcelamento,
		    String indicadorExisteDadosParcelamento,
		    RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean parametrosSelecionadosBean,
		    BigDecimal valorParceladoTotalLoc,
		    BigDecimal valorParceladoEntradaTotalLoc,
			BigDecimal valorParceladoEntradaPagoTotalLoc, 
			Integer numeroPrestacoesTotalLoc, 
			Integer numeroPrestacoesCobradasPagasTotalLoc,
			BigDecimal valorParceladoCobradoPagoTotalLoc, 
			Integer numeroPrestacoesCobradasNaoPagasTotalLoc,
			BigDecimal valorParceladoCobradoNaoPagoTotalLoc,
			Integer numeroPrestacoesNaoCobradasTotalLoc,
			BigDecimal valorParceladoNaoCobradoTotalLoc, 
			BigDecimal valorParceladoCanceladoTotalLoc
			) {
		super();
		// TODO Auto-generated constructor stub
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.cpfCnpj = cpfCnpj;
		this.valorNegativado = valorNegativado;
		this.valorPago = valorPago;
		this.valorCancelado = valorCancelado;
		this.valorPendente = valorPendente;
		this.situacao = situacao;
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
		this.localidade = localidade;
		this.idLocalidade = idLocalidade;
		this.idNegativador = idNegativador;
		this.negativador = negativador;
		this.indicadorExisteDadosParcelamento = indicadorExisteDadosParcelamento;
		
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean = new ArrayList();
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean.addAll(colecaoDadosParcelamento);
		this.arrayJRDadosParcelamento = new JRBeanCollectionDataSource(this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean);
		
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean = new ArrayList();
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean.add(parametrosSelecionadosBean);
		this.arrayJRParametrosSelecionados = new JRBeanCollectionDataSource(this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean);
		
		this.valorParceladoTotalLoc = valorParceladoTotalLoc;	
		this.valorParceladoEntradaTotalLoc = valorParceladoEntradaTotalLoc;
		this.valorParceladoEntradaPagoTotalLoc = valorParceladoEntradaPagoTotalLoc;
		this.numeroPrestacoesTotalLoc = numeroPrestacoesTotalLoc;
		this.numeroPrestacoesCobradasPagasTotalLoc = numeroPrestacoesCobradasPagasTotalLoc;
		this.valorParceladoCobradoPagoTotalLoc = valorParceladoCobradoPagoTotalLoc;
		this.numeroPrestacoesCobradasNaoPagasTotalLoc = numeroPrestacoesCobradasNaoPagasTotalLoc;
		this.valorParceladoCobradoNaoPagoTotalLoc = valorParceladoCobradoNaoPagoTotalLoc;
		this.numeroPrestacoesNaoCobradasTotalLoc = numeroPrestacoesNaoCobradasTotalLoc;
		this.valorParceladoNaoCobradoTotalLoc = valorParceladoNaoCobradoTotalLoc;
		this.valorParceladoCanceladoTotalLoc = valorParceladoCanceladoTotalLoc;
	}

	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj O cpfCnpj a ser setado.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo periodoEnvioNegativacao.
	 */
	public String getPeriodoEnvioNegativacao() {
		return periodoEnvioNegativacao;
	}

	/**
	 * @param periodoEnvioNegativacao O periodoEnvioNegativacao a ser setado.
	 */
	public void setPeriodoEnvioNegativacao(String periodoEnvioNegativacao) {
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo valorCancelado.
	 */
	public BigDecimal getValorCancelado() {
		return valorCancelado;
	}

	/**
	 * @param valorCancelado O valorCancelado a ser setado.
	 */
	public void setValorCancelado(BigDecimal valorCancelado) {
		this.valorCancelado = valorCancelado;
	}

	
	/**
	 * @return Retorna o campo valorNegativado.
	 */
	public Double getValorNegativado() {
		return valorNegativado;
	}

	/**
	 * @param valorNegativado O valorNegativado a ser setado.
	 */
	public void setValorNegativado(Double valorNegativado) {
		this.valorNegativado = valorNegativado;
	}

	/**
	 * @return Retorna o campo valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago O valorPago a ser setado.
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * @return Retorna o campo valorParcelado.
	 */
	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	/**
	 * @param valorParcelado O valorParcelado a ser setado.
	 */
	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	/**
	 * @return Retorna o campo valorPendente.
	 */
	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	/**
	 * @param valorPendente O valorPendente a ser setado.
	 */
	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
	}



	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo valorParceladoEntrada.
	 */
	public BigDecimal getValorParceladoEntrada() {
		return valorParceladoEntrada;
	}

	/**
	 * @param valorParceladoEntrada O valorParceladoEntrada a ser setado.
	 */
	public void setValorParceladoEntrada(BigDecimal valorParceladoEntrada) {
		this.valorParceladoEntrada = valorParceladoEntrada;
	}
	
	/**
	 * @return Retorna o campo motivosEncerramento.
	 */
	public String getMotivosRejeicao() {
		return motivosRejeicao;
	}

	/**
	 * @param motivosEncerramento O motivosEncerramento a ser setado.
	 */
	public void setMotivosRejeicao(String motivosEncerramento) {
		this.motivosRejeicao = motivosEncerramento;
	}

	

	public Integer getNumeroPrestacoesCobradasNaoPagasTotalLoc() {
		return numeroPrestacoesCobradasNaoPagasTotalLoc;
	}

	public void setNumeroPrestacoesCobradasNaoPagasTotalLoc(
			Integer numeroPrestacoesCobradasNaoPagasTotalLoc) {
		this.numeroPrestacoesCobradasNaoPagasTotalLoc = numeroPrestacoesCobradasNaoPagasTotalLoc;
	}

	public Integer getNumeroPrestacoesCobradasPagasTotalLoc() {
		return numeroPrestacoesCobradasPagasTotalLoc;
	}

	public void setNumeroPrestacoesCobradasPagasTotalLoc(
			Integer numeroPrestacoesCobradasPagasTotalLoc) {
		this.numeroPrestacoesCobradasPagasTotalLoc = numeroPrestacoesCobradasPagasTotalLoc;
	}

	public Integer getNumeroPrestacoesNaoCobradasTotalLoc() {
		return numeroPrestacoesNaoCobradasTotalLoc;
	}

	public void setNumeroPrestacoesNaoCobradasTotalLoc(
			Integer numeroPrestacoesNaoCobradasTotalLoc) {
		this.numeroPrestacoesNaoCobradasTotalLoc = numeroPrestacoesNaoCobradasTotalLoc;
	}

	public Integer getNumeroPrestacoesTotalLoc() {
		return numeroPrestacoesTotalLoc;
	}

	public void setNumeroPrestacoesTotalLoc(Integer numeroPrestacoesTotalLoc) {
		this.numeroPrestacoesTotalLoc = numeroPrestacoesTotalLoc;
	}

	public BigDecimal getValorParceladoCanceladoTotalLoc() {
		return valorParceladoCanceladoTotalLoc;
	}

	public void setValorParceladoCanceladoTotalLoc(
			BigDecimal valorParceladoCanceladoTotalLoc) {
		this.valorParceladoCanceladoTotalLoc = valorParceladoCanceladoTotalLoc;
	}

	public BigDecimal getValorParceladoCobradoNaoPagoTotalLoc() {
		return valorParceladoCobradoNaoPagoTotalLoc;
	}

	public void setValorParceladoCobradoNaoPagoTotalLoc(
			BigDecimal valorParceladoCobradoNaoPagoTotalLoc) {
		this.valorParceladoCobradoNaoPagoTotalLoc = valorParceladoCobradoNaoPagoTotalLoc;
	}

	public BigDecimal getValorParceladoCobradoPagoTotalLoc() {
		return valorParceladoCobradoPagoTotalLoc;
	}

	public void setValorParceladoCobradoPagoTotalLoc(
			BigDecimal valorParceladoCobradoPagoTotalLoc) {
		this.valorParceladoCobradoPagoTotalLoc = valorParceladoCobradoPagoTotalLoc;
	}

	public BigDecimal getValorParceladoEntradaPagoTotalLoc() {
		return valorParceladoEntradaPagoTotalLoc;
	}

	public void setValorParceladoEntradaPagoTotalLoc(
			BigDecimal valorParceladoEntradaPagoTotalLoc) {
		this.valorParceladoEntradaPagoTotalLoc = valorParceladoEntradaPagoTotalLoc;
	}

	public BigDecimal getValorParceladoNaoCobradoTotalLoc() {
		return valorParceladoNaoCobradoTotalLoc;
	}

	public void setValorParceladoNaoCobradoTotalLoc(
			BigDecimal valorParceladoNaoCobradoTotalLoc) {
		this.valorParceladoNaoCobradoTotalLoc = valorParceladoNaoCobradoTotalLoc;
	}

	public BigDecimal getValorParceladoEntradaTotalLoc() {
		return valorParceladoEntradaTotalLoc;
	}

	public void setValorParceladoEntradaTotalLoc(
			BigDecimal valorParceladoEntradaTotalLoc) {
		this.valorParceladoEntradaTotalLoc = valorParceladoEntradaTotalLoc;
	}

	public BigDecimal getValorParceladoTotalLoc() {
		return valorParceladoTotalLoc;
	}

	public void setValorParceladoTotalLoc(BigDecimal valorParceladoTotalLoc) {
		this.valorParceladoTotalLoc = valorParceladoTotalLoc;
	}

	public JRBeanCollectionDataSource getArrayJRDadosParcelamento() {
		return arrayJRDadosParcelamento;
	}

	public void setArrayJRDadosParcelamento(
			JRBeanCollectionDataSource arrayJRDadosParcelamento) {
		this.arrayJRDadosParcelamento = arrayJRDadosParcelamento;
	}

	public ArrayList getArrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean() {
		return arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	}

	public void setArrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean(
			ArrayList arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean) {
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean = arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	}


	public String getIndicadorExisteDadosParcelamento() {
		return indicadorExisteDadosParcelamento;
	}

	public void setIndicadorExisteDadosParcelamento(
			String indicadorExisteDadosParcelamento) {
		this.indicadorExisteDadosParcelamento = indicadorExisteDadosParcelamento;
	}

	public JRBeanCollectionDataSource getArrayJRParametrosSelecionados() {
		return arrayJRParametrosSelecionados;
	}

	public void setArrayJRParametrosSelecionados(
			JRBeanCollectionDataSource arrayJRParametrosSelecionados) {
		this.arrayJRParametrosSelecionados = arrayJRParametrosSelecionados;
	}

	public ArrayList getArrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean() {
		return arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	}

	public void setArrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(
			ArrayList arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean) {
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean = arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
}
