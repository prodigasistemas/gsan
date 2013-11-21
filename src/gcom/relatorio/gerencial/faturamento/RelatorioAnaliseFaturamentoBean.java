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
package gcom.relatorio.gerencial.faturamento;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioAnaliseFaturamentoBean implements RelatorioBean {

	private String descricao;

	private Integer quantidadeConta;
	
	private Integer quantidadeEconomia;
	
	private Integer volumeConsumidoAgua;
	
	private BigDecimal valorFaturadoAgua;
	
	private Integer volumeColetadoEsgoto;
	
	private BigDecimal valorFaturadoEsgoto;
	
	private BigDecimal debitosCobrados;
	
	private BigDecimal creditosRealizados;
	
	private BigDecimal totalCobrado;
	
	private BigDecimal valorImpostos;
	
	private String idQuebra;
	
	private String idAgrupamento;
	
	private JRBeanCollectionDataSource arrayDetalhesJRDetail;
	

	public RelatorioAnaliseFaturamentoBean(String descricao,
			Integer quantidadeConta, Integer quantidadeEconomia,
			Integer volumeConsumidoAgua, BigDecimal valorFaturadoAgua,
			Integer volumeColetadoEsgoto, BigDecimal valorFaturadoEsgoto,
			BigDecimal debitosCobrados, BigDecimal creditosRealizados,
			BigDecimal totalCobrado) {
		this.descricao = descricao;
		this.quantidadeConta = quantidadeConta;
		this.quantidadeEconomia = quantidadeEconomia;
		this.volumeConsumidoAgua = volumeConsumidoAgua;
		this.valorFaturadoAgua = valorFaturadoAgua;
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
		this.debitosCobrados = debitosCobrados;
		this.creditosRealizados = creditosRealizados;
		this.totalCobrado = totalCobrado;
	}

	public RelatorioAnaliseFaturamentoBean(String descricao,
			Integer quantidadeConta, Integer quantidadeEconomia,
			Integer volumeConsumidoAgua, BigDecimal valorFaturadoAgua,
			Integer volumeColetadoEsgoto, BigDecimal valorFaturadoEsgoto,
			BigDecimal debitosCobrados, BigDecimal creditosRealizados,
			BigDecimal totalCobrado, BigDecimal valorImpostos, String idQuebra,
			String idAgrupamento) {
		this.descricao = descricao;
		this.quantidadeConta = quantidadeConta;
		this.quantidadeEconomia = quantidadeEconomia;
		this.volumeConsumidoAgua = volumeConsumidoAgua;
		this.valorFaturadoAgua = valorFaturadoAgua;
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
		this.debitosCobrados = debitosCobrados;
		this.creditosRealizados = creditosRealizados;
		this.totalCobrado = totalCobrado;
		this.valorImpostos = valorImpostos;
		this.idAgrupamento = idAgrupamento;
		this.idQuebra = idQuebra;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeConta() {
		return quantidadeConta;
	}

	public void setQuantidadeConta(Integer quantidadeConta) {
		this.quantidadeConta = quantidadeConta;
	}

	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public Integer getVolumeConsumidoAgua() {
		return volumeConsumidoAgua;
	}

	public void setVolumeConsumidoAgua(Integer volumeConsumidoAgua) {
		this.volumeConsumidoAgua = volumeConsumidoAgua;
	}

	public BigDecimal getValorFaturadoAgua() {
		return valorFaturadoAgua;
	}

	public void setValorFaturadoAgua(BigDecimal valorFaturadoAgua) {
		this.valorFaturadoAgua = valorFaturadoAgua;
	}


	public BigDecimal getValorFaturadoEsgoto() {
		return valorFaturadoEsgoto;
	}

	public void setValorFaturadoEsgoto(BigDecimal valorFaturadoEsgoto) {
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
	}

	public BigDecimal getDebitosCobrados() {
		return debitosCobrados;
	}

	public void setDebitosCobrados(BigDecimal debitosCobrados) {
		this.debitosCobrados = debitosCobrados;
	}

	public BigDecimal getCreditosRealizados() {
		return creditosRealizados;
	}

	public void setCreditosRealizados(BigDecimal creditosRealizados) {
		this.creditosRealizados = creditosRealizados;
	}

	public BigDecimal getTotalCobrado() {
		return totalCobrado;
	}

	public void setTotalCobrado(BigDecimal totalCobrado) {
		this.totalCobrado = totalCobrado;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public JRBeanCollectionDataSource getArrayDetalhesJRDetail() {
		return arrayDetalhesJRDetail;
	}

	public void setArrayDetalhesJRDetail(
			JRBeanCollectionDataSource arrayDetalhesJRDetail) {
		this.arrayDetalhesJRDetail = arrayDetalhesJRDetail;
	}

	public Integer getVolumeColetadoEsgoto() {
		return volumeColetadoEsgoto;
	}

	public void setVolumeColetadoEsgoto(Integer volumeColetadoEsgoto) {
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
	}

	public String getIdQuebra() {
		return idQuebra;
	}

	public void setIdQuebra(String idQuebra) {
		this.idQuebra = idQuebra;
	}

	public String getIdAgrupamento() {
		return idAgrupamento;
	}

	public void setIdAgrupamento(String idAgrupamento) {
		this.idAgrupamento = idAgrupamento;
	}
	
	
}
