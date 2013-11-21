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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 13/02/2007
 */
public class RelatorioMedicaoConsumoLigacaoAguaBean implements RelatorioBean {
	
    //Medição
	private String mesAnoMedicao;
	private String dataLeituraInformada;
	private String leituraInformada;
	private String dataLeituraFaturada;
	private String leituraFaturada;
	private String anormalidadeInformada;
	private String anormalidadeFaturada;
	private String leituraAtual;

	//Consumo
	private String mesAnoConsumo;
	private String consumoMedido;
	private String consumoFaturado;
	private String anormalidadeConsumo;
	private String diasConsumo;
	private String tipoConsumo;


	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
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
	public RelatorioMedicaoConsumoLigacaoAguaBean(String mesAnoMedicao, String dataLeituraInformada, 
			String leituraInformada, String dataLeituraFaturada, String leituraFaturada,String anormalidadeInformada,
			String anormalidadeFaturada, String leituraAtual, String mesAnoConsumo, String consumoMedido, 
			String consumoFaturado, String anormalidadeConsumo, String diasConsumo, String tipoConsumo) {
		this.mesAnoMedicao = mesAnoMedicao;
		this.dataLeituraInformada = dataLeituraInformada;
		this.leituraInformada = leituraInformada;
		this.dataLeituraFaturada = dataLeituraFaturada;
		this.leituraFaturada = leituraFaturada;
		this.anormalidadeInformada = anormalidadeInformada;
		this.anormalidadeFaturada = anormalidadeFaturada;
		this.leituraAtual = leituraAtual;
		this.mesAnoConsumo = mesAnoConsumo;
		this.consumoMedido = consumoMedido;
		this.consumoFaturado = consumoFaturado;
		this.anormalidadeConsumo = anormalidadeConsumo;
		this.diasConsumo = diasConsumo;
		this.tipoConsumo = tipoConsumo;
	}

	/**
	 * @return Retorna o campo anormalidadeConsumo.
	 */
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}


	/**
	 * @param anormalidadeConsumo O anormalidadeConsumo a ser setado.
	 */
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}


	/**
	 * @return Retorna o campo anormalidadeFaturada.
	 */
	public String getAnormalidadeFaturada() {
		return anormalidadeFaturada;
	}


	/**
	 * @param anormalidadeFaturada O anormalidadeFaturada a ser setado.
	 */
	public void setAnormalidadeFaturada(String anormalidadeFaturada) {
		this.anormalidadeFaturada = anormalidadeFaturada;
	}


	/**
	 * @return Retorna o campo anormalidadeInformada.
	 */
	public String getAnormalidadeInformada() {
		return anormalidadeInformada;
	}


	/**
	 * @param anormalidadeInformada O anormalidadeInformada a ser setado.
	 */
	public void setAnormalidadeInformada(String anormalidadeInformada) {
		this.anormalidadeInformada = anormalidadeInformada;
	}


	/**
	 * @return Retorna o campo consumoFaturado.
	 */
	public String getConsumoFaturado() {
		return consumoFaturado;
	}


	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}


	/**
	 * @return Retorna o campo consumoMedido.
	 */
	public String getConsumoMedido() {
		return consumoMedido;
	}


	/**
	 * @param consumoMedido O consumoMedido a ser setado.
	 */
	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}


	/**
	 * @return Retorna o campo dataLeituraFaturada.
	 */
	public String getDataLeituraFaturada() {
		return dataLeituraFaturada;
	}


	/**
	 * @param dataLeituraFaturada O dataLeituraFaturada a ser setado.
	 */
	public void setDataLeituraFaturada(String dataLeituraFaturada) {
		this.dataLeituraFaturada = dataLeituraFaturada;
	}


	/**
	 * @return Retorna o campo dataLeituraInformada.
	 */
	public String getDataLeituraInformada() {
		return dataLeituraInformada;
	}


	/**
	 * @param dataLeituraInformada O dataLeituraInformada a ser setado.
	 */
	public void setDataLeituraInformada(String dataLeituraInformada) {
		this.dataLeituraInformada = dataLeituraInformada;
	}


	/**
	 * @return Retorna o campo diasConsumo.
	 */
	public String getDiasConsumo() {
		return diasConsumo;
	}


	/**
	 * @param diasConsumo O diasConsumo a ser setado.
	 */
	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}


	/**
	 * @return Retorna o campo leituraAtual.
	 */
	public String getLeituraAtual() {
		return leituraAtual;
	}


	/**
	 * @param leituraAtual O leituraAtual a ser setado.
	 */
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}


	/**
	 * @return Retorna o campo leituraFaturada.
	 */
	public String getLeituraFaturada() {
		return leituraFaturada;
	}


	/**
	 * @param leituraFaturada O leituraFaturada a ser setado.
	 */
	public void setLeituraFaturada(String leituraFaturada) {
		this.leituraFaturada = leituraFaturada;
	}


	/**
	 * @return Retorna o campo leituraInformada.
	 */
	public String getLeituraInformada() {
		return leituraInformada;
	}


	/**
	 * @param leituraInformada O leituraInformada a ser setado.
	 */
	public void setLeituraInformada(String leituraInformada) {
		this.leituraInformada = leituraInformada;
	}


	/**
	 * @return Retorna o campo mesAnoConsumo.
	 */
	public String getMesAnoConsumo() {
		return mesAnoConsumo;
	}


	/**
	 * @param mesAnoConsumo O mesAnoConsumo a ser setado.
	 */
	public void setMesAnoConsumo(String mesAnoConsumo) {
		this.mesAnoConsumo = mesAnoConsumo;
	}


	/**
	 * @return Retorna o campo mesAnoMedicao.
	 */
	public String getMesAnoMedicao() {
		return mesAnoMedicao;
	}


	/**
	 * @param mesAnoMedicao O mesAnoMedicao a ser setado.
	 */
	public void setMesAnoMedicao(String mesAnoMedicao) {
		this.mesAnoMedicao = mesAnoMedicao;
	}


	/**
	 * @return Retorna o campo tipoConsumo.
	 */
	public String getTipoConsumo() {
		return tipoConsumo;
	}


	/**
	 * @param tipoConsumo O tipoConsumo a ser setado.
	 */
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

}
