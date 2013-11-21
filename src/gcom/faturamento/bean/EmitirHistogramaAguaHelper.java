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
package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de agua
 *
 * @author Rafael Pinto
 * @date 01/06/2007
 */
public class EmitirHistogramaAguaHelper {
	
	private String opcaoTotalizacao = null;
	private String descricaoTitulo = null;
	
	private int totalQuantidadeLigacoes;
	private double totalPercentualParcialLigacao;
	private double totalPercentualAcumuladoLigacao;
	
	//Economias
	private int totalQuantidadeEconomias;
	
	//Consumo
	private int totalQuantidadeVolumeMedido;
	private int totalQuantidadeVolumeEstimado;
	private int totalQuantidadeVolumeTotal;
	private double totalPercentualParcialConsumo;
	private double totalPercentualAcumuladoConsumo;
	private int totalMediaConsumo;
	
	//Faturamento
	private BigDecimal totalValorFaturado;
	private double totalPercentualParcialFaturamento;
	private double totalPercentualAcumuladoFaturamento;
	
	private Collection<EmitirHistogramaAguaDetalheHelper> colecaoEmitirHistogramaAguaDetalhe = null;
	
	
	public Collection<EmitirHistogramaAguaDetalheHelper> getColecaoEmitirHistogramaAguaDetalhe() {
		return colecaoEmitirHistogramaAguaDetalhe;
	}

	public void setColecaoEmitirHistogramaAguaDetalhe(
			Collection<EmitirHistogramaAguaDetalheHelper> colecaoEmitirHistogramaAguaDetalhe) {
		this.colecaoEmitirHistogramaAguaDetalhe = colecaoEmitirHistogramaAguaDetalhe;
	}

	public EmitirHistogramaAguaHelper() { }
	
	public String getDescricaoTitulo() {
		return descricaoTitulo;
	}
	
	public void setDescricaoTitulo(String descricaoTitulo) {
		this.descricaoTitulo = descricaoTitulo;
	}
	
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	public int getTotalMediaConsumo() {
		
		int media = this.totalQuantidadeVolumeTotal / this.totalQuantidadeLigacoes;
		totalMediaConsumo = media;
		
		return totalMediaConsumo;
	}
	

	public void setTotalMediaConsumo(int totalMediaConsumo) {
		this.totalMediaConsumo = totalMediaConsumo;
	}

	public double getTotalPercentualAcumuladoConsumo() {
		return totalPercentualAcumuladoConsumo;
	}

	public void setTotalPercentualAcumuladoConsumo(
			double totalPercentualAcumuladoConsumo) {
		this.totalPercentualAcumuladoConsumo = totalPercentualAcumuladoConsumo;
	}

	public double getTotalPercentualAcumuladoFaturamento() {
		return totalPercentualAcumuladoFaturamento;
	}

	public void setTotalPercentualAcumuladoFaturamento(
			double totalPercentualAcumuladoFaturamento) {
		this.totalPercentualAcumuladoFaturamento = totalPercentualAcumuladoFaturamento;
	}

	public double getTotalPercentualAcumuladoLigacao() {
		return totalPercentualAcumuladoLigacao;
	}

	public void setTotalPercentualAcumuladoLigacao(
			double totalPercentualAcumuladoLigacao) {
		this.totalPercentualAcumuladoLigacao = totalPercentualAcumuladoLigacao;
	}

	public double getTotalPercentualParcialConsumo() {
		return totalPercentualParcialConsumo;
	}

	public void setTotalPercentualParcialConsumo(
			double totalPercentualParcialConsumo) {
		this.totalPercentualParcialConsumo = totalPercentualParcialConsumo;
	}

	public double getTotalPercentualParcialFaturamento() {
		return totalPercentualParcialFaturamento;
	}

	public void setTotalPercentualParcialFaturamento(
			double totalPercentualParcialFaturamento) {
		this.totalPercentualParcialFaturamento = totalPercentualParcialFaturamento;
	}

	public double getTotalPercentualParcialLigacao() {
		return totalPercentualParcialLigacao;
	}

	public void setTotalPercentualParcialLigacao(
			double totalPercentualParcialLigacao) {
		this.totalPercentualParcialLigacao = totalPercentualParcialLigacao;
	}

	public int getTotalQuantidadeEconomias() {
		return totalQuantidadeEconomias;
	}

	public void setTotalQuantidadeEconomias(int totalQuantidadeEconomias) {
		this.totalQuantidadeEconomias = totalQuantidadeEconomias;
	}

	public int getTotalQuantidadeLigacoes() {
		return totalQuantidadeLigacoes;
	}

	public void setTotalQuantidadeLigacoes(int totalQuantidadeLigacoes) {
		this.totalQuantidadeLigacoes = totalQuantidadeLigacoes;
	}

	public int getTotalQuantidadeVolumeEstimado() {
		return totalQuantidadeVolumeEstimado;
	}

	public void setTotalQuantidadeVolumeEstimado(int totalQuantidadeVolumeEstimado) {
		this.totalQuantidadeVolumeEstimado = totalQuantidadeVolumeEstimado;
	}

	public int getTotalQuantidadeVolumeMedido() {
		return totalQuantidadeVolumeMedido;
	}

	public void setTotalQuantidadeVolumeMedido(int totalQuantidadeVolumeMedido) {
		this.totalQuantidadeVolumeMedido = totalQuantidadeVolumeMedido;
	}

	
	public int getTotalQuantidadeVolumeTotal() {
		int total = 
			this.totalQuantidadeVolumeMedido + this.totalQuantidadeVolumeEstimado ;
		
		totalQuantidadeVolumeTotal = total;
		
		return totalQuantidadeVolumeTotal;
	}

	public BigDecimal getTotalValorFaturado() {
		return totalValorFaturado;
	}

	public void setTotalValorFaturado(BigDecimal totalValorFaturado) {
		this.totalValorFaturado = totalValorFaturado;
	}
	

	
	

}