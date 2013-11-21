/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.faturamento.bean;

import java.math.BigDecimal;

import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.Util;



/**
 * [CRC:1710] - Bot�es de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servir� para exibir os dados dos Debitos Autom�ticos
 * no RelatorioDadosComplementaresImovel.
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que n�o mude o que j� existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class DebitoACobrarRelatoriosHelper {
	
	public DebitoACobrarRelatoriosHelper() {
	}
	
	public DebitoACobrarRelatoriosHelper(DebitoACobrar c) {
		this.debitoCobrar = c;
	}

	private DebitoACobrar debitoCobrar;

	public DebitoACobrar getDebitoCobrar() {
		return debitoCobrar;
	}

	public void setDebitoCobrar(DebitoACobrar c) {
		this.debitoCobrar = c;
	}	
	
	public String getDescricaoTipoDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoTipo()!=null){
			return this.debitoCobrar.getDebitoTipo().getDescricao();
		}
		
		return "";
	}

	public String getAnoMesReferenciaDebito(){
		
		/**TODO:COSANPA
		 * @author Adriana Muniz
		 * @date 21/12/2011
		 * 
		 * Altera��o para evitar erro de atributo nulo quando o debito n�o possui referencia, j� que esse campo 
		 * n�o� de preenchimento obrigatorio no momento de inser��o do debito. 
		 * */
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesReferenciaDebito() != null){
			return Util.formatarAnoMesParaMesAno(this.debitoCobrar.getAnoMesReferenciaDebito());
		}
		
		return "";
	}

	public String getAnoMesCobrancaDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesCobrancaDebito() != null){
			return this.debitoCobrar.getAnoMesCobrancaDebito() !=null ? 
					Util.formatarAnoMesParaMesAno(this.debitoCobrar.getAnoMesCobrancaDebito()) : null;
		}
		
		return "";
	}

	public Short getNumeroPrestacaoCobradas(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoCobradas();
		}
		
		return null;
	}

	public Short getNumeroPrestacaoDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoDebito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getValorDebito();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoCreditoSituacaoAtual()!=null){
			return this.debitoCobrar.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}
}

