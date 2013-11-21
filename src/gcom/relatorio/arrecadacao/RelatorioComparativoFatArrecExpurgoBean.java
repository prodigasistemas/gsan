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
package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
 * 
 * @author Sávio Luiz
 *
 * @date 09/12/2008
 */
public class RelatorioComparativoFatArrecExpurgoBean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String unidadeNegocio;

	private String gerenciaRegional;

	private String codigoCentroCusto;

	private String nomeLocalidade;

	private String valorItemFaturado;
	
	private String valorItemFaturadoLiquido;

	private String valorItemArrecadacao;
	
	private String valorItemFaturadoBruto;
	
	private String valorItemCancelados;

	private String valorPagamentoExpurgado;

	private String valorArrecadacaoPagamento;
	
	private String percentualArrecadadoFaturadoLiquido;
	
	private String percentualArrecadadoFaturamentoEmConta;
	
	private String qtdeItemFaturado;
	
	private String qtdeItemArrecadacao;
	
	private String qtdeItemFaturadoLiquido;
	
	

	public RelatorioComparativoFatArrecExpurgoBean(String unidadeNegocio,
			String gerenciaRegional, String codigoCentroCusto,
			String nomeLocalidade, String valorItemFaturado,String valorItemFaturadoLiquido,
			String valorItemArrecadacao,String valorItemFaturadoBruto,String valorItemCancelados,
			String valorPagamentoExpurgado,
			String valorArrecadacaoPagamento, String qtdeItemFaturado, 
			String qtdeItemArrecadacao, String qtdeItemFaturadoLiquido) {

		
		this.unidadeNegocio = unidadeNegocio;
		this.gerenciaRegional = gerenciaRegional;
		this.codigoCentroCusto = codigoCentroCusto;
		this.nomeLocalidade = nomeLocalidade;
		this.valorItemFaturado = valorItemFaturado;
		this.valorItemFaturadoLiquido = valorItemFaturadoLiquido;
		this.valorItemArrecadacao = valorItemArrecadacao;
		this.valorItemFaturadoBruto = valorItemFaturadoBruto;
		this.valorItemCancelados = valorItemCancelados;
		this.valorPagamentoExpurgado = valorPagamentoExpurgado;
		this.valorArrecadacaoPagamento = valorArrecadacaoPagamento;
		this.qtdeItemFaturado = qtdeItemFaturado;
		this.qtdeItemArrecadacao = qtdeItemArrecadacao;
		this.qtdeItemFaturadoLiquido = qtdeItemFaturadoLiquido;
	}
	

	public String getPercentualArrecadadoFaturamentoEmConta() {
		return percentualArrecadadoFaturamentoEmConta;
	}
	
	public void setPercentualArrecadadoFaturamentoEmConta(
			String percentualArrecadadoFaturamentoEmConta) {
		this.percentualArrecadadoFaturamentoEmConta = percentualArrecadadoFaturamentoEmConta;
	}

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getValorItemArrecadacao() {
		return valorItemArrecadacao;
	}

	public void setValorItemArrecadacao(String valorItemArrecadacao) {
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public String getValorItemFaturado() {
		return valorItemFaturado;
	}

	public void setValorItemFaturado(String valorItemFaturado) {
		this.valorItemFaturado = valorItemFaturado;
	}

	public String getValorArrecadacaoPagamento() {
		return valorArrecadacaoPagamento;
	}

	public void setValorArrecadacaoPagamento(String valorArrecadacaoPagamento) {
		this.valorArrecadacaoPagamento = valorArrecadacaoPagamento;
	}

	public String getValorPagamentoExpurgado() {
		return valorPagamentoExpurgado;
	}

	public void setValorPagamentoExpurgado(String valorPagamentoExpurgado) {
		this.valorPagamentoExpurgado = valorPagamentoExpurgado;
	}

	public String getValorItemFaturadoLiquido() {
		return valorItemFaturadoLiquido;
	}

	public void setValorItemFaturadoLiquido(String valorItemFaturadoLiquido) {
		this.valorItemFaturadoLiquido = valorItemFaturadoLiquido;
	}

	public String getQtdeItemArrecadacao() {
		return qtdeItemArrecadacao;
	}

	public void setQtdeItemArrecadacao(String qtdeItemArrecadacao) {
		this.qtdeItemArrecadacao = qtdeItemArrecadacao;
	}

	public String getQtdeItemFaturado() {
		return qtdeItemFaturado;
	}

	public void setQtdeItemFaturado(String qtdeItemFaturado) {
		this.qtdeItemFaturado = qtdeItemFaturado;
	}

	public String getQtdeItemFaturadoLiquido() {
		return qtdeItemFaturadoLiquido;
	}

	public void setQtdeItemFaturadoLiquido(String qtdeItemFaturadoLiquido) {
		this.qtdeItemFaturadoLiquido = qtdeItemFaturadoLiquido;
	}


	public String getValorItemFaturadoBruto() {
		return valorItemFaturadoBruto;
	}


	public void setValorItemFaturadoBruto(String valorItemFaturadoBruto) {
		this.valorItemFaturadoBruto = valorItemFaturadoBruto;
	}


	public String getValorItemCancelados() {
		return valorItemCancelados;
	}


	public void setValorItemCancelados(String valorItemCancelados) {
		this.valorItemCancelados = valorItemCancelados;
	}


	public String getPercentualArrecadadoFaturadoLiquido() {
		return percentualArrecadadoFaturadoLiquido;
	}


	public void setPercentualArrecadadoFaturadoLiquido(
			String percentualArrecadadoFaturadoLiquido) {
		this.percentualArrecadadoFaturadoLiquido = percentualArrecadadoFaturadoLiquido;
	}
	
	





	

}
