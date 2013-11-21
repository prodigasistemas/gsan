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
 * Classe que irá auxiliar no formato de entrada do relatório 
 * de emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */
public class EmitirHistogramaEsgotoEconomiaDetalheHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoFaixa = null;
	
	private int economiasMedido = 0;
	private BigDecimal consumoMedioMedido;
	private BigDecimal consumoExcedenteMedido;
	private int volumeConsumoFaixaMedido = 0;
	private int volumeFaturadoFaixaMedido = 0;
	private BigDecimal receitaMedido = new BigDecimal(0.0);
	
	private int economiasNaoMedido = 0;
	private BigDecimal consumoMedioNaoMedido;
	private BigDecimal consumoExcedenteNaoMedido;
	private int volumeConsumoFaixaNaoMedido = 0;
	private int volumeFaturadoFaixaNaoMedido = 0;
	private BigDecimal receitaNaoMedido = new BigDecimal(0.0);
	
	private boolean existeHistograma = true;
	
	private int ligacoesMedido = 0;
	private int ligacoesNaoMedido = 0;
	
	public EmitirHistogramaEsgotoEconomiaDetalheHelper() { }

	public BigDecimal getConsumoExcedenteMedido() {
		return consumoExcedenteMedido;
	}

	public void setConsumoExcedenteMedido(BigDecimal consumoExcedenteMedido) {
		this.consumoExcedenteMedido = consumoExcedenteMedido;
	}

	public BigDecimal getConsumoExcedenteNaoMedido() {
		return consumoExcedenteNaoMedido;
	}

	public void setConsumoExcedenteNaoMedido(BigDecimal consumoExcedenteNaoMedido) {
		this.consumoExcedenteNaoMedido = consumoExcedenteNaoMedido;
	}

	public BigDecimal getConsumoMedioMedido() {
		return consumoMedioMedido;
	}

	public void setConsumoMedioMedido(BigDecimal consumoMedioMedido) {
		this.consumoMedioMedido = consumoMedioMedido;
	}

	public BigDecimal getConsumoMedioNaoMedido() {
		return consumoMedioNaoMedido;
	}

	public void setConsumoMedioNaoMedido(BigDecimal consumoMedioNaoMedido) {
		this.consumoMedioNaoMedido = consumoMedioNaoMedido;
	}

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	public int getEconomiasMedido() {
		return economiasMedido;
	}

	public void setEconomiasMedido(int economiasMedido) {
		this.economiasMedido = economiasMedido;
	}

	public int getEconomiasNaoMedido() {
		return economiasNaoMedido;
	}

	public void setEconomiasNaoMedido(int economiasNaoMedido) {
		this.economiasNaoMedido = economiasNaoMedido;
	}

	public BigDecimal getReceitaMedido() {
		return receitaMedido;
	}

	public void setReceitaMedido(BigDecimal receitaMedido) {
		this.receitaMedido = receitaMedido;
	}

	public BigDecimal getReceitaNaoMedido() {
		return receitaNaoMedido;
	}

	public void setReceitaNaoMedido(BigDecimal receitaNaoMedido) {
		this.receitaNaoMedido = receitaNaoMedido;
	}

	public int getVolumeConsumoFaixaMedido() {
		return volumeConsumoFaixaMedido;
	}

	public void setVolumeConsumoFaixaMedido(int volumeConsumoFaixaMedido) {
		this.volumeConsumoFaixaMedido = volumeConsumoFaixaMedido;
	}

	public int getVolumeConsumoFaixaNaoMedido() {
		return volumeConsumoFaixaNaoMedido;
	}

	public void setVolumeConsumoFaixaNaoMedido(int volumeConsumoFaixaNaoMedido) {
		this.volumeConsumoFaixaNaoMedido = volumeConsumoFaixaNaoMedido;
	}

	public int getVolumeFaturadoFaixaMedido() {
		return volumeFaturadoFaixaMedido;
	}

	public void setVolumeFaturadoFaixaMedido(int volumeFaturadoFaixaMedido) {
		this.volumeFaturadoFaixaMedido = volumeFaturadoFaixaMedido;
	}

	public int getVolumeFaturadoFaixaNaoMedido() {
		return volumeFaturadoFaixaNaoMedido;
	}

	public void setVolumeFaturadoFaixaNaoMedido(int volumeFaturadoFaixaNaoMedido) {
		this.volumeFaturadoFaixaNaoMedido = volumeFaturadoFaixaNaoMedido;
	}

	public boolean isExisteHistograma() {
		return existeHistograma;
	}

	public void setExisteHistograma(boolean existeHistograma) {
		this.existeHistograma = existeHistograma;
	}

	public int getLigacoesMedido() {
		return ligacoesMedido;
	}

	public void setLigacoesMedido(int ligacoesMedido) {
		this.ligacoesMedido = ligacoesMedido;
	}

	public int getLigacoesNaoMedido() {
		return ligacoesNaoMedido;
	}

	public void setLigacoesNaoMedido(int ligacoesNaoMedido) {
		this.ligacoesNaoMedido = ligacoesNaoMedido;
	}
	
	
}