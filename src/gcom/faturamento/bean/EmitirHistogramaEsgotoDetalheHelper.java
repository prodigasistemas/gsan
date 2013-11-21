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

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de esgoto
 *
 * @author Rafael Pinto
 * @date 05/11/2007
 */
public class EmitirHistogramaEsgotoDetalheHelper {
	
	private String descricaoCategoria = null;
	private String descricaoSubcategoria = null;
	
	//Ligações
	private int quantidadeLigacoes;
	private double percentualParcialLigacao;
	private double percentualAcumuladoLigacao;
	
	//Economias
	private int quantidadeEconomias;
	
	//Consumo
	private int quantidadeVolumeMedido;
	private int quantidadeVolumeEstimado;
	private int quantidadeVolumeTotal;
	private double percentualParcialConsumo;
	private double percentualAcumuladoConsumo;
	private int mediaConsumo;
	
	//Faturamento
	private BigDecimal valorFaturado;
	private double percentualParcialFaturamento;
	private double percentualAcumuladoFaturamento;	
	
	public EmitirHistogramaEsgotoDetalheHelper() { }

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public int getMediaConsumo() {
		
		int media = this.quantidadeVolumeTotal / this.quantidadeLigacoes;
		mediaConsumo = media;
		
		return mediaConsumo;
	}

	public void setMediaConsumo(int mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public double getPercentualAcumuladoConsumo() {
		return percentualAcumuladoConsumo;
	}

	public void setPercentualAcumuladoConsumo(double percentualAcumuladoConsumo) {
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
	}

	public double getPercentualAcumuladoFaturamento() {
		return percentualAcumuladoFaturamento;
	}

	public void setPercentualAcumuladoFaturamento(
			double percentualAcumuladoFaturamento) {
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public double getPercentualAcumuladoLigacao() {
		return percentualAcumuladoLigacao;
	}

	public void setPercentualAcumuladoLigacao(double percentualAcumuladoLigacao) {
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
	}

	public double getPercentualParcialConsumo() {
		return percentualParcialConsumo;
	}

	public void setPercentualParcialConsumo(double percentualParcialConsumo) {
		this.percentualParcialConsumo = percentualParcialConsumo;
	}

	public double getPercentualParcialFaturamento() {
		return percentualParcialFaturamento;
	}

	public void setPercentualParcialFaturamento(double percentualParcialFaturamento) {
		this.percentualParcialFaturamento = percentualParcialFaturamento;
	}

	public double getPercentualParcialLigacao() {
		return percentualParcialLigacao;
	}

	public void setPercentualParcialLigacao(double percentualParcialLigacao) {
		this.percentualParcialLigacao = percentualParcialLigacao;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public int getQuantidadeVolumeEstimado() {
		return quantidadeVolumeEstimado;
	}

	public void setQuantidadeVolumeEstimado(int quantidadeVolumeEstimado) {
		this.quantidadeVolumeEstimado = quantidadeVolumeEstimado;
	}

	public int getQuantidadeVolumeMedido() {
		return quantidadeVolumeMedido;
	}

	public void setQuantidadeVolumeMedido(int quantidadeVolumeMedido) {
		this.quantidadeVolumeMedido = quantidadeVolumeMedido;
	}

	public int getQuantidadeVolumeTotal() {
		int total = 
			this.quantidadeVolumeMedido + this.quantidadeVolumeEstimado ;
		
		quantidadeVolumeTotal = total;
		
		return quantidadeVolumeTotal;
	}

	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}

	public void setQuantidadeVolumeTotal(int quantidadeVolumeTotal) {
		this.quantidadeVolumeTotal = quantidadeVolumeTotal;
	}
	
	

}