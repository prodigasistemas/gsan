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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório de emitir histograma de água por economia.
 * 
 * @author Rafael Pinto
 * @created 18/06/2007
 */
public class RelatorioHistogramaAguaEconomiaBean implements RelatorioBean {

	private String descricaoTarifa;
	private String opcaoTotalizacao;
	private String descricao;
	private String subcategoria;
	private String faixa;
	
	private String economiasMedido;
	private String consumoMedioMedido;
	private String consumoExcedenteMedido;
	private String volumeConsumoMedido;
	private String volumeFaturadoMedido;
	private String receitaMedido;
	private String ligacoesMedido;
	
	private String economiasNaoMedido;
	private String consumoMedioNaoMedido;
	private String consumoExcedenteNaoMedido;
	private String volumeConsumoNaoMedido;
	private String volumeFaturadoNaoMedido;
	private String receitaNaoMedido;
	private String ligacoesNaoMedido;

	public RelatorioHistogramaAguaEconomiaBean(
			String opcaoTotalizacao, 
		String descricao, 
		String subcategoria,
		String faixa, 
		String economiasMedido, 
		String consumoMedioMedido, 
		String consumoExcedenteMedido, 
		String volumeConsumoMedido, 
		String volumeFaturadoMedido, 
		String receitaMedido, 
		String economiasNaoMedido, 
		String consumoMedioNaoMedido, 
		String consumoExcedenteNaoMedido, 
		String volumeConsumoNaoMedido, 
		String volumeFaturadoNaoMedido, 
		String receitaNaoMedido,
		String descricaoTarifa,
		String ligacoesMedido,
		String ligacoesNaoMedido) {
		
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.descricao = descricao;
		this.subcategoria = subcategoria;
		this.faixa = faixa;
		this.economiasMedido = economiasMedido;
		this.consumoMedioMedido = consumoMedioMedido;
		this.consumoExcedenteMedido = consumoExcedenteMedido;
		this.volumeConsumoMedido = volumeConsumoMedido;
		this.volumeFaturadoMedido = volumeFaturadoMedido;
		this.receitaMedido = receitaMedido;
		this.ligacoesMedido = ligacoesMedido;
		
		this.economiasNaoMedido = economiasNaoMedido;
		this.consumoMedioNaoMedido = consumoMedioNaoMedido;
		this.consumoExcedenteNaoMedido = consumoExcedenteNaoMedido;
		this.volumeConsumoNaoMedido = volumeConsumoNaoMedido;
		this.volumeFaturadoNaoMedido = volumeFaturadoNaoMedido;
		this.receitaNaoMedido = receitaNaoMedido;
		this.ligacoesNaoMedido = ligacoesNaoMedido;
		
		this.descricaoTarifa = descricaoTarifa;
		
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsumoExcedenteMedido() {
		return consumoExcedenteMedido;
	}

	public void setConsumoExcedenteMedido(String consumoExcedenteMedido) {
		this.consumoExcedenteMedido = consumoExcedenteMedido;
	}

	public String getConsumoExcedenteNaoMedido() {
		return consumoExcedenteNaoMedido;
	}

	public void setConsumoExcedenteNaoMedido(String consumoExcedenteNaoMedido) {
		this.consumoExcedenteNaoMedido = consumoExcedenteNaoMedido;
	}

	public String getConsumoMedioMedido() {
		return consumoMedioMedido;
	}

	public void setConsumoMedioMedido(String consumoMedioMedido) {
		this.consumoMedioMedido = consumoMedioMedido;
	}

	public String getConsumoMedioNaoMedido() {
		return consumoMedioNaoMedido;
	}

	public void setConsumoMedioNaoMedido(String consumoMedioNaoMedido) {
		this.consumoMedioNaoMedido = consumoMedioNaoMedido;
	}

	public String getEconomiasMedido() {
		return economiasMedido;
	}

	public void setEconomiasMedido(String economiasMedido) {
		this.economiasMedido = economiasMedido;
	}

	public String getEconomiasNaoMedido() {
		return economiasNaoMedido;
	}

	public void setEconomiasNaoMedido(String economiasNaoMedido) {
		this.economiasNaoMedido = economiasNaoMedido;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public String getReceitaMedido() {
		return receitaMedido;
	}

	public void setReceitaMedido(String receitaMedido) {
		this.receitaMedido = receitaMedido;
	}

	public String getReceitaNaoMedido() {
		return receitaNaoMedido;
	}

	public void setReceitaNaoMedido(String receitaNaoMedido) {
		this.receitaNaoMedido = receitaNaoMedido;
	}

	public String getVolumeConsumoMedido() {
		return volumeConsumoMedido;
	}

	public void setVolumeConsumoMedido(String volumeConsumoMedido) {
		this.volumeConsumoMedido = volumeConsumoMedido;
	}

	public String getVolumeConsumoNaoMedido() {
		return volumeConsumoNaoMedido;
	}

	public void setVolumeConsumoNaoMedido(String volumeConsumoNaoMedido) {
		this.volumeConsumoNaoMedido = volumeConsumoNaoMedido;
	}

	public String getVolumeFaturadoMedido() {
		return volumeFaturadoMedido;
	}

	public void setVolumeFaturadoMedido(String volumeFaturadoMedido) {
		this.volumeFaturadoMedido = volumeFaturadoMedido;
	}

	public String getVolumeFaturadoNaoMedido() {
		return volumeFaturadoNaoMedido;
	}

	public void setVolumeFaturadoNaoMedido(String volumeFaturadoNaoMedido) {
		this.volumeFaturadoNaoMedido = volumeFaturadoNaoMedido;
	}

	public String getDescricaoTarifa() {
		return descricaoTarifa;
	}

	public void setDescricaoTarifa(String descricaoTarifa) {
		this.descricaoTarifa = descricaoTarifa;
	}
	
	public String getLigacoesMedido() {
		return ligacoesMedido;
	}

	public void setLigacoesMedido(String ligacoesMedido) {
		this.ligacoesMedido = ligacoesMedido;
	}

	public String getLigacoesNaoMedido() {
		return ligacoesNaoMedido;
	}

	public void setLigacoesNaoMedido(String ligacoesNaoMedido) {
		this.ligacoesNaoMedido = ligacoesNaoMedido;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}	
	
	
}
