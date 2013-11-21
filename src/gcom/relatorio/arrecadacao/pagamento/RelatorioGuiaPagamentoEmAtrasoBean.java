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
package gcom.relatorio.arrecadacao.pagamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Flávio Leonardo
 * @created 28/01/2009
 */
public class RelatorioGuiaPagamentoEmAtrasoBean implements RelatorioBean {

	private String financiamentoTipoId;
	
	private String financiamentoTipoDescricao;

	private String vencimentoInicial;

	private String vencimentoFinal;

	private String referenciaInicialContabil;

	private String referenciaFinalContabil;
	
	private String clienteId;
	
	private String clienteNome;
	
	private String clienteCpf;
	
	private String clienteEndereco;
	
	private String emissao;
	
	private String pagamento;
	
	private String valor;
	
	private String referencia;
	
	private String parcelas;
	
	private String parcelasTotal;
	
	private String vencimento;
	
	private String imovel;
	
	private String debitoTipoDescricao;

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioGuiaPagamentoEmAtrasoBean(String financiamentoTipoId, String financiamentoTipoDescricao,
			String vencimentoInicial, String vencimentoFinal, String referenciaInicialContabil,
			String referenciaFinalContabil, String clienteId, String clienteNome, String clienteCpf,
			String clienteEndereco, String emissao, String pagamento, String valor, String referencia,
			String parcelas, String parcelasTotal, String vencimento) {
		this.financiamentoTipoId = financiamentoTipoId;
		this.financiamentoTipoDescricao = financiamentoTipoDescricao;
		this.vencimentoInicial = vencimentoInicial;
		this.vencimentoFinal = vencimentoFinal;
		this.referenciaInicialContabil = referenciaInicialContabil;
		this.referenciaFinalContabil = referenciaFinalContabil;
		
		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteCpf = clienteCpf;
		this.clienteEndereco = clienteEndereco;
		this.emissao = emissao;
		
		this.pagamento = pagamento;
		this.valor = valor;
		this.referencia = referencia;
		this.parcelas = parcelas;
		this.parcelasTotal = parcelasTotal;
		this.vencimento = vencimento;

	}
	
	public RelatorioGuiaPagamentoEmAtrasoBean(RelatorioGuiaPagamentoEmAtrasoHelper helper) {
		this.financiamentoTipoId = helper.getFinanciamentoTipoId();
		this.financiamentoTipoDescricao = helper.getFinanciamentoTipoDescricao();
		this.vencimentoInicial = helper.getVencimentoInicial();
		this.vencimentoFinal = helper.getVencimentoFinal();
		this.referenciaInicialContabil = helper.getReferenciaInicialContabil();
		this.referenciaFinalContabil = helper.getReferenciaFinalContabil();
		
		this.clienteId = helper.getClienteId();
		this.clienteNome = helper.getClienteNome();
		this.clienteCpf = helper.getClienteCpf();
		this.clienteEndereco = helper.getClienteEndereco();
		this.emissao = helper.getEmissao();
		
		this.pagamento = helper.getPagamento();
		this.valor = helper.getValor();
		this.referencia = helper.getReferencia();
		this.parcelas = helper.getParcelas();
		this.parcelasTotal = helper.getParcelasTotal();
		this.vencimento = helper.getVencimento();
		this.imovel = helper.getImovel();
		this.debitoTipoDescricao = helper.getDebitoTipoDescricao();

	}

	public String getClienteCpf() {
		return clienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}

	public String getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(String clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getEmissao() {
		return emissao;
	}

	public void setEmissao(String emissao) {
		this.emissao = emissao;
	}

	public String getFinanciamentoTipoDescricao() {
		return financiamentoTipoDescricao;
	}

	public void setFinanciamentoTipoDescricao(String financiamentoTipoDescricao) {
		this.financiamentoTipoDescricao = financiamentoTipoDescricao;
	}

	public String getFinanciamentoTipoId() {
		return financiamentoTipoId;
	}

	public void setFinanciamentoTipoId(String financiamentoTipoId) {
		this.financiamentoTipoId = financiamentoTipoId;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getParcelasTotal() {
		return parcelasTotal;
	}

	public void setParcelasTotal(String parcelasTotal) {
		this.parcelasTotal = parcelasTotal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaFinalContabil() {
		return referenciaFinalContabil;
	}

	public void setReferenciaFinalContabil(String referenciaFinalContabil) {
		this.referenciaFinalContabil = referenciaFinalContabil;
	}

	public String getReferenciaInicialContabil() {
		return referenciaInicialContabil;
	}

	public void setReferenciaInicialContabil(String referenciaInicialContabil) {
		this.referenciaInicialContabil = referenciaInicialContabil;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVencimentoFinal() {
		return vencimentoFinal;
	}

	public void setVencimentoFinal(String vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	public String getVencimentoInicial() {
		return vencimentoInicial;
	}

	public void setVencimentoInicial(String vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getDebitoTipoDescricao() {
		return debitoTipoDescricao;
	}

	public void setDebitoTipoDescricao(String debitoTipoDescricao) {
		this.debitoTipoDescricao = debitoTipoDescricao;
	}

	
}
