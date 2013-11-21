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
public class RelatorioGerarRelacaoDebitosContasBean implements RelatorioBean {
	
	private String contaIndicadorContaRevisao;
	private String contaMesAnoReferenciaConta;
	private String contaDataVencimentoConta;
	private String contaValorOriginal;
	private String contaCodigoBarras;
	
	
	private JRBeanCollectionDataSource arrayJR;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean;	
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 * @param contaIndicadorContaRevisao
	 * @param contaMesAnoReferenciaConta
	 * @param contaDataVencimentoConta
	 * @param contaValorOriginal
	 * @param contaValorTotalOriginal
	 * @param contaCodigoBarras
	 * @param contaValorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 * @param arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 */
	public RelatorioGerarRelacaoDebitosContasBean(String contaIndicadorContaRevisao, String contaMesAnoReferenciaConta, String contaDataVencimentoConta, String contaValorOriginal,String contaCodigoBarras, Collection colecaoGerarRelacaoDebitosContasTotalBean) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
		this.contaDataVencimentoConta = contaDataVencimentoConta;
		this.contaValorOriginal = contaValorOriginal;
		this.contaCodigoBarras = contaCodigoBarras;
		
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean.addAll(colecaoGerarRelacaoDebitosContasTotalBean);
		
		
		this.arrayJR = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosContasTotalBean);
				
		
	}	

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 */
	public RelatorioGerarRelacaoDebitosContasBean() {
		
	}		
	
	/**
	 * @return Retorna o campo contaCodigoBarras.
	 */
	public String getContaCodigoBarras() {
		return contaCodigoBarras;
	}

	/**
	 * @param contaCodigoBarras O contaCodigoBarras a ser setado.
	 */
	public void setContaCodigoBarras(String contaCodigoBarras) {
		this.contaCodigoBarras = contaCodigoBarras;
	}

	/**
	 * @return Retorna o campo contaDataVencimentoConta.
	 */
	public String getContaDataVencimentoConta() {
		return contaDataVencimentoConta;
	}

	/**
	 * @param contaDataVencimentoConta O contaDataVencimentoConta a ser setado.
	 */
	public void setContaDataVencimentoConta(String contaDataVencimentoConta) {
		this.contaDataVencimentoConta = contaDataVencimentoConta;
	}

	/**
	 * @return Retorna o campo contaIndicadorContaRevisao.
	 */
	public String getContaIndicadorContaRevisao() {
		return contaIndicadorContaRevisao;
	}

	/**
	 * @param contaIndicadorContaRevisao O contaIndicadorContaRevisao a ser setado.
	 */
	public void setContaIndicadorContaRevisao(String contaIndicadorContaRevisao) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
	}

	/**
	 * @return Retorna o campo contaMesAnoReferenciaConta.
	 */
	public String getContaMesAnoReferenciaConta() {
		return contaMesAnoReferenciaConta;
	}

	/**
	 * @param contaMesAnoReferenciaConta O contaMesAnoReferenciaConta a ser setado.
	 */
	public void setContaMesAnoReferenciaConta(String contaMesAnoReferenciaConta) {
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
	}

	/**
	 * @return Retorna o campo contaValorOriginal.
	 */
	public String getContaValorOriginal() {
		return contaValorOriginal;
	}

	/**
	 * @param contaValorOriginal O contaValorOriginal a ser setado.
	 */
	public void setContaValorOriginal(String contaValorOriginal) {
		this.contaValorOriginal = contaValorOriginal;
	}

	/**
	 * @return Retorna o campo arrayJR.
	 */
	public JRBeanCollectionDataSource getArrayJR() {
		return arrayJR;
	}

	/**
	 * @param arrayJR O arrayJR a ser setado.
	 */
	public void setArrayJR(JRBeanCollectionDataSource arrayJR) {
		this.arrayJR = arrayJR;
	}

	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosContasTotalBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosContasTotalBean() {
		return arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

	/**
	 * @param arrayRelatorioGerarRelacaoDebitosContasTotalBean O arrayRelatorioGerarRelacaoDebitosContasTotalBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosContasTotalBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean) {
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

}
