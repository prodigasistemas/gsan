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

public class RelatorioNegativacoesExcluidasBean implements RelatorioBean {

	private String idNegativador;

	private String negativador;

	private String nomeCliente;

	private String matricula;

	private String cpfCnpj;

	private BigDecimal valorNegativado;

	private String motivoExclusao;

	private String periodoEnvioNegativacao;

	private String dataSituacaoDebito;

	private String dataExclusao;

	private String localidade;

	private String idLocalidade;
	
	private BigDecimal valorParceladoEntrada;
	
	private BigDecimal valorParcelado;	
	
	private BigDecimal valorPago;

	private BigDecimal valorParceladoPago;
	
	private BigDecimal valorParceladoEntradaPago;

	public RelatorioNegativacoesExcluidasBean(String nomeCliente,
			String matricula, String cpfCnpj, BigDecimal valorNegativado,
			String motivoExclusao, String periodoEnvioNegativacao,
			String localidade, String idLocalidade, String idNegativador,
			String negativador, String dataSituacaoDebito, String dataExclusao,
			BigDecimal valorParceladoEntrada,
			BigDecimal valorParcelado, BigDecimal valorPago,
			BigDecimal valorParceladoPago, BigDecimal valorParceladoEntradaPago) {
		super();
		// TODO Auto-generated constructor stub
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.cpfCnpj = cpfCnpj;
		this.valorNegativado = valorNegativado;
		this.motivoExclusao = motivoExclusao;
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
		this.localidade = localidade;
		this.idLocalidade = idLocalidade;
		this.idNegativador = idNegativador;
		this.negativador = negativador;
		this.dataSituacaoDebito = dataSituacaoDebito;
		this.dataExclusao = dataExclusao;
		this.valorParceladoEntrada = valorParceladoEntrada;
		this.valorParcelado = valorParcelado;
		this.valorPago = valorPago;
		this.valorParceladoPago = valorParceladoPago;
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
	}

	/**
	 * @return Retorna o campo valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago
	 *            O valorPago a ser setado.
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
	 * @param valorParcelado
	 *            O valorParcelado a ser setado.
	 */
	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            O cpfCnpj a ser setado.
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
	 * @param idLocalidade
	 *            O idLocalidade a ser setado.
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
	 * @param matricula
	 *            O matricula a ser setado.
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
	 * @param nomeCliente
	 *            O nomeCliente a ser setado.
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
	 * @param localidade
	 *            O localidade a ser setado.
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
	 * @param periodoEnvioNegativacao
	 *            O periodoEnvioNegativacao a ser setado.
	 */
	public void setPeriodoEnvioNegativacao(String periodoEnvioNegativacao) {
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
	}

	/**
	 * @return Retorna o campo valorNegativado.
	 */
	public BigDecimal getValorNegativado() {
		return valorNegativado;
	}

	/**
	 * @param valorNegativado
	 *            O valorNegativado a ser setado.
	 */
	public void setValorNegativado(BigDecimal valorNegativado) {
		this.valorNegativado = valorNegativado;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador
	 *            O idNegativador a ser setado.
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
	 * @param negativador
	 *            O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo motivoExclusao.
	 */
	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	/**
	 * @param motivoExclusao
	 *            O motivoExclusao a ser setado.
	 */
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public String getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito
	 *            O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(String dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	/**
	 * @return Retorna o campo dataExclusao.
	 */
	public String getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * @param dataExclusao
	 *            O dataExclusao a ser setado.
	 */
	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
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

	public BigDecimal getValorParceladoEntradaPago() {
		return valorParceladoEntradaPago;
	}

	public void setValorParceladoEntradaPago(BigDecimal valorParceladoEntradaPago) {
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
	}

	public BigDecimal getValorParceladoPago() {
		return valorParceladoPago;
	}

	public void setValorParceladoPago(BigDecimal valorParceladoPago) {
		this.valorParceladoPago = valorParceladoPago;
	}

}
