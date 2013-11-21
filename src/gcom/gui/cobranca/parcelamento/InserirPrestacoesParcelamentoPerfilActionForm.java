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

import org.apache.struts.action.ActionForm;

public class InserirPrestacoesParcelamentoPerfilActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String quantidadeMaximaPrestacao;
	
	private String taxaJuros;
	
	private String percentualMinimoEntrada;
	
	private String percentualTarifaMinimaImovel;
	
	private String percentualValorReparcelado;
	
	private String valorMaxPercFaixaValor;

	private String percentualPercFaixaValor;

	private String quantidadeMaxPrestacaoEspecial;
	
	private String fatorQuantidadePrestacoes;
	
	private String indicadorMediaValorContas;
	
	private String indicadorValorUltimaContaEmAtraso;
	
	public String getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public String getPercentualPercFaixaValor() {
		return percentualPercFaixaValor;
	}

	public void setPercentualPercFaixaValor(String percentualPercFaixaValor) {
		this.percentualPercFaixaValor = percentualPercFaixaValor;
	}

	public String getValorMaxPercFaixaValor() {
		return valorMaxPercFaixaValor;
	}

	public void setValorMaxPercFaixaValor(String valorMaxPercFaixaValor) {
		this.valorMaxPercFaixaValor = valorMaxPercFaixaValor;
	}

	public void setPercentualMinimoEntrada(String percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public String getPercentualTarifaMinimaImovel() {
		return percentualTarifaMinimaImovel;
	}

	public void setPercentualTarifaMinimaImovel(String percentualTarifaMinimaImovel) {
		this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
	}

	public String getQuantidadeMaximaPrestacao() {
		return quantidadeMaximaPrestacao;
	}

	public void setQuantidadeMaximaPrestacao(String quantidadeMaximaPrestacao) {
		this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public String getPercentualValorReparcelado() {
		return percentualValorReparcelado;
	}

	public void setPercentualValorReparcelado(String percentualValorReparcelado) {
		this.percentualValorReparcelado = percentualValorReparcelado;
	}

	public String getQuantidadeMaxPrestacaoEspecial() {
		return quantidadeMaxPrestacaoEspecial;
	}

	public void setQuantidadeMaxPrestacaoEspecial(
			String quantidadeMaxPrestacaoEspecial) {
		this.quantidadeMaxPrestacaoEspecial = quantidadeMaxPrestacaoEspecial;
	}

	public String getFatorQuantidadePrestacoes() {
		return fatorQuantidadePrestacoes;
	}

	public void setFatorQuantidadePrestacoes(String fatorQuantidadePrestacoes) {
		this.fatorQuantidadePrestacoes = fatorQuantidadePrestacoes;
	}

	public String getIndicadorMediaValorContas() {
		return indicadorMediaValorContas;
	}

	public void setIndicadorMediaValorContas(String indicadorMediaValorContas) {
		this.indicadorMediaValorContas = indicadorMediaValorContas;
	}

	public String getIndicadorValorUltimaContaEmAtraso() {
		return indicadorValorUltimaContaEmAtraso;
	}

	public void setIndicadorValorUltimaContaEmAtraso(
			String indicadorValorUltimaContaEmAtraso) {
		this.indicadorValorUltimaContaEmAtraso = indicadorValorUltimaContaEmAtraso;
	}
	
	
}

