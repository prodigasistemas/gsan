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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosTotaisImovelBean implements RelatorioBean {
	
	private String valorTotalContas;
	private String valorTotalDebitos;
	private String valorToralCreditos;
	private String valorToralGuias;
	private String valorTotalMultas;
	private String valorTotalJuros;
	private String valorTotalAtualizacaoMonetaria;
	private String valorTotalAtualizado;
	
	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosTotaisImovelBean 
	 * 
	 * @param valorTotalContas
	 * @param valorTotalDebitos
	 * @param valorToralCreditos
	 * @param valorToralGuias
	 * @param valorTotalMultas
	 * @param valorTotalJuros
	 * @param valorTotalAtualizacaoMonetaria
	 * @param valorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean
	 * @param aarrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean
	 */
	public RelatorioGerarRelacaoDebitosTotaisImovelBean(String valorTotalContas, String valorTotalDebitos, String valorToralCreditos, String valorToralGuias, String valorTotalMultas, String valorTotalJuros, String valorTotalAtualizacaoMonetaria, String valorTotalAtualizado, Collection colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean) {
		this.valorTotalContas = valorTotalContas;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorToralCreditos = valorToralCreditos;
		this.valorToralGuias = valorToralGuias;
		this.valorTotalMultas = valorTotalMultas;
		this.valorTotalJuros = valorTotalJuros;
		this.valorTotalAtualizacaoMonetaria = valorTotalAtualizacaoMonetaria;
		this.valorTotalAtualizado = valorTotalAtualizado;
		
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean.add(colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean);
		
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean);
	}

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosTotaisImovelBean 
	 * 
	 * @param valorTotalContas
	 * @param valorTotalDebitos
	 * @param valorToralCreditos
	 * @param valorToralGuias
	 * @param valorTotalMultas
	 * @param valorTotalJuros
	 * @param valorTotalAtualizacaoMonetaria
	 * @param valorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean
	 * @param aarrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean
	 */
	public RelatorioGerarRelacaoDebitosTotaisImovelBean(String valorTotalContas, String valorTotalDebitos, String valorToralCreditos, String valorToralGuias, String valorTotalMultas, String valorTotalJuros, String valorTotalAtualizacaoMonetaria, String valorTotalAtualizado) {
		this.valorTotalContas = valorTotalContas;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorToralCreditos = valorToralCreditos;
		this.valorToralGuias = valorToralGuias;
		this.valorTotalMultas = valorTotalMultas;
		this.valorTotalJuros = valorTotalJuros;
		this.valorTotalAtualizacaoMonetaria = valorTotalAtualizacaoMonetaria;
		this.valorTotalAtualizado = valorTotalAtualizado;
		
	}

	/**
	 * @return Retorna o campo aarrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean() {
		return arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @param aarrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean O aarrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean) {
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean = arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean O jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean = jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @return Retorna o campo valorToralCreditos.
	 */
	public String getValorToralCreditos() {
		return valorToralCreditos;
	}

	/**
	 * @param valorToralCreditos O valorToralCreditos a ser setado.
	 */
	public void setValorToralCreditos(String valorToralCreditos) {
		this.valorToralCreditos = valorToralCreditos;
	}

	/**
	 * @return Retorna o campo valorToralGuias.
	 */
	public String getValorToralGuias() {
		return valorToralGuias;
	}

	/**
	 * @param valorToralGuias O valorToralGuias a ser setado.
	 */
	public void setValorToralGuias(String valorToralGuias) {
		this.valorToralGuias = valorToralGuias;
	}

	/**
	 * @return Retorna o campo valorTotalAtualizacaoMonetaria.
	 */
	public String getValorTotalAtualizacaoMonetaria() {
		return valorTotalAtualizacaoMonetaria;
	}

	/**
	 * @param valorTotalAtualizacaoMonetaria O valorTotalAtualizacaoMonetaria a ser setado.
	 */
	public void setValorTotalAtualizacaoMonetaria(
			String valorTotalAtualizacaoMonetaria) {
		this.valorTotalAtualizacaoMonetaria = valorTotalAtualizacaoMonetaria;
	}

	/**
	 * @return Retorna o campo valorTotalAtualizado.
	 */
	public String getValorTotalAtualizado() {
		return valorTotalAtualizado;
	}

	/**
	 * @param valorTotalAtualizado O valorTotalAtualizado a ser setado.
	 */
	public void setValorTotalAtualizado(String valorTotalAtualizado) {
		this.valorTotalAtualizado = valorTotalAtualizado;
	}

	/**
	 * @return Retorna o campo valorTotalContas.
	 */
	public String getValorTotalContas() {
		return valorTotalContas;
	}

	/**
	 * @param valorTotalContas O valorTotalContas a ser setado.
	 */
	public void setValorTotalContas(String valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

	/**
	 * @return Retorna o campo valorTotalDebitos.
	 */
	public String getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	/**
	 * @param valorTotalDebitos O valorTotalDebitos a ser setado.
	 */
	public void setValorTotalDebitos(String valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	/**
	 * @return Retorna o campo valorTotalJuros.
	 */
	public String getValorTotalJuros() {
		return valorTotalJuros;
	}

	/**
	 * @param valorTotalJuros O valorTotalJuros a ser setado.
	 */
	public void setValorTotalJuros(String valorTotalJuros) {
		this.valorTotalJuros = valorTotalJuros;
	}

	/**
	 * @return Retorna o campo valorTotalMultas.
	 */
	public String getValorTotalMultas() {
		return valorTotalMultas;
	}

	/**
	 * @param valorTotalMultas O valorTotalMultas a ser setado.
	 */
	public void setValorTotalMultas(String valorTotalMultas) {
		this.valorTotalMultas = valorTotalMultas;
	}
}
